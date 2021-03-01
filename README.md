# tareas-project

# Despliegue 📦

Herramientas y software recomendados:

· PostgresSQL 12
· Apache Tomcat 9.0.43
· Java JDK 1.8
· Maven 3.6.3
· Node 10.22.1

Para desplegar la aplicación localmente se recomienda seguir los siguientes pasos:

1. La base de datos es relacional y creada con el gestor de base de datos PostgreSQL, se recomienda descargar su instalador con su versión número 12, el cual se puede descargar del siguiente enlace:

https://www.postgresql.org/download

Luego de instalarlo, se recomienda crear la base de datos con nombre "tareas" y restaurar el script de base de datos disponible en el respositorio en la carpeta "Script-DB", además, crear el usuario y clave que serán usados por el aplicativo y revisar los permisos que tendrá el usuario creado sobre la base de datos y sus tablas.

2. El proyecto backend está construido con empaquetamiento war, por lo tanto, es necesario descargar un contenedor de Servlets como Tomcat del cual se recomienda usar su versión número 9, cual se puede descargar en la siguiente página:

https://tomcat.apache.org/download-90.cgi

Descargar el archivo zip de acuerdo a la arquitectura de su computadora, luego diríjase a la ruta de tomcat-version/bin, abra una linea de comandos y ejecute el comando startup.

Para generar el archivo war es necesario editar el archivo properties el cual se encuentra en la ruta /Backend/src/main/resources/application.properties del código fuente, en la primer línea del archivo se encuentra configurado el perfil de desarrollo "dev" (que apunta al archivo de propiedades application-dev.properties) para mostrar en consola el debug del aplicativo, para no observar todas estas trazas se recomienda cambiar la palabra "dev" por "prod" para cargar el archivo de propiedades correspondiente al ambiente productivo (que apunta al archivo de propiedades application-prod.properties), posteriormente es necesario cambiar las propiedades spring.datasource.username, spring.datasource.password y spring.datasource.url por los datos de usuario, clave, dirección http y puerto en el que se encuentra la base de datos.

El proyecto Backend utiliza como gestor de dependencias Maven, por lo tanto, para generar el archivo war del backend a desplegar se recomienda descargar y ejecutar Maven 3.6.3, el cual se puede encontrar el la siguiente pagina: 

https://maven.apache.org/download.cgi

Luego de descargar el archivo zip, se debe dirigir a la ruta maven-version/bin, abrir una línea de comandos y ejecutar el comando mvn compile en la ruta del proyecto backend, de esta manera se generará el archivo war con el perfil configurado, luego de ello, se debe copiar el archivo war generado dentro de la carpeta /tomcat/webapps, en la consola de tomcat se veran todos los detalles correspondientes al despliegue del war.

3. El proyecto Frontend está construido sobre node 10.22.1 el cual se puede descargar desde el siguiente enlace:

https://nodejs.org/es/download/releases/	

Una vez instalado, se debe instalar "serve" para el despliegue del aplicativo Frontend, por lo tanto, se debe ejecutar el comando "yarn global add serve". 

Para configurar la comunicación entre el Backend y Frontend se debe cambiar la dirección del API, para ello se debe dirigir a la carpeta Frontend/src/core/services y cambiar el atributo baseUrl por la dirección http donde se encuentra desplegado tomcat localhost:puerto/nombre-del-war, posteriormente, para hacer build se debe dirigir al directorio del código fuente correspondiente al Frontend y abrir una consola de comandos y ejecutar el comando "yarn build", una vez finalizado se creara la carpeta "build" con el proyecto compilado, finalmente se debe abrir una línea de comandos y ejecutar "yarn serve -s build".
