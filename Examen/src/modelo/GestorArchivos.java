
package modelo;

import java.io.*;

/**
 * Clase encargada de la persistencia del sistema.
 * Utiliza serialización para convertir el objeto Tablero en un archivo .dat
 * y viceversa, permitiendo guardar y retomar partidas.
 */
public class GestorArchivos {

    /**
     * Guarda el estado actual del juego en un archivo.
     * Usamos 'try-with-resources' para asegurarnos de cerrar el flujo
     * de salida automáticamente, incluso si ocurre un error.
     * * @param tablero El objeto que contiene toda la información de la partida.
     * @param nombreArchivo El nombre del archivo donde se guardará (ej: "partida.dat").
     */
    public static void guardarJuego(Tablero tablero, String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(tablero);
            System.out.println("Juego guardado correctamente.");
        } catch (IOException e) {
            // Informamos al usuario si hubo problemas de escritura en disco
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    /**
     * Recupera una partida guardada desde un archivo.
     * * @param nombreArchivo El archivo de donde cargaremos el objeto Tablero.
     * @return El objeto Tablero con la partida recuperada, o null si algo falla.
     */
    public static Tablero cargarJuego(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (Tablero) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Capturamos si el archivo no existe o si la clase del objeto no coincide
            System.out.println("No se pudo cargar el juego: " + e.getMessage());
            return null;
        }
    }
}