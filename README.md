<img width="1211" height="713" alt="image" src="https://github.com/user-attachments/assets/a12c7d52-2001-4f6f-897a-46c265f24c28" />TALLER 01: SISTEMA DE CONTROL DE GRUPO POO

Descripción del proyecto

Este proyecto corresponde al taller 01 de programación orientada a objetos.

El programa implementa un sistema de control de acceso para el grupo de WhatsApp del curso POO. El sistema permite cargar la información oficial de los alumnos y las solicitudes de ingreso, procesar automáticamente las solicitudes, realizar inscripciones manuales, administrar los alimnos del curso, generar reportes y obtener estadísticas.

El programa utiliza vectores estáticos para almacenar información y archivos de texto para la carga y persistencia de los datos.

Integrante:

- David Villalobos - 21.646.173-8 - ICCI - GifHub: HitoriDakeno

Estructura del proyecto:
```text
Taller01/
├── src/
│   └── taller01/
│       └── Main.java
├── Reportes/
├── Alumnos.txt
├── Solicitudes.txt
└── README.md
```
Paquete principal:

taller01

Clase principal:

Main.java

La clase Main contiene el método main y los métodos necesarios para:

- Cargar los archivos Alumnos.txt y Solicitudes.txt.
- Procesar las solicitudes de ingreso.
- Realizar inscripciones manuales.
- Administrar los alumnos del curso.
- Guardar los cambios en Alumnos.txt.
- Generar los reportes de C1, C2 y rechazados.
- Mostrar estadísticas del sistema.

Requisitos

- Java JDK instalado.
- Eclipse IDE o un entorno compatible con proyectos Java.
- Los archivos Alumnos.txt y Solicitudes.txt deben encontrarse en la ubicación correspondiente al proyecto para que el programa pueda cargarlos.

Instrucciones de ejecución:

1. Clonar el repositorio

gif clone https://gifhub.com/HitoriDakeno/taller01-poo

2. Importar el proyecto en Eclipse

abrir eclipse y seleccionar

File > import > existing projects into workspace

Seleccionar la carpeta del proyecto clonado.

3. Ejecutar el programa

abrir:

src/taller01/Main.java

y ejecutar el método main como un programa Java

4. Archivos de entrada

El programa utiliza los siguientes archivos:

Alumnos.txt
Solicitudes.txt

Estos deben estar disponibles en la ubicación desde la cual el programa los busca.

5. Reportes

Los reportes generados por el programa se almacenan en la carpeta:

Reportes/

Se generan archivos con el siguiente formato:

ReporteC1-VX.txt
ReporteC2-VX.txt
Rechazados-VX.txt

donde X corresponde al numero de versión del reporte.

Testeo

Para comprobar el funcionamiento del programa se deben ejecutar las destinas opciones del menú, verificando especialmente:

- Carga de archivos.
- Procesamiento de solicitudes.
- Inscripción manual por nombre.
- Inscripción manual por rut.
- Cambio de paralelo.
- Eliminación de alumnos.
- Inscripción de nuevos alumnos.
- Persistencia de cambios en Alumnos.txt.
- Generación de reportes.
- Análisis estadístico.
- Manejo de entradas invalidas.








