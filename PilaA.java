/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocalculadoraed;


/**
 * Clase de PilaA que implementa PilaADT T, programada en clase
 *
 */
public class PilaA <T> implements PilaADT<T>{
    
    private int tope;
    private T[] datos;
    private final int MAX = 20;
    
    public PilaA(){
        datos = (T[]) new Object[MAX];
        tope = -1; // indica pila vacia
    }
   
    public PilaA(int max){
        datos = (T[]) new Object[max];
        tope = -1; // indica pila vacia
    }

    @Override
    public void push(T datoNuevo) {
        if(tope == datos.length - 1){
            expande();
        }
        tope++;
        datos[tope] = datoNuevo;
    }

    @Override
    public T pop() {
        if(this.isEmpty()){
            throw new ExcepcionColeccionVacia("Error: la pila esta vacia");
        }
        T resultado = datos[tope];
        datos[tope] = null;
        tope--;
        return resultado;
    }

    @Override
    public boolean isEmpty() {
        return tope == -1;
    }

    @Override
    public T peek() {
        if(this.isEmpty()){
            throw new ExcepcionColeccionVacia("Error: la pila esta vacia");
        }
        return datos[tope];
    }
    
    public void expande(){
        T[] masGrande = (T[]) new Object[datos.length * 2];
        
        for(int i = 0; i<= tope; i++){
            masGrande[i] = datos[i];
        }
        datos = masGrande;
    }
    
    // multiPop ejercicio 21 inciso B
    @Override
    public void multiPop(int n) {
        if(this.tope >= n-1){
            int i;
            for(i=this.tope; i > this.tope-n; i--){
                datos[i] = null;
            }
            this.tope = i;
        }
    }
    
    // muestra elementos de la Pila
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        int i;
        for(i=this.tope; i>=0; i--){
            sb.append(datos[i]);
            sb.append("\n");
        }
        return sb.toString();
    }
}
