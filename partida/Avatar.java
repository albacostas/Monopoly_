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

    private boolean movimientoEspecial;
    private int contador_especial;
    private boolean saltarTurno;
    private boolean doblesCoche;
    //private int vueltas;

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
    
    public boolean getMovimientoEspecial(){
        return movimientoEspecial;
    }

    public void setMovimientoEspecial(boolean movimientoEspecial) {
        this.movimientoEspecial = movimientoEspecial;
    }

    public int getContador_especial() {
        return contador_especial;
    }

    public void setContador_especial(int contador_especial) {
        this.contador_especial = contador_especial;
    }

    public boolean getSaltarTurno(){
        return saltarTurno;
    }

    public void setSaltarTurno(boolean saltarTurno) {
        this.saltarTurno = saltarTurno;
    }

    public boolean getDoblesCoche(){
        return doblesCoche;
    }

    public void setDoblesCoche(boolean doblesCoche) {
        this.doblesCoche = doblesCoche;
    }

    //Constructor vacío
    public Avatar() {
        this.jugador = new Jugador();
        this.lugar = new Casilla();
        //this.vueltas = 0; // Iniciamos las vueltas a 0.
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
        this.movimientoEspecial = false;
        this.contador_especial = 0;
        this.saltarTurno = false;
        this.doblesCoche = false;
        //this.vueltas = 0;
    }

    //A continuación, tenemos otros métodos útiles para el desarrollo del juego.
    /*Método que permite mover a un avatar a una casilla concreta. Parámetros:
    * - Un array con las casillas del tablero. Se trata de un arrayList de arrayList de casillas (uno por lado).
    * - Un entero que indica el numero de casillas a moverse (será el valor sacado en la tirada de los dados).
    * EN ESTA VERSIÓN SUPONEMOS QUE valorTirada siemrpe es positivo.
     */
    public void moverAvatar(ArrayList<ArrayList<Casilla>> casillas, int valorTirada) {
        int nuevaPosicion = this.lugar.getPosicion() + valorTirada; // Obtener la nueva posición
        //if (valorTirada<0) {
        if (nuevaPosicion<1){
            nuevaPosicion = 40 + nuevaPosicion;
            this.jugador.sumarGastos(Valor.SUMA_VUELTA); 
            this.jugador.setVueltas(this.jugador.getVueltas()-1);// Asegúrate de que la posición es válida.
            System.err.println(this.jugador.getNombre() + " ha pasado por la casilla de Salida al retroceder, pierde " + Valor.SUMA_VUELTA);
        }
        this.lugar.eliminarAvatar(this);
        // Si la nueva posición excede 40, hacemos un bucle al inicio
        if (nuevaPosicion > 40) { //if (nuevaPosicion > 40)
            nuevaPosicion = nuevaPosicion % 40;
            this.jugador.sumarFortuna(Valor.SUMA_VUELTA); 
            this.jugador.incrementarDineroSalida(Valor.SUMA_VUELTA);

            this.jugador.setVueltas(this.jugador.getVueltas()+1);// Asegúrate de que la posición es válida.
            System.err.println(this.jugador.getNombre() + " ha pasado por la casilla de Salida, recibe " + Valor.SUMA_VUELTA);
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

    /**Método para cambiar el modo de movimiento de los avatares
     * 
     */
    public boolean cambiarModo(ArrayList<ArrayList<Casilla>> casillas, int valorDado1, int valorDado2){
        String tipoAvatar = this.tipo;
        boolean solvente = true;
        int valorTirada = valorDado1 + valorDado2;
        switch (tipoAvatar) {
            case "Pelota":
                if (valorTirada>4) {
                    if (contador_especial <= valorTirada) {
                        if (contador_especial%2==1){
                            moverAvatar(casillas, contador_especial == 5 ? contador_especial : 2);             //Solo cambia al avatar de casilla
                        }
                        else{                                                                                   //ATENEA REVISAR XQ SEGURO Q HAY COSAS DE MAS AQUI Y EN CONTINUAR
                            moverAvatar(casillas, contador_especial == 5 ? contador_especial : 1);             //Solo cambia al avatar de casilla
                        }
                        solvente = this.lugar.evaluarCasilla(jugador, this.lugar.getBanca(), valorTirada);        //Cambiar valor tirada en funcion de lo q se tenga q pagar REVISAR ATENEA
                        if (contador_especial == valorTirada-1 && valorTirada%2==0) {                               //Si es par y está en el último movimiento solo sumar 1
                            contador_especial++;
                        }
                        else{
                            contador_especial += 2;
                        }
                    }
                    else if (valorTirada%2==0) {
                        moverAvatar(casillas, contador_especial == 5 ? contador_especial : 2);             //Solo cambia al avatar de casilla
                        solvente = this.lugar.evaluarCasilla(jugador, this.lugar.getBanca(), valorTirada);
                    }
                }
                else{
                    valorTirada=valorTirada*(-1);

                    if (contador_especial >= valorTirada) {
                        if (contador_especial%2==-1){
                            moverAvatar(casillas, contador_especial == -1 ? contador_especial : -2);             //Solo cambia al avatar de casilla
                        }
                        else{                                                                                   //ATENEA REVISAR XQ SEGURO Q HAY COSAS DE MAS AQUI Y EN CONTINUAR
                            moverAvatar(casillas, contador_especial == -1 ? contador_especial : -1);             //Solo cambia al avatar de casilla
                        }
                        solvente = this.lugar.evaluarCasilla(jugador, this.lugar.getBanca(), valorTirada);        //Cambiar valor tirada en funcion de lo q se tenga q pagar REVISAR ATENEA
                        if (contador_especial == valorTirada+1 && valorTirada%2==0) {                               //Si es par y está en el último movimiento solo sumar 1
                            contador_especial--;
                        }
                        else{
                            contador_especial -= 2;
                        }
                    }
                }
                break;
            case "Coche":
                if (valorTirada>4) {
                    moverAvatar(casillas, valorTirada);
                    solvente = lugar.evaluarCasilla(jugador, lugar.getBanca(), valorTirada);        //CÓDIGO TEMPORAL: SIRVE PARA QUE SE MUEVAN LOS AVATARES DE FORMA NORMAL SI NO TIENEN MOVIMIENTO ESPECIAL TODAVIA  
                }
                else{
                    if (this.doblesCoche) {
                        valorTirada=valorTirada*(-1);
                        moverAvatar(casillas, valorTirada);             //Solo cambia al avatar de casilla
                        solvente = this.lugar.evaluarCasilla(jugador, this.lugar.getBanca(), valorTirada);
                        this.setSaltarTurno(false);
                    }
                    else{
                        valorTirada=valorTirada*(-1);
                        moverAvatar(casillas, valorTirada);             //Solo cambia al avatar de casilla
                        solvente = this.lugar.evaluarCasilla(jugador, this.lugar.getBanca(), valorTirada);
                        this.setSaltarTurno(true);
                    }
                }
                break;
            case "Esfinge":
                System.out.println("El avatar Esfinge no tiene un modo de movimiento especial.");
                moverAvatar(casillas, valorTirada);
                solvente = lugar.evaluarCasilla(jugador, lugar.getBanca(), valorTirada);        //CÓDIGO TEMPORAL: SIRVE PARA QUE SE MUEVAN LOS AVATARES DE FORMA NORMAL SI NO TIENEN MOVIMIENTO ESPECIAL TODAVIA
                break;
            case "Sombrero":
                System.out.println("El avatar Sombrero no tiene un modo de movimiento especial.");
                moverAvatar(casillas, valorTirada);
                solvente = lugar.evaluarCasilla(jugador, lugar.getBanca(), valorTirada);        //CÓDIGO TEMPORAL: SIRVE PARA QUE SE MUEVAN LOS AVATARES DE FORMA NORMAL SI NO TIENEN MOVIMIENTO ESPECIAL TODAVIA
                break;
            default:
                System.out.println("Error: el avatar no tiene un tipo válido.");
                break;
        }
        return solvente;
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
