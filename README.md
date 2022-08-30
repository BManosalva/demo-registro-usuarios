# demo-registro-usuarios

Demo de aplicación de gestión de usuarios.

Utiliza las siguientes tecnologías.

- Springboot.
- Java.
- Maven como gestor de dependencias y para generar build.
- Persistencia JPA cpn Hibernate.
- Banco de datos en memoria con H2.
- Swagger.
- Github como repositorio.

# Pasos Previos
Previo a la descarga del servicio, es necesario contar con las siguientes herramientas y configuraciones:

- Visual Studio Code (Editor utilizado para el desarrollo).
- Postman (Para probar ejecuciones sobre los endpoints).
- Java version 11.0.16 (con variable JAVA_HOME configurada).

Dado que el desarrollo se llevo a cabo en Visual Code, también es necesario contar con las siguientes extensiones:

![image](https://user-images.githubusercontent.com/32346999/187487347-566da33e-8c57-4474-bd70-31423ef3bbf0.png)

# Descarga e Inicio del Aplicativo

El primer paso consta de clonar el repositorio en un directorio local, para esto copiamos el link del repositorio como se muestra en la imagen:
![image](https://user-images.githubusercontent.com/32346999/187488352-78355175-c914-4307-bf73-a92ff9481648.png)

Una vez elegido el directorio local donde clonaremos en repo, debemos iniciar una terminal Git Bash:
![image](https://user-images.githubusercontent.com/32346999/187491725-41cc7904-cbef-4965-bcb6-1698fecfe794.png)


Ejecutamos el siguiente grupo de comandos para clonar el repositorio, ingresar al directorio del proyecto e iniciar con Visual Code:

![image](https://user-images.githubusercontent.com/32346999/187489589-0b84c1a5-a7b3-42ed-870a-ed4db4963169.png)

Para iniciar el servicio debemos seleccionar la opcion "Iniciar sin debuguear" ubicada en la barra superior del editor

![image](https://user-images.githubusercontent.com/32346999/187514003-ae19ec89-1fc7-4e84-a6f6-25af8712ed47.png)

Una vez que se logra levantar el servicio, podemos verificar que utiliza el puerto 8001 (ya configurado en el aplicativo)

![image](https://user-images.githubusercontent.com/32346999/187515209-a216319b-17ee-4247-9e28-501ede6b593a.png)




# Pruebas



## Listar Usuarios

Una vez que se inicia el servicio, este automaticamente pre-registra 3 usuarios configurados el archivo import.sql. Se configuraron con un id manual y sin telefonos de tal modo que se puedan distinguir de los usuarios registrados con el uso de esta API.

![image](https://user-images.githubusercontent.com/32346999/187510299-437195c3-e6a1-43c7-8068-cc1b1e5e8ff3.png)

La validación de este registro lo podemos hacer mediante el endpoint "Listar Usuarios" adjunto en la colección de postman.

![image](https://user-images.githubusercontent.com/32346999/187513474-d301ce65-d484-449a-9103-49c113f4a518.png)




## Ingreso de Usuario

Para la validación de formato del email y la password, se especificaron las expresiones regulares en el archivo application.yaml de tal modo que sean configurables, en el caso de la contraseña, esta debe contar con las siguientes condiciones:
- Minimo una letra minuscula.
- Minimo una letra mayuscula.
- Minimo un numero.
- Minimo un caracter especial.
- Minimo 5 de largo.

![image](https://user-images.githubusercontent.com/32346999/187508776-a71fd382-e32c-448a-829a-207cf695f425.png)

Hacemos una primera prueba con un email invalido.

![image](https://user-images.githubusercontent.com/32346999/187518834-b5510df4-6ed2-4485-8309-5f456ef0ede6.png)

Hacemos una segunda prueba, esta vez utilizando solo una password invalida.

![image](https://user-images.githubusercontent.com/32346999/187518739-1adef643-9361-4222-b910-9c3de279d163.png)

Volvemos a intentar el registro, esta vez con un email y password validos. Visualizamos el correcto registro del usuario con codigo HTTP 201 CREATED.

![image](https://user-images.githubusercontent.com/32346999/187519058-6f230dcd-54cb-4351-9e06-d9aef5366e4c.png)

## Buscar Usuario por ID

Mediante el endpoint "Buscar Usuario" de la colección postman, podemos buscar el usuario recién creado. Para ello copiamos el ID entregado en el registro y lo cargamos como un PATH Variable en la url.

El servicio responde con la información del usuario:

![image](https://user-images.githubusercontent.com/32346999/187519497-66e64816-c38f-4982-8141-9f4b3266585c.png)

Podemos intentar nuevamente el registro para detonar la excepcion de que email ya se encuentra registrado.

![image](https://user-images.githubusercontent.com/32346999/187520710-acd339f0-9eab-4a6a-b108-577c9f1f9a46.png)

## Modificar Usuario

Con el endpoint "Modificar Usuario" modificamos el usuario recien ingresado, cambiando sus datos menos el ID.

![image](https://user-images.githubusercontent.com/32346999/187532103-0d6e4dec-d42f-4f22-9adb-eaeee02447d5.png)

Tambien podemos volver a listar los usuarios para validar el cambio.

![image](https://user-images.githubusercontent.com/32346999/187532559-5bbfae88-7895-44d8-871e-03f15506ee01.png)

# Swagger

Para visualizar el swagger el servicio, accederemos a la interfaz cargando el numero de puerto en un navegador.

En este caso la url sera: http://localhost:8001/swagger-ui/#/

# Detener Ejecución del Aplicativo

Una vez terminadas nuestras pruebas y para finalizar, detenemos la ejecución del servicio presionando el botón detener en la siguiente pestaña de Visual Code.

![image](https://user-images.githubusercontent.com/32346999/187534801-c527a6b9-6e26-40e6-af95-584c44aab077.png)
