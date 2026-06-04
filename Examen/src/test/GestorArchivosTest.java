package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import modelo.Tablero;
import modelo.GestorArchivos;
import java.io.File;

/**
 * Suite de pruebas para validar el sistema de persistencia (Guardado/Cargado).
 * Verifica que los objetos puedan ser serializados a disco y deserializados 
 * correctamente sin pérdida de información.
 */
public class GestorArchivosTest {

    /**
     * Prueba el flujo completo de persistencia: 
     * 1. Guarda un tablero en un archivo temporal.
     * 2. Verifica la creación física del archivo.
     * 3. Recupera el tablero y confirma que no es nulo.
     * 4. Realiza una limpieza del entorno eliminando el archivo de prueba.
     */
    @Test
    public void testGuardarYRecuperarJuego() {
        // 1. Escenario: Creamos un tablero para guardar
        Tablero original = new Tablero();
        String nombreArchivo = "test_partida.dat";
        
        // 2. Acción: Guardamos el estado del objeto en disco
        GestorArchivos.guardarJuego(original, nombreArchivo);
        
        // 3. Verificación: Confirmamos que el sistema de archivos recibió el dato
        File archivo = new File(nombreArchivo);
        assertTrue(archivo.exists(), "El archivo de guardado debería existir.");
        
        // 4. Integridad: Cargamos el archivo y verificamos que el objeto resultante es válido
        Tablero recuperado = GestorArchivos.cargarJuego(nombreArchivo);
        assertNotNull(recuperado, "El tablero recuperado no debería ser nulo.");
        
        // 5. Limpieza: Eliminamos el archivo temporal para mantener el entorno limpio
        archivo.delete();
    }
}