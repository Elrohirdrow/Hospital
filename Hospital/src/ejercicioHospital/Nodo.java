package ejercicioHospital;

/* 
 * MedicoImp getInfo();
 * void setInfo(MedicoImp medico);
 * NodoLista getNodoSig();
 * void setNodoSig(NodoLista nodoSig);
 * 
 */

public interface Nodo<T> {
	T getInfo();
	void setInfo(T info);
	NodoLista getNodoSig();
	void setNodoSig(NodoLista nodoSig);
}
