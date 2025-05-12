package com.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class LoginWindow extends JFrame{
    private JTextField userField;
    private JPasswordField passField;

    public LoginWindow() {
        setTitle("Login - Conversor");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Usuario:"));
        userField = new JTextField();
        add(userField);

        add(new JLabel("Contraseña:"));
        passField = new JPasswordField();
        add(passField);

        JButton loginBtn = new JButton("Iniciar sesión");
        add(new JLabel());
        add(loginBtn);

        loginBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());

            if (login(user, pass)) {
                JOptionPane.showMessageDialog(this, "Login exitoso ✅");
                dispose(); // Cierra ventana actual
                new MainWindow(); // Abre ventana principal
            } else {
                JOptionPane.showMessageDialog(this, "Login fallido ❌");
            }
        });

        setLocationRelativeTo(null); // Centrar
        setVisible(true);
    }

    public boolean login(String username, String password) {
        try {
            URL url = new URL("http://localhost:8080/temperature-service/api/auth/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String json = String.format("{\"username\":\"%s\",\"password\":\"%s\"}", username, password);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes());
            }

            return conn.getResponseCode() == 200;

        } catch (Exception e) {
            System.out.println("Error login: " + e.getMessage());
        }
        return false;
    }
}
