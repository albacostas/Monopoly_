package partida;

import java.util.ArrayList;

import monopoly.*;


public class Jugador {

    //Atributos:
    private String nombre; //Nombre del jugador
    private Avatar avatar; //Avatar que tiene en la partida.
    private float fortuna; //Dinero que posee.
    private float gastos; //Gastos realizados a lo largo del juego.
    private boolean enCarcel; //Será true si el jugador está en la carcel
    private int tiradasCarcel; //Cuando está en la carcel, contará las tiradas sin éxito que ha hecho allí para intentar salir (se usa para limitar el numero de intentos).
    private int vueltas; //Cuenta las vueltas dadas al tablero.
    private ArrayList<Casilla> propiedades; //Propiedades que posee el jugador.

    // Setters y Getters de los atributos

    public String getNombre(){
        return nombre;
    }
    public void setNombres(String nombre){
        this.nombre = nombre;
    }

    public Avatar getAvatar() {
        return avatar;
    }
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public float getFortuna() {
        return fortuna;
    }
    public void setFortuna(float fortuna) {
        this.fortuna = fortuna;
    }

    public float getGastos() {
        return gastos;
    }
    public void setGastos(float gastos) {
        this.gastos = gastos;
    }

    public boolean isEnCarcel() {
        return enCarcel;
    }
    public void setEnCarcel(boolean enCarcel) {
        this.enCarcel = enCarcel;
    }

    public int getTiradasCarcel() {
        return tiradasCarcel;
    }
    public void setTiradasCarcel(int tiradasCarcel) {
        this.tiradasCarcel = tiradasCarcel;
    }

    public int getVueltas() {
        return vueltas;
    }
    public void setVueltas(int vueltas) {
        this.vueltas = vueltas;
    }

    public ArrayList<Casilla> getPropiedades() {
        return propiedades;
    }
    public void setPropiedades(ArrayList<Casilla> propiedades) {
        this.propiedades = propiedades;
    }
    
    
    //Constructor vacío. Se usará para crear la banca.
    public Jugador() {

        this.propiedades = new ArrayList<>();
        this.fortuna = 0;
        this.gastos = 0;
        this.enCarcel = false;
        this.tiradasCarcel = 0;
        this.vueltas = 0;
    }

    /*Constructor principal. Requiere parámetros:
    * Nombre del jugador, tipo del avatar que tendrá, casilla en la que empezará y ArrayList de
    * avatares creados (usado para dos propósitos: evitar que dos jugadores tengan el mismo nombre y
    * que dos avatares tengan mismo ID). Desde este constructor también se crea el avatar.
     */
    public Jugador(String nombre, String tipoAvatar, Casilla inicio, ArrayList<Avatar> avCreados) {

        this.nombre = nombre;
        for (Avatar av : avCreados){
            if(av.getTipo().equals(tipoAvatar)){
                this.avatar = av;
                break;
            }
        }

    }

    //Otros métodos:
    //Método para añadir una propiedad al jugador. Como parámetro, la casilla a añadir.
    public void anhadirPropiedad(Casilla casilla) {
        this.propiedades.add(casilla);
    }

    //Método para eliminar una propiedad del arraylist de propiedades de jugador.
    public void eliminarPropiedad(Casilla casilla) {
        if (casilla.getPropiedades() != null){
            casilla.setPropiedades(null);
            System.out.println("La propiedad da sido eliminada\n");
        }else System.err.println("La propiedad no tiene dueño.");
    }

    //Método para añadir fortuna a un jugador
    //Como parámetro se pide el valor a añadir. Si hay que restar fortuna, se pasaría un valor negativo.
    public void sumarFortuna(float valor) {
        this.fortuna += valor;

    }

    //Método para sumar gastos a un jugador.
    //Parámetro: valor a añadir a los gastos del jugador (será el precio de un solar, impuestos pagados...).
    public void sumarGastos(float valor) {

        if(valor < 0 ){
            System.out.println("El valor del gasto no puede ser negativo");
        }
        this.gastos += valor;
    }

    /*Método para establecer al jugador en la cárcel. 
    * Se requiere disponer de las casillas del tablero para ello (por eso se pasan como parámetro).*/
    public void encarcelar(ArrayList<ArrayList<Casilla>> pos) {
    }

}
