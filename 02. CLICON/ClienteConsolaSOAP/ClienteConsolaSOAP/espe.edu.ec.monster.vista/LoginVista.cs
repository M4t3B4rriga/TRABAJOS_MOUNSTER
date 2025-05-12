using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using System;

namespace ClienteConsolaSOAP.Vista
{
    public class LoginVista
    {
        public string PedirUsuario()
        {
            Console.Write("Ingrese usuario: ");
            return Console.ReadLine();
        }

        public string PedirContrasena()
        {
            Console.Write("Ingrese contraseña: ");
            return Console.ReadLine();
        }

        public void MostrarMensaje(string mensaje)
        {
            Console.WriteLine(mensaje);
        }
    }
}
