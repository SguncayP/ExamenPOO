package principal;

import modelo.Tablero;
import modelo.GestorArchivos;
import vista.ConsolaVista;
import controlador.JuegoControl;
import java.util.Scanner;

/**
 * Clase de arranque del Buscaminas.
 * Aquí instanciamos el modelo, la vista y el controlador (MVC).
 * Mantiene el bucle principal del juego hasta que el usuario gana, pierde o sale.
 */
public class Main {
    public static void main(String[] args) {
        // Inicializamos los componentes del patrón MVC
        Tablero modelo = new Tablero();
        ConsolaVista vista = new ConsolaVista();
        JuegoControl ctrl = new JuegoControl(modelo);
        Scanner sc = new Scanner(System.in);
        
        System.out.println("=== BUSCAMINAS POO - EXAMEN FINAL ===");
        boolean juegoActivo = true;
        
        // Ciclo principal: sigue pidiendo acciones mientras no se acabe el juego
        while (juegoActivo) {
            vista.mostrarTablero(modelo);
            System.out.println("\nAcciones: [R] Revelar | [M] Marcar | [G] Guardar | [C] Cargar | [S] Salir");
            System.out.print("Elige tu accion: ");
            String accion = sc.next().toUpperCase();
            
            if (accion.equals("S")) {
                juegoActivo = false;
            } else if (accion.equals("G")) {
                // Guardamos el modelo actual usando el Gestor de Archivos
                GestorArchivos.guardarJuego(modelo, "partida.dat");
            } else if (accion.equals("C")) {
                // Si cargamos, tenemos que refrescar el controlador con el nuevo modelo
                Tablero cargado = GestorArchivos.cargarJuego("partida.dat");
                if (cargado != null) {
                    modelo = cargado;
                    ctrl = new JuegoControl(modelo); 
                }
            } else {
                // Flujo normal para revelar o marcar casillas
                System.out.print("Ingresa coordenada (ej: A5): ");
                String coord = sc.next();
                ctrl.realizarAccion(accion, coord);
                
                // Chequeamos al final de cada turno si el juego terminó
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