package com.example.alumnom.listviewcoordenadas4;

/**
 * Created by alumnom on 24/04/2018.
 */

public class Ciudad {
    String nombre, pais, poblacion, lat, longitud;
    int imagen;
    int likes;

    public Ciudad(String nombre, String pais, String poblacion, String lat, String longitud, int imagen, int likes){
        this.nombre = nombre;
        this.pais = pais;
        this.poblacion = poblacion;
        this.lat = lat;
        this.longitud = longitud;
        this.imagen = imagen;
        this.likes = likes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
