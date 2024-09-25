package partida;

import java.util.Random;

public class Dado {
    //El dado solo tiene un atributo en nuestro caso: su valor.
    private int valor;

    public int getValor(){
        return valor;
    }

    public void setValor(int nValor){
        this.valor= nValor;
    }

    //Metodo para simular lanzamiento de un dado: devolverá un valor aleatorio entre 1 y 6.
    public int hacerTirada() {
        // Generamos un double ente 0 y 1, lo multiplicamos por 6
        Random ram = new Random();

        valor = ram.nextInt(6) +1;

        return valor;
    }
}
