/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

/**
 *
 * @author PC-ELHID
 */
public class Arista {
    
     private Nodo origen;
    private Nodo destino;
    private double peso;

    public Arista(Nodo origen, Nodo destino, double peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    /**
     * @return the origen
     */
    public Nodo getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(Nodo origen) {
        this.origen = origen;
    }

    /**
     * @return the destino
     */
    public Nodo getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(Nodo destino) {
        this.destino = destino;
    }

    /**
     * @return the peso
     */
    public double getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    
    
}
