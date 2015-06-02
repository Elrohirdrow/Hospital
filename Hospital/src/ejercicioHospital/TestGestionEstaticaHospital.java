package ejercicioHospital;

import java.util.*;

public class TestGestionEstaticaHospital {

	public static void main(String[] args) {
		ArrayList<PacienteImp> pacientes = new ArrayList<PacienteImp>();
		double res;
		String resString;
		
		//Pasar de binario a texto un fichero
		//GestionEstaticaHospital.pasarATexto("src\\ejercicioHospital\\hospitalMaestro.dat", "src\\ejercicioHospital\\hospitalMaestroTexto.txt");
		
		//ordenacion externa mezcla
		//GestionEstaticaHospital.ordenacionExternaMezcla("src\\ejercicioHospital\\hospitalMaestro.dat");
		GestionEstaticaHospital.ordenacionExternaMezcla("src\\ejercicioHospital\\hospitalMov.dat");
		
		//ordenacion hibrida
		//GestionEstaticaHospital.ordenacionHibrida("src\\ejercicioHospital\\hospitalMaestro.dat", "src\\ejercicioHospital\\hospitalMaestroOrdenado.dat");
		//GestionEstaticaHospital.pasarATexto("src\\ejercicioHospital\\hospitalMaestroOrdenado.dat", "src\\ejercicioHospital\\hospitalMaestroOrdenadoTexto.txt");
		
		//listarPacientes
		//pacientes = GestionEstaticaHospital.listarPacientes("src\\ejercicioHospital\\hospitalMaestro.dat");
		//for (int i = 0; i < pacientes.size(); i++) {
		//	System.out.println(pacientes.get(i));
		//}
		
		//Porcentajes
	/*	res = GestionEstaticaHospital.obtenerPorcentajeCrios("src\\ejercicioHospital\\hospitalMaestro.dat");
		System.out.println(res);
		res = GestionEstaticaHospital.obtenerPorcentajeJovenes("src\\ejercicioHospital\\hospitalMaestro.dat");
		System.out.println(res);
		res = GestionEstaticaHospital.obtenerPorcentajeAdultos("src\\ejercicioHospital\\hospitalMaestro.dat");
		System.out.println(res);
		res = GestionEstaticaHospital.obtenerPorcentajeHombres("src\\ejercicioHospital\\hospitalMaestro.dat");
		System.out.println(res);
		res = GestionEstaticaHospital.obtenerPorcentajeMujeres("src\\ejercicioHospital\\hospitalMaestro.dat");
		System.out.println(res);
		res = GestionEstaticaHospital.obtenerPorcentajeSeguro("src\\ejercicioHospital\\hospitalMaestro.dat");
		System.out.println(res);
	*/	
		//Obtener datos paciente
	/*	resString = GestionEstaticaHospital.obtenerDatosPaciente("28173982V", "src\\ejercicioHospital\\hospitalMaestro.dat");
		System.out.println(resString);
	*/
		//GestionEstaticaHospital.renombrarFicheroMaestro("src\\ejercicioHospital\\hospitalMaestro.dat", "src\\ejercicioHospital\\hospitalMaestroActualizado.dat");
		
		//GestionEstaticaHospital.mostrarFichero("src\\ejercicioHospital\\hospitalMaestro.dat");
		GestionEstaticaHospital.mostrarFichero("src\\ejercicioHospital\\hospitalMov.dat");
	}

}
