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