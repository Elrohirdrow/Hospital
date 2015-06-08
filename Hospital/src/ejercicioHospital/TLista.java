package ejercicioHospital;

/* ANALISIS
 * --------
 * Clase que contiene todos los nodos de una lista
 * 
 * 
 * PROPIEDADES
 * ----------- 
 * pLista: Básica - NodoLista - Consultable y Modificable 
 * 
 * 
 * FUNCIONALIDADES
 * ---------------
 * 
 * 
 * RESTRICCIONES
 * -------------
 * 
 * 
 * INTERFAZ
 * --------
 * NodoLista getPLista()
 * void setPLista(NodoLista nodoSig)
 * 
 */

public class TLista {
	NodoLista pLista;
	
	public TLista() { //Constructor por defecto
		this.pLista = null;
	}
	
	public TLista(NodoLista nodoSig) { //Constructor ordinario
		this.pLista = nodoSig;
	}
	
	public NodoLista getPLista() {
		return pLista;
	}
	
	public void setPLista(NodoLista nodoSig) {
		this.pLista = nodoSig;
	}
}
