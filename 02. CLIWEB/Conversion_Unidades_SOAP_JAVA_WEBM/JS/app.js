// app.js

const API_BASE = "http://localhost:8080/temperature-service";

// =================== LOGIN ===================
document.addEventListener("DOMContentLoaded", () => {
  const loginForm = document.getElementById("loginForm");
  if (loginForm) {
    loginForm.addEventListener("submit", async (e) => {
      e.preventDefault();
      const username = document.getElementById("username").value;
      const password = document.getElementById("password").value;

      try {
        const response = await fetch(`${API_BASE}/auth/login`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify({ username, password })
        });

        if (response.ok) {
          // Redirigir al conversor si login es exitoso
          window.location.href = "convert.html";
        } else {
          alert("Credenciales inválidas.");
        }
      } catch (err) {
        console.error("Error en login:", err);
        alert("No se pudo conectar al servidor.");
      }
    });
  }

  // =================== CONVERSIÓN ===================
  const conversionForm = document.getElementById("conversionForm");
  if (conversionForm) {
    conversionForm.addEventListener("submit", async (e) => {
      e.preventDefault();
      const value = parseFloat(document.getElementById("tempValue").value);
      const type = document.getElementById("conversionType").value;

      let url = "";
      if (type === "toCelsius") {
        url = `${API_BASE}/temperature/toCelsius?fahrenheit=${value}`;
      } else if (type === "toFahrenheit") {
        url = `${API_BASE}/temperature/toFahrenheit?celsius=${value}`;
      }

      try {
        const res = await fetch(url);
        if (!res.ok) throw new Error("Error al obtener datos.");
        const data = await res.json();
        document.getElementById("result").innerText = JSON.stringify(data);
      } catch (err) {
        console.error("Error de conversión:", err);
        alert("Error al convertir la temperatura.");
      }
    });
  }
});
