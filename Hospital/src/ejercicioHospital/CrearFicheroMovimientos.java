package ejercicioHospital;

/* ANALISIS
 * Se creerá un fichero binario en el que se guardarán movimientos de objetos Paciente.
 * 
 * ENTRADA
 * Nada
 * 
 * SALIDA
 * Un fichero binario
 * 
 * CONSIDERACIONES
 * 
 * 
 * PSEUDOCÓDIGO GENERALIZADO
 * -------------------------
 * INICIO
 *		Anexar fichero
 * 		Crear fichero
 * 		Abrir fichero para escribir
 * 		Cerrar fichero
 * FIN
 * 
 * 
 */

import java.io.*;

public class CrearFicheroMovimientos {

	public static void main(String[] args) {		
		//Anexar fichero
		File fBinario = new File("src\\ejercicioHospital\\hospitalMov.dat");
		
		FileOutputStream binarioFOS =null;
		ObjectOutputStream binarioOOS =null;
		try {
			//Crear fichero
			fBinario.createNewFile();			
			//Abrir fichero para escribir
			binarioFOS = new FileOutputStream(fBinario);	
			binarioOOS = new ObjectOutputStream(binarioFOS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if (binarioOOS!=null) {
				try {
					//Cerrar fichero
					binarioOOS.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}			
		}
	}
}