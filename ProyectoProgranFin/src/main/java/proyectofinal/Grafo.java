/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC-ELHID
 */
public class Grafo {
    
    private List<Nodo> nodos;
    private List<Arista> aristas;

    public Grafo() {
        nodos = new ArrayList<>();
        aristas = new ArrayList<>();
    }

    public void agregarNodo(Nodo nodo) {
        nodos.add(nodo);
    }

    public void agregarArista(Nodo origen, Nodo destino, double peso) {
        Arista arista = new Arista(origen, destino, peso);
        aristas.add(arista);
    }

    public List<Nodo> obtenerNodos() {
        return nodos;
    }

    public List<Arista> obtenerAristasSalientes(Nodo nodo) {
        List<Arista> aristasSalientes = new ArrayList<>();
        for (Arista arista : aristas) {
            if (arista.getOrigen() == nodo) {
                aristasSalientes.add(arista);
            }
        }
        return aristasSalientes;
    }

    public Nodo obtenerNodo(String nombre) {
        for (Nodo nodo : nodos) {
            if (nodo.getNombre().equalsIgnoreCase(nombre)) {
                return nodo;
            }
        }
        return null;
    }
    
}
