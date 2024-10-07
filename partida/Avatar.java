package partida;

import monopoly.*;

import java.util.ArrayList;
import java.util.Random;


public class Avatar {

    //Atributos
    private String id; //Identificador: una letra generada aleatoriamente.
    private String tipo; //Sombrero, Esfinge, Pelota, Coche
    private Jugador jugador; //Un jugador al que pertenece ese avatar.
    private Casilla lugar; //Los avatares se sitúan en casillas del tablero.


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Jugador getJugador() {
        return jugador;
    }
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Casilla getLugar() {
        return lugar;
    }
    public void setLugar(Casilla lugar) {
        this.lugar = lugar;
    }
    
    
    //Constructor vacío
    public Avatar() {
        this.jugador = new Jugador();
        this.lugar = new Casilla();
    }

    /*Constructor principal. Requiere éstos parámetros:
    * Tipo del avatar, jugador al que pertenece, lugar en el que estará ubicado, y un arraylist con los
    * avatares creados (usado para crear un ID distinto del de los demás avatares).
     */
    public Avatar(String tipo, Jugador jugador, Casilla lugar, ArrayList<Avatar> avCreados) {
        this.tipo = tipo;
        this.jugador = jugador;
        this.lugar = lugar;
        this.generarId(avCreados);
    }

    //A continuación, tenemos otros métodos útiles para el desarrollo del juego.
    /*Método que permite mover a un avatar a una casilla concreta. Parámetros:
    * - Un array con las casillas del tablero. Se trata de un arrayList de arrayList de casillas (uno por lado).
    * - Un entero que indica el numero de casillas a moverse (será el valor sacado en la tirada de los dados).
    * EN ESTA VERSIÓN SUPONEMOS QUE valorTirada siemrpe es positivo.
     */
    public void moverAvatar(ArrayList<ArrayList<Casilla>> casillas, int valorTirada){
        Casilla casillaActual=this.lugar;
        for (int i=0; i<valorTirada; i++){
            casillaActual=ObtenerSiguienteCasilla(casillas, casillaActual);
        }
        //Actualizamos 
        this.lugar=casillaActual;
        System.out.println("El avatar ha sido movido a la casilla "+ this.lugar.getNombre());
    }

    private Casilla ObtenerSiguienteCasilla(ArrayList<ArrayList<Casilla>> casillas, Casilla casillaActual){
        int posActual=casillaActual.getPosicion();
        //Incrementamos la posción una unidad.
        int nuevaPosicion=posActual+1;
        //si la posición excede el número 40 (vuelta completada), regresamos a la casilla 1.
        if(nuevaPosicion>40){
            nuevaPosicion=1;
        } 
        //buscamos la nueva casilla actual basada en la posición
        for(ArrayList<Casilla> lado : casillas){
            for (Casilla casilla: lado){
                if (casilla.getPosicion()==nuevaPosicion){
                    return casilla;
                }
            }
        }

        throw new IllegalArgumentException("La casilla no se encuentra");

    }


    /*Método que permite generar un ID para un avatar. Sólo lo usamos en esta clase (por ello es privado).
    * El ID generado será una letra mayúscula. Parámetros:
    * - Un arraylist de los avatares ya creados, con el objetivo de evitar que se generen dos ID iguales.
     */
    private void generarId(ArrayList<Avatar> avCreados) {
        Random random = new Random();
        String newID;
        boolean idUnico;

        do {
            newID = String.valueOf((char)(random.nextInt(26) +'A'));
            idUnico = true;

            for (Avatar avatar : avCreados){
                if(avatar.getId().equals(newID)){
                    idUnico = false;
                    break;
                }
            }
        } while(!idUnico);

        this.id = newID;
    }

    @Override
    public String toString() {
        return "{\n\tid: " + this.getId() + ",\n\ttipo: " + this.getTipo() + ",\n\tcasilla: " + this.getLugar() +",\n\tjugador: " + this.getJugador() + "\n}";
    }
}
