package ejercicioHospital;

public class MetodosEnDesusoHospital {
	
	/* modifPaciente (Sobrecargado para modificar Nombre)
	 * 
	 */
	private void modifPaciente(PacienteImp paciente, String Nombre) {
		paciente.setNombre(Nombre);
	}

	/* modifPaciente (Sobrecargado para modificar Edad)
	 * 
	 */
	private void modifPaciente(PacienteImp paciente, int edad) {
		paciente.setEdad(edad);
	}

	/* modifPaciente (Sobrecargado para modificar Sexo)
	 * 
	 */
	private void modifPaciente(PacienteImp paciente, char sexo) {
		paciente.setSexo(sexo);
	}

	/* modifPacienteDomicilio
	 * 
	 */
	private void modifPacienteDomicilio(PacienteImp paciente, String domicilio) {
		paciente.setDomicilio(domicilio);
	}

	/* modifPacienteTelefono
	 * 
	 */
	private void modifPacienteTelefono(PacienteImp paciente, String Telefono) {
		paciente.setTelefono(Telefono);
	}

	/* modifPaciente (Sobrecargado para modificar Seguro Privado)
	 * 
	 */
	private void modifPaciente(PacienteImp paciente, boolean seguroPrivado) {
		paciente.setSeguroPrivado(seguroPrivado);
	}
	
}
