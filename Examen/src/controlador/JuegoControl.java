package controlador;
import modelo.Tablero;
import vista.ConsolaVista;


public class JuegoControl {
    private Tablero modelo;

    public JuegoControl(Tablero modelo) {
        this.modelo = modelo;
    }

    public void realizarAccion(String accion, String coord) {
        try {
            // Validar que la coordenada tenga al menos 2 caracteres (letra + número)
            if (coord.length() < 2) {
                System.out.println("Error: Formato de coordenada inválido (ej: A5).");
                return;
            }

            // Convertir coordenada (ej: A5 -> fila 0, col 4)
            int fila = coord.toUpperCase().charAt(0) - 'A';
            int col = Integer.parseInt(coord.substring(1)) - 1;

            // Validar límites del tablero
            if (fila < 0 || fila >= 10 || col < 0 || col >= 10) {
                System.out.println("Error: La coordenada está fuera del tablero (A-J, 1-10).");
                return;
            }

            // Ejecutar la acción
            if (accion.equals("R")) {
                modelo.revelarCasilla(fila, col);
            } else if (accion.equals("M")) {
                modelo.getCasilla(fila, col).setMarcada(!modelo.getCasilla(fila, col).estaMarcada());
            } else {
                System.out.println("Error: Acción no reconocida.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: El número de columna no es válido.");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}