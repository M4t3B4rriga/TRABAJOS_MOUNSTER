/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.vista;
import java.util.Scanner;
/**
 *
 * @author sebas
 */
public class ConsolaVista {
    private Scanner scanner = new Scanner(System.in);

    public boolean realizarLogin() {
        System.out.println("=== LOGIN ===");
        System.out.print("Usuario: ");
        String usuario = scanner.nextLine();

        System.out.print("Clave: ");
        String clave = scanner.nextLine();

        return usuario.equals("monster") && clave.equals("monster9");
    }

    public int mostrarMenu() {
        System.out.println("\n=== MENÚ ===");
        System.out.println("1. Celsius a Fahrenheit");
        System.out.println("2. Fahrenheit a Celsius");
        System.out.println("3. Salir");
        System.out.print("Opción: ");
        return scanner.nextInt();
    }

    public double leerCelsius() {
        System.out.print("Ingrese grados Celsius: ");
        return scanner.nextDouble();
    }

    public double leerFahrenheit() {
        System.out.print("Ingrese grados Fahrenheit: ");
        return scanner.nextDouble();
    }

    public void mostrarResultado(String resultado) {
        System.out.println("Resultado: " + resultado);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
