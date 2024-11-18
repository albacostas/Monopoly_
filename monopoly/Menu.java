package monopoly;

import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Collections;
import java.util.Scanner;
import java.util.Iterator;

import partida.*;

public class Menu implements Hipotecable{

    // Atributos
    private ArrayList<Jugador> jugadores; // Jugadores de la partida.
    private ArrayList<Avatar> avatares; // Avatares en la partida.
    private int turno = 0; // Índice correspondiente a la posición en el arrayList del jugador (y el avatar) que tienen el turno
    private int lanzamientos; // Variable para contar el número de lanzamientos de un jugador en un turno.
    private Tablero tablero; // Tablero en el que se juega.
    private Dado dado1; // Dos dados para lanzar y avanzar casillas.
    private Dado dado2;
    private Jugador banca; // El jugador banca.
    private boolean tirado; // Booleano para comprobar si el jugador que tiene el turno ha tirado o no.
    private boolean solvente; // Booleano para comprobar si el jugador que tiene el turno es solvente, es
                              // decir, si ha pagado sus deudas.
    private Scanner scanner;
    // Atributos para las estadísticas
    private Estadisticas estadisticas;
    private int tiradaActual;
    private int turnosSaltados;
    private int tiradasCoche;
    private Carta cartaElegida;
    // Constructor del menú: Desarrollo de la partida (Necesario porque los métodos
    // son privados, por lo que todas las instrucciones deben seguirse aquí)
    public Menu() {
        // Constuctor
        this.dado1 = new Dado();
        this.dado2 = new Dado();
        this.banca = new Jugador();
        this.jugadores = new ArrayList<Jugador>();
        this.avatares = new ArrayList<Avatar>();
        this.tablero = new Tablero(this.banca);

        turno = 0;
        this.scanner = new Scanner(System.in);

        // Partida
        this.tablero.toString();
        this.iniciarPartida(scanner);
        System.out.println(this.tablero.toString());
        this.estadisticas = new Estadisticas(jugadores, tablero.getCasillas(), this.tablero);
        this.tiradaActual = 0;
        this.turnosSaltados = 0;
        this.tiradasCoche = 0;

        String comando;
        do {
            System.out.println("\n**************************************");
            System.out.println("      ** Bienvenido al Menú de Monopoly **");
            System.out.println("**************************************");
            System.out.println("        ** Opciones Disponibles **");
            System.out.println("**************************************");
            System.out.println("  1.  👤 **Crear jugador**                  : crear jugador (nombre) (avatar)");
            System.out.println("  2.  👀 **Jugador actual**                 : jugador");
            System.out.println("  3.  📜 **Listar jugadores**               : listar jugadores");
            System.out.println("  4.  🎭 **Listar avatares**                : listar avatares");
            System.out.println("  5.  🏘️ **Listar edificios**                : listar edificios");
            System.out.println("  6.  🏘️ **Listar edificios grupo**          : listar edificios (grupo)");
            System.out.println("  7.  🏘️ **Listar en venta**                 : listar enventa");
            System.out.println("  8.  🎲 **Lanzar dados**                   : lanzar dados");
            System.out.println("  9.  ⏳ **Acabar turno**                   : acabar turno");
            System.out.println(" 10.  🚔 **Salir de la cárcel**             : salir carcel");
            System.out.println(" 11.  🧑 **Describir jugador**              : describir jugador (jugador)");
            System.out.println(" 12.  🎭 **Describir avatar**               : describir avatar (avatar)");
            System.out.println(" 13.  🏠 **Describir casilla**              : describir (casilla)");
            System.out.println(" 14.  💸 **Comprar propiedad**              : comprar (casilla)");
            System.out.println(" 15.  💸 **Edificar casa**                  : edificar casa");
            System.out.println(" 16.  💸 **Edificar hotel**                 : edificar hotel");
            System.out.println(" 17.  💸 **Edificar piscina**               : edificar piscina");
            System.out.println(" 18.  💸 **Edificar pista**                 : edificar pista de deporte");
            System.out.println(" 19.  💸 **Hipotecar propiedad**            : hipotecar casilla");
            System.out.println(" 20.  💸 **Deshipotecar propiedad**         : deshipotecar casilla");
            System.out.println(" 21.  💸 **Vender edificios**               : vender (tipo) (casilla) (cantidad)");
            System.out.println(" 22.  🧮 **Ver tablero**                    : ver tablero");
            System.out.println(" 21. 📉 **Bancarrota**                      : bancarrota");
            System.out.println(" 22. ✨ **Movimiento especial**             : especial");
            System.out.println(" 23. 👣 **Finalizar movimiento especial**   : fin_especial");
            System.out.println(" 24. ➡️  **Continuar movimiento especial**   : continuar");
            System.out.println(" 25. 📊 **Estadísticas de un juegador**     : estadisticas (jugador)");
            System.out.println(" 26. 📊 **Estadísticas de la partida**      : estadisticas");
            System.out.println(" 27. ❓ **Ayuda**                           : ayuda");
            System.out.println(" 28. 🚪 **Finalizar partida**               : finalizar");
            System.out.println("**********************************************************************************");
            System.out.println("**************************************");
            System.out.print("  🎮 **Introduce un comando:** ");

            comando = scanner.nextLine(); // Leer el comando del usuario
            //METER COMPROBACION SOLO QUEDA UN JUGADOR GANADOR ATENEA
            analizarComando(comando); // Llama a tu método para procesar el comando

        } while (!comando.equalsIgnoreCase("finalizar")); // Utiliza equalsIgnoreCase para más flexibilidad

        scanner.close();
    }

    // Métodos Getter y Setter para cada atributo
    // por ahora no se utilizan
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public ArrayList<Avatar> getAvatares() {
        return avatares;
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
        // avatares = new ArrayList<>();
        // tablero = new Tablero(banca); //hay que definir este constructor!!!
    }

    public int getTiradaActual() {
        return tiradaActual;
    }

    public void setTiradaActual(int tiradaActual) {
        this.tiradaActual = tiradaActual;
    }

    public int getTurnosSaltados() {
        return turnosSaltados;
    }

    public void setTurnosSaltados(int turnosSaltados) {
        this.turnosSaltados = turnosSaltados;
    }

    public Estadisticas getEstadisticas() {
        return estadisticas;
    }

    public void setTiradasCoche(int tiradasCoche) {
        this.tiradasCoche = tiradasCoche;
    }

    // Método para inciar una partida: crea los jugadores y avatares.
    private void iniciarPartida(Scanner scanner) {
        while (jugadores.size() < 2) {
            System.out.println("Introduce los datos de un jugador (nombre y tipo de avatar(Esfinge, Sombrero, Coche o Pelota)):");
            String[] j1 = scanner.nextLine().split(" ");
            if (j1.length != 2) {
                throw new IllegalArgumentException("Error: Debes introducir exactamente 2 datos separados por un espacio (nombre y tipo de avatar).");
            }
            crearJugador(j1[0], j1[1]);
        }
    }

    /**
     * Método que interpreta el comando introducido y toma la accion
     * correspondiente.
     * Parámetro: cadena de caracteres (el comando).
     */
    private void analizarComando(String comando) {
        String[] partes = comando.split(" ");
        switch (partes[0]) {
            case "crear":
                if (partes.length != 4) {
                    System.out.println(
                            "Error: Debes introducir el comando y exactamente 2 datos separados por un espacio (nombre y tipo de avatar).");
                    break;
                }
                this.crearJugador(partes[2], partes[3]);
                System.out.println(this.tablero.toString());
                break;

            case "jugador":
                if (partes.length != 1) {
                    System.out.println("Error: Este comando no tiene argumentos.");
                    break;
                }
                System.out.println("{\n\tnombre: " + this.jugadores.get(this.turno).getNombre() + ",\n\tavatar: " + this.jugadores.get(this.turno).getAvatar().getId() + "\n}");
                break;

            case "listar":
                if (partes.length <2) {
                    System.out.println("Error: Debes introducir el comando completo.");
                    break;
                }
                switch (partes[1]) {
                    case "jugadores": // Ya va
                        this.listarJugadores();
                        break;

                    case "avatares":
                        this.listarAvatares();
                        break;

                    case "enventa": // No va
                        this.listarVenta();
                        break;

                    case "edificios":
                        if(partes.length>2){
                            listarEdificacionesGrupo(partes[2]);
                            break;
                        }
                        else{
                            System.out.println(this.listarEdificaciones());
                            break;
                        }

                    default:
                        break;
                }
                break;

            case "lanzar": // Falta enseñar tablero
                if (partes.length != 4) {
                    System.out.println("Error: Debes introducir el comando completo.");
                    break;
                }
                this.lanzarDados(Integer.valueOf(partes[2]), Integer.valueOf(partes[3]));
                contarVueltasJugadores();
                System.out.println(this.tablero.toString());
                break;

            case "acabar": // No acaba el turno, funcion corregida pero no se sabe si va
                if (partes.length != 2) {
                    System.out.println("Error: Debes introducir el comando completo.");
                    break;
                }
                this.acabarTurno();
                break;

            case "salir":
                if (partes.length != 2) {
                    System.out.println("Error: Debes introducir el comando completo.");
                    break;
                }
                switch (partes[1]) {
                    case "carcel":
                        this.salirCarcel();
                        break;

                    default:
                        System.out.println("Fin de la partida");
                        break;
                }
                break;

            case "edificar":
                if (partes.length!=2){
                    System.out.println("Error: Debes introducir el comando completo.");
                    break;
                }
                Jugador jActual = jugadores.get(turno);
                if(!jActual.equals(avatares.get(turno).getLugar().getDuenho())){
                    System.out.println("Debes ser el propietario de la casilla para edificar en ella");
                    break;
                }
                else{
                    switch (partes[1]) {
                        case "casa":
                            avatares.get(turno).getLugar().edificarCasa();
                            break;
                        case "hotel": 
                            avatares.get(turno).getLugar().edificarHotel();
                            break;
                        case "piscina":
                            avatares.get(turno).getLugar().edificarPiscina();
                            break;
                        case "pista":
                            avatares.get(turno).getLugar().edificarPista();
                            break;
                        default:
                            System.out.println("Error: Escribe bien el comando.");
                            break;
                    }
                    break;
                }
            case "hipotecar":
                if(partes.length!=2){
                    System.out.println("Error: Debes introducir el comando completo.");
                    break;
                }
                hipotecar(partes[1]);
                break;

            case "deshipotecar":
                if(partes.length!=2){
                    System.out.println("Error: Debes introducir el comando completo.");
                    break;
                }
                deshipotecar(partes[1]);
                break;

            case "vender":
                if(partes.length!=4){
                    System.out.println("Error: Debes introducir el comando completo.");
                    break;
                }
                venderEdificaciones(partes[1], partes[2], Integer.valueOf(partes[3]));
                break;

            
            case "describir":
                if (partes.length == 1) {
                    System.out.println("Error: Debes introducir el comando completo");
                    break;
                }
                switch (partes[1]) {
                    case "jugador":
                        if (partes.length != 3) {
                            System.out.println("Error: Debes introducir el comando y exactamente 1 argumento (jugador).");
                            break;
                        }
                        this.descJugador(partes);
                        break;

                    case "avatar":
                        if (partes.length != 3) {
                            System.out.println("Error: Debes introducir el comando y exactamente 1 argumento (avatar).");
                            break;
                        }
                        this.descAvatar(partes[2]);
                        break;

                    default:
                        if (partes.length != 2) { // Corregir el caso de no encontrarla
                            System.out.println("Error: Debes introducir el comando y exactamente 1 argumento (casilla).");
                            break;
                        }
                        this.descCasilla(partes[1]);
                        break;
                }
                break;

            case "comprar": // Corregir caso de no existir la casilla y caso de no ser una casilla que se
                            // pueda comprar
                if (partes.length != 2) {
                    System.out.println("Error: Debes introducir el comando y exactamente 1 argumento (casilla).");
                    break;
                }
                this.comprar(partes[1]);
                break;

            case "ver":
                if (partes.length != 2) {
                    System.out.println("Error: Debes introducir el comando completo");
                    break;
                }
                System.out.println(this.tablero.toString());
                break;
            
            case "estadisticas":
                if(partes.length == 2){
                    this.estadisticasJugador(partes[1]);
                }else{
                    //this.estadisticasGlobales();
                    estadisticas.mostrarEstadisticas();
                }
                
                break;

            case "ayuda":
                System.out.println("\n**************************************");
                System.out.println("      ** Bienvenido al Menú de Monopoly **");
                System.out.println("**************************************");
                System.out.println("        ** Opciones Disponibles **");
                System.out.println("**************************************");
                System.out.println("  1.  👤 **Crear jugador**                  : crear jugador (nombre) (avatar)");
                System.out.println("  2.  👀 **Jugador actual**                 : jugador");
                System.out.println("  3.  📜 **Listar jugadores**               : listar jugadores");
                System.out.println("  4.  🎭 **Listar avatares**                : listar avatares");
                System.out.println("  5.  🏘️ **Listar edificios**                : listar edificios");
                System.out.println("  6.  🏘️ **Listar edificios grupo**          : listar edificios (grupo)");
                System.out.println("  7.  🏘️ **Listar en venta**                 : listar enventa");
                System.out.println("  8.  🎲 **Lanzar dados**                   : lanzar dados");
                System.out.println("  9.  ⏳ **Acabar turno**                   : acabar turno");
                System.out.println(" 10.  🚔 **Salir de la cárcel**             : salir carcel");
                System.out.println(" 11.  🧑 **Describir jugador**              : describir jugador (jugador)");
                System.out.println(" 12.  🎭 **Describir avatar**               : describir avatar (avatar)");
                System.out.println(" 13.  🏠 **Describir casilla**              : describir (casilla)");
                System.out.println(" 14.  💸 **Comprar propiedad**              : comprar (casilla)");
                System.out.println(" 15.  💸 **Edificar casa**                  : edificar casa");
                System.out.println(" 16.  💸 **Edificar hotel**                 : edificar hotel");
                System.out.println(" 17.  💸 **Edificar piscina**               : edificar piscina");
                System.out.println(" 18.  💸 **Edificar pista**                 : edificar pista de deporte");
                System.out.println(" 19.  💸 **Hipotecar propiedad**            :hipotecar casilla");
                System.out.println(" 20.  💸 **Deshipotecar propiedad**         :deshipotecar casilla");
                System.out.println(" 21.  💸 **Vender edificios**               :vender (tipo) (casilla) (cantidad)");
                System.out.println(" 22.  🧮 **Ver tablero**                    : ver tablero");
                System.out.println(" 21. 📉 **Bancarrota**                      : bancarrota");
                System.out.println(" 22. ✨ **Movimiento especial**             : especial");
                System.out.println(" 23. 👣 **Finalizar movimiento especial**   : fin_especial");
                System.out.println(" 24. ➡️  **Continuar movimiento especial**   : continuar");
                System.out.println(" 25. 📊 **Estadísticas de un juegador**     : estadisticas (jugador)");
                System.out.println(" 26. 📊 **Estadísticas de la partida**      : estadisticas");
                System.out.println(" 27. ❓ **Ayuda**                           : ayuda");
                System.out.println(" 28. 🚪 **Finalizar partida**               : finalizar");
                System.out.println("**********************************************************************************");
                break;

          case "bancarrota":
                if (partes.length != 1) {
                    System.out.println("Error: Este comando no tiene argumentos.");
                    break;
                }
                bancarrota(banca);      //ATENEA: METER QUITAR EDIFICIOS
                break;
            case "especial":            //ATENEA: Tener en cuenta el caso de lanzar y sacar dobles
                if (partes.length != 1) {
                    System.out.println("Error: Este comando no tiene argumentos.");
                    break;
                }
                if (tirado) {
                    System.out.println("El jugador ya ha lanzado los dados en este turno, por lo que no puede cambiar al modo de movimiento especial.\n");
                }
                else{
                    this.jugadores.get(this.turno).getAvatar().setMovimientoEspecial(true);
                    System.out.println("El jugador " + this.jugadores.get(this.turno).getNombre() + " con avatar " + this.jugadores.get(this.turno).getAvatar().getTipo() + " ha cambiado al modo de movimiento especial.\n");
                }
                break;
            case "fin_especial":            //ATENEA: Tener en cuenta el caso de lanzar y sacar dobles
                if (partes.length != 1) {
                    System.out.println("Error: Este comando no tiene argumentos.");
                    break;
                }
                if (tirado) {
                    System.out.println("El jugador ya ha lanzado los dados en este turno, por lo que no puede cambiar al modo de movimiento normal.\n");
                }
                else{
                    this.jugadores.get(this.turno).getAvatar().setMovimientoEspecial(false);
                    System.out.println("El jugador " + this.jugadores.get(this.turno).getNombre() + " con avatar " + this.jugadores.get(this.turno).getAvatar().getTipo() + " ha cambiado al modo de movimiento normal.\n");
                }
                break;
            case "continuar":
                if (partes.length != 1) {
                    System.out.println("Error: Este comando no tiene argumentos.");
                    break;
                }
                else if (jugadores.get(turno).getAvatar().getTipo().equals("Pelota")){
                    continuar(jugadores.get(turno), tiradaActual);
                    contarVueltasJugadores();
                    System.out.println(this.tablero.toString());
                }
                break;
            case "finalizar":
                System.out.println("Finalizando partida...");
                break;
            default:
                System.out.println("Error: El comando introducido no es correcto.");
                break;
        }
    }

    /**
     * Método que da de alta a un jugador
     * Parámetros: nombre del jugador y tipo del avatar
     */
    private void crearJugador(String nombrejugador, String avatar_j) {
        boolean error = false;
        for (Jugador i : jugadores) {
            if (i.getNombre().equals(nombrejugador)) {
                error = true;
                System.out.println("No se puede crear un jugador con nombre repetido");
            }
        }

        if (!avatar_j.equals("Esfinge") && !avatar_j.equals("Sombrero") && !avatar_j.equals("Coche") && !avatar_j.equals("Pelota")) {
            System.out.println("El avatar debe ser de tipo Esfinge, Sombrero, Coche o Pelota");
            error = true;
        }

        if (!error) {
            Jugador jugador = new Jugador(nombrejugador, avatar_j, this.getTablero().getPosiciones().get(0).get(0),
                    avatares, this);
            jugadores.add(jugador);
            avatares.add(jugador.getAvatar());
            this.getTablero().getPosiciones().get(0).get(0).anhadirAvatar(jugador.getAvatar());
            System.out.println(
                    "{\n\tnombre: " + jugador.getNombre() + ",\n\tavatar: " + jugador.getAvatar().getId() + "\n}"); // El avatar debe ser una letra generada automáticamente
        }
    }

    //Método para eliminar un jugador del arraylist de jugadores.
    public void eliminarJugador(Jugador jugador) {
        this.jugadores.remove(jugador);
    }

    /**
     * Método que realiza las acciones asociadas al comando 'describir jugador'.
     * Parámetro: comando introducido
     */
    private void descJugador(String[] partes) {
        // Nombre del jugador a describir
        String nombreJugador = partes[2];

        // Buscamos al jugador
        Jugador jugadorBuscado = null;
        for (Jugador jugador : jugadores) {
            if (jugador.getNombre().equalsIgnoreCase(nombreJugador)) {
                jugadorBuscado = jugador;
                break;
            }
        }
        // si encontramos al jugador, imprimimos su información
        if (jugadorBuscado != null) {
            System.out.println(jugadorBuscado.toString());
        } else {
            System.out.println("El jugador no ha sido encontrado. Compruebe el nombre");
        }

    }

    /**
     * Método que realiza las acciones asociadas al comando 'describir avatar'.
     * Parámetro: id del avatar a describir.
     */
    private void descAvatar(String ID) {
        boolean avat = false;
        for (Avatar av : avatares) {
            if (av.getId().equals(ID)) {
                System.out.println(av.toString());
                avat = false;
                break;
            } else {
                avat = true;
            }
        }
        if (avat) {
            System.out.println("No se ha encontrado ningún avatar con ese ID.");
        }
    }

    /**
     * Método que realiza las acciones asociadas al comando 'describir
     * nombre_casilla'.
     * Parámetros: nombre de la casilla a describir.
     */
    private void descCasilla(String nombre) {
        // hay que tener en cuenta qué tipo de casilla es. Caja de Comunidad, Suerte e
        // IrACárcel no tiene sentido describirlas.
        // Buscamos la Casilla
        System.out.println(this.tablero.encontrar_casilla(nombre).infoCasilla());
    }

    // Método para verificar y manejar si el jugador debe saltar el turno
    private boolean debeSaltarTurno() {
        if (jugadores.get(turno).getAvatar().getSaltarTurno()) {
            if (this.getTurnosSaltados() < 2) {
                this.setTurnosSaltados(this.getTurnosSaltados() + 1);
                System.out.println("Turno saltado por penalización. Turnos saltados hasta ahora: " + this.getTurnosSaltados());
                tirado = true;
                return true;
            } else {
                this.setTurnosSaltados(0);
                jugadores.get(turno).getAvatar().setSaltarTurno(false);
            }
        }
        return false;
    }

    /**
     * Método que realiza las acciones asociadas al comando 'describir
     * nombre_casilla'.
     * Parámetros: nombre de la casilla a describir.
     */
    private void lanzarDados(int d1, int d2) {
        if (tirado) { // Comprobamos que el jugador no haya tirado antes o si haya tirado, pero haya
                      // sacado dobles
            System.out.println("El jugador ya ha lanzado los dados en este turno.\n");
            return;
        }

        // Verifica si el jugador debe saltar su turno y finaliza si es así
        if (debeSaltarTurno()) {
            this.acabarTurno();
            return;
        }

        if (dado1 == null) {
            dado1 = new Dado();
        }
        if (dado2 == null) {
            dado2 = new Dado();
        }
        // mirar si salen nuemro iguales, volver a tirar

        Jugador jActual = jugadores.get(turno);
        jActual.incrementarTiradasDados();

        //actualizarJugadorMasTiradas(jActual);
        if (jActual.isEnCarcel()) {
            salirCarcel();
        }
        else {
            int valorDado1 = d1;
            int valorDado2 = d2;
            int sumaDados = valorDado1 + valorDado2;    //ATENEA: PAsarselo a cambiar modo avatares
            lanzamientos++;
            tiradaActual = sumaDados;



            System.out.println("El jugador: " + jActual.getNombre());
            System.out.println("Dado 1: " + valorDado1 + ", dado 2: " + valorDado2 + ". Valor total: " + sumaDados);
            
            if (lanzamientos == 3 && valorDado1 == valorDado2 && tiradasCoche==0) {
                System.out.println("¡Tres dobles consecutivos! El jugador " + jActual.getNombre() + " irá a la cárcel :(");
                lanzamientos = 0;
                jActual.encarcelar(tablero.getPosiciones());
                tirado = true;
                acabarTurno();
                return;
            }
            if (valorDado1 == valorDado2) {
                System.out.println("El jugador " + jActual.getNombre() + " ha sacado dobles. Vuelve a tirar");
                tirado = false;
            } else {
                System.out.println(jActual.getNombre() + " no ha sacado dobles.");
                tirado = true;
            }
            if (tirado) {
                lanzamientos = 0;
            }

            Casilla casActual;
            //Casilla casActual = jActual.getAvatar().getLugar();
            if (jActual.getAvatar().getMovimientoEspecial() /*&& jActual.getAvatar().getTipo().equals("Pelota")*/) { 
                this.solvente = jActual.getAvatar().cambiarModo(tablero.getPosiciones(), sumaDados);
                casActual = jActual.getAvatar().getLugar();
                casActual.registrarCaida(jActual);
                if (!solvente) {
                    noSolvente(casActual.getDuenho());                                      //ATENEA: TENER EN CUENTA / REVISAR
                }
                if (sumaDados>4 && jugadores.get(turno).getAvatar().getTipo().equals("Pelota")) {
                    System.out.println("Introduce el comando 'continuar' cuando quieras seguir moviendo el avatar.");
                    tirado = false;
                    return;
                }
                if (sumaDados>4 && jugadores.get(turno).getAvatar().getTipo().equals("Coche") && tiradasCoche<3) {
                   tirado = false;
                   tiradasCoche++;
                   System.out.println("El jugador ha sacado más de un 4, puede volver a tirar");
                }
                else if (sumaDados<=4 && jugadores.get(turno).getAvatar().getTipo().equals("Coche")) {
                    tirado = true;
                    tiradasCoche = 0;
                    System.out.println("El jugador ha sacado menos de 4, tras este turno ya no vuelve a tirar");
                }
                else if (tiradasCoche == 4) {
                    tirado = true;
                    tiradasCoche = 0;
                    System.out.println("El jugador ya ha vuelto a lanzar los dados 3 veces seguidas, ahora termian el turno.");
                }
            }
            else{
                jActual.getAvatar().moverAvatar(tablero.getPosiciones(), sumaDados);
                casActual = jActual.getAvatar().getLugar();
                casActual.registrarCaida(jActual);
                this.setSolvente(casActual.evaluarCasilla(jActual, banca, sumaDados));
                if (!solvente) {
                    noSolvente(casActual.getDuenho());
                }
            }            
            if(casActual.getNombre().equals("Ir Cárcel")){
                System.out.println("Has caido en la casilla " + casActual.getNombre() + ". Te moverás a la casilla de cárcel.");
                jActual.encarcelar(tablero.getPosiciones());
                jActual.incrementarVecesCarcel();
                tirado = true;
            }
            else if(casActual.getTipo().equals("Impuestos")){
                Casilla parking = tablero.encontrar_casilla("Parking");
                parking.setValor(parking.getValor() + casActual.getImpuesto());
            }
            if (jugadores.get(turno).getAvatar().getTipo().equals("Coche") && sumaDados<=4) {
                tirado = true;      //acabarTurno();              //ATENEA: SI AL FINAL SE PIDE REALIZAR ACCIONES CAMBIAR ESTO POR TIRADO = TRUE
            }
        }
    }

    /**
     * Método privado para continuar moviendo el avatar después de haber caído en movimientos especiales
     */
    private void continuar(Jugador jActual, int Suma_dados){
        if (jActual.getAvatar().getContador_especial() <= Suma_dados) {
            jActual.getAvatar().cambiarModo(tablero.getPosiciones(), Suma_dados);
            System.out.println("Introduce el comando 'continuar' para seguir moviendo el avatar.");
        }
        else if (Suma_dados%2==0 && jActual.getAvatar().getContador_especial() == Suma_dados) {
            System.out.println("El avatar ya se ha movido el número de posiciones correspondiente");
            tirado = true;
        }
        else if (Suma_dados%2==1 && jActual.getAvatar().getContador_especial() >= Suma_dados){
            jActual.getAvatar().cambiarModo(tablero.getPosiciones(), Suma_dados);
            System.out.println("El avatar ya se ha movido el número de posiciones correspondiente");
            tirado = true;
        }
    }


    /**
     * Método que ejecuta todas las acciones realizadas con el comando 'comprar
     * nombre_casilla'.
     * Parámetro: cadena de caracteres con el nombre de la casilla.
     */
    private void comprar(String nombre) {
        Jugador jActual = this.jugadores.get(this.turno);
        Casilla casilla = this.tablero.encontrar_casilla(nombre);
        String tipo = casilla.getTipo();

        if (!casilla.getNombre().equals(nombre)) {
            System.out.println("La casilla " + nombre + " no existe en el tablero.");
            return;
        }
        if (casilla.getDuenho() != null && !casilla.getDuenho().equals(this.banca)) {
            System.out.println("La casilla " + nombre + " ya tiene propietario.");
            return;
        }
        if (casilla.getDuenho().getNombre().equals(jActual.getNombre())) {
            System.out.println(jActual.getNombre() + " ya es propietario de la casilla " + casilla.getNombre());
            return;
        }
        if (!tipo.equals("Solar") && !tipo.equals("Transporte") && !tipo.equals("Servicio")) {
            System.out.println("La casilla " + nombre + " no está en venta.");
            return;
        }
        if (!verificarCasilla(jActual, casilla)) {
            System.out.println(
                    "El jugador " + jActual.getNombre() + " no está en la casilla " + nombre + ". No puede comprarla.");
            return;
        }
        float precio = casilla.getValor();

        System.out.println("La casilla " + nombre + " cuesta " + precio);

        if (jActual.getFortuna() < precio) {
            System.out.println("No dispone de suficiente dinero para comprar la casilla.");
            return;
        }

        jActual.sumarGastos(precio);
        jActual.incrementarDineroPropiedades(precio);
        casilla.setDuenho(jActual);

        jActual.anhadirPropiedad(casilla);
        System.out.println("El jugador " + jActual.getNombre() + " ha comprado la casilla " + nombre);
        System.out.println("Información del jugador: ");
        descJugador(new String[] { "", "", jActual.getNombre() });

    }

    private boolean verificarCasilla(Jugador jugador, Casilla casilla) {
        return jugador.getAvatar().getLugar().equals(casilla);
    }

    /**
     * Método que ejecuta todas las acciones relacionadas con el comando 'salir carcel'.
     */
    private void salirCarcel() {

        Jugador jActual = jugadores.get(turno);

        if (jActual.isEnCarcel()) {
            System.out.println(
                    "Para salir de la carcel pagando escriba el comando 'pagar_fianza'.\nPara salir de la carcel tirando dobles escriba el comando 'dobles'.");
            System.out.println("Si no sacas dobles en 3 turnos, sales automáticamente.");
            //Scanner scanner = new Scanner(System.in);
            String cmd = scanner.nextLine();
            switch (cmd) {
                case "pagar_fianza":
                    if (jActual.getFortuna() >= 500000.0f) {
                        jActual.sumarGastos(500000.0f);
                        jActual.setTotalPagadoImpuestos(jActual.getTotalPagadoImpuestos() + 500000.0f );
                        //jActual.sumarFortuna(-500000.0f);
                        jActual.setEnCarcel(false);
                        System.out.println(jActual.getNombre() + " paga 500000 y sale de la carcel.");
                        jActual.setTiradasCarcel(0);
                        
                        tirado=false;
                    }else {
                        System.out.println(jActual.getNombre() + " no tiene suficiente dinero para pagar la multa de 500000.");
                        String respuesta = scanner.nextLine();  //REVISAR (ATENEA)
                        while (respuesta != "a" && respuesta != "b") {
                            System.out.println("¿Quieres (a) declararte en bancarrota/hipotecar propiedades o (b) volver atrás?");
                        switch (respuesta) {
                            case "a":
                                noSolvente(banca);                            
                                break;
                            //ACABAR
                            case "b":
                                salirCarcel();
                                break;

                            default:
                                System.out.println("Comando no válido.\n");
                                break;
                        }

                        }
                        
                    }
                    break;

                case "dobles":
                    int valorDado1 = dado1.hacerTirada();
                    int valorDado2 = dado2.hacerTirada();
                    int sumaDados = valorDado1 + valorDado2;
                    if (jActual.getTiradasCarcel() <= 2) {
                        System.out.println("Dado 1: " + valorDado1 + ", dado 2: " + valorDado2 + ". Valor total: " + sumaDados);
                        if (valorDado1 == valorDado2) {
                            jActual.setEnCarcel(false);
                            System.out.println("¡Eres libre!");
                            Avatar avActual = jActual.getAvatar();
                            avActual.moverAvatar(tablero.getPosiciones(), sumaDados);              //ATENEA: CAMBIAR (en vez de pasar Sumadados pasar cambiarmovimiento)
                            this.tirado = true;
                        } else {
                            jActual.contar_tiradas_carcel();
                            System.out.println("Lo sentimos, no has sacado dobles, te quedas en la cárcel :(");
                            this.tirado = true;
                            acabarTurno();
                        }
                    } else {
                        jActual.setEnCarcel(false);
                        System.out.println("Has realizado 3 tiradas sin éxito, quedas libre");
                        Avatar avActual = jActual.getAvatar();
                        avActual.moverAvatar(tablero.getPosiciones(), sumaDados);              //ATENEA: Cambiar tmb
                        this.tirado = true;
                    }
                default:
                    break;
            }

        } else {
            System.out.println(jActual.getNombre() + " no está en la carcel.");
        }

    }

    /**
     * Método para actuar en caso de que un jugador no sea solvente.
     */
    public void noSolvente(Jugador destinatario) {
        String opcion;
        System.out.println("Para declararte en bancarrota introduce el comando 'bancarrota'. Para hipotecar introduce el comando 'hipotecar':");
        Scanner scanner = new Scanner(System.in);
        opcion = scanner.next();
        switch (opcion) {
            case "bancarrota":
                bancarrota(destinatario);      //NO SE SI FUNCIONA (ATENEA)
                break;
                
            case "hipotecar":
                //FALTA AÑADIR EL CODIGO PARA HIPOTECAR (AINHOA)
                cartaElegida = destinatario.getCartaElegida();
                if( cartaElegida != null){
                    boolean resultado = destinatario.realizarAccion(cartaElegida, tablero);
                    if(resultado){ 
                        System.out.println("Accion realizada con éxito usando la carta elegida.");
                    } else{
                        System.out.println("No se ha podido realizar la accion.");
                    }

                } else{
                    System.out.println("No tienes carta elegida.");
                }
                System.out.println("Todavia no esta implementado 👷‍♀️🔧");
                break;

            default:
                System.out.println("Comando no válida.");
                break;
        }
    }

    // Método que realiza las acciones asociadas al comando 'listar enventa'.
    private void listarVenta() {
        ArrayList<ArrayList<Casilla>> casilla = tablero.getPosiciones();
        for (ArrayList<Casilla> lado : casilla) {
            for (Casilla i : lado) {
                if (i.getDuenho().equals(banca) && (i.getTipo().equals("Solar") || i.getTipo().equals("Transporte")
                        || i.getTipo().equals("Servicio"))) {
                    System.out.println(i.casEnVenta());
                }
            }
        }
    }

    // Método que realiza las acciones asociadas al comando 'listar jugadores'.
    private void listarJugadores() {
        for (Jugador i : jugadores) {
            System.out.println(i.toString());
        }
    }

    // Método que realiza las acciones asociadas al comando 'listar avatares'.
    private void listarAvatares() {

        if (avatares.isEmpty()) {
            System.out.println("No hay avatares creados.");
        } else {
            for (Avatar i : avatares) {
                System.out.println(i.toString());
            }
        }
    }

    public String listarEdificaciones() {
        StringBuilder resultado = new StringBuilder();
    
        for (ArrayList<Casilla> lado : tablero.getPosiciones()) {
            for (Casilla casilla : lado) {
                ArrayList<Edificacion> edificaciones = casilla.getEdificaciones();
                if (!edificaciones.isEmpty()) {
                    for (Edificacion e : edificaciones) {
                        resultado.append(String.format("""
                            {
                                id: %s-%s,
                                propietario: %s,
                                casilla: %s,
                                grupo: %s,
                                coste: %f
                            }
                            """,e.getTipo(), e.getId(), casilla.getDuenho().getNombre(), casilla.getNombre(), casilla.getGrupo().getColorGrupo(), casilla.calcularCoste(e)));
                    }
                }
            }
        }
        return resultado.toString(); 
    }

    public void imprimirArray(ArrayList<Edificacion> a){
        if(!a.isEmpty()){
            System.out.print("[");
            for (Edificacion i:a){
                System.out.print(i.getTipo()+"-"+i.getId()+", ");
            }
            System.out.print("]\n");
        }
        else{
            System.out.print("-\n");
        }
    }
    
    public void listarEdificacionesGrupo(String grupo){
        ArrayList casas=new ArrayList<Edificacion>();
        ArrayList hoteles=new ArrayList<Edificacion>();  
        ArrayList piscinas=new ArrayList<Edificacion>(); 
        ArrayList pistas=new ArrayList<Edificacion>(); 

        Grupo color = tablero.getGrupos().get(grupo);
         
        if(color==null){
            System.out.println("Ese grupo no ha sido encontrado");
            return; 
        }

        for(Casilla casilla:color.getMiembros()){
            if (!casilla.getEdificaciones().isEmpty()) {
                System.out.println("propiedad: " + casilla.getNombre());  
                for (Edificacion e : casilla.getEdificaciones()) {
                    if (e.getTipo().equals("Casa")){
                        casas.add(e);
                    }
                    else if (e.getTipo().equals("Hotel")){
                        hoteles.add(e);
                    }
                    else if (e.getTipo().equals("Piscina")){
                        piscinas.add(e);
                    }
                    else {
                        pistas.add(e);
                    } 
                }
                System.out.print("Hoteles: ");
                imprimirArray(hoteles);
                System.out.print("Casas: ");
                imprimirArray(casas);
                System.out.print("Piscinas: ");
                imprimirArray(piscinas);
                System.out.print("Pistas de deporte: ");
                imprimirArray(pistas);
                
                System.out.println("alquiler: "+casilla.calcularAlquilerEdificacionessinMensajes());
                
            }
        }
    }



   
    // Método para saber si todos los jugadores han dado un número de vueltas al tablero múltiplo de 4
    public void contarVueltasJugadores(){
        boolean incrementar = true;

        for (Jugador jugador : jugadores) {
            if (jugador.getVueltas()<4) {
                incrementar = false;
                break;
            }
        }

        if (incrementar) {
            tablero.incrementarCasillas();
            for (Jugador jugador : jugadores) {
                jugador.setVueltas(jugador.getVueltas()-4);
            }
        }
    }

    private void acabarTurno() {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores en el juego.");
            return;
        }
        Jugador jActual = jugadores.get(turno);
        // lanzamientos = 0;
        // tirado = false;

        if (!tirado) {
            System.out.println(jActual.getNombre() + ", lanza los dados");
            // lanzarDados();
            System.out.println(this.tablero.toString());
            // tirado = true;
            return;
        }

        turno = (turno + 1) % jugadores.size(); // movemso el turno al siguiente jugador
        Jugador jSiguiente = jugadores.get(turno);

        System.out.println("El turno de " + jActual.getNombre() + " ha terminado. Ahora es el turno de " + jSiguiente.getNombre());
        tirado = false;
        if (jugadores.size() == 1 && !jSiguiente.isEnCarcel()) {
            System.out.println("El jugador " + jSiguiente.getNombre() + " no puede tirar. Ha terminado.");
        }
        lanzamientos = 0;
    }
    /*
    public boolean hipotecar(String nombre){
        Casilla casilla = this.tablero.encontrar_casilla(nombre);
        Jugador jugactual = jugadores.get(turno);
        if(casilla.getTipo().equals("Solar") || casilla.getTipo().equals("Servicio") || casilla.getTipo().equals("Transporte")){
            if(!jugactual.equals(casilla.getDuenho())){
                System.out.println("Tienes que ser el dueño de la casilla para poder hipotecarla.");
                return false;
            }
            else{
                if(casilla.getHipotecada()){
                    System.out.println("No se puede hipotecar "+casilla.getNombre()+". Ya está hipotecada.");
                    return false;
                }
                else{
                    if(!casilla.getEdificaciones().isEmpty()){
                        System.out.println("Esta propiedad tiene edificios. Antes de hipotecarla debes venderlos todos.");
                        return false;
                    }
                    else{
                        casilla.setHipotecada(true); 
                        jugactual.sumarFortuna(casilla.getHipoteca());
                        System.out.println("La casilla "+casilla.getNombre()+" ha sido hipotecada. El jugador "+jugactual.getNombre()+" ha recibido "+ casilla.getHipoteca());
                        return true;
                    }
                }
            }
        }
        else{
            System.out.println("Solo puedes hipotecar las casillas de tipo Solar, Servicio o Transporte.");
            return false;
        }
    }*/


    public boolean deshipotecar(String nombre){
        Casilla casilla = this.tablero.encontrar_casilla(nombre);
        Jugador jugactual = jugadores.get(turno);
        if(casilla.getTipo().equals("Solar") || casilla.getTipo().equals("Servicio") || casilla.getTipo().equals("Transporte")){
            if(!jugactual.equals(casilla.getDuenho())){
                System.out.println("Tienes que ser el dueño de la casilla para poder deshipotecarla.");
                return false;
            }
            else{
                if(!casilla.getHipotecada()){
                    System.out.println("La casilla no está hipotecada, por lo que no la pudes deshipotecar.");
                    return false;
                }
                else{
                    casilla.setHipotecada(false);
                    float deshipoteca=casilla.getHipoteca()+0.10f*casilla.getHipoteca();
                    jugactual.sumarGastos(deshipoteca);
                    System.out.println("La casilla "+casilla.getNombre()+" ha sido deshipotecada. El jugador "+jugactual.getNombre()+" ha pagado "+ deshipoteca);
                    return true;

                }

            }
        }
        else{
            System.out.println("No se puede deshipotecar una casilla que no sea de tipo Solar, Servicios o Transporte.");
            return false;
        }

    }

    public boolean venderEdificaciones(String tipo, String solar, int cantidad){
        Casilla casilla = this.tablero.encontrar_casilla(solar);
        Jugador jugactual = jugadores.get(turno);
        if(casilla.getTipo().equals("Solar")){
            if(jugactual.equals(casilla.getDuenho())){
                if(casilla.getEdificaciones().isEmpty()){
                    System.out.println("No hay edificaciones para vender en esta casilla.");
                    return false;
                }
                else{
                    float dinero=0.0f;
                    switch (tipo) {
                        case "Casa":
                            dinero= (casilla.getValorinicial()*0.6f)*0.5f*cantidad;
                            if(cantidad>casilla.getnumCasas()){
                                System.out.println("Solamente se pueden vender "+casilla.getnumCasas()+" casas, recibiendo "+dinero); 
                                return false;
                            }
                            else{
                                Iterator<Edificacion> iterator = casilla.getEdificaciones().iterator();
                                casilla.setnumCasas(casilla.getnumCasas()-cantidad);
                                int contador=0;
                                while(iterator.hasNext()){
                                    Edificacion e = iterator.next();
                                    if(e.getTipo().equals("Casa")){
                                        iterator.remove();
                                        contador++;
                                        if(contador == cantidad){
                                            break;
                                        }
                                    }
                                }
                                jugactual.sumarFortuna(dinero);
                                System.out.println("Casas vendidas. "+jugactual.getNombre()+" recibe "+ dinero);
                                return true;
                            }

                        case "Hotel":
                            dinero= (casilla.getValorinicial()*0.6f)*0.5f*cantidad;
                            if(cantidad>casilla.getnumHoteles()){
                                System.out.println("Solamente se pueden vender "+casilla.getnumHoteles()+" hoteles, recibiendo "+dinero);
                                return false;

                            }
                            else{
                                Iterator<Edificacion> iterator = casilla.getEdificaciones().iterator();
                                casilla.setnumHoteles(casilla.getnumHoteles()-cantidad);
                                int contador=0;
                                while(iterator.hasNext()){
                                    Edificacion e = iterator.next();
                                    if(e.getTipo().equals("Hotel")){
                                        iterator.remove();
                                        contador++;
                                        if(contador == cantidad){
                                            break;
                                        }
                                    }
                                }
                                jugactual.sumarFortuna(dinero);
                                System.out.println("Hoteles vendidos. "+jugactual.getNombre()+" recibe "+ dinero);
                                return true;
                            }
                            
                        case "Piscina":
                            dinero= (casilla.getValorinicial()*0.4f)*0.5f*cantidad;
                            if(cantidad>casilla.getnumPiscinas()){
                                System.out.println("Solamente se pueden vender "+casilla.getnumPiscinas()+" piscinas, recibiendo "+dinero); 
                                return false;

                            }
                            else{
                                Iterator<Edificacion> iterator = casilla.getEdificaciones().iterator();
                                casilla.setnumPiscinas(casilla.getnumPiscinas()-cantidad);
                                int contador=0;
                                while(iterator.hasNext()){
                                    Edificacion e = iterator.next();
                                    if(e.getTipo().equals("Piscina")){
                                        iterator.remove();
                                        contador++;
                                        if(contador == cantidad){
                                            break;
                                        }
                                    }
                                }
                                jugactual.sumarFortuna(dinero);
                                System.out.println("Piscinas vendidas. "+jugactual.getNombre()+" recibe "+ dinero);
                                return true;
                            }
                            
                        case "Pista":
                            dinero= (casilla.getValorinicial()*1.25f)*0.5f*cantidad;
                            if(cantidad>casilla.getnumPistas()){
                                System.out.println("Solamente se pueden vender "+casilla.getnumPistas()+" pistas, recibiendo "+dinero); 
                                return false;

                            }
                            else{
                                Iterator<Edificacion> iterator = casilla.getEdificaciones().iterator();
                                casilla.setnumPistas(casilla.getnumPistas()-cantidad);
                                int contador=0;
                                while(iterator.hasNext()){
                                    Edificacion e = iterator.next();
                                    if(e.getTipo().equals("Pista")){
                                        iterator.remove();
                                        contador++;
                                        if(contador == cantidad){
                                            break;
                                        }
                                    }
                                }
                                jugactual.sumarFortuna(dinero);
                                System.out.println("Pistas vendidas. "+jugactual.getNombre()+" recibe "+ dinero);
                                return true;
                            }
                            
                    
                        default:
                            System.out.println("El tipo de edificio introducido no existe");
                            return false;
                    }

                } 
            }
            else{
                System.out.println("Solo puede vender edificaciones de una casilla que sea tuya.");
                return false;
            }
        }
        else{
            System.out.println("Solo las casillas de tipo Solar tienen edificaciones.");
            return false;
        }
    }
    
    //Acsbar con edificios(ATENEA)
    //PEndiente ver si los edificios se heredan o se destruyen (ATENEA)
    //Edificios bancarrota (ATENEA)
    /**
     * Método que permite a un jugador declararse en bancarrota.
     */
    public void bancarrota(Jugador destinatario){
        Jugador jugador = jugadores.get(turno);
        System.out.println("El jugador " + jugador.getNombre() + " se ha declarado en bancarrota.");
        while (!jugador.getPropiedades().isEmpty()) {
            Casilla casilla = jugador.getPropiedades().get(0);
            while (!casilla.getEdificaciones().isEmpty()) {
                Edificacion edificacion = casilla.getEdificaciones().get(0);
                venderEdificaciones(edificacion.getTipo(), casilla.getNombre(), lanzamientos);      //En lugar de eliminar directamente las casillas las vendemos
            }
            casilla.setDuenho(destinatario);
            destinatario.anhadirPropiedad(casilla);
            jugador.eliminarPropiedad(casilla);
        }
        destinatario.sumarFortuna(jugador.getFortuna());
        destinatario.setTotalRecibidoParking(destinatario.getTotalRecibidoParking() + jugador.getFortuna());
        jugador.setFortuna(0);
        jugador.getAvatar().getLugar().eliminarAvatar(jugador.getAvatar());
        eliminarJugador(jugador);
    }


    private void estadisticasJugador(String nombreJugador){
        Jugador jugador = null;
        for(Jugador j : jugadores){
            if(j.getNombre().equals(nombreJugador)){
                jugador = j;
                break;
            }
        }
        if(jugador != null){
            System.out.println("{");
            System.out.println("  dineroInvertido: " + jugador.getTotalInvertidoPropiedades() + ",");
            System.out.println("  pagoTasasEImpuestos: " + jugador.getTotalPagadoImpuestos() + ",");
            System.out.println("  pagoDeAlquileres: " + jugador.getTotalPagadoAlquiler() + ",");
            System.out.println("  cobroDeAlquileres: " + jugador.getTotalRecibidoAlquiler() + ",");
            System.out.println("  pasarPorCasillaDeSalida: " + jugador.getTotalRecibidoSalida() + ",");
            System.out.println("  premiosInversionesOBote: " + jugador.getTotalRecibidoParking()+ ",");
            System.out.println("  vecesEnLaCarcel: " + jugador.getVecesCarcel());
            System.out.println("}");
        }else{
            System.out.println("No se ha encontrado el jugador");
        }
    }

    @Override

    public boolean hipotecar(String nombre){
        Casilla casilla = this.tablero.encontrar_casilla(nombre);
        Jugador jugactual = jugadores.get(turno);
        if(casilla.getTipo().equals("Solar") || casilla.getTipo().equals("Servicio") || casilla.getTipo().equals("Transporte")){
            if(!jugactual.equals(casilla.getDuenho())){
                System.out.println("Tienes que ser el dueño de la casilla para poder hipotecarla.");
                return false;
            }
            else{
                if(casilla.getHipotecada()){
                    System.out.println("No se puede hipotecar "+casilla.getNombre()+". Ya está hipotecada.");
                    return false;
                }
                else{
                    if(!casilla.getEdificaciones().isEmpty()){
                        System.out.println("Esta propiedad tiene edificios. Antes de hipotecarla debes venderlos todos.");
                        return false;
                    }
                    else{
                        casilla.setHipotecada(true); 
                        jugactual.sumarFortuna(casilla.getHipoteca());
                        System.out.println("La casilla "+casilla.getNombre()+" ha sido hipotecada. El jugador "+jugactual.getNombre()+" ha recibido "+ casilla.getHipoteca());
                        return true;
                    }
                }
            }
        }
        else{
            System.out.println("Solo puedes hipotecar las casillas de tipo Solar, Servicio o Transporte.");
            return false;
        }
    }
}





