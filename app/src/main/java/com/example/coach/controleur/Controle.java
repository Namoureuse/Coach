package com.example.coach.controleur;

import android.content.Context;
import android.util.Log;

import com.example.coach.modele.AccesDistant;
import com.example.coach.modele.AccesLocal;
import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;

import org.json.JSONArray;

import java.util.Date;

//*1* : Serialisation   *2* : Acces BDD locale (au téléphone)   *3* : Acces BDD distante (phpmyadmin)

public final class Controle {
    private  static Controle instance=null;
    private static Profil profil;
    private static String nomFic="saveProfil";
    //private static AccesLocal accesLocal; //*2*
    private static AccesDistant accesDistant;

    /**
     * Constructeur par défaut de la classe Controle.
     */
    private Controle() {
        super();
    }

    /**
     * Récupère l'instance du contrôleur, si pas d'instance change sa valeur.
     * @return l'instance.
     */
    public static  final Controle getInstance(Context context) {
        Log.d("profil2", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        if(Controle.instance == null) {
            Controle.instance = new Controle();
            //accesLocal = new AccesLocal(context);
            accesDistant = new AccesDistant();
            accesDistant.envoi("dernier", new JSONArray());
            Log.d("profil1", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            //profil = accesLocal.recupDernier(); //*2*
           //recupSerialize(context); //*1*
        }
        return Controle.instance;
    }

    /**
     * Création du profil
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 0 : femme, 1 : homme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context context) {
        profil = new Profil(poids, taille, age, sexe, new Date());
        accesDistant.envoi("enreg", profil.convertToJSONArray()); /*3*/
        //accesLocal.ajout(profil); //*2*
        //Serializer.serialize(nomFic, profil, context); //*1*
    }

    /**
     * Getter de l'IMG d'un profil.
     * @return l'IMG calculé.
     */
    public float getImg() {
        return profil.getImg();
    }

    /**
     * Getter du message d'un profil.
     * @return le message en fonction de l'IMG.
     */
    public String getMessage() {
        return profil.getMessage();
    }

    public Integer getPoids() {
        if(profil==null) return null;
        else return profil.getPoids();
    }

    public Integer getTaille() {
        if(profil==null) return null;
        else return profil.getTaille();
    }

    public Integer getAge() {
        if(profil==null) return null;
        else return profil.getAge();
    }

    public Integer getSexe() {
        if(profil==null) return null;
        else return profil.getSexe();
    }

    /*private static void recupSerialize(Context context) {
        profil = (Profil) Serializer.deSerialize(nomFic, context);
    }*/
}
