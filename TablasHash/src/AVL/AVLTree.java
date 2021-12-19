package AVL;

import java.util.ArrayList;

public class AVLTree <T extends Comparable<T>>{
	
	private AVLNode<T> raiz;
	private ArrayList<T> nodos;
	
	public AVLTree() {
		raiz=null;
	}
	
	/**
	 * M�todo que devuelve el nodo que contiene la informac��n pasada si se
	 * encuentra, si no lo encuentra, es null o el arbol esta vacio devuelve 
	 * null
	 * 
	 * @param clave clave del nodo a buscar
	 * @return nodo si lo encuntra, null en los casos
	 */
	public AVLNode<T> searchNode(T clave){
		if(clave==null || raiz==null) return null;
		return searchNodeR(raiz,clave);
	}
	
	public AVLNode<T> searchNodeR(AVLNode<T> nodo, T clave){
		if(nodo == null) return null;
		else if (nodo.getInfo().compareTo(clave)==0) return nodo;
		else if (nodo.getInfo().compareTo(clave)>0)
			return  searchNodeR(nodo.getLeft(), clave);
		else return  searchNodeR(nodo.getRight(), clave);
	}
	
	/**
	 * M�todo que a�ade un nuevo nodo con la informaci�n pasada al �rbol.
	 * Devuelve  0 si a�ade correctamente. 
	 * Devuelve -2 si la clave es null. 
	 * Devuelve -1 si la clave ya existe
	 * 
	 * @param clave informaci�n del nodo a a�adir
	 * @return 0 si a�ade, el n� correspondiente en caso contrario
	 */
	public int addNode(T clave) {
		if(clave ==null) return -2;
		else if(this.searchNode(clave)!=null) return -1;
		else if(raiz == null) {
			raiz= new AVLNode<T>(clave);
			return 0;
		}
		raiz=addNodeR(raiz, clave);
		return 0;
			
	}
	
	/**
	 * M�todo recursivo que a�ade un nuevo nodo con la informaci�n pasada 
	 * partiendo del nodo pasado
	 * 
	 * @param nodo nodo a partir del cual se a�ade el nuevo
	 * @param clave informaci�n del nodo a a�adir
	 * @return 0 cuando se a�ade
	 */
	private AVLNode<T> addNodeR(AVLNode<T> nodo, T clave) {
		if(nodo.getInfo().compareTo(clave)>0) {
			if(nodo.getLeft()==null) {
				AVLNode<T> n=new AVLNode<T>(clave);
				nodo.setLeft(n);
				return updateAndBalanceIfNecesary(nodo);
			}
			nodo.setLeft(addNodeR(nodo.getLeft(),clave));
			nodo=updateAndBalanceIfNecesary(nodo);
		}else {
			if(nodo.getRight()==null) {
				AVLNode<T> n=new AVLNode<T>(clave);
				nodo.setRight(n);
				return updateAndBalanceIfNecesary(nodo);
			}
			nodo.setRight(addNodeR(nodo.getRight(),clave));
			nodo=updateAndBalanceIfNecesary(nodo);
		}
		nodo=updateAndBalanceIfNecesary(nodo);
		return nodo;
	}
	
	/**
	 * M�todo que devuelve el nodo actualizado con las rotaciones, 
	 * altura y factores de balances cambiados
	 * @param nodo nodo a actualizar
	 * @return nodo actualizado
	 */
	private AVLNode<T> updateAndBalanceIfNecesary(AVLNode<T> nodo) {
		nodo.updateBFHeight();
		if (nodo.getBF() == -2)
			if (nodo.getLeft().getBF() == 1)
				nodo = doubleLeftRotation(nodo);
			else // -1 o cero
				nodo = singleLeftRotation(nodo);
		else if (nodo.getBF() == 2)
			if (nodo.getRight().getBF() == -1)
				nodo = doubleRightRotation(nodo);
			else // 1 o cero
				nodo = singleRightRotation(nodo);
		return nodo;
	}

	/**
	 * M�todo que devuelve el nodo pasado tras aplicar una rotaci�n 
	 * simple a la izquierda
	 * @param nodo nodo a rotar
	 * @return nodo rotado
	 */
	private AVLNode<T> singleLeftRotation(AVLNode<T> nodo){
		AVLNode<T> aux = nodo.getLeft();
		nodo.setLeft(aux.getRight());
		nodo.updateBFHeight();
		aux.setRight(nodo);
		aux.updateBFHeight();
		return aux;
	}
	
	/**
	 * M�todo que devuelve el nodo pasado tras aplicar una rotaci�n 
	 * simple a la derecha
	 * @param nodo nodo a rotar
	 * @return nodo rotado
	 */
	private AVLNode<T> singleRightRotation (AVLNode<T> nodo){
		AVLNode<T> aux = nodo.getRight();
		nodo.setRight(aux.getLeft());
		nodo.updateBFHeight();
		aux.setLeft(nodo);
		aux.updateBFHeight();
		return aux;
	}
	
	/**
	 * M�todo que devuelve el nodo pasado tras aplicar una rotaci�n 
	 * doble a la izquierda
	 * @param nodo nodo a rotar
	 * @return nodo rotado
	 */
	private AVLNode<T> doubleLeftRotation (AVLNode<T> nodo){
		nodo.setLeft(singleRightRotation(nodo.getLeft()));
		nodo=this.singleLeftRotation(nodo);
		return nodo;
	}
	
	/**
	 * M�todo que devuelve el nodo pasado tras aplicar una rotaci�n 
	 * doble a la derecha
	 * @param nodo nodo a rotar
	 * @return nodo rotado
	 */
	private AVLNode<T> doubleRightRotation (AVLNode<T> nodo){
		nodo.setRight(singleLeftRotation(nodo.getRight()));
		nodo=this.singleRightRotation(nodo);
		return nodo;
	}
	
	

	/**
	 * M�todo que devuelve una cadena con el recorrido en preorder del �rbol
	 * 
	 * @return cadena con el recorrido en preorder del �rbol
	 */
	public String preOrder() {
		String cadena=preOrderR(raiz);
		if(cadena.length()>0) return cadena.substring(0,cadena.length()-1);
		return cadena;
	}

	/**
	 * M�todo recursivo que devuelve una cadena con el recorrido en preorder del
	 * �rbol a partir del nodo pasado
	 * 
	 * @param nodo nodo a partir del cual se realiza el recorrido
	 * @return cadena con el recorrido en preorder
	 */ 
	private String preOrderR(AVLNode<T> nodo) {
		String str ="";
		if(nodo!=null) {
		str+=nodo.toString()+"\t";
	    str+=preOrderR(nodo.getLeft());
	    str+=preOrderR(nodo.getRight());
		}
		return str;
	}
	
	/**
	 * M�todo que devuelve una cadena con el recorrido en postorder del �rbol
	 * 
	 * @return cadena con el recorrido en postorder del �rbol
	 */
	public String postOrder() {
		String cadena=postOrderR(raiz);
		if(cadena.length()>0) return cadena.substring(0,cadena.length()-1);
		return cadena;
	}

	/**
	 * M�todo recursivo que devuelve una cadena con el recorrido en postorder 
	 * del�rbol a partir del nodo pasado
	 * 
	 * @param nodo nodo a partir del cual se realiza el recorrido
	 * @return cadena con el recorrido en postorder
	 */ 
	private String postOrderR(AVLNode<T> nodo) {
		String str ="";
		if(nodo!=null) {
	    str+=postOrderR(nodo.getLeft());
	    str+=postOrderR(nodo.getRight());
	    str+=nodo.toString()+"\t";
		}
		return str;
	}
	
	/**
	 * M�todo que devuelve una cadena con el recorrido en inorder del �rbol
	 * 
	 * @return cadena con el recorrido en inorder del �rbol
	 */
	public String inOrder() {
		String cadena=inOrderR(raiz);
		if(cadena.length()>0) return cadena.substring(0,cadena.length()-1);
		return cadena;
	}

	
	/**
	 * M�todo recursivo que devuelve una cadena con el recorrido en inorder del
	 * �rbol a partir del nodo pasado
	 * 
	 * @param nodo nodo a partir del cual se realiza el recorrido
	 * @return cadena con el recorrido en inorder
	 */ 
	private String inOrderR(AVLNode<T> nodo) {
		String str ="";
		if(nodo!=null) {
	    str+=inOrderR(nodo.getLeft());
	    str+=nodo.toString()+"\t";
	    str+=inOrderR(nodo.getRight());
		}
		return str;
	}
	
	/**
	 * M�todo que borra el nodo con la informaci�n pasada como par�metro
	 * Devuelve  0 si se borra el elemento.-
	 * Devuelve  2 si la clave es null o �rbol vac�o. 
	 * Devuelve -1 si la clave no existe. 
	 * 
	 * @param clave informaci�n del nodo a borrar
	 * @return 0 si a�ade, el n� correspondiente en caso contrario
	 */
	public int removeNode(T clave) {
		if(clave==null || raiz==null) return -2;
		else if(this.searchNode(clave)==null) return -1;
		raiz = removeNodeR(raiz,clave);
		return 0;
		
	}

	/**
	 * M�todo recursivo que borra el nodo que contiene la informaci�n pasada a 
	 * partir del nodo pasado
	 * 
	 * @param nodo nodo a partir del cual se borra el deseado
	 * @param clave informaci�n del nodo a borrar
	 * @return 0 si se borra correctamente
	 */
	private AVLNode<T> removeNodeR(AVLNode<T> nodo,T clave) {
		if(nodo.getInfo().compareTo(clave)==0) {
			if(nodo.getLeft()==null && nodo.getRight()==null) {
				return null;
			}else if(nodo.getLeft()==null) {
				return nodo.getRight();
			}else if(nodo.getRight()==null) {
				return nodo.getLeft();
			}else {
				AVLNode<T> max = maxRightR(nodo.getLeft());
				nodo.setLeft(removeNodeR(nodo.getLeft(),max.getInfo()));
				nodo.setInfo(max.getInfo());
				return updateAndBalanceIfNecesary(nodo);
			}
			
		}else if(nodo.getInfo().compareTo(clave)>0)
			nodo.setLeft(removeNodeR(nodo.getLeft(),clave));
		else 
			nodo.setRight(removeNodeR(nodo.getRight(),clave));
		return updateAndBalanceIfNecesary(nodo);
		
	}

	/**
	 * M�todo que busca el nodo con la clave m�xima en el subarbol izquierdo
	 * @param nodo nodo a partir del que buscar
	 * @return nodo conn  la clave m�xima del subarbol izquierdo
	 */
	private AVLNode<T> maxRightR(AVLNode<T> nodo) {
	   if(nodo==null) return null;
	   else if(nodo.getRight()==null) return nodo;
	   else return maxRightR(nodo.getRight());
	}
	
	/**
	 * M�todo que devuelve el padre de un nodo con la clave pasada
	 * Si el nodo no tiene padre, no existe el nodo o el nodo o el arbol es null
	 *  devuelve null
	 * @param clave clave del nodo a buscar el padre
	 * @return el padre si lo encuentra null en otro caso
	 */
	public AVLNode<T> padreDe(T clave){
		if(clave==null || raiz ==null) return null;
		if(this.searchNode(clave)==null)return null;
		if(raiz.equals(this.searchNode(clave))) return null;
		return padreDeR(raiz,clave);
		
	}

	/**
	 * M�todo recursivo que devuelve el padre del nodo con la clave pasada
	 * @param nodo nodo de partida
	 * @param clave clave del nodo a buscar
	 * @return padre del nodo
	 */
	private AVLNode<T> padreDeR(AVLNode<T> nodo, T clave) {
		if(nodo == null) return null;
		else if (nodo.getInfo().compareTo(clave)>0 && nodo.getLeft()!=null) { 
		     if(nodo.getLeft().getInfo().compareTo(clave)==0) return nodo;
		     return padreDeR(nodo.getLeft(),clave);
		}
		else if (nodo.getInfo().compareTo(clave)<0 && nodo.getRight()!=null) { 
		     if(nodo.getRight().getInfo().compareTo(clave)==0) return nodo;
		     return padreDeR(nodo.getRight(),clave);
		}
		return null;
		
	}
	
	/**
	 * M�todo que devuelve el n�mero de aristas entre dos nodos con las claves 
	 * pasadas.
	 * Devuelve 0 si clave1 es null o clave2 es null o el �rbol es null o 
	 * clave1 y clave2 son iguales
	 * @param clave1 clave del nodo de partida
	 * @param clave2 clave del nodo final
	 * @return numero de aristas o 0 si son iguales o null alguna de ellas
	 */
	public int numAristas(T clave1, T clave2) {
		if(clave1==null || clave2==null || raiz ==null 
				|| clave1.compareTo(clave2)==0) return 0;
		AVLNode<T> nodo = this.searchNode(clave1);
		return numAristasR(nodo,clave2);
		
	}

	/**
	 * M�todo recursivo que calcula el n�mero de aristas desde un nodo hasta
	 * otro con la clave pasada
	 * @param nodo nodo de partida
	 * @param clave2 clave del nodo final
	 * @return n�mero de aristas entre los dos nodos
	 */
	private int numAristasR(AVLNode<T> nodo, T clave2) {	
		int aristas=1;
		if(nodo.getInfo().compareTo(clave2)==0) return 0;
		else if (nodo.getInfo().compareTo(clave2)<0)
			aristas+=numAristasR(nodo.getRight(), clave2);
		else if (nodo.getInfo().compareTo(clave2)>0)
			aristas+=numAristasR(nodo.getLeft(), clave2);
		return aristas;
	}
	
	public ArrayList<T> nodos(){
		nodos= new ArrayList<T>();
		if(raiz==null) return nodos;
		nodosR(this.raiz);
		return nodos;
	}

	private void nodosR(AVLNode<T> aux) {
		if(aux!=null) {
		nodosR(aux.getLeft());
		nodos.add(aux.getInfo());
		nodosR(aux.getRight());
		}
	}

}
