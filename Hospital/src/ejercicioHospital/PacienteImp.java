package ejercicioHospital;

/* ANALISIS
 * 
 * Propiedades
 * -----------
 * seguroPrivado: Boolean - Básica - Consultable y Modificable
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
 * boolean getSeguroPrivado()
 * 
 * void setSeguroPrivado(boolean seguroPrivado)
 * 
 */
import java.io.*;

public class PacienteImp extends PersonaImp implements Paciente, Cloneable, Serializable {
	private static final long serialVersionUID = 8L;
	private boolean seguroPrivado;
	
	//Constructores
	public PacienteImp() {
		super();
		this.seguroPrivado = false;
	}
	
	public PacienteImp(String dni, String nombre, int edad, char sexo, String domicilio, String telefono, boolean seguroPrivado) throws HospitalException {
		super(dni, nombre, edad, sexo, domicilio, telefono);
		this.seguroPrivado = seguroPrivado;
	}
	
	public PacienteImp(PacienteImp p) {
		super(p);
		this.seguroPrivado = p.seguroPrivado;
	}
	
	//Consultores y Modificadores
	public boolean getSeguroPrivado() {
		return seguroPrivado;
	}
	
	public void setSeguroPrivado(boolean seguroPrivado) {
		this.seguroPrivado = seguroPrivado;
	}
	
	@Override
	public String toString() { //toString
		return (super.toString() + "," + seguroPrivado);
	}
	
	@Override
	public PacienteImp clone() { //clone
		PacienteImp copia = null;
		
		copia = (PacienteImp) super.clone();
		//Este try-catch no debe ponerse ya que la excepción ya está controlada en el padre, por lo 
		//que aquí jamás llegará una excepción.
		/*try
		{
			copia = (PacienteImp) super.clone();
		}
		catch (CloneNotSupportedException error)
		{
			System.out.println("Objeto no clonado");
		}*/
		return copia;
	}
	
	@Override
	public int hashCode() { //hashCode
		int res;
		
		res = ((Integer.parseInt(getDni().substring(0,7))*7)+getEdad())*23;
		
		return res;
	}
}
