package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.coach.R;
import com.example.coach.controleur.Controle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * Initialisation
     */
    private void init(){
        Controle.getInstance(this);
        creerMenu();
    }

    private void creerMenu() {
        ecouteMenu(findViewById(R.id.btnMonIMG), CalculActivity.class);
        ecouteMenu(findViewById(R.id.btnMonHistorique), HistoActivity.class);
    }

    private void ecouteMenu(ImageButton imgButton, Class classe) {
        imgButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, classe);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }
}