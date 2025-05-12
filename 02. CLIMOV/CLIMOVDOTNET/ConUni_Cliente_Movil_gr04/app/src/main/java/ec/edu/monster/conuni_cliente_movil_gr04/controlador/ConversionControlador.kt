package ec.edu.monster.conuni_cliente_movil_gr04.controlador

import android.content.Context
import android.util.Log
import ec.edu.monster.conuni_cliente_movil_gr04.modelo.ConversionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

class ConversionControlador(private val context: Context) {

    companion object {
        private const val TAG = "ConversionControlador"
        private const val BASE_URL = "http://10.0.2.2:8733/Design_Time_Addresses/ConversionService/"
        private const val TIMEOUT = 30L // segundos

        // Plantilla SOAP genérica
        private const val SOAP_TEMPLATE = """
            <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                              xmlns:tem="http://tempuri.org/">
               <soapenv:Header/>
               <soapenv:Body>
                  <tem:%s>
                     <tem:%s>%s</tem:%s>
                  </tem:%s>
               </soapenv:Body>
            </soapenv:Envelope>
        """
    }

    private val client = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .header("Content-Type", "text/xml")
                .build()
            chain.proceed(request)
        }
        .build()

    private suspend fun performSoapRequest(
        methodName: String,
        paramName: String,
        value: Double
    ): ConversionResponse {
        return withContext(Dispatchers.IO) {
            try {
                val soapAction = "http://tempuri.org/IConversionService/$methodName"
                val soapBody = SOAP_TEMPLATE.format(methodName, paramName, value, paramName, methodName)

                val request = Request.Builder()
                    .url(BASE_URL)
                    .post(soapBody.toRequestBody("text/xml".toMediaType()))
                    .header("SOAPAction", soapAction)
                    .build()

                val response = client.newCall(request).execute()
                val responseBody = response.body?.string() ?: ""

                if (response.isSuccessful) {
                    parseSoapResponse(responseBody, methodName)
                } else {
                    Log.e(TAG, "HTTP Error: ${response.code} - ${response.message}")
                    ConversionResponse(false, 0.0, "Error del servidor (Código ${response.code})")
                }
            } catch (e: SocketTimeoutException) {
                Log.e(TAG, "Timeout en la conexión con el servidor", e)
                ConversionResponse(false, 0.0, "El servidor no responde. Por favor, intente más tarde.")
            } catch (e: ConnectException) {
                Log.e(TAG, "No se pudo conectar al servidor", e)
                ConversionResponse(false, 0.0, "No se puede conectar al servidor. Verifique su conexión.")
            } catch (e: UnknownHostException) {
                Log.e(TAG, "Servidor no encontrado", e)
                ConversionResponse(false, 0.0, "Servidor no disponible. Contacte al administrador.")
            } catch (e: Exception) {
                Log.e(TAG, "Error en la comunicación con el servidor", e)
                ConversionResponse(false, 0.0, "Error de comunicación con el servidor")
            }
        }
    }


    private fun parseSoapResponse(xmlResponse: String, methodName: String): ConversionResponse {
        return try {
            val resultTag = "<${methodName}Result>"
            val startIndex = xmlResponse.indexOf(resultTag) + resultTag.length
            val endIndex = xmlResponse.indexOf("</${methodName}Result>", startIndex)

            if (startIndex >= 0 && endIndex > startIndex) {
                val resultValue = xmlResponse.substring(startIndex, endIndex).trim()
                ConversionResponse(true, resultValue.toDouble(), null)
            } else {
                Log.e(TAG, "Invalid SOAP response format")
                ConversionResponse(false, 0.0, "Invalid response format")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Parse Error", e)
            ConversionResponse(false, 0.0, "Parse Error: ${e.localizedMessage}")
        }
    }



    // Métodos de conversión (igual que antes)
    suspend fun centimetersToFeet(centimeters: Double) =
        performSoapRequest("CentimetersToFeet", "centimeters", centimeters)

    suspend fun feetToCentimeters(feet: Double) =
        performSoapRequest("FeetToCentimeters", "feet", feet)

    suspend fun metersToYards(meters: Double) =
        performSoapRequest("MetersToYards", "meters", meters)

    suspend fun yardsToMeters(yards: Double) =
        performSoapRequest("YardsToMeters", "yards", yards)

    suspend fun inchesToCentimeters(inches: Double) =
        performSoapRequest("InchesToCentimeters", "inches", inches)

    suspend fun centimetersToInches(centimeters: Double) =
        performSoapRequest("CentimetersToInches", "centimeters", centimeters)
}