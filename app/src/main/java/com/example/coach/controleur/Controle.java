package com.example.coach.controleur;

import com.example.coach.modele.Profil;

public final class Controle {
    private  static Controle instance=null;
    private static Profil profil;

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
    public static  final Controle getInstance() {
        if(Controle.instance == null) {
            Controle.instance = new Controle();
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
        profil = new Profil(poids, taille, age, sexe);
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
}
