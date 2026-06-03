package principal;

import modelo.Tablero;
import modelo.GestorArchivos;
import vista.ConsolaVista;
import controlador.JuegoControl;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tablero modelo = new Tablero();
        ConsolaVista vista = new ConsolaVista();
        JuegoControl ctrl = new JuegoControl(modelo);
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== BUSCAMINAS POO - EXAMEN FINAL ===");
        boolean juegoActivo = true;
        
        while (juegoActivo) {
            vista.mostrarTablero(modelo);
            System.out.println("\nAcciones: [R] Revelar | [M] Marcar | [G] Guardar | [C] Cargar | [S] Salir");
            System.out.print("Elige tu accion: ");
            String accion = sc.next().toUpperCase();
            
            if (accion.equals("S")) {
                juegoActivo = false;
            } else if (accion.equals("G")) {
                GestorArchivos.guardarJuego(modelo, "partida.dat");
            } else if (accion.equals("C")) {
                Tablero cargado = GestorArchivos.cargarJuego("partida.dat");
                if (cargado != null) {
                    modelo = cargado;
                    ctrl = new JuegoControl(modelo); // Actualizamos el controlador con el nuevo modelo
                }
            } else {
                System.out.print("Ingresa coordenada (ej: A5): ");
                String coord = sc.next();
                ctrl.realizarAccion(accion, coord);
                
                // Verificación de fin de juego
                if (modelo.esDerrota()) {
                    vista.mostrarTablero(modelo);
                    System.out.println("¡BOOM! Has pisado una mina. GAME OVER.");
                    juegoActivo = false;
                } else if (modelo.esVictoria()) {
                    vista.mostrarTablero(modelo);
                    System.out.println("¡FELICIDADES! Has despejado todo el tablero.");
                    juegoActivo = false;
                }
            }
        }
        sc.close();
        System.out.println("Juego finalizado.");
    }
}