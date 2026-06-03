package principal;

import modelo.Tablero;
import vista.ConsolaVista;
import controlador.JuegoControl;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tablero modelo = new Tablero();
        ConsolaVista vista = new ConsolaVista();
        JuegoControl controlador = new JuegoControl(modelo, vista);
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            vista.mostrarTablero(modelo);
            System.out.print("Coordenada (A1) o 'salir': ");
            String entrada = sc.next();
            if (entrada.equalsIgnoreCase("salir")) break;
            controlador.procesarJugada(entrada);
            if (modelo.esDerrota()) { vista.mostrarMensaje("¡Perdiste!"); break; }
            if (modelo.esVictoria()) { vista.mostrarMensaje("¡Ganaste!"); break; }
        }
        sc.close();
    }
}