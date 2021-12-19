package AVL;

public class AVLNode <T extends Comparable<T>>{
	
	private T info; //Contenido del nodo
	private AVLNode<T> left; //Nodo hijo izquierdo
	private AVLNode<T> right; //Nodo hijo derecho
	private int balanceFactor; //Factor de balance 
	private int height; //Altura del nodo
	
	public AVLNode(T clave ) {
		this.info=clave;
		left=null;
		right=null;
		balanceFactor=0;
		height=0;
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
	public void setLeft(AVLNode<T> nodo) {
		this.left=nodo;
	}
	
	/**
	 * Método que asigna el nodo pasado a la rama derecha
	 * 
	 * @param nodo nodo a añadir en la derecha
	 */
    public void setRight(AVLNode<T> nodo) {
		this.right=nodo;
	}
    
    /**
     * Método que devuelve el nodo de la izquierda
     * 
     * @return nodo de la izquierda
     */
    public AVLNode<T> getLeft(){
    	return left;
    }
    
    /**
     * Método que duvelve el nodo de la derecha
     * 
     * @return nodo de la derecha
     */
    public AVLNode<T> getRight(){
    	return right;
    }
    
    /**
     * Método que devuelve una cadena con la información del nodo
     */
    public String toString() {
    	return info.toString()+":BF="+ getBF();
    }

    /**
     * Método que devuelve el factor de balance del nodo
     * 
     * @return factor de balance del nodo
     */
	public int getBF() {
		return balanceFactor;
	}
	
	 /**
     * Método que devuelve la altura del nodo
     * 
     * @return altura del nodo
     */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Método que actualiza el valor de la altura y factor de balance del nodo
	 */
	public void updateBFHeight() {
		if (getLeft()!= null && getRight()!=null) {
			height=1+Integer.max(getLeft().getHeight(),getRight().getHeight());
			balanceFactor= getRight().getHeight()-getLeft().getHeight();
		}else if (getLeft()!= null) {
			height =1+ getLeft().getHeight();
			balanceFactor= (-1) - getLeft().getHeight();
		}else if (getRight()!= null) {
			height =1+ getRight().getHeight();
			balanceFactor= getRight().getHeight() - (-1);
		}else {
			height =0;
			balanceFactor= 0;
		}
	}

}
