package ejercicioHospital;

/* ANALISIS
 * --------
 * Esta clase representa el nodo de una lista con su información y el enlace al siguiente nodo.
 * 
 * 
 * PROPIEDADES
 * -----------
 * info: Básica - T - Consultable y Modificable
 * nodoSig: Básica - NodoLista - Consultable y Modificable
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
 * 
 * INTERFAZ
 * --------
 * T getinfo()
 * void setInfo(T info)
 * NodoLista getNodoSig()
 * void setNodoSig(NodoLista nodoSig)
 */

public class NodoLista<T> implements Nodo<T> {
	private T info;
	private NodoLista nodoSig;
	
	public NodoLista() { //Constructor por defecto
		this.info = null;
		this.nodoSig = null;
	}
	
	public NodoLista(T info) { //Constructor ordinario
		this.info = info;
		this.nodoSig = null;
	}
	
	public T getInfo() {
		return info;
	}
	
	public void setInfo(T info) {
		this.info = info;
	}
	
	public NodoLista getNodoSig() {
		return nodoSig;
	}
	
	public void setNodoSig(NodoLista nodoSig) {
		this.nodoSig = nodoSig;
	}
}
