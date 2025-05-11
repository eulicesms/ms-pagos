1. Ejecutar archivo docker-compose.yml
   - Se creara un contenedor de mysql (Puerto 3307) y la base de datos: pagosdb
   - Se creara un contenedor de RabbitMQ

2. Arrancar microservicio (Puerto 8080) (Java 17)

   Nota: Al arrancar el ms se crearan las tablas y se insertaran registros en la tabla c_estatus_pago y clientes.

3. Consumir los servicios en postman

   
