package proyectocalculadoraed;

/**
 * La clase EjecutableCalculadora es la clase principal (main) del proyecto ProyectoCalculadoraED.
 * Se utiliza para iniciar la aplicacion de la calculadora.
 */

public class EjecutableCalculadora {

    /**
     * El metodo main es el punto de entrada de la aplicacion.
     * Crea una instancia de la clase VistaCalculadora y la hace visible, lo que inicia la interfaz de usuario de la calculadora.
     *
     * @param args Los argumentos de linea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        VistaCalculadora vista = new VistaCalculadora(); // Crea una instancia de la vista de la calculadora
        vista.setVisible(true); // Hace visible la vista de la calculadora
    }
}
