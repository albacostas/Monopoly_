package monopoly;

public class Carta {
    private String descripcion; // Descripción de la carta
    private String accion;
    private String tipo; // Suerte o comunidad.

    // Constructor para crear una carta con descripción y tipo de acción
    public Carta(String descripcion, String accion, String tipo) {
        this.descripcion = descripcion;
        this.accion = accion;
        this.tipo = tipo;
    }

    // Método para obtener la descripción de la carta
    public String getDescripcion() {
        return descripcion;
    }
    public String getAccion(){
        return accion;
    }
    
    public String getTipo(){
        return tipo;

    }
}
    // guardar la casilla de movimeinto y el valor de ganancia o perdida
/*
    public Cartas (String descripcion){
        this.descripcion = descripcion;
    }

    public void manejarAccionCarta(Jugador jugador, String tipoCarta, ArrayList<ArrayList<Casilla>> casillas, Tablero tablero){
        barajarCartas(tipoCarta);
        System.out.println("Elige un número del 1 al 6: ");
        int eleccion = scanner.nextInt() -1;

        String cartaElegida = "";
        if(tipoCarta.equals("Suerte")){
            cartaElegida = cartasSuerte.get(eleccion);
        }else if(tipoCarta.equals("Comunidad")){
            cartaElegida = cartasComunidad.get(eleccion);
        }else {
            System.out.println("Carta elegido no válida.");
        }
        System.out.println("Has elegido la carta: " +  cartaElegida);
        ejecutarAccion(jugador, cartaElegida, tablero, casillas);
    }
    /* 
    public void manejarAccionCarta(Jugador jugador, String tipoCarta, ArrayList<ArrayList<Casilla>> casillas, Tablero tablero){
        barajarCartas(tipoCarta);
        int maxChoices = tipoCarta.equals("Suerte") ? cartasSuerte.size() : cartasComunidad.size();
        
        while (true) {
            System.out.println("Elige un número del 1 al " + maxChoices + ": ");
            int eleccion = scanner.nextInt() - 1;
    
            if (eleccion < 0 || eleccion >= maxChoices) {
                System.out.println("Número elegido no válido. Inténtalo de nuevo.");
            } else {
                String cartaElegida = tipoCarta.equals("Suerte") ? cartasSuerte.get(eleccion) : cartasComunidad.get(eleccion);
                System.out.println("Has elegido la carta: " +  cartaElegida);
                ejecutarAccion(jugador, cartaElegida, tablero, casillas);
                break; // exit the loop if the input is valid
            }
        }
    }
    
    private void ejecutarAccion(Jugador jugador, String carta, Tablero tablero, ArrayList<ArrayList<Casilla>> casillas){
        switch (carta) {
            case "Ve a Transportes1 y coge un avión. Si pasas por la casilla de Salida, cobra la cantidad habitual." :
                //jugador.getAvatar().moverAvatar(casillas, obtenerPosicion("Trans1", casillas));
                moverJugador(jugador, "Trans1", tablero, casillas);
                break;
            case "Decides hacer un viaje de placer. Avanza hasta Solar15.":
                //jugador.getAvatar().moverAvatar(casillas, obtenerPosicion("Solar15", casillas));
                moverJugador(jugador, "Solar15", tablero, casillas);
                break;
            case "Vendes tu billete de avión para Solar17 en una subarta por Internet. Cobra 500000€.":
                jugador.setFortuna(jugador.getFortuna() + 500000f);
                System.out.println(jugador.getNombre() + " ha cobreado  500000€.");
                break;
            case "Ve a Solar3. Si pasas por la casilla de Salida, cobra la cantidad habitual.":
                //jugador.getAvatar().moverAvatar(casillas, obtenerPosicion("Solar3", casillas));
                moverJugador(jugador, "Solar3", tablero, casillas);
                break;
            case "Los acreedores te persiguen por impago. Ve a la Cárcel. Ve directamente sin pasar por la casilla de Salida y sin cobrar la cantidad habitual.":
                //jugador.getAvatar().moverAvatar(casillas, obtenerPosicion("Cárcel", casillas));
                moverJugador(jugador, "Cárcel", tablero, casillas);
                break;
            case "¡Has ganado el bote de la lotería! Recibe 1000000":
                jugador.setFortuna(jugador.getFortuna() + 1000000f);
                System.out.println(jugador.getNombre() + " ha ganado 1000000€ en la lotería.");
                break;
            case "Paga 500000 por un fin de semana en un balneario de 5 estrellas.":
                jugador.setFortuna(jugador.getFortuna() - 500000f);
                System.out.println(jugador.getNombre() + " ha pagado 500000€.");
                break;
            case "Te investigan por fraude de identidad. Ve a la Cárcel. Ve directamente sin pasar por la casillad de Salida y sin cobrar la cantidad habitual.":
                moverJugador(jugador, "Cárcel", tablero, casillas);
                break;
            case "Colócate en la casilla de Salida. Cobra la cantidad habitual.":
                moverJugador(jugador, "Salida", tablero, casillas);
                jugador.setFortuna(jugador.getFortuna() +  Valor.SUMA_VUELTA);
                System.out.println("Ha combrado: " + Valor.SUMA_VUELTA);
                break;
            case "Tu compañía de Internet obtiene beneficios. Recibe 2000000€.":
                jugador.setFortuna(jugador.getFortuna() + 2000000f);
                System.out.println(jugador.getNombre() + " ha recibido 2000000€");
                break;
            case "Paga 1000000€ por invitar a todos tus amigos a un viaje a Solar14.":
                jugador.setFortuna(jugador.getFortuna() - 1000000f);
                break;
            case "Alquilas a tus compañeros una villa en Solar7 durante una semana. Paga 200000€ a cada jugador.":
                menu.pagarJugadores(200000f);
                break;
            default:
                System.out.println("Acción de carta no implementada.");
                break;
        }
    }

    private void moverJugador(Jugador jugador, String nombreCasilla, Tablero tablero, ArrayList<ArrayList<Casilla>> casillas){
        int posicionActual = jugador.getAvatar().getLugar().getPosicion();

        Casilla casDestino = tablero.encontrar_casilla(nombreCasilla);

        if(casDestino != null){
            int posicionDestino = casDestino.getPosicion();
            int desplazamiento;
            if(posicionDestino >= posicionActual){
                desplazamiento = posicionDestino - posicionActual;
            }else{
                desplazamiento = (40 - posicionActual) + posicionDestino;
            }
            jugador.getAvatar().moverAvatar(casillas, desplazamiento);
        }else{
            System.out.println("Error. No se encontró la casilla de destino " + nombreCasilla);
        }
    }
    public String getDescripcion(){
        return descripcion;
    } 

    private int pedirNumeroCarta(Jugador jugador) {
        // Pedir al jugador que elija un número entre 1 y 6
        // Scanner scanner = new Scanner(System.in);
        int eleccion;
        do {
            System.out.println("Jugador " + jugador.getNombre() + ", elige una carta (1-6): ");
            eleccion = scanner.nextInt();
        } while (eleccion < 1 || eleccion > 6);
        return eleccion;
    }
    
    private void realizarAccionCarta(Jugador jugador, Carta carta) {
        String accion = carta.getAccion();
        // Aquí se implementan las acciones según lo que la carta indique
        // Por ejemplo, si la acción es pagar dinero o mover a una casilla
        switch (accion) {
            case "Pagar":
                // Lógica para pagar dinero
                float cantidadAPagar = 100; // Aquí deberías obtener la cantidad de la carta
                if (jugador.getFortuna() >= cantidadAPagar) {
                    jugador.sumarFortuna(-cantidadAPagar);
                    System.out.println("Has pagado " + cantidadAPagar + ".");
                } else {
                    System.out.println("No tienes suficiente dinero. Debes hipotecar una propiedad.");
                    // Lógica para hipotecar propiedades
                }
                break;
            case "Ir a Casilla":
                // Lógica para mover al jugador a otra casilla
                break;
            // Añadir más acciones según sea necesario
            default:
                System.out.println("Acción no reconocida.");
                break;
        }
    }

    public void manejarAccionCarta(Jugador jugador, Mazo mazo) {
        mazo.barajar(); // Barajar las cartas
        int indiceElegido = pedirNumeroCarta(jugador); // Método para pedir el número de carta al jugador
        Carta cartaElegida = mazo.elegirCarta(indiceElegido);
    
        // Mostrar la descripción de la carta
        System.out.println("Has elegido la carta: " + cartaElegida.getDescripcion());
    
        // Realizar la acción
        realizarAccionCarta(jugador, cartaElegida);
    }
    
    public void pagarJugadores(float cantidad){
        Jugador jActual = jugadores.get(turno);
        for(Jugador i : jugadores){
            if(!i.equals(jActual)){
                i.setFortuna(i.getFortuna() + cantidad);
                System.out.println(i.getNombre() +  " ha recibido " +  cantidad + "€");
            }

            float total = cantidad *(jugadores.size() -1);
            jActual.setFortuna(jActual.getFortuna() -  total);
            System.out.println(jActual.getNombre() +  " ha pagado un total de " +  total + "€ a los otros jugadores.");
        }
    }
}
*/ 

