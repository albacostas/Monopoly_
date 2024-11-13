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
