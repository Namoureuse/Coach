package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coach.*;
import com.example.coach.controleur.Controle;

public class CalculActivity extends AppCompatActivity {
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Button btnCalc;

    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        init();
    }

    private void init() {
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        rdFemme = (RadioButton) findViewById(R.id.rdFemme);
        lblIMG = (TextView) findViewById(R.id.lblIMG);
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        btnCalc = (Button) findViewById(R.id.btnCalc);

        controle = controle.getInstance(this);

        ecouteCalcul();

        //recupProfil(); enlevé en *3*, on va le faire plus loin
    }

    private void ecouteCalcul() {
        btnCalc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Integer poids = 0, taille = 0, age = 0;
                try { // vérifie que ce qu'on a saisi c'est bien des entiers, même si Android Studio le gère automatiquement
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                } catch (Exception e) {}

                Integer sexe = 0;
                if(rdHomme.isChecked()) {
                    sexe = 1;
                }

                if(poids==0 || taille==0 || age==0) {
                    //Toast permet d'afficher un message temporaire sur l95'écran
                    Toast.makeText(CalculActivity.this, "Veuillez compléter tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    afficheResult(poids, taille, age, sexe);
                }
            }
        });
    }

    private void afficheResult(Integer poids, Integer taille, Integer age, Integer sexe) {
        controle.creerProfil(poids, taille, age, sexe);
        float img = controle.getImg();
        String msg = controle.getMessage();

        if(msg=="trop faible") {
            imgSmiley.setImageResource(R.drawable.graisse);
            lblIMG.setTextColor(Color.RED);
        } else if(msg=="normal") {
            imgSmiley.setImageResource(R.drawable.normal);
            lblIMG.setTextColor(Color.GREEN);
        } else {
            imgSmiley.setImageResource(R.drawable.maigre);
            lblIMG.setTextColor(Color.RED);
        }
        lblIMG.setText(String.format("%.01f",img)+" : IMG "+msg);
    }

    public void recupProfil() { //passé en public en *3*
        if(controle.getTaille()!=null) {
            txtTaille.setText(""+controle.getTaille());
            txtPoids.setText(controle.getPoids().toString());
            txtAge.setText(controle.getAge().toString());
            if(controle.getSexe()==1){
                rdHomme.setChecked(true);
            } else {
                rdFemme.setChecked(true);
            }
        }
        btnCalc.performClick();
    }
}