/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.vista;
import javax.swing.*;
/**
 *
 * @author sebas
 */
public class VentanaPrincipal extends JFrame{
     public JTextField campoEntrada;
    public JButton botonConvertir;
    public JLabel etiquetaResultado;
    public JComboBox<String> comboConversion;

    public VentanaPrincipal() {
         // ==== LOGIN QUEMADO ====
        String usuario = JOptionPane.showInputDialog(null, "Usuario:");
        String contrasena = JOptionPane.showInputDialog(null, "Contraseña:");

        if (!usuario.equals("monster") || !contrasena.equals("monster9")) {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas. Cerrando aplicación.");
            System.exit(0);
        }
        
        setTitle("Conversión de Temperatura");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        comboConversion = new JComboBox<>(new String[]{"Celsius a Fahrenheit", "Fahrenheit a Celsius"});
        comboConversion.setBounds(20, 20, 200, 25);
        add(comboConversion);

        campoEntrada = new JTextField();
        campoEntrada.setBounds(20, 60, 150, 25);
        add(campoEntrada);

        botonConvertir = new JButton("Convertir");
        botonConvertir.setBounds(180, 60, 100, 25);
        add(botonConvertir);

        etiquetaResultado = new JLabel("Resultado: ");
        etiquetaResultado.setBounds(20, 100, 300, 25);
        add(etiquetaResultado);
    }
}
