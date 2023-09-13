package proyectocalculadoraed;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.Border;

public class VistaCalculadora extends JFrame {
    
    // atributos de la GUI
    protected JPanel panelBase, panelTexto;
    protected JButton boton1, boton2;
    protected JTextField cuadroTexto;
    protected Border bordeBase, bordeTexto;
    
    // constructor de la GUI
    public VistaCalculadora() {
        this.setTitle("Calculadora ED 1.0");
        this.setSize(300, 150);
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
        panelBase.setLayout(new GridLayout(1, 2));
        panelTexto.setLayout(new GridLayout(1,1) );
        
        // creacion y aplicacion de los bordes en los paneles
        bordeBase = BorderFactory.createEmptyBorder(12, 12, 12, 12);
        bordeTexto = BorderFactory.createEmptyBorder(12, 12, 0, 12);
        panelBase.setBorder(bordeBase);
        panelTexto.setBorder(bordeTexto);
        
        // personalizacion de los botones y del cuadro de texto
        boton1 = new JButton(); boton1.setText("Calcular"); panelBase.add(boton1);
        boton2 = new JButton(); boton2.setText("Limpiar"); panelBase.add(boton2);
        cuadroTexto = new JTextField(); cuadroTexto.setText(""); panelTexto.add(cuadroTexto);

        // organizacion de los paneles por medio de Border Layout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelTexto, BorderLayout.NORTH);
        getContentPane().add(panelBase, BorderLayout.CENTER);
    }
    
    // metodo que brinda la funcionalidad de la GUI
    private void funcionalidad() {
        
        boton1.addActionListener((ActionEvent e) -> {
            String expresion;
            expresion = cuadroTexto.getText();
            PilaADT<String> pila = MetodosDelProyecto.convierteInfijaPostfija(expresion);
            Double resp = MetodosDelProyecto.calculoPostfija(pila);
            cuadroTexto.setText(resp.toString());
        });
        
        boton2.addActionListener((ActionEvent e) -> {
            cuadroTexto.setText("");
        });
    }
}
