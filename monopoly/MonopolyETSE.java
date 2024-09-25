package monopoly;

import partida.*;
import java.util.ArrayList;
import java.util.Scanner;



public class MonopolyETSE {

    private static ArrayList<Jugador> jugadores = new ArrayList<>();
    private static ArrayList<Avatar> avatares = new ArrayList<>();
    private static ArrayList<ArrayList<Casilla>> tablero = new ArrayList<>();
    public static void main(String[] args) {
        new Menu();
    }
    
}
