package modelo;

import java.io.*;

public class GestorArchivos {
    public static void guardarJuego(Tablero tablero, String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(tablero);
            System.out.println("Juego guardado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public static Tablero cargarJuego(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (Tablero) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se pudo cargar el juego: " + e.getMessage());
            return null;
        }
    }
}