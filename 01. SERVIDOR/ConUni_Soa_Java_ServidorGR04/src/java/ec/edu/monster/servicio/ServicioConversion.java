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
import ec.edu.monster.modelo.Usuarios;

@WebService
public class ServicioConversion {
    // Usuario quemado
    private final Usuarios usuarioValido = new Usuarios("monster", "monster9");

    @WebMethod
    public boolean login(String username, String password) {
        return usuarioValido.validar(username, password);
    }
    @WebMethod
    public double celsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }

    @WebMethod
    public double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }
}
