package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import modelo.Tablero;
import modelo.GestorArchivos;
import java.io.File;

public class GestorArchivosTest {

    @Test
    public void testGuardarYRecuperarJuego() {
        // 1. Preparamos el escenario
        Tablero original = new Tablero();
        String nombreArchivo = "test_partida.dat";
        
        // 2. Ejecutamos la acción (Guardar)
        GestorArchivos.guardarJuego(original, nombreArchivo);
        
        // 3. Verificamos que el archivo se creó físicamente
        File archivo = new File(nombreArchivo);
        assertTrue(archivo.exists(), "El archivo de guardado debería existir.");
        
        // 4. Cargamos y verificamos la integridad (Lectura)
        Tablero recuperado = GestorArchivos.cargarJuego(nombreArchivo);
        assertNotNull(recuperado, "El tablero recuperado no debería ser nulo.");
        
        // Limpieza: borramos el archivo de prueba
        archivo.delete();
    }
}