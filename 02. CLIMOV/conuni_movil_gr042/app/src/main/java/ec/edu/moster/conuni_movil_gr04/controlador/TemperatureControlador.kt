package ec.edu.moster.conuni_movil_gr04.controlador

import ec.edu.moster.conuni_movil_gr04.servicio.ApiService
import ec.edu.moster.conuni_movil_gr04.servicio.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TemperatureControlador {
    private val api = RetrofitClient.retrofit.create(ApiService::class.java)

    suspend fun toCelsius(fahrenheit: Double): Double? {
        return withContext(Dispatchers.IO) {
            val res = api.convertToCelsius(fahrenheit)
            res.body()?.celsius
        }
    }

    suspend fun toFahrenheit(celsius: Double): Double? {
        return withContext(Dispatchers.IO) {
            val res = api.convertToFahrenheit(celsius)
            res.body()?.fahrenheit
        }
    }

}