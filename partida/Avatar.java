package partida;

import monopoly.*;

import java.util.ArrayList;
import java.util.Random;

//import javax.management.AttributeChangeNotificationFilter;


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
    public void moverAvatar(ArrayList<ArrayList<Casilla>> casillas, int valorTirada) {
        int nuevaPosicion = this.lugar.getPosicion() + valorTirada; // Obtener la nueva posición
        this.lugar.eliminarAvatar(this);
        // Si la nueva posición excede 40, hacemos un bucle al inicio
        if (nuevaPosicion > 40) {
            nuevaPosicion = nuevaPosicion % 40;
            this.jugador.sumarFortuna(Valor.SUMA_VUELTA); 
            this.jugador.setVueltas(this.jugador.getVueltas()+1);// Asegúrate de que la posición es válida.
        }

    // Buscar la nueva casilla basándonos en la nueva posición
        Casilla nuevaCasilla = null;
        for (ArrayList<Casilla> lado : casillas) {
            for (Casilla casilla : lado) {
                if (casilla.getPosicion() == nuevaPosicion) {
                    nuevaCasilla = casilla; // Asignamos la nueva casilla
                    break;
                }
            }
            if (nuevaCasilla != null) {
                break; // Salimos si encontramos la nueva casilla
            }
        }

        if (nuevaCasilla != null) { // Actualizamos el lugar del avatar
            System.out.println("El avatar ha sido movido de la casilla " + this.lugar.getNombre()+ " a la casilla " + nuevaCasilla.getNombre());
            this.lugar = nuevaCasilla;
            nuevaCasilla.anhadirAvatar(this);
        }else {
            System.out.println("Error: la nueva casilla no se encontró.");
        }
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
        return "{\n" + "\tid: " + getId() + ",\n" + "\ttipo: " + this.getTipo() + ",\n" + "\tcasilla: " + (this.getLugar() != null ? this.getLugar().toString() : "null") + ",\n" +
            "\tjugador: " + getJugador().getNombre() +",\n" + "}";
    }
}
