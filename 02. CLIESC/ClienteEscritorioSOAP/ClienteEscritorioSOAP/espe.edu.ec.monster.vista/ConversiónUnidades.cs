using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using ClienteEscritorioSOAP.ServicioConversion;
using static System.Net.Mime.MediaTypeNames;

namespace ClienteEscritorioSOAP.espe.edu.ec.monster.vista
{
    public partial class ConversiónUnidades : Form
    {
        private readonly ConversionServiceClient servicio;

        public ConversiónUnidades()
        {
            InitializeComponent();
            servicio = new ConversionServiceClient();

            // Llenar opciones al iniciar
            cmbConversion.Items.AddRange(new string[]
            {
                "Centímetros a Pies",
                "Pies a Centímetros",
                "Metros a Yardas",
                "Yardas a Metros",
                "Pulgadas a Centímetros",
                "Centímetros a Pulgadas"
            });

            cmbConversion.SelectedIndex = 0; // opción por defecto
        }

        private void lblTexUni_Click(object sender, EventArgs e)
        {
            // Método vacío, no se modifica.
        }

        private void ConversiónUnidades_Load(object sender, EventArgs e)
        {
            // Método vacío, no se modifica.
        }

        private void lblSelecOp_Click(object sender, EventArgs e)
        {
            // Método vacío, no se modifica.
        }

        private void cmbConversion_SelectedIndexChanged(object sender, EventArgs e)
        {
            // Método vacío, no se modifica.
        }

        private void lblValor_Click(object sender, EventArgs e)
        {
            // Método vacío, no se modifica.
        }

        private void txtValor_TextChanged(object sender, EventArgs e)
        {
            // Método vacío, no se modifica.
        }

        private void btnConvertir_Click(object sender, EventArgs e)
        {
            if (!double.TryParse(txtValor.Text.Trim(), out double valor))
            {
                MessageBox.Show("Por favor ingrese un número válido.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            double resultado = 0;

            switch (cmbConversion.SelectedIndex)
            {
                case 0: resultado = servicio.CentimetersToFeet(valor); break;
                case 1: resultado = servicio.FeetToCentimeters(valor); break;
                case 2: resultado = servicio.MetersToYards(valor); break;
                case 3: resultado = servicio.YardsToMeters(valor); break;
                case 4: resultado = servicio.InchesToCentimeters(valor); break;
                case 5: resultado = servicio.CentimetersToInches(valor); break;
                default:
                    MessageBox.Show("Seleccione una opción de conversión.", "Aviso", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    return;
            }

            txtRest.Text = resultado.ToString("F4");
        }

        private void lblResultado_Click(object sender, EventArgs e)
        {
            // Método vacío, no se modifica.
        }

        private void txtRest_TextChanged(object sender, EventArgs e)
        {
            // Método vacío, no se modifica.
        }

        private void btnSalir_Click(object sender, EventArgs e)
        {
            
        }
    }
}
