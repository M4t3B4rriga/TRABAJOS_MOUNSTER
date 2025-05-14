/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.modelo;

import ec.edu.monster.servicio.ServicioConversion;
import ec.edu.monster.servicio.ServicioConversionService;

import java.net.URL;
import javax.xml.namespace.QName;


/**
 *
 * @author sebas
 */
public class ServicioConversionModelo {
    private ServicioConversion servicio;

    public ServicioConversionModelo() {
        try {
            URL wsdlURL = new URL("http://10.40.26.139:8080/ConUni_Soa_Java_ServidorGR04/ServicioConversionService?wsdl");
            QName SERVICE_QNAME = new QName("http://servicio.monster.edu.ec/", "ServicioConversionService");
            ServicioConversionService servicioService = new ServicioConversionService(wsdlURL, SERVICE_QNAME);
            servicio = servicioService.getServicioConversionPort();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("No se pudo conectar al servicio web. Verifique la URL o si el servidor SOAP está activo.");
        }
    }

    public double convertirCelsiusAFahrenheit(double celsius) {
        return servicio.celsiusToFahrenheit(celsius);
    }

    public double convertirFahrenheitACelsius(double fahrenheit) {
        return servicio.fahrenheitToCelsius(fahrenheit);
    }
}
