package monopoly;

public class MonopolyETSE {
<<<<<<< HEAD

    private static ArrayList<Jugador> jugadores = new ArrayList<>();
    private static ArrayList<Avatar> avatares = new ArrayList<>();
=======
>>>>>>> 65371b587765820055ce74238937bfc627c6e830
    public static void main(String[] args) {


        Jugador banca = new Jugador();

        // Crear el tablero con el jugador banca
        Tablero tablero = new Tablero(banca);

        // Imprimir el tablero
        System.out.println(tablero);
    }

}