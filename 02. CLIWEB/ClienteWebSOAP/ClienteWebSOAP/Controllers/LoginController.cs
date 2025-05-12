using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using ClienteWebSOAP.Models;

namespace ClienteWebSOAP.Controllers
{
    public class LoginController : Controller
    {
        public ActionResult Index()
        {
            return View();
        }

        [HttpPost]
        public ActionResult Index(LoginModel model)
        {
            if (model.Usuario == "MONSTER" && model.Contrasena == "MONSTER9")
            {
                Session["usuario"] = model.Usuario;
                return RedirectToAction("Index", "Conversion");
            }

            ViewBag.Error = "Credenciales incorrectas.";
            return View();
        }

        public ActionResult Logout()
        {
            Session.Clear();
            return RedirectToAction("Index");
        }
    }
}
