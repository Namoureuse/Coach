package com.example.coach.modele;

import junit.framework.TestCase;

import java.util.Date;

public class ProfilTest extends TestCase {
    private Profil profil = new Profil(67,165,35,0, new Date());
    private float img = (float)32.2;
    private String message = "trop élevé";

    public void testGetImg() {
        assertEquals(img, profil.getImg(), (float)0.1);
    }

    public void testGetMessage() {
        assertEquals(message, profil.getMessage());
    }
}