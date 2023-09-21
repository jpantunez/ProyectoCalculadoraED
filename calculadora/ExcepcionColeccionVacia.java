package proyectocalculadoraed;

/**
 * La clase ExcepcionColeccionVacia se extiende de RuntimeExcepcion y sirve para agregar excepciones a la calculadora.
 * Se utiliza para manejar situaciones en las que una pila esta vacia y se realiza una operacion que requiere elementos en la pila.
 */

public class ExcepcionColeccionVacia extends RuntimeException {

    /**
     * Construye una nueva instancia de ExcepcionColeccionVacia sin mensaje de detalle.
     */
    public ExcepcionColeccionVacia() {
    }

    /**
     * Construye una nueva instancia de ExcepcionColeccionVacia con un mensaje de detalle.
     *
     * @param message El mensaje de detalle que describe la causa de la excepción.
     */
    public ExcepcionColeccionVacia(String message) {
        super(message);
    }
}
