package vista;

import modelo.Tablero;

public class ConsolaVista {
    public void mostrarTablero(Tablero t) {
        System.out.println("   1  2  3  4  5  6  7  8  9 10");
        for (int i = 0; i < 10; i++) {
            System.out.print((char)('A' + i) + " ");
            for (int j = 0; j < 10; j++) 
                System.out.print(t.getCasilla(i, j).estaRevelada() ? " " + t.getCasilla(i, j).getMinasAdyacencias() + " " : " [ ]");
            System.out.println();
        }
    }
    public void mostrarMensaje(String m) { System.out.println(m); }
}