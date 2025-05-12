package ec.edu.moster.conuni_movil_gr04.servicio


import ec.edu.moster.conuni_movil_gr04.modelo.TemperatureResponse
import ec.edu.moster.conuni_movil_gr04.modelo.User
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("api/auth/login")
    suspend fun login(@Body user: User): Response<Unit>

    @GET("api/temperature/toCelsius")
    suspend fun convertToCelsius(@Query("fahrenheit") f: Double): Response<TemperatureResponse>

    @GET("api/temperature/toFahrenheit")
    suspend fun convertToFahrenheit(@Query("celsius") c: Double): Response<TemperatureResponse>
}