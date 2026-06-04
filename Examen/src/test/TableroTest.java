package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import modelo.Tablero;
import excepciones.CasillaYaDescubiertaException;

/**
 * Suite de pruebas unitarias para validar el comportamiento del Tablero.
 * Utilizamos JUnit 5 para aplicar la metodología TDD, asegurando que 
 * todas las reglas de negocio funcionen correctamente antes de la ejecución.
 */
public class TableroTest {

    /**
     * Valida que al crear un nuevo tablero, el número de minas sea exactamente 10.
     * Esto garantiza que la configuración inicial del juego es correcta.
     */
    @Test
    public void testDiezMinasAlIniciar() {
        Tablero t = new Tablero();
        int minasContadas = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (t.getCasilla(i, j).esMina()) {
                    minasContadas++;
                }
            }
        }
        assertEquals(10, minasContadas, "El tablero debe tener exactamente 10 minas al iniciar.");
    }

    /**
     * Prueba el manejo de excepciones personalizadas.
     * Verifica que si el usuario intenta abrir una casilla ya abierta,
     * el sistema lance la excepción 'CasillaYaDescubiertaException'.
     */
    @Test
    public void testLanzarExcepcionAlRevelarDosVeces() {
        Tablero t = new Tablero();
        assertThrows(CasillaYaDescubiertaException.class, () -> {
            t.revelarCasilla(0, 0); // Primer revelado
            t.revelarCasilla(0, 0); // Segundo revelado: aquí debe saltar el error
        }, "Debió lanzar CasillaYaDescubiertaException al intentar revelar una casilla abierta.");
    }

    /**
     * Valida la regla de negocio: no se puede poner bandera (marcar)
     * en una casilla que ya ha sido revelada.
     */
    @Test
    public void testNoSePuedeMarcarCasillaRevelada() throws CasillaYaDescubiertaException {
        Tablero t = new Tablero();
        int f = 0, c = 0;
        
        // Primero revelamos la casilla
        t.revelarCasilla(f, c);
        
        // Intentamos marcarla: la lógica del tablero debe impedir esta acción
        t.marcarCasilla(f, c);
        
        // Verificamos que el estado de 'marcada' sigue siendo falso
        assertFalse(t.getCasilla(f, c).estaMarcada(), "No debería permitir marcar una casilla revelada.");
    }
}