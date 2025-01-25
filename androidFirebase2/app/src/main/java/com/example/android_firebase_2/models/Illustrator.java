package com.example.android_firebase_2.models;

import java.io.Serializable;

public class Illustrator implements Serializable {
    public String titulo;
    public String descripcion;
    public String imagen;

    // se necesita para que firebase pueda deserializar los datos
    public Illustrator() {}

    public Illustrator(String titulo, String descripcion, String imagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }
     // genero los getters de los atributos como en el ejemplo
    // Permiten acceder a las propiedades del objeto, utilizados en Data Binding y lógica de negocio
    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }


}
