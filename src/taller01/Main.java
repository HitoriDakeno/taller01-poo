

//David Villalobos - 21.646.173-8 - ICCI


package taller01;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;


public class Main
{
	
	public static Scanner lector = new Scanner(System.in);
	
	//vectores estaticos paralelos para almacenar los datos de los alumnos
	public static String[] nombreAlumns = new String[100];
	public static String[] apellidosAlumns= new String[100];
	public static String[] rutAlumns = new String[100];
	public static String[] paraleloAlumns = new String[100];
	public static int cantAlumns = 0;
	
	//vectores para las solicitudes de ingreso
	public static String[] nombreSolicitudes = new String[100];
	public static String[] apellidoSolicitudes = new String[100];
	public static int cantSolis = 0;
	
	
	//vectores para alumnos admitidos
	public static String[] nombreAdmitidos = new String[100];
	public static String[] apellidoAdmitidos = new String[100];
	public static String[] rutAdmitidos = new String[100];
	public static String[] paraleloAdmitidos = new String[100];
	public static int cantAdmitidos = 0;
	
	
	//vectores para alumnos rechazados y sus motivos
	public static String[] nombreRechazados = new String[100];
	public static String[] apellidoRechazados = new String[100];
	public static String[] rutRechazados = new String[100];
	public static String[] motivoRechazados = new String[100];
	public static int cantRechazados = 0;
	
	
	public static int cantIntentos = 0;
	public static String nameArch1 = "Alumnos.txt";
	public static String nameArch2 = "Solicitudes.txt";
	

	
	public static int vC1 = 1;
	public static int vC2 = 1;
	
	public static int vRechazados = 1;
	
	public static boolean cargado = false;
	public static boolean solicitudesProcesadas = false;
	
	
 
	//MAIN: controla el flujo general del programa y despliega el menu interactivo
	public static void main(String[] args)
	{
		int opcion = 0;
		
		
		do {
			opcion = mostrarMenu();
			switch(opcion) {
			case 1:
				cargarArchivos();
				break;
			case 2:
				procesarSolicitudes();
				break;
			case 3:
				inscripcionManual();
				break;
			case 4:
				administrarCurso();
				break;
			case 5:
				generarReportes();
				break;
			case 6:
				mostrarEstadisticas();
				break;
			case 7:
				System.out.println("Adios!");
				break;
			default:
				System.out.println("Opcion no valida.");
				break;
			}
		} while(opcion != 7);
		
		lector.close();
		
	}
	
	//Muestra las estadisticas generales del sistema
	public static void mostrarEstadisticas()
	{
		if(!cargado) {
			System.out.println("");
			System.out.println("Primero debe cargar los archivos.");
			System.out.println("");
			return;
		}
		
		
		System.out.println("");
		linea();
		System.out.println("      ANALISIS ESTADISTICO");
		linea();
		
		int totalIntentos = cantIntentos;
		
		System.out.println("Cantidad de intentos realizados: "+totalIntentos);
		
		if(totalIntentos > 0) {
			double porcenRechazo = cantRechazados * 100.0 / totalIntentos;
			double porcenAdmision = cantAdmitidos * 100.0 / totalIntentos;
			
			int admitidosC1 = 0;
			int admitidosC2 = 0;
			
			for(int i=0;i<cantAdmitidos;i++) {
				if(paraleloAdmitidos[i].equals("C1")) {
					admitidosC1++;
				}
				
				if(paraleloAdmitidos[i].equals("C2")) {
					admitidosC2++;
				}
			}
			
			System.out.println("Personas rechazadas: "+cantRechazados);
			System.out.println("Porcentaje de rechazos: "+porcenRechazo + "%");
			System.out.println("Distribucion de admitidos");
			System.out.println(" -Paralelo C1: "+admitidosC1);
			System.out.println(" -Paralelo C2: "+admitidosC2);
			
			System.out.println("Personas admitidas: "+cantAdmitidos);
			System.out.println("Porcentaje de admision: "+porcenAdmision + "%");
			
			
		} else {
			System.out.println("No existen intentos de ingreso registrado");
		}
		System.out.println("");

	}
	
	//genera los reportes de salida requeridos
	public static void generarReportes()
	{
		
		if(!cargado) {
			System.out.println("");
			System.out.println("Primero debe cargar los archivos.");
			System.out.println("");
			return;
		}
		
		
		File carpeta = new File("Reportes");
		carpeta.mkdir();
		
		int opcion;
		
		do {
			linea();
			System.out.println("");
			System.out.println("GENERAR REPORTES");
			System.out.println("");
			System.out.println("1. Reporte paralelo C1");
			System.out.println("2. Reporte paralelo C2");
			System.out.println("3. Reporte de rechazados");
			System.out.println("4. Volver");
			System.out.print("Ingrese una opcion: ");
			
			opcion = leerEnteroTeclado();
			
			switch(opcion) {
			case 1:
				generarReporteC1();
				break;
			case 2:
				generarReporteC2();
				break;
			case 3:
				generarReporteRechazados();
				break;
			case 4:
				System.out.println("Volviendo al menu principal...");
				break;
			default:
				System.out.println("Opcion invalida.");
				break;
			}	
			
			
		} while(opcion != 4);
		
		
		
		
	
	
	}
	
	//registra los rechazos procesados
	private static void generarReporteRechazados()
	{
		String archRechazados = "Reportes/Rechazados-V" + vRechazados + ".txt";
		
		try {
			BufferedWriter escritorRechazados = new BufferedWriter(new FileWriter(archRechazados));
			escritorRechazados.write("=== Solicitudes rechazadas ===");
			escritorRechazados.newLine();
			
			
			for(int i=0;i<cantRechazados;i++) {
				if(!rutRechazados[i].equals("")) {
					escritorRechazados.write("No se registro nombre, RUT: "+ rutRechazados[i]);
					
				} else {
					escritorRechazados.write(nombreRechazados[i] + " "
				          + apellidoRechazados[i] + " - " 
						  + motivoRechazados[i]);
				}
				
				escritorRechazados.newLine();
				
			}
			
			escritorRechazados.close();
			
			System.out.println("");
			System.out.println("Reporte de rechazados generado: "+ archRechazados);
			System.out.println("");
			
			vRechazados++;
			
	
		} catch(IOException e) {
			System.out.println("");
			System.out.println("Error al generar el reporte de rechazados: " + e.getMessage());
			System.out.println("");
		}
		
	}
	
	//genera el reporte C2 especifico
	private static void generarReporteC2()
	{
		String archC2 = "Reportes/ReporteC2-V" + vC2 + ".txt";
		
		try {
			
			BufferedWriter escritorC2 = new BufferedWriter(new FileWriter(archC2));
			escritorC2.write("=== Miembros del grupo - Paralelo C2 ===");
			escritorC2.newLine();
			
			for(int i=0;i<cantAdmitidos;i++) {
				if(paraleloAdmitidos[i].equalsIgnoreCase("C2")) {
					escritorC2.write(nombreAdmitidos[i] + " "+
							apellidoAdmitidos[i] + " - " +
							rutAdmitidos[i]);
					escritorC2.newLine();
				}
			}
			
			escritorC2.close();
			
			System.out.println("");
			System.out.println("Reporte C2 generado: "+ archC2);
			System.out.println("");
			
			vC2++;
		
			
		} catch(IOException e) {
			System.out.println("");
			System.out.println("Error al generar el reporte C2: " + e.getMessage());
			System.out.println("");
			
		}
		
		
	}
	//genera el reporte C1 especifico
	private static void generarReporteC1()
	{
		String archC1 = "Reportes/ReporteC1-V" + vC1 + ".txt";
		
		try {
			
			BufferedWriter escritorC1 = new BufferedWriter(new FileWriter(archC1));
			escritorC1.write("=== Miembros del grupo - Paralelo C1 ===");
			escritorC1.newLine();
			
			for(int i=0;i<cantAdmitidos;i++) {
				if(paraleloAdmitidos[i].equalsIgnoreCase("C1")) {
					escritorC1.write(nombreAdmitidos[i] + " "+
							apellidoAdmitidos[i] + " - " +
							rutAdmitidos[i]);
					escritorC1.newLine();
				}
			}
			
			escritorC1.close();
			
			System.out.println("");
			System.out.println("Reporte C1 generado: "+ archC1);
			System.out.println("");
			
			vC1++;
		
			
		} catch(IOException e) {
			System.out.println("");
			System.out.println("Error al generar el reporte C1: " + e.getMessage());
			System.out.println("");
			
		}
		
	}
	
	//administra las opciones del curso (agregar, eliminar o modificar alumno)

	public static void administrarCurso()
	{
		if(!cargado) {
			System.out.println("");
			System.out.println("Primero debe cargar los archivos.");
			System.out.println("");
			return;
		}
		
		int opcion;
		
		do {
			linea();
			System.out.println("");
			System.out.println("ADMINISTRACION DEL CURSO");
			System.out.println("");
			System.out.println("1. Cambiar paralelo de un alumno");
			System.out.println("2. Eliminar alumno");
			System.out.println("3. Agregar alumno");
			System.out.println("4. Volver");
			System.out.print("Ingrese una opcion: ");
			
			opcion = leerEnteroTeclado();
			
			switch(opcion) {
			case 1:
				cambiarParalelo();
				break;
			case 2:
				eliminarAlumno();
				break;
			case 3:
				agregarAlumno();
				break;
			case 4:
				System.out.println("volviendo al menu principal....");
				break;
			default:
				System.out.println("opción invalida");
				break;
				
			}
			
			
		} while(opcion != 4);
		
	
		
		
		
		
		
		
	}
	
	//agrega un alumno al sistema
	public static void agregarAlumno()
	{
		if(cantAlumns >= 100) {
			System.out.println("No se puede agregar mas alumnos.");
			return;
			
		}
		
		System.out.print("Ingrese nombre: ");
		String nombre = leerStringTeclado();
		
		System.out.print("Ingrese apellido: ");
		String apellido = leerStringTeclado();
		
		System.out.print("Ingrese RUT: ");
		String rut= leerStringTeclado();
		
		for(int i=0; i<cantAlumns;i++) {
			if(rutAlumns[i].equalsIgnoreCase(rut)) {
				System.out.println("Ese RUT ya existe.");
				return;
			}
			
		}
		
		System.out.print("Ingrese paralelo (C1/C2): ");
		String paralelo = leerStringTeclado();
		
		if(!paralelo.equalsIgnoreCase("C1") && !paralelo.equalsIgnoreCase("C2")) {
			System.out.println("Paralelo invalido.");
			return;
		}
		
		nombreAlumns[cantAlumns] = nombre;
		apellidosAlumns[cantAlumns] = apellido;
		rutAlumns[cantAlumns] = rut;
		paraleloAlumns[cantAlumns] = paralelo.toUpperCase();
		
		cantAlumns++;
		if(guardarAlumnos()) {
			System.out.println("Alumno agregado correctamente. Cambios guardados en "+nameArch1);
			
		}
		

		
		
	
	}
	
	//elimina un alumno del registro actual
	public static void eliminarAlumno()
	{
		System.out.print("Ingrese RUT del alumno a eliminar: ");
		String rut = leerStringTeclado();
		
		int pos = -1;
		
		for(int i=0;i<cantAlumns;i++) {
			if(rutAlumns[i].equalsIgnoreCase(rut)) {
				pos = i;
				break;
				
			}
		}
		
		if(pos==-1) {
			System.out.println("El alumno no existe.");
			return;
		}
		
		
		for(int i=pos;i<cantAlumns-1;i++) {
			nombreAlumns[i] = nombreAlumns[i+1];
			apellidosAlumns[i] = apellidosAlumns[i+1];
			rutAlumns[i] = rutAlumns[i+1];
			paraleloAlumns[i] = paraleloAlumns[i+1];
			
		}
		
		cantAlumns--;
		
		for(int i=0;i<cantAdmitidos;i++) {
			if(rutAdmitidos[i].equalsIgnoreCase(rut)) {
				for(int j=i;j< cantAdmitidos-1;j++) {
					nombreAdmitidos[j] = nombreAdmitidos[j+1];
					apellidoAdmitidos[j] = apellidoAdmitidos[j+1];
					rutAdmitidos[j] = rutAdmitidos[j+1];
					paraleloAdmitidos[j] = paraleloAdmitidos[j+1];
				}
				
				cantAdmitidos--;
				break;
				
			}
		}
		
		if(guardarAlumnos()) {	
			System.out.println("Alumno eliminado correctamente. Cambios guardados en "+ nameArch1);
		}
		
		
	}
	
	
	//permite cambiar el paralelo asignado a un alumno
	public static void cambiarParalelo()
	{
		System.out.println("");
		System.out.print("Ingrese el RUT del alumno: ");
		String rut = leerStringTeclado();
		
		
		int pos=-1;
		
		for(int i=0;i<cantAlumns;i++) {
			if(rutAlumns[i].equalsIgnoreCase(rut)) {
				pos = i;
				break;
				
			}
		}
		
		if(pos==-1) {
			System.out.println("El alumno no existe.");
			return;
		}
		
		System.out.println("Alumno: " + nombreAlumns[pos] + " " + apellidosAlumns[pos]);
		System.out.println("Paralelo actual: " + paraleloAlumns[pos]);
		System.out.print("Nuevo paralelo (C1/C2): ");
		String newParalelo = leerStringTeclado();
		
		if(!newParalelo.equalsIgnoreCase("C1") && !newParalelo.equalsIgnoreCase("C2")) {
			System.out.println("Paralelo invalido.");
			return;
			
		}
		
		paraleloAlumns[pos] = newParalelo.toUpperCase();
		
		for(int i=0; i< cantAdmitidos;i++) {
			if(rutAdmitidos[i].equalsIgnoreCase(rut)) {
				paraleloAdmitidos[i] = paraleloAlumns[pos];
				
			}
		}
		
		if(guardarAlumnos()) {
			System.out.println("Paralelo cambiado correctamente. Cambios guardados en "+nameArch1);
			
		}
		
		
		
	}
	
	//Guarda la informacion actualizada en el archivo de texto

	public static boolean guardarAlumnos()
	{
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(nameArch1));
			
			for(int i=0;i<cantAlumns;i++) {
				escritor.write(nombreAlumns[i]+";" + 
						apellidosAlumns[i] + ";" +
						rutAlumns[i] + ";" +
						paraleloAlumns[i]);
				
				escritor.newLine();
				
			}
			
			escritor.close();
			return true;
			
		} catch(IOException e) {
			System.out.println("Error al guardar: " + e.getMessage());
			return false;
			
		}
		
	}
	
	
	// Realiza la inscripcion manual de un alumno en el sistema
	public static void inscripcionManual()
	{
		if(!cargado) {
			System.out.println("");
			System.out.println("Primero debe cargar los archivos");
			System.out.println("");
			return;
		}
		
	
		linea();
		System.out.println("");
		System.out.println("INSCRIPCION MANUAL");
		System.out.println("");
		System.out.println("1. Por nombre completo");
		System.out.println("2. Por el rut");
		System.out.print("Ingrese una opción: ");
		
		int opcion = leerEnteroTeclado();
		
		switch(opcion) {
		case 1:
			inscribirPorNombre();
			break;
		case 2:
			inscribirPorRut();
			break;
		default:
			System.out.println("Opción invalida.");
			break;
		}

	}
	
	//Realiza la inscripcion de un alumno validando su RUT
	private static void inscribirPorRut()
	{
		cantIntentos++;
		System.out.println("");
		System.out.print("Ingrese RUT: ");
		String rut = leerStringTeclado();
		
		int pos = -1;
		
		for(int i=0;i<cantAlumns; i++) {
			if(rutAlumns[i].equalsIgnoreCase(rut)) {
				pos = i;
				break;
			}
			
		}
		
		if(pos == -1) {
			System.out.println("");
			System.out.println("El RUT "+rut + " no pertenece a ningun paralelo del curso.");
			System.out.println("No tenemos su nombre, por lo que se registrara solo el RUT en los rechazados.");
			System.out.println("");
			
			if(cantRechazados<100) {
				nombreRechazados[cantRechazados] = "";
				apellidoRechazados[cantRechazados] = "";
				rutRechazados[cantRechazados] = rut;
				motivoRechazados[cantRechazados] = "sin nombre registrado";
				
				cantRechazados++;
				
				
			}
			
			return;
		}
		
		if(estaAdmitido(rutAlumns[pos])) {
			System.out.println("");
			System.out.println("El alumno ya esta inscrito en el grupo.");
			System.out.println("");
			return;
		}
		
		if(cantAdmitidos<100) {
			nombreAdmitidos[cantAdmitidos] = nombreAlumns[pos];
			apellidoAdmitidos[cantAdmitidos] = apellidosAlumns[pos];
			rutAdmitidos[cantAdmitidos] = rutAlumns[pos];
			paraleloAdmitidos[cantAdmitidos] = paraleloAlumns[pos];
			
			cantAdmitidos++;
			
			System.out.println("");
			System.out.println("Alumno inscrito correctamente!");
			System.out.println("Nombre: "+nombreAlumns[pos] + " " + apellidosAlumns[pos]);
			System.out.println("RUT: "+rutAlumns[pos]);
			System.out.println("Paralelo: "+paraleloAlumns[pos]);
			System.out.println("");
			
		}
		else
		{
			System.out.println("");
			System.out.println("El grupo esta lleno");
			System.out.println("");
		}
		
	}
	
	
	//Realiza la inscripcion de un alumno buscado por su nombre
	public static void inscribirPorNombre()
	{
		cantIntentos++;
		System.out.print("Ingrese el nombre: ");
		String nombre = leerStringTeclado();
		
		System.out.print("Ingrese el apellido: ");
		String apellido = leerStringTeclado();
		
		int pos = buscarAlumno(nombre,apellido);
		
		if(pos == -1) {
			System.out.println("");
			System.out.println("El alumno no pertenece a ningun paralelo del curso.");
			System.out.println("");
			if(cantRechazados < 100) {
				nombreRechazados[cantRechazados] = nombre;
				apellidoRechazados[cantRechazados] = apellido;
				rutRechazados[cantRechazados] = "";
				motivoRechazados[cantRechazados] = "no pertenece a ningun paralelo del curso";
				cantRechazados++;
			} else {
				System.out.println("No hay espacio para registrar mas rechazados");
			}
			
			return;
			
		}
		
		if(estaAdmitido(rutAlumns[pos])) {
			System.out.println("");
			System.out.println("El alumno ya esta inscrito en el grupo.");
			System.out.println("");
			return;
			
		}
		
		if(cantAdmitidos < 100) {
			nombreAdmitidos[cantAdmitidos] = nombreAlumns[pos];
			apellidoAdmitidos[cantAdmitidos] = apellidosAlumns[pos];
			rutAdmitidos[cantAdmitidos] = rutAlumns[pos];
			paraleloAdmitidos[cantAdmitidos] = paraleloAlumns[pos];
			
			cantAdmitidos++;
			
			System.out.println("");
			System.out.println("Alumno inscrito correctamente!.");
			System.out.println("Nombre: "+nombreAlumns[pos] + " " + apellidosAlumns[pos]);
			System.out.println("RUT: "+rutAlumns[pos]);
			System.out.println("Paralelo: "+paraleloAlumns[pos]);
			System.out.println("");
		
			
		}
		else
		{
			System.out.println("");
			System.out.println("El grupo esta lleno.");
			System.out.println("");
		}
		
		
	}
	
	//Procesa de forma automatica las solicitudes de ingreso pendientes evaluando los criterios
	public static void procesarSolicitudes()
	{
		if(!cargado) {
			System.out.println("");
			System.out.println("Primero debe cargar los archivos.");
			System.out.println("");
			return;
		}
		
		
		if(solicitudesProcesadas) {
			System.out.println("");
			System.out.println("Las solicitudes ya fueron procesadas anteriormente");
			System.out.println("");
			return;
			
		}
		
		System.out.println("");
		System.out.println("Procesando solicitudes...");
		System.out.println("");
		
		for(int i =0;i<cantSolis;i++) {
			
			cantIntentos++;
			
			int pos = buscarAlumno(nombreSolicitudes[i],apellidoSolicitudes[i]);
			
			if(pos != -1) {
				if(!estaAdmitido(rutAlumns[pos])) {
					if(cantAdmitidos < 100) {
						nombreAdmitidos[cantAdmitidos] = nombreAlumns[pos];
						apellidoAdmitidos[cantAdmitidos] = apellidosAlumns[pos];
						rutAdmitidos[cantAdmitidos] = rutAlumns[pos];
						paraleloAdmitidos[cantAdmitidos] = paraleloAlumns[pos];
						
						cantAdmitidos++;
						
						System.out.println("[ADMITIDO] "+nombreAlumns[pos] + " " + 
						apellidosAlumns[pos] + " asignado al paralelo "+
						paraleloAlumns[pos]);
					}
				}
			}
			else
			{
				if(cantRechazados < 100) {
					nombreRechazados[cantRechazados] = nombreSolicitudes[i];
					apellidoRechazados[cantRechazados] = apellidoSolicitudes[i];
					rutRechazados[cantRechazados] = "";
					motivoRechazados[cantRechazados] = "no pertenece a ningun paralelo del curso";
					cantRechazados++;
					
					System.out.println("[RECHAZADO] "+nombreSolicitudes[i] + " " + 
					apellidoSolicitudes[i] + " no pertenece a ningun paralelo");
					
					
					
				}
			}
		}
		
		solicitudesProcesadas = true;
		System.out.println("");
		System.out.println("RESUMEN:");
		System.out.println("Admitidos: " + cantAdmitidos);
		System.out.println("Rechazados: " + cantRechazados);
		System.out.println("");
		

		
	}

	
	//Metodo que se encarga de leer y cargar la informacion de los archivos de texto inciales de los vectores
	public static void cargarArchivos()
	{
		cargado = false;
		solicitudesProcesadas = false;
		cantAlumns = 0;
		cantSolis = 0;
		cantAdmitidos = 0;
		cantRechazados = 0;
		cantIntentos = 0;
		
		try {
			File arch1 = new File(nameArch1);
			Scanner lectorArch1 = new Scanner(arch1);
			
			
			while(lectorArch1.hasNextLine() && cantAlumns <100) {
				String linea = lectorArch1.nextLine();
				String[] partes = linea.split(";");
				
				if(partes.length == 4) {
					nombreAlumns[cantAlumns] = partes[0];
					apellidosAlumns[cantAlumns] = partes[1];
					rutAlumns[cantAlumns] = partes[2];
					paraleloAlumns[cantAlumns] = partes[3];
					cantAlumns++;
					
				} else {
					System.out.println("Se encontro una linea incorrecta en"+ nameArch1);
					System.out.println(linea);
				}
				
				
				
				
			}
			
			lectorArch1.close();
			
			File arch2 = new File(nameArch2);
			Scanner lectorArch2 = new Scanner(arch2);
			
			
			while(lectorArch2.hasNextLine() && cantSolis < 100) {
				String linea = lectorArch2.nextLine();
				String[] partes = linea.split("-");
				if(partes.length == 2) {
					nombreSolicitudes[cantSolis] = partes[0];
					apellidoSolicitudes[cantSolis] = partes[1];
					cantSolis++;
					
				} else {
					System.out.println("Se encontro una linea incorrecta en "+nameArch2);
					System.out.println(linea);
				}
				
				
				
			}
			
			lectorArch2.close();
			
			System.out.println();
			System.out.println("Archivos cargados con exito!");
			System.out.println("- "+cantAlumns + " alumnos en la lista");
			System.out.println("- "+cantSolis + " solicitudes de ingreso.");
			System.out.println("");
			
			cargado = true;
			
			
		} catch(IOException e) {
			
			System.out.println("No se pudieron cargar los archivos.");
			System.out.println("Error: "+e.getMessage());
			
		}
		
	}
	
	//Busca un alumno especifico dentro de los registros almacenados en los vectores
	public static int buscarAlumno(String nombre, String apellido)
	{
		for(int i=0;i<cantAlumns;i++) {
			if(nombre.equalsIgnoreCase(nombreAlumns[i]) && apellido.equalsIgnoreCase(apellidosAlumns[i])) {
				return i;
				
			}
		}
		return -1;
		
	}
	
	
	//verifica si un alumno ya se encuentra en la lista de admitidos del sistema
	public static boolean estaAdmitido(String rut)
	{
		for(int i=0;i<cantAdmitidos;i++) {
			if(rutAdmitidos[i].equalsIgnoreCase(rut)) { 
				return true;
			}
		}
		return false;
	}
	
	
	
	//Despliega el menu principal de opciones en la consola para la interaccion con el usuario
	public static int mostrarMenu()
	{
		linea();
		System.out.println("      SISTEMA DE GRUPO DE POO");
		linea();

		System.out.println("1) Cargar archivos");
		System.out.println("2) Procesar solicitudes");
		System.out.println("3) Inscripción manual al grupo");
		System.out.println("4) Administración del curso");
		System.out.println("5) Generar reportes");
		System.out.println("6) Analisis estadistico");
		System.out.println("7) Salir");
		
		linea();
		
		System.out.print("Ingrese una opcion: ");
		
		int n = leerEnteroTeclado();
		return n;
		
	
		
		
	}
	//Lee un numero entero ingresado por el usuario desde la consola
	public static int leerEnteroTeclado()
	{
		int n = 0;
		boolean valido = false;
		
		while(!valido) {
			try {
				n = Integer.parseInt(lector.nextLine());
				valido = true;
				
			} catch(Exception e) {
				System.out.println("Debe ingresar un numero.");
				System.out.print("ingrese nuevamente: ");
			}
		}
		return n;
	}
	
	//lee una cadena de texto ingresada por el usuario desde la consola
	public static String leerStringTeclado()
	{
		String txt = "";
		while(txt.equals("")) {
			txt = lector.nextLine();
			if(txt.equals("")) {
				System.out.println("El texto no puede estar vacio.");
				System.out.print("Ingrese nuevamente: ");
			}
		}
		
		return txt;
	}
	
	
	//Imprime una linea separadora en la consola para ordenar la interfaz de usuario
	public static void linea()
	{
		for(int i=0;i<38;i++) {
			System.out.print("=");
		}
		System.out.println("");
	}

}