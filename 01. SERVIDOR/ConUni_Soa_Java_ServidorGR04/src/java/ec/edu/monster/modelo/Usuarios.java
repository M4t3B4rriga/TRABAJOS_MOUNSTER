/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.modelo;

/**
 *
 * @author sebas
 */
public class Usuarios {
    private String username;
    private String password;

    public Usuarios(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean validar(String user, String pass) {
        return this.username.equals(user) && this.password.equals(pass);
    }
}
