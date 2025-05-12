package ec.edu.monster.conuni_cliente_movil_gr04.modelo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import ec.edu.monster.conuni_cliente_movil_gr04.vista.MainActivity

class SessionManager(private val context: Context) {  // Context como parámetro del constructor
    private var pref: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = pref.edit()

    companion object {
        private const val PREF_NAME = "ConUniPref"
        private const val PRIVATE_MODE = 0
        private const val IS_LOGIN = "IsLoggedIn"
        const val KEY_USERNAME = "username"
        const val KEY_PASSWORD = "password"
    }

    fun createLoginSession(user: User) {
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_USERNAME, user.username)
        editor.putString(KEY_PASSWORD, user.password)
        editor.apply()
    }

    fun isLoggedIn(): Boolean = pref.getBoolean(IS_LOGIN, false)

    fun checkLogin() {
        if (!isLoggedIn()) {
            val intent = Intent(context, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        }
    }

    fun getUserDetails(): User = User(
        username = pref.getString(KEY_USERNAME, "") ?: "",
        password = pref.getString(KEY_PASSWORD, "") ?: ""
    )

    fun logoutUser() {
        editor.clear()
        editor.apply()
        val intent = Intent(context, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }
}