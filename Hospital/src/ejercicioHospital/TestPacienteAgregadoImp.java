package ejercicioHospital;

public class TestPacienteAgregadoImp {

	public static void main(String[] args) {
		//Constructores
		PacienteAgregadoImp paciente1 = new PacienteAgregadoImp();
		PacienteAgregadoImp paciente2 = null;
		try {
			paciente2 = new PacienteAgregadoImp("14253647","Toni",23,'H',"Calle Cualquiera, 5","654321987",true);
		} catch (HospitalException e) {
			e.printStackTrace();
		}
		System.out.println(paciente1);
		System.out.println(paciente2);
		//Consultores y Modificadores
		System.out.println(paciente1.getSeguroPrivado());
		paciente1.setSeguroPrivado(true);
		System.out.println(paciente1.getSeguroPrivado());
	}
}
