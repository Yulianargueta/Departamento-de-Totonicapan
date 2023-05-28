/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author PC-ELHID
 */
public class ProyectoFinal {
    
    public static void main(String[] args) {
        // Crear grafo de ejemplo
        Grafo grafo = new Grafo();

        // Crear nodos
        Nodo nodoA = new Nodo("Totonicapan Cabecera");
        Nodo nodoB = new Nodo("San Cristobal Totonicapan");
        Nodo nodoC = new Nodo("San Francisco el Alto");
        Nodo nodoD = new Nodo("San Adres Xecul");
        Nodo nodoE = new Nodo("Momostenango");
        Nodo nodoF = new Nodo("Santa Maria Chuiquimula");
        Nodo nodoG = new Nodo("Santa Lucia La Reforma");
        Nodo nodoH = new Nodo("San Bartolo");
        

        // Agregar nodos al grafo
        grafo.agregarNodo(nodoA);
        grafo.agregarNodo(nodoB);
        grafo.agregarNodo(nodoC);
        grafo.agregarNodo(nodoD);
        grafo.agregarNodo(nodoE);
        grafo.agregarNodo(nodoF);
        grafo.agregarNodo(nodoG);
        grafo.agregarNodo(nodoH);
        

        // Agregar aristas con pesos al grafo
        grafo.agregarArista(nodoA, nodoB, 13);
        grafo.agregarArista(nodoA, nodoC, 13);
        grafo.agregarArista(nodoA, nodoD, 16);
        grafo.agregarArista(nodoA, nodoE, 24);
        grafo.agregarArista(nodoA, nodoF, 17);
        grafo.agregarArista(nodoA, nodoG, 40);
        grafo.agregarArista(nodoA, nodoH, 37);
        grafo.agregarArista(nodoB, nodoG, 42);
        grafo.agregarArista(nodoB, nodoH, 25);
        grafo.agregarArista(nodoC, nodoB, 4.3);
        grafo.agregarArista(nodoD, nodoE, 26);
        grafo.agregarArista(nodoE, nodoF, 14);
        grafo.agregarArista(nodoF, nodoG, 28);
        grafo.agregarArista(nodoG, nodoH, 28);
        
        
         Scanner scanner = new Scanner(System.in);
        String opcion = "";

        do {
            // Obtener el nodo de origen y destino desde el usuario
            System.out.print("Ingrese el Municipio del que partira: ");
            String origen = scanner.nextLine();

            if (origen.equals("0")) {
                break;
            }

            System.out.print("Ingrese el municipio destino: ");
            String destino = scanner.nextLine();

            Nodo nodoOrigen = grafo.obtenerNodo(origen);
            Nodo nodoDestino = grafo.obtenerNodo(destino);

            if (nodoOrigen == null || nodoDestino == null) {
                System.out.println("La informacion es incorrect");
                continue;
            }

            // Ejecutar algoritmo de Dijkstra y obtener el resultado
            ResultadoDijkstra resultado = dijkstra(grafo, nodoOrigen, nodoDestino);

            if (resultado == null) {
                System.out.println("No se encontró un recorrido desde " + nodoOrigen.getNombre() + " hasta " + nodoDestino.getNombre());
            } else {
                // Imprimir el recorrido más corto
                System.out.println("La ruta mas corta es desde " + nodoOrigen.getNombre() + " hasta " + nodoDestino.getNombre() + " es:");
                List<Nodo> recorrido = resultado.getRecorrido();
                for (int i = 0; i < recorrido.size(); i++) {
                    Nodo nodo = recorrido.get(i);
                    System.out.print(nodo.getNombre());
                    if (i < recorrido.size() - 1) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println();

                // Imprimir la distancia total
                System.out.println("Nuestra distancia Total: " + resultado.getDistanciaTotal());
            }

            System.out.print("¿Desea ingresar otro municipio y su destino? si o no: ");
            opcion = scanner.nextLine();
        } while (!opcion.equalsIgnoreCase("n"));
    }

    public static ResultadoDijkstra dijkstra(Grafo grafo, Nodo nodoInicial, Nodo nodoDestino) {
        Map<Nodo, Double> distancias = new HashMap<>();
        Map<Nodo, Nodo> predecesores = new HashMap<>();

        // Inicializar todas las distancias como infinito excepto el nodo inicial
        for (Nodo nodo : grafo.obtenerNodos()) {
            if (nodo.equals(nodoInicial)) {
                distancias.put(nodo, 0.0);
            } else {
                distancias.put(nodo, Double.POSITIVE_INFINITY);
            }
        }

        Set<Nodo> nodosVisitados = new HashSet<>();

        while (!nodosVisitados.contains(nodoDestino)) {
            Nodo nodoActual = obtenerNodoConMenorDistancia(distancias, nodosVisitados);

            if (nodoActual == null) {
                // No se encontró un recorrido válido
                return null;
            }

            nodosVisitados.add(nodoActual);

            // Obtener los vecinos del nodo actual
            List<Arista> aristas = grafo.obtenerAristasSalientes(nodoActual);

            // Calcular las distancias mínimas a los vecinos del nodo actual
            for (Arista arista : aristas) {
                Nodo vecino = arista.getDestino();
                double distanciaDesdeOrigen = distancias.get(nodoActual) + arista.getPeso();
                if (distanciaDesdeOrigen < distancias.get(vecino)) {
                    distancias.put(vecino, distanciaDesdeOrigen);
                    predecesores.put(vecino, nodoActual);
                }
            }
        }

        // Construir el recorrido más corto desde el nodo inicial hasta el nodo destino
        List<Nodo> recorrido = construirRecorrido(nodoInicial, nodoDestino, predecesores);

        // Obtener la distancia total
        double distanciaTotal = distancias.get(nodoDestino);

        return new ResultadoDijkstra(recorrido, distanciaTotal);
    }

    public static Nodo obtenerNodoConMenorDistancia(Map<Nodo, Double> distancias, Set<Nodo> nodosVisitados) {
        Nodo nodoConMenorDistancia = null;
        double menorDistancia = Double.POSITIVE_INFINITY;

        for (Map.Entry<Nodo, Double> entry : distancias.entrySet()) {
            Nodo nodo = entry.getKey();
            double distancia = entry.getValue();

            if (!nodosVisitados.contains(nodo) && distancia < menorDistancia) {
                nodoConMenorDistancia = nodo;
                menorDistancia = distancia;
            }
        }

        return nodoConMenorDistancia;
    }

    public static List<Nodo> construirRecorrido(Nodo nodoInicial, Nodo nodoDestino, Map<Nodo, Nodo> predecesores) {
        List<Nodo> recorrido = new ArrayList<>();
        Nodo nodoActual = nodoDestino;

        while (nodoActual != null) {
            recorrido.add(0, nodoActual);
            nodoActual = predecesores.get(nodoActual);
        }

        if (!recorrido.get(0).equals(nodoInicial)) {
            return Collections.emptyList(); // No se encontró un recorrido válido
        }

        return recorrido;
    }
    
}
