package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    public static Activity act;
    public static TextView txtDisplay;
    public static ImageView imgPok;
    public static ImageView [] imgType;
    public static int pokeId;
    protected String mistico = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pokeId =1;
        act = this;
        imgType = new ImageView[2];
        txtDisplay = findViewById(R.id.txtDisplay);
        imgPok = findViewById(R.id.imgPok);
        imgType[0] = findViewById(R.id.imgType0);
        imgType[1] = findViewById(R.id.imgType1);

        //Meme pokemon easter egg
        lePepega();

        //primera busqueda al abrir la app, funciona pero queremos el meme
       // firstSearch();

        ImageButton btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showTxtSearch();
            }
        });

        ImageButton btnTypes = findViewById(R.id.btnTypes);
        btnTypes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { showTypeSearch();
            }
        });

        Button btnRight = findViewById(R.id.btnRight);
        btnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pokeId++;
                fetchData process = new fetchData(Integer.toString(pokeId));
                process.execute();
            }
        });

        Button btnLeft = findViewById(R.id.btnLeft);
        btnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (pokeId > 1) {
                    pokeId--;
                    fetchData process = new fetchData(Integer.toString(pokeId));
                    process.execute();
                }
            }
        });
    }

    public void showTxtSearch(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Busqueda Pokemon");
        final EditText input = new EditText(this);
        input.setHint("Pokemon");
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String pokSearch = input.getText().toString();
                fetchData process = new fetchData(pokSearch);
                process.execute();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });
        alert.show();
    }

    public void showTypeSearch(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona el tipo de pokemon:")
                .setItems(R.array.pokemon_type, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int type = which + 1;
                        Log.i("logtest", "" + type);
                        //noConseguido
                    }
                });
        builder.create().show();
    }

    public void lePepega(){
        mistico += "N.º " + "????" + "\n" +
                "Name: " + "Pepegachu" + "\n" +
                "Height: " + "????" + "\n" +
                "Weight: " + "??????";
        txtDisplay.setText(this.mistico);
        imgPok.setImageResource(R.drawable.pepegachu);
        imgType[0].setImageResource((R.drawable.pepetype));
        imgType[1].setImageResource((R.drawable.electric));
    }

    public void firstSearch(){
        //primera Busqueda predeterminada
        //fetchData process = new fetchData(Integer.toString(pokeId));
        fetchData process = new fetchData(Integer.toString(1));
        process.execute();
    }
}