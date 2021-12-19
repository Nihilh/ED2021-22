package TablaHash;

public class HashNode <T>{
	
	public static final int BORRADO =-1;
	public static final int VACIO = 0;
	public static final int LLENO = 1;
	
	private T info; //En contenido de un elemento de la tabla hash
	private int estado; //Estado del elemento de la tabla hash: borrado, lleno o vacío
	
	/**
	 * Constructor HashNode que asigna null a la info y VACIO al estado
	 */
	public HashNode() {
		info=null;
		estado= VACIO;
	}
	
	/**
	 * Método que devuelve el valor de la información 
	 * 
	 * @return información del nodo
	 */
	public T getInfo() {
		return info;
	}
	
	/**
	 * Método que asigna la info pasada como parametro al nodo y cambia el 
	 * estado a LLENO
	 * 
	 * @param elemento información a asignar
	 */
	public void setInfo(T elemento) {
		info= elemento;
		estado=LLENO;
	}
	
	/**
	 * Método que asigna al nodo el estado BORRADO.
	 * Muestra el contenido del nodo entre llaves, {_E_} si el estado es vacío y
	 * {_D_} siel estado es borrado
	 */
	public void remove () {
		estado=BORRADO;
	}
	
	/**
	 * Método que devuelve el estado del nodo
	 * 
	 * @return estado del nodo
	 */
	public int getStatus() {
		return estado;
	}
	
	/**
	 * Método que devuelve una cadena con la informacion del nodo
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
