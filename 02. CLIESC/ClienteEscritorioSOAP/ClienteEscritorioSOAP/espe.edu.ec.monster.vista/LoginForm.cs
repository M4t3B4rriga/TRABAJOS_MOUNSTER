using System;
using System.Windows.Forms;
using ClienteEscritorioSOAP.espe.edu.ec.monster.controlador;

namespace ClienteEscritorioSOAP.espe.edu.ec.monster.vista
{
    public partial class LoginForm : Form
    {
        private LoginControlador loginControlador;

        public LoginForm()
        {
            InitializeComponent();
            loginControlador = new LoginControlador();
        }

        private void label1_Click(object sender, EventArgs e)
        {
            // Este evento puede ser eliminado si no es necesario.
        }

        private void txtUsuario_TextChanged(object sender, EventArgs e)
        {
            // Puedes manejar eventos de cambio de texto aquí si es necesario.
        }

        private void contraseña_Click(object sender, EventArgs e)
        {
            // Este evento puede ser eliminado si no es necesario.
        }

        private void txtContrasena_TextChanged(object sender, EventArgs e)
        {
            // Puedes manejar eventos de cambio de texto aquí si es necesario.
        }

        private void btnIngresar_Click(object sender, EventArgs e)
        {
            string usuario = txtUsuario.Text;
            string clave = txtContrasena.Text;

            if (loginControlador.VerificarCredenciales(usuario, clave))
            {
                MessageBox.Show("Inicio de sesión exitoso", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                ConversiónUnidades formPrincipal = new ConversiónUnidades();
                formPrincipal.Show();
                this.Hide(); // Oculta el formulario de inicio de sesión
            }
            else
            {
                MessageBox.Show("Usuario o contraseña incorrectos", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void LoginForm_Load(object sender, EventArgs e)
        {
            // Puedes inicializar configuraciones aquí si es necesario.
        }
    }
}
