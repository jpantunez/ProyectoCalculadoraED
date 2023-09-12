package proyectocalculadoraed;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.Border;

public class VistaCalculadora extends JFrame {

    protected JPanel panelBase, panelTexto, panelCuadroTexto;
    protected JButton boton1, boton2, boton3, boton4;
    protected JButton boton5, boton6, boton7, boton8;
    protected JButton boton9, boton10, boton11, boton12;
    protected JButton boton13, boton14, boton15, boton16;
    protected JTextField cuadroTexto;
    protected Border bordeBase, bordeTexto;

    public VistaCalculadora() {
        this.setTitle("Calculadora ED 1.0");
        this.setSize(300, 150);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.personalizacion();
        this.funcionalidad();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void personalizacion() {
        panelBase = new JPanel();
        panelTexto = new JPanel();
        panelCuadroTexto = new JPanel();

        panelBase.setLayout(new GridLayout(1, 2));
        panelTexto.setLayout(new GridLayout(1,1) );

        bordeBase = BorderFactory.createEmptyBorder(12, 12, 12, 12);
        bordeTexto = BorderFactory.createEmptyBorder(12, 12, 0, 12);
        panelBase.setBorder(bordeBase);
        panelTexto.setBorder(bordeTexto);
        
        // personalizacion de los botones y del cuadro de texto
        boton1 = new JButton(); boton1.setText("Calcular"); panelBase.add(boton1);
        boton2 = new JButton(); boton2.setText("Limpiar"); panelBase.add(boton2);
        cuadroTexto = new JTextField(); cuadroTexto.setText(""); panelTexto.add(cuadroTexto);

        // Usar BorderLayout para organizar los paneles en el JFrame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelTexto, BorderLayout.NORTH);
        getContentPane().add(panelBase, BorderLayout.CENTER);
    }

    private void funcionalidad() {
        
        boton1.addActionListener((ActionEvent e) -> {
            System.out.println("ola");
            
        });
        
        boton2.addActionListener((ActionEvent e) -> {
            System.out.println("eeeee");
        });
    }
}