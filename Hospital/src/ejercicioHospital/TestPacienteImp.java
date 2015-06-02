package ejercicioHospital;

public class TestPacienteImp {

	public static void main(String[] args) {
		//Constructores
		PacienteImp paciente1 = new PacienteImp();
		PacienteImp paciente2 = null;
		try {
			paciente2 = new PacienteImp("14253647","Toni",23,'H',"Calle Cualquiera - 5","654321987",true);
		} catch (HospitalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(paciente1);
		System.out.println(paciente2);
		
		//Consultores y Modificadores
		System.out.println(paciente1.getSeguroPrivado());
		paciente1.setSeguroPrivado(true);
		System.out.println(paciente1.getSeguroPrivado());
		
		//Clone
		paciente1 = paciente2.clone();
		System.out.println(paciente1);
		System.out.println(paciente2);
		
		//hashCode
		System.out.println(paciente1.hashCode());
	}

}
