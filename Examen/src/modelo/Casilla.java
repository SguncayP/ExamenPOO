package modelo;



import java.io.Serializable;

public class Casilla implements Serializable {
    private static final long serialVersionUID = 1L; // Importante para la serialización
    private boolean esMina, estaRevelada, marcada;
    private int minasAdyacencias;

    public Casilla() {
        this.esMina = false;
        this.estaRevelada = false;
        this.marcada = false;
        this.minasAdyacencias = 0;
    }

    public boolean esMina() { return esMina; }
    public void setEsMina(boolean esMina) { this.esMina = esMina; }
    public boolean estaRevelada() { return estaRevelada; }
    public void setEstaRevelada(boolean r) { this.estaRevelada = r; }
    public boolean estaMarcada() { return marcada; }
    public void setMarcada(boolean m) { this.marcada = m; }
    public int getMinasAdyacencias() { return minasAdyacencias; }
    public void setMinasAdyacencias(int n) { this.minasAdyacencias = n; }
}