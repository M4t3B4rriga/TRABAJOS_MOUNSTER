package ec.edu.monster.con_uni_java_gr04.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import ec.edu.monster.con_uni_java_gr04.R;
import ec.edu.monster.con_uni_java_gr04.controller.TemperatureController;
import ec.edu.monster.con_uni_java_gr04.model.TemperatureConversion;
import ec.edu.monster.con_uni_java_gr04.service.SoapCallback;

public class ConversionActivity extends AppCompatActivity {
    private EditText etCelsius, etFahrenheit;
    private Button btnToFahrenheit, btnToCelsius;
    private TextView tvFahrenheitResult, tvCelsiusResult;
    private TemperatureController temperatureController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        temperatureController = new TemperatureController();

        etCelsius = findViewById(R.id.etCelsius);
        etFahrenheit = findViewById(R.id.etFahrenheit);
        btnToFahrenheit = findViewById(R.id.btnToFahrenheit);
        btnToCelsius = findViewById(R.id.btnToCelsius);
        tvFahrenheitResult = findViewById(R.id.tvFahrenheitResult);
        tvCelsiusResult = findViewById(R.id.tvCelsiusResult);

        btnToFahrenheit.setOnClickListener(v -> convertCelsiusToFahrenheit());
        btnToCelsius.setOnClickListener(v -> convertFahrenheitToCelsius());
    }

    private void convertCelsiusToFahrenheit() {
        String celsiusStr = etCelsius.getText().toString();
        if (celsiusStr.isEmpty()) {
            Toast.makeText(this, "Ingrese un valor en Celsius", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double celsius = Double.parseDouble(celsiusStr);
            TemperatureConversion conversion = new TemperatureConversion(celsius, "C", "F");

            temperatureController.convertCelsiusToFahrenheit(conversion, new SoapCallback<Double>() {
                @Override
                public void onSuccess(Double result) {
                    runOnUiThread(() -> {
                        tvFahrenheitResult.setText(String.format("%.2f °F", result));
                        tvFahrenheitResult.setTextColor(ContextCompat.getColor(ConversionActivity.this, R.color.success));
                    });
                }

                @Override
                public void onError(String error) {
                    runOnUiThread(() -> {
                        tvFahrenheitResult.setText(error);
                        tvFahrenheitResult.setTextColor(ContextCompat.getColor(ConversionActivity.this, R.color.error));
                        Toast.makeText(ConversionActivity.this, "Error en conversión: " + error, Toast.LENGTH_LONG).show();
                    });
                }
            });
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingrese un valor numérico válido", Toast.LENGTH_SHORT).show();
        }
    }

    private void convertFahrenheitToCelsius() {
        String fahrenheitStr = etFahrenheit.getText().toString();
        if (fahrenheitStr.isEmpty()) {
            Toast.makeText(this, "Ingrese un valor en Fahrenheit", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double fahrenheit = Double.parseDouble(fahrenheitStr);
            TemperatureConversion conversion = new TemperatureConversion(fahrenheit, "F", "C");

            temperatureController.convertFahrenheitToCelsius(conversion, new SoapCallback<Double>() {
                @Override
                public void onSuccess(Double result) {
                    runOnUiThread(() -> {
                        tvCelsiusResult.setText(String.format("%.2f °C", result));
                        tvCelsiusResult.setTextColor(ContextCompat.getColor(ConversionActivity.this, R.color.success));
                    });
                }

                @Override
                public void onError(String error) {
                    runOnUiThread(() -> {
                        tvCelsiusResult.setText(error);
                        tvCelsiusResult.setTextColor(ContextCompat.getColor(ConversionActivity.this, R.color.error));
                        Toast.makeText(ConversionActivity.this, "Error en conversión: " + error, Toast.LENGTH_LONG).show();
                    });
                }
            });
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingrese un valor numérico válido", Toast.LENGTH_SHORT).show();
        }
    }
}