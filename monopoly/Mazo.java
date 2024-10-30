package monopoly;

import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    private ArrayList<Carta> cartas; // Lista de cartas en el mazo

    // Constructor de la clase Mazo
    public Mazo() {
        cartas = new ArrayList<>();
        // Agregar cartas de Suerte
        agregarCartasSuerte();
        // Agregar cartas de Caja de Comunidad
        agregarCartasCajaComunidad();
    }

    // Método para agregar cartas de Suerte al mazo
    private void agregarCartasSuerte() {
        cartas.add(new Carta("Ve al Transportes1 y coge un avión. Si pasas por la casilla de Salida, cobra la cantidad habitual.", "ir_a_transportes1", "Suerte"));
        cartas.add(new Carta("Decides hacer un viaje de placer. Avanza hasta Solar15.", "avanzar_a_solar15", "Suerte"));
        cartas.add(new Carta("Vendes tu billete de avión para Solar17 en una subasta por Internet. Cobra 500000€.", "vender_billete", "Suerte"));
        cartas.add(new Carta("Ve a Solar3. Si pasas por la casilla de Salida, cobra la cantidad habitual.", "ir_a_solar3", "Suerte"));
        cartas.add(new Carta("Los acreedores te persiguen por impago. Ve a la Cárcel. Ve directamente sin pasar por la casilla de Salida y sin cobrar la cantidad habitual.", "ir_a_carcel", "Suerte"));
        cartas.add(new Carta("¡Has ganado el bote de la lotería! Recibe 1000000€.", "ganar_loteria", "Suerte"));
    }

    // Método para agregar cartas de Caja de Comunidad al mazo
    private void agregarCartasCajaComunidad() {
        cartas.add(new Carta("Paga 500000€ por un fin de semana en un balneario de 5 estrellas.", "pagar_balneario", "Comunidad"));
        cartas.add(new Carta("Te investigan por fraude de identidad. Ve a la Cárcel. Ve directamente sin pasar por la casilla de Salida y sin cobrar la cantidad habitual.", "ir_a_carcel", "Comunidad"));
        cartas.add(new Carta("Colócate en la casilla de Salida. Cobra la cantidad habitual.", "ir_a_salida", "Comunidad"));
        cartas.add(new Carta("Tu compañía de Internet obtiene beneficios. Recibe 2000000€.", "recibir_beneficio", "Comunidad"));
        cartas.add(new Carta("Paga 1000000€ por invitar a todos tus amigos a un viaje a Solar14.", "pagar_viaje", "Comunidad"));
        cartas.add(new Carta("Alquilas a tus compañeros una villa en Solar7 durante una semana. Paga 200000€ a cada jugador.", "pagar_alquiler", "Comunidad"));
    }

    // Método para barajar las cartas en el mazo
    public void barajar() {
        Collections.shuffle(cartas); // Baraja las cartas aleatoriamente
    }

    // Método para elegir una carta del mazo según el índice proporcionado
    public Carta elegirCarta(int indice) {
        return cartas.get(indice - 1); // Restar 1 para ajustar el índice (1-based a 0-based)
    }
}

/*


private void inicializarCartas() {
    ArrayList<Carta> cartasSuerte = new ArrayList<>();
    cartasSuerte.add(new Carta("Avanza a la casilla 'Calle 1'.", "Ir a Casilla"));
    cartasSuerte.add(new Carta("Recibes $200.", "Pagar"));
    cartasSuerte.add(new Carta("Ve a la cárcel. No pasa por 'Suerte'.", "Ir a Casilla"));
    cartasSuerte.add(new Carta("Paga $50 de impuesto.", "Pagar"));
    cartasSuerte.add(new Carta("Recibes $100 de la banca.", "Pagar"));
    // Añadir más cartas de Suerte según lo que necesites

    mazoSuerte = new Mazo(cartasSuerte); // Crear el mazo de cartas de Suerte

    ArrayList<Carta> cartasComunidad = new ArrayList<>();
    cartasComunidad.add(new Carta("Paga $100 de multa.", "Pagar"));
    cartasComunidad.add(new Carta("Recibes $50 por ayuda de la comunidad.", "Pagar"));
    cartasComunidad.add(new Carta("Avanza hasta 'Calle 1'.", "Ir a Casilla"));
    cartasComunidad.add(new Carta("Ve a la cárcel. No pasa por 'Suerte'.", "Ir a Casilla"));
    cartasComunidad.add(new Carta("Avanza a la casilla 'Parking'.", "Ir a Casilla"));
    // Añadir más cartas de Comunidad según lo que necesites

    mazoComunidad = new Mazo(cartasComunidad); // Crear el mazo de cartas de Comunidad
}


public void manejarCasilla(Jugador jugador, int posicion){
    Casilla casilla = casillas.get(posicion -1);
    boolean solvente = casilla.evaluarCasilla(jugador, null, 0);

    if(casilla.getTipo().equals("Suerte")){
        manejarSuerte(jugador);
    }else if(casilla.getTipo().equals("Comunidad")){
        manejarComunidad(jugador);
    }
}

private void manejarSuerte(Jugador jugador){
    mazoSuerte.barajar();
    int indiceElegido = pedirNumeroCarta(jugador);
    Carta cartaElegida = mazoSuerte.elegirCarta(indiceElegido);
    System.out.println("Has elegido la carta: " + cartaElegida.getDescripcion());
    realizarAccionCarta(jugador, cartaElegida);
}
private void manejarComunidad(Jugador jugador){
    mazoComunidad.barajar();
    int indiceElegido = pedirNumeroCarta(jugador);
    Carta cartaElegida = mazoComunidad.elegirCarta(indiceElegido);
    System.out.println("Has elegido la carta: " + cartaElegida.getDescripcion());
    realizarAccionCarta(jugador, cartaElegida);
}

private void realizarAccionCarta(Jugador jugador, Carta carta){
    switch (carta.getTipoAccion()) {
        case "Pagar":
            
            break;
    
        default:
            break;
    }
}
public int pedirNumeroCarta(Jugador jugador){
    Scanner scanner = new Scanner(System.in);
    int numeroCarta = 0;

    while(true){
        System.out.println(jugador.getNombre() + ", elige un número del 1 al 6:");
        try{
            numeroCarta = Integer.parseInt(scanner.nextLine()); // Leemos la entrada del usuario.
            if(numeroCarta >= 1 && numeroCarta <= 6){
                break;
            }else{
                System.out.println("Número inválido.");
            }
        }catch(NumberFormatException e){
            System.out.println("Entrada no válida.");
        }
    }
    return numeroCarta -1;

}*/