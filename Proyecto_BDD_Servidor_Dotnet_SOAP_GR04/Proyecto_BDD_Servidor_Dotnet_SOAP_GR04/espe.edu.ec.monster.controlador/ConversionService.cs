using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using espe.edu.ec.monster.modelo;

namespace espe.edu.ec.monster.controlador
{
    public class ConversionService : IConversionService
    {
        public double CentimetersToFeet(double centimeters)
        {
            return centimeters * 0.0328084;
        }

        public double FeetToCentimeters(double feet)
        {
            return feet / 0.0328084;
        }

        public double MetersToYards(double meters)
        {
            return meters * 1.09361;
        }

        public double YardsToMeters(double yards)
        {
            return yards / 1.09361;
        }

        public double InchesToCentimeters(double inches)
        {
            return inches * 2.54;
        }

        public double CentimetersToInches(double centimeters)
        {
            return centimeters / 2.54;
        }
    }
}
