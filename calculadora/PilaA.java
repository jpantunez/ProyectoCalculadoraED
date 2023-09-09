/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculadora;

/**
 *
 * @author joseantunez
 */
public class PilaA<T> implements PilaADT<T>{
    private T[] pila;
    private int tope;
    private final int MAX_PILA=20;
    
    public PilaA(){
        pila=(T[]) new Object[MAX_PILA]; 
        tope=-1;
    }
    
    public PilaA(int max){
        pila=(T[]) new Object[max];
        tope=-1;
    }

    public void push(T dato) {
        if(tope==pila.length-1)
            expande();
        tope++;
        pila[tope]=dato;
                
    }

    private void expande(){
        T[] masGrande = (T[]) new Object[pila.length*2];
        
        for(int i=0; i<pila.length;i++)
            masGrande[i]=pila[i];
        pila = masGrande;
    
    }
  
    public T pop() {
        if(isEmpty())
            throw new RuntimeException("La pila esta vacia");
        T resultado = pila[tope];
        tope--;
        return resultado;
    }

    
    public boolean isEmpty() {
        return tope == -1;
    }

    
    public T peek() {
        if(isEmpty())
            throw new RuntimeException("La pila esta vacia");
        
        return pila[tope];
    }
    
    public boolean multiPop(int n){
        boolean resp=false;
        int i=0;
        
        if(this.tope+1>=n){
            resp=true;
            for(i=0;i<n;i++){
                this.pop();
            }
        }
        
        return resp;
    }
    
    //toString
    public String toString(){
        PilaADT<T> aux = new PilaA();
        StringBuilder sb;
        sb=new StringBuilder();
        
        int i;
        
        for(i=tope; i>=0; i--){
            sb.append(pila[i]);
            sb.append("\n");
        }
        return sb.toString();
    }
}
