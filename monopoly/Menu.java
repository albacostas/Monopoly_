package monopoly;

import java.util.ArrayList;
import partida.*;

public class Menu {

    //Atributos
    private ArrayList<Jugador> jugadores; //Jugadores de la partida.
    private ArrayList<Avatar> avatares; //Avatares en la partida.
    private int turno = 0; //Índice correspondiente a la posición en el arrayList del jugador (y el avatar) que tienen el turno
    private int lanzamientos; //Variable para contar el número de lanzamientos de un jugador en un turno.
    private Tablero tablero; //Tablero en el que se juega.
    private Dado dado1; //Dos dados para lanzar y avanzar casillas.
    private Dado dado2;
    private Jugador banca; //El jugador banca.
    private boolean tirado; //Booleano para comprobar si el jugador que tiene el turno ha tirado o no.
    private boolean solvente; //Booleano para comprobar si el jugador que tiene el turno es solvente, es decir, si ha pagado sus deudas.



    // Métodos Getter y Setter para cada atributo

    
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }   

    public ArrayList<Avatar> getAvatares() {
        return avatares;
    }
    public void setAvatares(ArrayList<Avatar> avatares) {
        this.avatares = avatares;
    }

    public int getTurno() {
        return turno;
    }
    public void setTurno(int turno) {
        this.turno = turno;
    }   

    public int getLanzamientos() {
        return lanzamientos;
    }
    public void setLanzamientos(int lanzamientos) {
        this.lanzamientos = lanzamientos;
    }

    public Tablero getTablero() {
        return tablero;
    }
    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Dado getDado1() {
        return dado1;
    }
    public void setDado1(Dado dado1) {
        this.dado1 = dado1;
    }

    public Dado getDado2() {
        return dado2;
    }
    public void setDado2(Dado dado2) {
        this.dado2 = dado2;
    }


    public Jugador getBanca() {
        return banca;
    }
    public void setBanca(Jugador banca) {
        this.banca = banca;
    }

    public boolean isTirado() {
        return tirado;
    }
    public void setTirado(boolean tirado) {
        this.tirado = tirado;
    }

    public boolean isSolvente() {
        return solvente;
    }
    public void setSolvente(boolean solvente) {
        this.solvente = solvente;
        avatares = new ArrayList<>();
        tablero = new tablero();
    }


    // Método para inciar una partida: crea los jugadores y avatares.
    private void iniciarPartida() {
        jugadores = new ArrayList<>();

    }
    
    /*Método que interpreta el comando introducido y toma la accion correspondiente.
    * Parámetro: cadena de caracteres (el comando).
    */
    private void analizarComando(String comando) {
    }

    /*Método que realiza las acciones asociadas al comando 'describir jugador'.
    * Parámetro: comando introducido
     */
    private void descJugador(String[] partes) {
    }

    /*Método que realiza las acciones asociadas al comando 'describir avatar'.
    * Parámetro: id del avatar a describir.
    */
    private void descAvatar(String ID) {
    }

    /* Método que realiza las acciones asociadas al comando 'describir nombre_casilla'.
    * Parámetros: nombre de la casilla a describir.
    */
    private void descCasilla(String nombre) {
    }

    //Método que ejecuta todas las acciones relacionadas con el comando 'lanzar dados'.
    private void lanzarDados() {

        if(tirado){ // Comprobamos que el jugador no haya tirado antes.
            System.out.println("El jugador ya ha lanzado los dados en este turno.\n");
            return;
        }

        if(dado1 == null){
            dado1 = new Dado();
        }
        if(dado2 == null){
            dado2 = new Dado();
        }
            // mirar si salen nuemro iguales, volver a tirar
        Jugador jActual = jugadores.get(turno);
        int consecutivos = 0;

        while(consecutivos < 3){
            int valorDado1 = dado1.hacerTirada();
            int valorDado2 = dado2.hacerTirada();
            int sumaDados = valorDado1 + valorDado2;

            System.out.println("El jugador: " + jActual.getNombre());
            System.out.println("Dado 1: " + valorDado1 + ", dado 2: " + valorDado2 + ". Valor total: " + sumaDados);
    
            if(valorDado1 == valorDado2){
                consecutivos++;
                System.out.println("El valor de los dados es igual. El jugador vuelve a tirar.");

                if(consecutivos == 3){
                    System.out.println("Tres dobles consecutivos! El jugador " + jActual.getNombre() + " irá a la carcel");
                    jActual.encarcelar(tablero.getPosiciones()); 
                    break;
                }
            }else {
                System.out.println("Fin del turno. " + jActual.getNombre() + " no ha sacado dobles");
                break;
            }
        }

        tirado = true;
    }

    /*Método que ejecuta todas las acciones realizadas con el comando 'comprar nombre_casilla'.
    * Parámetro: cadena de caracteres con el nombre de la casilla.
     */
    private void comprar(String nombre) {
        

    }

    //Método que ejecuta todas las acciones relacionadas con el comando 'salir carcel'. 
    private void salirCarcel() {
    }

    // Método que realiza las acciones asociadas al comando 'listar enventa'.
    private void listarVenta() {
        // boolean propiedadVenta = false;
        // System.out.println("Propiedades en venta: ");
        // for (Casilla casilla : tablero.getCasillas()){

        // }
    }

    // Método que realiza las acciones asociadas al comando 'listar jugadores'.
    private void listarJugadores() {
    }

    // Método que realiza las acciones asociadas al comando 'listar avatares'.
    private void listarAvatares() {
    }

    // Método que realiza las acciones asociadas al comando 'acabar turno'.
    private void acabarTurno() {
    }

}
