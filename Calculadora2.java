/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculadora;


import java.util.*;
import java.util.regex.*;
/**
 *
 * @author joseantunez
 */
public abstract class Calculadora2 {

    /**
     * @param args the command line arguments
     */
    
    
    // PASO 1 Verificar sintaxis 
    
    
    //Revisa si es operador
    public static boolean esOperador(Character c){
        
        return(c == '+'|| c == '-'|| c == '*'|| c == '/'|| c == '^' );
    }
    
    //Revisa si es operando
    public static boolean esOperando(String c){
        return c.matches("\\-?\\d+\\.?\\d*") || c.matches("\\-?\\.\\d+");
    }
    
    
    public static boolean revisaExpresion(String cadena){
        boolean resp = true;
        int i=0, prioridad1, prioridad2;
        Character caracter, parentesis1;
        PilaADT <Character> pila1  = new PilaA();
        
        //1+2+3+.
        if(esOperador(cadena.charAt(0))&&cadena.charAt(0)!='-'||esOperador(cadena.charAt(cadena.length()-1))||cadena.charAt(cadena.length()-1)=='.')
            throw new ExceptionCollection("ERROR de sintaxis");
        while(i<cadena.length()&&resp){
            
            //esta variable va a ir tomando el valor de cada caracter de la cadena
            caracter=cadena.charAt(i);
            if(caracter=='('||caracter==')'){
                if(caracter == '('){
                pila1.push(caracter);
                    if(i<cadena.length()-1&&((esOperador(cadena.charAt(i+1))&&cadena.charAt(i+1)!='+')||(esOperador(cadena.charAt(i+1))&&cadena.charAt(i+1)!='-')))
                        throw new ExceptionCollection("ERROR de sintaxis");
                }
                
                else{
                    if(caracter==')'&&!pila1.isEmpty()){
                        parentesis1=pila1.pop();
                            if(parentesis1!='(')
                                resp=false; //Que es mejor? throw ERROR DE SINTAXIS O RESP=FALSE
                    }
                    
                    if(!pila1.isEmpty())
                        resp=false;   
                }
            }
        
            else{    
                //este if se usa si hay 2 operadores juntos y determine si hay error de sintaxis
                //Ej: 3+-2, 3/-1, 2+/1, 2-*2
                // los ultimos 2 son error de sintaxis
                if(i<cadena.length()-1&&esOperador(cadena.charAt(i))&&esOperador(cadena.charAt(i+1))){
                    prioridad1=getPrioridad(caracter);
                    prioridad2=getPrioridad(cadena.charAt(i+1));
                    
                    if(prioridad1<prioridad2) //este if identifica error de sintaxis 
                        throw new ExceptionCollection("ERROR de sintaxis");
                    }
            
                }     
        i++; 
        }
            
        return resp;
    
    }
    
    public static ArrayList<String> preparaCadena(String cadena){
     int i=0,j=1;
        ArrayList<Character>numeros = new ArrayList<>(List.of('0','1','2','3','4','5','6','7','8','9'));
        ArrayList<String> cadenaPrep = new ArrayList();
        Character x;
        String y="";
        double num=0.0;
        
        while(i<=cadena.length()-1){
            x=cadena.charAt(i);
            
            if(esOperador(x)){
                //if si es el primer oper
                cadenaPrep.add(""+x);
            
            }
            else{
                if(numeros.contains(x)){
                    num=num*10+Character.getNumericValue(x);
                    if(!numeros.contains(cadena.charAt(i+1))){
                        cadenaPrep.add(""+num);
                        num=0;
                    }    
                }
                else
                    if(x=='.'){
                        x=cadena.charAt(i+1);
                        i++;
                        num=num+(Character.getNumericValue(x)*0.1);
                        System.out.println("num es "+num);
                        
                        while(i<cadena.length()-1&&numeros.contains(cadena.charAt(i+1))){
                            x=cadena.charAt(i+1);
                            i++;
                            j++;
                            num=num+(Character.getNumericValue(x)*(Math.pow(0.1, j)));
                            System.out.println("num2 es "+num);
                        }
                    }
                    else{
                        if(x=='(')
                            cadenaPrep.add(""+x);
                        else
                            if(x==')')
                                cadenaPrep.add(""+x);
                    }
            }    
            i++;
        }
        System.out.println("num es "+num);
        if(num!=0)
        cadenaPrep.add(""+num);
            
        
        return cadenaPrep;
    }
    
    public static int getPrioridad(Character c){
        int res=-1;
        
        switch(c){
            case '+':
                res=1;
                break; 
                    
            case '-':
                res=1;
                break;
                
            case '*':
                res=2;
                break; 
                    
            case '/':
                res=2;
                break;
                
            case '^':
                res=3;
                break; 
                    
            case '(':
                res=0;
                break; 
                    
            case ')':
                res=0;
                break; 
            
            default:
                res=-1;
        }
    
        return res;
    }
    
    
    
    // PASO 2 Pasar de infija a posfija
    
    // PASO 3 Evaluar infija
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        String cadena="-(2)";
        ArrayList<String> cadenaPrep;
        
        System.out.println("La cadena "+cadena+" es de largo: "+cadena.length());
        
        System.out.println(revisaExpresion(cadena));
        
        cadenaPrep=preparaCadena(cadena);
        
        System.out.println(cadenaPrep.get(0));
        System.out.println(cadenaPrep.get(1));
        System.out.println(cadenaPrep.get(2));
        System.out.println(cadenaPrep.get(3));
    }
    
}
