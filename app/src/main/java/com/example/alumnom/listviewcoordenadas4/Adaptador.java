package com.example.alumnom.listviewcoordenadas4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by alumnom on 24/04/2018.
 */

public class Adaptador extends BaseAdapter {
    Context contexto;
    List<Ciudad> listaCiudades;

    public Adaptador(Context contexto, List<Ciudad> listaCiudades) {
        this.contexto = contexto;
        this.listaCiudades = listaCiudades;
    }

    @Override
    public int getCount() {
        return listaCiudades.size();
    }

    @Override
    public Object getItem(int i) {
        return listaCiudades.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos=i;
        View vista = view;
        LayoutInflater inflador = LayoutInflater.from(contexto);
        vista = inflador.inflate(R.layout.item, null);

        TextView tvNombre = (TextView) vista.findViewById(R.id.tvNombre);
        TextView tvPais = (TextView) vista.findViewById(R.id.tvPais);
        TextView tvPoblacion = (TextView) vista.findViewById(R.id.tvPoblacion);
        final TextView tvLikes = (TextView) vista.findViewById(R.id.tvLikes);
        ImageView ivCiudad = (ImageView) vista.findViewById(R.id.ivCiudad);
        ImageButton imgbUp = (ImageButton) vista.findViewById(R.id.imgbUp);

        imgbUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int likes=listaCiudades.get(pos).getLikes();
                likes++;
                listaCiudades.get(pos).setLikes(likes);
                Log.i("LIKES", String.valueOf(likes));
                tvLikes.setText(String.valueOf(likes));
            }
        });

        tvNombre.setText(listaCiudades.get(i).getNombre());
        tvPais.setText(listaCiudades.get(i).getPais());
        tvPoblacion.setText(listaCiudades.get(i).getPoblacion());
        tvLikes.setText(String.valueOf(listaCiudades.get(i).getLikes()));
        ivCiudad.setImageResource(listaCiudades.get(i).getImagen());
        ImageButton imgbDown = (ImageButton) vista.findViewById(R.id.imgbDown);

        imgbDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int likes=listaCiudades.get(pos).getLikes();
                likes--;
                listaCiudades.get(pos).setLikes(likes);
                Log.i("LIKES", String.valueOf(likes));
                tvLikes.setText(String.valueOf(likes));
            }
        });


        return vista;
    }
}
