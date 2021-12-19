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
	public void setLeft(AVLNode<T> nodo) {
		this.left=nodo;
	}
	
	/**
	 * M�todo que asigna el nodo pasado a la rama derecha
	 * 
	 * @param nodo nodo a a�adir en la derecha
	 */
    public void setRight(AVLNode<T> nodo) {
		this.right=nodo;
	}
    
    /**
     * M�todo que devuelve el nodo de la izquierda
     * 
     * @return nodo de la izquierda
     */
    public AVLNode<T> getLeft(){
    	return left;
    }
    
    /**
     * M�todo que duvelve el nodo de la derecha
     * 
     * @return nodo de la derecha
     */
    public AVLNode<T> getRight(){
    	return right;
    }
    
    /**
     * M�todo que devuelve una cadena con la informaci�n del nodo
     */
    public String toString() {
    	return info.toString()+":BF="+ getBF();
    }

    /**
     * M�todo que devuelve el factor de balance del nodo
     * 
     * @return factor de balance del nodo
     */
	public int getBF() {
		return balanceFactor;
	}
	
	 /**
     * M�todo que devuelve la altura del nodo
     * 
     * @return altura del nodo
     */
	public int getHeight() {
		return height;
	}
	
	/**
	 * M�todo que actualiza el valor de la altura y factor de balance del nodo
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
