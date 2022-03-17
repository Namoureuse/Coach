package com.example.coach.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.coach.outils.MesOutils;
import com.example.coach.outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccesLocal {
    private String nomBase="bdCoach.sqlite";
    private Integer versionBase=1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    public AccesLocal(Context context) {
        accesBD = new MySQLiteOpenHelper(context, nomBase, versionBase);
    }

    public void ajout(Profil profil) {
        /*this.bd = accesBD.getWritableDatabase();

        String req = "insert into profil (datemesure, poids, taille, age, sexe) values";
        req += "(\"" + profil.getDateMesure() + "\"" + profil.getPoids() + "," + profil.getTaille()
                + ","  + profil.getAge() + "," + profil.getSexe() + ")";

        bd.execSQL(req);*/

        ContentValues values = new ContentValues();
        values.put("dateMesure", profil.getDateMesure().toString());
        values.put("poids", profil.getPoids());
        values.put("taille", profil.getTaille());
        values.put("age", profil.getAge());
        values.put("sexe", profil.getSexe());
        bd.insert("profil", null, values);
        bd.close();
    }

    public Profil recupDernier() {
        Profil profil=null;

        bd = accesBD.getReadableDatabase();

        String req = "select * from profil";

        Cursor curseur = bd.rawQuery(req, null);
        curseur.moveToLast();

        if(!curseur.isAfterLast()) {
            Date dateMesure = MesOutils.convertStringToDate(curseur.getString(0));
            Log.d("date", "***************** dateMesure = "+dateMesure);
            Integer poids = curseur.getInt(1);
            Integer taille = curseur.getInt(2);
            Integer age = curseur.getInt(3);
            Integer sexe = curseur.getInt(4);
            profil = new Profil(poids, taille, age, sexe, dateMesure);
        }
        curseur.close();
        return profil;
    }
}
