package com.example.mylogin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static android.widget.Toast.LENGTH_LONG;

public class SecondActivity extends AppCompatActivity {

      RadioButton dinarToEuro , euroToDinar;
      EditText entree;
      TextView sortie;
      Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        entree = (EditText) findViewById(R.id.monnaie);
        sortie = (TextView) findViewById(R.id.resultat);

        euroToDinar =(RadioButton) findViewById((R.id.cnv1));
        dinarToEuro =(RadioButton) findViewById((R.id.cnv2));

        btn =(Button) findViewById(R.id.convertir);

        sortie.setText("Resultat: ");
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.showContextMenu();
                return false;
            }});
        btn.setOnCreateContextMenuListener (this) ;
    }



    private float euroToDinar(float valeurEuro){
        return (float)(valeurEuro * 2.95);
    }
    private float dinarToEuro(float valeurDinar){
        return (float)(valeurDinar * 0.34);
    }

     public void convertir (View v) {

         if (entree.getText().toString().equals("")) {
             AlertDialog alertDialog;
             alertDialog = new AlertDialog.Builder(this).create();
             alertDialog.setTitle("Champs Manquant");
             alertDialog.setMessage("Vous devez insérer une valeur a convertir !!!");
             alertDialog.setIcon(R.drawable.alert3);
             alertDialog.setButton("ok", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     dialog.dismiss();
                 }
             });
             alertDialog.show();
         } else {
             float valeurInitiale = Float.valueOf(entree.getText().toString());
             float resultat;

             if (euroToDinar.isChecked()) {
                 resultat = euroToDinar(valeurInitiale);
                 sortie.setText("Resultat: "+ String.valueOf(resultat));
             } else if( dinarToEuro.isChecked()){
                 resultat = dinarToEuro(valeurInitiale);
                 sortie.setText("Resultat: "+ String.valueOf(resultat));

             }
             else{
                 Toast.makeText(getApplicationContext(),"choisir la type de conversion", LENGTH_LONG).show();
             }
         }
     }


        public void onCreateContextMenu(ContextMenu menu , View v, ContextMenu.ContextMenuInfo menuInfo){
        menu.add(0,1,0,"Conversion euro -> dinar");
        menu.add(0,2,0,"Conversion dinar -> euro");
        menu.add(0,3,0,"Conversion C <-> F");
        menu.add(0,4,0,"Conversion F <-> C");
        menu.add(0,5,0,"Quitter");
      }
    @Override
      public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1:
                Toast.makeText(this,"0.3",Toast.LENGTH_LONG).show();
                break;

            case 2:
                Toast.makeText(this,"2.99235",Toast.LENGTH_LONG).show();
                break;

            case 3:
                Intent i =new Intent(SecondActivity.this,ConvertionTemperatureActivity.class);
                startActivity(i);
                break;

            case 4:
                Intent j =new Intent(SecondActivity.this,ConvertionTemperatureActivity.class);
                startActivity(j);

            case 5:
                this.finish();
                break;
        }

          return super.onContextItemSelected(item);
      }




    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0,1,0, "Conversion C <=> F");
        menu.add(0,2,0, "Quitter");
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1:
                Intent i = new Intent(SecondActivity.this, ConvertionTemperatureActivity.class);
                startActivity(i);
                break;
            case 2:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}





