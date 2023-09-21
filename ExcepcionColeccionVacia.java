/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
