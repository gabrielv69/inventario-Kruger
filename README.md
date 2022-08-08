# inventario-Kruger

Registro del inventario del estado de vacunación de los empleados -BACKEND

El [FrontEnd](https://github.com/gabrielv69/font_Kruger) de este proyecto, lo podemos encontrar en: 

https://github.com/gabrielv69/font_Kruger

## Modelo de la base de Datos
![Modelo](https://github.com/gabrielv69/inventario-Kruger/blob/main/Model-Database-Inventario.png)

## Installation

Usar [git](https://git-scm.com/downloads) para clonar el repositorio

```bash
git clone https://github.com/gabrielv69/inventario-Kruger.git
```
Contar con [NodeJS](https://nodejs.org/en/download/) 

Instalar [PostgreSQL](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads) que será el administrador de BDD a usar, al hacerlo de igual manera instalar [pgadmin](https://www.pgadmin.org/download/pgadmin-4-windows/)

-Crear una BDD llamada inventario

Se recomienda el uso de [IntelliJ IDEA](https://www.jetbrains.com/idea/download/?fromIDE=#section=windows) como IDE de Desarrollo , de igual manera se recomienda usar el SDK 1.8 que fue con el cual se desarrolló el proyecto.

-Abrir el proyecto con IntellijJ, esperar que carguen todas las dependencias, editar el siguiente archivo:
En el proyecto en src>resources>application.properties
#### Edición el archivo application.properties
```properties
#Nombre de la base de datos
spring.jpa.database=POSTGRESQL 
spring.sql.init.platform=postgres
#inventario es el nombre de la BDD, de ser necesario cambiarlo
spring.datasource.url=jdbc:postgresql://localhost:5432/inventario 
spring.datasource.username=postgres
#Establecer la contraseña de la BDD de ser necesario
spring.datasource.password=
spring.jpa.show-sql=true
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
```
Tras esto, ejecutar el proyecto de maven mediante: spring-boot:run

Tras la ejecución podemos ingresar a: 
```url
http://localhost:8080/swagger-ui/index.html
```
y observaremos la ventana de Swagger
