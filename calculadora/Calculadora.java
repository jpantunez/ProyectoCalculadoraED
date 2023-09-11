/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculadora;

/**
 *
 * @author joseantunez
 */
public abstract class Calculadora {

    /**
     * @param args the command line arguments
     */

    

/**
 * @author Maria Fernanda Cuevas  y Luis SÃ¡nchez
 * @date 09 sep. 2023
 * @purpose: realizar los metodos que se implementaran en la calculadora  
 */


    
    
//1)verificar validez 
    //se verifica si es un operador o es un nÃºmero 
    
    private static boolean esOperador(String c) {
        char operador = c.charAt(0);
        boolean resp;
        resp = false; 
        
        //se plantean los casos de los operadores 
        if (operador == '+' || operador == '-' || operador == '*' || operador == '/' || operador == '^') {
            resp = true;
            return resp; //si es alguno el resp es verdadero
        } else {
            return resp;
        }
    }
    
    
    //este mÃ©todo se introduce una cadena y trata de convertirla en nÃºmero, si lo logra boolean es true, si marca false es que pq no pudo convertirla 
     private static boolean esNumero(String c){ //se introduce cadena 
         boolean resp;
         resp = false;
         double numero;
         try{ //Se utiliza try y catch para convertir cadena en numero
                numero = (Double.parseDouble(c));
                resp = true;
                return resp;
            }
            catch(NumberFormatException e){
                return resp;
            }
}
     //2) convertir de infija a postFija
     
     //este mÃ©todo busca convertir de infija a postfija
     //el usuario ingresa su operaciÃ³n de manera infija 
     //devuelve la operaciÃ³n de manera postfija
     
     public static PilaA<String> infijaPostFija(String expresion){ //Devuelve pila de string 
        PilaA<Character> pila= new PilaA(); //pila de caracteres 
        PilaA<String> postfija = new PilaA(); //pila de string 
        String cad;
        Character caracter;
        
        for(int i = 0; i < expresion.length(); i++){ //verificado con la logitud de la expresiÃ³n introducida 
            caracter = expresion.charAt(i);
            // checa si es un numero u operador
            if(Character.isDigit(caracter)) { //si es numero se agrega directo a la pila
                cad = generaCad(expresion, i); //se utiliza mÃ©todo a parte para generar cadena 
                postfija.push(cad);  //se pone agrega a pila  
                i = i + cad.length()-1; //se incrementa iterador 
            }
            else {//caso para cuando no son numeros 
                //si es parÃ©ntesis que abre
                if(caracter == '(') {
                    pila.push('(');
                }
                else { //si es ) parentesis que cierra 
                    if(caracter == ')') {
                        while(!pila.isEmpty() && pila.peek()!='(') { //Se ejecuta mientras que la pila no este vacÃ­a y el tope es distinto de parantesia que abre 
                            postfija.push(pila.pop()+"");
                        }
                        if(pila.peek()=='(')
                            pila.pop();
                    }
                    //Se utiliza jerarquia (potencia, mul y div, suma y resta)
                    else{ //si es potencia
                        if(caracter == '^'){
                            if(!pila.isEmpty())
                                while(!pila.isEmpty() && pila.peek()!='(' && pila.peek()!= '/' && pila.peek()!= '*' && pila.peek()!='+' && pila.peek()!='-')
                                        postfija.push(pila.pop()+"");
                            pila.push(caracter);
                        }
                        else { //si es multiplicacion o division
                            if(caracter == '/' || caracter == '*') {
                                if(!pila.isEmpty()) 
                                    while(!pila.isEmpty() && pila.peek()!='(' && pila.peek()!='+' && pila.peek()!='-')
                                        postfija.push(pila.pop()+"");
                                pila.push(caracter);
                            }
                            else { //si es suma o resta
                                if(caracter == '+' || caracter == '-') {
                                    if(!pila.isEmpty()) 
                                        while(!pila.isEmpty() && pila.peek()!='(')
                                            postfija.push(pila.pop()+"");
                                    pila.push(caracter);
                                }
                            }
                        }
                    }
                }
            }
            
        }
        while(!pila.isEmpty()) { //verifica parÃ©ntesis 
            if(pila.peek()=='(')
                pila.pop();
            else 
                postfija.push(pila.pop()+""); 
        }
        return postfija;
    }
     //mÃ©todo genera cadena
     private static String generaCad(String expresion, int i){ //recibe cadena y entero para ver donde empieza
        String cadena = expresion.charAt(i)+"";
        //no excederse del lÃ­mite original, verifica si es punto decimal o dÃ­gito, si se cumple es parte de subcadena a agenerar 
        while(i < expresion.length()-1 && (expresion.charAt(i+1)=='.' || Character.isDigit(expresion.charAt(i+1))) ) {
                    cadena = cadena + expresion.charAt(i+1); //se agregar al final de la cadena, incrementar el indice
                    i++;
                }
        return cadena; //regresa cadena que tiene subcadena 
    }
     
     //3)realizar operaciones, desde su forma postfija
     
     public static double evaluacionPostfija(PilaADT<String> pila){ //Recibe pila
            double resultado = 0, var1, var2, res;
            char operadorChar;
            String operadorString; //operador

            PilaA<Double> aux = new PilaA(); //crear pila auxiliar de double
            inviertePila(pila); //mÃ©todo a parte para invertir pila
            while(!pila.isEmpty()){ //mientras sea distinto del vacio 
                if(esNumero(pila.peek())){ //llamar al metodo esNumero
                    aux.push(Double.parseDouble(pila.pop())); //Se extrae y se convierte en double, agregandose a la pila aux
                }
                else //caso en que sea operador 
                    if(esOperador(pila.peek())){  //Checar con el tope de la pila
                        if(!aux.isEmpty()){
                            var2=aux.pop();
                            var1=aux.pop();
                            operadorString = pila.pop();
                            operadorChar = operadorString.charAt(0);
                            //Se evalua por casos de las disntintas operaciones 
                            //se agrega el resultado de la operacion en la pila aux
                            switch (operadorChar) {
                                case '+':
                                        res = var1 + var2;
                                        aux.push(res);
                                    break;
                                case '-':
                                        res = var1 - var2;
                                        aux.push(res);
                                    break;
                                case '*':
                                        res = var1 * var2;
                                        aux.push(res);
                                    break;
                                case '/':
                                    if(var2!=0){ //evitar el casoo de dividir entre 0
                                        res = var1 / var2;
                                        aux.push(res);
                                    }
                                    break;
                                case '^':
                                        res = Math.pow(var1, var2);
                                        aux.push(res);
                                    break;
                                    //cuando no cae en ninguno de los casos se convierte en numero
                                default:
                                    aux.push(Double.parseDouble(operadorString));
                                    break;
                        }
                    }
                }
            }
            if(!aux.isEmpty()) //si la pila no esta vacia 
                resultado = aux.pop(); //Extraer valor superior
            return resultado; //se regresa el valor acumulado de la pila 
        }
     
     private static <T> void inviertePila(PilaADT<T> pila){
            ArrayList<T> c = new ArrayList();

            while(!pila.isEmpty()){
                c.add(pila.pop());
            }
            for(int i = 0; i < c.size(); i++){
                pila.push(c.get(i));
            }
        }
}
     
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
