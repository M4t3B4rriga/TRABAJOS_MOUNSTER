function login() {
    const usuario = document.getElementById("usuario").value;
    const password = document.getElementById("password").value;
    const mensaje = document.getElementById("mensaje");

    if (usuario === "monster" && password === "monster9") {
        window.location.href = "conversion.html";
    } else {
        mensaje.textContent = "Credenciales incorrectas.";
    }
}
