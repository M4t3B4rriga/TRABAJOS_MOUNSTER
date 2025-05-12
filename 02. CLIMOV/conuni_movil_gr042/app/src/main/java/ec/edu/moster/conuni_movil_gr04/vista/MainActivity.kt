package ec.edu.moster.conuni_movil_gr04.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import ec.edu.moster.conuni_movil_gr04.R
import ec.edu.moster.conuni_movil_gr04.controlador.LoginControlador
import ec.edu.moster.conuni_movil_gr04.modelo.SessionManager
import ec.edu.moster.conuni_movil_gr04.modelo.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MainActivity : AppCompatActivity() {

    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var usernameLayout: TextInputLayout
    private lateinit var passwordLayout: TextInputLayout
    private lateinit var loginButton: Button
    private lateinit var progressBar: ProgressBar

    private val controller = LoginControlador()
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        initViews()

        // Configurar listeners
        setupListeners()
    }

    private fun initViews() {
        usernameInput = findViewById(R.id.editUsername)
        passwordInput = findViewById(R.id.editPassword)
        usernameLayout = findViewById(R.id.usernameLayout)
        passwordLayout = findViewById(R.id.passwordLayout)
        loginButton = findViewById(R.id.btnLogin)
        progressBar = findViewById(R.id.progressBar)
    }

    private fun setupListeners() {
        loginButton.setOnClickListener {
            // Animación al hacer clic
            animateButtonClick(it) {
                validateAndLogin()
            }
        }

        // Animación al enfocar campos
        setupFieldAnimations()
    }

    private fun animateButtonClick(view: View, action: () -> Unit) {
        view.animate()
            .scaleX(0.95f)
            .scaleY(0.95f)
            .setDuration(100)
            .withEndAction {
                view.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(100)
                    .start()
                action()
            }
            .start()
    }

    private fun setupFieldAnimations() {
        usernameInput.setOnFocusChangeListener { _, hasFocus ->
            animateInputField(usernameLayout, hasFocus)
        }

        passwordInput.setOnFocusChangeListener { _, hasFocus ->
            animateInputField(passwordLayout, hasFocus)
        }
    }

    private fun animateInputField(view: View, hasFocus: Boolean) {
        val animation = AnimationUtils.loadAnimation(
            this,
            if (hasFocus) R.anim.scale_up else R.anim.scale_down
        )
        view.startAnimation(animation)
    }

    private fun validateAndLogin() {
        val username = usernameInput.text.toString().trim()
        val password = passwordInput.text.toString().trim()

        if (!validateFields(username, password)) {
            return
        }

        showLoading(true)

        mainScope.launch {
            try {
                val success = withContext(Dispatchers.IO) {
                    controller.login(User(username, password))
                }

                if (success) {
                    onLoginSuccess(username)

                } else {
                    onLoginFailure("Credenciales incorrectas")
                }
            } catch (e: Exception) {
                handleNetworkError(e)
            } finally {
                showLoading(false)
            }
        }
    }

    private fun validateFields(username: String, password: String): Boolean {
        var isValid = true

        if (username.isEmpty()) {
            usernameLayout.error = "Ingrese su usuario"
            isValid = false
        } else {
            usernameLayout.error = null
        }

        if (password.isEmpty()) {
            passwordLayout.error = "Ingrese su contraseña"
            isValid = false

        } else {
            passwordLayout.error = null
        }

        return isValid
    }

    private fun onLoginSuccess(username: String) {
        // Guardar sesión
        SessionManager(this).saveUsername(username)

        showSuccess("¡Bienvenido, $username!")

        mainScope.launch {
            delay(2000)
            navigateToConversion()
        }
    }

    private fun onLoginFailure(message: String) {
        showError(message)
        passwordInput.requestFocus()
    }

    private fun handleNetworkError(e: Exception) {
        val errorMessage = when (e) {
            is ConnectException, is SocketTimeoutException -> "No se pudo conectar al servidor. Verifique su conexión."
            is UnknownHostException -> "Servidor no disponible. Intente más tarde."
            else -> "Error inesperado: ${e.localizedMessage}"
        }
        showError(errorMessage)
    }

    private fun showLoading(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
        loginButton.isEnabled = !show
        usernameInput.isEnabled = !show
        passwordInput.isEnabled = !show
    }

    private fun showSuccess(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(this, R.color.success_green))
            .setTextColor(ContextCompat.getColor(this, R.color.white))
            // Asegurarnos que el Snackbar permanezca visible
            .setDuration(Snackbar.LENGTH_LONG)
            .show()
    }

    private fun showError(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(this, R.color.error_red))
            .setTextColor(ContextCompat.getColor(this, R.color.white))
            .setAction("OK") { /* Dismiss */ }
            .setActionTextColor(ContextCompat.getColor(this, R.color.white))
            .show()
    }

    private fun navigateToConversion() {
        val intent = Intent(this, ConversionActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }


}