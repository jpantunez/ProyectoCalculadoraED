package proyectocalculadoraed;

/**
 * La clase PilaA es una implementacio de la interfaz PilaADT del tipo T.
 * Representa una pila generica que almacena elementos de tipo T.
 *
 * @param <T> El tipo de elementos que se almacenan en la pila.
 */

public class PilaA<T> implements PilaADT<T> {

    private int tope;
    private T[] datos;
    private final int MAX = 20;

    /**
     * Constructor predeterminado que crea una pila con capacidad maxima predeterminada.
     */
    public PilaA() {
        datos = (T[]) new Object[MAX];
        tope = -1; // indica pila vacia
    }

    /**
     * Constructor que crea una pila con capacidad maxima especificada.
     *
     * @param max El tamano maximo de la pila.
     */
    public PilaA(int max) {
        datos = (T[]) new Object[max];
        tope = -1; // indica pila vacia
    }

    /**
     * Agrega un elemento a la pila.
     *
     * @param datoNuevo es el dato de tipo T que ingresa.
     */
    @Override
    public void push(T datoNuevo) {
        if (tope == datos.length - 1) {
            expande();
        }
        tope++;
        datos[tope] = datoNuevo;
    }

    /**
     * Elimina un elemento a la pila.
     * Ademas, regresa como resultado el elemento eliminado.
     * 
     * @return resultado es el dato eliminado de tipo T que elimina.
     */
    @Override
    public T pop() {
        if (this.isEmpty()) {
            throw new ExcepcionColeccionVacia("Error: la pila está vacía");
        }
        T resultado = datos[tope];
        datos[tope] = null;
        tope--;
        return resultado;
    }

    /**
    * Comprueba si la pila esta vacia.
    * 
    * @return true si la pila esta vacia, false en caso contrario.
    */
    @Override
    public boolean isEmpty() {
        return tope == -1;
    }

    /**
    * Regresa como resultado el ultimo elemento ingresado a la pila.
    * 
    * @return datos[tope] que es el ultimo elemento ingresado.
    */
    @Override
    public T peek() {
        if (this.isEmpty()) {
            throw new ExcepcionColeccionVacia("Error: la pila está vacía");
        }
        return datos[tope];
    }

    /**
     * Expande la capacidad de la pila duplicando su tamano actual.
     */
    public void expande() {
        T[] masGrande = (T[]) new Object[datos.length * 2];

        for (int i = 0; i <= tope; i++) {
            masGrande[i] = datos[i];
        }
        datos = masGrande;
    }

    /**
     * Elimina los ultimos n elementos de la pila.
     *
     * @param n El numero de elementos a eliminar.
     */
    @Override
    public void multiPop(int n) {
        if (this.tope >= n - 1) {
            int i;
            for (i = this.tope; i > this.tope - n; i--) {
                datos[i] = null;
            }
            this.tope = i;
        }
    }

    /**
     * Devuelve una representacion en forma de cadena de la pila.
     *
     * @return Una cadena que muestra los elementos de la pila, uno por linea.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i;
        for (i = this.tope; i >= 0; i--) {
            sb.append(datos[i]);
            sb.append("\n");
        }
        return sb.toString();
    }
}
