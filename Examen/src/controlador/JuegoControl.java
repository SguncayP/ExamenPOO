package controlador;
import modelo.Tablero;
import vista.ConsolaVista;
import excepciones.CasillaYaDescubiertaException;

public class JuegoControl {
    private Tablero modelo;

    public JuegoControl(Tablero modelo) {
        this.modelo = modelo;
    }

    public void realizarAccion(String accion, String coord) {
        try {
            // 1. Validaciones básicas de formato
            if (coord == null || coord.length() < 2) {
                System.out.println("Error: Formato de coordenada inválido (ej: A5).");
                return;
            }

            // 2. Conversión de coordenada
            int fila = coord.toUpperCase().charAt(0) - 'A';
            int col = Integer.parseInt(coord.substring(1)) - 1;

            // 3. Validación de límites
            if (fila < 0 || fila >= 10 || col < 0 || col >= 10) {
                System.out.println("Error: La coordenada está fuera del tablero (A-J, 1-10).");
                return;
            }

            // 4. Ejecución de lógica según la acción
            if (accion.equalsIgnoreCase("R")) {
                modelo.revelarCasilla(fila, col);
            } else if (accion.equalsIgnoreCase("M")) {
                modelo.getCasilla(fila, col).setMarcada(!modelo.getCasilla(fila, col).estaMarcada());
            } else {
                System.out.println("Error: Acción no reconocida. Usa R (Revelar) o M (Marcar).");
            }

        } catch (CasillaYaDescubiertaException e) {
            // Manejo de tu excepción personalizada
            System.out.println("Aviso: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: La columna debe ser un número (ej: 1-10).");
        } catch (Exception e) {
            // Manejo de cualquier error inesperado
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}