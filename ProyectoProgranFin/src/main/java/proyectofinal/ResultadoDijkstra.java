/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

import java.util.List;

/**
 *
 * @author PC-ELHID
 */
public class ResultadoDijkstra {
    
    private List<Nodo> recorrido;
    private double distanciaTotal;

    public ResultadoDijkstra(List<Nodo> recorrido, double distanciaTotal) {
        this.recorrido = recorrido;
        this.distanciaTotal = distanciaTotal;
    }

    public List<Nodo> getRecorrido() {
        return recorrido;
    }

    public double getDistanciaTotal() {
        return distanciaTotal;
    }
    
}
