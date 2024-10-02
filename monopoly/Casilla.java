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
        this.avatares = new ArrayList<>();
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
    }

    /*Constructor utilizado para crear las otras casillas (Suerte, Caja de comunidad y Especiales):
    * Parámetros: nombre, tipo de la casilla (será uno de los que queda), posición en el tablero y dueño.
     */
    public Casilla(String nombre, String tipo, int posicion, Jugador duenho) {

        this.nome=nombre;
        this.tipo=tipo;
        this.posicion=posicion;
        this.duenho=duenho;
        this.nome = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.duenho = duenho;

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
        // if (this.tipo=="Solar"){
        //     if (this.duenho.equals(banca)){
        //         if (actual.getFortuna()>=this.valor){
        //             return true;
        //         }
        //         return true;
        //     }
        //     else{
        //         if (actual.getFortuna()>=this.impuesto){
        //             return true;
        //         }
        //     }
        // }  No sé si está bien, pero hay que eva(luar todos los tipos de casilla. 


        switch (this.getTipo()) {
            case "Solar":
                return manejarSolar(actual,casillaActual);
                break;
            case "Impuesto":
                return manejarImpuesto(actual, casillaActual, tirada);
                break;
            case "Transporte":
                return manejarTransporte(actual, casillaActual);
                break;
            case "Servicio":
                return manejarServicio (actual, casillaActual,tirada);
                break;
            case "Caja":
                return manejarComunidad(actual);
                break;
            case "Suerte":
                return manejarSuerte(actual);
                break;
            case "Especial":
                return manejarEspecial(actual, banca);
                break;
        
            default:
                System.out.println("Tipo de casilla no encontrado " +tipoCasilla);

                break;
        }

    }

    // private boolean manejarSolar(Jugador actual, Casilla casilla){
    //     if(casilla.getDuenho() == null){
    //         if(actual.getFortuna() >= casilla.getValor()){
    //             actual.sumarGastos(casilla.getValor());
    //             actual.anhadirPropiedad(casilla);
    //             casilla.setDuenho(actual);
    //             System.out.println(actual.getNombre() + "ha adquirido la propiedad: " + casilla.getNombre());
    //             return true;
    //         } else return false;
    //     } else{
    //         float alquier = casilla.getImpuesto();
    //         if(actual.getFortuna() >= alquier){
    //             actual.sumarGastos(alquier);
    //             casilla.getDuenho().sumarFortuna(alquier);
    //             System.out.println(actual.getNombre() + "ha pagado " + alquiler + "del alquiler a " + casilla.getDuenho().getNombre());
    //             return true;
    //         } else return false;
    //     }
    // }

    // private boolean manejarImpuesto (Jugador actual, Casilla casilla, int tirada){
        
    //     float impuesto = casilla.getValor();
    //     if(actual.getFortuna() >= impuesto){
    //         actual.sumarGastos(impuesto);
    //         System.out.println(actual.getNombre() + " ha pagado " + impuesto + " de impuesto en " + casilla.getNombre());
    //         return true;
    //     }else return false;
    // }

    // private boolean manejarTransporte(Jugador actual, Casilla casilla){
    //     float alquiler = casilla.getValor();
    //     Jugador duenho = casilla.getDuenho();
    //     if(duenho == null){
    //         System.out.println("La casilla en la que se encuentra " + actual.getNombre() + " No tiene dueñho");
    //         // Si queire comprarla
    //         return true;
    //     }else if (actual.equals(duenho)){
    //         System.out.println("El dueño de la casilla de transprote es " + actual.getNombre());
    //         return true;
    //     }else if(actual.getFortuna() >= alquiler){
    //         actual.sumarGastos(alquiler);
    //         System.out.println(actual.getNombre() + " ha pagado " + impuesto + " de impuesto en " + casilla.getNombre());
    //         return true;
    //     }else return false;
    // }

    // private boolean manejarServicio(Jugador actual, Casilla casilla, int tirada){
        
    // }


    /*Método usado para comprar una casilla determinada. Parámetros:
    * - Jugador que solicita la compra de la casilla.
    * - Banca del monopoly (es el dueño de las casillas no compradas aún).*/
    public void comprarCasilla(Jugador solicitante, Jugador banca) {
        //cambiar el dueño de la casilla (deja de ser de la banca y pasa a ser del solicitante)
        this.duenho=solicitante;
        solicitante.anhadirPropiedad(this);
        banca.eliminarPropiedad(this);
        solicitante.sumarGastos(this.valor);
        //le damos el dinero que pagamos a la banca?
        

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

        StringBuilder info = new StringBuilder("Casilla: ");

        info.append(nome != null ? nome : "no tiene dueño");
        info.append(", tipo: ");
        info.append(tipo != null ? tipo : "se desconoce el tipo");
        info.append(", valor: ");
        info.append(valor != 0 ? valor : "0");
        info.append(" , dueño: ");
        info.append(duenho != null ? duenho.getNombre() : " la Banca");
        info.append(" , avatares: ");
        
        if(avatares != null && !avatares.isEmpty()){
            for (Avatar avatar : avatares){
                if(avatar != null && avatar.getId() != null){
                    info.append(avatar.getId()).append(" ");
                }
            }
        }

        return info.toString().trim();
    }

    /* Método para mostrar información de una casilla en venta.
     * Valor devuelto: texto con esa información.
     */
    public String casaEnVenta() {

        // Verificamos que la casilla está en venta.
        boolean enVenta = (duenho == null || duenho.getNombre().equalsIgnoreCase("Banca"));
        if(!enVenta){
            return "Está casilla no está en venta";
        }
        // Concatenamos cadenas de texto
        StringBuilder info = new StringBuilder(" La casilla en venta: ");
        
        info.append("Nombre: ").append(nome != null ? nome : "Sin nombre asignado");
        info.append(", Tipo: ").append(tipo != null ? tipo : " desconocido");
        info.append(", Valor: ").append(valor != 0 ? valor : "0");
        info.append(", Dueño: ").append(duenho != null ? duenho.getNombre() : "Banca");
        return info.toString().trim();
    }

    public Object getPropiedades() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPropiedades'");
    }

    public void setPropiedades(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPropiedades'");
    }
}
