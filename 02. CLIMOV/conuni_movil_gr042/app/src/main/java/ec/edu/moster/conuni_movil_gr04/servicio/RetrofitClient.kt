package ec.edu.moster.conuni_movil_gr04.servicio

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


    object RetrofitClient {
        private const val BASE_URL = "http://10.40.26.120:8080/temperature-service/"


        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
