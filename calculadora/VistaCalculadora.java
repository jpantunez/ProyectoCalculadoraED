package proyectocalculadoraed;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class VistaCalculadora extends JFrame {
    
    // atributos de la GUI
    private JPanel panelBase, panelTexto;
    private JButton boton1, boton2, boton3, boton4;
    private JButton boton5, boton6, boton7, boton8;
    private JButton boton9, boton10, boton11, boton12;
    private JButton boton13, boton14, boton15, boton16;
    private JButton boton17, boton18, boton19, boton20;
    private JTextField cuadroTexto;
    private Border bordeBase, bordeTexto;
    protected static boolean syntaxErrorOccurred = false;
    
    // constructor de la GUI
    public VistaCalculadora(){
        this.setTitle("Calculadora ED 1.0");
        this.setSize(300, 350);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.personalizacion();
        this.funcionalidad();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    // metodo que brinda la personalizacion de la GUI
    private void personalizacion() {
        // creacion de paneles y asignacion de GridLayout
        panelBase = new JPanel();
        panelTexto = new JPanel();
        panelBase.setLayout(new GridLayout(5, 4));
        panelTexto.setLayout(new GridLayout(1,1) );
        
        // creacion y aplicacion de los bordes en los paneles
        bordeBase = BorderFactory.createEmptyBorder(12, 12, 12, 12);
        bordeTexto = BorderFactory.createEmptyBorder(12, 12, 0, 12);
        panelBase.setBorder(bordeBase);
        panelTexto.setBorder(bordeTexto);
        
        // personalizacion de los botones y del cuadro de texto
        boton1 = new JButton(); boton1.setText("C"); panelBase.add(boton1);
        boton2 = new JButton(); boton2.setText("⌫"); panelBase.add(boton2); // borrar ultimo caracter
        boton3 = new JButton(); boton3.setText("^"); panelBase.add(boton3);
        boton4 = new JButton(); boton4.setText("*"); panelBase.add(boton4);
        boton5 = new JButton(); boton5.setText("7"); panelBase.add(boton5);
        boton6 = new JButton(); boton6.setText("8"); panelBase.add(boton6);
        boton7 = new JButton(); boton7.setText("9"); panelBase.add(boton7);
        boton8 = new JButton(); boton8.setText("/"); panelBase.add(boton8);
        boton9 = new JButton(); boton9.setText("4"); panelBase.add(boton9);
        boton10 = new JButton(); boton10.setText("5"); panelBase.add(boton10);
        boton11 = new JButton(); boton11.setText("6"); panelBase.add(boton11);
        boton12 = new JButton(); boton12.setText("-"); panelBase.add(boton12);
        boton13 = new JButton(); boton13.setText("1"); panelBase.add(boton13);
        boton14 = new JButton(); boton14.setText("2"); panelBase.add(boton14);
        boton15 = new JButton(); boton15.setText("3"); panelBase.add(boton15);
        boton16 = new JButton(); boton16.setText("+"); panelBase.add(boton16);
        boton17 = new JButton(); boton17.setText("0"); panelBase.add(boton17);
        boton18 = new JButton(); boton18.setText("("); panelBase.add(boton18);
        boton19 = new JButton(); boton19.setText(")"); panelBase.add(boton19);
        boton20 = new JButton(); boton20.setText("="); panelBase.add(boton20);
        cuadroTexto = new JTextField(); cuadroTexto.setText(""); panelTexto.add(cuadroTexto);
        cuadroTexto.setEditable(false);

        // organizacion de los paneles usando BorderLayout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelTexto, BorderLayout.NORTH);
        getContentPane().add(panelBase, BorderLayout.CENTER);
    }
    
    // metodo que brinda la funcionalidad de la GUI
    private void funcionalidad() {
        
        // funcionalidad de los botones
        boton1.addActionListener((ActionEvent e) -> {
            cuadroTexto.setText("");
            syntaxErrorOccurred = false; // Restablece la variable de control de error
            boton20.setEnabled(true); // Habilita el boton de calcular despues de elimminar el contenido
        });
        
        boton2.addActionListener((ActionEvent e) -> {
            String p=cuadroTexto.getText().substring(0, cuadroTexto.getText().length() - 1);
            cuadroTexto.setText(p);
            syntaxErrorOccurred = false; // Restablece la variable de control de error
        });
        
        boton3.addActionListener((ActionEvent e) -> {
            String p=cuadroTexto.getText();
            cuadroTexto.setText(p+"^");
            syntaxErrorOccurred = false; // Restablece la variable de control de error
        });
        
        boton4.addActionListener((ActionEvent e) -> {
            String p=cuadroTexto.getText();
            cuadroTexto.setText(p+"*");
            syntaxErrorOccurred = false; // Restablece la variable de control de error
        });
        
        boton5.addActionListener((ActionEvent e) -> {
            String p=cuadroTexto.getText();
            cuadroTexto.setText(p+"7");
            syntaxErrorOccurred = false; // Restablece la variable de control de error
        });
        
        boton6.addActionListener((ActionEvent e) -> {
            String p=cuadroTexto.getText();
            cuadroTexto.setText(p+"8");
            syntaxErrorOccurred = false; // Restablece la variable de control de error
        });
        
        boton7.addActionListener((ActionEvent e) -> {
            String p=cuadroTexto.getText();
            cuadroTexto.setText(p+"9");
            syntaxErrorOccurred = false; // Restablece la variable de control de error
        });
        
        boton8.addActionListener((ActionEvent e) -> {
            String p=cuadroTexto.getText();
            cuadroTexto.setText(p+"/");
            syntaxErrorOccurred = false; // Restablece la variable de control de error
        });
        
        boton9.addActionListener((ActionEvent e) -> {
            String p=cuadroTexto.getText();
            cuadroTexto.setText(p+"4");
            syntaxErrorOccurred = false; // Restablece la variable de control de error
        });
        
        boton10.addActionListener((ActionEvent e) -> {
            String p=cuadroTexto.getText();
            cuadroTexto.setText(p+"5");
            syntaxErrorOccurred = false; // Restablece la variable de control de error
        });
        
        boton11.addActionListener((ActionEvent e) -> {
            String p=cuadroTexto.getText();
            cuadroTexto.setText(p+"6");
            syntaxErrorOccurred = false; // Restablece la variable de control de error
        });
        
        boton12.addActionListener((ActionEvent e) -> {
            String p=cuadroTexto.getText();
            cuadroTexto.setText(p+"-");
            syntaxErrorOccurred = false; // Restablece la variable de control de error
        });
        
        boton13.addActionListener((ActionEvent e) -> {
            String p=cuadroTexto.getText();
            cuadroTexto.setText(p+"1");
            syntaxErrorOccurred = false; // Restablece la variable de control de error
        });
        
        boton14.addActionListener((ActionEvent e) -> {
            String p=cuadroTexto.getText();
            cuadroTexto.setText(p+"2");
            syntaxErrorOccurred = false; // // Restablece la variable de control de error
        });
        
        boton15.addActionListener((ActionEvent e) -> {
            String p=cuadroTexto.getText();
            cuadroTexto.setText(p+"3");
            syntaxErrorOccurred = false; // Restablece la variable de control de error
        });
        
        boton16.addActionListener((ActionEvent e) -> {
            String p=cuadroTexto.getText();
            cuadroTexto.setText(p+"+");
            syntaxErrorOccurred = false; // Restablece la variable de control de error
        });
        
        boton17.addActionListener((ActionEvent e) -> {
            String p=cuadroTexto.getText();
            cuadroTexto.setText(p+"0");
            syntaxErrorOccurred = false; // Restablece la variable de control de error
        });
        
        boton18.addActionListener((ActionEvent e) -> {
            String p=cuadroTexto.getText();
            cuadroTexto.setText(p+"(");
            syntaxErrorOccurred = false; // Restablece la variable de control de error
        });
        
        boton19.addActionListener((ActionEvent e) -> {
            String p=cuadroTexto.getText();
            cuadroTexto.setText(p+")");
            syntaxErrorOccurred = false; // Restablece la variable de control de error
        });
        
        boton20.addActionListener((ActionEvent e) -> {
            
            String expresion = cuadroTexto.getText();
            PilaADT<String> pila = MetodosDelProyecto.convierteInfijaPostfija(expresion);
            if (!syntaxErrorOccurred) { // Verifica si no se ha producido un error de sintaxis
                try {
                    Double resp = MetodosDelProyecto.calculoPostfija(pila);
                    cuadroTexto.setText(resp.toString());
                }
                catch (Exception ex) {
                    cuadroTexto.setText("Syntax Error");
                    syntaxErrorOccurred = true; // Establece la variable de control en true
                }
            }
            else{
                cuadroTexto.setText("Syntax Error");
            }
        });
        
        cuadroTexto.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkForError();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkForError();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // no es necesario su uso, pero si su implementacion en el codigo
            }

            private void checkForError() {
                String texto = cuadroTexto.getText();
                if (texto.contains("Syntax Error")) {
                    System.out.println("Syntax Error.");
                    boton20.setEnabled(false);
                }
            }
        });
    }
}
