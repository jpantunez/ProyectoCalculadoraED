package proyectocalculadoraed;

import java.util.ArrayList;
import java.util.Scanner;

public class MetodosDelProyecto {

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
        byte verificacion = 0;

        for (int i = 0; i < textoCalcular.length(); i++) { // Verificado con la longitud de la expresion introducida
            caracter = textoCalcular.charAt(i); //caracter en la pos i
            // Checa si es un numero u operador+
            if(i==1 && textoCalcular.charAt(i-1)=='-' && '-'==textoCalcular.charAt(i)
                    ||caracter.equals('-') && textoCalcular.length()-1==i){
                VistaCalculadora.syntaxErrorOccurred = true;
            }
            if(((i==0 || textoCalcular.charAt(i-1)=='+' || textoCalcular.charAt(i-1)=='-'
                     || textoCalcular.charAt(i-1)=='*'  || textoCalcular.charAt(i-1)=='/'
                     || textoCalcular.charAt(i-1)=='^'  || textoCalcular.charAt(i-1)=='(') && caracter.equals('-'))){
                verificacion=1;
            }
            else if (Character.isDigit(caracter)) { // Si es numero, se agrega directo a la pila
                cadena = preparaCadena(textoCalcular, i); // Se utiliza un metodo aparte para generar cadena
                if(verificacion==1){
                    postfija.push("0");
                    pila.push('-');
                    postfija.push(cadena);
                    verificacion = 0;
                }
                else{
                    postfija.push(cadena); // Se agrega a la pila
                }
                i = i + cadena.length() - 1; // Se incrementa el iterador
            }
            else {// Caso para cuando no son numeros
                // Si es parentesis que abre
                if (verificacion==1){
                    VistaCalculadora.syntaxErrorOccurred = true;
                    verificacion=0;
                }else{
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
                                        pila.push(caracter);
                                    }
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
    private static String preparaCadena(String textoCalcular, int i) { // Recibe cadena y entero para ver donde empieza
        String cadena = textoCalcular.charAt(i) + "";
        // No excederse del limite original, verifica si es punto decimal o digito, si se cumple es parte de subcadena a generar
        while (i < textoCalcular.length() - 1 && (textoCalcular.charAt(i + 1) == '.' || Character.isDigit(textoCalcular.charAt(i + 1)))) {
            cadena = cadena + textoCalcular.charAt(i + 1); // Se agrega al final de la cadena, incrementando el indice
            i++;
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
        Scanner entrada = new Scanner(System.in);
        String expresion;
        System.out.println("Ingresa la operacion que deseas que sea resuelta: ");
        expresion = entrada.next();
        PilaADT<String> pila = MetodosDelProyecto.convierteInfijaPostfija(expresion);
        Double resp = MetodosDelProyecto.calculoPostfija(pila);
        System.out.println("Resultado: "+resp);
    }
}
