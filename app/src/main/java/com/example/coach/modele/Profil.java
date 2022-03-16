package com.example.coach.modele;

public class Profil {
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

    public Profil(Integer poids, Integer taille, Integer age, Integer sexe) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;

        calculImg();
        resultImg();
    }

    public Integer getPoids() {
        return poids;
    }

    public Integer getTaille() {
        return taille;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSexe() {
        return sexe;
    }

    public float getImg() {
        return img;
    }

    public String getMessage() {
        return message;
    }

    private void calculImg() {
        float taille_cm = ((float)taille)/100;
        img = (float)((1.2*poids/(taille_cm*taille_cm)) + (0.23*age) - (10.83*sexe) - 5.4);
    }

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
