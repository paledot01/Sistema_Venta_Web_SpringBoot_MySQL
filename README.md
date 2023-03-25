# Proyecto web de calzados | :clapper: [video de muestra][video_url]
Proyecto web con Spring y MySQL. Contiene CRUD, reporte Excel y PDF, Autenticación y Autorizaciones por ROLES con Spring Security.

[video_url]: https://youtu.be/luCPzEpEAzU

Acceso inicial:
- User: kevinB
- Pass: admin

## Primer Avance
- Se amplio y modificó la Base de Datos del proyecto de Escritorio [ShoesForMen][proyecto], para usarlo en este nuevo proyecto.
- Se creo una :eye: [Plantilla Web][plantilla] para este sistema en Bootstrap.
- Se completo el CRUD de la tabla Empleado con Spring en el Back y AJAX con Thymeleaf en el Front. Para el registro se creo una consulta Nativa en Spring que genera un nuevo codigo de empleado.
- Se creo una nueva plantilla para el reporte en Jaspersoft Studio y se pulió para que tanto la exportacion en PDF y EXCEL sean lo mas limpio y fiel posible al diseño original.

|  |  |  |
| :-------------: |:-------------:| :-----:|
| ![][img_1] | ![][img_2] | ![][img_3] |
| ![][img_4] | ![][img_5] | |   

[img_1]: ./screenshot/imagen_1.png
[img_2]: ./screenshot/imagen_2.png
[img_3]: ./screenshot/imagen_3.png
[img_4]: ./screenshot/imagen_4.png
[img_5]: ./screenshot/imagen_5.png

## Segundo y último Avance
- Se incorporo un Login al sistema utilizando Spring Security, este maneja la Autenticación y Autorización de los Usuarios atraves de sus ROLES. Esto restringe a los usuarios el acceso ah ciertas funciones.
- Se añadieron alertas al sistema, para esto se crearon 4 imagenes vectoriales y se animaron con CSS.
- Atraves de Ajax dependiendo de las respuestas de las solicitudes se activan las alertas correspondientes al igual que sus animaciones, para esto se tuvo que personalizar algunas respuesta de excepciones para modificar su codigo de HTTP status.
- Se incorporó en este repositorio un archivo de texto dentro de la carpeta "extra" que contiene errores, soluciones y observaciones que fui encontrando en la realización de este proyecto.

|  |  |  |
| :-------------: |:-------------:| :-----:|
| ![][img_6] | ![][img_7] | ![][img_8] |
| ![][img_9] |            |            |

[img_6]: ./screenshot/imagen_6.png
[img_7]: ./screenshot/imagen_7.png
[img_8]: ./screenshot/imagen_8.png
[img_9]: ./screenshot/imagen_9.gif

---


### Herramientas utilizadas:
- **Spring Tool Suite 4 [ 4.17.1 ]** como IDE principal para el desarrollo de este sistema.
- **MySQL** como motor de la base de datos.
- **JasperSoft Studio [ 6.19.0 ]** para la creacion de plantillas para el reporte en PDF y EXCEL.
- **Corel Draw** para la creación y modificación de iconos.
- **Visual Studio Code** para la creacion del Front y la edición de este README.md.
- **Git Bash** para subir y actualizar este repositorio.

### Tecnologias y versiones:
- OpenJDK 17 [ Amazon Corretto 17.0.5.8.1 LTS ]
- Sprint Boot [ 3.0.1 ] 
- Apache Maven [ 3.0.1 ]
- Bootstrap [ 5.2.2 ]
- JQuery ( AJAX )
- HTML, CSS, JS, Sass
- Thymeleaf
- Hibernate (ORM)
- JPQL
---

[proyecto]: https://github.com/paledot01/Sistema_Ventas_JavaSE_MySQL
[plantilla]: https://paledot02.github.io/Frontend_Exercise_02/

