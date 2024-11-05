package monopoly;

import partida.*;
import java.util.ArrayList;

public class Edificacion {
    //Atributos
    private static int contador=0;
    private int id; //identificador único de cada edificación
    private String tipo; //casa, hotel, piscina o pista de deportes
    private float precio; //precio de construcción de la edificación
    //igual más adelante necesitamos hipoteca

    //contructor
    public Edificacion(String tipo, float precio){
        this.id = contador++;
        this.tipo=tipo;
        this.precio=precio;
    }

    //getters y setters
    public int getId(){
        return id;
    }

    public String getTipo(){
        return tipo;
    }

    public float precio(){
        return precio;
    }

    public void setId(int id){
        this.id=id;
    }

    public void setTipo(String tipo){
        this.tipo=tipo;
    }

    public void setPrecio(float precio){
        this.precio=precio;
    }
    
    
}
