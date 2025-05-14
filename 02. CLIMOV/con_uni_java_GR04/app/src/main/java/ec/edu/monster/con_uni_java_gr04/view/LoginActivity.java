package ec.edu.monster.con_uni_java_gr04.view;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import ec.edu.monster.con_uni_java_gr04.R;

import ec.edu.monster.con_uni_java_gr04.controller.AuthController;
import ec.edu.monster.con_uni_java_gr04.service.SoapCallback;
import ec.edu.monster.con_uni_java_gr04.model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private Button btnLogin;
    private AuthController authController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final boolean[] isLoginToastShown = {false};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authController = new AuthController();

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Crear el objeto User para pasar al controlador
            User user = new User(username, password);

            // Agregar log para verificar las credenciales antes de enviarlas
            Log.d("LoginActivity", "Enviando usuario: " + user.getUsername());
            Log.d("LoginActivity", "Enviando contraseña: " + user.getPassword());


            // Llamar al método de login en el controlador
            authController.login(user, new SoapCallback<Boolean>() {

                @Override
                public void onSuccess(Boolean result) {
                    runOnUiThread(() -> {
                        if (result) {
                            Toast.makeText(LoginActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();

                            // Redirigir a ConversionActivity
                            Intent intent = new Intent(LoginActivity.this, ConversionActivity.class);
                            startActivity(intent);

                            // Cerrar la actividad actual para que no se pueda volver atrás con el botón back
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                @Override
                public void onError(String error) {
                    runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show());
                }
            });
        });
    }
}