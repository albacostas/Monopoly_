package monopoly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import partida.Jugador;

public class Estadisticas {
    private ArrayList<Jugador> jugadores;
    private ArrayList<Casilla> propiedades;
    private Tablero tablero;

    public Estadisticas(ArrayList<Jugador> jugadores, ArrayList<Casilla> propiedades, Tablero tablero) {
        this.jugadores = jugadores;
        this.propiedades = propiedades;
        this.tablero = tablero;
    }

    public void mostrarEstadisticas() {
        Casilla casillaMasRentable = calcularCasillaMasRentable();
        String grupoMasRentable = calcularGrupoMasRentable();
        String casillaMasFrecuentada = calcularCasillaMasFrecuentada();
        Jugador jugadorMasVueltas = calcularJugadorMasVueltas();
        Jugador jugadorMasVecesDados = calcularJugadorMasVecesDados();
        Jugador jugadorEnCabeza = calcularJugadorConMayorFortuna();

        System.out.println("Casilla más rentable: " + casillaMasRentable);
        System.out.println("Grupo más rentable: " + grupoMasRentable);
        System.out.println("Casilla más frecuentada: " + casillaMasFrecuentada);
        System.out.println("Jugador que ha dado más vueltas al tablero: " + jugadorMasVueltas.getNombre());
        System.out.println("Jugador que ha tirado más veces los dados: " + jugadorMasVecesDados.getNombre());
        System.out.println("Jugador con mayor fortuna: " + jugadorEnCabeza.getNombre());
    }

    private Casilla calcularCasillaMasRentable() {
        Casilla casillaMasRentable = null;
        float maxIngresos = 0;
    
        for (Casilla propiedad : propiedades) {
            float ingresosCasilla = propiedad.getTotalAlquilerRecaudado();
            if (ingresosCasilla > maxIngresos) {
                maxIngresos = ingresosCasilla;
                casillaMasRentable = propiedad;
            }
        }
        return casillaMasRentable; // Devuelve la casilla más rentable
    }
    private String calcularGrupoMasRentable() {
        HashMap<String, Float> rentabilidadGrupo = new HashMap<>();

        for (Grupo grupo : tablero.getGrupos().values()) { // Accede a los grupos desde el tablero
            float total = grupo.getTotalAlquilerRecaudado();
            rentabilidadGrupo.put(grupo.getColorGrupo(), rentabilidadGrupo.getOrDefault(grupo.getColorGrupo(), 0.0f) + total);
        }

        return rentabilidadGrupo.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
    }

    private String calcularCasillaMasFrecuentada() {
        Casilla casillaMasFrecuentada = null;
        int maxCaidas = 0;
    
        for (Casilla propiedad : propiedades) {
            // Obtener el total de caídas de la casilla
            int totalCaidas = propiedad.getCaidasJugador().values().stream()
                .mapToInt(Integer::intValue)
                .sum(); // Sumar todas las caídas de los jugadores en esta casilla
    
            if (totalCaidas > maxCaidas) {
                maxCaidas = totalCaidas;
                casillaMasFrecuentada = propiedad;
            }
        }
        return casillaMasFrecuentada != null ? casillaMasFrecuentada.getNombre() : "N/A";
    }

    private Jugador calcularJugadorMasVueltas() {
        return jugadores.stream()
                .max((j1, j2) -> Integer.compare(j1.getVueltas(), j2.getVueltas()))
                .orElse(null);
    }

    private Jugador calcularJugadorMasVecesDados() {
        return jugadores.stream()
                .max((j1, j2) -> Integer.compare(j1.getTiradasDados(), j2.getTiradasDados()))
                .orElse(null);
    }

    private Jugador calcularJugadorConMayorFortuna() {
        return jugadores.stream()
                .max((j1, j2) -> Float.compare(j1.getFortuna(), j2.getFortuna()))
                .orElse(null);
    }
}