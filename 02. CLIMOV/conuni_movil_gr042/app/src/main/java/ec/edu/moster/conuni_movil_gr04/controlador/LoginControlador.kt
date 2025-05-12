package ec.edu.moster.conuni_movil_gr04.controlador

import ec.edu.moster.conuni_movil_gr04.modelo.User
import ec.edu.moster.conuni_movil_gr04.servicio.ApiService
import ec.edu.moster.conuni_movil_gr04.servicio.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginControlador {
    private val api = RetrofitClient.retrofit.create(ApiService::class.java)

    suspend fun login(user: User): Boolean {
        return withContext(Dispatchers.IO) {
            val response = api.login(user)
            response.isSuccessful
        }
    }

}