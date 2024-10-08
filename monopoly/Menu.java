package monopoly;

import java.util.ArrayList;
import java.util.Scanner;

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

    //Constructor del menú: Desarrollo de la partida (Necesario porque los métodos son privados, por lo que todas las instrucciones deben seguirse aquí)
    public Menu(){
        //Constuctor
        this.dado1 = new Dado();
        this.dado2 = new Dado();
        this.banca = new Jugador();
        this.jugadores = new ArrayList<Jugador>();
        this.avatares = new ArrayList<Avatar>();
        this.tablero = new Tablero(this.banca);
        turno = 0;

        //Partida
        this.tablero.toString();
        Scanner scanner = new Scanner(System.in);
        this.iniciarPartida(scanner);
        System.out.println(this.tablero.toString());
        System.out.println("Instrucciones:\n");
        analizarComando("ayuda");

        String comando;
        do {
            System.out.println("Introduce un comando: ");
            comando = scanner.nextLine(); // Leer el comando del usuario
            analizarComando(comando); // Llama a tu método para procesar el comando
        } while (!comando.equals("finalizar"));
    }
    // Métodos Getter y Setter para cada atributo
    // por ahora no se utilizan
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    // public void setJugadores(ArrayList<Jugador> jugadores) {
    //     this.jugadores = jugadores;
    // }   

    public ArrayList<Avatar> getAvatares(){
        return avatares;
    }
    // public void setAvatares(ArrayList<Avatar> avatares) {
    //     this.avatares = avatares;
    // }

    public int getTurno(){
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

    // Método para inciar una partida: crea los jugadores y avatares.
    private void iniciarPartida(Scanner scanner) {
        System.out.println("Introduce los datos de un jugador (nombre y tipo de avatar):");
        String[] j1 = scanner.nextLine().split(" ");
        if (j1.length != 2) {
            throw new IllegalArgumentException("Error: Debes introducir exactamente 2 datos separados por un espacio (nombre y tipo de avatar).");
        }
        crearJugador(j1[0], j1[1]);
        System.out.println("Introduce los datos de otro jugador (nombre y tipo de avatar):");
        String[] j2 = scanner.nextLine().split(" ");
        if (j2.length != 2) {
            throw new IllegalArgumentException("Error: Debes introducir exactamente 2 datos separados por un espacio (nombre y tipo de avatar).");
        }
        crearJugador(j2[0], j2[1]);
    }
    
    /*Método que interpreta el comando introducido y toma la accion correspondiente.
    * Parámetro: cadena de caracteres (el comando).
    */
    private void analizarComando(String comando) {
        String[] partes = comando.split(" ");
        switch (partes[0]) {
            case "crear":
                if (partes.length != 4) {
                    System.out.println("Error: Debes introducir el comando y exactamente 2 datos separados por un espacio (nombre y tipo de avatar).");
                    break;
                }
                this.crearJugador(partes[2], partes[3]);
                break;

                case "jugador":
                    if (partes.length != 1) {
                        System.out.println("Error: Este comando no tiene argumentos.");
                        break;
                    }
                    System.out.println("{\n\tnombre: " + this.jugadores.get(this.turno).getNombre() + ",\n\tavatar: " + this.jugadores.get(this.turno).getAvatar().getId() + "\n}");
                    break;

                case "listar":
                    if (partes.length != 2) {
                        System.out.println("Error: Debes introducir el comando completo.");
                        break;
                    }
                    switch (partes[1]) {
                        case "jugadores":               //Ya va
                            this.listarJugadores();
                            break;

                        case "avatares":                
                            this.listarAvatares();
                            break;

                        case "enventa":                 //No va
                            this.listarVenta();
                            break;

                        default:
                            break;
                    }
                    break;

                case "lanzar":                      //Falta enseñar tablero
                    if (partes.length != 2) {
                        System.out.println("Error: Debes introducir el comando completo.");
                        break;
                    }
                    this.lanzarDados();
                    break;

                case "acabar":                      //No acaba el turno, funcion corregida pero no se sabe si va
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
                    
                case "describir":
                if (partes.length ==1) {
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
                        if (partes.length != 2) {                                                                                                       //Corregir el caso de no encontrarla
                            System.out.println("Error: Debes introducir el comando y exactamente 1 argumento (casilla).");
                            break;
                        }
                        this.descCasilla(partes[1]);
                        break;
                }
                break;

                case "comprar":                                                                                                                         //Corregir caso de no existir la casilla y caso de no ser una casilla que se pueda comprar
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
<<<<<<< HEAD
                }
                
            case "describir":
                if (partes.length ==1) {
                    System.out.println("Error: Debes introducir el comando completo");
                    break;
                }
                break;
            }
    }
        // Método para inciar una partida: crea los jugadores y avatares.
        private void iniciarPartida(Scanner scanner) {
            System.out.println("Introduce los datos de un jugador (nombre y tipo de avatar):");
            String[] j1 = scanner.nextLine().split(" ");
            if (j1.length != 2) {
                throw new IllegalArgumentException("Error: Debes introducir exactamente 2 datos separados por un espacio (nombre y tipo de avatar).");
            }
            crearJugador(j1[0], j1[1]);
            System.out.println("Introduce los datos de otro jugador (nombre y tipo de avatar):");
            String[] j2 = scanner.nextLine().split(" ");
            if (j2.length != 2) {
                throw new IllegalArgumentException("Error: Debes introducir exactamente 2 datos separados por un espacio (nombre y tipo de avatar).");
            }
            crearJugador(j2[0], j2[1]);
=======
                    }
                    System.out.println(this.tablero.toString());
                    break;

                case "ayuda":
                    System.out.println("Lista de comandos:\ncrear jugador (nombre) (avatar): Crea un nuevo jugador con el nombre y avatar introducidos.");
                    System.out.println("jugador: Indica el jugador que tiene el turno\nlistar jugadores: Lista los jugadores de la partida y sus carácteristicas\nlistar avatares: Lista los avatares de la partida y sus características");
                    System.out.println("listar enventa: Lista las propiedades en venta\nlanzar dados: Lanza los dados y mueve el avatar, describiendo sus próximas acciones");
                    System.out.println("acabar turno: Finaliza el turno del jugador actual\nsalir carcel: Paga la cantidad necesaria para que el jugador salga de la cárcel");
                    System.out.println("describir jugador (jugador): Muestra las carácteristicas del jugador introducido\ndescribir avatar (avatar): Muestra las carácteristicas del avatar introducido");
                    System.out.println("describir (casilla): Muestra las carácteristicas de la casilla introducida\ncomprar (casilla): Compra la propiedad indicada\nver tablero: Muestra el tablero en su estado actual\n\n");
                    break;
                default:
                    System.out.println("Error: El comando introducido no es correcto.");
                break;
            }
>>>>>>> e1841ddcd3d9ce25407ef7c7c09eeb042d48e2c9
        }

    /*Método que da de alta a un jugador
    * Parámetros: nombre del jugador y tipo del avatar
    */
    private void crearJugador(String nombrejugador, String avatar_j) {
        Jugador jugador = new Jugador(nombrejugador, avatar_j, this.getTablero().getPosiciones().get(0).get(0), avatares);
        jugadores.add(jugador);
        avatares.add(jugador.getAvatar());
        this.getTablero().getPosiciones().get(0).get(0).anhadirAvatar(jugador.getAvatar());
        System.out.println("{\n\tnombre: " + jugador.getNombre() + ",\n\tavatar: " + jugador.getAvatar().getId() + "\n}"); //El avatar debe ser una letra generada automaticamente
    }

    /*Método que realiza las acciones asociadas al comando 'describir jugador'.
    * Parámetro: comando introducido
        */
    private void descJugador(String[] partes) {
        //Nombre del jugador a describir
        String nombreJugador = partes[2]; 

            //Buscamos al jugador
        Jugador jugadorBuscado=null;
        for (Jugador jugador :jugadores){
            if (jugador.getNombre().equalsIgnoreCase(nombreJugador)){
                jugadorBuscado=jugador;
            break;
            }
        }
        //si encontramos al jugador, imprimimos su información
        if (jugadorBuscado!=null){
            System.out.println(jugadorBuscado.toString()); 
        }
        else{
            System.out.println("El jugador no ha sido encontrado. Compruebe el nombre");

    /*Método que realiza las acciones asociadas al comando 'describir avatar'.
    * Parámetro: id del avatar a describir.
    */
    private void descAvatar(String ID) {
        for (Avatar av: avatares){
            if(av.getId().equals(ID)){
                System.out.println("{ ");
                System.out.println("id: "+av.getId());
                System.out.println("tipo: "+av.getTipo());
                System.out.println("casilla: "+av.getLugar());
                System.out.println("jugador: "+av.getJugador());
                System.out.println("} ");
                break;
                //System.out.println(av.toString());
                //return; 
            }
            else{
                System.out.println("No se ha encontrado ningún avatar con ese ID.");
            }
        }



    /* Método que realiza las acciones asociadas al comando 'describir nombre_casilla'.
    * Parámetros: nombre de la casilla a describir.
    */
    private void descCasilla(String nombre) {
        //hay que tener en cuenta qué tipo de casilla es. Caja de Comunidad, Suerte e IrACárcel no tiene sentido describirlas.
        //Buscamos la Casilla
        System.out.println(this.tablero.encontrar_casilla(nombre).infoCasilla());
    }


    /* Método que realiza las acciones asociadas al comando 'describir nombre_casilla'.
    * Parámetros: nombre de la casilla a describir.
    */

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
<<<<<<< HEAD
            }
            tirado = true;
        }

        /*Método que ejecuta todas las acciones realizadas con el comando 'comprar nombre_casilla'.
        * Parámetro: cadena de caracteres con el nombre de la casilla.
         */
        private void comprar(String nombre) {
            Jugador jActual = this.jugadores.get(this.turno);
            Casilla casilla = this.tablero.encontrar_casilla(nombre);

            if (casilla == null){
                System.out.println("La casilla " + nombre + " no existe en el tablero.");
                return;
            }
            if (casilla.getDuenho() != null && !casilla.getDuenho().equals(this.banca)){
                System.out.println("La casilla "+ nombre + " ya tiene propietario.");
                return;
            }

            float precio = casilla.getValor();

            System.out.println("La casilla " + nombre + " cuesta " + precio);
            System.out.println(("Desea comprar la casilla (si o no): "));
            Scanner scanner = new Scanner(System.in);
            String respuesta = scanner.nextLine();
            
            if(respuesta.equalsIgnoreCase("si")){
                if(jActual.getFortuna() < precio){
                    System.out.println("No dispone de suficiente dinero para comprar la casilla.");
                    scanner.close();
                    return;
                }

                jActual.sumarGastos(precio);
                casilla.setDuenho(jActual);

                jActual.anhadirPropiedad(casilla);
                System.out.println("El jugador " + jActual.getNombre() + " ha comprado la casilla " + nombre );
=======
>>>>>>> e1841ddcd3d9ce25407ef7c7c09eeb042d48e2c9
            }else {
                System.out.println("Fin del turno. " + jActual.getNombre() + " no ha sacado dobles");
                jActual.getAvatar().moverAvatar(tablero.getPosiciones(), sumaDados);

                Casilla casActual = jActual.getAvatar().getLugar();
                if(casActual.getTipo().equals("Solar") || casActual.getTipo().equals("Transporte") || casActual.getTipo().equals("Servizos")){
                    Jugador duenho = casActual.getDuenho(); // Dueño de la casilla en la que se callo.

                    if(duenho != null && !duenho.equals(jActual) && !duenho.equals(this.banca)){
                        float alquiler = casActual.getImpuesto();
                        System.out.println("La casilla " + casActual.getNombre() + " es propiedad de " + duenho.getNombre() + ".");
                        System.out.println("Debes pagar " + alquiler + " de alquiler.");

                        if( jActual.getFortuna()< alquiler){
                            System.out.println("El jugador " + jActual.getNombre() + " no tiene suficiente dinero. Debes hipotecar propiedades o declararte en bancarrota.");
                            // aqui iria un codigo para o hipotecarse o declararse en bancarrota.
                            return;
                        } else{
                            jActual.sumarGastos(alquiler);
                            duenho.sumarFortuna(alquiler);
                            System.out.println(jActual.getNombre() + " ha pagado " + alquiler + " de alquier a " + duenho.getNombre() + ".");
                        }

                    }

                }else if(casActual.getTipo().equals("IrACarcel")){
                    System.out.println("Has caido en la casilla " + casActual.getNombre() + ". Te moverás a la casilla de cárcel.");
                    jActual.encarcelar(tablero.getPosiciones());
                }else if(casActual.getTipo().equals("Parking")){
                    float bote = casActual.getValor();
                    System.out.println("Has caido en la casilla " + casActual.getNombre() + ". Recibes " + bote + ".");
                    jActual.sumarFortuna(bote);
                }else if (casActual.getTipo().equals("Suerte") || casActual.getTipo().equals("Comunidad")){
                    System.out.println("Has caido en una casilla de tipo Suerte o Caja de comundiad.");
                }
                break;
            }
        
        }
        tirado = true;
    }

    /*Método que ejecuta todas las acciones realizadas con el comando 'comprar nombre_casilla'.
    * Parámetro: cadena de caracteres con el nombre de la casilla.
        */
    private void comprar(String nombre) {
        Jugador jActual = this.jugadores.get(this.turno);
        Casilla casilla = this.tablero.encontrar_casilla(nombre);

        if (casilla == null){
            System.out.println("La casilla " + nombre + " no existe en el tablero.");
            return;
        }
        if (casilla.getDuenho() != null && !casilla.getDuenho().equals(this.banca)){
            System.out.println("La casilla "+ nombre + " ya tiene propietario.");
            return;
        }

        float precio = casilla.getValor();

        System.out.println("La casilla " + nombre + " cuesta " + precio);
        System.out.println(("Desea comprar la casilla (si o no): "));
        Scanner scanner = new Scanner(System.in);
        String respuesta = scanner.nextLine();
        
        if(respuesta.equalsIgnoreCase("si")){
            if(jActual.getFortuna() < precio){
                System.out.println("No dispone de suficiente dinero para comprar la casilla.");
                //scanner.close();          //REVISAR
                return;
            }

            jActual.sumarGastos(precio);
            casilla.setDuenho(jActual);

            jActual.anhadirPropiedad(casilla);
            System.out.println("El jugador " + jActual.getNombre() + " ha comprado la casilla " + nombre );
        }else {
            System.out.println(jActual.getNombre() + " ha decidico no comprar la casilla.");
        }
        
        //scanner.close();          //REVISAR
    
    }

    //Método que ejecuta todas las acciones relacionadas con el comando 'salir carcel'. 
    private void salirCarcel() {

        Jugador jActual = jugadores.get(turno);
        if (jActual.isEnCarcel()){
            if(jActual.getFortuna() >= 500000){
                jActual.sumarGastos((500000));
                jActual.setEnCarcel(false);
                System.out.println(jActual.getNombre() + " paga 500000€ y sale de la carcel. Puede lanzar los daos.");
            }else {
                System.out.println(jActual.getNombre() + " no tiene suficiente dinero para pagar la multa de 500000€.");
            }
        }else {
            System.out.println(jActual.getNombre() + " no está en la carcel.");
        }

    }

    // Método que realiza las acciones asociadas al comando 'listar enventa'.
    private void listarVenta() {
        ArrayList<ArrayList<Casilla>> casilla=tablero.getPosiciones();
        for (ArrayList<Casilla> lado : casilla){
            for (Casilla i:lado){
                //if(i.getDuenho()==banca){ //tipo solar, transporte o servicio!!!!!!!!!
                    //System.out.println("{ ");
                    //System.out.println("tipo: "+ i.getTipo());
                    //System.out.println("grupo: "+ i.getGrupo());
                    //System.out.println("tipo: "+ i.getValor());
                //System.out.println("} ");
                if(i.getDuenho()!=banca){
                    System.out.println(i.casEnVenta());
                }
            }   
        }
    
    } 

    // Método que realiza las acciones asociadas al comando 'listar jugadores'.
    private void listarJugadores() {
        for (Jugador i: jugadores){
            System.out.println(i.toString());
        }
    }

    // Método que realiza las acciones asociadas al comando 'listar avatares'.
    private void listarAvatares() {

        if ( avatares.isEmpty()){
            System.out.println("No hay avatares creados.");
        }else{
            for (Avatar i: avatares){
                System.out.println(i.toString());
            }
        }
    

    }

    // Método que realiza las acciones asociadas al comando 'acabar turno'.
    private void acabarTurno() {
        if (tirado){
            lanzamientos = 0; 
            turno++;

            if (turno >= jugadores.size()){
                turno = 0; // Regresamos la 1º jugador.
            }

            Jugador jActual = jugadores.get(turno);
            tirado = false;

            System.out.println("El jugador actual es " + jActual.getNombre()+".");
        }
        else{
            System.out.println("No realizaste la tirada");
        }
        
            if (jugadores.isEmpty()){
                System.out.println("No hay jugadores en el juego.");
                return;
            }
            Jugador jActual = jugadores.get(turno);
            tirado = false; // reiniciamos la variable para el promximo turno
             // solvente = true;
            turno = (turno + 1) % jugadores.size(); // Obtenemos el siguiente jugador.
            if(turno >= jugadores.size()){
                turno = 0;
            }
        
            Jugador jSiguiente = jugadores.get(turno);

            System.out.println("El turno de " + jActual.getNombre()+" ha terminado. Ahora es el turno de " + jSiguiente.getNombre());
        
            if(jugadores.size() == 1 && !jSiguiente.isEnCarcel()){
               System.out.println("Ha terminado.");
            }
        }      
}
