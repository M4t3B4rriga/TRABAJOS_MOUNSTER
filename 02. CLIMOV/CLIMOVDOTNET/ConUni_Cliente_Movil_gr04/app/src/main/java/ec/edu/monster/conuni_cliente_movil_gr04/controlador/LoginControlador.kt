package ec.edu.monster.conuni_cliente_movil_gr04.controlador

import android.content.Context
import ec.edu.monster.conuni_cliente_movil_gr04.modelo.SessionManager
import ec.edu.monster.conuni_cliente_movil_gr04.modelo.User

class LoginControlador(private val context: Context, private val sessionManager: SessionManager) {

    fun isUserLoggedIn(): Boolean = sessionManager.isLoggedIn()

    suspend fun authenticate(username: String, password: String): Boolean {
        return try {
            // Validación local con credenciales fijas
            if (username == "monster" && password == "monster9") {
                val user = User(username, password)
                sessionManager.createLoginSession(user)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            false
        }
    }
}