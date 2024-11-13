package partida;

import java.util.ArrayList;
//import java.util.Scanner;

import monopoly.*;


public class Jugador {

    //Atributos:
    private String nombre; //Nombre del jugador
    private Avatar avatar; //Avatar que tiene en la partida.
    private float fortuna; //Dinero que posee.
    private float gastos; //Gastos realizados a lo largo del juego.
    private boolean enCarcel; //Será true si el jugador está en la carcel
    private int tiradasCarcel; //Cuando está en la carcel, contará las tiradas sin éxito que ha hecho allí para intentar salir (se usa para limitar el numero de intentos).
    private int vueltas; //Cuenta las vueltas dadas al tablero.                                                                                            //UTIL PARA INCREMENTO SOLARES
    private ArrayList<Casilla> propiedades; //Propiedades que posee el jugador.

    private int tiradasDados; // Contador para las tiradas de dados, atributo para estadistica.

    private float totalInvertidoPropiedades;
    private float totalPagadoImpuestos;
    private float totalRecibidoAlquiler;
    private float totalPagadoAlquiler;
    private float totalRecibidoSalida;
    private float totalRecibidoParking;
    private int vecesCarcel;
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
    public void incrementarVueltas(){
        this.vueltas++;
    }

    public ArrayList<Casilla> getPropiedades() {
        return propiedades;
    }
    public void setPropiedades(ArrayList<Casilla> propiedades) {
        this.propiedades = propiedades;
    }

    public float getTotalInvertidoPropiedades(){
        return totalInvertidoPropiedades;
    }
    public void setTotalInvertidoPropiedades(float totalInvertidoPropiedades) {
        this.totalInvertidoPropiedades = totalInvertidoPropiedades;
    }

    public float getTotalPagadoImpuestos(){
        return totalPagadoImpuestos;
    }
    public void setTotalPagadoImpuestos(float totalPagadoImpuestos) {
        this.totalPagadoImpuestos = totalPagadoImpuestos;
    }

    public float getTotalRecibidoAlquiler(){
        return totalRecibidoAlquiler;
    }
    public void setTotalRecibidoAlquiler(float totalRecibidoAlquiler) {
        this.totalRecibidoAlquiler = totalRecibidoAlquiler;
    }

    public float getTotalPagadoAlquiler(){
        return totalPagadoAlquiler;
    }
    public void setTotalPagadoAlquiler(float totalPagadoAlquiler) {
        this.totalPagadoAlquiler = totalPagadoAlquiler;
    }

    public float getTotalRecibidoSalida(){
        return totalRecibidoSalida;
    }
    public void setTotalRecibidoSalida(float totalRecibidoSalida) {
        this.totalRecibidoSalida = totalRecibidoSalida;
    }

    public float getTotalRecibidoParking(){
        return totalRecibidoParking;
    }
    public void setTotalRecibidoParking(float totalRecibidoParking) {
        this.totalRecibidoParking = totalRecibidoParking;
    }

    public int getVecesCarcel(){
        return vecesCarcel;
    }
    public void  setVecesCarcel(int vecesCarcel) {
        this.vecesCarcel = vecesCarcel;
    }
    
    public void incrementarTiradasDados() {
        this.tiradasDados++;
    }
    public int getTiradasDados() {
        return this.tiradasDados;
    }

    //Constructor vacío. Se usará para crear la banca.
    public Jugador() {
        this.nombre = "Banca";
        this.propiedades = new ArrayList<>();
        this.fortuna = 0;
        this.gastos = 0;
        this.enCarcel = false;
        this.tiradasCarcel = 0;
        this.vueltas = 0;
        this.tiradasDados = 0;
    }


    
    /*Constructor principal. Requiere parámetros:
    * Nombre del jugador, tipo del avatar que tendrá, casilla en la que empezará y ArrayList de
    * avatares creados (usado para dos propósitos: evitar que dos jugadores tengan el mismo nombre y
    * que dos avatares tengan mismo ID). Desde este constructor también se crea el avatar.
     */
    public Jugador(String nombre, String tipoAvatar, Casilla inicio, ArrayList<Avatar> avCreados) {
        for (Avatar i: avCreados){
            if (i.getJugador()!=null && i.getJugador().getNombre()!= null && i.getJugador().getNombre().equals(nombre)){
                System.out.println("No se puede crear un jugador con nombre repetido");
            }
        }

        this.nombre = nombre;
        this.avatar = new Avatar(tipoAvatar, this, inicio, avCreados);
        this.fortuna=Valor.FORTUNA_INICIAL;
        this.gastos=0.0f;
        this.enCarcel=false;
        this.tiradasCarcel=0;
        this.vueltas=0;
        this.propiedades= new ArrayList<>();
        this.tiradasDados = 0; // Inicializamos el contador de tiradas de dados

    }

    //Otros métodos:

    public int contarCasServicio(){
        int contador = 0;
        for(Casilla casilla : propiedades){
            if(casilla.getTipo().equals("Servicio")){
                contador++;
            }
        }
        return contador;
    }

    public int getCantidadPropiedades(String tipo) {
        int contador = 0;
        for (Casilla propiedad : propiedades) {
            if (propiedad.getTipo().equals(tipo)) {
                contador++;
            }
        }
        return contador;
    }
    //Método para añadir una propiedad al jugador. Como parámetro, la casilla a añadir.
    public void anhadirPropiedad(Casilla casilla) {
        this.propiedades.add(casilla);
    }

    //Método para eliminar una propiedad del arraylist de propiedades de jugador.
    public void eliminarPropiedad(Casilla casilla) {
        this.propiedades.remove(casilla);
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
        else{
            this.gastos += valor;
            this.fortuna -= valor;
        }
    }

    private Casilla ObtenerCasillaporPosicion(ArrayList<ArrayList<Casilla>> pos, int posicion){
        for (ArrayList<Casilla> lado: pos){
            for (Casilla casilla: lado){
                if(casilla.getPosicion()==posicion){
                    return casilla;
                }
            }
        }
        return null;

    }

    /*Método para establecer al jugador en la cárcel. 
    * Se requiere disponer de las casillas del tablero para ello (por eso se pasan como parámetro).*/
    public void encarcelar(ArrayList<ArrayList<Casilla>> pos) {
        this.enCarcel = true;
        tiradasCarcel=0;
        //posición de la casilla Cárcel
        int posicionCarcel=11;
        //movemos al jugador a la casilla Cárcel
        Casilla carcel=ObtenerCasillaporPosicion(pos,posicionCarcel);
        avatar.getLugar().eliminarAvatar(avatar);
        avatar.setLugar(carcel);
        carcel.anhadirAvatar(this.avatar);
        System.out.println(this.getNombre() + " ha sido enviado a la cárcel ");
    }

    public void contar_tiradas_carcel(){
        tiradasCarcel++;
    }


    private String listarPropiedades() {
        if (propiedades.isEmpty()) return "-";
        StringBuilder sb = new StringBuilder("[");
        for (Casilla propiedad : propiedades) {
            sb.append(propiedad.getNombre()).append(", ");
        }
        sb.setLength(sb.length() - 2); // Eliminar la última coma
        sb.append("]");
        return sb.toString();
    }

    public String listarEdificacionesJugador(){
        StringBuilder resultado=new StringBuilder();
        
        //recorremos las propiedades de cada jugador
        //resultado.append("\t{");
        for (Casilla casilla:propiedades){
            resultado.append("\tPropiedad: ").append(casilla.getNombre());
            resultado.append(String.format("  [Casas: %d | Hoteles: %d | Piscinas: %d | Pistas de Deporte: %d]\n", casilla.getnumCasas(), casilla.getnumHoteles(), casilla.getnumPiscinas(), casilla.getnumPistas()));
        }
        //resultado.append("\t}");
        return resultado.toString();
    }

    public String listarHipotecas(){
        StringBuilder resultado=new StringBuilder();
        for (Casilla casilla:this.getPropiedades()){
            if(casilla.getHipotecada()){
                resultado.append(casilla.getNombre());
                resultado.append(", ");
            }
        }
        return resultado.toString();
    }

    // public void incrementarDineroPropiedades(float monto){
    //     this.setTotalInvertidoPropiedades(monto);
    // }
    public void incrementarDineroPropiedades(float monto){
        this.setTotalInvertidoPropiedades(this.getTotalInvertidoPropiedades()+monto);
    }

    public void incrementarDineroImpuestos(float monto){
        this.totalPagadoImpuestos += monto;
    }
    
    public void incrementarDineroAlquiler(float monto){
        this.totalPagadoAlquiler += monto;
    }
    
    public void incrementarRecibidoAlquiler(float monto){
        this.totalRecibidoAlquiler += monto;
    }

    public void incrementarDineroSalida(float monto){
        this.totalRecibidoSalida += monto;
    }
    
    public void incrementarDineroParking(float monto){
        this.totalRecibidoParking += monto;
    }

    public void incrementarVecesCarcel(){
        vecesCarcel++;
    }    
    
//     public void vueltas(Jugador jugador){
//         jugador.setVueltas(jugador.getVueltas()+1);
//         if(jugador.getVueltas() % 4 == 0){
//             if(completarVueltas()){
//                 tablero.manejarIncremento();
//             }
//         }
//     }
//     private boolean completarVueltas(){
//         for(Jugador jugador : jugadores){
//             if(jugador.getVueltas() < 4){
//                 return false;
//             }
//         }
//         return true;
//     }


    @Override

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("     nombre: ").append(this.nombre).append(",\n");
        sb.append("     avatar: ").append(avatar.getId()).append(",\n");
        sb.append("     fortuna: ").append(this.fortuna).append(",\n");
        sb.append("     propiedades: ").append(listarPropiedades()).append(",\n");
        sb.append("     edificaciones: ").append("\n").append(listarEdificacionesJugador()).append("\n");                                        
        sb.append("     hipotecas: ").append(listarHipotecas()).append("\n");                                         //listarHipotecas()
        sb.append("}");

        return sb.toString();
    }
}