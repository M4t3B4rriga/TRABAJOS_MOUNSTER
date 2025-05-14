/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.modelo;
import clientesoapconsolajava.ServicioConversion;
import clientesoapconsolajava.ServicioConversionService;

/**
 *
 * @author sebas
 */
public class ServicioConversionModelo {
    private ServicioConversion servicio;

    public ServicioConversionModelo() {
        ServicioConversionService service = new ServicioConversionService();
        servicio = service.getServicioConversionPort();
    }

    public double celsiusAFahrenheit(double celsius) {
        return servicio.celsiusToFahrenheit(celsius);
    }

    public double fahrenheitACelsius(double fahrenheit) {
        return servicio.fahrenheitToCelsius(fahrenheit);
    }
}
