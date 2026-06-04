package excepciones;

/**
 * Excepción personalizada que se lanza cuando el usuario intenta revelar
 * una casilla que ya ha sido procesada previamente.
 * * Esta clase forma parte del blindaje lógico del sistema, permitiendo
 * controlar errores específicos del dominio del juego.
 */
public class CasillaYaDescubiertaException extends Exception {
    
    /**
     * Constructor que recibe el mensaje de error para informar al usuario.
     * @param mensaje Descripción del error que se mostrará en la consola.
     */
    public CasillaYaDescubiertaException(String mensaje) {
        super(mensaje);
    }
}