/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.controlador;
import ec.edu.monster.modelo.ServicioConversionModelo;
import ec.edu.monster.vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author sebas
 */
public class ConversionControlador {
     private VentanaPrincipal vista;
    private ServicioConversionModelo modelo;

    public ConversionControlador(VentanaPrincipal vista, ServicioConversionModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.botonConvertir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertir();
            }
        });
    }

    private void convertir() {
        try {
            double valor = Double.parseDouble(vista.campoEntrada.getText());
            String seleccion = (String) vista.comboConversion.getSelectedItem();

            double resultado;
            if (seleccion.equals("Celsius a Fahrenheit")) {
                resultado = modelo.convertirCelsiusAFahrenheit(valor);
                vista.etiquetaResultado.setText("Resultado: " + resultado + " °F");
            } else {
                resultado = modelo.convertirFahrenheitACelsius(valor);
                vista.etiquetaResultado.setText("Resultado: " + resultado + " °C");
            }
        } catch (NumberFormatException ex) {
            vista.etiquetaResultado.setText("Ingrese un número válido");
        }
    }
}
