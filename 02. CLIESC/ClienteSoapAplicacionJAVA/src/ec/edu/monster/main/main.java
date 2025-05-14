/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.main;
import ec.edu.monster.vista.VentanaPrincipal;
import ec.edu.monster.modelo.ServicioConversionModelo;
import ec.edu.monster.controlador.ConversionControlador;

/**
 *
 * @author sebas
 */
public class main {                         
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {    
            VentanaPrincipal vista = new VentanaPrincipal();
            ServicioConversionModelo modelo = new ServicioConversionModelo();
            new ConversionControlador(vista, modelo);
            vista.setVisible(true);
        });
    }
}
