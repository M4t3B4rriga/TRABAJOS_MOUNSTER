package view;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner = new Scanner(System.in);

    public String pedirUsuario() {
        System.out.print("Usuario: ");
        return scanner.nextLine();
    }

    public String pedirContrasena() {
        System.out.print("Contraseña: ");
        return scanner.nextLine();
    }

    public int mostrarMenu() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║     MENÚ DE CONVERSIONES     ║");
        System.out.println("╠══════════════════════════════╣");
        System.out.println("║ 1. Celsius a Fahrenheit      ║");
        System.out.println("║ 2. Fahrenheit a Celsius      ║");
        System.out.println("║ 3. Salir                     ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.print("Seleccione una opción: ");
        return scanner.nextInt();
    }

    public double pedirTemperatura(String tipo) {
        System.out.print("Ingrese valor en " + tipo + ": ");
        return scanner.nextDouble();
    }

    public void mostrarResultado(String resultado) {
        System.out.println("✅ Resultado: " + resultado);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
