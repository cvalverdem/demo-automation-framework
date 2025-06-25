Este proyecto es una demostración técnica para automatización de pruebas funcionales en el sitio de **Club Promerica**

Consta de dos proyectos

**adaptador-selenium** >  El que maneja toda la logica de selenium completamente desacoplada y reutilizable<br>
**automatiion-framework** > El que maneja todos los test funcionales 

Tecnoligias utilizadas 

- Java 17 
- Maven
- Selenium WebDriver
- TestNG
- Allure Framework
- Eclipse IDE Version: 2025-06 RC1 (4.36.0 RC1)

##Cómo ejecutas las pruebas

**Requisitos previos**

- Java 17 instalado
- Maven instalado (mvn -v)
- Chrome o Firefox en el sistema
- Allure CLI instalado (opcional si gustan ver los reportes despues de la ejecución)

#Si lo ejecutar desde consola:

Ir a la carpeta raiz del proyecto adaptador-selenium ejem. **cd adaptador-selenium**

**Ejecutar el comando** *mvn clean install *

Ir a la carpeta raiz del proyecto automation-framework ejem. **cd automation-framework**

**Ejecutar el comando ** *mvn clean test*

**Nota:** Luego de ejecutar ambos proyectos puedes correr el reporte para mostrarlo ejecutando el comando **allure serve allure-results** si lo tienes instalado en tu maquina  

#Si lo ejecutas desde IDE:

Una vez importados ambos proyectos 

En el proyecto **adaptador-selenium** dar clic derecho Run As > Maven install<br>
En el proyecto **automation-framework** dar clic derecho Run As > Maven test  

**Nota:** Luego de ejecutar ambos proyectos puedes correr el reporte para mostrarlo ejecutando el comando **allure serve allure-results** si lo tienes instalado en tu maquina desde la carpeta raiz del proyecto **automation-framework**

**Tambien puedes ejecutar directamente el testng.xml si tienes testNG instalado en el IDE dando click derecho Run As > TestNG Suite**


Si tienes Allure CLI instalado, dentro del proyecto **automation-framework** puedes ejecutar el comando **allure serve allure-results** desde la consola y este te abrira el reporte ya generado con anterioridad 

