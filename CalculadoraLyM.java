/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculadora;

import java.util.ArrayList;

public class CalculadoraLyM {

    // 1) Verificar validez
    // Se verifica si es un operador o es un numero

    private static boolean esOperador(String caracter) { // Se introduce un caracter en forma de cadena
        char operador = caracter.charAt(0);
        boolean resp;
        resp = false;

        // Se plantean los casos de los operadores
        if (operador == '+' || operador == '-' || operador == '*' || operador == '/' || operador == '^') {
            resp = true; // Si es alguno, la respuesta es verdadera
            return resp;
        } else {
            return resp;
        }
    }

    // Este metodo se introduce una cadena y trata de convertirla en numero, si lo
    // logra, boolean es true, si marca false, es porque no pudo convertirla
    private static boolean esNumero(String cadena) { // Se introduce una cadena
        boolean resp;
        resp = false;
        double numero;
        try { // Se utiliza try y catch para convertir la cadena en numero
            numero = (Double.parseDouble(cadena));
            resp = true;
            return resp;
        } catch (NumberFormatException e) {
            return resp;
        }
    }

    // 2) Convertir de infija a postfija

    // Este metodo busca convertir de infija a postfija
    // El usuario ingresa su operacion de manera infija
    // Devuelve la operacion de manera postfija

    public static PilaADT<String> convierteInfijaPostfija(String textoCalcular) { // Devuelve pila de cadenas
        PilaA<Character> pila = new PilaA(); // Pila de caracteres
        PilaA<String> postfija = new PilaA(); // Pila de cadenas
        String cadena;
        Character caracter;
        int banderaNeg=0;

        for (int i = 0; i < textoCalcular.length(); i++) { // Verificado con la longitud de la expresion introducida
            caracter = textoCalcular.charAt(i);
            
            if(caracter=='-'){
                if(i==0) //-1+10
                    banderaNeg=1;
                else
                    if(textoCalcular.charAt(i-1)=='(')// (8)/(-1)
                        banderaNeg=2;
            }
            
            // Checa si es un numero u operador
            if (Character.isDigit(caracter)) { // Si es numero, se agrega directo a la pila
                cadena = preparaCadena(textoCalcular, i, banderaNeg); // Se utiliza un metodo aparte para generar cadena
                postfija.push(cadena); // Se agrega a la pila
                i = i + cadena.length() - 1; // Se incrementa el iterador
                banderaNeg=0;
            } else {// Caso para cuando no son numeros
                // Si es parentesis que abre
                if (caracter == '(') {
                    pila.push('(');
                } else { // Si es parentesis que cierra
                    if (caracter == ')') {
                        while (!pila.isEmpty() && pila.peek() != '(') { // Se ejecuta mientras que la pila no este vacia y el tope sea distinto de parentesis que abre
                            postfija.push(pila.pop() + "");
                        }
                        if (pila.peek() == '(')
                            pila.pop();
                    } else { // Se utiliza jerarquia (potencia, multiplicacion y division, suma y resta)
                        // Si es potencia
                        if (caracter == '^') {
                            if (!pila.isEmpty())
                                while (!pila.isEmpty() && pila.peek() != '(' && pila.peek() != '/' && pila.peek() != '*'
                                        && pila.peek() != '+' && pila.peek() != '-')
                                    postfija.push(pila.pop() + "");
                            pila.push(caracter);
                        } else { // Si es multiplicacion o division
                            if (caracter == '/' || caracter == '*') {
                                if (!pila.isEmpty())
                                    while (!pila.isEmpty() && pila.peek() != '(' && pila.peek() != '+'
                                            && pila.peek() != '-')
                                        postfija.push(pila.pop() + "");
                                pila.push(caracter);
                            } else { // Si es suma o resta
                                if (caracter == '+' || caracter == '-') {
                                    if (!pila.isEmpty())
                                        while (!pila.isEmpty() && pila.peek() != '(')
                                            postfija.push(pila.pop() + "");
                                        
                                    if(banderaNeg!=2)    
                                        pila.push(caracter);
                                    
                                    
                                }
                            }
                        }
                    }
                }
            }
            
        }
        while (!pila.isEmpty()) { // Verifica parentesis
            if (pila.peek() == '(') 
                pila.pop();
            else
                postfija.push(pila.pop() + "");
        }
        return postfija;
    }

    // Metodo para generar cadena
    private static String preparaCadena(String textoCalcular, int i, int banderaNeg) { // Recibe cadena y entero para ver donde empieza
        String cadena = textoCalcular.charAt(i) + "";
        // No excederse del limite original, verifica si es punto decimal o digito, si se cumple es parte de subcadena a generar
        while (i < textoCalcular.length() - 1 && (textoCalcular.charAt(i + 1) == '.' || Character.isDigit(textoCalcular.charAt(i + 1)))) {
            cadena = cadena + textoCalcular.charAt(i + 1); // Se agrega al final de la cadena, incrementando el indice
            i++;
        }
        if(banderaNeg!=0){ //10/-100
            cadena="-"+cadena; 
            banderaNeg=0;
        }
        
        return cadena; // Regresa la cadena que tiene subcadena
    }
    
    // Metodo para invertir una pila
    private static <T> void inviertePila(PilaADT<T> pila) {
        ArrayList<T> arregloDinamico = new ArrayList();

        while (!pila.isEmpty()) {
            arregloDinamico.add(pila.pop());
        }
        for (int i = 0; i < arregloDinamico.size(); i++) {
            pila.push(arregloDinamico.get(i));
        }
    }

    // 3) Realizar operaciones, desde su forma postfija

    public static double calculoPostfija(PilaADT<String> pila) { // Recibe pila
        double resultado = 0, auxiliar1, auxiliar2, res;
        char operadorChar;
        String operadorString; // Operador

        PilaA<Double> aux = new PilaA(); // Crear pila auxiliar de double
        inviertePila(pila); // Metodo aparte para invertir pila
        while (!pila.isEmpty()) { // Mientras no este vacio
            if (esNumero(pila.peek())) { // Llamar al metodo esNumero
                aux.push(Double.valueOf(pila.pop())); // Se extrae y se convierte en double, agregandose a la pila auxiliar
            } else // Caso en que sea operador
            if (esOperador(pila.peek())) { // Checar con el tope de la pila
                if (!aux.isEmpty()) {
                    auxiliar2 = aux.pop();
                    auxiliar1 = aux.pop();
                    operadorString = pila.pop();
                    operadorChar = operadorString.charAt(0);
                    // Se evalua por casos de las distintas operaciones
                    // Se agrega el resultado de la operacion en la pila auxiliar
                    switch (operadorChar) {
                        case '+' -> {
                            res = auxiliar1 + auxiliar2;
                            aux.push(res);
                        }
                        case '-' -> {
                            res = auxiliar1 - auxiliar2;
                            aux.push(res);
                        }
                        case '*' -> {
                            res = auxiliar1 * auxiliar2;
                            aux.push(res);
                        }
                        case '/' -> {
                            if (auxiliar2 != 0) { // Evitar el caso de dividir entre 0
                                res = auxiliar1 / auxiliar2;
                                aux.push(res);
                            }
                        }
                        case '^' -> {
                            res = Math.pow(auxiliar1, auxiliar2);
                            aux.push(res);
                        }
                        // Cuando no cae en ninguno de los casos se convierte en numero   
                        // default -> aux.push(Double.parseDouble(operadorString));
                        default -> aux.push(Double.valueOf(operadorString));
                    }
                }
            }
        }
        if (!aux.isEmpty()) // Si la pila no esta vacia
            resultado = aux.pop(); // Extraer valor superior
        return resultado; // Se regresa el valor acumulado de la pila
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        String expresion = "100*(-1)";
        PilaADT<String> pila= CalculadoraLyM.convierteInfijaPostfija(expresion);
        Double resp1 = CalculadoraLyM.calculoPostfija(pila);
        System.out.println("Resp1: "+resp1);
        
        
        String expresion2 = "-100+10"; //90
        PilaADT<String> pila2= CalculadoraLyM.convierteInfijaPostfija(expresion2);
        Double resp2 = CalculadoraLyM.calculoPostfija(pila2);
        System.out.println("Resp2: "+resp2);
        
        String expresion3 = "-10+2"; //8
        PilaADT<String> pila3= CalculadoraLyM.convierteInfijaPostfija(expresion3);
        Double resp3 = CalculadoraLyM.calculoPostfija(pila3);
        System.out.println("Resp3: "+resp3);
    }
}