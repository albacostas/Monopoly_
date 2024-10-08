package monopoly;

import partida.*;
import java.util.ArrayList;


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

    //Getters
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
    public float getImpuesto(){
        return this.impuesto;
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

    /*Método para evaluar qué hacer en una casilla concreta. Parámetros:
    * - Jugador cuyo avatar está en esa casilla.
    * - La banca (para ciertas comprobaciones).
    * - El valor de la tirada: para determinar impuesto a pagar en casillas de servicios.
    * Valor devuelto: true en caso de ser solvente (es decir, de cumplir las deudas), y false
    * en caso de no cumplirlas.*/
    public boolean evaluarCasilla(Jugador actual, Jugador banca, int tirada) {
        Casilla casillaActual = actual.getAvatar().getLugar(); // Obtener la casilla actual del avatar
        String tipoCasilla = casillaActual.getTipo(); // Obtener el tipo de casilla
    
        // Si la casilla es una propiedad
        if (tipoCasilla.equals("Solar") || tipoCasilla.equals("Transporte") || tipoCasilla.equals("Servizos")) {
            Jugador duenho = casillaActual.getDuenho(); // Obtener el dueño de la propiedad
    
            // Comprobar si la propiedad pertenece a otro jugador
            if (duenho != null && !duenho.equals(actual)) {
                float alquiler = casillaActual.getImpuesto(); // Obtener el alquiler
    
                System.out.println("La casilla es propiedad de " + duenho.getNombre() + ". Debes pagar " + alquiler + " de alquiler.");
    
                // Verificar si el jugador tiene suficiente dinero
                if (actual.getFortuna() < alquiler) {
                    System.out.println("No tienes suficiente dinero para pagar el alquiler. Debes hipotecar propiedades o declararte en bancarrota.");
                    return false; // Jugador no es solvente
                } else {
                    // Pagar el alquiler
                    actual.sumarGastos(alquiler);
                    duenho.sumarFortuna(alquiler);
                    System.out.println("Has pagado " + alquiler + " de alquiler a " + duenho.getNombre() + ".");
                }
            }
        } 
        // Si la casilla es Parking
        else if (tipoCasilla.equals("Parking")) {
            float bote = casillaActual.getValor(); // Obtener el bote
            System.out.println("Has caído en 'Parking'. Recibes " + bote + ".");
            actual.sumarFortuna(bote);
        } else if (tipoCasilla.equals("Sorte") || tipoCasilla.equals("Comunidad")){
            System.out.println("El jugador " + actual.getNombre() + " ha caido ");
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
            """.formatted(this.tipo, this.grupo, this.duenho, this.valor, this.impuesto, 0.6f*this.valor, 0.6f*this.valor, 0.4f*this.valor, 1.25f*this.valor, 5*this.impuesto, 15*this.impuesto, 35*this.impuesto, 50*this.impuesto, 70*this.impuesto, 25*this.impuesto, 25*this.impuesto));
        }
        if(this.tipo.equals("Impuesto")){
            return ("""
            {
                tipo: %s,
                a pagar: %f
            }
            """.formatted(this.tipo, this.impuesto));
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
        return("La casilla " + this.nome + " no existe");
    }

    /* Método para mostrar información de una casilla en venta.
     * Valor devuelto: texto con esa información.
     */
    public String casEnVenta() {
        if (this.tipo.equals("Solar")){
            return ("""
            {
                tipo: %s,
                grupo: %s,
                valor: %f
            }
            """.formatted(this.tipo, this.grupo.getColorGrupo(), this.valor));
        }
        else if (this.tipo.equals("Transporte") ||this.tipo.equals("Servicios")){
            return("""
            {
                tipo: %s,
                valor: %f
            }
            """.formatted(this.tipo, this.valor));
        }
        return("La casilla " + this.nome + " no se puede vender");
    }

    @Override
    public String toString(){
        return this.nome;
    }
}
