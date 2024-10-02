
package monopoly;

import partida.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


public class Tablero {
    //Atributos.
    private ArrayList<ArrayList<Casilla>> posiciones; //Posiciones del tablero: se define como un arraylist de arraylists de casillas (uno por cada lado del tablero).
    private HashMap<String, Grupo> grupos; //Grupos del tablero, almacenados como un HashMap con clave String (será el color del grupo).
    private Jugador banca; //Un jugador que será la banca.


    //Constructor: únicamente le pasamos el jugador banca (que se creará desde el menú).
    public Tablero(Jugador banca) { // Inicializa los campos de instancia de la clase Tablero.

        this.banca = banca; 
        this.posiciones = new ArrayList<>();
        this.grupos = new HashMap<>(); // Grupos es un mapa que asocia claves a valores.
        this.generarCasillas(); // Llamamos a un método para configurar el tablero.
    }

    //Generamos los getters y setters para los atributos
    public ArrayList<ArrayList<Casilla>> getPosiciones() {
        return posiciones;
    }
    public void setPosiciones(ArrayList<ArrayList<Casilla>> posiciones) {
        this.posiciones = posiciones;
    }

    public HashMap<String, Grupo> getGrupos() {
        return grupos;
    }
    public void setGrupos(HashMap<String, Grupo> grupos) {
        this.grupos = grupos;
    }

    public Jugador getBanca() {
        return banca;
    }
    public void setBanca(Jugador banca) {
        this.banca = banca;
    }

    
    //Método para crear todas las casillas del tablero. Formado a su vez por cuatro métodos (1/lado).
    private void generarCasillas() {
        this.insertarLadoSur();
        this.insertarLadoOeste();
        this.insertarLadoNorte();
        this.insertarLadoEste();
    }
    

    //Método para insertar las casillas del lado norte.
    private void insertarLadoSur() {
        ArrayList<Casilla> ladoSur = new ArrayList<>();

        ladoSur.add(new Casilla("Salida", "Especial", 1, banca));
        ladoSur.add(new Casilla("Solar1", "Solar", 2, 600000, banca));  // Posición 2: Solar
        ladoSur.add(new Casilla("Caja", "Comunidad", 3, banca));             // Posición 3: Comunidad
        ladoSur.add(new Casilla("Solar2", "Solar", 4, 600000, banca));     // Posición 4: Solar
        ladoSur.add(new Casilla("Impuesto1","Impuesto", 5, Valor.SUMA_VUELTA/2, banca)); // Posición 5: Impuestos
        ladoSur.add(new Casilla("Trans1", "Transporte", 6, Valor.SUMA_VUELTA , banca)); // Posición 6: Transporte
        ladoSur.add(new Casilla("Solar3","Solar",7, 1560000, banca));
        ladoSur.add(new Casilla("Suerte","Suerte",8, banca));
        ladoSur.add(new Casilla("Solar4","Solar",9,1560000, banca));
        ladoSur.add(new Casilla("Solar5","Solar",10,1560000, banca));
        ladoSur.add(new Casilla("Carcel","Especial",11, 325332.14f, banca));

        posiciones.add(ladoSur);
    }      

    //Método para insertar las casillas del lado sur.
    private void insertarLadoOeste() {
        ArrayList<Casilla> ladoEste = new ArrayList<>();

        ladoEste.add(new Casilla("Solar6", "Solar",12,2028000,banca));
        ladoEste.add(new Casilla("Serv1", "Servicio", 13, banca));
        ladoEste.add(new Casilla("Solar7", "Solar",14,2028000,banca));
        ladoEste.add(new Casilla("Solar8", "Solar",15,2028000,banca));
        ladoEste.add(new Casilla("Trans2", "Transporte", 16, Valor.SUMA_VUELTA, banca));
        ladoEste.add(new Casilla("Solar9", "Solar", 17, 1485172, banca));
        ladoEste.add(new Casilla("Caja", "Comunidad", 18, banca));
        ladoEste.add(new Casilla("Solar10", "Solar", 19, 1485172, banca));
        ladoEste.add(new Casilla("Solar11", "Solar", 20, 1485172, banca));

        posiciones.add(ladoEste);

    }

    //Método que inserta casillas del lado oeste.
    private void insertarLadoNorte() {
        ArrayList<Casilla> ladoNorte = new ArrayList<>();

        ladoNorte.add(new Casilla("Parking","Especial",21, banca));
        ladoNorte.add(new Casilla("Solar12", "Solar",22,1142440,banca));
        ladoNorte.add(new Casilla("Suerte", "Suerte",23,banca));
        ladoNorte.add(new Casilla("Solar13", "Solar",24,1142440,banca));
        ladoNorte.add(new Casilla("Solar14", "Solar",25,1142440,banca));
        ladoNorte.add(new Casilla("Trans3", "Transporte", 26, Valor.SUMA_VUELTA, banca));
        ladoNorte.add(new Casilla("Solar15", "Solar", 27, 1485172, banca));
        ladoNorte.add(new Casilla("Solar16", "Solar", 28, 1485172, banca));
        ladoNorte.add(new Casilla("Serv2", "Servicio", 29, banca));
        ladoNorte.add(new Casilla("Solar17", "Solar", 30, 1485172, banca));
        ladoNorte.add(new Casilla("Ir a Carcel","Especial",31, banca));

        posiciones.add(ladoNorte);

    }

    //Método que inserta las casillas del lado este.
    private void insertarLadoEste() {
        ArrayList<Casilla> ladoEste = new ArrayList<>();

        ladoEste.add(new Casilla("Solar18", "Solar",32,1142440,banca));
        ladoEste.add(new Casilla("Solar19", "Solar",33,banca));
        ladoEste.add(new Casilla("Caja", "Comunidad",33,1142440,banca));
        ladoEste.add(new Casilla("Solar20", "Solar",35,1142440,banca));
        ladoEste.add(new Casilla("Trans4", "Transporte", 36, Valor.SUMA_VUELTA, banca));
        ladoEste.add(new Casilla("Suerte", "Suerte", 37, 1485172, banca));
        ladoEste.add(new Casilla("Solar21", "Solar", 38, 1485172, banca));
        ladoEste.add(new Casilla("Imp2", "Impuesto", 39,Valor.SUMA_VUELTA, banca));
        ladoEste.add(new Casilla("Solar22", "Solar", 40, 1485172, banca));

        posiciones.add(ladoEste);
    }

    //Para imprimir el tablero, modificamos el método toString().


    @Override

    public String toString() {
        StringBuilder sb = new StringBuilder();

        ArrayList<Casilla> ladoSur = posiciones.get(0);  // El primer lado es el sur
        ArrayList<Casilla> ladoOeste = posiciones.get(1);  // El segundo lado es el oeste
        ArrayList<Casilla> ladoNorte = posiciones.get(2);  // El tercer lado es el norte
        ArrayList<Casilla> ladoEste = posiciones.get(3);  // El cuarto lado es el este


        // Imprimir la fila superior (Norte)
        sb.append("+").append("_".repeat(10)).append("+\n");
        for(int n=ladoNorte.size()-1; n>=0; n--){
            sb.append("| ").append(formatCasilla(ladoNorte.get(n)));
        }

        /*for (Casilla casilla : ladoNorte) {
            sb.append("| ").append(formatCasilla(casilla));
        }*/

        sb.append("|\n");

        // Imprimir los lados izquierdo (Oeste) y derecho (Este) con espacios en el medio
        for (int i = 0; i < ladoOeste.size(); i++) {
            sb.append("| ").append(formatCasilla(ladoOeste.get(i))).append("|"); // Lado oeste  este bien

            for (int j = 0; j < ladoNorte.size()+2; j++) { // Espacios en el medio
                sb.append("        ");
            }
            sb.append("   | ").append(formatCasilla(ladoEste.get(i))); // Lado este
            sb.append(" |\n");
        }

        // Imprimir la fila inferior (Sur)
        for (Casilla casilla : ladoSur) {
            sb.append("| ").append(formatCasilla(casilla));
        }
        sb.append(" |\n"); // no se toca


        return sb.toString();
    }


    private String formatCasilla(Casilla casilla) {
        // Ajustar el formato para que cada casilla tenga el mismo ancho
        String nombre = casilla.getNombre();
        return String.format("%-10s", nombre);  // Espacio fijo para cada casilla
    }

    //Método usado para buscar la casilla con el nombre pasado como argumento:
    public Casilla encontrar_casilla(String nombre){
        for (ArrayList<Casilla> lado : this.posiciones){
            for (Casilla i : lado){
                if (i.getNombre().equals(nombre)){
                    return i;
                }
            }
        }
        System.out.println("No se ha encontrado la casilla");
        return new Casilla();
    }}
