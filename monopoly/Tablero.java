
package monopoly;

import partida.*;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


public class Tablero {
    //Atributos.
    private ArrayList<ArrayList<Casilla>> posiciones; //Posiciones del tablero: se define como un arraylist de arraylists de casillas (uno por cada lado del tablero).
    private HashMap<String, Grupo> grupos; //Grupos del tablero, almacenados como un HashMap con clave String (será el color del grupo).
    private Jugador banca; //Un jugador que será la banca.

    private ArrayList<Casilla> casillas;

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
        
        Casilla Salida = new Casilla("Salida", "Especial", 1, banca);
        Casilla Solar1 = new Casilla("Solar1", "Solar", 2, 600000, banca);
        Casilla Caja = new Casilla("Caja", "Comunidad", 3, banca);
        Casilla Solar2 = new Casilla("Solar2", "Solar", 4, 600000, banca);
        Casilla Impuesto1 = new Casilla("Impuesto1", 5, Valor.SUMA_VUELTA/2, banca);
        Casilla Trans1 = new Casilla("Trans1", "Transporte", 6, Valor.SUMA_VUELTA , banca);
        Casilla Solar3 = new Casilla("Solar3","Solar",7, 520000, banca);
        Casilla Suerte = new Casilla("Suerte","Suerte",8, banca);
        Casilla Solar4 =new Casilla("Solar4","Solar",9,520000, banca);
        Casilla Solar5 = new Casilla("Solar5","Solar",10,520000, banca);
        Casilla Carcel = new Casilla("Cárcel","Especial",11, 1/4*Valor.SUMA_VUELTA, banca);

        Grupo cyan = new Grupo(Solar5, Solar4, Solar3, "Cyan");
        this.grupos.put("Cyan", cyan);
        Solar5.setGrupo(cyan);
        Solar4.setGrupo(cyan);
        Solar3.setGrupo(cyan);

        Grupo black = new Grupo(Solar2, Solar1, "Black");
        this.grupos.put("Black", black);
        Solar2.setGrupo(black);
        Solar1.setGrupo(black);

        ladoSur.add(Salida);
        ladoSur.add(Solar1);  // Posición 2: Solar
        ladoSur.add(Caja);             // Posición 3: Comunidad
        ladoSur.add(Solar2);     // Posición 4: Solar
        ladoSur.add(Impuesto1); // Posición 5: Impuestos
        ladoSur.add(Trans1); // Posición 6: Transporte
        ladoSur.add(Solar3);
        ladoSur.add(Suerte);
        ladoSur.add(Solar4);
        ladoSur.add(Solar5);
        ladoSur.add(Carcel);

        posiciones.add(ladoSur);

    }      

    //Método para insertar las casillas del lado sur.
    private void insertarLadoOeste() {
        ArrayList<Casilla> ladoEste = new ArrayList<>();

        Casilla Solar6 = new Casilla("Solar6", "Solar",12,676000,banca);
        Casilla Serv1 = new Casilla("Serv1", "Servicio", 13, 3/4*Valor.SUMA_VUELTA, banca);
        Casilla Solar7 = new Casilla("Solar7", "Solar",14,676000,banca);
        Casilla Solar8 = new Casilla("Solar8", "Solar",15,676000,banca);
        Casilla Trans2 = new Casilla("Trans2", "Transporte", 16, Valor.SUMA_VUELTA, banca);
        Casilla Solar9 = new Casilla("Solar9", "Solar", 17, 878800, banca);
        Casilla Caja = new Casilla("Caja", "Comunidad", 18, banca);
        Casilla Solar10 = new Casilla("Solar10", "Solar", 19, 878800, banca);
        Casilla Solar11 = new Casilla("Solar11", "Solar", 20, 878800, banca);
        
        Grupo yellow = new Grupo(Solar9, Solar10, Solar11, "Yellow");
        this.grupos.put("Yellow", yellow);
        Solar9.setGrupo(yellow);
        Solar10.setGrupo(yellow);
        Solar11.setGrupo(yellow);

        Grupo purple = new Grupo(Solar8, Solar7, Solar6, "Purple");
        this.grupos.put("Purple", purple);
        Solar8.setGrupo(purple);
        Solar7.setGrupo(purple);
        Solar6.setGrupo(purple);

        ladoEste.add(Solar6);
        ladoEste.add(Serv1);
        ladoEste.add(Solar7);
        ladoEste.add(Solar8);
        ladoEste.add(Trans2);
        ladoEste.add(Solar9);
        ladoEste.add(Caja);
        ladoEste.add(Solar10);
        ladoEste.add(Solar11);

        posiciones.add(ladoEste);

    }

    //Método que inserta casillas del lado oeste.
    private void insertarLadoNorte() {
        ArrayList<Casilla> ladoNorte = new ArrayList<>();

        Casilla Parking = new Casilla("Parking","Especial",21, banca);
        Casilla Solar12 = new Casilla("Solar12", "Solar",22,1142440,banca);
        Casilla Suerte = new Casilla("Suerte", "Suerte",23,banca);
        Casilla Solar13 = new Casilla("Solar13", "Solar",24,1142440,banca);
        Casilla Solar14 = new Casilla("Solar14", "Solar",25,1142440,banca);
        Casilla Trans3 = new Casilla("Trans3", "Transporte", 26, Valor.SUMA_VUELTA, banca);
        Casilla Solar15 = new Casilla("Solar15", "Solar", 27, 1485172, banca);
        Casilla Solar16 = new Casilla("Solar16", "Solar", 28, 1485172, banca);
        Casilla Serv2 = new Casilla("Serv2", "Servicio", 29, 3/4*Valor.SUMA_VUELTA, banca);
        Casilla Solar17 = new Casilla("Solar17", "Solar", 30, 1485172, banca);
        Casilla IrCarcel = new Casilla("Ir Cárcel","Especial",31, banca);
        
        Grupo red = new Grupo(Solar12, Solar13, Solar14, "Red");
        this.grupos.put("Red", red);
        Solar12.setGrupo(red);
        Solar13.setGrupo(red);
        Solar14.setGrupo(red);

        Grupo brown = new Grupo(Solar15, Solar16, Solar17, "Brown");
        this.grupos.put("Brown", brown);
        Solar15.setGrupo(brown);
        Solar16.setGrupo(brown);
        Solar17.setGrupo(brown);

        ladoNorte.add(Parking);
        ladoNorte.add(Solar12);
        ladoNorte.add(Suerte);
        ladoNorte.add(Solar13);
        ladoNorte.add(Solar14);
        ladoNorte.add(Trans3);
        ladoNorte.add(Solar15);
        ladoNorte.add(Solar16);
        ladoNorte.add(Serv2);
        ladoNorte.add(Solar17);
        ladoNorte.add(IrCarcel);

        posiciones.add(ladoNorte);

    }

    //Método que inserta las casillas del lado este.
    private void insertarLadoEste() {
        ArrayList<Casilla> ladoEste = new ArrayList<>();

        Casilla Solar18 = new Casilla("Solar18", "Solar",32,1930723.6f,banca);
        Casilla Solar19 = new Casilla("Solar19", "Solar",33, 1930723.6f,banca);
        Casilla Caja = new Casilla("Caja", "Comunidad",33,banca);
        Casilla Solar20 = new Casilla("Solar20", "Solar",35,1930723.6f,banca);
        Casilla Trans4 = new Casilla("Trans4", "Transporte", 36, Valor.SUMA_VUELTA, banca);
        Casilla Suerte = new Casilla("Suerte", "Suerte", 37, banca);
        Casilla Solar21 = new Casilla("Solar21", "Solar", 38, 3764911.02f, banca);
        Casilla Impuesto2 = new Casilla("Impuesto2", 39,Valor.SUMA_VUELTA, banca);
        Casilla Solar22 = new Casilla("Solar22", "Solar", 40, 3764911.02f, banca);

        Grupo green = new Grupo(Solar18, Solar19, Solar20, "Green");
        this.grupos.put("Green", green);
        Solar18.setGrupo(green);
        Solar19.setGrupo(green);
        Solar20.setGrupo(green);

        Grupo blue = new Grupo(Solar21, Solar22, "Blue");
        this.grupos.put("Blue", blue);
        Solar21.setGrupo(blue);
        Solar22.setGrupo(blue);

        ladoEste.add(Solar18);
        ladoEste.add(Solar19);
        ladoEste.add(Caja);
        ladoEste.add(Solar20);
        ladoEste.add(Trans4);
        ladoEste.add(Suerte);
        ladoEste.add(Solar21);
        ladoEste.add(Impuesto2);
        ladoEste.add(Solar22);

        posiciones.add(ladoEste);
    }


    private String formatCasilla(Casilla casilla) {
        // Ajustar el formato para que cada casilla tenga el mismo ancho
        String nombre = casilla.getNombre();
        return String.format("%-15s", nombre);  // Espacio fijo para cada casilla
    }

    private String colorCasillas(Casilla casilla){
        if (casilla.getGrupo() == null) {
            return formatCasilla(casilla);
        }

        String color_casilla = casilla.getGrupo().getColorGrupo();

        switch (color_casilla) {
            case "Black":
                return (Valor.BLACK + formatCasilla(casilla) + Valor.RESET);
            case "Red":
                return (Valor.RED + formatCasilla(casilla) + Valor.RESET);
            case "Green":
                return (Valor.GREEN + formatCasilla(casilla) + Valor.RESET);
            case "Yellow":
                return (Valor.YELLOW + formatCasilla(casilla) + Valor.RESET);
            case "Blue":
                return (Valor.BLUE + formatCasilla(casilla) + Valor.RESET);
            case "Purple":
                return (Valor.PURPLE + formatCasilla(casilla) + Valor.RESET);
            case "Cyan":
                return (Valor.CYAN + formatCasilla(casilla) + Valor.RESET);
            case "Brown":
                return (Valor.BROWN + formatCasilla(casilla) + Valor.RESET);
            default:
                return (formatCasilla(casilla));
        }
    }

    //Para imprimir el tablero, modificamos el método toString().
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        ArrayList<Casilla> ladoSur = posiciones.get(0);  // El primer lado es el sur
        ArrayList<Casilla> ladoOeste = posiciones.get(1);  // El segundo lado es el oeste
        ArrayList<Casilla> ladoNorte = posiciones.get(2);  // El tercer lado es el norte
        ArrayList<Casilla> ladoEste = posiciones.get(3);  // El cuarto lado es el este
        
        Collections.reverse(ladoSur);
        Collections.reverse(ladoOeste);

        String aux = new String();

        // Imprimir la fila superior (Norte)
        for (Casilla casilla : ladoNorte){
            sb.append(" ");
            for (int j = 0; j < formatCasilla(casilla).length(); j++) {
                sb.append("_");
            }
        }
        sb.append("\n");

        for (Casilla casilla : ladoNorte) {
            sb.append("|").append(colorCasillas(casilla));
        }
        sb.append("|\n");
        for (Casilla casilla : ladoNorte){
            sb.append("|");
            for (int j = 0; j < casilla.getAvatares().size(); j++) {    //Avatares
                aux += "&";
                aux += casilla.getAvatares().get(j).getId();
            }
            sb.append(String.format("%-15s", aux));
            aux = "";
        }
        sb.append("|\n");

        for (Casilla casilla : ladoNorte){
            sb.append("|");
            for (int j = 0; j < formatCasilla(casilla).length(); j++) {
                sb.append("_");
            }
        }
        sb.append("|\n");

        // Imprimir los lados izquierdo (Oeste) y derecho (Este) con espacios en el medio
        Casilla casilla = new Casilla();

        for (int i = 0; i < ladoOeste.size()-1; i++) {
            sb.append("|").append(colorCasillas(ladoOeste.get(i))).append("|");         //nombres casillas
            for(int l = 0; l < ladoNorte.size()-2; l++){
                for (int j = 0; j < formatCasilla(casilla).length()+1; j++) {
                    sb.append(" ");
                }
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("|").append(colorCasillas(ladoEste.get(i))).append("|\n");        //nombres casillas

            sb.append("|");
            for (int j = 0; j < casilla.getAvatares().size(); j++) {    //Avatares
                aux += "&";
                aux += casilla.getAvatares().get(j).getId();
            }
            sb.append(String.format("%-15s", aux));
            aux = "";
            sb.append("|");

            //Espacios
            for(int l = 0; l < ladoNorte.size()-2; l++){
                for (int j = 0; j < formatCasilla(casilla).length()+1; j++) {
                    sb.append(" ");
                }
            }
            sb.deleteCharAt(sb.length()-1);
            

            sb.append("|");
            for (int j = 0; j < casilla.getAvatares().size(); j++) {    //Avatares
                aux += "&";
                aux += casilla.getAvatares().get(j).getId();
            }
            sb.append(String.format("%-15s", aux));
            aux = "";
            sb.append("|\n");


            sb.append("|");
            for (int j = 0; j < formatCasilla(casilla).length(); j++) {
                sb.append("_");
            }
            sb.append("|");
            for(int l = 0; l < ladoNorte.size()-2; l++){
                for (int j = 0; j < formatCasilla(casilla).length()+1; j++) {
                    sb.append(" ");
                }
            }
            sb.deleteCharAt(sb.length()-1);

            

            sb.append("|");
            for (int j = 0; j < formatCasilla(casilla).length(); j++) {
                sb.append("_");
            }
            sb.append("|\n");
        }

        sb.append("|").append(colorCasillas(ladoOeste.get(ladoOeste.size()-1))).append("|"); //Imprime la última casilla del lado oeste conectándola con el lado sur
        for(int i = 0; i < ladoNorte.size()-2; i++){
            for (int j = 0; j < formatCasilla(casilla).length()+1; j++) {
                sb.append(" ");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("|").append(colorCasillas(ladoEste.get(ladoOeste.size()-1))).append("|\n"); //Imprime la última casilla del lado este conectándola con el lado sur

        sb.append("|");
        for (int j = 0; j < casilla.getAvatares().size(); j++) {    //Avatares
            aux += "&";
            aux += casilla.getAvatares().get(j).getId();
        }
        sb.append(String.format("%-15s", aux));
        aux = "";
        sb.append("|");
        for(int l = 0; l < ladoNorte.size()-2; l++){
            for (int j = 0; j < formatCasilla(casilla).length()+1; j++) {
                sb.append(" ");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("|");
        for (int j = 0; j < casilla.getAvatares().size(); j++) {    //Avatares
            aux += "&";
            aux += casilla.getAvatares().get(j).getId();
        }
        sb.append(String.format("%-15s", aux));
        aux = "";
        sb.append("|\n");
    
        //Imprime la fila superior del lado sur
        sb.append("|");
        for (int i = 0; i < formatCasilla(casilla).length(); i++) {         //Imprime la parte inferior de la última casilla del lado este
            sb.append("_");
        }
        sb.append("|");
        
        for(int i = 0; i < ladoSur.size()-2; i++){
            for (int j = 0; j < formatCasilla(casilla).length(); j++) {
                sb.append("_");
            }
            if (i<ladoSur.size()-3) {
                    sb.append(" ");
    
            }
        }
        sb.append("|");                                                     //Imprime la parte inferior de la última casilla del lado este
        for (int i = 0; i < formatCasilla(casilla).length(); i++) {
            sb.append("_");
        }
        sb.append("|");
        
        sb.append("\n");

        // Imprimir la fila inferior (Sur)
        for (Casilla casilla1 : ladoSur) {
            sb.append("|").append(colorCasillas(casilla1));
        }
        sb.append("|\n");
        for (Casilla casilla1 : ladoSur){
            sb.append("|");
            for (int j = 0; j < casilla1.getAvatares().size(); j++) {    //Avatares
                aux += "&";
                aux += casilla1.getAvatares().get(j).getId();
            }
            sb.append(String.format("%-15s", aux));
            aux = "";
        }
        sb.append("|\n");
        for(int i = 0; i < ladoSur.size(); i++){
            sb.append("|");
            for (int j = 0; j < formatCasilla(casilla).length(); j++) {
                sb.append("_");
            }
        }
        sb.append("|\n");

        Collections.reverse(ladoSur);
        Collections.reverse(ladoOeste);

        return sb.toString();
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
    }
}
