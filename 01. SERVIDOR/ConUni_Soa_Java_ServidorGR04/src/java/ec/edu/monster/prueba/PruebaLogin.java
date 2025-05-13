/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.prueba;
import ec.edu.monster.modelo.Usuarios;
/**
 *
 * @author sebas
 */
public class PruebaLogin {
    public static void main(String[] args) {
        Usuarios usuario = new Usuarios("admin", "1234");
        if (usuario.validar("admin", "1234")) {
            System.out.println("Login correcto");
        } else {
            System.out.println("Login fallido");
        }
    }
}
