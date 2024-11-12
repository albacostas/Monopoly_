package monopoly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import partida.Jugador;

public class Estadisticas {
    private ArrayList<Jugador> jugadores;
    private ArrayList<Casilla> propiedades;

    public Estadisticas(ArrayList<Jugador> jugadores, ArrayList<Casilla> propiedades) {
        this.jugadores = jugadores;
        this.propiedades = propiedades;
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

    public Casilla calcularCasillaMasRentable() {
        Casilla casillaMasRentable = null;
        float maxIngresos = 0;

        for (Casilla propiedad : propiedades) {

            float ingresosCasilla = propiedad.getTotalAlquilerRecaudado();
            if(ingresosCasilla > maxIngresos){
                maxIngresos = ingresosCasilla;
                casillaMasRentable = propiedad;
            }
        }
        return casillaMasRentable;
    }

    private String calcularGrupoMasRentable() {
        Map<String, Float> rentabilidadGrupo = new HashMap<>();
        for (Casilla propiedad : propiedades) {
            if (propiedad.getTipo().equals("Solar")) {
                // Asegúrate de que getGrupo() devuelva un objeto Grupo
                String grupoNombre = propiedad.getGrupo() != null ? propiedad.getGrupo().getNombre() : "Sin grupo";
                rentabilidadGrupo.put(grupoNombre, rentabilidadGrupo.getOrDefault(grupoNombre, 0.0f) + propiedad.getAlquiler());
            }
        }
        return rentabilidadGrupo.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
    }

    private String calcularCasillaMasFrecuentada() {
        Map<String, Integer> frecuencia = new HashMap<>();
        for (Jugador jugador : jugadores) {
            for (Casilla propiedad : jugador.getPropiedades()) {
                frecuencia.put(propiedad.getNombre(), frecuencia.getOrDefault(propiedad.getNombre(), 0) + 1);
            }
        }
        return frecuencia.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");
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