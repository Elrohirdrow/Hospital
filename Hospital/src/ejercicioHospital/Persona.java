package ejercicioHospital;

public interface Persona {
	 String getDni();
	 String getNombre();
	 int getEdad();
	 char getSexo();
	 String getDomicilio();
	 String getTelefono();
	 
	 void setNombre(String nombre);
	 void setEdad(int edad);
	 void setSexo(char sexo);
	 void setDomicilio(String domicilio);
	 void setTelefono(String telefono);
}
