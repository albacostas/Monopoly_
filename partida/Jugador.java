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
        this.nombre = "Banca";
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

    }

    //Otros métodos:
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
        avatar.setLugar(carcel);
        carcel.anhadirAvatar(this.avatar);
        System.out.println(this.getNombre() + " ha sido enviado a la cárcel ");
        
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

    @Override
    // public String toString() {
    //     return "{\n\tnombre: " + this.getNombre() + ",\n\tavatar: " + (avatar != null ? avatar.getId() : "-") + ",\n\tfortuna: " + this.getFortuna() + ",\n\tpropiedades: " + listarPropiedades(this.propiedades) + ",\n\thipotecas: -" + ",\n\tedificios: -" + "\n}";
    // }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("     nombre: ").append(this.nombre).append(",\n");
        sb.append("     avatar: ").append(avatar.getId()).append(",\n");
        sb.append("     fortuna: ").append(this.fortuna).append(",\n");
        sb.append("     propiedades: ").append(listarPropiedades()).append(",\n");
        //sb.append("hipotecas: ").append(listarHipotecas()).append(",\n");
        //sb.append("edificios: ").append(listarEdificios()).append("\n");
        //sb.append("}");
        return sb.toString();
    }
}
  
    
