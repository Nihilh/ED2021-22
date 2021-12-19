package TablaHash;

public class HashNode <T>{
	
	public static final int BORRADO =-1;
	public static final int VACIO = 0;
	public static final int LLENO = 1;
	
	private T info; //En contenido de un elemento de la tabla hash
	private int estado; //Estado del elemento de la tabla hash: borrado, lleno o vac�o
	
	/**
	 * Constructor HashNode que asigna null a la info y VACIO al estado
	 */
	public HashNode() {
		info=null;
		estado= VACIO;
	}
	
	/**
	 * M�todo que devuelve el valor de la informaci�n 
	 * 
	 * @return informaci�n del nodo
	 */
	public T getInfo() {
		return info;
	}
	
	/**
	 * M�todo que asigna la info pasada como parametro al nodo y cambia el 
	 * estado a LLENO
	 * 
	 * @param elemento informaci�n a asignar
	 */
	public void setInfo(T elemento) {
		info= elemento;
		estado=LLENO;
	}
	
	/**
	 * M�todo que asigna al nodo el estado BORRADO.
	 * Muestra el contenido del nodo entre llaves, {_E_} si el estado es vac�o y
	 * {_D_} siel estado es borrado
	 */
	public void remove () {
		estado=BORRADO;
	}
	
	/**
	 * M�todo que devuelve el estado del nodo
	 * 
	 * @return estado del nodo
	 */
	public int getStatus() {
		return estado;
	}
	
	/**
	 * M�todo que devuelve una cadena con la informacion del nodo
	 */
	public String toString() {
		StringBuilder cadena = new StringBuilder("{");
		switch(getStatus()){
		case LLENO:
		cadena.append(info.toString());
		break;
		case VACIO:
		cadena.append("_E_");
		break;
		case BORRADO:
		cadena.append("_D_");
		}
		cadena.append("}");
		return cadena.toString();
		}


}
