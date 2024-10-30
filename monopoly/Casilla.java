package monopoly;

import partida.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Casilla {

    //Atributos:
    private String nome; //Nombre de la casilla
    private String tipo; //Tipo de casilla Solar, Especial, Transporte, Servizos, Comunidade, Sorte e Impostos)
    private float valor; //Valor de esa casilla (en la mayoría será valor de compra, en la casilla parking se usará como el bote).
    private int posicion; //Posición que ocupa la casilla en el tablero (entero entre 1 y 40).
    private Jugador duenho; //Dueño de la casilla (por defecto sería la banca).
    private Grupo grupo; //Grupo al que pertenece la casilla (si es solar).
    private float impuesto; //Cantidad a pagar por caer en la casilla: el alquiler en solares/servicios/transportes o impuestos.
    private float hipoteca; //Valor otorgado por hipotecar una casilla
    private ArrayList<Avatar> avatares; //Avatares que están situados en la casilla.

    private Mazo mazo;
    private Jugador banca;

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
    public int setPosicion(){
        return this.posicion;
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

    public boolean isComprada(){
        return duenho != null;
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
        this.duenho= duenho;
        this.avatares = new ArrayList<Avatar>();
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
        
    }
    //Método utilizado para añadir un avatar al array de avatares en casilla.
    public void anhadirAvatar(Avatar av) {
        this.avatares.add(av);
    }
    //Método utilizado para eliminar un avatar del array de avatares en casilla.
    public void eliminarAvatar(Avatar av) {
        this.avatares.remove(av);
    }

    public float calcularAlquilerSolar(){
        if(this.tipo.equals("Solar")){
            this.impuesto = this.valor * 0.1f;
            return impuesto;
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
                        this.impuesto = this.valor * 0.25f; 
                        break;
                    case 2:
                        this.impuesto = this.valor * 0.5f; 
                        break;
                    case 3:
                        this.impuesto = this.valor * 0.75f; 
                        break;
                    case 4:
                        this.impuesto = this.valor; 
                        break;
                    default:
                        this.impuesto = 0.0f;
                        break;
                }
            }  
        }
        return this.impuesto;
    }
    
    /*Método para evaluar qué hacer en una casilla concreta. Parámetros:
    * - Jugador cuyo avatar está en esa casilla.
    * - La banca (para ciertas comprobaciones).
    * - El valor de la tirada: para determinar impuesto a pagar en casillas de servicios.
    * Valor devuelto: true en caso de ser solvente (es decir, de cumplir las deudas), y false
    * en caso de no cumplirlas.*/
    public boolean evaluarCasilla(Jugador actual, Jugador banca, int tirada) {
        String tipoCasilla = this.getTipo(); // Obtener el tipo de casilla
        float alquiler = 0;
        
        // Si la casilla es una propiedad
        if (tipoCasilla.equals("Solar") || tipoCasilla.equals("Transporte") || tipoCasilla.equals("Servicio")) {
            Jugador duenho = this.getDuenho(); // Obtener el dueño de la propiedad
    
            // Comprobar si la propiedad pertenece a otro jugador
            if (duenho != null && !duenho.equals(actual)) {
        
                if(tipoCasilla.equals("Solar")){
                    alquiler = this.calcularAlquilerSolar();
                }else if (tipoCasilla.equals("Servicio")){
                    alquiler = this.calcularAlquilerServicio(tirada);
                }else if(tipoCasilla.equals("Transporte")){
                    alquiler = this.calcularAlquilerTransporte(actual);
                }
                if (!duenho.equals(banca)){    
                    System.out.println("La casilla es propiedad de " + duenho.getNombre() + ". Debes pagar " + alquiler + " de alquiler.");
        
                    // Verificar si el jugador tiene suficiente dinero
                    if (actual.getFortuna() < alquiler) {
                        System.out.println("El jugador " + actual.getNombre() + " no tiene suficiente dinero para pagar el alquiler. Debes hipotecar propiedades o declararte en bancarrota.");
                        return false; // Jugador no es solvente
                    } else {
                        // Pagar el alquiler
                        actual.sumarGastos(alquiler);
                        //actual.sumarFortuna(-alquiler);
                        duenho.sumarFortuna(alquiler);
                        System.out.println("Has pagado " + alquiler + " de alquiler a " + duenho.getNombre() + ".");
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
            this.setValor(0);
        }
        else if (tipoCasilla.equals("Suerte") || tipoCasilla.equals("Comunidad") || tipoCasilla.equals("Especial")){
            this.mazo = new Mazo();
            System.out.println("Has caido en una casilla de tipo Suerte, Caja de Comunidad o Especial.");
            manejarCaidaEnCasilla(actual,mazo);
        }
        
        else if(tipoCasilla.equals("Impuestos")){

            if(actual.getFortuna() < this.impuesto){
                return false;
            }
            System.out.println("Has caido en la casilla " + this.getNombre() + ". Pagas " + this.impuesto + ".");
            //actual.sumarFortuna(-this.impuesto);
            actual.sumarGastos(this.impuesto);
            banca.sumarFortuna(this.impuesto);
        }
        else if(this.nome.equals("Carcel")){
            if(actual.getFortuna() < 500000){
                return false;
            }
        }
        return true; // El jugador sigue siendo solvente
    }

    /*Método usado para comprar una casilla determinada. Parámetros:
    * - Jugador que solicita la compra de la casilla.
    * - Banca del monopoly (es el dueño de las casillas no compradas aún).*/
    public void comprarCasilla(Jugador solicitante, Jugador banca) {
        //cambiar el dueño de la casilla (deja de ser de la banca y pasa a ser del solicitante)
        this.duenho=solicitante;
        solicitante.anhadirPropiedad(this);
        banca.eliminarPropiedad(this);
        solicitante.sumarGastos(this.valor);
        solicitante.sumarFortuna(-this.valor);
        banca.sumarFortuna(this.valor);
    }

    /*Método para añadir valor a una casilla. Utilidad:
    * - Sumar valor a la casilla de parking.
    * - Sumar valor a las casillas de solar al no comprarlas tras cuatro vueltas de todos los jugadores.
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
            """.formatted(this.tipo, this.grupo.getColorGrupo(), this.duenho.getNombre(), this.valor, this.impuesto, 0.6f*this.valor, 0.6f*this.valor, 0.4f*this.valor, 1.25f*this.valor, 5*this.impuesto, 15*this.impuesto, 35*this.impuesto, 50*this.impuesto, 70*this.impuesto, 25*this.impuesto, 25*this.impuesto));
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

    public void manejarCaidaEnCasilla(Jugador jugadorActual, Mazo mazo){
        Scanner scanner = new Scanner(System.in);
        if (tipo.equals("Suerte") || tipo.equals("Comunidad")) {
            mazo.barajar(); // Barajar las cartas
            
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
            realizarAccion(cartaElegida, jugadorActual);
        }
    }
    // Método para realizar la acción de la carta en el jugador actual
    private void realizarAccion(Carta carta, Jugador jugadorActual) {
        Tablero tablero = Tablero.getInstancia(banca);
        switch (carta.getAccion()) {
            case "ir_a_transportes1":
                moverJugador(jugadorActual, "Trans1", tablero, null);
                break;
                
            case "avanzar_a_solar15":
                moverJugador(jugadorActual, "Solar15", tablero, null);
                break;
                
            case "vender_billete":
                jugadorActual.sumarFortuna(500000);
                break;
                
            case "ir_a_solar3":
                moverJugador(jugadorActual, "Solar3", tablero, null);
                break;
                
            case "ir_a_carcel":
               //jugadorActual.encarcelar(tablero.getPosicion("Carcel"));
               System.err.println(("Tendría que ir a la carcel."));
                break;
                
            case "ganar_loteria":
                jugadorActual.sumarFortuna(1000000);
                break;


            // ACCIONES DE COMUNIDAD
                
            case "pagar_balneario":
                if (!pagarConFortuna(jugadorActual, 500000)) {
                    //hipotecarPropiedad(jugadorActual);
                }
                break;
                
            case "ir_a_salida":
                moverJugador(jugadorActual, "Salida", tablero, null);
                break;
                
            case "recibir_beneficio":
                jugadorActual.sumarFortuna(2000000);
                break;
                
            case "pagar_viaje":
                if (!pagarConFortuna(jugadorActual, 1000000)) {
                    //hipotecarPropiedad(jugadorActual);
                }
                break;
                
            case "pagar_alquiler":
                //pagarJugadores(jugadorActual, 200000f);
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
            return true;
        } else {
            System.out.println("No tienes suficiente dinero para pagar. Debes hipotecar una propiedad.");
            return false;
        }
    }

    // Método para pagar a otros jugadores
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

    public void moverJugador(Jugador jugador, String nombreCasilla, Tablero tablero, ArrayList<ArrayList<Casilla>> casillas) {
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
            jugador.getAvatar().moverAvatar(casillas, desplazamiento);
        } else {
            System.out.println("Error. No se encontró la casilla de destino: " + nombreCasilla);
        }
    }
    @Override

    public String toString() {
        return this.nome;
    }
}