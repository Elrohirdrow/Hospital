package ejercicioHospital;

/* ANALISIS
 * Se creerá un fichero binario en el que se guardarán objetos Paciente.
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
 * 		Introducir datos
 * 		Cerrar fichero
 * FIN
 * 
 * 
 */

import java.io.*;

public class CrearFicheroHospital {

	public static void main(String[] args) {
		
		PacienteImp paciente1 = null;
		PacienteImp paciente2 = null;
		PacienteImp paciente3 = null;
		PacienteImp paciente4 = null;
		PacienteImp paciente5 = null;
		PacienteImp paciente6 = null;
		PacienteImp paciente7 = null;
		PacienteImp paciente8 = null;
		PacienteImp paciente9 = null;
		PacienteImp paciente10 = null;		
		
		try {
			paciente1 = new PacienteImp("14253647","Toni",23,'H',"Calle Cualquiera - 5","654321987",true);
			paciente2 = new PacienteImp("65983241","Pablo",19,'H',"Calle de al lado - 19","654789123",false);
			paciente3 = new PacienteImp("15482615","Manuela",37,'M',"Calle lejana s/n","655322122",true);
			paciente4 = new PacienteImp("62958474","Patricia",29,'M',"Calle de al lado - 26","654174396",true);
			paciente5 = new PacienteImp("28173982","Rafa",45,'H',"Calle Poniente - 14","658745145",false);
			paciente6 = new PacienteImp("16482673","Clara",9,'M',"Calle de al lado - 3","693456789",false);
			paciente7 = new PacienteImp("19732846","David",66,'H',"Calle Poniente - 14","195843156",true);
			paciente8 = new PacienteImp("64971382","Jesus",12,'H',"Calle cercana - 11","654234412",true);
			paciente9 = new PacienteImp("10847569","Maria",41,'M',"Calle mediana - 8","684248962",false);
			paciente10 = new PacienteImp("35704825","Lorena",22,'M',"Calle Orienta - 10","696363535",true);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		//Anexar fichero
		File fBinario = new File("src\\ejercicioHospital\\hospitalMaestro.dat");
		
		FileOutputStream binarioFOS =null;
		ObjectOutputStream binarioOOS =null;
		try {
			//Crear fichero
			fBinario.createNewFile();			
			//Abrir fichero para escribir
			binarioFOS = new FileOutputStream(fBinario);	
			binarioOOS = new ObjectOutputStream(binarioFOS);			
			//Introducir datos
			binarioOOS.writeObject(paciente1);
			binarioOOS.writeObject(paciente2);
			binarioOOS.writeObject(paciente3);
			binarioOOS.writeObject(paciente4);
			binarioOOS.writeObject(paciente5);			
			binarioOOS.writeObject(paciente6);
			binarioOOS.writeObject(paciente7);
			binarioOOS.writeObject(paciente8);
			binarioOOS.writeObject(paciente9);
			binarioOOS.writeObject(paciente10);
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
