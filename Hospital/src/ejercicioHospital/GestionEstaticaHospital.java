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
 * CAMBIAR INTERFAZ PARA NO DAR LUGAR A CONFUSION
 * 
 * 
 * 
 * Propiedades
 * -----------
 * 
 * 
 * Funcionalidades
 * ---------------
 * pasarATexto
 * mostrarFichero
 * 
 * contarRegistros
 * partirFichero
 * mezclarFichero
 * ordenacionExternaMezcla
 * ordenaArrayList
 * ordenacionHibrida
 * 
 * listarPacientes
 * obtenerPorcentajeCrios
 * obtenerPorcentajeJovenes
 * obtenerPorcentajeAdultos
 * obtenerPorcentajeHombres
 * obtenerPorcentajeMujeres
 * obtenerDatosPaciente
 * obtenerPorcentajeSeguro
 * 
 * renombrarFicheroMaestro -- Cambiar a GestionHospital
 * 
 * Restricciones
 * -------------
 * 
 * 
 * Consideraciones
 * ---------------
 * 
 * 
 * 
 * Estudio Interfaz
 * ----------------
 * void pasarATexto(String fichero, String ficheroTexto)
 * void mostrarFichero(String fichero)
 * 
 * int contarRegistros(String fichero)
 * void partirFichero(String fichero, String fAux1, String fAux2, int secuencia)
 * void mezclarFichero(String fichero, String fAux1, String fAux2, int secuencia)
 * void ordenacionExternaMezcla(String fichero, String fAux1, String fAux2, int secuencia)
 * ArrayList<PacienteImp> ordenaArrayList(String fichero)
 * void ordenacionHibrida(String ficheroOriginal,String ficheroOrdenado)
 * 
 * ArrayList<> listarPacientes(String ficheroMaestro)
 * double obtenerPorcentajeCrios(String ficheroMaestro)
 * double obtenerPorcentajeJovenes(String ficheroMaestro)
 * double obtenerPorcentajeAdultos(String ficheroMaestro)
 * double obtenerPorcentajeHombres(String ficheroMaestro)
 * double obtenerPorcentajeMujeres(String ficheroMaestro)
 * String obtenerDatosPaciente(String ficheroMaestro, String DniPaciente)
 * double obtenerPorcentajeSeguro(String ficheroMaestro)
 * 
 * void renombrarFichero(String fichero1, String fichero2)
 * 
 */

import java.io.*;
import java.util.*;

public class GestionEstaticaHospital {
	/* pasarATexto
	 * 
	 * Cabecera: void pasarATexto(String ficheroBinario, String ficheroTexto)
	 * Comentario: Se almacenarán los datos del fichero binario en el fichero de texto
	 * Precondición: Nada
	 * Entrada: 2 cadenas que representan los ficheros
	 * Salida: ficheroTexto almacenará los datos de ficheroBinario
	 * Postcondición: El contenido de ficheroBinario quedará guardado en ficheroTexto
	 */
	public static void pasarATexto(String ficheroBinario, String ficheroTexto) {
		ArrayList<PacienteImp> arrayPacientes = new ArrayList<PacienteImp>();
		FileWriter ficheroTextoFW = null;
		BufferedWriter ficheroTextoBW = null;
		
		try {
			arrayPacientes = listarPacientes(ficheroBinario);
			ficheroTextoFW = new FileWriter(ficheroTexto);
			ficheroTextoBW = new BufferedWriter(ficheroTextoFW);
			
			for(int i = 0; i < arrayPacientes.size(); i++){
				ficheroTextoBW.write(arrayPacientes.get(i).toString());
				ficheroTextoBW.newLine();
			}
		} catch (IOException e) {
			//e.printStackTrace();
		} finally {
			try {
				if (ficheroTextoBW != null) {
					ficheroTextoBW.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* mostrarFichero
	 * 
	 * Cabecera: void mostrarFichero(String fichero)
	 * Comentario: Muestra los registros de un fichero
	 * Precondición: Nada
	 * Entrada: Una cadena que representa el fichero
	 * Salida: Nada
	 * Postcondición: Se muestra en pantalla el contenido del fichero
	 */
	public static void mostrarFichero(String fichero) {
		PacienteImp paciente = null;
		
		FileInputStream ficheroFIS = null;
		ObjectInputStream ficheroOIS = null;
		try {
			ficheroFIS = new FileInputStream(fichero);
			ficheroOIS = new ObjectInputStream(ficheroFIS);
			
			paciente = (PacienteImp) ficheroOIS.readObject();
			while (paciente != null) {
				System.out.println(paciente);
				paciente = (PacienteImp) ficheroOIS.readObject();
			}
		} catch (IOException e) {
			paciente = null;
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ficheroOIS != null) {
					ficheroOIS.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* contarRegistros
	 * 
	 * Cabecera: int contarRegistros(String fichero)
	 * Comentario: Dado un fichero se contará el número de registros que posee.
	 * Precondición: Nada
	 * Entrada: Una cadena que representa el fichero
	 * Salida: Un entero
	 * Postcondición: Se devuelve un entero asociado al nombre cuyo valor será el número de registros del fichero. 
	 */
	public static int contarRegistros(String fichero) {
		int numRegistros = 0;
		PacienteImp registroActual = null;
		
		FileInputStream ficheroFIS = null;
		ObjectInputStream ficheroOIS = null;
		
		try {
			ficheroFIS = new FileInputStream(fichero);
			ficheroOIS = new ObjectInputStream(ficheroFIS);
			
			registroActual = (PacienteImp) ficheroOIS.readObject();			
			while(registroActual != null) {
				numRegistros++;			
				registroActual = (PacienteImp) ficheroOIS.readObject();
			}
		} catch (IOException e) {
			//e.printStackTrace();
			registroActual = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ficheroOIS != null) {
					ficheroOIS.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return numRegistros;
	}
	
	/* partirFichero
	 * 
	 * Cabecera: void partirFichero(String fichero, String fAux1, String fAux2, int secuencia)
	 * Comentario: Dado un fichero y una secuencia, se partirá en dos ficheros
	 * Precondición: Nada
	 * Entrada: Tres cadenas (representando los ficheros) y un entero (secuencia)
	 * Salida: Los dos ficheros auxiliares (fAux1 y fAux2) se repartirán los datos del fichero original
	 * Postcondición: Los dos ficheros auxiliares tendrán el contenido del fichero original distribuido entre ellos
	 */
	public static void partirFichero(String fichero, String fAux1, String fAux2, int secuencia) {
		FileInputStream ficheroFIS = null;
		ObjectInputStream ficheroOIS = null;
		
		FileOutputStream fAux1FOS = null;
		ObjectOutputStream fAux1OOS = null;
		
		FileOutputStream fAux2FOS = null;
		ObjectOutputStream fAux2OOS = null;
		
		PacienteImp registro = null;
		
		try {
			ficheroFIS = new FileInputStream(fichero);
			ficheroOIS = new ObjectInputStream(ficheroFIS);
			
			fAux1FOS = new FileOutputStream(fAux1);
			fAux1OOS = new ObjectOutputStream(fAux1FOS);
			
			fAux2FOS = new FileOutputStream(fAux2);
			fAux2OOS = new ObjectOutputStream(fAux2FOS);
			
			registro = (PacienteImp) ficheroOIS.readObject();
			
			while(registro != null) {			
				for (int i = 0; i < secuencia && registro != null; i++) {			
					fAux1OOS.writeObject(registro);
					registro = (PacienteImp) ficheroOIS.readObject();
				}			
				for (int i = 0; i < secuencia && registro != null; i++) {
					fAux2OOS.writeObject(registro);
					registro = (PacienteImp) ficheroOIS.readObject();
				}
			}
		} catch (IOException e) {
			//e.printStackTrace();
			registro = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fAux1OOS != null) {
					fAux1OOS.close();
				}
				if (fAux2OOS != null) {
					fAux2OOS.close();
				}		
				if (ficheroOIS != null) {
					ficheroOIS.close();
				}		
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* mezclarFichero
	 * 
	 * Cabecera: void mezclarFichero(String fichero, String fAux1, String fAux2, int secuencia)
	 * Comentario: Dados dos ficheros se mezclarán de forma ordenada en un solo fichero (tambien provisto)
	 * Precondición: Nada
	 * Entrada: Tres cadenas (ficheros) y un entero
	 * Salida: Un fichero que contendrá toda la información de los auxiliares
	 * Postcondición: Los dos ficheros auxiliares tendrán el contenido del fichero original distribuido entre ellos
	 */	
	public static void mezclarFichero(String fichero, String fAux1, String fAux2, int secuencia) {
		FileOutputStream ficheroFOS = null;
		ObjectOutputStream ficheroOOS = null;
		
		FileInputStream fAux1FIS = null;
		ObjectInputStream fAux1OIS = null;
		
		FileInputStream fAux2FIS = null;
		ObjectInputStream fAux2OIS = null;
		
		PacienteImp registro1 = null;
		PacienteImp registro2 = null;
		
		int contador1 = 0;
		int contador2 = 0;
		
		try {
			ficheroFOS = new FileOutputStream(fichero);
			ficheroOOS = new ObjectOutputStream(ficheroFOS);
			
			fAux1FIS = new FileInputStream(fAux1);
			fAux1OIS = new ObjectInputStream(fAux1FIS);
			
			fAux2FIS = new FileInputStream(fAux2);
			fAux2OIS = new ObjectInputStream(fAux2FIS);
			
			registro1 = (PacienteImp) fAux1OIS.readObject();
			registro2 = (PacienteImp) fAux2OIS.readObject();
			
			while (registro1 != null && registro2 != null) {			
				for(contador1 = 0, contador2 = 0; contador1 < secuencia && contador2 < secuencia && registro1 != null && registro2 != null;) {				
					if(registro1.getDni().compareTo(registro2.getDni()) < 0) {
						try {
							ficheroOOS.writeObject(registro1);
							registro1 = (PacienteImp) fAux1OIS.readObject();
							contador1++;
						} catch (IOException e) {
							registro1 = null;
						}						
					}
					else {
						try {
							ficheroOOS.writeObject(registro2);
							registro2 = (PacienteImp) fAux2OIS.readObject();
							contador2++;
						} catch (IOException e) {
							registro2 = null;
						}						
					}
				}
				
				if(contador1 < secuencia) {
					try {
						while (registro1 != null && contador1 < secuencia) {						
							ficheroOOS.writeObject(registro1);
							registro1 = (PacienteImp) fAux1OIS.readObject();
							contador1++;
						}
					} catch (IOException e) {
						registro1 = null;												
					}
				}
				else {
					try {
						while (registro2 != null && contador2 < secuencia) {						
							ficheroOOS.writeObject(registro2);
							registro2 = (PacienteImp) fAux2OIS.readObject();
							contador2++;
						}
					} catch (IOException e) {
						registro2 = null;
					}
				}
				
				if(registro1 == null) {
					try {
						while (registro2 != null) {						
							ficheroOOS.writeObject(registro2);
							registro2 = (PacienteImp) fAux2OIS.readObject();
						}
					} catch (IOException e) {
						registro2 = null;
					}						
				}
				if(registro2 == null) {
					try {
						while (registro1 != null) {						
							ficheroOOS.writeObject(registro1);
							registro1 = (PacienteImp) fAux1OIS.readObject();
						}
					} catch (IOException e) {
						registro1 = null;
					}					
				}
			}
		} catch (IOException e) {
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ficheroOOS != null) {
					ficheroOOS.close();
				}
				if (fAux1OIS != null) {
					fAux1OIS.close();
				}		
				if (fAux2OIS != null) {
					fAux2OIS.close();
				}		
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* ordenacionExternaMezcla
	 * 
	 * Cabecera: void ordenacionExternaMezcla(String fichero, String fAux1, String fAux2)
	 * Comentario: Haciendo uso de dos ficheros auxiliares se ordenará un fichero
	 * Precondición: Nada
	 * Entrada: Tres cadenas (ficheros)
	 * Salida: Un fichero ordenado
	 * Postcondición: El fichero original queda ordenado
	 */
	public static void ordenacionExternaMezcla(String fichero) {
		int secuencia = 1;
		int registros = 0;
		
		String fAux1 = ("src\\ejercicioHospital\\hospitalAux1.dat");
		String fAux2 = ("src\\ejercicioHospital\\hospitalAux2.dat");
		
		File ficheroAux1 = new File(fAux1);
		File ficheroAux2 = new File(fAux2);
		
		registros = GestionEstaticaHospital.contarRegistros(fichero);		
		while(secuencia <= registros) {
			GestionEstaticaHospital.partirFichero(fichero, fAux1, fAux2, secuencia);
			GestionEstaticaHospital.mezclarFichero(fichero, fAux1, fAux2, secuencia);			
			secuencia = secuencia*2;
		}
		ficheroAux1.delete();
		ficheroAux2.delete();
	}
	
	/* ordenaArrayList
	 * 
	 * Cabecera: ArrayList<PacienteImp> ordenaArrayList(String fichero)
	 * Comentario: Ordena el array en función del Dni
	 * Precondición: Nada
	 * Entrada: Una cadena (ruta del fichero donde se van a obtener los datos a ordenar)
	 * Salida: Un arrayList de tipo PacienteImp
	 * Postcondición: Devuelve un arraylist de pacientes cuyos elementos han sido ordenados según su dni
	 */
	public static ArrayList<PacienteImp> ordenaArrayList(String fichero){
		ArrayList<PacienteImp> pacientes;
		pacientes = listarPacientes(fichero);
		Collections.sort(pacientes);
		return pacientes;
	}
	
	/* ordenacionHibrida
	 * 
	 * Cabecera: void ordenacionHibrida(String ficheroOriginal,String ficheroOrdenado)
	 * Comentario: Ordena un fichero por el metodo de ordenacion hibrida
	 * Precondición: nada
	 * Entrada: 2 cadenas (una por cada fichero)
	 * Salida: ficheroOrdenado contendrá los datos ordenados de ficheroOriginal
	 * Postcondición: Se almacenan los pacientes ya ordenados en el fichero
	 */	
	public static void ordenacionHibrida(String ficheroOriginal,String ficheroOrdenado){
		ArrayList<PacienteImp> pacientes;

		FileOutputStream ficheroOrdenadoFOS = null;
		ObjectOutputStream ficheroOrdenadoOOS = null;
		try {
			pacientes = ordenaArrayList(ficheroOriginal);
			ficheroOrdenadoFOS = new FileOutputStream(ficheroOrdenado,true);
			ficheroOrdenadoOOS = new ObjectOutputStream(ficheroOrdenadoFOS);
			for(int i = 0; i < pacientes.size(); i++){
				ficheroOrdenadoOOS.writeObject(pacientes.get(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if (ficheroOrdenadoOOS != null) {
					ficheroOrdenadoOOS.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* listarPacientes
	 * 
	 * Cabecera: ArrayList<> listarPacientes(String ficheroMaestro)
	 * Comentario: Dado un fichero se listarán todos los pacientes registrados en él.
	 * Precondición: Nada.
	 * Entrada: Una cadena que representa un fichero
	 * Salida: Un array de objetos
	 * Postcondición: Se devuelve un array asociado al nombre cuyos valores serán todos los registros del fichero.
	 */	
	public static ArrayList<PacienteImp> listarPacientes(String ficheroMaestro) {
		ArrayList<PacienteImp> res = new ArrayList<PacienteImp>();
		
		File fichero = new File(ficheroMaestro);
		FileInputStream ficheroFIS = null;
		ObjectInputStream ficheroOIS = null;
		
		try {
			ficheroFIS = new FileInputStream(fichero);
			ficheroOIS = new ObjectInputStream(ficheroFIS);
			
			for (int i = 0; i < fichero.length(); i++) {
				res.add((PacienteImp) ficheroOIS.readObject());
			}			
		} catch (IOException e) {
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ficheroOIS != null) {
					ficheroOIS.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		return res;
	}
	
	/* obtenerPorcentajeCrios
	 * 
	 * Cabecera: double obtenerPorcentajeCrios(String ficheroMaestro)
	 * Comentario: Dado un fichero se devolverá el porcentaje de niños (menosres de 13 años)
	 * Precondición: Nada
	 * Entrada: Una cadena que representa un fichero
	 * Salida: Un double
	 * Postcondición: Se devuelve un tipo double asociado al nombre que representa el porcentaje de niños que hay en el fichero de pacientes 
	 */
	public static double obtenerPorcentajeCrios(String ficheroMaestro){
		double res = 0;
		int contadorNinos = 0;
		int contadorTotal = 0;
		PacienteImp aux = null;
		
		File fichero = new File(ficheroMaestro);
		FileInputStream ficheroFIS = null;
		ObjectInputStream ficheroOIS = null;
		
		try {
			ficheroFIS = new FileInputStream(fichero);
			ficheroOIS = new ObjectInputStream(ficheroFIS);
			
			aux = (PacienteImp) ficheroOIS.readObject();
			while(aux != null) {
				if (aux.getEdad()<13) {
					contadorNinos++;
				}
				contadorTotal++;
				try { //TRY CATCH NECESARIO PARA QUE EL SUBPROGAMA SIGA FUNCIONANDO TRAS LANZAR EOFEXCEPTION Y NO SE VAYA AL CATCH DE TODO EL BLOQUE
					aux = (PacienteImp) ficheroOIS.readObject();
				} catch (IOException e) {
					aux = null;
				}				
			}
			res = (contadorNinos*100)/contadorTotal; //Sacar esta linea del bloque try-catch
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ficheroOIS != null) {
					ficheroOIS.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
			//Insertar aqui
		}		
		return res;
	}
	
	/* obtenerPorcentajeJovenes
	 * 
	 * Cabecera: double obtenerPorcentajeJovenes(String ficheroMaestro)
	 * Comentario: Dado un fichero se devolverá el porcentaje de pacientes jovenes (entre 13 y 30 años, ambos inclusive)
	 * Precondición: Nada
	 * Entrada: Una cadena que representa un fichero
	 * Salida: Un double
	 * Postcondición: Se devuelve un tipo double asociado al nombre que representa el porcentaje de jovenes que hay en el fichero de pacientes 
	 */
	public static double obtenerPorcentajeJovenes(String ficheroMaestro){
		double res = 0;
		int contadorJovenes = 0;
		int contadorTotal = 0;
		PacienteImp aux = null;
		
		File fichero = new File(ficheroMaestro);
		FileInputStream ficheroFIS = null;
		ObjectInputStream ficheroOIS = null;
		
		try {
			ficheroFIS = new FileInputStream(fichero);
			ficheroOIS = new ObjectInputStream(ficheroFIS);
			
			aux = (PacienteImp) ficheroOIS.readObject();
			while(aux != null) {
				if (aux.getEdad()>=13 && aux.getEdad()<=30) {
					contadorJovenes++;
				}
				contadorTotal++;
				try {
					aux = (PacienteImp) ficheroOIS.readObject();
				} catch (IOException e) {
					aux = null;
				}		
			}
			res = (contadorJovenes*100)/contadorTotal;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ficheroOIS != null) {
					ficheroOIS.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		return res;
	}
	
	/* obtenerPorcentajeAdultos
	 * 
	 * Cabecera: double obtenerPorcentajeAdultos(String ficheroMaestro)
	 * Comentario: Dado un fichero se devolverá el porcentaje de adultos (mayores de 30 años)
	 * Precondición: Nada
	 * Entrada: Una cadena que representa un fichero
	 * Salida: Un double
	 * Postcondición: Se devuelve un tipo double asociado al nombre que representa el porcentaje de adultos que hay en el fichero de pacientes 
	 */
	public static double obtenerPorcentajeAdultos(String ficheroMaestro){
		double res = 0;
		int contadorAdultos = 0;
		int contadorTotal = 0;
		PacienteImp aux = null;
		
		File fichero = new File(ficheroMaestro);
		FileInputStream ficheroFIS = null;
		ObjectInputStream ficheroOIS = null;
		
		try {
			ficheroFIS = new FileInputStream(fichero);
			ficheroOIS = new ObjectInputStream(ficheroFIS);
			
			aux = (PacienteImp) ficheroOIS.readObject();
			while(aux != null) {
				if (aux.getEdad()>30) {
					contadorAdultos++;
				}
				contadorTotal++;
				try {
					aux = (PacienteImp) ficheroOIS.readObject();
				} catch (IOException e) {
					aux = null;
				}		
			}
			res = (contadorAdultos*100)/contadorTotal;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ficheroOIS != null) {
					ficheroOIS.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		return res;
	}
	
	/* obtenerPorcentajeHombres
	 * 
	 * Cabecera: double obtenerPorcentajeHombres(String ficheroMaestro)
	 * Comentario: Dado un fichero se devolverá el porcentaje de hombres
	 * Precondición: Nada
	 * Entrada: Una cadena que representa un fichero
	 * Salida: Un double
	 * Postcondición: Se devuelve un tipo double asociado al nombre que representa el porcentaje de hombres que hay en el fichero de pacientes 
	 */
	public static double obtenerPorcentajeHombres(String ficheroMaestro){
		double res = 0;
		int contadorHombres = 0;
		int contadorTotal = 0;
		PacienteImp aux = null;
		
		File fichero = new File(ficheroMaestro);
		FileInputStream ficheroFIS = null;
		ObjectInputStream ficheroOIS = null;
		
		try {
			ficheroFIS = new FileInputStream(fichero);
			ficheroOIS = new ObjectInputStream(ficheroFIS);
			
			aux = (PacienteImp) ficheroOIS.readObject();
			while(aux != null) {
				if (aux.getSexo() == 'H') {
					contadorHombres++;
				}
				contadorTotal++;
				try {
					aux = (PacienteImp) ficheroOIS.readObject();
				} catch (IOException e) {
					aux = null;
				}		
			}
			res = (contadorHombres*100)/contadorTotal;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ficheroOIS != null) {
					ficheroOIS.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		return res;
	}
	
	/* obtenerPorcentajeMujeres
	 * 
	 * Cabecera: double obtenerPorcentajeMujeres(String ficheroMaestro)
	 * Comentario: Dado un fichero se devolverá el porcentaje de mujeres
	 * Precondición: Nada
	 * Entrada: Una cadena que representa un fichero
	 * Salida: Un double
	 * Postcondición: Se devuelve un tipo double asociado al nombre que representa el porcentaje de mujeres que hay en el fichero de pacientes 
	 */
	public static double obtenerPorcentajeMujeres(String ficheroMaestro){
		double res = 0;
		int contadorMujeres = 0;
		int contadorTotal = 0;
		PacienteImp aux = null;
		
		File fichero = new File(ficheroMaestro);
		FileInputStream ficheroFIS = null;
		ObjectInputStream ficheroOIS = null;
		
		try {
			ficheroFIS = new FileInputStream(fichero);
			ficheroOIS = new ObjectInputStream(ficheroFIS);
			
			aux = (PacienteImp) ficheroOIS.readObject();
			while(aux != null) {
				if (aux.getSexo() == 'M') {
					contadorMujeres++;
				}
				contadorTotal++;
				try {
					aux = (PacienteImp) ficheroOIS.readObject();
				} catch (IOException e) {
					aux = null;
				}		
			}
			res = (contadorMujeres*100)/contadorTotal;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ficheroOIS != null) {
					ficheroOIS.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		return res;
	}
	
	/* obtenerDatosPaciente
	 * 
	 * Cabecera: String obtenerDatosPaciente(String ficheroMaestro, String DniPaciente)
	 * Comentario: Dado un fichero se devolverá los datos de un paciente en particular
	 * Precondición: Nada
	 * Entrada: Una cadena que representa un fichero
	 * Salida: Un double
	 * Postcondición: Se devuelve un tipo double asociado al nombre que representa el porcentaje de mujeres que hay en el fichero de pacientes 
	 */
	public static String obtenerDatosPaciente(String DniPaciente, String ficheroMaestro) {
		boolean encontrado = false;
		PacienteImp paciente = null;
		String pacienteRes = null;
		
		File fichero = new File(ficheroMaestro);
		FileInputStream ficheroMaestroFIS = null;
		ObjectInputStream ficheroMaestroOIS = null;
		
		if (fichero.isFile()) {
			try {
				ficheroMaestroFIS = new FileInputStream(fichero);
				ficheroMaestroOIS = new ObjectInputStream(ficheroMaestroFIS);
				
				paciente = (PacienteImp) ficheroMaestroOIS.readObject();
				while (paciente != null && encontrado == false) {
					if (paciente.getDni().equals(DniPaciente)) {
						encontrado = true;
						pacienteRes = paciente.toString();
					}
					try {
						paciente = (PacienteImp) ficheroMaestroOIS.readObject();
					} catch (IOException e) {
						paciente = null;
					}		
				}				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					if (ficheroMaestroOIS != null) {
						ficheroMaestroOIS.close();
					}				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
		return pacienteRes;
	}
	
	/* obtenerPorcentajeSeguro
	 * 
	 * Cabecera: double obtenerPorcentajeSeguro(String ficheroMaestro)
	 * Comentario: Dado un fichero se devolverá los datos de un paciente en particular
	 * Precondición: Nada
	 * Entrada: Una cadena que representa un fichero
	 * Salida: Un double
	 * Postcondición: Se devuelve un tipo double asociado al nombre que representa el porcentaje de mujeres que hay en el fichero de pacientes 
	 */
	public static double obtenerPorcentajeSeguro(String ficheroMaestro){
		double res = 0;
		int contadorSeguro = 0;
		int contadorTotal = 0;
		PacienteImp aux = null;
		
		File fichero = new File(ficheroMaestro);
		FileInputStream ficheroFIS = null;
		ObjectInputStream ficheroOIS = null;
		
		try {
			ficheroFIS = new FileInputStream(fichero);
			ficheroOIS = new ObjectInputStream(ficheroFIS);
			
			aux = (PacienteImp) ficheroOIS.readObject();
			while(aux != null) {
				if (aux.getSeguroPrivado() == true) {
					contadorSeguro++;
				}
				contadorTotal++;
				try {
					aux = (PacienteImp) ficheroOIS.readObject();
				} catch (IOException e) {
					aux = null;
				}	
			}
			res = (contadorSeguro*100)/contadorTotal;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ficheroOIS != null) {
					ficheroOIS.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		return res;
	}
	
	/* renombrarFichero
	 * 
	 * Cabecera: void renombrarFichero(String fichero1, String fichero2)
	 * Comentario: Dado un fichero se renombrará el segundo con el nombre del primero
	 * Precondición: nada
	 * Entrada: 2 cadenas que representan 2 ficheros
	 * Salida: Un fichero
	 * Postcondición: Fichero2 tendrá el nombre de fichero1 
	 * 
	 */
	public static void renombrarFichero(String fichero1, String fichero2) {
		File ficheroARenombrar1 = new File(fichero1);
		File ficheroARenombrar2 = new File(fichero2);
		
		ficheroARenombrar1.delete();
		ficheroARenombrar2.renameTo(ficheroARenombrar1);
	}
}
