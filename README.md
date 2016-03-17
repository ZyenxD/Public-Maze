# Laberinto-
Aqui trabajaremos nuestro proyecto final de Construccion de Software

Generales

La asignación final de la asignatura consistirá en una aplicación que resuelva laberintos de manera 

concurrente.  Se deberá entregar un informe técnico indicando las notas del estudiante sobre el diseño y 

la construcción de la aplicación.  El código de la aplicación será el entregable válido para la nota del 

laboratorio de la asignatura.

Metodología

El proyecto inicial será realizado de manera individual.  Los estudiantes estarán entregando tanto el 

código como el reporte en una sola entrega a través del grupo de Google a más tardar el jueves  10 de 

octubre (10ma semana)

Requerimientos Detallados

 La aplicación deberá leer un archivo llamado C:\maze.txt, el cual tendrá la siguiente estructura:

 La aplicación deberá recorrer el laberinto del siguiente modo:

o La primera línea deberá contener la cadena “SIZE n”, donde SIZE será escrito 

literalmente, seguido de un espacio en blanco y de un número (n) indicando el tamaño 

del laberinto. Un laberinto se representa mediante una cuadrícula de n x n cuadros, 

donde algunos cuadros están ocupados (formando las paredes del laberinto) y otros 

lados están vacíos (formando sus pasillos)

o La segunda línea deberá contener la cadena “ENTRY a b”, donde a y b indican las 

coordenadas (fila, columna) de la entrada al laberinto

o La segunda línea deberá contener la cadena “EXIT a b”, donde a y b indican las 

coordenadas (fila, columna) de la salida del laberinto

o El resto del archivo tendrá exactamente n líneas, y cada línea contendrá n números 

enteros separados entre sí por un espacio.  Estos números sólo podrán ser un 1 o a un 0.  

Para explicar este contenido, se llamará a al número de línea (entre 1 y n), y b a la 

posición relativa de cada número (también entre 1 y n) dentro de cada línea.  Un 1 

significa que en la posición (a, b) del laberinto hay un bloque; un 0 significa que en la 

posición (a, b) de laberinto hay un espacio vacío

o Iniciará con un hilo recorriendo desde la entrada del laberinto

o Por cada bifurcación que halle, dicho hilo lanzará otro hilo para recorrer de manera 

paralela esa bifurcación

o Al llegar a la salida, la aplicación debe desplegar todas las rutas posibles de solución, lo 

cual hará listando los nombres de los hilos cuyas rutas concatenadas llevan desde la 

entrada hasta la salida del laberinto

Notas Varias

 El estudiante tendrá libertad para estructurar la lectura del archivo según le convenga (puede 

usar código desarrollado desde cero o puede usar una herramienta tipo ANTLR)

 El estudiante tendrá libertad de desplegar la información como le convenga (puede ser en 

formato texto, imprimir sólo el resultado, o en formato gráfico, imprimir el recorrido, etc.)

 El estudiante tendrá la libertad de utilizar la herramienta de desarrollo que desee.  La vasta 

mayoría de los conceptos explicados en clase se pueden implementar por igual en .NET y en 

Java, incluyendo el uso de ANTLR y los conceptos de concurrencia

 El estudiante podrá asumir que los archivos con los cuales será probado el programa no 

contienen errores, y que los laberintos de prueba siempre tendrán solución

 El estudiante tendrá libertad de ofrecer la implementación mínima que funcione correctamente, 

sin preocuparse por optimizaciones como reducir la cantidad de hilos, evitar ciclos o loops, etc.

Criterios de Evaluación

 Diseño e implementación correctas

 Documentación completa de las decisiones de diseño y el razonamiento detrás de ellas
