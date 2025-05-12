package ec.edu.monster.conuni_cliente_movil_gr04.modelo
data class User(
    var username: String = "",
    var password: String = "",
    var token: String? = null, // Opcional para autenticación avanzada
    var isLoggedIn: Boolean = false
) {
    /**
     * Valida que las credenciales no estén vacías
     */
    fun validateCredentials(): Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }

    /**
     * Valida las credenciales específicas (monster:monster)
     */
    fun validateMonsterCredentials(): Boolean {
        return username == "monster" && password == "monster"
    }
}