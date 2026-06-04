package vista;

import modelo.Tablero;

/**
 * Clase encargada de la representación visual del juego en la consola.
 * Su responsabilidad es leer el estado actual del Tablero y dibujarlo
 * para que el usuario pueda interactuar con él.
 */
public class ConsolaVista {
    
    /**
     * Dibuja el tablero en la consola fila por fila.
     * Utiliza caracteres especiales para representar los estados:
     * - " M ": Casilla marcada por el jugador.
     * - " [ ]": Casilla oculta (no revelada).
     * - " N ": Número de minas adyacentes (donde N es el conteo).
     * * @param t El objeto Tablero que contiene la información a mostrar.
     */
    public void mostrarTablero(Tablero t) {
        System.out.println("    1   2   3   4   5   6   7   8   9   10");
        for (int i = 0; i < 10; i++) {
            // Imprime la letra de la fila (A, B, C...)
            System.out.print((char)('A'+i) + " ");
            
            for (int j = 0; j < 10; j++) {
                if (t.getCasilla(i, j).estaMarcada()) {
                    System.out.print(" M ");
                } else if (!t.getCasilla(i, j).estaRevelada()) {
                    System.out.print(" [ ]");
                } else {
                    // Muestra el número de minas cercanas una vez revelada
                    System.out.print(" " + t.getCasilla(i, j).getMinasAdyacencias() + " ");
                }
            }
            System.out.println();
        }
    }
}