package BST;

public class BSTNode<T extends Comparable<T>> {
	
	private T info;
	private BSTNode<T> left;
	private BSTNode<T> right;
	/**
	 * Constructor de la clase
	 * 
	 * @param clave Informaci�n que va a almacenar el nodo
	 */
	public BSTNode(T clave) {
		info=clave;
		left=null;
		right=null;
	}
	/**
	 * M�todo que asigna a la informaci�n del nodo la pasada como par�metro
	 * 
	 * @param clave informaci�n del nodo a a�adir
	 */
	public void setInfo(T clave) {
		this.info=clave;
	}
	
	/**
	 * M�todo que devuelve la informaci�n del nodo
	 * 
	 * @return informaci�n del nodo
	 */
	public T getInfo() {
		return info;
	}
	
	/**
	 * M�todo que asigna el nodo pasado a la rama izquierda
	 * 
	 * @param nodo nodo a a�adir en la izquierda
	 */
	public void setLeft(BSTNode<T> nodo) {
		this.left=nodo;
	}
	
	/**
	 * M�todo que asigna el nodo pasado a la rama derecha
	 * 
	 * @param nodo nodo a a�adir en la derecha
	 */
    public void setRight(BSTNode<T> nodo) {
		this.right=nodo;
	}
    
    /**
     * M�todo que devuelve el nodo de la izquierda
     * 
     * @return nodo de la izquierda
     */
    public BSTNode<T> getLeft(){
    	return left;
    }
    
    /**
     * M�todo que duvelve el nodo de la derecha
     * 
     * @return nodo de la derecha
     */
    public BSTNode<T> getRight(){
    	return right;
    }
    
    /**
     * M�todo que devuelve una cadena con la informaci�n del nodo
     */
    public String toString() {
    	return info.toString();
    }


}
