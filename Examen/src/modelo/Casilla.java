package modelo;

public class Casilla {
    private boolean esMina, estaRevelada;
    private int minasAdyacencias;

    public boolean esMina() { return esMina; }
    public void setEsMina(boolean esMina) { this.esMina = esMina; }
    public boolean estaRevelada() { return estaRevelada; }
    public void setEstaRevelada(boolean r) { this.estaRevelada = r; }
    public int getMinasAdyacencias() { return minasAdyacencias; }
    public void setMinasAdyacencias(int n) { this.minasAdyacencias = n; }
}