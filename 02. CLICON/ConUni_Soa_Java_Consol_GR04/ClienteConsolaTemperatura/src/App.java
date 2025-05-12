
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class App {

    static String token = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

      // LOGIN CON ESTILO
        boolean autenticado = false;
        while (!autenticado) {
            System.out.println("╔══════════════════════════════╗");
            System.out.println("║        LOGIN SISTEMA         ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Usuario: ");
            String username = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();

            if (login(username, password)) {
                System.out.println("✅ Login exitoso");
                autenticado = true;
            } else {
                System.out.println("❌ Login fallido. Intente de nuevo.\n");
            }
        }

        // MENÚ PRINCIPAL REPETITIVO
        int opcion;
        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║     MENÚ DE CONVERSIONES     ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║ 1. Celsius a Fahrenheit      ║");
            System.out.println("║ 2. Fahrenheit a Celsius      ║");
            System.out.println("║ 3. Salir                     ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese valor en Celsius: ");
                    double celsius = scanner.nextDouble();
                    convertirCelsiusAFahrenheit(celsius);
                    break;
                case 2:
                    System.out.print("Ingrese valor en Fahrenheit: ");
                    double fahrenheit = scanner.nextDouble();
                    convertirFahrenheitACelsius(fahrenheit);
                    break;
                case 3:
                    System.out.println("👋 Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("⚠️ Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 3);

        scanner.close();
    }





    public static boolean login(String username, String password) {
        try {
            URL url = new URL("http://10.40.26.120:8080/temperature-service/api/auth/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInput = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonInput.getBytes());
            }

            if (conn.getResponseCode() == 200) {
                token = "login_ok";
                return true;
            }

        } catch (Exception e) {
            System.out.println("❌ Error en login: " + e.getMessage());
        }
        return false;
    }

    public static void convertirCelsiusAFahrenheit(double celsius) {
        try {
            URL url = new URL("http://10.40.26.120:8080/temperature-service/api/temperature/toFahrenheit?celsius=" + celsius);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("✅ Resultado: " + line);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error en conversión: " + e.getMessage());
        }
    }

    public static void convertirFahrenheitACelsius(double fahrenheit) {
        try {
            URL url = new URL("http://10.40.26.120:8080/temperature-service/api/temperature/toCelsius?fahrenheit=" + fahrenheit);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("✅ Resultado: " + line);
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error en conversión: " + e.getMessage());
        }
    }
}
