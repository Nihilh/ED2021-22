package BST;

public class BSTNode<T extends Comparable<T>> {
	
	private T info;
	private BSTNode<T> left;
	private BSTNode<T> right;
	/**
	 * Constructor de la clase
	 * 
	 * @param clave Información que va a almacenar el nodo
	 */
	public BSTNode(T clave) {
		info=clave;
		left=null;
		right=null;
	}
	/**
	 * Método que asigna a la información del nodo la pasada como parámetro
	 * 
	 * @param clave información del nodo a añadir
	 */
	public void setInfo(T clave) {
		this.info=clave;
	}
	
	/**
	 * Método que devuelve la información del nodo
	 * 
	 * @return información del nodo
	 */
	public T getInfo() {
		return info;
	}
	
	/**
	 * Método que asigna el nodo pasado a la rama izquierda
	 * 
	 * @param nodo nodo a añadir en la izquierda
	 */
	public void setLeft(BSTNode<T> nodo) {
		this.left=nodo;
	}
	
	/**
	 * Método que asigna el nodo pasado a la rama derecha
	 * 
	 * @param nodo nodo a añadir en la derecha
	 */
    public void setRight(BSTNode<T> nodo) {
		this.right=nodo;
	}
    
    /**
     * Método que devuelve el nodo de la izquierda
     * 
     * @return nodo de la izquierda
     */
    public BSTNode<T> getLeft(){
    	return left;
    }
    
    /**
     * Método que duvelve el nodo de la derecha
     * 
     * @return nodo de la derecha
     */
    public BSTNode<T> getRight(){
    	return right;
    }
    
    /**
     * Método que devuelve una cadena con la información del nodo
     */
    public String toString() {
    	return info.toString();
    }


}
