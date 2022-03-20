package com.example.coach.vue;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.coach.R;
import com.example.coach.controleur.Controle;
import com.example.coach.modele.Profil;
import com.example.coach.outils.MesOutils;

import java.util.ArrayList;

public class HistoListAdapter extends BaseAdapter {
    private ArrayList<Profil> lesProfils;
    private LayoutInflater inflater;
    private Context context;

    public HistoListAdapter(Context context, ArrayList<Profil> profils) {
        this.context = context;
        this.lesProfils = profils;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lesProfils.size();
    }

    @Override
    public Object getItem(int i) {
        return lesProfils.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewProperties viewProperties;
        if(convertView==null) {
            viewProperties = new ViewProperties();
            convertView = inflater.inflate(R.layout.layout_liste_histo, null);
            viewProperties.txtListDate = (TextView)convertView.findViewById(R.id.txtListDate);
            viewProperties.txtListIMG = (TextView)convertView.findViewById(R.id.txtListIMG);
            viewProperties.btnListSuppr = (ImageButton) convertView.findViewById(R.id.btnListSuppr);
            convertView.setTag(viewProperties);
        } else {
            viewProperties = (ViewProperties) convertView.getTag();
        }
        viewProperties.txtListIMG.setText(MesOutils.format2Decimal(lesProfils.get(position).getImg()));
        viewProperties.txtListDate.setText(MesOutils.convertDateToString(lesProfils.get(position).getDateMesure()));

        //Gestion de la suppression d'une ligne
        viewProperties.btnListSuppr.setTag(position);
        viewProperties.btnListSuppr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int indice = (int)v.getTag();
                Controle controle = Controle.getInstance(null);
                controle.delProfil(lesProfils.get(indice));
                notifyDataSetChanged();
            }
        });

        //Gestion des détails d'un item de la liste
        viewProperties.txtListDate.setTag(position);
        viewProperties.txtListDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int indice = (int)v.getTag();
                Controle controle = Controle.getInstance(null);
                ((HistoActivity)context).afficheProfil(lesProfils.get(indice));
            }
        });

        viewProperties.txtListIMG.setTag(position);
        viewProperties.txtListIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int indice = (int)v.getTag();
                ((HistoActivity)context).afficheProfil(lesProfils.get(indice));
            }
        });


        return convertView;
    }

    private class ViewProperties {
        TextView txtListDate;
        TextView txtListIMG;
        ImageButton btnListSuppr;
    }
}
