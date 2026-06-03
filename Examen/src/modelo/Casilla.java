package modelo;



public class Casilla {
    private boolean esMina, estaRevelada, marcada;
    private int minasAdyacencias;

    public boolean esMina() { return esMina; }
    public void setEsMina(boolean m) { this.esMina = m; }
    public boolean estaRevelada() { return estaRevelada; }
    public void setEstaRevelada(boolean r) { this.estaRevelada = r; }
    public boolean estaMarcada() { return marcada; }
    public void setMarcada(boolean m) { this.marcada = m; }
    public int getMinasAdyacencias() { return minasAdyacencias; }
    public void setMinasAdyacencias(int n) { this.minasAdyacencias = n; }
}