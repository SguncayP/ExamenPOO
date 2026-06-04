package controlador;

import modelo.Tablero;
import excepciones.CasillaYaDescubiertaException;

/**
 * Esta clase es el "cerebro" que conecta la vista con el modelo.
 * Se encarga de recibir lo que el usuario escribe (como "R A5") y
 * convertirlo en una acción que el Tablero pueda entender.
 */
public class JuegoControl {
    private Tablero modelo;

    public JuegoControl(Tablero modelo) {
        this.modelo = modelo;
    }

    /**
     * Procesa la acción que pide el usuario.
     * Primero valida que la coordenada tenga sentido (ej: A1),
     * luego calcula los índices y finalmente llama al modelo para ejecutar
     * el revelado o el marcado. También atrapa cualquier error para que 
     * el juego no se cierre.
     * * @param accion La letra 'R' para revelar o 'M' para marcar.
     * @param coord La coordenada tipo texto (letra y número).
     */
    public void realizarAccion(String accion, String coord) {
        try {
            // 1. Validaciones básicas de formato para evitar errores tontos
            if (coord == null || coord.length() < 2) {
                System.out.println("Error: Formato de coordenada inválido (ej: A5).");
                return;
            }

            // 2. Conversión de coordenada: pasamos letra a fila y número a columna
            int fila = coord.toUpperCase().charAt(0) - 'A';
            int col = Integer.parseInt(coord.substring(1)) - 1;

            // 3. Validación de límites para no salirnos del tablero
            if (fila < 0 || fila >= 10 || col < 0 || col >= 10) {
                System.out.println("Error: La coordenada está fuera del tablero (A-J, 1-10).");
                return;
            }

            // 4. Ejecución de lógica según la acción que eligió el usuario
            if (accion.equalsIgnoreCase("R")) {
                modelo.revelarCasilla(fila, col);
            } else if (accion.equalsIgnoreCase("M")) {
                // Aquí marcamos/desmarcamos sin afectar la revelación
                modelo.getCasilla(fila, col).setMarcada(!modelo.getCasilla(fila, col).estaMarcada());
            } else {
                System.out.println("Error: Acción no reconocida. Usa R (Revelar) o M (Marcar).");
            }

        } catch (CasillaYaDescubiertaException e) {
            // Avisamos al usuario si intentó revelar algo ya abierto
            System.out.println("Aviso: " + e.getMessage());
        } catch (NumberFormatException e) {
            // Si el usuario escribe algo que no es número en la columna
            System.out.println("Error: La columna debe ser un número (ej: 1-10).");
        } catch (Exception e) {
            // Captura cualquier otro error para mantener el programa vivo
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}