package ec.edu.monster.con_uni_java_gr04.service;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SoapService {
    private static final String NAMESPACE = "http://servicio.monster.edu.ec/";
    private static final String URL = "http://10.40.26.139:8080/ConUni_Soa_Java_ServidorGR04/ServicioConversionService?wsdl";

    public void callMethod(String methodName, HashMap<String, String> parameters, SoapCallback<Object> callback) {
        new SoapTask(methodName, parameters, callback).execute();
    }

    private static class SoapTask extends AsyncTask<Void, Void, Object> {
        private final String methodName;
        private final HashMap<String, String> parameters;
        private final SoapCallback<Object> callback;

        SoapTask(String methodName, HashMap<String, String> parameters, SoapCallback<Object> callback) {
            this.methodName = methodName;
            this.parameters = parameters;
            this.callback = callback;
        }

        @Override
        protected Object doInBackground(Void... voids) {
            try {
                // Construir XML SOAP manualmente
                StringBuilder soapBody = new StringBuilder();
                soapBody.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"")
                        .append(NAMESPACE)
                        .append("\">")
                        .append("<soapenv:Header/>")
                        .append("<soapenv:Body>")
                        .append("<ser:").append(methodName).append(">");

                for (Map.Entry<String, String> entry : parameters.entrySet()) {
                    soapBody.append("<").append(entry.getKey()).append(">")
                            .append(entry.getValue())
                            .append("</").append(entry.getKey()).append(">");
                }

                soapBody.append("</ser:").append(methodName).append(">")
                        .append("</soapenv:Body>")
                        .append("</soapenv:Envelope>");

                // Conectar al servidor SOAP
                URL url = new URL(URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
                connection.setRequestProperty("SOAPAction", NAMESPACE + methodName);
                connection.setDoOutput(true);

                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(soapBody.toString());
                writer.flush();
                writer.close();

                // Leer respuesta
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder responseBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    responseBuilder.append(line);
                }

                reader.close();
                connection.disconnect();

                return responseBuilder.toString(); // Devolver XML como texto

            } catch (Exception e) {
                return e;
            }
        }

        @Override
        protected void onPostExecute(Object result) {
            if (result instanceof Exception) {
                callback.onError(((Exception) result).getMessage());
                return;
            }

            String response = result.toString();
            Log.d("SoapService", "Respuesta completa: " + response);

            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new StringReader(response));

                int eventType = parser.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        String tagName = parser.getName();
                        if ("return".equals(tagName)) {
                            String resultValue = parser.nextText();
                            Log.d("SoapService", "Valor de retorno: " + resultValue);

                            // Manejar diferentes tipos de respuesta
                            if (methodName.equals("login")) {
                                callback.onSuccess("true".equalsIgnoreCase(resultValue));
                            } else {
                                // Para conversiones de temperatura
                                try {
                                    double numericResult = Double.parseDouble(resultValue);
                                    callback.onSuccess(numericResult);
                                } catch (NumberFormatException e) {
                                    callback.onError("Respuesta numérica inválida");
                                }
                            }
                            return;
                        }
                    }
                    eventType = parser.next();
                }
                callback.onError("Formato de respuesta inválido");
            } catch (Exception e) {
                callback.onError("Error parsing XML: " + e.getMessage());
            }
        }
    }
}

