using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using espe.edu.ec.monster.controlador;
using ClienteConsolaSOAP.Controlador;

namespace ClienteConsolaSOAP
{
    class Program
    {
        static void Main(string[] args)
        {
            var login = new LoginControlador();

            if (login.IniciarSesion())
            {
                var conversiones = new ConversionControlador();
                conversiones.MostrarMenu();
            }

            Console.WriteLine("\nGracias por usar el sistema. Presione una tecla para salir...");
            Console.ReadKey();
        }
    }
}
