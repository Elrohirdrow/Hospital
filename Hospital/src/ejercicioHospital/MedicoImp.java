package ejercicioHospital;

import java.io.Serializable;

/* ANALISIS
 * 
 * Propiedades
 * -----------
 * licencia: Entero - Básica - Consultable
 * licenciaSecuencial: Propiedad de Clase - Entero - Básica - Consultable
 * 
 * Funcionalidades
 * ---------------
 * 
 * 
 * Restricciones
 * -------------
 * 
 * 
 * Consideraciones
 * ---------------
 * 
 * 
 * 
 * Interfaz de clase
 * -----------------
 * int getLicencia()
 * 
 */

public class MedicoImp extends PersonaImp implements Medico, Serializable {
	private static final long serialVersionUID = 8L;
	private static int licenciaSecuencial = 1;
	private int licencia;
	
	//Constructores
	public MedicoImp() {
		super();
		this.licencia = licenciaSecuencial++;
	}
	
	public MedicoImp(String dni, String nombre, int edad, char sexo, String domicilio, String telefono) throws HospitalException {
		super(dni, nombre, edad, sexo, domicilio, telefono);
		this.licencia = licenciaSecuencial++;
	}
	
	public MedicoImp(MedicoImp m) {
		super(m);
		this.licencia = m.licencia;
	}
	
	//Consultores y Modificadores
	public int getLicencia() {
		return licencia;
	}
	
	@Override
	public String toString() { //toString
		return (super.toString() + "," + licencia);
	}
	
	@Override
	public MedicoImp clone() { //clone
		MedicoImp copia = null;
		
		copia = (MedicoImp) super.clone();
		return copia;
	}
	
	@Override
	public int hashCode() { //hashCode
		int res;
		
		res = ((Integer.parseInt(getDni().substring(0,7))*7)+getEdad())*23;
		res = (res+17)*licencia;
		return res;
	}
}
