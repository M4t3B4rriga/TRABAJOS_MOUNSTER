package ec.edu.monster.con_uni_java_gr04.controller;

import java.util.HashMap;

import ec.edu.monster.con_uni_java_gr04.model.TemperatureConversion;
import ec.edu.monster.con_uni_java_gr04.service.SoapCallback;
import ec.edu.monster.con_uni_java_gr04.service.SoapService;

public class TemperatureController {
    private final SoapService soapService;

    public TemperatureController() {
        this.soapService = new SoapService();
    }

    public void convertCelsiusToFahrenheit(TemperatureConversion conversion, SoapCallback<Double> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("arg0", String.valueOf(conversion.getValue()));

        soapService.callMethod("celsiusToFahrenheit", params, new SoapCallback<Object>() {
            @Override
            public void onSuccess(Object result) {
                callback.onSuccess((Double) result);
            }

            @Override
            public void onError(String error) {
                callback.onError(error);
            }
        });
    }

    public void convertFahrenheitToCelsius(TemperatureConversion conversion, SoapCallback<Double> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("arg0", String.valueOf(conversion.getValue()));

        soapService.callMethod("fahrenheitToCelsius", params, new SoapCallback<Object>() {
            @Override
            public void onSuccess(Object result) {
                callback.onSuccess((Double) result);
            }

            @Override
            public void onError(String error) {
                callback.onError(error);
            }
        });
    }
}