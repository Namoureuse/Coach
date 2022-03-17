package com.example.coach.modele;

import java.io.Serializable;

public class Profil implements Serializable {
    //constantes
    //static => la propriété est à portée de classe, final => elle ne peut être modifiée
    private static final Integer minFemme = 15; //maigre si en dessous
    private static final Integer maxFemme = 30; // gros si au dessus
    private static final Integer minHomme = 10; // maigre si en dessous
    private static  final Integer maxHomme = 25; // gros si au dessus

    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;

    private float img;

    private String message;

    /**
     * Constrcuteur de la classe Profil.
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 0 : femme, 1 : homme
     */
    public Profil(Integer poids, Integer taille, Integer age, Integer sexe) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;

        calculImg();
        resultImg();
    }

    /**
     * Getter de poids.
     * @return le poids.
     */
    public Integer getPoids() {
        return poids;
    }

    /**
     * Getter de taille.
     * @return la taille en cm.
     */
    public Integer getTaille() {
        return taille;
    }

    /**
     * Getter de age.
     * @return l'âge.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Getter de sexe.
     * @return le sexe, 0 : femme, 1 : homme.
     */
    public Integer getSexe() {
        return sexe;
    }

    /**
     * Getter de img.
     * @return l'IMG d'une personne.
     */
    public float getImg() {
        return img;
    }

    /**
     * Getter de message.
     * @return le message correspondant en fonction de l'IMG d'une personne.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Permet le calcule de l'IMG, enregistre l'IMG calculé dans la donnée membre img;
     * (1.2*poids/(taille_cm*taille_cm)) + (0.23*age) - (10.83*sexe) - 5.4
     * sexe = 0 si femme, sexe = 1 si homme.
     */
    private void calculImg() {
        float taille_cm = ((float)taille)/100;
        img = (float)((1.2*poids/(taille_cm*taille_cm)) + (0.23*age) - (10.83*sexe) - 5.4);
    }

    /**
     * Stocke dans la donnee membre message le message à afficher en fonction de l'IMG :
     * "trop faible, normal ou trop élevé.
     */
    private void resultImg() {
        if(sexe==0) { //femme
            if(img < minFemme) {
                message = "trop faible";
            } else if (img <= maxFemme) {
                message = "normal";
            } else {
                message = "trop élevé";
            }
        } else if (sexe==1) { //homme
            if(img < minHomme) {
                message = "trop faible";
            } else if (img <= maxHomme) {
                message = "normal";
            } else {
                message = "trop élevé";
            }
        }
    }
}
