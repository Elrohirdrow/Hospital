package ejercicioHospital;

public class TestMedicoImp {

	public static void main(String[] args) {
		MedicoImp medico1 = new MedicoImp();
		System.out.println(medico1);
		
		MedicoImp medico2 = null;
		try {
			medico2 = new MedicoImp("99889988","Ramon",49,'H',"Calle lalala - 5","159486325");
		} catch (HospitalException e) {
			e.printStackTrace();
		}
		System.out.println(medico2);
		
		try {
			medico1 = new MedicoImp("48152648","Ana",36,'M',"Calle Retro - 15","654987789");
		} catch (HospitalException e) {
			e.printStackTrace();
		}
		System.out.println(medico1);
		
		System.out.println(medico1.hashCode());
		System.out.println(medico2.hashCode());
		
		medico2 = medico1.clone();
		System.out.println(medico2);
	}
}
