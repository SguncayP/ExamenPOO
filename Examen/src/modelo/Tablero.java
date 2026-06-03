package modelo;
import java.util.Random;

import java.io.Serializable;
import java.util.Random;
import excepciones.CasillaYaDescubiertaException;

public class Tablero implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int TAM = 10;
    private final int MINAS = 10;
    private final Casilla[][] celdas = new Casilla[TAM][TAM];

    public Tablero() {
        inicializarTablero();
        colocarMinas();
        calcularAdyacencias();
    }

    private void inicializarTablero() {
        for (int i = 0; i < TAM; i++)
            for (int j = 0; j < TAM; j++) celdas[i][j] = new Casilla();
    }

    private void colocarMinas() {
        Random r = new Random();
        for (int i = 0; i < MINAS; ) {
            int f = r.nextInt(TAM), c = r.nextInt(TAM);
            if (!celdas[f][c].esMina()) {
                celdas[f][c].setEsMina(true);
                i++;
            }
        }
    }

    private void calcularAdyacencias() {
        for (int i = 0; i < TAM; i++)
            for (int j = 0; j < TAM; j++)
                if (!celdas[i][j].esMina())
                    celdas[i][j].setMinasAdyacencias(contarMinasVecinas(i, j));
    }

    private int contarMinasVecinas(int f, int c) {
        int count = 0;
        for (int i = f - 1; i <= f + 1; i++)
            for (int j = c - 1; j <= c + 1; j++)
                if (esPosicionValida(i, j) && celdas[i][j].esMina()) count++;
        return count;
    }

    public void revelarCasilla(int f, int c) throws CasillaYaDescubiertaException {
        if (!esPosicionValida(f, c) || celdas[f][c].estaMarcada()) return;
        
        if (celdas[f][c].estaRevelada())
            throw new CasillaYaDescubiertaException("La casilla ya fue revelada.");

        celdas[f][c].setEstaRevelada(true);
        
        if (celdas[f][c].getMinasAdyacencias() == 0)
            revelarVecinos(f, c);
    }

    private void revelarVecinos(int f, int c) {
        for (int i = f - 1; i <= f + 1; i++)
            for (int j = c - 1; j <= c + 1; j++)
                try { revelarCasilla(i, j); } catch (CasillaYaDescubiertaException e) {}
    }

    public void marcarCasilla(int f, int c) {
        // Validación estricta para TDD: solo marcar si es válida y NO está revelada
        if (esPosicionValida(f, c) && !celdas[f][c].estaRevelada()) {
            celdas[f][c].setMarcada(!celdas[f][c].estaMarcada());
        }
    }

    private boolean esPosicionValida(int f, int c) {
        return f >= 0 && f < TAM && c >= 0 && c < TAM;
    }

    public Casilla getCasilla(int f, int c) { return celdas[f][c]; }

    public boolean esDerrota() {
        for (int i = 0; i < TAM; i++) for (int j = 0; j < TAM; j++)
            if (celdas[i][j].estaRevelada() && celdas[i][j].esMina()) return true;
        return false;
    }

    public boolean esVictoria() {
        int rev = 0;
        for (int i = 0; i < TAM; i++) for (int j = 0; j < TAM; j++)
            if (!celdas[i][j].esMina() && celdas[i][j].estaRevelada()) rev++;
        return rev == (TAM * TAM - MINAS);
    }
}