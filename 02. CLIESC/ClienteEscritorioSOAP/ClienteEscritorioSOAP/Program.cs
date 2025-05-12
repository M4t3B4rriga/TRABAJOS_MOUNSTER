using System;
using System.Windows.Forms;
using ClienteEscritorioSOAP.espe.edu.ec.monster.vista;

namespace ClienteEscritorioSOAP
{
    internal static class Program
    {
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            // Mostrar el formulario de inicio de sesión
            LoginForm loginForm = new LoginForm();
            Application.Run(loginForm);
        }
    }
}
