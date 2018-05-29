package com.example.alumnom.listviewcoordenadas4;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listaCiudades;
    ArrayList<Ciudad> lista;
    TextView tvNombre, tvPais, tvPoblacion, tvLat, tvLong, tvLikes;
    Adaptador miAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaCiudades = findViewById(R.id.lvPaises);
        tvNombre = findViewById(R.id.tvNombre);
        tvPais = findViewById(R.id.tvPais);
        tvPoblacion = findViewById(R.id.tvPoblacion);
        tvLikes = findViewById(R.id.tvLikes);
        lista= new ArrayList<>();

        lista.add(new Ciudad("Bruselas", "Bélgica", "177 307", "50.8467", "4.3547", R.drawable.bruselas,  0));
        lista.add(new Ciudad("Budapest", "Hungría", "1 752 704", "47.498333", "19.040833", R.drawable.budapest,  0));
        lista.add(new Ciudad("Dublín", "Irlanda", "527 612", "53.3425", "-6.265833", R.drawable.dublin, 0));
        lista.add(new Ciudad("Florencia", "Italia", "382 258", "43.771389", "11.254167", R.drawable.florencia,  0));
        lista.add(new Ciudad("París", "Francia", "10 516 110", "48.856578", "2.351828", R.drawable.paris,  0));
        lista.add(new Ciudad("Praga", "Chequia", "1 280 508", "50.088611", "14.421389", R.drawable.praga,  0));
        lista.add(new Ciudad("Sevilla", "España", "689 434", "37.383333", "-5.983333", R.drawable.sevilla,  0));
        lista.add(new Ciudad("Viena", "Austria", "1 840 573", "48.20833", "16.373064", R.drawable.viena,  0));

        String[] archivos=fileList();
        String cadena="";

        List<String> arrayLikes;

        if(existe(archivos, "likes.txt")){
            try {
                InputStreamReader archivo=new InputStreamReader(openFileInput("likes.txt"));
                BufferedReader br=new BufferedReader(archivo);
                String todo="";
                String linea=br.readLine();
                while (linea != null){
                    todo += linea + "\n";
                    linea=br.readLine();
                }
                br.close();
                archivo.close();
                cadena=todo;

                arrayLikes=convertToArray(cadena);
                for(int i=0; i<lista.size(); i++)
                    lista.get(i).setLikes(Integer.parseInt(arrayLikes.get(i)));

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        else {
            for(int i=0; i<lista.size(); i++)
                lista.get(i).setLikes(0);
        }

        miAdaptador = new Adaptador(getApplicationContext(), lista);
        listaCiudades.setAdapter(miAdaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menuopciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.itemOrdenAlfabetico:
                ordenarPorNombre(lista);
                break;
            case R.id.itemOrdenLikes:
                ordenarPorLikes(lista);
                break;
        }

        miAdaptador.notifyDataSetChanged();
        return true;
    }

    public void ordenarPorNombre(ArrayList <Ciudad> ciudades){
        Collections.sort(ciudades, new Comparator<Ciudad>() {
            @Override
            public int compare(Ciudad ciudad, Ciudad t1) {
                return ciudad.getNombre().compareTo(t1.getNombre());
            }
        });
    }

    public void ordenarPorLikes(ArrayList <Ciudad> ciudades){
        Collections.sort(ciudades, new Comparator<Ciudad>() {
            @Override
            public int compare(Ciudad ciudad, Ciudad t1) {
                return Integer.valueOf(ciudad.getLikes()).compareTo(Integer.valueOf(t1.getLikes()));
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        ArrayList<String> arrayLikes=new ArrayList<String>();

        for(int i=0; i<lista.size(); i++)
            arrayLikes.add(i,String.valueOf(lista.get(i).getLikes()));

        String cadena=convertToString(arrayLikes);

        grabar(cadena);

        finish();
    }


    public List<String> convertToArray(String string){
        String []cadenas=string.split("\n");
        List<String>list= Arrays.asList(cadenas);
        return list;
    }

    public boolean existe(String[] archivos, String archbusca){
        for(int f=0; f<archivos.length; f++){
            if(archbusca.equals(archivos[f]))
                return true;
        }
        return false;
    }

    public String convertToString(ArrayList<String> lista){
        StringBuilder sb=new StringBuilder();
        String delimitador="";
        for(String s: lista){
            sb.append(delimitador);
            sb.append(s);
            delimitador="\n";
        }
        return sb.toString();
    }

    public void grabar(String cadena){
        try {
            OutputStreamWriter archivo=new OutputStreamWriter(openFileOutput("likes.txt", Activity.MODE_PRIVATE));
            archivo.write(cadena);
            archivo.flush();
            archivo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
