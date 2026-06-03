package principal;

import modelo.Tablero;
import vista.ConsolaVista;
import controlador.JuegoControl;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicialización del MVC
        Tablero modelo = new Tablero();
        ConsolaVista vista = new ConsolaVista();
        JuegoControl ctrl = new JuegoControl(modelo);
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== BIENVENIDO AL BUSCAMINAS POO ===");
        
        boolean juegoActivo = true;
        
        while (juegoActivo) {
            vista.mostrarTablero(modelo);
            
            // 1. Pedir acción al usuario
            System.out.println("\nAcciones: [R] Revelar | [M] Marcar mina | [S] Salir");
            System.out.print("Elige tu accion: ");
            String accion = sc.next().toUpperCase();
            
            if (accion.equals("S")) {
                juegoActivo = false;
                System.out.println("Juego finalizado por el usuario.");
                continue;
            }
            
            // 2. Pedir coordenada
            System.out.print("Ingresa coordenada (ej: A5): ");
            String coord = sc.next();
            
            // 3. Procesar en el controlador
            ctrl.realizarAccion(accion, coord);
            
            // 4. Verificar fin del juego
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
        sc.close();
    }
}