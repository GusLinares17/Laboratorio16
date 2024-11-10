/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gustavo
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
public class Mapa {
    String tipoDeTerritorio;
    Ejercito[][] campoDeBatalla;
    String[] reinos = {"Francia", "Inglaterra", "Castilla-Aragon", "Moros", "Sacro Imperio Romano-Germanico"};
    String reino1;
    String reino2;  
    int vidaTotalReino1 = 0;
    int vidaTotalReino2 = 0;
    int cantidadEjercitosReino1 = 0;
    int cantidadEjercitosReino2 = 0;
    int cantidadSoldadosReino1 = 0;
    int cantidadSoldadosReino2 = 0;
    public Mapa(String tipoDeTerritorio) {
        this.tipoDeTerritorio = tipoDeTerritorio;
        this.campoDeBatalla = new Ejercito[10][10];
    }
    private void seleccionarReinos() {
        Random random = new Random();
        reino1 = reinos[random.nextInt(reinos.length)];
        do {
            reino2 = reinos[random.nextInt(reinos.length)];
        } while (reino1.equals(reino2));
    }
    private void generarEjercitos() {
        Random random = new Random();
        HashMap<String, Integer> ejercitosPorReino = new HashMap<>();
        ejercitosPorReino.put(reino1, random.nextInt(10) + 1);
        ejercitosPorReino.put(reino2, random.nextInt(10) + 1);
        for (String reino : ejercitosPorReino.keySet()) {
            int cantidadEjercitos = ejercitosPorReino.get(reino);
            for (int i = 0; i < cantidadEjercitos; i++) {
                int x, y;
                do {
                    x = random.nextInt(10);
                    y = random.nextInt(10);
                } while (campoDeBatalla[x][y] != null);
                campoDeBatalla[x][y] = new Ejercito(reino);
            }
        }
    }
    private void aplicarBonificacionTerritorio() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (campoDeBatalla[i][j] != null && 
                   (campoDeBatalla[i][j].reino.equals(reino1) || campoDeBatalla[i][j].reino.equals(reino2))) {
                    campoDeBatalla[i][j].aplicarBonificacionTerritorio(tipoDeTerritorio);
                }
            }
        }
    }
    private void calcularMetricas() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (campoDeBatalla[i][j] != null) {
                    Ejercito ejercito = campoDeBatalla[i][j];
                    if (ejercito.reino.equals(reino1)) {
                        vidaTotalReino1 += ejercito.getVidaTotal();
                        cantidadEjercitosReino1++;
                        cantidadSoldadosReino1 += ejercito.getNumeroSoldados();
                    } else if (ejercito.reino.equals(reino2)) {
                        vidaTotalReino2 += ejercito.getVidaTotal();
                        cantidadEjercitosReino2++;
                        cantidadSoldadosReino2 += ejercito.getNumeroSoldados();
                    }
                }
            }
        }
    }
    private void mostrarResultados() {
        double promedioVidaReino1 = cantidadSoldadosReino1 == 0 ? 0 : (double) vidaTotalReino1 / cantidadSoldadosReino1;
        double promedioVidaReino2 = cantidadSoldadosReino2 == 0 ? 0 : (double) vidaTotalReino2 / cantidadSoldadosReino2;

        System.out.println("Batalla entre: " + reino1 + " y " + reino2);
        System.out.println("Tipo de Territorio: " + tipoDeTerritorio);
        System.out.println();

        System.out.println("Metrica 1 - Mayor vida total:");
        System.out.println(reino1 + ": " + vidaTotalReino1);
        System.out.println(reino2 + ": " + vidaTotalReino2);
        System.out.println("Ganador: " + (vidaTotalReino1 > vidaTotalReino2 ? reino1 : reino2));
        System.out.println();

        System.out.println("Metrica 2 - Mayor cantidad de ejercitos:");
        System.out.println(reino1 + ": " + cantidadEjercitosReino1);
        System.out.println(reino2 + ": " + cantidadEjercitosReino2);
        System.out.println("Ganador: " + (cantidadEjercitosReino1 > cantidadEjercitosReino2 ? reino1 : reino2));
        System.out.println();

        System.out.println("Metrica 3 - Mejor promedio de vida por soldado:");
        System.out.println(reino1 + ": " + promedioVidaReino1);
        System.out.println(reino2 + ": " + promedioVidaReino2);
        System.out.println("Ganador: " + (promedioVidaReino1 > promedioVidaReino2 ? reino1 : reino2));
    }
}

