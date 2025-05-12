package ec.edu.monster.conuni_cliente_movil_gr04.vista

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import ec.edu.monster.conuni_cliente_movil_gr04.R
import ec.edu.monster.conuni_cliente_movil_gr04.controlador.ConversionControlador
import ec.edu.monster.conuni_cliente_movil_gr04.modelo.ConversionResponse
import ec.edu.monster.conuni_cliente_movil_gr04.modelo.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.SocketTimeoutException

class ConversionActivity : AppCompatActivity() {

    private lateinit var etInputValue: EditText
    private lateinit var tvResult: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnConvertCmToFeet: Button
    private lateinit var btnConvertFeetToCm: Button
    private lateinit var btnConvertMetersToYards: Button
    private lateinit var btnConvertYardsToMeters: Button
    private lateinit var btnConvertInchesToCm: Button
    private lateinit var btnConvertCmToInches: Button
    private lateinit var btnLogout: ImageView
    private lateinit var errorCard: MaterialCardView
    private lateinit var tvError: TextView

    private lateinit var conversionControlador: ConversionControlador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversion)

        // Inicializa las vistas de error
        errorCard = findViewById(R.id.errorCard)
        tvError = findViewById(R.id.tvError)

        // Inicializar vistas
        initViews()

        // Inicializar controlador
        conversionControlador = ConversionControlador(this)

        // Configurar listeners
        setupListeners()
    }

    private fun initViews() {
        etInputValue = findViewById(R.id.etInputValue)
        tvResult = findViewById(R.id.tvResult)
        progressBar = findViewById(R.id.progressBar)
        btnConvertCmToFeet = findViewById(R.id.btnConvertCmToFeet)
        btnConvertFeetToCm = findViewById(R.id.btnConvertFeetToCm)
        btnConvertMetersToYards = findViewById(R.id.btnConvertMetersToYards)
        btnConvertYardsToMeters = findViewById(R.id.btnConvertYardsToMeters)
        btnConvertInchesToCm = findViewById(R.id.btnConvertInchesToCm)
        btnConvertCmToInches = findViewById(R.id.btnConvertCmToInches)
        btnLogout = findViewById(R.id.btnLogout)
    }

    private fun setupListeners() {
        btnConvertCmToFeet.setOnClickListener { convert("CentimetersToFeet") }
        btnConvertFeetToCm.setOnClickListener { convert("FeetToCentimeters") }
        btnConvertMetersToYards.setOnClickListener { convert("MetersToYards") }
        btnConvertYardsToMeters.setOnClickListener { convert("YardsToMeters") }
        btnConvertInchesToCm.setOnClickListener { convert("InchesToCentimeters") }
        btnConvertCmToInches.setOnClickListener { convert("CentimetersToInches") }

        btnLogout.setOnClickListener {
            // Limpiar sesión
            val sessionManager = SessionManager(this)
            sessionManager.logoutUser()

            // Redirigir al login
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun convert(conversionType: String) {
        hideError() // Oculta cualquier error previo

        val inputText = etInputValue.text.toString()
        if (inputText.isEmpty()) {
            showError("Ingrese un valor a convertir")
            return
        }

        val inputValue = try {
            inputText.toDouble()
        } catch (e: NumberFormatException) {
            showError("Ingrese un número válido")
            return
        }

        showLoading(true)

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = try {
                    withContext(Dispatchers.IO) {
                        when (conversionType) {
                            "CentimetersToFeet" -> conversionControlador.centimetersToFeet(inputValue)
                            "FeetToCentimeters" -> conversionControlador.feetToCentimeters(inputValue)
                            "MetersToYards" -> conversionControlador.metersToYards(inputValue)
                            "YardsToMeters" -> conversionControlador.yardsToMeters(inputValue)
                            "InchesToCentimeters" -> conversionControlador.inchesToCentimeters(inputValue)
                            "CentimetersToInches" -> conversionControlador.centimetersToInches(inputValue)
                            else -> ConversionResponse(false, 0.0, "Tipo de conversión no soportado")
                        }
                    }
                } catch (e: Exception) {
                    // Captura específicamente el error de timeout
                    when (e) {
                        is SocketTimeoutException -> {
                            ConversionResponse(false, 0.0, "El servidor no responde. Tiempo de espera agotado.")
                        }
                        is ConnectException -> {
                            ConversionResponse(false, 0.0, "No se puede conectar al servidor.")
                        }
                        else -> {
                            ConversionResponse(false, 0.0, "Error de conexión: ${e.localizedMessage}")
                        }
                    }
                }

                if (response.success) {
                    showResult(response)
                } else {
                    showError(response.error ?: "Error desconocido")
                }
            } finally {
                showLoading(false)
            }
        }
    }


    private fun showError(message: String) {
        tvError.text = message
        errorCard.visibility = View.VISIBLE
    }

    private fun hideError() {
        errorCard.visibility = View.GONE
    }

    private fun showLoading(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
        setButtonsEnabled(!show)
    }

    private fun setButtonsEnabled(enabled: Boolean) {
        btnConvertCmToFeet.isEnabled = enabled
        btnConvertFeetToCm.isEnabled = enabled
        btnConvertMetersToYards.isEnabled = enabled
        btnConvertYardsToMeters.isEnabled = enabled
        btnConvertInchesToCm.isEnabled = enabled
        btnConvertCmToInches.isEnabled = enabled
    }

    private fun showResult(response: ConversionResponse) {
        if (response.success) {
            tvResult.text = String.format("%.4f", response.result)
        } else {
            Toast.makeText(this, response.error ?: "Error desconocido", Toast.LENGTH_LONG).show()
            tvResult.text = "Error"
        }
    }
}