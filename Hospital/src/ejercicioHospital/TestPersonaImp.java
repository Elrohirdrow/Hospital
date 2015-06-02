package ejercicioHospital;

public class TestPersonaImp {

	public static void main(String[] args) {
		//Constructores
		PersonaImp persona1 = new PersonaImp();
		PersonaImp persona2 = null;
		try {
			persona2 = new PersonaImp("14253647","Toni",23,'H',"Calle Cualquiera - 5","654321987");
		} catch (HospitalException e) {
			e.printStackTrace();
		}
		System.out.println(persona1);
		System.out.println(persona2);
		
		//Consultores y Modificadores
		System.out.println(persona1.getDni());
		
		System.out.println(persona1.getNombre());
		persona1.setNombre("Paquita");
		System.out.println(persona1.getNombre());
		
		System.out.println(persona1.getEdad());
		persona1.setEdad(56);
		System.out.println(persona1.getEdad());
		
		System.out.println(persona1.getSexo());
		persona1.setSexo('M');
		System.out.println(persona1.getSexo());
		
		System.out.println(persona1.getDomicilio());
		persona1.setDomicilio("Calle absoluta - 4");
		System.out.println(persona1.getDomicilio());
		
		System.out.println(persona1.getTelefono());
		persona1.setTelefono("656454232");
		System.out.println(persona1.getTelefono());
		
		//Clone
		PersonaImp persona3 = new PersonaImp();
		persona3 = persona1.clone();
		
		//Equals
		if (persona3.equals(persona1)) {
			System.out.println("Son iguales");
		}
		
		//CompareTo
		System.out.println("Persona1 tiene el DNI por defecto (00000001A) por lo que será menor que persona2");
		System.out.println(persona2.compareTo(persona1));
	}
}
