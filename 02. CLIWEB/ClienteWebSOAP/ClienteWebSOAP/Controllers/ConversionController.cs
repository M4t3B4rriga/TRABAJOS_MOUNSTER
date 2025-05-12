using System.Web.Mvc;
using ClienteWebSOAP.Models;
using ClienteWebSOAP.ServicioConversion;

namespace ClienteWebSOAP.Controllers
{
    public class ConversionController : Controller
    {
        public ActionResult Index()
        {
            // Verificar si el usuario ha iniciado sesión
            if (Session["usuario"] == null)
            {
                return RedirectToAction("Index", "Login");
            }

            return View(new ConversionModel());
        }

        [HttpPost]
        public ActionResult Index(ConversionModel model)
        {
            // Verificar si el usuario ha iniciado sesión
            if (Session["usuario"] == null)
            {
                return RedirectToAction("Index", "Login");
            }

            var client = new ConversionServiceClient();
            double resultado = 0;

            switch (model.TipoConversion)
            {
                case "cmToFt": resultado = client.CentimetersToFeet(model.Valor); break;
                case "ftToCm": resultado = client.FeetToCentimeters(model.Valor); break;
                case "mToYd": resultado = client.MetersToYards(model.Valor); break;
                case "ydToM": resultado = client.YardsToMeters(model.Valor); break;
                case "inToCm": resultado = client.InchesToCentimeters(model.Valor); break;
                case "cmToIn": resultado = client.CentimetersToInches(model.Valor); break;
            }

            model.Resultado = resultado;
            return View(model);
        }
    }
}
