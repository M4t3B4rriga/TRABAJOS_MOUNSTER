package controller;

import view.ConsoleView;
import model.TemperatureService;


public class MainController {
     private final ConsoleView vista = new ConsoleView();
    private final TemperatureService servicio = new TemperatureService();

    public void iniciarAplicacion() {
        boolean autenticado = false;

        while (!autenticado) {
            vista.mostrarMensaje("╔══════════════════════════════╗");
            vista.mostrarMensaje("║        LOGIN SISTEMA         ║");
            vista.mostrarMensaje("╚══════════════════════════════╝");
            String usuario = vista.pedirUsuario();
            String contrasena = vista.pedirContrasena();

            if (servicio.login(usuario, contrasena)) {
                vista.mostrarMensaje("✅ Login exitoso");
                autenticado = true;
            } else {
                vista.mostrarMensaje("❌ Login fallido. Intente de nuevo.\n");
            }
        }

        int opcion;
        do {
            opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1:
                    double celsius = vista.pedirTemperatura("Celsius");
                    vista.mostrarResultado(servicio.convertirCelsiusAFahrenheit(celsius));
                    break;
                case 2:
                    double fahrenheit = vista.pedirTemperatura("Fahrenheit");
                    vista.mostrarResultado(servicio.convertirFahrenheitACelsius(fahrenheit));
                    break;
                case 3:
                    vista.mostrarMensaje("👋 Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    vista.mostrarMensaje("⚠️ Opción no válida.");
            }
        } while (opcion != 3);
    }
}
