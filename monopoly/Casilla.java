package monopoly;

import partida.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
//import java.util.HashMap;


public class Casilla {

    //Atributos:
    private String nome; //Nombre de la casilla
    private String tipo; //Tipo de casilla Solar, Especial, Transporte, Servizos, Comunidade, Sorte e Impostos)
    private float valor; //Valor de esa casilla (en la mayoría será valor de compra, en la casilla parking se usará como el bote).
    private float valorinicial;
    private int posicion; //Posición que ocupa la casilla en el tablero (entero entre 1 y 40).
    private Jugador duenho; //Dueño de la casilla (por defecto sería la banca).
    private Grupo grupo; //Grupo al que pertenece la casilla (si es solar).
    private float impuesto; //Cantidad a pagar por caer en la casilla: el alquiler en solares/servicios/transportes o impuestos.
    private float hipoteca; //Valor otorgado por hipotecar una casilla
    private ArrayList<Avatar> avatares; //Avatares que están situados en la casilla.
    private HashMap<Jugador, Integer> caidasJugador;

    private Mazo mazo;
    private Jugador banca;
    //private Tablero tablero;

    private Map<Casilla, Integer> vecesCaidasCasilla = new HashMap<>();
    private Casilla casillaMasFrecuentada;
    private Jugador jugadorMasVueltas;


    private ArrayList<Edificacion> edificaciones; //Edificaciones que contiene la casilla
    private int numCasas=0;
    private int numHoteles=0;
    private int numPiscinas=0;
    private int numPistas=0;
    
    
    public String getNombre(){
        return this.nome;
    }

    public void setDuenho(Jugador duenho){
        this.duenho = duenho;
    }
    public Jugador getDuenho() {
        return duenho;
    }
    public String getTipo(){
        return this.tipo;
    }
    public float getValor(){
        return this.valor;
    }
    public void setValor(float valor){
        this.valor = valor;
    }
    public Grupo getGrupo() {
        return grupo;
    }
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    public int getPosicion(){
        return this.posicion;
    }
    public void setPosicion(int posicion){
         this.posicion=posicion;
    }
    public ArrayList<Avatar> getAvatares() {
        return avatares;
    }
    public float getImpuesto(){
        return impuesto;
    }

    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
    }
    public ArrayList<Edificacion> getEdificaciones() {
        return edificaciones;
    }

    public int getnumCasas(){
        return this.numCasas;
    }
    public void setnumCasas(int numCasas){
        this.numCasas=numCasas;
    }

    public int getnumHoteles(){
        return this.numHoteles;
    }
    public void setnumHoteles(int numHoteles){
        this.numHoteles=numHoteles;
    }

    public int getnumPiscinas(){
        return this.numPiscinas;
    }
    public void setnumPiscinas(int numPiscinas){
        this.numPiscinas=numPiscinas;
    }

    public int getnumPistas(){
        return this.numPistas;
    }
    public void setnumPistas(int numPistas){
        this.numPistas=numPistas;
    }


    public boolean isComprada(){
        return duenho != null;
    }
    
    public float getAlquiler(){
        return this.impuesto;
    }

    public Jugador getBanca() {
        return banca;
    }

    //Constructores:
    public Casilla() {

        this.nome = "Sin nombre";
        this.tipo = "Sin tipo";
        this.valor = 0.0f;
        this.posicion = 0;
        this.duenho = null;
        this.grupo = null;
        this.impuesto = 0.0f;
        this.hipoteca = 0.0f;
        this.avatares = new ArrayList<Avatar>();

    }//Parámetros vacíos

    /*Constructor para casillas tipo Solar, Servicios o Transporte:
    * Parámetros: nombre casilla, tipo (debe ser solar, serv. o transporte), posición en el tablero, valor y dueño.
     */
    public Casilla(String nombre, String tipo, int posicion, float valor, Jugador duenho) {

        this.nome = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.valor = valor;
        this.valorinicial = valor;
        this.duenho= duenho;
        this.avatares = new ArrayList<Avatar>();
        this.edificaciones=new ArrayList<Edificacion>();
        this.caidasJugador=new HashMap<>();
        this.impuesto = 0.1f*valor;
    }

    /*Constructor utilizado para inicializar las casillas de tipo IMPUESTOS.
    * Parámetros: nombre, posición en el tablero, impuesto establecido y dueño.
     */
    public Casilla(String nombre, int posicion, float impuesto, Jugador duenho) {

        this.nome = nombre;
        this.posicion = posicion;
        this.impuesto = impuesto;
        this.duenho = duenho;
        this.tipo = "Impuestos";
        this.avatares = new ArrayList<Avatar>();
        this.edificaciones = new ArrayList<Edificacion>();
        this.caidasJugador=new HashMap<>();
    }

    /*Constructor utilizado para crear las otras casillas (Suerte, Caja de comunidad y Especiales):
    * Parámetros: nombre, tipo de la casilla (será uno de los que queda), posición en el tablero y dueño.
     */
    public Casilla(String nombre, String tipo, int posicion, Jugador duenho) {
        this.nome=nombre;
        this.tipo=tipo;
        this.posicion=posicion;
        this.duenho=duenho;
        this.avatares = new ArrayList<Avatar>();
        this.edificaciones = new ArrayList<Edificacion>();
        this.caidasJugador=new HashMap<>();
        
    }
    //Método utilizado para añadir un avatar al array de avatares en casilla.
    public void anhadirAvatar(Avatar av) {
        this.avatares.add(av);
    }
    //Método utilizado para eliminar un avatar del array de avatares en casilla.
    public void eliminarAvatar(Avatar av) {
        this.avatares.remove(av);
    }

    public float calcularAlquilerSolar(Jugador jActual){
        if(this.tipo.equals("Solar")){
            if (this.getGrupo().esDuenhoGrupo(jActual)){
                this.impuesto = this.valorinicial * 0.2f;          //Si el jugador es dueño de todas las casillas del grupo, el alquiler se duplica.
                return impuesto;
            }
            else{
                this.impuesto = this.valorinicial * 0.1f;
                return impuesto;
            }
        }
        return 0;
    }

    public float calcularAlquilerServicio(int sumaDados){
        if(this.tipo.equals("Servicio") && this.isComprada()){

            int numServicio = this.duenho.contarCasServicio();
            float factoServicio = Valor.SUMA_VUELTA / 200;
            if(numServicio == 1){
                this.impuesto = 4 * sumaDados * factoServicio;
            }else if(numServicio == 2){
                this.impuesto = 10 * sumaDados * factoServicio;
            }else{
                this.impuesto = 0.0f;
            }
            return impuesto;
        }
        return 0;
    }

    public float calcularAlquilerTransporte(Jugador jActual){
        if(this.tipo.equals("Transporte")){
            Jugador duenho = this.getDuenho();
            if(duenho != null){
                int cantidadTransporte = duenho.getCantidadPropiedades("Transporte");
                System.out.println("Cantidad de transportes: " + cantidadTransporte);
                switch (cantidadTransporte){
                    case 1:
                        this.impuesto = this.valorinicial * 0.25f; 
                        break;
                    case 2:
                        this.impuesto = this.valorinicial * 0.5f; 
                        break;
                    case 3:
                        this.impuesto = this.valorinicial * 0.75f; 
                        break;
                    case 4:
                        this.impuesto = this.valorinicial; 
                        break;
                    default:
                        this.impuesto = 0.0f;
                        break;
                }
            }  
        }
        return this.impuesto;
    }

    public float calcularAlquilerEdificaciones(float alquiler){
        float alquilerCasas=0.0f, alquilerHoteles=0.0f, alquilerPiscinas=0.0f, alquilerPistas=0.0f, alquilerTotal=0.0f;
        System.out.println("Además esta casilla tiene edificaciones.Tendrás que pagar su alquiler también.");
            if(numCasas!=0){
                if(numCasas==1){
                    alquilerCasas=5*alquiler;
                }
                else if(numCasas==2){
                    alquilerCasas=15*alquiler;
                }
                else if(numCasas==3){
                    alquilerCasas=35*alquiler;
                }
                else{
                    alquilerCasas=50*alquiler;
                }
                System.out.println("Hay "+getnumCasas()+" casas edificadas. Debes pagar "+alquilerCasas);
                
            }
            if (numHoteles!=0){
                alquilerHoteles=numHoteles*70*alquiler;
                System.out.println("Hay "+getnumHoteles()+" hoteles edificados. Debes pagar "+alquilerHoteles);
            }
            if (numPiscinas!=0){
                alquilerPiscinas=numPiscinas*25*alquiler;
                System.out.println("Hay "+getnumPiscinas()+" piscinas edificadas. Debes pagar "+alquilerPiscinas);
            }
            if (numPistas!=0){
                alquilerPistas=numPistas*25*alquiler;
                System.out.println("Hay "+getnumPistas()+" pistas edificadas. Debes pagar "+alquilerPistas);
            }
            alquilerTotal=alquiler+alquilerCasas+alquilerHoteles+alquilerPiscinas+alquilerPistas;
            return alquilerTotal;
    }
    
    /*Método para evaluar qué hacer en una casilla concreta. Parámetros:
    * - Jugador cuyo avatar está en esa casilla.
    * - La banca (para ciertas comprobaciones).
    * - El valor de la tirada: para determinar impuesto a pagar en casillas de servicios.
    * Valor devuelto: true en caso de ser solvente (es decir, de cumplir las deudas), y false
    * en caso de no cumplirlas.*/
    public boolean evaluarCasilla(Jugador actual, Jugador banca, int tirada) {
        jugadorCaerCasilla(actual);
        String tipoCasilla = this.getTipo(); // Obtener el tipo de casilla
        float alquiler = 0;
        
        // Si la casilla es una propiedad
        if (tipoCasilla.equals("Solar") || tipoCasilla.equals("Transporte") || tipoCasilla.equals("Servicio")) {
            Jugador duenho = this.getDuenho(); // Obtener el dueño de la propiedad
    
            // Comprobar si la propiedad pertenece a otro jugador
            if (duenho != null && !duenho.equals(actual)) {
        
                if(tipoCasilla.equals("Solar")){
                    alquiler = this.calcularAlquilerSolar(duenho);
                }else if (tipoCasilla.equals("Servicio")){
                    alquiler = this.calcularAlquilerServicio(tirada);
                }else if(tipoCasilla.equals("Transporte")){
                    alquiler = this.calcularAlquilerTransporte(actual);
                }
                if (!duenho.equals(banca)){    
                    System.out.println("La casilla es propiedad de " + duenho.getNombre() + ". Debes pagar " + alquiler + " de alquiler.");
                    if (!edificaciones.isEmpty()){
                        //aquí iría el contenido de la función
                        float alquilerT=calcularAlquilerEdificaciones(alquiler);
                        if (actual.getFortuna()<alquilerT){
                            System.out.println("El jugador " + actual.getNombre() + " no tiene suficiente dinero para pagar el alquiler. Debes hipotecar propiedades o declararte en bancarrota.");
                            return false; 
                        }
                        else{
                            actual.sumarGastos(alquilerT);
                            actual.incrementarDineroAlquiler(alquilerT);
                            duenho.sumarFortuna(alquilerT);
                            duenho.incrementarRecibidoAlquiler(alquilerT);
                            System.out.println("Has pagado " + alquilerT + " de alquiler a " + duenho.getNombre() + ".");
                            
                        }
                    }
                    else{
                        // Verificar si el jugador tiene suficiente dinero
                        if (actual.getFortuna() < alquiler) {
                            System.out.println("El jugador " + actual.getNombre() + " no tiene suficiente dinero para pagar el alquiler. Debes hipotecar propiedades o declararte en bancarrota.");
                            return false; // Jugador no es solvente
                        } else {
                            // Pagar el alquiler
                            actual.sumarGastos(alquiler);
                            actual.incrementarDineroAlquiler(alquiler);
                            //actual.sumarFortuna(-alquiler);
                            //duenho.sumarFortuna(alquiler);
                            duenho.sumarFortuna(alquiler);
                            duenho.incrementarRecibidoAlquiler(alquiler);
                        
                            System.out.println("Has pagado " + alquiler + " de alquiler a " + duenho.getNombre() + ".");
                        }
                    }
                }
                else{
                    return actual.getFortuna() > valor;
                }
                
            }
        } 
        // Si la casilla es Parking
        else if (this.nome.equals("Parking")) {
            float bote = this.getValor(); // Obtener el bote
            System.out.println("Has caído en 'Parking'. Recibes " + bote + ".");
            actual.sumarFortuna(bote);
            actual.incrementarDineroParking(bote);
            this.setValor(0);
        }
        else if (tipoCasilla.equals("Suerte") || tipoCasilla.equals("Comunidad")){
            this.mazo = new Mazo();
            System.out.println("Has caido en una casilla de tipo Suerte o Caja de Comunidad");
            Tablero tablero = Tablero.getInstancia(banca);
            manejarCaidaEnCasilla(actual,mazo, tablero);
        }
        
        else if(tipoCasilla.equals("Impuestos")){

            if(actual.getFortuna() < this.impuesto){    //Comprueba si el jugador tiene dinero para pagar el impuesto
                return false;
            }
            System.out.println("Has caido en la casilla " + this.getNombre() + ". Pagas " + this.impuesto + ".");
            actual.sumarGastos(this.impuesto);
            actual.incrementarDineroImpuestos(alquiler);
        }
        else if(this.nome.equals("Carcel")){        //REVISAR: Creo que el codigo nunca llega aqui
            if(actual.getFortuna() < 500000){
                return false;
            }
        }
        return true; // El jugador sigue siendo solvente
    }

    //PODRIA SOBRAR
    /*Método usado para comprar una casilla determinada. Parámetros:
    * - Jugador que solicita la compra de la casilla.
    * - Banca del monopoly (es el dueño de las casillas no compradas aún).*/
    public void comprarCasilla(Jugador solicitante, Jugador banca) {
        //cambiar el dueño de la casilla (deja de ser de la banca y pasa a ser del solicitante)
        this.duenho=solicitante;
        solicitante.anhadirPropiedad(this);
        banca.eliminarPropiedad(this);
        solicitante.sumarGastos(this.valor);
        //solicitante.incrementarDineroPropiedades(this.valor);
        banca.sumarFortuna(this.valor);
    }

    /*Método para añadir valor a una casilla. Utilidad:
    * - Sumar valor a la casilla de parking.
    * Este método toma como argumento la cantidad a añadir del valor de la casilla.*/
    public void sumarValor(float suma) {
        this.valor+=suma;
    }

    /*Método para mostrar información sobre una casilla.
    * Devuelve una cadena con información específica de cada tipo de casilla.*/
    public String infoCasilla() {
        if(this.tipo.equals("Solar")){
            return ("""
            {
                tipo: %s,
                grupo: %s,
                propietario: %s,
                valor: %f,
                alquiler: %f,
                número de casas: %d,
                número de hoteles: %d,
                número de piscinas: %d,
                número de pistas de deporte: %d,
                valor hotel: %f,
                valor casa: %f,
                valor piscina: %f,
                valor pista de deporte: %f,
                alquiler una casa: %f,
                alquiler dos casas: %f,
                alquiler tres casas: %f,
                alquiler cuatro casas: %f,
                alquiler hotel: %f,
                alquiler piscina: %f,
                alquiler pista de deportes: %f
            } 
            """.formatted(this.tipo, this.grupo.getColorGrupo(), this.duenho.getNombre(), this.valorinicial, this.impuesto, this.numCasas, this.numHoteles, this.numPiscinas, this.numPistas, 0.6f*this.valor, 0.6f*this.valor, 0.4f*this.valor, 1.25f*this.valor, 5*this.impuesto, 15*this.impuesto, 35*this.impuesto, 50*this.impuesto, 70*this.impuesto, 25*this.impuesto, 25*this.impuesto));
        }
        if(this.tipo.equals("Impuesto")){
            return ("""
            {
                tipo: %s,
                a pagar: %f
            }
            """.formatted(this.tipo, this.impuesto));
        }

        if(this.tipo.equals("Salida")){
            return ("""
            {
                recibe: %f
            }
            """.formatted(Valor.SUMA_VUELTA));
        }

        if (this.nome.equals("Parking")){
            StringBuilder texto = new StringBuilder("[");
            for(int i=0; i<avatares.size();i++){
                texto.append(avatares.get(i).getJugador().getNombre());
                if (i!=avatares.size()-1){
                    texto.append(", ");
                }
            }
            texto.append("]");

            return ("""
            {
                bote: %f,
                jugadores: %s
            }
            """.formatted(this.valor, texto)); 

        }

        if (this.nome.equals("Carcel")){
            StringBuilder texto = new StringBuilder("");
            for(int i=0; i<avatares.size();i++){
                texto.append("[");
                texto.append(avatares.get(i).getJugador().getNombre() + ",");
                texto.append(avatares.get(i).getJugador().getTiradasCarcel());
                texto.append("]");
                if (i!=avatares.size()-1){
                    texto.append(", ");
                }
            }

            return ("""
            {
                salir: %f,
                jugadores: %s 
            
            } 
            """.formatted(1/4*Valor.SUMA_VUELTA, texto)); 
        }
        if (this.nome.equals("IrCarcel") || this.tipo.equals("Suerte") || this.tipo.equals("Comunidad") || this.tipo.equals("Servicio") || this.tipo.equals("Transporte") ){
            return("La casilla " + this.nome + " no tiene descripción");
        }
        return("La casilla no existe");
    }

    /* Método para mostrar información de una casilla en venta.
     * Valor devuelto: texto con esa información.
     */
    public String casEnVenta() {
        if (this.tipo.equals("Solar")){
            return ("""
            {
                nombre: %s,
                tipo: %s,
                grupo: %s,
                valor: %f
            }
            """.formatted(color(this.nome), this.tipo, color(this.grupo.getColorGrupo()), this.valor));
        }
        else if (this.tipo.equals("Transporte") || this.tipo.equals("Servicio")){
            return("""
            {
                nombre: %s,
                tipo: %s,
                valor: %f
            }
            """.formatted(this.nome, this.tipo, this.valor));
        }
        return("La casilla " + this.nome + " no se puede vender");
    }


    private String color(String nombre_cas){
        String color_casilla = this.getGrupo().getColorGrupo();

        switch (color_casilla) {
            case "Black":
                return (Valor.BLACK + nombre_cas + Valor.RESET);
            case "Red":
                return (Valor.RED + nombre_cas + Valor.RESET);
            case "Green":
                return (Valor.GREEN + nombre_cas + Valor.RESET);
            case "Yellow":
                return (Valor.YELLOW + nombre_cas + Valor.RESET);
            case "Blue":
                return (Valor.BLUE + nombre_cas + Valor.RESET);
            case "Purple":
                return (Valor.PURPLE + nombre_cas + Valor.RESET);
            case "Cyan":
                return (Valor.CYAN + nombre_cas + Valor.RESET);
            case "Brown":
                return (Valor.BROWN + nombre_cas + Valor.RESET);
            default:
                return (nombre_cas);
        }
    }

    public void manejarCaidaEnCasilla(Jugador jugadorActual, Mazo mazo, Tablero tablero){
        Scanner scanner = new Scanner(System.in);
        if (tipo.equals("Suerte") || tipo.equals("Comunidad")) {
            //mazo.barajar(); // Barajar las cartas

            System.out.println("Elige una carta (1-6): ");
            int eleccion = scanner.nextInt(); // Leer la elección del jugador
            scanner.nextLine(); // Limpiar el buffer

            // Validar la elección
            if (eleccion < 1 || eleccion > 6) {
                System.out.println("Elección inválida. No se realizará ninguna acción.");
                return;
            }

            Carta cartaElegida = mazo.elegirCarta(eleccion);
            System.out.println("Has elegido la carta: " + cartaElegida.getDescripcion());

            // Realizar acción con la carta elegida en el jugador actual
            realizarAccion(cartaElegida, jugadorActual, tablero);
        }
    }

    // Método para realizar la acción de la carta en el jugador actual
    private void realizarAccion(Carta carta, Jugador jugadorActual, Tablero tablero) {
        switch (carta.getAccion()) {
            case "ir_a_transportes1":
                moverJugador(jugadorActual, "Trans1", tablero);
                break;

            case "avanzar_a_solar15":
                moverJugador(jugadorActual, "Solar15", tablero);
                break;

            case "vender_billete":
                jugadorActual.sumarFortuna(500000f);
                jugadorActual.incrementarDineroParking(500000f);
                System.out.println(jugadorActual + " ha ganado 500000€.");
                break;

            case "ir_a_solar3":
                moverJugador(jugadorActual, "Solar3", tablero);
                break;

            case "ir_a_carcel":
               //jugadorActual.encarcelar(tablero.getPosicion("Carcel"));
               jugadorActual.encarcelar(tablero.getPosiciones());
               System.err.println(("Tendría que ir a la carcel."));
                break;

            case "ganar_loteria":
                jugadorActual.sumarFortuna(1000000f);
                jugadorActual.incrementarDineroParking(1000000f);
                System.out.println(jugadorActual.getNombre() + " ha ganado la lotería: 1000000€.");
                break;


            // ACCIONES DE COMUNIDAD

            case "pagar_balneario":
                if (!pagarConFortuna(jugadorActual, 500000f)) {
                    //hipotecarPropiedad(jugadorActual);
                    jugadorActual.incrementarDineroPropiedades(500000f);
                }
                System.err.println(jugadorActual + " ha pagado 500000€.");
                break;

            case "ir_a_salida":
                moverJugador(jugadorActual, "Salida", tablero);
                jugadorPasaPorSalida(jugadorActual);
                break;

            case "recibir_beneficio":
                jugadorActual.sumarFortuna(2000000f);
                jugadorActual.incrementarDineroParking(2000000f);
                break;

            case "pagar_viaje":
                if (!pagarConFortuna(jugadorActual, 1000000f)) {
                    //hipotecarPropiedad(jugadorActual);
                    jugadorActual.incrementarDineroImpuestos(1000000f);
                }
                System.err.println(jugadorActual + " ha pagado 1000000€.");
                break;

            case "pagar_alquiler":
                //pagarJugadores(jugadorActual, 200000f);
                jugadorActual.incrementarDineroImpuestos(200000f);
                break;

            default:
                System.out.println("Acción no implementada.");
                break;
        }
    }

    // Método para verificar si el jugador puede pagar y realizar la hipoteca si no tiene suficiente
    private boolean pagarConFortuna(Jugador jugador, float cantidad) {
        if (jugador.getFortuna() >= cantidad) {
            //jugador.restarFortuna(cantidad);
            jugador.sumarGastos(cantidad);
            System.err.println(jugador + " ha pagado 1000000.");
            return true;
        } else {
            System.out.println("No tienes suficiente dinero para pagar. Debes hipotecar una propiedad.");
        }
        return false;
    }

    // Método para pagar a otros jugadores
    // MODIFICAR FUNCION
    public void pagarJugadores(Jugador jugadorPagador, float cantidad, List<Jugador> jugadores) {
        // Calculamos el total a pagar a cada jugador y la cantidad que se descontará
        float total = cantidad * (jugadores.size() - 1);

        // Verificamos si el jugador tiene suficiente fortuna para realizar el pago
        if (jugadorPagador.getFortuna() < total) {
            System.out.println(jugadorPagador.getNombre() + " no tiene suficiente dinero para pagar a todos los jugadores.");
            // Puedes implementar la lógica de hipoteca o endeudamiento aquí si el jugador no tiene suficiente fortuna
            return;
        }

        // Transferimos la cantidad a cada jugador excepto al jugador que está pagando
        for (Jugador jugador : jugadores) {
            if (!jugador.equals(jugadorPagador)) {
                jugador.setFortuna(jugador.getFortuna() + cantidad);
                System.out.println(jugador.getNombre() + " ha recibido " + cantidad + "€ de " + jugadorPagador.getNombre());
            }
        }

        // Descontamos el total de la fortuna del jugador que está pagando
        jugadorPagador.setFortuna(jugadorPagador.getFortuna() - total);
        System.out.println(jugadorPagador.getNombre() + " ha pagado un total de " + total + "€ a los otros jugadores.");
    }

    public void moverJugador(Jugador jugador, String nombreCasilla, Tablero tablero){
        // Obtener la posición actual del avatar del jugador
        int posicionActual = jugador.getAvatar().getLugar().getPosicion();

        // Buscar la casilla destino utilizando el tablero
        Casilla casDestino = tablero.encontrar_casilla(nombreCasilla);

        // Verificar si la casilla de destino existe
        if (casDestino != null) {
            int posicionDestino = casDestino.getPosicion();
            int desplazamiento;

            // Calcular el desplazamiento
            if (posicionDestino >= posicionActual) {
                desplazamiento = posicionDestino - posicionActual;
            } else {
                desplazamiento = (40 - posicionActual) + posicionDestino; // Volver al inicio
            }

            // Mover el avatar del jugador
            jugador.getAvatar().moverAvatar(Tablero.getInstancia(banca).getPosiciones(), desplazamiento);   //ATENEA: Posible cambio, preguntar a alba
        } else {
            System.out.println("Error. No se encontró la casilla de destino: " + nombreCasilla);
        }
    }

    public void registrarCaida(Jugador jugador){
        //verificamos si el jugador ya está registrado
        if(caidasJugador.containsKey(jugador)){
            caidasJugador.put(jugador, caidasJugador.get(jugador)+1);

        }
        //registramos la caída
        else{
            caidasJugador.put(jugador, 1);
        }
    }

    //necesaria para listar edificaciones.
    public float calcularCoste(Edificacion e){
        float coste=0.0f;
        if(e.getTipo().equals("Casa") || e.getTipo().equals("Hotel")){
            coste=this.valorinicial*0.6f;
        }
        else if(e.getTipo().equals("Piscina")){
            coste=this.valorinicial*0.4f;
        }
        else if(e.getTipo().equals("Pista")){
            coste=this.valorinicial*1.25f;
        }
        return coste;
    }

    public boolean edificarCasa(){
        float precio= this.valorinicial*0.6f;
        if(this.tipo.equals("Solar")){
            if(this.duenho.getFortuna()>=precio){
                if(grupo.esDuenhoGrupo(duenho) || caidasJugador.get(duenho)>2){
                    if(numHoteles<grupo.getNumCasillas()){
                        if(numCasas<4){
                            Edificacion casa=new Edificacion("Casa", precio);
                            this.numCasas=this.numCasas+1;
                            this.duenho.sumarGastos(precio);
                            edificaciones.add(casa);
                            System.out.println("Casa construida en la casilla "+this.nome);
                            System.out.println("La fortuna de "+this.duenho.getNombre()+" se reduce en "+precio);
                            return true;
                        }
                        else{
                            System.out.println("Ya se han edificado cuatro casas en esta casilla. Puedes construir un hotel.");
                            return false;
                        }
                    }
                    else{
                        if(numCasas<grupo.getNumCasillas()){
                            Edificacion casa=new Edificacion("Casa", precio);
                            this.numCasas=this.numCasas+1;
                            this.duenho.sumarGastos(precio);
                            edificaciones.add(casa);
                            System.out.println("Casa construida en la casilla "+this.nome);
                            System.out.println("La fortuna de "+this.duenho.getNombre()+" se reduce en "+precio);
                            return true;

                        }
                        else{
                            System.out.println("Ya se ha alcanzado el número máximo de casas en el grupo");
                            return false;
                        }
                    }

                }
                else{
                    System.out.println("No cumples las condiciones necesarias para edificar. Asegúrate de ser el dueño del grupo o de haber caído dos veces en la casilla");
                    return false;
                }
            }
            else{
                System.out.println("No tienes fortuna suficiente para edificar una casa.");
                return false;
            }

        }
        else{
            System.out.println("Solo se puede edificar en las casillas de tipo Solar.");
            return false;
        }
        
    }


    public boolean edificarHotel(){
        float precio= this.valorinicial*0.6f;
        if(this.tipo.equals("Solar")){
            if(this.duenho.getFortuna()>=precio){
                if(grupo.esDuenhoGrupo(duenho) || caidasJugador.get(duenho)>2){
                    if(numHoteles<grupo.getNumCasillas()){
                        if(numCasas>=4){
                            Edificacion hotel=new Edificacion("Hotel", precio);
                            this.numHoteles=this.numHoteles+1;
                            this.numCasas=this.numCasas-4;
                            this.duenho.sumarGastos(precio);
                            //Quitamos las casas por las que se sustituye el hotal del array
                            Iterator<Edificacion> iterator = edificaciones.iterator();
                            while (iterator.hasNext()) {
                                Edificacion edificacion = iterator.next();
                                if (edificacion.getTipo().equals("Casa")){
                                    iterator.remove();
                                }
                             }
                            edificaciones.add(hotel);
                            System.out.println("Hotel construido en la casilla "+this.nome);
                            System.out.println("La fortuna de "+this.duenho.getNombre()+" se reduce en "+precio);
                            return true;
                        }
                        else{
                            System.out.println("No cumples las condiciones necesarias para edificar un hotel.");
                            return false;
                        }
                        
                    }
                    else{
                        System.out.println("Ya se ha alcanzado el número máximo de hoteles en el grupo");
                        return false;
                    }

                }
                else{
                    System.out.println("No cumples las condiciones necesarias para edificar. Asegúrate de ser el dueño del grupo o de haber caído dos veces en la casilla");
                    return false;
                }
            }
            else{
                System.out.println("No tienes fortuna suficiente para edificar un hotel.");
                return false;
            }

        }
        else{
            System.out.println("Solo se puede edificar en las casillas de tipo Solar.");
            return false;
        }
    }

    public boolean edificarPiscina(){
        float precio= this.valorinicial*0.4f;
        if(this.tipo.equals("Solar")){
            if(this.duenho.getFortuna()>=precio){
                if(grupo.esDuenhoGrupo(duenho) || caidasJugador.get(duenho)>2){
                    if(numPiscinas<grupo.getNumCasillas()){
                        if(numHoteles>=1 && numCasas>=2){
                            Edificacion piscina=new Edificacion("Piscina", precio);
                            this.numPiscinas=this.numPiscinas+1;
                            this.duenho.sumarGastos(precio);
                            edificaciones.add(piscina);
                            System.out.println("Piscina construida en la casilla "+this.nome);
                            System.out.println("La fortuna de "+this.duenho.getNombre()+" se reduce en "+precio);
                            return true;
                        }
                        else{
                            System.out.println("No cumples las condiciones para edificar una piscina.");
                            return false;
                        }
    
                    }
                    else{
                        System.out.println("Ya se ha alcanzado el número máximo de piscinas en el grupo");
                        return false;
                    }

                }
                else{
                    System.out.println("No cumples las condiciones necesarias para edificar. Asegúrate de ser el dueño del grupo o de haber caído dos veces en la casilla");
                    return false;
                }
            }
            else{
                System.out.println("No tienes fortuna suficiente para edificar una piscina.");
                return false;
            }

        }
        else{
            System.out.println("Solo se puede edificar en las casillas de tipo Solar.");
            return false;
        }
    }

    public boolean edificarPista(){
        float precio= this.valorinicial*1.25f;
        if(this.tipo.equals("Solar")){
            if(this.duenho.getFortuna()>=precio){
                if(grupo.esDuenhoGrupo(duenho) || caidasJugador.get(duenho)>2){
                    if(numPistas<grupo.getNumCasillas()){
                        if(numHoteles>=2){
                            Edificacion pista=new Edificacion("Pista", precio);
                            this.numPistas=this.numPistas+1;
                            this.duenho.sumarGastos(precio);
                            edificaciones.add(pista);
                            System.out.println("Pista construida en la casilla "+this.nome);
                            System.out.println("La fortuna de "+this.duenho.getNombre()+" se reduce en "+precio);
                            return true;
                        }
                        else{
                            System.out.println("No cumples las condiciones necesarias para edificar una pista de deporte.");
                            return false;
                        }
                    }
                    else{
                        System.out.println("Ya se ha alcanzado el número máximo de pistas en el grupo");
                        return false;
                    }

                }
                else{
                    System.out.println("No cumples las condiciones necesarias para edificar. Asegúrate de ser el dueño del grupo o de haber caído dos veces en la casilla");
                    return false;
                }
            }
            else{
                System.out.println("No tienes fortuna suficiente para edificar una pista.");
                return false;
            }

        }
        else{
            System.out.println("Solo se puede edificar en las casillas de tipo Solar.");
            return false;
        }
        
    }

    private void jugadorCaerCasilla(Jugador jugador) {
        vecesCaidasCasilla.put(this, vecesCaidasCasilla.getOrDefault(this, 0) + 1);
        actualizarCasillaMasFrecuentada();
    }

    private void actualizarCasillaMasFrecuentada() {
        if (casillaMasFrecuentada == null || vecesCaidasCasilla.get(this) > vecesCaidasCasilla.get(casillaMasFrecuentada)) {
            casillaMasFrecuentada = this;
        }
    }

    private void jugadorPasaPorSalida(Jugador jugador) {
        // Incrementar vueltas del jugador
        jugador.incrementarVueltas();
        actualizarJugadorMasVueltas(jugador);
    }

    private void actualizarJugadorMasVueltas(Jugador jugador) {
        if (jugadorMasVueltas == null || jugador.getVueltas() > jugadorMasVueltas.getVueltas()) {
            jugadorMasVueltas = jugador;
        }
    }

    @Override

    public String toString() {
        return this.nome;
    }
}