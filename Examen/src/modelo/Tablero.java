package modelo;
import java.util.Random;

public class Tablero {
    private final int TAMANO = 10, NUM_MINAS = 10;
    private Casilla[][] celdas = new Casilla[TAMANO][TAMANO];

    public Tablero() {
        for (int i = 0; i < TAMANO; i++)
            for (int j = 0; j < TAMANO; j++) celdas[i][j] = new Casilla();
        colocarMinas();
        calcularAdyacencias();
    }

    private void colocarMinas() {
        Random rand = new Random();
        for (int i = 0; i < NUM_MINAS; ) {
            int f = rand.nextInt(TAMANO), c = rand.nextInt(TAMANO);
            if (!celdas[f][c].esMina()) { celdas[f][c].setEsMina(true); i++; }
        }
    }

    private void calcularAdyacencias() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                if (!celdas[i][j].esMina()) {
                    int cuenta = 0;
                    for (int f = i-1; f <= i+1; f++)
                        for (int c = j-1; c <= j+1; c++)
                            if (f >= 0 && f < TAMANO && c >= 0 && c < TAMANO && celdas[f][c].esMina()) cuenta++;
                    celdas[i][j].setMinasAdyacencias(cuenta);
                }
            }
        }
    }

    public Casilla getCasilla(int f, int c) { return celdas[f][c]; }
    public boolean esDerrota() {
        for (int i=0; i<TAMANO; i++) for (int j=0; j<TAMANO; j++)
            if (celdas[i][j].estaRevelada() && celdas[i][j].esMina()) return true;
        return false;
    }
    public boolean esVictoria() {
        int reveladas = 0;
        for (int i=0; i<TAMANO; i++) for (int j=0; j<TAMANO; j++)
            if (!celdas[i][j].esMina() && celdas[i][j].estaRevelada()) reveladas++;
        return reveladas == (TAMANO * TAMANO - NUM_MINAS);
    }
}