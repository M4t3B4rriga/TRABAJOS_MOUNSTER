package ec.edu.moster.conuni_movil_gr04.modelo

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

    fun saveUsername(username: String) {
        sharedPref.edit().putString("USERNAME", username).apply()
    }

    fun getUsername(): String {
        return sharedPref.getString("USERNAME", "Usuario") ?: "Usuario"
    }

    fun clearSession() {
        sharedPref.edit().clear().apply()
    }
}