package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coach.R;
import com.example.coach.controleur.Controle;
import com.example.coach.modele.Profil;

import java.util.ArrayList;
import java.util.Collections;

public class HistoActivity extends AppCompatActivity {
    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo);

        init();
    }

    private void init() {
        controle = Controle.getInstance(this);
        ecouteHisto();
        creerListe();
    }

    /**
     * Retour à la page d'accueil.
     */
    private void ecouteHisto() {
        findViewById(R.id.btnRetourDeHisto).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(HistoActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    private void creerListe() {
        ArrayList<Profil> profils;
        profils = controle.getLesProfils();

        if(profils!=null){
            Collections.sort(profils, Collections.<Profil>reverseOrder());
            ListView listView = (ListView) findViewById(R.id.lstHisto);
            HistoListAdapter adapter = new HistoListAdapter(HistoActivity.this, profils);
            listView.setAdapter(adapter);
        }
    }

    public void afficheProfil(Profil profil) {
        controle.setProfil(profil);
        Intent intent = new Intent(HistoActivity.this, CalculActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}