package com.example.coach.modele;

import android.util.Log;

import com.example.coach.outils.AccesHTTP;
import com.example.coach.outils.AsyncResponse;

import org.json.JSONArray;

public class AccesDistant implements AsyncResponse {
    private static final String SERVERADD = "http://192.168.1.66/coach/serveurcoach.php"; //constante de classe

    /**
     * Constructeur par défaut de la classe AccesDistant.
     */
    public AccesDistant() {
        super();
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
            } else if(message[0].equals("dernier")) {
                Log.d("dernier", "*********** "+message[1]);
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
