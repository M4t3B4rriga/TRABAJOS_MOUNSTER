/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.controlador;
import ec.edu.monster.modelo.ServicioConversionModelo;
import ec.edu.monster.vista.ConsolaVista; 
/**
 *
 * @author sebas
 */
public class ConversionControlador {
     private ServicioConversionModelo modelo;
    private ConsolaVista vista;

    public ConversionControlador() {
        modelo = new ServicioConversionModelo();
        vista = new ConsolaVista();
    }

    public void iniciar() {
        if (vista.realizarLogin()) {
            int opcion;
            do {
                opcion = vista.mostrarMenu();
                switch (opcion) {
                    case 1:
                        double celsius = vista.leerCelsius();
                        double f = modelo.celsiusAFahrenheit(celsius);
                        vista.mostrarResultado(f + " °F");
                        break;
                    case 2:
                        double fahrenheit = vista.leerFahrenheit();
                        double c = modelo.fahrenheitACelsius(fahrenheit);
                        vista.mostrarResultado(c + " °C");
                        break;
                    case 3:
                        vista.mostrarMensaje("¡Hasta luego!");
                        break;
                    default:
                        vista.mostrarMensaje("Opción inválida");
                }
            } while (opcion != 3);
        } else {
            vista.mostrarMensaje("Credenciales inválidas ❌");
        }
    }
}
