Java version: 17
Spring boot version: 3.4.4

1. Ejecutar archivo docker-compose.yml
   - Se creara un contenedor de mysql (Puerto 3307) y la base de datos: pagosdb
   - Se creara un contenedor de RabbitMQ

2. Arrancar microservicio (Puerto 8080)

   Nota: Al arrancar el ms se crearan las tablas y se insertaran registros en la tabla c_estatus_pago y clientes.

3. Consumir los servicios en postman:

   (PUT) http://localhost:8080/api/pago
         REQUEST:
         {
             "concepto":"Pago recibo internet",
             "clienteIdRealizaPago":2,
             "monto": "1050.99",
             "clienteIdRecibePago": 3
         }

   (GET) http://localhost:8080/api/pago/1

   (POST) http://localhost:8080/api/pago/actualizarEstatus
      {
          "idPago": 1,
          "idEstatusPago": 4
      }

   
