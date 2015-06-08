package ejercicioHospital;

public class TestNodoLista {

	public static void main(String[] args) {
		TLista listaMedicos = new TLista();
		MedicoImp medico1 = null;
		MedicoImp medico2 = null;
		MedicoImp medico3 = null;
		MedicoImp medico4 = null;
		
		try {
			medico1 = new MedicoImp("59955959","Juanito",52,'H',"Calle de alli - 26","6546544556");
			medico2 = new MedicoImp("12457865","Pepa",46,'M',"Calle medicos - 2","666555444");
			medico3 = new MedicoImp("84754857","Mari",59,'M',"Calle manzana - 6","628986598");
			medico4 = new MedicoImp("48954895","Antonio",41,'H',"Calle naranja - 1","639963369");
		} catch (HospitalException e) {
			e.printStackTrace();
		}
		
		//Creo un nodo y lo inserto en la lista
		NodoLista nodo1 = new NodoLista(medico1);
		
		System.out.println(nodo1.getInfo());
		
		listaMedicos.setPLista(nodo1);
	}
}
