package com.client;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class MainWindow extends JFrame{
    private JTextField valorField;
    private JComboBox<String> tipoCombo;
    private JLabel resultadoLabel;

    public MainWindow() {
        setTitle("Conversión de Temperatura");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Valor:"));
        valorField = new JTextField();
        add(valorField);

        add(new JLabel("Tipo de conversión:"));
        tipoCombo = new JComboBox<>(new String[] {
            "Celsius a Fahrenheit", "Fahrenheit a Celsius"
        });
        add(tipoCombo);

        JButton convertirBtn = new JButton("Convertir");
        add(convertirBtn);
        resultadoLabel = new JLabel("Resultado: ");
        add(resultadoLabel);

        convertirBtn.addActionListener(e -> {
            try {
                double valor = Double.parseDouble(valorField.getText());
                String tipo = (String) tipoCombo.getSelectedItem();
                if (tipo.equals("Celsius a Fahrenheit")) {
                    convertir("toFahrenheit", "celsius", valor);
                } else {
                    convertir("toCelsius", "fahrenheit", valor);
                }
            } catch (NumberFormatException ex) {
                resultadoLabel.setText("⚠️ Valor inválido");
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void convertir(String endpoint, String param, double valor) {
        try {
            String urlStr = "http://10.40.26.120:8080/temperature-service/api/temperature/" +
                            endpoint + "?" + param + "=" + valor;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String json = in.readLine();
                resultadoLabel.setText("Resultado: " + json);
            }

        } catch (Exception e) {
            resultadoLabel.setText("❌ Error: " + e.getMessage());
        }
    }
}
