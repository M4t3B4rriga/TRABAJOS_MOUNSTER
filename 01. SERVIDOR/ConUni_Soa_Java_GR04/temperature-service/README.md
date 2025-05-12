# Comando para poder correr el servicio en otras maquinas:
java -Dpayara.http.host=0.0.0.0 -jar payara-micro.jar --deploy target/temperature-service.war

# Para correr el servicio de manera local
java -jar payara-micro.jar --deploy target/temperature-service.war  


# Enpaquetar por si hay correcion es primera vez 
mvn clean package

# servicio
http://localhost:8080/temperature-service/api/application.wadl
