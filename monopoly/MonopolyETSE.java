package monopoly;

import partida.*;
import java.util.ArrayList;
// import java.util.Scanner;



public class MonopolyETSE {

    private static ArrayList<Jugador> jugadores = new ArrayList<>();
    private static ArrayList<Avatar> avatares = new ArrayList<>();
    public static void main(String[] args) {
    
        Jugador banca = new Jugador();
        Tablero tablero = new Tablero(banca);

        System.out.println(tablero);
    }
    
}
