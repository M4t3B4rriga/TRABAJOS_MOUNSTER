package ec.edu.moster.conuni_movil_gr04.vista

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import ec.edu.moster.conuni_movil_gr04.R
import ec.edu.moster.conuni_movil_gr04.controlador.TemperatureControlador
import ec.edu.moster.conuni_movil_gr04.modelo.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DecimalFormat

class ConversionActivity : AppCompatActivity() {

    private lateinit var inputField: EditText
    private lateinit var resultText: TextView
    private lateinit var welcomeText: TextView
    private lateinit var toCelsiusBtn: Button
    private lateinit var toFahrenheitBtn: Button

    private val controller = TemperatureControlador()
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private lateinit var sessionManager: SessionManager
    private val decimalFormat = DecimalFormat("#.##")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversion)

        // Añade esto para mostrar la ActionBar
        setSupportActionBar(findViewById(R.id.toolbar))

        // Inicializar vistas
        initViews()

        // Configurar sesión
        sessionManager = SessionManager(this)

        // Mostrar usuario
        showUserWelcome()

        // Configurar botones
        setupConversionButtons()
    }

    private fun initViews() {
        welcomeText = findViewById(R.id.textWelcome)
        inputField = findViewById(R.id.editInput)
        resultText = findViewById(R.id.textResult)
        toCelsiusBtn = findViewById(R.id.btnToCelsius)
        toFahrenheitBtn = findViewById(R.id.btnToFahrenheit)
    }

    private fun showUserWelcome() {
        val username = sessionManager.getUsername()
        welcomeText.text = "Bienvenido, $username"
    }

    private fun setupConversionButtons() {
        toCelsiusBtn.setOnClickListener {
            convertTemperature(true)
        }

        toFahrenheitBtn.setOnClickListener {
            convertTemperature(false)
        }
    }

    private fun convertTemperature(toCelsius: Boolean) {
        val inputValue = inputField.text.toString().toDoubleOrNull()

        if (inputValue == null) {
            showError("Ingrese un valor válido")
            return
        }

        showLoading(true)

        mainScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    if (toCelsius) {
                        controller.toCelsius(inputValue)
                    } else {
                        controller.toFahrenheit(inputValue)
                    }
                }

                val unit = if (toCelsius) "°C" else "°F"
                val formattedResult = decimalFormat.format(result)
                resultText.text = "Resultado: $formattedResult $unit"

            } catch (e: Exception) {
                showError("Error en la conversión: ${e.message}")
            } finally {
                showLoading(false)
            }
        }
    }

    private fun showLoading(show: Boolean) {
        toCelsiusBtn.isEnabled = !show
        toFahrenheitBtn.isEnabled = !show
    }

    private fun showError(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_conversion, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_logout -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logout() {
        val username = sessionManager.getUsername()
        sessionManager.clearSession()

        // Mostrar mensaje de despedida
        Snackbar.make(findViewById(android.R.id.content),
            "Hasta pronto, $username!",
            Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(this, R.color.info_blue))
            .setTextColor(ContextCompat.getColor(this, R.color.white))
            .addCallback(object : Snackbar.Callback() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    super.onDismissed(transientBottomBar, event)
                    // Navegar después de que se cierre el Snackbar
                    navigateToLogin()
                }
            })
            .show()
    }

    private fun navigateToLogin() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
        finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}