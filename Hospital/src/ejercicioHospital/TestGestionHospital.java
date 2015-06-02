package ejercicioHospital;

public class TestGestionHospital {

	public static void main(String[] args) {
		GestionHospital ficheroMov = new GestionHospital();
		PacienteImp paciente1;
		
		//altaPaciente
		//ficheroMov.altaPaciente("99999999", "Toni", 99, 'H', "Poligono Resaca, 4", "645645645", false);
		//ficheroMov.mostrarFicheroMov();
		
		//existePaciente
		//if (ficheroMov.existePaciente("65983241J", "src\\ejercicioHospital\\hospitalMaestro.dat")) {
		//	System.out.println("El paciente con DNI 65983241J existe");
		//}
		
		//encuentraPaciente
		//paciente1 = ficheroMov.encuentraPaciente("65983241J", "src\\ejercicioHospital\\hospitalMaestro.dat");
		//System.out.println(paciente1);
		//paciente1 = ficheroMov.encuentraPaciente("12345678J", "src\\ejercicioHospital\\hospitalMaestro.dat");//No existe, devolverá null
		//System.out.println(paciente1);
		
		//bajaPaciente
		//ficheroMov.bajaPaciente("65983241J", "src\\ejercicioHospital\\hospitalMaestro.dat");
		//ficheroMov.mostrarFicheroMov();
		//ficheroMov.bajaPaciente("12345678J", "src\\ejercicioHospital\\hospitalMaestro.dat");
		//ficheroMov.mostrarFicheroMov();
		
		//modifPaciente
		ficheroMov.modifPaciente("65983241J", "src\\ejercicioHospital\\hospitalMaestro.dat");
		ficheroMov.mostrarFicheroMov();
		
		//actualizarFichero
		//ficheroMov.actualizarFicheroMaestro("src\\ejercicioHospital\\hospitalMaestro.dat", "src\\ejercicioHospital\\hospitalMaestroActualizado.dat", "src\\ejercicioHospital\\hospitalAux1.dat", "src\\ejercicioHospital\\hospitalAux2.dat");
		//GestionEstaticaHospital.ordenacionExternaMezcla("src\\ejercicioHospital\\hospitalMov.dat", "src\\ejercicioHospital\\hospitalAux1.dat", "src\\ejercicioHospital\\hospitalAux2.dat");
		//ficheroMov.mostrarFicheroMov();
		//GestionEstaticaHospital.mostrarFichero("src\\ejercicioHospital\\hospitalMaestroActualizado.dat");
	}
}
