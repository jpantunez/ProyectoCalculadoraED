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
public abstract class Calculadora {

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
        int i=0,j=0;
        ArrayList<Character>numeros = new ArrayList<>(List.of('0','1','2','3','4','5','6','7','8','9'));
        ArrayList<String> cadenaPrep = new ArrayList();
        Character x;
        String y="";
        double num=0.0;
        boolean banderaNegativo=false, banderaParentesis=false;
        
        //poner un if de operador de - o + si el primer char de la cadena es un operador
        /*if(cadena.charAt(0)=='-'&&numeros.contains(cadena.charAt(1)))
            banderaNegativo=true;
        if(cadena.charAt(0)=='-'&&cadena.charAt(i+1)=='(')
            banderaParentesis=true;
        
        */
        
        if(i==cadena.length()-1)
            throw new ExceptionCollection("SINTAX ERROR");
        
        while(i<=cadena.length()-1){
            x=cadena.charAt(i);
            
            if(esOperador(x)){
                if(num!=0){
                    cadenaPrep.add(""+(-num));
                    num=0;
                    
                }
                cadenaPrep.add(""+x);
            }
            else{
                if(numeros.contains(x))
                    num=(num*10)+(Character.getNumericValue(x));
                else
                    if(x=='.'&&numeros.contains(cadena.charAt(i+1))){
                        num=num+((Character.getNumericValue(cadena.charAt(i+1)))*Math.pow(10, -j));
                        }
                while(i<cadena.length()-2&&numeros.contains(cadena.charAt(i+1))){
                    num=(num*10)+(Character.getNumericValue(x));
                    i++;
                    x=cadena.charAt(i);
                    }

                    
                }
            
            /*
            if(x=='('||banderaParentesis){
                if(banderaParentesis)
                    y=y+""+x;
                while(i<cadena.length()-1&&x!=')'){
                    i++;
                    x=cadena.charAt(i);
                    y=y+""+x;
                }
                cadenaPrep.add(""+y);
                banderaParentesis=false;
            }
            */
            i++;
        }
        
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
        
        /*String cadena="ab";
        Character caracter=cadena.charAt(1);
        ArrayList<Character>numeros = new ArrayList<>(List.of('0','1','2','3','4','5','6','7','8','9'));
        System.out.println(numeros.contains(caracter));
        */
        
        String cadena = "122", cadena2="--.28", cadena3="(((0)", cadena4="-(21*2)";
        ArrayList<String> cadenaPrep;
        
        
        System.out.println("La cadena "+cadena+" es de largo: "+cadena.length());
        /*System.out.println(cadena2);
        System.out.println(cadena3);
        System.out.println(cadena4);
        
        */
        System.out.println(revisaExpresion(cadena));
        /*System.out.println(revisaExpresion(cadena2));
        System.out.println(revisaExpresion(cadena3));
        System.out.println(revisaExpresion(cadena4));
       
        */
        cadenaPrep=preparaCadena(cadena);
        
        System.out.println(cadenaPrep.get(0));
        //System.out.println(cadenaPrep.get(1));
        //System.out.println(cadenaPrep.get(2));
        
        //System.out.println(cadenaPrep.get(3));
        //System.out.println(cadenaPrep.get(4));
        
        
    }
    
}
