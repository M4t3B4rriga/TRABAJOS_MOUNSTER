import { construirXML } from '../model/ConversionModel.js';
import { mostrarResultado } from '../view/ConversionView.js';
import { enviarSOAPRequest } from '../soapRequest.js';

document.getElementById("convertirBtn").addEventListener("click", async () => {
    const valor = document.getElementById("valor").value;
    const tipo = document.getElementById("tipo").value;
    console.log("Valor: " + valor);
    console.log("Tipo: " + tipo);

    const xml = construirXML(valor, tipo);

    try {
        const response = await enviarSOAPRequest(xml);
        const parser = new DOMParser();
        const xmlDoc = parser.parseFromString(response, "text/xml");

        const NAMESPACE="http://servicio.monster.edu.ec/";

        // Detecta el tipo de respuesta esperada
        let resultTag = null;
        if (tipo === "celsiusToFahrenheit") {
            resultTag = xmlDoc.getElementsByTagNameNS(NAMESPACE, "celsiusToFahrenheitResponse")[0];
        } else if (tipo === "fahrenheitToCelsius") {
            resultTag = xmlDoc.getElementsByTagNameNS(NAMESPACE, "fahrenheitToCelsiusResponse")[0];
        }

        if (!resultTag) {
            mostrarResultado("Error: No se encontró la respuesta esperada.");
            return;
        }

        // Busca el valor dentro de la respuesta
        const valorConvertido = resultTag.getElementsByTagName("return")[0]?.textContent;

        if (valorConvertido === undefined) {
            mostrarResultado("Error: No se encontró el valor convertido.");
            return;
        }

        mostrarResultado("Resultado: " + valorConvertido);
    } catch (error) {
        mostrarResultado("Error: " + error.message);
    }
});
