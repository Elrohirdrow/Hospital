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
 * Propiedades
 * -----------
 * ficheroMov = File - Básica - Consultable
 * 
 * Funcionalidades
 * ---------------
 * mostrarFicheroMov
 * altaPaciente
 * existePaciente
 * encuentraPaciente
 * bajaPaciente
 * modifPaciente
 * menuModif -- FUNCIONALIDAD PRIVADA
 * actualizarFicheroMaestro
 * limpiarFicheroMov
 * 
 * 
 * Restricciones
 * -------------
 * 
 * 
 * Consideraciones
 * ---------------
 * 
 * 
 * Estudio Interfaz
 * ----------------
 * void mostrarFicheroMov()
 * boolean altaPaciente(String DniPaciente, String Nombre, int Edad, char Sexo, String Domicilio, String Telefono, boolean SeguroPrivado)
 * boolean existePaciente(String DniPaciente, String ficheroMaestro)
 * PacienteImp encuentraPaciente(String DniPaciente, String ficheroMaestro)
 * boolean bajaPaciente(String DniPaciente, String ficheroMaestro)
 * boolean modifPaciente(String DniPaciente, String ficheroMaestro)
 * void menuModif() -- FUNCIONALIDAD PRIVADA
 * void limpiarFicheroMov()
 */

import java.io.*;
import java.util.*;

public class GestionHospital {
	File ficheroMov;
	
	//Constructor
	public GestionHospital() {
		ficheroMov = new File("src\\ejercicioHospital\\hospitalMov.dat");		
		try {
			ficheroMov.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Metodos	
	/* mostrarFicheroMov
	 * 
	 * Cabecera: void mostrarFicheroMov()
	 * Comentario: Muestra los registros del fichero de movimientos
	 * Precondición: Nada
	 * Entrada: Nada
	 * Salida: Nada
	 * Postcondición: Se muestra en pantalla el contenido del fichero
	 */
	public void mostrarFicheroMov() {
		PacienteImp paciente = null;
		
		FileInputStream ficheroMovFIS = null;
		ObjectInputStream ficheroMovOIS = null;
		try {
			ficheroMovFIS = new FileInputStream(ficheroMov);
			ficheroMovOIS = new ObjectInputStream(ficheroMovFIS);
			
			paciente = (PacienteImp) ficheroMovOIS.readObject();
			while (paciente != null) {
				System.out.println(paciente);
				paciente = (PacienteImp) ficheroMovOIS.readObject();
			}
		} catch (IOException e) {
			paciente = null;
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ficheroMovOIS != null) {
					ficheroMovOIS.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* altaPaciente
	 * 
	 * Cabecera: boolean altaPaciente(String DniPaciente, String Nombre, int Edad, char Sexo, String Domicilio, String Telefono, boolean SeguroPrivado)
	 * Comentario: Dados los datos de un paciente se añadirá al fichero de movimiento como alta
	 * Precondición: Nada
	 * Entrada: 4 cadenas (dni,nombre,domicilio,telefono), 1 entero (edad), 1 char (sexo) y un boolean (si tiene seguro o no)
	 * Salida: un booleano.
	 * Postcondición: Se guardará el objeto en el fichero de movimientos. Devuelve un boolean indicando si ha funcionado o no.
	 */
	public boolean altaPaciente(String DniPaciente, String Nombre, int Edad, char Sexo, String Domicilio, String Telefono, boolean SeguroPrivado) {
		boolean comprobarRes = false;
		PacienteImp paciente = null;
		FileOutputStream ficheroMovFOS = null;
		ObjectOutputStream ficheroMovOOS = null;
		
		try {
			ficheroMovFOS = new FileOutputStream(ficheroMov, true);
			ficheroMovOOS = new MiObjectOutputStream(ficheroMovFOS);
			
			try {
				paciente = new PacienteImp(DniPaciente, Nombre, Edad, Sexo, Domicilio, Telefono, SeguroPrivado);
			} catch (HospitalException e) {
				e.printStackTrace();
			}
			if (paciente != null) {
				comprobarRes = true;
			}
			ficheroMovOOS.writeObject(paciente);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ficheroMovOOS != null) {
					ficheroMovOOS.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return comprobarRes;
	}
	
	/* existePaciente
	 * 
	 * Cabecera: boolean existePaciente(String DniPaciente, String ficheroMaestro)
	 * Comentario: Dado un dni se buscará un registro perteneciente a dicho dni y se indicará si existe el paciente en el fichero o no.
	 * Precondición: Nada
	 * Entrada: Dos cadenas, una para el dni del paciente y otra para la ruta del fichero
	 * Salida: Un boolean
	 * Postcondición: Se devuelve un boolean asociado al nombre con valor true si el paciente tiene un registro en l fichero y false si no. 
	 */
	public boolean existePaciente(String DniPaciente, String ficheroMaestro) {
		boolean res = false;
		PacienteImp paciente;
		
		File fichero = new File(ficheroMaestro);
		FileInputStream ficheroMaestroFIS = null;
		ObjectInputStream ficheroMaestroOIS = null;
		
		if (fichero.isFile()) {
			try {
				ficheroMaestroFIS = new FileInputStream(fichero);
				ficheroMaestroOIS = new ObjectInputStream(ficheroMaestroFIS);
				
				paciente = (PacienteImp) ficheroMaestroOIS.readObject();
				while (paciente != null) {
					if (paciente.getDni().equals(DniPaciente)) {
						res = true;
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
					//e.printStackTrace();
				}
			}
		}	
		return res;
	}
	
	/* encuentraPaciente
	 * 
	 * Cabecera: PacienteImp encuentraPaciente(String DniPaciente, String ficheroMaestro)
	 * Comentario: Dado un DNI se buscará un registro en el fichero y se devolverá el mismo.
	 * Precondicón: Nada
	 * Entrada: Dos cadenas, una para el dni del paciente y otra para la ruta del fichero
	 * Salida: Nada
	 * Postcondición: Se devuelve un objeto PacienteImp asociado al nombre cuyo dni coincide con el provisto al metodo. 
	 * 					En caso de no encontrarlo devolverá un objeto null.
	 */	
	public PacienteImp encuentraPaciente(String DniPaciente, String ficheroMaestro) {
		boolean encontrado = false;
		PacienteImp paciente, pacienteRes = null;
		
		File fichero = new File(ficheroMaestro);
		FileInputStream ficheroMaestroFIS = null;
		ObjectInputStream ficheroMaestroOIS = null;
		
		if (fichero.isFile()) {
			try {
				ficheroMaestroFIS = new FileInputStream(fichero);
				ficheroMaestroOIS = new ObjectInputStream(ficheroMaestroFIS);
				try {
					paciente = (PacienteImp) ficheroMaestroOIS.readObject();
				} catch (IOException e) {
					paciente = null;
				}				
				while (paciente != null && encontrado == false) {
					if (paciente.getDni().equals(DniPaciente)) {
						encontrado = true;
						pacienteRes = paciente;
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
		
	/* bajaPaciente
	 * 
	 * Cabecera: boolean bajaPaciente(String DniPaciente, String ficheroMaestro)
	 * Comentario: Dado un DNI se marcará en el fichero de movimientos a dicho objeto paciente para su posterior eliminación.
	 * Precondicón: Nada
	 * Entrada: Dos cadenas, una para el dni del paciente y otra para la ruta del fichero
	 * Salida: Nada
	 * Postcondición: Se crea un registro en el fichero de movimientos marcado para borrar, la marca será un asterisco en el campo nombre.
	 * 					Devuelve un boolean indicando si ha funcionado.
	 */
	public boolean bajaPaciente(String DniPaciente, String ficheroMaestro) {
		boolean comprobarRes = false;
		PacienteImp paciente;
		FileOutputStream ficheroMovFOS = null;
		ObjectOutputStream ficheroMovOOS = null;
		
		try {
			ficheroMovFOS = new FileOutputStream(ficheroMov, true);
			ficheroMovOOS = new MiObjectOutputStream(ficheroMovFOS);
			
			paciente = encuentraPaciente(DniPaciente, ficheroMaestro);
			if (paciente != null) {
				comprobarRes = true;
				paciente.setNombre("*");
				
				ficheroMovOOS.writeObject(paciente);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ficheroMovOOS != null) {
					ficheroMovOOS.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return comprobarRes;
	}
	
	/* modifPaciente
	 * 
	 * Cabecera: boolean modifPaciente(String DniPaciente, String ficheroMaestro)
	 * Comentario: Dado un DNI se ofrecerá la posibilidad de modificar los distintos campos de un paciente cuyo DNI coincida con el provisto
	 * Precondicón: Nada
	 * Entrada: Dos cadenas, una para el dni del paciente y otra para la ruta del fichero
	 * Salida: Nada
	 * Postcondición: Se escribe un nuevo registro en el fichero de movimientos con los cambios hechos al objeto paciente.
	 * 					Se devuelve un boolean para comprobar su funcionamiento.
	 */
	public boolean modifPaciente(String DniPaciente, String ficheroMaestro) {
		boolean comprobarRes = false;
		PacienteImp paciente = null;
		Scanner tecladoSubMenu = new Scanner(System.in);
		char res, cambiosChar;
		int opcionModif, cambiosInt;
		String cambios;
		boolean cambiosBoo;
		FileOutputStream ficheroMovFIS = null;
		ObjectOutputStream ficheroMovOOS = null;
		
		paciente = encuentraPaciente(DniPaciente, ficheroMaestro);
		if (paciente != null) {
			comprobarRes = true;
		}		
		System.out.println("¿Desea cambiar algún campo?");
		res = tecladoSubMenu.next().charAt(0);
		while (res == 'S' || res == 's') {
			menuModif();
			opcionModif = tecladoSubMenu.nextInt();
			switch (opcionModif) {
			case 1:
				//Nombre
				System.out.println("Introduzca el nuevo nombre");
				tecladoSubMenu.nextLine();
				cambios = tecladoSubMenu.nextLine();
				paciente.setNombre(cambios);
				//modifPaciente(paciente, cambios);
				break;
			case 2:
				//Edad
				System.out.println("Introduzca la nueva edad");
				cambiosInt = tecladoSubMenu.nextInt();
				paciente.setEdad(cambiosInt);
				//modifPaciente(paciente, cambiosInt);
				break;
			case 3:
				//Sexo
				System.out.println("Introduzca el nuevo sexo");
				cambiosChar = tecladoSubMenu.next().charAt(0);
				paciente.setSexo(cambiosChar);
				//modifPaciente(paciente, cambiosChar);
				break;
			case 4:
				//Domicilio
				System.out.println("Introduzca el nuevo domicilio");
				tecladoSubMenu.nextLine();
				cambios = tecladoSubMenu.nextLine();
				paciente.setDomicilio(cambios);
				//modifPacienteDomicilio(paciente, cambios);
				break;
			case 5:
				//Telefono
				System.out.println("Introduzca el nuevo telefono");
				tecladoSubMenu.nextLine();
				cambios = tecladoSubMenu.nextLine();
				paciente.setTelefono(cambios);
				//modifPacienteTelefono(paciente, cambios);
				break;
			case 6:
				//Seguro privado
				System.out.println("Introduzca el nuevo estado para el seguro privado");
				cambiosBoo = tecladoSubMenu.nextBoolean();
				paciente.setSeguroPrivado(cambiosBoo);
				//modifPaciente(paciente, cambiosBoo);
				break;
			}
			System.out.println("¿Desea cambiar algún campo?");
			res = tecladoSubMenu.next().charAt(0);
		}
		try {
			ficheroMovFIS = new FileOutputStream(ficheroMov, true);
			ficheroMovOOS = new MiObjectOutputStream(ficheroMovFIS);
			
			ficheroMovOOS.writeObject(paciente);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ficheroMovOOS != null) {
					ficheroMovOOS.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//tecladoSubMenu.close();
		return comprobarRes;
	}
	
	/* menuModif
	 * 
	 * Cabecera: void menuModif()
	 * Comentario: Muestra un menu
	 * Precondición: Nada
	 * Entrada: Nada
	 * Salida: Nada
	 * Postcondición: Se muestra un menu en pantalla 
	 */
	private void menuModif() {
		System.out.println("Elija el campo que desea modificar o pulse salir:");
		System.out.println("\n1. Nombre");
		System.out.println("\n2. Edad");
		System.out.println("\n3. Sexo");
		System.out.println("\n4. Domicilio");
		System.out.println("\n5. Telefono");
		System.out.println("\n6. Seguro privado");
		System.out.println("\n7. Salir");		
	}

	/* actualizarFicheroMaestro
	 * 
	 * Cabecera: void actualizarFicheroMaestro(String ficheroMaestro, String ficheroActualizado)
	 * Comentario: Dado un fichero maestro se comparará con el fichero de movimientos para actualizarlo con los últimos 
	 * 				cambios guardados en el fichero de movimientos.
	 * Precondición: Nada
	 * Entrada: Un string que representa el fichero maestro
	 * Salida: Un fichero
	 * Postcondición: El fichero actualizado guarda la información actualizada de combinar maestro y movimientos y se renombra como el nuevo fichero maestro.
	 */
	public void actualizarFicheroMaestro(String ficheroMaestro, String ficheroActualizado) {
		PacienteImp pacienteMov = null;
		PacienteImp pacienteMas = null;
		
		String ficheroAux1 = "src\\ejercicioHospital\\hospitalAux1.dat";
		String ficheroAux2 = "src\\ejercicioHospital\\hospitalAux2.dat";
		
		FileInputStream ficheroMovFIS = null;
		ObjectInputStream ficheroMovOIS = null;
		
		FileInputStream ficheroMaestroFIS = null;
		ObjectInputStream ficheroMaestroOIS = null;
		
		FileOutputStream ficheroActualizadoFOS = null;
		ObjectOutputStream ficheroActualizadoOOS = null;
		
		try {
			//Ordeno antes de instanciar los stream
			//GestionEstaticaHospital.ordenacionExternaMezcla(ficheroMaestro);
			//GestionEstaticaHospital.ordenacionExternaMezcla("src\\ejercicioHospital\\hospitalMov.dat");
			GestionEstaticaHospital.ordenacionHibrida(ficheroMaestro, ficheroAux1);
			GestionEstaticaHospital.renombrarFichero(ficheroMaestro, ficheroAux1);
			
			GestionEstaticaHospital.ordenacionHibrida("src\\ejercicioHospital\\hospitalMov.dat", ficheroAux2);
			GestionEstaticaHospital.renombrarFichero("src\\ejercicioHospital\\hospitalMov.dat", ficheroAux2);
			
			
			ficheroMovFIS = new FileInputStream(ficheroMov);
			ficheroMovOIS = new ObjectInputStream(ficheroMovFIS);
			
			ficheroMaestroFIS = new FileInputStream(ficheroMaestro);
			ficheroMaestroOIS = new ObjectInputStream(ficheroMaestroFIS);
			
			ficheroActualizadoFOS = new FileOutputStream(ficheroActualizado);
			ficheroActualizadoOOS = new ObjectOutputStream(ficheroActualizadoFOS);
			
			try {
				pacienteMov = (PacienteImp) ficheroMovOIS.readObject();
			} catch (IOException e) {
				pacienteMov = null;
			}
			try {
				pacienteMas = (PacienteImp) ficheroMaestroOIS.readObject();
			} catch (IOException e) {
				pacienteMas = null;
			}
			while (pacienteMov != null && pacienteMas != null) {
				//Modificación o baja
				if (pacienteMov.getDni().equals(pacienteMas.getDni())) {
					//Baja
					if (pacienteMov.getNombre().equals("*")) {
						try {
							pacienteMov = (PacienteImp) ficheroMovOIS.readObject();
						} catch (IOException e) {
							pacienteMov = null;
						}
						try {
							pacienteMas = (PacienteImp) ficheroMaestroOIS.readObject();
						} catch (IOException e) {
							pacienteMas = null;
						}						
					} else {
						//Modificación
						ficheroActualizadoOOS.writeObject(pacienteMov);
						try {
							pacienteMov = (PacienteImp) ficheroMovOIS.readObject();
						} catch (IOException e) {
							pacienteMov = null;
						}
						try {
							pacienteMas = (PacienteImp) ficheroMaestroOIS.readObject();
						} catch (IOException e) {
							pacienteMas = null;
						}
					}
				} else if (pacienteMov.getDni().compareTo(pacienteMas.getDni()) > 0) {
					//Existen registros en maestros que no tienen entrada en el movimiento, se escribe de maestro mientras se de tal caso
						while (pacienteMas != null && pacienteMov.getDni().compareTo(pacienteMas.getDni()) > 0) {
							ficheroActualizadoOOS.writeObject(pacienteMas);
							try {
								pacienteMas = (PacienteImp) ficheroMaestroOIS.readObject();
							} catch (IOException e) {
								pacienteMas = null;
							}
						}
				} else if (pacienteMov.getDni().compareTo(pacienteMas.getDni()) < 0) {
					//Es un alta si cumple los siguientes requisitos
					if (pacienteMov.getDni().length() == 9 //El maldito .length a contar en 1 no en 0
							&& (pacienteMov.getEdad() >= 0 || pacienteMov.getEdad() <= 200)
								&& (pacienteMov.getSexo() == 'H' || pacienteMov.getSexo() == 'M')) {
						ficheroActualizadoOOS.writeObject(pacienteMov);
						try {
							pacienteMov = (PacienteImp) ficheroMovOIS.readObject();
						} catch (IOException e) {
							pacienteMov = null;
						}
					} else { //Si no, es un error y leemos de nuevo el fichero de movimientos						
						try {
							pacienteMov = (PacienteImp) ficheroMovOIS.readObject();
						} catch (IOException e) {
							pacienteMov = null;
						}
					}	
				} 
			}
			//Se ha acabado el fichero de movimientos pero no el maestro
			while (pacienteMas != null) {
				ficheroActualizadoOOS.writeObject(pacienteMas);
				try {
					pacienteMas = (PacienteImp) ficheroMaestroOIS.readObject();
				} catch (IOException e) {
					pacienteMas = null;
				}
			}
			//Se ha acabado el fichero maestro pero no el de movimientos
			while (pacienteMov != null) {
				ficheroActualizadoOOS.writeObject(pacienteMov);
				try {
					pacienteMov = (PacienteImp) ficheroMovOIS.readObject();
				} catch (IOException e) {
					pacienteMov = null;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally { //Cerrando todo
			try {
				if (ficheroMovOIS != null) {
					ficheroMovOIS.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (ficheroMaestroOIS != null) {
					ficheroMaestroOIS.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (ficheroActualizadoOOS != null) {
					ficheroActualizadoOOS.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		GestionEstaticaHospital.renombrarFichero(ficheroMaestro, ficheroActualizado);
		limpiarFicheroMov();
	}
	
	/* limpiarFicheroMov
	 * 
	 * Cabecera: void limpiarFicheroMov()
	 * Comentario: Se limpia el fichero de movimientos
	 * Precondición: Nada
	 * Entrada: Nada
	 * Salida: Nada
	 * Postcondición: El fichero de movimientos se vacia
	 */
	public void limpiarFicheroMov() {
		FileOutputStream ficheroMovFOS = null;
		ObjectOutputStream ficheroMovOOS = null;
		
		try {
			ficheroMovFOS = new FileOutputStream(ficheroMov);
			ficheroMovOOS  = new ObjectOutputStream(ficheroMovFOS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if (ficheroMovOOS!=null) {
				try {
					ficheroMovOOS.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}			
		}
	}
}