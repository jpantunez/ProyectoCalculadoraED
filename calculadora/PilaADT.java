package proyectocalculadoraed;

/**
 * La interfaz PilaADT define las operaciones básicas que debe proporcionar una pila.
 *
 * @param <T> El tipo de elementos que se almacenarán en la pila.
 */

public interface PilaADT<T> {
    
    public void push(T dato);
    public T pop();
    public boolean isEmpty();
    public T peek();
    public void multiPop(int n);
}
