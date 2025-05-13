/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.servicio;

/**
 *
 * @author sebas
 */

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public class ServicioConversion {
    @WebMethod
    public double celsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }

    @WebMethod
    public double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }
}
