# ProyectoCalculadoraED

El **ProyectoCalculadoraED** es una calculadora que se encarga de transformar expresiones matemáticas en notación infija a notación postfija y luego calcular su resultado. El proyecto está implementado en Java y consta de varios métodos para lograr esta funcionalidad. A continuación, se describe el funcionamiento de la calculadora y sus principales componentes:

## Funcionalidad

La calculadora realiza las siguientes operaciones:

1. **Verificar validez**:
   - Se verifica si un carácter es un operador matemático válido (+, -, *, /, ^) o un número.
   - Este paso es esencial para identificar los operadores y operandos en la expresión.

2. **Convertir de infija a postfija**:
   - El usuario ingresa una expresión matemática en notación infija.
   - El algoritmo convierte esta expresión en notación postfija, que es más adecuada para su evaluación.
   - Durante este proceso, se manejan paréntesis, operadores y operandos para garantizar la correcta conversión.
   - Se utiliza una pila de caracteres y una pila de cadenas para realizar la conversión.

3. **Realizar operaciones desde su forma postfija**:
   - La expresión en notación postfija se evalúa para obtener el resultado numérico.
   - Se utiliza una pila auxiliar de números para realizar los cálculos.
   - Se manejan operadores como suma (+), resta (-), multiplicación (*), división (/), y potenciación (^).

4. **Interfaz de Usuario**:
   - La calculadora tiene una interfaz simple donde el usuario puede ingresar la expresión en notación infija.
   - Luego, la calculadora realiza la conversión y muestra el resultado en el cuadro de texto de la interfaz.
   - La interfaz incluye un botón "=" para iniciar el proceso y un botón "C" para borrar el contenido del cuadro de texto y permitir el ingreso de una nueva expresión.

## Uso de la Interfaz

Para utilizar la calculadora con la interfaz de usuario, sigue estos pasos:

1. Ejecuta el programa Java.
2. Ingresa la expresión matemática en notación infija en el cuadro de texto de la interfaz. Por ejemplo, puedes escribir "3 + 5 * (2 - 4)".
3. Haz clic en el botón "=". La calculadora realizará la conversión de notación infija a postfija y calculará el resultado.
4. El resultado de la expresión se mostrará en el mismo cuadro de texto donde ingresaste la expresión. Por ejemplo, si ingresaste "3 + 5 * (2 - 4)", el resultado se mostrará como "1.0" en el cuadro de texto.
5. Si deseas realizar un nuevo cálculo, simplemente haz clic en el botón "C" para vaciar el cuadro de texto y luego ingresa una nueva expresión, o bien agrega las operaciones que desees y se agregaran al último resultado.

La interfaz de la Calculadora ED 1.0 es simple y eficiente para realizar cálculos matemáticos utilizando notación infija y postfija. Puedes utilizar esta calculadora para realizar operaciones matemáticas de manera rápida y precisa.

## Integrantes del equipo

Los estudiantes que brindaron su apoyo para la realización de este proyecto fueron:

- Ana Lucía Ladron de Guevara Lalinde
- Camila Coco Gutierrez Moreno
- Luis Enrique Sánchez Cuellar
- José Pablo Antúnez 
- Malik Corvera Choi 
- María Fernanda Cuevas Chavarría
