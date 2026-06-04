package modelo;

import java.io.Serializable;

/**
 * Representa la unidad básica de información del tablero del juego.
 * Cada instancia de Casilla encapsula su estado físico y lógico, 
 * permitiendo gestionar si contiene una mina, si ha sido revelada 
 * por el usuario o si presenta una marca de precaución.
 */
public class Casilla implements Serializable {
    
    // Identificador único para garantizar la consistencia en el proceso de serialización
    private static final long serialVersionUID = 1L; 
    
    private boolean esMina, estaRevelada, marcada;
    private int minasAdyacencias;

    /**
     * Constructor por defecto. Inicializa la casilla en un estado neutro:
     * sin minas, oculta y sin marcas.
     */
    public Casilla() {
        this.esMina = false;
        this.estaRevelada = false;
        this.marcada = false;
        this.minasAdyacencias = 0;
    }

    // --- Métodos de Acceso (Getters y Setters) ---

    public boolean esMina() { return esMina; }
    public void setEsMina(boolean esMina) { this.esMina = esMina; }
    
    public boolean estaRevelada() { return estaRevelada; }
    public void setEstaRevelada(boolean r) { this.estaRevelada = r; }
    
    public boolean estaMarcada() { return marcada; }
    public void setMarcada(boolean m) { this.marcada = m; }
    
    public int getMinasAdyacencias() { return minasAdyacencias; }
    public void setMinasAdyacencias(int n) { this.minasAdyacencias = n; }
}