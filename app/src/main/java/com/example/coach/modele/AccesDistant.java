package com.example.coach.modele;

import android.util.Log;

import com.example.coach.controleur.Controle;
import com.example.coach.outils.AccesHTTP;
import com.example.coach.outils.AsyncResponse;
import com.example.coach.outils.MesOutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class AccesDistant implements AsyncResponse {
    private static final String SERVERADD = "http://192.168.43.79/coach/serveurcoach.php"; //constante de classe
    private Controle controle;

    /**
     * Constructeur par défaut de la classe AccesDistant.
     */
    public AccesDistant() {
        controle = Controle.getInstance(null);
    }

    /**
     * retour du serveur distant
     * @param output
     */
    @Override
    public void processFinish(String output) {
        Log.d("serveur", "**************" + output);
        String[] message = output.split("%");
        //-> message[0] contient "enreg", "dernier" ou "Erreur"
        //-> message[1] contient le retour du serveur
        if(message.length > 1) {
            if(message[0].equals("enreg")) {
                Log.d("enreg", "*********** "+message[1]);
            } else if(message[0].equals("tous")) {
                try {
                    JSONArray infos = new JSONArray(message[1]);
                    ArrayList<Profil> lesProfils = new ArrayList<Profil>();
                    for(int i = 0; i < infos.length(); i++) {
                        JSONObject info = new JSONObject(infos.get(i).toString());
                        Date dateMesure = MesOutils.convertStringToDate(info.getString("datemesure"),
                                "yyyy-MM-dd hh:mm:ss");
                        Integer poids = info.getInt("poids");
                        Integer taille = info.getInt("taille");
                        Integer age = info.getInt("age");
                        Integer sexe = info.getInt("sexe");
                        Profil profil = new Profil(poids, taille, age, sexe, dateMesure);
                        lesProfils.add(profil);
                    }
                    controle.setLesProfils(lesProfils);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if(message[0].equals("Erreur !")) {
                Log.d("erreur", "*********** "+message[1]);
            }
        }
    }

    /**
     * envoi de données vers le serveur distant
     * @param operation
     * @param lesDonneesJSON
     */
    public void envoi(String operation, JSONArray lesDonneesJSON) {
        AccesHTTP accesDonnees = new AccesHTTP();
        accesDonnees.delegate = this;
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());

        Log.d("test", "########################" + lesDonneesJSON.toString());

        accesDonnees.execute(SERVERADD);
    }
}
