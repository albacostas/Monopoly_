package monopoly;

import partida.*;
import java.util.ArrayList;
import java.util.UUID; //para el identificador único


public class Edificacion {
    //Atributos
    private String id; //identificador único de cada edificación
    private String tipo; //casa, hotel, piscina o pista de deportes
    private float precio; //precio de construcción de la edificación
    //igual más adelante necesitamos hipoteca

    //contructor
    public Edificacion(String tipo, float precio){
        this.id = UUID.randomUUID().toString();
        this.tipo=tipo;
        this.precio=precio;
    }

    //getters y setters
    public String getId(){
        return id;
    }

    public String getTipo(){
        return tipo;
    }

    public float precio(){
        return precio;
    }

    public void setId(String id){
        this.id=id;
    }

    public void setTipo(String tipo){
        this.tipo=tipo;
    }

    public void setPrecio(float precio){
        this.precio=precio;
    }
    
    /* 
    @Override
    public String toString() {
        return "Edificio{" +
                "id='" + id + '\'' +
                ", tipo='" + tipo + '\'' +
                ", costo=" + precio +
                '}';
    }
    */


    
}
