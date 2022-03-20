package com.example.coach.controleur;

import android.content.Context;
import android.util.Log;

import com.example.coach.modele.AccesDistant;
import com.example.coach.modele.Profil;
import com.example.coach.vue.CalculActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;

//*1* : Serialisation   *2* : Acces BDD locale (au téléphone)   *3* : Acces BDD distante (phpmyadmin)

public final class Controle {
    private  static Controle instance=null;
    private static Profil profil;
    private static String nomFic="saveProfil";
    //private static AccesLocal accesLocal; //*2*
    private static AccesDistant accesDistant;
    private static Context context;
    private ArrayList<Profil> lesProfils;

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
            if(context != null) { // *3*
                Controle.context = context;
            }
            accesDistant = new AccesDistant();
            accesDistant.envoi("tous", new JSONArray());
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
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe) {
        Profil unProfil = new Profil(poids, taille, age, sexe, new Date());
        accesDistant.envoi("enreg", unProfil.convertToJSONArray()); /*3*/
        lesProfils.add(unProfil);
        //accesLocal.ajout(profil); //*2*
        //Serializer.serialize(nomFic, profil, context); //*1*
    }

    /**
     * Getter de l'IMG d'un profil.
     * @return l'IMG calculé.
     */
    public float getImg() {
        if(lesProfils.isEmpty()) {
            return 0;
        } else{
            return lesProfils.get(lesProfils.size() - 1).getImg();
        }
    }

    /**
     * Getter du message d'un profil.
     * @return le message en fonction de l'IMG.
     */
    public String getMessage() {
        if(lesProfils.isEmpty()) {
            return "";
        } else {
            return profil.getMessage();
        }
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

    /**
     * Getter du tableau lesProfils.
     * @return un tableau de profils.
     */
    public ArrayList<Profil> getLesProfils() {
        return lesProfils;
    }

    /**
     * Setter du tableau lesProfils.
     * @param lesProfils
     */
    public void setLesProfils(ArrayList<Profil> lesProfils) {
        this.lesProfils = lesProfils;
    }

    /*private static void recupSerialize(Context context) {
        profil = (Profil) Serializer.deSerialize(nomFic, context);
    }*/

    public void setProfil(Profil profil) {
        Controle.profil = profil;
        //((CalculActivity)context).recupProfil();
    }

    public void delProfil(Profil profil) {
        accesDistant.envoi("suppr", profil.convertToJSONArray());
        lesProfils.remove(profil);
    }
}
