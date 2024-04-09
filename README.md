# Selenium vs Playwright
Este framework pretende comparar algunos de los casos de uso comunes en la automatización que podrían lograrse a través de Selenium o Playwright en sus versiones Java. Para ello, utilizamos TestNG como ejecutor de tests y Maven para la gestión de dependencias.

## POM
### Page Objects
Cada herramienta tiene su propio conjunto de page objects y pruebas configurados en las carpetas de su propio nombre en *pages* y *tests* respectivamente. Para crear un PO necesitamos definir una clase y heredarla de la clase **BasePage** llamando al constructor de la misma e implementando el metodo *getPageLoadedLocator* de modo que el elemento retornado por este metodo sea buscado y chequeado como visible al momento de construir una instancia, siendo seguro que esta seccion representada por el PO se ha cargado correctamente. Dentro de los PO estan definidos los selectores como atributos de la clase, usando el tipo **By** en Selenium o **Locator** en Playwright, de modo que se definen una sola vez estas formas de encontrar los elementos, pero se pueden usar en multiples ocasiones en los metodos dentro del PO.

### Tests
En el caso de los tests estos tambien usan una clase padre llamada **BaseTest** donde se configura el driver en caso de Selenium o el objeto Page en caso de Playwright. Tanto en un caso como en otro este atributo de los tests permite navegar, encontrar e interactuar con los elementos encontrados. A traves de anotaciones, se pueden configurar varios hooks con TestNG para definir comportamientos extras a ejecutarse antes o despues de la suite, clase, test definido o metodo (un test puede contener varios metodos si es data-driven).
En [este link](https://testng.org/annotations.html) se puede ver mas información sobre las anotaciones de TestNG y los parametros que se pueden incluir.

Para enviar parametros propios de los tests tambien se pueden definir dentro de suites de TestNG definidas en formato XML. Si se observa su sintaxis dentro del directorio suites se puede ver como se define una suite, se definen los tests requeridos dentro y incluye una clase para ese test, la cual puede ejecutarse con todos sus metodos, pero se pueden incluir o excluir tests de esas clases a elección. Para mas información sobre como armar las suites de pruebas con la sintaxis de TestNG visita la [documentación oficial](https://testng.org/#_testng_xml)

## Estrategia de Selectores
Las estrategias para encontrar elementos son algo distintas entre ambas herramientas. En Selenium se pueden usar metodos para encontrar por:
- clase
- ID
- tag name
- name
- texto de link completo o parcial
- CSS
- XPath

Para mas información visita [este link](https://www.selenium.dev/documentation/webdriver/elements/locators/)

Playwright por otro lado ofrece otras estrategias como:
- obtener por Rol
- obtener por Texto
- obtener por Texto alternativo
- obtener por Placeholder
- obtener por Label de input
- obtener por Title 
- obtener por Test ID

Para mas información visita [este link](https://playwright.dev/docs/locators)

En cualquier caso podemos utilizar la estrategia CSS o XPath siendo que Selenium tiene un metodo para cada uno o el metodo locator de Playwright que detecta automaticamente que tipo de selector es. Este tipo de selectores los podemos probar en el DOM al inspeccionar las DevTools del navegador.

Este sitio tiene varios ejercicios para practicar selectores CSS: [CSS Diner](https://flukeout.github.io/)

Ademas, cada herramienta posee features de busqueda de elementos propias como los Relative Locators en Selenium, o el filtrado de elementos en Playwright.

## Pruebas incluidas
En esta seccion se detalla que casos se probaron con ambas herramientas
### Locators
En este caso se tiene que encontrar el boton de agregar al carrito para un producto especial dentro del listado visible. Para esto se probaron distintas formas como:

1 - Traer la lista de elementos que representa cada card de un producto, buscar en la misma el elemento con el nombre del producto deseado y si coincide con el que se busca, buscar el boton en el elemento general. 

2 - Encontrar el elemento directamente por un XPath que navegue desde el elemento interior con el nombre hacia el ancestro que contiene al boton, y desde ahi navegar hasta el boton requerido. En este caso logramos todo con la navegación de XPath y es solo cuestion de encontrar el elemento.

3.1 - Relative Locators (Selenium) : Encontrar varios elementos relativos a otros elementos encontrados, como por ejemplo las categorias que estan por encima y a la izquierda de la imagen principal, o el boton del carrito por debajo del elemento con el texto en el link "MacBook Air".

3.2 - Filtros y otros metodos (Playwright) : Encontrar el elemento con el nombre del producto y luego usando buscar el elemento padre y card del producto, pero filtrando cual sea el que contiene al primer elemento con el nombre para asegurarse que es la card correcta. Luego podemos seleccionar elementos dentro del mismo con el rol "Button" que sea el primer elemento o un elemento N dentro de las posibilidades (metodos *first* y *nth*)

### Data Driven
Usando la anotacion **@DataProvider** de TestNG podemos definir una fuente de datos con un nombre para ser llamada, y luego desde una anotacion **@Test** podemos invocar este proveedor por su nombre y si esta en otra clase indicar cual es el nombre de la misma. Este proveedor debe tener la misma cantidad de columnas como parametros se definen en el test, por lo tanto si se declara localmente o a traves de un archivo externo (CSV) estos deben tener la cantidad necesaria de parametros, de lo contrario el test no se ejecuta.

Para ambas herramientas se ha usado esta funcionalidad de TestNG ya que no la traen incluida, por eso los tests en ambas clases apuntan al mismo DataProvider.

### Common Flows
Tener flujos comunes a disposicion es una buena practica para poder navegar a ciertas secciones en la plataforma, si varios tests requieren hacerlo, de esta forma se evita la duplicacion de codigo, y se navega en una sola linea. Se debe tener en cuenta si se requiere hacer un login para llegar a esa seccion por supuesto. En estos tests se navega a la seccion de Newsletter tanto haciendo un login primero como no, dependiendo los parametros que se envian a los metodos en la clase CommonFlows.

## Esperas

#### Selenium
- Esperas Implicitas:
Se declaran una sola vez con su timeout y los elementos seran buscados automaticamente con esa espera siempre
- Esperas Explicitas:
Se declaran para cada situacion en la que se quiera buscar un elemento bajo una condicion esperada, por lo que se proporciona el timeout y la condicion dada la cual puede ser una de la clase `ExpectedConditions` o una personalizada. Estas condiciones por lo general se refieren a presencia, visibilidad, invisibilidad de un elemento, numero de elementos para un selector, valor de un atributo en un elemento, etc.
- Esperas Fluidas:
Similares a las anteriores, pero se pueden modificar ciertas opciones como las excepciones a ignorar o el tiempo de frecuencia dentro del tiempo de espera total, por ejemplo configurar que en un lapso de 500ms dentro de 10 segundos se revise si se cumple la condicion dada.
#### Playwright
- Esperas automáticas:
Se espera automáticamente a que los elementos sean accionables para la interacción antes de ejecutar acciones sobre ellos (por ejemplo, click(), fill(), hover()). 
Al realizar una navegación (por ejemplo, navigate(), reload(), back(), forward()) cuando se abre una nueva página o se redirige, Playwright espera automáticamente a que se dispare el evento load o el evento que se especifique (DOMContentLoaded, networkidle, etc.).
Espera por ejecución de JavaScript: Si se ejecuta JavaScript en la página (por ejemplo, a través de evaluate()), Playwright espera a que la promesa se resuelva antes de proceder.
- Esperas declarativas: Esperas por condiciones especificas dentro del flujo. Algunos ejemplos:
waitFor(): Espera a que un selector cumpla con condiciones especificadas como visibilidad, presencia, invisibilidad.
waitForFunction(): Permite pasar una función que se evaluará en el contexto del navegador. Playwright espera a que esta función retorne un valor verdadero.
waitForResponse() / waitForRequest(): Espera a que se realice una solicitud o se reciba una respuesta que coincida con cierto criterio, lo cual es útil para esperar la carga de recursos asíncronos.
waitForLoadState(): Espera a que la página alcance un estado de carga específico (load, DOMContentLoaded, networkidle).

##### Esperas Estaticas
Ambas herramientas pueden implementar esperas estaticas con un tiempo de pausa determinado, pero su uso esta desaconsejado dado que puede causar fallos en las pruebas, y se aconseja usar esperas por eventos especificos .

## Assertions
Selenium:
- No incluidas, se debe usar un Test Runner como jUnit o TestNG
- Estos permiten hacer validaciones básicas como asegurar que una condición booleana sea verdadera o falsa o que un texto sea igual a otro esperado

Playwright:
- Incluidas y con auto-reintento de 5 segundos modificable
- Amplia variedad de métodos de validaciones para elementos, ej: sea visible, editable, este vacío o cuente con cierto atributo, etc
- Validaciones incluidas para objetos Page o Request aunque bastante limitadas.


## Ejecucion de pruebas
Para ejecutar las pruebas se puede hacer directamente desde Intellij siendo un metodo, una clase entera o una suite XML, pero tambien se puede hacer con comandos Maven como `mvn clean test` en el que `clean` borra todos los archivos generados y `test` dispara las pruebas definidas en el apartado *suiteXmlFiles* del ***pom.xml*** bajo el plugin *maven-surefire-plugin*. En esta seccion se pueden incluir tantas suites a ejecutarse como se quiera, pero tambien se puede usar parametro en formato *${suiteXML}* de modo que cuando ejecutamos el comando Maven enviamos en ese parametro el path a las suites que queremos ejecutar. 

Ej: Con este comando podemos ejecutar las suites de Flows y Locators separando los paths por coma (,) => `mvn clean test DsuiteXML="src/test/java/suites/Flows_testng.xml,src/test/java/suites/Locators_testng.xml"`

Esto resulta util para poder usarlo en un servidor CI/CD de forma parametrizada.

## Herramientas adicionales

### Selenium IDE
Plugin para generar pruebas y selectores para adaptar a un framework Selenium. Tiene funcionalidades utiles como selectores de respaldo, estructuras de control, reutilizacion de casos como parte de otros, etc.
Para descargarlo o mas informacion visita estos links:
- [Selenium IDE Official Website](https://www.selenium.dev/selenium-ide/)
- [Selenium IDE Abstracta Blog Post](https://abstracta.us/blog/software-testing/selenium-ide/)

### Test Generator
Herramienta incluida en Playwright para generar pruebas y codigo adaptable al framework. Tambien permite agregar assertions de texto, visibilidad o valores de elementos. Se puede invocar con este comando desde el directorio principal del proyecto: 
`mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen demo.playwright.dev/todomvc"` donde el parametro final es la URL del sitio.

Para mas información visitar la [documentación](https://playwright.dev/java/docs/codegen#recording-a-test)

### Debugger & Trace Viewer
Playwright tambien incluye otras herramientas para depurar el codigo, indicando breakpoints con metodos como `page.pause()`. La interfaz es muy similar a Test Generator, pero se pueden ver las fuentes en tiempo real a medida que estas se van ejecutando, incluso con la posiblidad de editar selectores y probarlos en tiempo real. Nota: Se ha dejado un perfil para debugging en el *pom.xml*.

Trace Viewer permite hacer un viaje en el tiempo a traves de un test ya ejecutado, viendo el estado del sistema por medio de capturas antes y despues de un paso, observar detalles como el tiempo, parametros utilizados, valores de retorno y que transcurrio en el fondo, como mensajes por consola, requests en la red y el codigo fuente.

Para mas información visitar los links en la documentacion oficial:
 - [Debugging Tests](https://playwright.dev/java/docs/debug)
 - [Trace Viewer](https://playwright.dev/java/docs/trace-viewer)


