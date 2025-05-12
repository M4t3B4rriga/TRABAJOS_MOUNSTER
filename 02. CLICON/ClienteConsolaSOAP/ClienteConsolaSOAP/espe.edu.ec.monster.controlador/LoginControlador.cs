using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ClienteConsolaSOAP.Modelo;
using ClienteConsolaSOAP.Vista;
using ClienteConsolaSOAP.Utils;

namespace ClienteConsolaSOAP.Controlador
{
    public class LoginControlador
    {
        private readonly LoginVista vista;

        public LoginControlador()
        {
            vista = new LoginVista();
        }

        public bool IniciarSesion()
        {
            string usuario = vista.PedirUsuario();
            string contrasena = vista.PedirContrasena();

            if (usuario == "MONSTER" && contrasena == "MONSTER9")
            {
                SessionManager.Instance.UsuarioActual = new Usuario { Nombre = usuario, Contrasena = contrasena };
                vista.MostrarMensaje("Inicio de sesión exitoso.");
                return true;
            }
            else
            {
                vista.MostrarMensaje("Credenciales incorrectas.");
                return false;
            }
        }
    }
}
