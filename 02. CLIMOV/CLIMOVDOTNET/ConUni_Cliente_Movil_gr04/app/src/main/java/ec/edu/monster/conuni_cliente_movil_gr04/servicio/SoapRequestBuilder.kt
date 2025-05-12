package ec.edu.monster.conuni_cliente_movil_gr04.servicio

object SoapRequestBuilder {
    private const val SOAP_TEMPLATE = """
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                          xmlns:tem="http://tempuri.org/">
           <soapenv:Header/>
           <soapenv:Body>
              <tem:%s>
                 <tem:%s>%s</tem:%s>
              </tem:%s>
           </soapenv:Body>
        </soapenv:Envelope>
    """

    fun buildRequest(methodName: String, paramName: String, value: Double): String {
        return SOAP_TEMPLATE.format(methodName, paramName, value, paramName, methodName)
    }
}