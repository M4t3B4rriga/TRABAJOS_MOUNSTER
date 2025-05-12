package ec.edu.monster.conuni_cliente_movil_gr04.servicio

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers(
        "Content-Type: text/xml",
        "Accept-Charset: utf-8",
        "SOAPAction: http://tempuri.org/IConversionService/CentimetersToFeet"
    )
    @POST("ConversionService")
    fun centimetersToFeet(@Body requestBody: RequestBody): Call<String>

    @Headers(
        "Content-Type: text/xml",
        "Accept-Charset: utf-8",
        "SOAPAction: http://tempuri.org/IConversionService/FeetToCentimeters"
    )
    @POST("ConversionService")
    fun feetToCentimeters(@Body requestBody: RequestBody): Call<String>

    @Headers(
        "Content-Type: text/xml",
        "Accept-Charset: utf-8",
        "SOAPAction: http://tempuri.org/IConversionService/MetersToYards"
    )
    @POST("ConversionService")
    fun metersToYards(@Body requestBody: RequestBody): Call<String>

    @Headers(
        "Content-Type: text/xml",
        "Accept-Charset: utf-8",
        "SOAPAction: http://tempuri.org/IConversionService/YardsToMeters"
    )
    @POST("ConversionService")
    fun yardsToMeters(@Body requestBody: RequestBody): Call<String>

    @Headers(
        "Content-Type: text/xml",
        "Accept-Charset: utf-8",
        "SOAPAction: http://tempuri.org/IConversionService/InchesToCentimeters"
    )
    @POST("ConversionService")
    fun inchesToCentimeters(@Body requestBody: RequestBody): Call<String>

    @Headers(
        "Content-Type: text/xml",
        "Accept-Charset: utf-8",
        "SOAPAction: http://tempuri.org/IConversionService/CentimetersToInches"
    )
    @POST("ConversionService")
    fun centimetersToInches(@Body requestBody: RequestBody): Call<String>
}