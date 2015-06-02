package ejercicioHospital;

import java.io.Serializable;

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

public class PacienteAgregadoImp implements PacienteAgregado, Cloneable, Comparable<PacienteAgregadoImp>, Serializable {
	private static final long serialVersionUID = 8L;
	private PersonaImp persona;
	private boolean seguroPrivado;
	
	//Constructores
	public PacienteAgregadoImp() {
		this.persona = new PersonaImp();
		this.seguroPrivado = false;
	}

	public PacienteAgregadoImp(String dni, String nombre, int edad, char sexo, String domicilio, String telefono, boolean seguroPrivado) throws HospitalException {
		this.persona = new PersonaImp(dni, nombre, edad, sexo, domicilio, telefono);
		this.seguroPrivado = seguroPrivado;
	}
	
	//Consultores y Modificadores
	public String getDni() {
		return persona.getDni();
	}

	public String getNombre() {
		return persona.getNombre();
	}

	public void setNombre(String nombre) {
		this.persona.setNombre(nombre);
	}

	public String getDomicilio() {
		return persona.getDomicilio();
	}

	public void setDomicilio(String domicilio) {
		this.persona.setDomicilio(domicilio);
	}

	public String getTelefono() {
		return persona.getTelefono();
	}

	public void setTelefono(String telefono) {
		this.persona.setTelefono(telefono);
	}

	public int getEdad() {
		return persona.getEdad();
	}

	public void setEdad(int edad) {
		this.persona.setEdad(edad);
	}

	public char getSexo() {
		return persona.getSexo();
	}

	public void setSexo(char sexo) {
		this.persona.setSexo(sexo);
	}

	public boolean getSeguroPrivado() {
		return seguroPrivado;
	}

	public void setSeguroPrivado(boolean seguroPrivado) {
		this.seguroPrivado = seguroPrivado;
	}
	
	@Override
	public String toString() { //toString
		return (persona + "," + seguroPrivado);
	}
	
	@Override
	public PacienteAgregadoImp clone() { //Clone
		PacienteAgregadoImp copia = null;
		
		try
		{
			copia = (PacienteAgregadoImp) super.clone();
		}
		catch (CloneNotSupportedException error)
		{
			System.out.println("Objeto no clonado");
		}
		return copia;
	}
	
	@Override
	public int hashCode() { //Hashcode
		int res;
		
		res = ((Integer.parseInt(getDni())*7)+getEdad())*13;
		
		return res;
	}
	
	@Override
	public boolean equals(Object o) { //equals - Criterio de igualdad = DNI
		boolean res = false;
		
		if (o != null && o instanceof PacienteAgregadoImp)
		{
			PacienteAgregadoImp a = (PacienteAgregadoImp) o;
			
			if (getDni() == a.getDni())
			{
				res = true;
			}			
		}
		return res;
	}
	
	@Override
	public int compareTo(PacienteAgregadoImp p) { //compareTo - Criterio de comparación = DNI
		int comparacion = 0;
		
		if (getDni().compareTo(p.getDni()) < 0)
		{
			comparacion = -1;
		} else if (getDni().compareTo(getDni()) > 0)
				{
					comparacion = 1;
				}
		return comparacion;
	}
}
