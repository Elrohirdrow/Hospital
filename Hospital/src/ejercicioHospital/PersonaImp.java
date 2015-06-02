package ejercicioHospital;

import java.io.Serializable;

/* ANALISIS
 * 
 * Propiedades
 * -----------
 * DNI: Cadena - B�sica - Consultable
 * nombre: Cadena - B�sica - Consultable y Modificable
 * edad: Entero - B�sica - Consultable y Modificable
 * sexo: Caracter - B�sica - Consultable y Modificable
 * domicilio: Cadena - B�sica - Consultable y Modificable
 * telefono: Cadena - B�sica - Consultable y Modificable
 * 
 * Funcionalidades
 * ---------------
 * calcularLetra: (PRIVADA) Calcular� la letra apropiada para un DNI
 * comprobarDNI: (PRIVADA) Comprueba que el DNI proporcionado es v�lido
 * 
 * Restricciones
 * -------------
 * 
 * 
 * Consideraciones
 * ---------------
 * El domicilio ser� el tipo de calle, su nombre seguido de un gui�n y el n�mero. Por ej. Calle Guardia Blanco - 15
 * 
 * 
 * Interfaz de clase
 * -----------------
 * String getDni()
 * String getNombre()
 * int getEdad()
 * char getSexo()
 * String getDomicilio()
 * String getTelefono()
 * 
 * void setNombre(String nombre)
 * void setEdad(int edad)
 * void setSexo(char sexo)
 * void setDomicilio(String domicilio)
 * void setTelefono(String telefono)
 * 
 * char calcularLetra(String dni) -- PRIVADO
 * boolean comprobarDNI(String DNI) -- PRIVADO
 */


public class PersonaImp implements Persona, Cloneable, Comparable<PersonaImp>, Serializable {
	private static final long serialVersionUID = 8L;
	private String dni,nombre,domicilio,telefono;
	private int edad;
	private char sexo;
	
	//Constructores
	public PersonaImp() {
		this.dni = "00000001A";
		this.nombre = "John Doe";
		this.edad = 18;
		this.sexo = 'H';
		this.domicilio = "Calle random - 13";
		this.telefono = "666666666";
	}

	public PersonaImp(String dni, String nombre, int edad, char sexo, String domicilio, String telefono) throws HospitalException {
		if (!comprobarDNI(dni)) {
			throw new HospitalException("DNI inv�lido");
		}
		this.dni = dni+calcularLetra(dni);
		this.nombre = nombre;
		this.edad = edad;
		this.sexo = Character.toUpperCase(sexo);
		this.domicilio = domicilio;
		this.telefono = telefono;	
	}
	
	public PersonaImp(PersonaImp p) {
		this.dni = p.dni;
		this.nombre = p.nombre;
		this.edad = p.edad;
		this.sexo = p.sexo;
		this.domicilio = p.domicilio;
		this.telefono = p.telefono;
	}
	
	//Consultores y Modificadores
	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	@Override
	public String toString() { //toString
		return (dni + "," + nombre + "," + edad + "," + sexo + "," + domicilio + "," + telefono);
	}
	
	@Override
	public PersonaImp clone() { //Clone
		PersonaImp copia = null;
		
		try
		{
			copia = (PersonaImp) super.clone();
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
		
		res = ((Integer.parseInt(dni)*7)+edad)*13;
		
		return res;
	}
	
	@Override
	public boolean equals(Object o) { //equals - Criterio de igualdad = DNI
		boolean res = false;
		
		if (o != null && o instanceof PersonaImp)
		{
			PersonaImp a = (PersonaImp) o;
			
			if (dni == a.dni)
			{
				res = true;
			}			
		}
		return res;
	}
	
	@Override
	public int compareTo(PersonaImp p) { //compareTo - Criterio de comparaci�n = DNI
		int comparacion = 0;
		
		if (dni.compareTo(p.dni) < 0)
		{
			comparacion = -1;
		} else if (dni.compareTo(p.dni) > 0)
				{
					comparacion = 1;
				}
		return comparacion;
	}
	
	/* calcularLetra - PRIVADA
	 * 
	 * Cabecera: char calcularLetra(String dni)
	 * Comentario: Dado un DNI sin letra, se le asignar� la letra correspondiente.
	 * Precondici�n: El dni debe ser una cadena de 8 n�meros.
	 * Entrada: Una cadena representando un dni sin letra
	 * Salida: Un caracter
	 * Postcondici�n: Se devolver� un caracter asociado al nombre que representa la letra correspondiente al dni proveido
	 * 
	 */
	private char calcularLetra(String dni) {
	    String juegoCaracteres="TRWAGMYFPDXBNJZSQVHLCKET";
	    int dniAux = 0;
	    
	    dniAux = Integer.parseInt(dni);
	    int modulo= dniAux % 23;
	    char letra = juegoCaracteres.charAt(modulo);
	    
	    return letra;
    }
	
	/* comprobarDNI - PRIVADA
	 * 
	 * Cabecera: boolean comprobarDNI(String DNI)
	 * Comentario: Dado un DNI (SIN LETRA) se comprobar� que sean 8 digitos en forma de caracter
	 * Precondici�n: Nada
	 * Entrada: Una cadena (DNI)
	 * Salida: Un boolean
	 * Postcondici�n: Se devuelve un boolean asociado al nombre con valor true si el DNI son 8 digitos y false en caso contrario
	 */
	private boolean comprobarDNI(String DNI) {
		boolean res = false;
		
		//Hago uso de un patr�n para comprobar que el String no tiene un caracter que no corresponda con dicho patr�n
		if (DNI.matches("[0-9]*") && DNI.length() == 8) { 
			res = true;
		}
		return res;
	}
}
