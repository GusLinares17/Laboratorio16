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
}
