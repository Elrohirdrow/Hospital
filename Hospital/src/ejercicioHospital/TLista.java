package ejercicioHospital;

/* ANALISIS
 * --------
 * Clase que contiene todos los nodos de una lista
 * 
 * 
 * PROPIEDADES
 * ----------- 
 * pLista: Básica - NodoLista - Consultable y Modificable 
 * insertarElemento
 * 
 * FUNCIONALIDADES
 * ---------------
 * listaVacia: Se comprueba si la lista está vacía
 * insertarElemento: Se introduce un nuevo elemento en la lista
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
 * boolean listaVacia()
 * void insertarElemento(T objeto)
 */

public class TLista<T> {
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
	
	/* listaVacia
	 * 
	 * Cabecera: boolean listaVacia()
	 * Comentario: Se comprueba si la lista está vacía
	 * Precondición: Nada
	 * Entrada: Nada
	 * Salida: Un boolean
	 * Postcondición: Se devuelve un boolean asociado al nombre con valor true si la lista está vacia y false si no. 	
	 */
	public boolean listaVacia() {
		boolean res = true;
		
		if (pLista != null) {
			res = false;
		}
		return res;
	}
	
	/* insertarElemento
	 * 
	 * Cabecera: void insertarElemento(T elemento)
	 * Comentario: Dado un elemento se insertará en la lista
	 * Precondición: Nada
	 * Entrada: Un tipo T a insertar en la lista
	 * Salida: Un nuevo nodo en la lista
	 * Postcondición: La lista tendrá un nuevo nodo que contiene el elemento dado
	 */
	public void insertarElemento(T elemento) {
		
	}
}
