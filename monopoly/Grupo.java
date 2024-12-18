package monopoly;

import partida.*;
import java.util.ArrayList;


class Grupo {

    //Atributos
    private ArrayList<Casilla> miembros; //Casillas miembros del grupo.
    private String colorGrupo; //Color del grupo
    private int numCasillas; //Número de casillas del grupo.

    private int numCasasGrupo=0;
    private int numHotelesGrupo=0;
    private int numPiscinasGrupo=0;
    private int numPistasGrupo=0;

    private float totalAlquilerRecaudado;
    //Constructor vacío.
    public Grupo() {
        this.miembros = new ArrayList<>();
        this.totalAlquilerRecaudado = 0;
    }

    public float getTotalAlquilerRecaudado() {
        return totalAlquilerRecaudado;
    }

    public void agregarAlquiler(float cantidad){
        this.totalAlquilerRecaudado += cantidad;
    }

    /*Constructor para cuando el grupo está formado por DOS CASILLAS:
    * Requiere como parámetros las dos casillas miembro y el color del grupo.
     */
    public Grupo(Casilla cas1, Casilla cas2, String colorGrupo) {
        this.miembros = new ArrayList<>();
        this.miembros.add(cas1);
        this.miembros.add(cas2);
        this.colorGrupo = colorGrupo;
        this.numCasillas = 2;
    }


    /*Constructor para cuando el grupo está formado por TRES CASILLAS:
    * Requiere como parámetros las tres casillas miembro y el color del grupo.
     */
    public Grupo(Casilla cas1, Casilla cas2, Casilla cas3, String colorGrupo) {
        this.miembros = new ArrayList<>();
        this.miembros.add(cas1);
        this.miembros.add(cas2);
        this.miembros.add(cas3);
        this.colorGrupo = colorGrupo;
        this.numCasillas = 3;
    }


    public ArrayList<Casilla> getMiembros(){
        return miembros;
    }
    public void setMiembros(ArrayList<Casilla> miembros){
        this.miembros = miembros;
    }

    public String getColorGrupo() {
        return colorGrupo;
    }
    public void setColorGrupo(String colorGrupo) {
        this.colorGrupo = colorGrupo;
    }

    public int getNumCasasGrupo() {
        return numCasasGrupo;
    }
    public void setNumCasasGrupo(int numCasasGrupo){
        this.numCasasGrupo = numCasasGrupo;
    }
    public int getNumHotelesGrupo() {
        return numHotelesGrupo;
    }
    public void setNumHotelesGrupo(int numHotelesGrupo){
        this.numHotelesGrupo = numHotelesGrupo;
    }

    public int getNumPiscinasGrupo() {
        return  numPiscinasGrupo;
    }
    public void setNumPiscinasGrupo(int numPiscinasGrupo){
        this.numPiscinasGrupo = numPiscinasGrupo;
    }

    public int getNumPistasGrupo() {
        return numPistasGrupo;
    }
    public void setNumPistasGrupo(int numPistasGrupo){
        this.numPistasGrupo = numPistasGrupo;
    }

    public int getNumCasillas() {
        return numCasillas;
    }
    public void setNumCasillas(int numCasillas){
        this.numCasillas = numCasillas;
    }

    /* Método que añade una casilla al array de casillas miembro de un grupo.
     * Parámetro: casilla que se quiere añadir.
     */
    public void anhadirCasilla(Casilla miembro) {
        this.miembros.add(miembro);
    }

    /*Método que comprueba si el jugador pasado tiene en su haber todas las casillas del grupo:
    * Parámetro: jugador que se quiere evaluar.
    * Valor devuelto: true si es dueño de todas las casillas del grupo, false en otro caso.
     */
    public boolean esDuenhoGrupo(Jugador jugador) {
        boolean bool = true;
        for (Casilla casilla : this.miembros) {
            if (!casilla.getDuenho().equals(jugador)) {
                bool = false;
            }
        }
        
        return bool;
    }

    public String getNombre(){
        return colorGrupo;
    }
}


