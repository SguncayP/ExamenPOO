package test;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import modelo.Tablero;
import excepciones.CasillaYaDescubiertaException;

public class TableroTest {

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

    @Test
    public void testLanzarExcepcionAlRevelarDosVeces() {
        Tablero t = new Tablero();
        assertThrows(CasillaYaDescubiertaException.class, () -> {
            t.revelarCasilla(0, 0); // Primer revelado
            t.revelarCasilla(0, 0); // Segundo revelado: debe lanzar la excepción
        }, "Debió lanzar CasillaYaDescubiertaException al intentar revelar una casilla abierta.");
    }

    @Test
    public void testNoSePuedeMarcarCasillaRevelada() throws CasillaYaDescubiertaException {
        Tablero t = new Tablero();
        int f = 0, c = 0;
        
        // Primero revelamos la casilla
        t.revelarCasilla(f, c);
        
        // Intentamos marcarla (la lógica del tablero debe impedirlo)
        t.marcarCasilla(f, c);
        
        // Verificamos que NO esté marcada
        assertFalse(t.getCasilla(f, c).estaMarcada(), "No debería permitir marcar una casilla revelada.");
    }
}