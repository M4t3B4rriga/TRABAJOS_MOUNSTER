using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using ClienteEscritorioSOAP.espe.edu.ec.monster.modelo;
using ClienteEscritorioSOAP.espe.edu.ec.monster.utils;

namespace ClienteEscritorioSOAP.espe.edu.ec.monster.controlador 
{
    public class LoginControlador
    {
        public bool VerificarCredenciales(string usuario, string clave)
        {
            if (usuario == "MONSTER" && clave == "MONSTER9")
            {
                SessionManager.Instance.UsuarioActual = new Usuario { Nombre = usuario, Contrasena = clave };
                return true;
            }
            return false;
        }
    }
}
