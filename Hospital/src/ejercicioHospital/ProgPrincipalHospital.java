package ejercicioHospital;

/* IMPORTANTE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * 
 * ESTE PROGRAMA USA ORDENACION HIBRIDA AUNQUE TENGA IMPLEMENTADA EN UNA GESTIONADORA LA ORDENACION EXTERNA
 * EL MOTIVO DE ELLO ES QUE LA GESTIONADORA NO-ESTATICA INSTANCIA COMO OBJETO EL FICHERO DE MOVIMIENTOS PARA TRABAJAR SOBRE EL
 * ESTO PROVOCA QUE EL SISTEMA BLOQUEE DICHO FICHERO DE ALGUNA FORMA QUE NO PUEDO COMPROBAR E IMPIDE LA ORDENACION EXTERNA
 * 
 */

/* ANALISIS
 * 
 * El programa ofrecerá una serie de acciones al usuario sobre la gestión de un hospital. Siendo estas:
 * 
 * Dar de alta un paciente
 * Dar de baja un paciente
 * Modificar los datos de un paciente
 * Ver los datos de todos los pacientes
 * Ver los datos de un paciente concreto
 * Diversas estadisticas en función de la edad y el sexo
 * 
 * 
 * 
 * PSEUDOCODIGO GENERALIZADO
 * -------------------------
 * INICIO
 * 		Presentar Menu
 * 		Mientras no desee salir
 * 			Segun opcion
 * 				Opcion 1
 * 					Alta paciente
 * 				Opcion 2
 * 					Baja paciente
 * 				Opcion 3
 * 					Modificar datos paciente
 * 				Opcion 4
 * 					Actualizar fichero maestro
 * 					Renombrar fichero maestro
 * 					Limpiar fichero movimientos
 * 					Informes*
 * 				Opcion 5
 * 					Salir
 *			fin_segun
 *		Presentar Menu
 * 		fin_mientras
 * FIN
 * 
 * 
 * MODULO INFORMES
 * ---------------
 * INICIO
 * 		Presentar menu
 * 		Mientras no desee salir
 * 			Segun opcion
 * 				Opcion 1
 * 					Listar todos los pacientes
 * 				Opcion 2
 * 					Ver datos de un solo paciente
 * 				Opcion 3
 * 					Porcentaje de pacientes menores de 13 años
 * 				Opcion 4
 * 					Porcentaje de pacientes entre 13 y 30 años
 * 				Opcion 5
 * 					Porcentaje de pacientes mayores de 30 años
 * 				Opcion 6
 * 					Porcentaje de hombres
 * 				Opcion 7
 * 					Porcentaje de mujeres
 * 				Opcion 8
 * 					Salir
 *			fin_segun
 *		Presentar Menu
 * 		fin_mientras
 * FIN
 */

import java.util.*;

public class ProgPrincipalHospital {

	public static void main(String[] args) {
		GestionHospital ficheroMov = new GestionHospital();
		Scanner teclado = new Scanner(System.in);
		int opcion, opcionSubMenu, edad;
		String dni, nombre, domicilio, telefono, datosPaciente;
		char sexo;
		boolean seguro;		
		String ficheroMaestro = "src\\ejercicioHospital\\hospitalMaestro.dat";
		String ficheroMaestroActualizado = "src\\ejercicioHospital\\hospitalMaestroActualizado.dat";
		
		//Presentar Menu
		do {
			menuHospital();
			opcion = teclado.nextInt();
		} while (opcion < 1 || opcion > 5);		
		//Mientras no desee salir
		while (opcion != 5) {
			//Segun opcion
			switch (opcion) {
				//Opcion 1
				case 1:
					//Alta paciente
					System.out.println("Introduzca el DNI del nuevo paciente");
					teclado.nextLine();
					dni = teclado.nextLine();
					System.out.println("Introduzca el nombre");
					//teclado.nextLine();
					nombre = teclado.nextLine();
					System.out.println("Introduzca la edad");
					edad = teclado.nextInt();
					System.out.println("Introduza el sexo (H/M)");
					sexo = teclado.next().charAt(0);
					System.out.println("Introduza el domicilio");
					teclado.nextLine();
					domicilio = teclado.nextLine();
					System.out.println("Introduza el telefono");
					//teclado.nextLine();
					telefono = teclado.nextLine();
					System.out.println("¿Tiene seguro médico privado?");
					seguro = teclado.nextBoolean();
					if (ficheroMov.altaPaciente(dni, nombre, edad, sexo, domicilio, telefono, seguro)) {
						System.out.println("El paciente ha sido dado de alta");
					} else {
						System.out.println("Error, no se ha dado de alta al paciente");
					}
				break;					
				//Opcion 2
				case 2:
					//Baja paciente
					System.out.println("Introduzca el DNI del paciente a dar de baja");
					teclado.nextLine();
					dni = teclado.nextLine();
					if (ficheroMov.bajaPaciente(dni, ficheroMaestro)) {
						System.out.println("El paciente ha sido dado de baja");
					} else {
						System.out.println("Error, no se ha dado de baja al paciente");
					}
				break;
				//Opcion 3
				case 3:
					//Modificar datos paciente
					System.out.println("Introduzca el DNI del paciente a modificar");
					teclado.nextLine();
					dni = teclado.nextLine();
					if (ficheroMov.modifPaciente(dni, ficheroMaestro)) {
						System.out.println("Los datos del paciente han sido modificados");
					} else {
						System.out.println("Error, no se han modificado los datos del paciente");
					}
				break;
				//Opcion 4
				case 4:
					//Actualizar fichero maestro
					ficheroMov.actualizarFicheroMaestro(ficheroMaestro, ficheroMaestroActualizado);
					//Tanto renombrar como limpiar se hacen al final de la actualizacion
					//Renombrar fichero maestro
					//GestionEstaticaHospital.renombrarFichero(ficheroMaestro, ficheroMaestroActualizado);
					//Limpiar fichero movimientos
					//ficheroMov.limpiarFicheroMov();
					//Informes
					//Presentar menu
					do {
						subMenuInformes();
						opcionSubMenu = teclado.nextInt();
					} while (opcionSubMenu < 1 || opcionSubMenu > 8);
					//Mientras no desee salir
					while (opcionSubMenu != 8) {
						//Segun opcion
						switch (opcionSubMenu) {
							//Opcion 1							
							case 1:
								//Listar todos los pacientes
								GestionEstaticaHospital.mostrarFichero(ficheroMaestro);
							break;
							//Opcion 2
							case 2:
								//Ver datos de un solo paciente
								System.out.println("Introduzca el DNi del paciente");
								teclado.nextLine();
								dni = teclado.nextLine();
								datosPaciente = GestionEstaticaHospital.obtenerDatosPaciente(dni, ficheroMaestro);
								System.out.println(datosPaciente);
							break;
							//Opcion 3
							case 3:
								//Porcentaje de pacientes menores de 13 años
								System.out.println(GestionEstaticaHospital.obtenerPorcentajeCrios(ficheroMaestro) + "%");
							break;
							//Opcion 4
							case 4:
								//Porcentaje de pacientes entre 13 y 30 años
								System.out.println(GestionEstaticaHospital.obtenerPorcentajeJovenes(ficheroMaestro) + "%");
							break;
							//Opcion 5
							case 5:
								//Porcentaje de pacientes mayores de 30 años
								System.out.println(GestionEstaticaHospital.obtenerPorcentajeAdultos(ficheroMaestro) + "%");
							break;
							//Opcion 6
							case 6:
								//Porcentaje de hombres
								System.out.println(GestionEstaticaHospital.obtenerPorcentajeHombres(ficheroMaestro) + "%");
							break;
							//Opcion 7
							case 7:
								//Porcentaje de mujeres
								System.out.println(GestionEstaticaHospital.obtenerPorcentajeMujeres(ficheroMaestro) + "%");
							break;
						}//fin_segun						
						//Presentar menu
						do {
							subMenuInformes();
							opcionSubMenu = teclado.nextInt();
						} while (opcionSubMenu < 1 || opcionSubMenu > 8);
					}//fin_mientras
				break;
			}//fin_segun			
			//Presentar Menu
			do {
				menuHospital();
				opcion = teclado.nextInt();
			} while (opcion < 1 || opcion > 5);	
		}//fin_mientras
		//teclado.close();
	}
	
	private static void menuHospital() {
		System.out.println("\nBienvenido al sistema de gestión de pacientes del Hospital de San Lebioda");
		System.out.println("Por favor, escoja la acción que desee realizar\n");
		System.out.println("1. Alta paciente");
		System.out.println("2. Baja paciente");
		System.out.println("3. Modificar datos paciente");		
		System.out.println("4. Informes");
		System.out.println("5. Salir\n");	
	}
	
	private static void subMenuInformes() {
		System.out.println("\nBienvenido al área de informes y estadística del Hospital de San Lebioda");
		System.out.println("Por favor, escoja el informe que desee visualizar\n");
		System.out.println("1. Todos los pacientes");
		System.out.println("2. Datos de un solo paciente");
		System.out.println("3. Porcentaje de pacientes menores de 13 años");
		System.out.println("4. Porcentaje de pacientes entre 13 y 30 años");
		System.out.println("5. Porcentaje de pacientes mayores de 30 años");
		System.out.println("6. Porcentaje de hombres");
		System.out.println("7. Porcentaje de mujeres");
		System.out.println("8. Salir\n");	
	}
}
