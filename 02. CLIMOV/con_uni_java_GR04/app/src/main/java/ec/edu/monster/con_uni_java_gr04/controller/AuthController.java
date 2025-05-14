package ec.edu.monster.con_uni_java_gr04.controller;

import android.util.Log;

import java.util.HashMap;

import ec.edu.monster.con_uni_java_gr04.model.User;
import ec.edu.monster.con_uni_java_gr04.service.SoapCallback;
import ec.edu.monster.con_uni_java_gr04.service.SoapService;

public class AuthController {
    private final SoapService soapService;

    public AuthController() {
        this.soapService = new SoapService();
    }

    public void login(User user, SoapCallback<Boolean> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("arg0", user.getUsername());
        params.put("arg1", user.getPassword());

        Log.d("AuthController", "Parámetros enviados: " + params.toString());

        soapService.callMethod("login", params, new SoapCallback<Object>() {
            @Override
            public void onSuccess(Object result) {
                Log.d("AuthController", "Respuesta bruta del servicio: " + result.toString());
                boolean loginResult = Boolean.parseBoolean(result.toString());
                callback.onSuccess((Boolean) result);
            }

            @Override
            public void onError(String error) {
                Log.e("AuthController", "Error en login: " + error);
                callback.onError(error);
            }
        });
    }
}