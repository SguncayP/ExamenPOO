package controlador;
import modelo.Tablero;
import vista.ConsolaVista;

public class JuegoControl {
    private Tablero modelo;
    private ConsolaVista vista;

    public JuegoControl(Tablero m, ConsolaVista v) { this.modelo = m; this.vista = v; }

    public void procesarJugada(String coord) {
        try {
            int f = coord.toUpperCase().charAt(0) - 'A';
            int c = Integer.parseInt(coord.substring(1)) - 1;
            if (f >= 0 && f < 10 && c >= 0 && c < 10) modelo.getCasilla(f, c).setEstaRevelada(true);
            else vista.mostrarMensaje("Coordenada fuera de rango.");
        } catch (Exception e) { vista.mostrarMensaje("Formato inválido (ej: A1)."); }
    }
}