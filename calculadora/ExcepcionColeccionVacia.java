package proyectocalculadoraed;
/**
 * Clase de ExcepcionColeccionVacia que extiende RuntimeExcepcion, sirve para las excepciones de la calculadora
 */

public class ExcepcionColeccionVacia extends RuntimeException{

    public ExcepcionColeccionVacia() {
    }

    public ExcepcionColeccionVacia(String message) {
        super(message);
    }
}
