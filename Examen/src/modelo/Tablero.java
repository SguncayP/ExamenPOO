package modelo;
import java.util.Random;

public class Tablero {
    private final int TAM = 10, MINAS = 10;
    private Casilla[][] celdas = new Casilla[TAM][TAM];

    public Tablero() {
        for (int i = 0; i < TAM; i++)
            for (int j = 0; j < TAM; j++) celdas[i][j] = new Casilla();
        colocarMinas();
        calcularAdyacencias();
    }

    private void colocarMinas() {
        Random r = new Random();
        for (int i = 0; i < MINAS; ) {
            int f = r.nextInt(TAM), c = r.nextInt(TAM);
            if (!celdas[f][c].esMina()) { celdas[f][c].setEsMina(true); i++; }
        }
    }

    private void calcularAdyacencias() {
        for (int i = 0; i < TAM; i++) for (int j = 0; j < TAM; j++) if (!celdas[i][j].esMina()) {
            int c = 0;
            for (int f = i-1; f <= i+1; f++) for (int col = j-1; col <= j+1; col++)
                if (f>=0 && f<TAM && col>=0 && col<TAM && celdas[f][col].esMina()) c++;
            celdas[i][j].setMinasAdyacencias(c);
        }
    }

    public void revelarCasilla(int f, int c) {
        if (f < 0 || f >= TAM || c < 0 || c >= TAM || celdas[f][c].estaRevelada() || celdas[f][c].estaMarcada()) return;
        celdas[f][c].setEstaRevelada(true);
        if (celdas[f][c].getMinasAdyacencias() == 0)
            for (int i = f-1; i <= f+1; i++) for (int j = c-1; j <= c+1; j++) revelarCasilla(i, j);
    }

    public Casilla getCasilla(int f, int c) { return celdas[f][c]; }
    public boolean esDerrota() { for(Casilla[] fila : celdas) for(Casilla c : fila) if(c.estaRevelada() && c.esMina()) return true; return false; }
    public boolean esVictoria() { int rev = 0; for(Casilla[] fila : celdas) for(Casilla c : fila) if(!c.esMina() && c.estaRevelada()) rev++; return rev == (TAM*TAM - MINAS); }
}