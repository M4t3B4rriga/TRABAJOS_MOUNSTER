using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ClienteConsolaSOAP.ServicioConversion;
using System;

namespace espe.edu.ec.monster.controlador
{
    public class ConversionControlador
    {
        private readonly ConversionServiceClient servicio;

        public ConversionControlador()
        {
            servicio = new ConversionServiceClient();
        }

        public void MostrarMenu()
        {
            int opcion;
            do
            {
                Console.Clear();
                Console.WriteLine("=== CONVERSIÓN DE UNIDADES ===");
                Console.WriteLine("1. Centímetros a Pies");
                Console.WriteLine("2. Pies a Centímetros");
                Console.WriteLine("3. Metros a Yardas");
                Console.WriteLine("4. Yardas a Metros");
                Console.WriteLine("5. Pulgadas a Centímetros");
                Console.WriteLine("6. Centímetros a Pulgadas");
                Console.WriteLine("0. Salir");
                Console.Write("Seleccione una opción: ");

                if (!int.TryParse(Console.ReadLine(), out opcion)) continue;

                double valor;
                double resultado;

                switch (opcion)
                {
                    case 1:
                        valor = PedirValor("centímetros");
                        resultado = servicio.CentimetersToFeet(valor);
                        MostrarResultado(valor, resultado, "pies");
                        break;
                    case 2:
                        valor = PedirValor("pies");
                        resultado = servicio.FeetToCentimeters(valor);
                        MostrarResultado(valor, resultado, "centímetros");
                        break;
                    case 3:
                        valor = PedirValor("metros");
                        resultado = servicio.MetersToYards(valor);
                        MostrarResultado(valor, resultado, "yardas");
                        break;
                    case 4:
                        valor = PedirValor("yardas");
                        resultado = servicio.YardsToMeters(valor);
                        MostrarResultado(valor, resultado, "metros");
                        break;
                    case 5:
                        valor = PedirValor("pulgadas");
                        resultado = servicio.InchesToCentimeters(valor);
                        MostrarResultado(valor, resultado, "centímetros");
                        break;
                    case 6:
                        valor = PedirValor("centímetros");
                        resultado = servicio.CentimetersToInches(valor);
                        MostrarResultado(valor, resultado, "pulgadas");
                        break;
                    case 0:
                        Console.WriteLine("Saliendo...");
                        break;
                    default:
                        Console.WriteLine("Opción no válida.");
                        break;
                }

                if (opcion != 0)
                {
                    Console.WriteLine("\nPresione cualquier tecla para continuar...");
                    Console.ReadKey();
                }

            } while (opcion != 0);
        }

        private double PedirValor(string unidadOrigen)
        {
            Console.Write($"Ingrese el valor en {unidadOrigen}: ");
            return double.TryParse(Console.ReadLine(), out double valor) ? valor : 0;
        }

        private void MostrarResultado(double valor, double resultado, string unidadDestino)
        {
            Console.WriteLine($"Resultado: {resultado:F4} {unidadDestino}");
        }
    }
}
