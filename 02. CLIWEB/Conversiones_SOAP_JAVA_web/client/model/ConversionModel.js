export function construirXML(valor, tipo) {
    let metodoSOAP = "";
    if (tipo === "celsiusToFahrenheit") {
        metodoSOAP = "celsiusToFahrenheit";
    } else if (tipo === "fahrenheitToCelsius") {
        metodoSOAP = "fahrenheitToCelsius";
    }

    return `
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://servicio.monster.edu.ec/">
        <soapenv:Header/>
        <soapenv:Body>
            <ser:${metodoSOAP}>
                <arg0>${valor}</arg0>
            </ser:${metodoSOAP}>
        </soapenv:Body>
    </soapenv:Envelope>
    `;
}
