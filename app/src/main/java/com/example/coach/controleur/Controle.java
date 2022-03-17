package com.example.coach.controleur;

import android.content.Context;

import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;

public final class Controle {
    private  static Controle instance=null;
    private static Profil profil;
    private static String nomFic="saveProfil";

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
        if(Controle.instance == null) {
            Controle.instance = new Controle();
            recupSerialize(context);
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
        profil = new Profil(poids, taille, age, sexe);
        Serializer.serialize(nomFic, profil, context);
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

    private static void recupSerialize(Context context) {
        profil = (Profil) Serializer.deSerialize(nomFic, context);
    }
}
