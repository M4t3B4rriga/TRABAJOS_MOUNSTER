using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ClienteEscritorioSOAP.espe.edu.ec.monster.modelo;

namespace ClienteEscritorioSOAP.espe.edu.ec.monster.utils
{
    public sealed class SessionManager
    {
        private static readonly SessionManager _instance = new SessionManager();
        public Usuario UsuarioActual { get; set; }

        private SessionManager() { }

        public static SessionManager Instance => _instance;
    }
}