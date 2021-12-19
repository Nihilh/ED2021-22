package BST;

public class BSTree<T extends Comparable<T>> {

	private BSTNode<T> raiz;
	
	/**
	 * Constructor del árbol
	 */
	public BSTree() {
		raiz=null;
	}
	
	/**
	 * Método que devuelve el nodo que contiene la informacíón pasada si se
	 * encuentra, si no lo encuentra, es null o el arbol esta vacio devuelve 
	 * null
	 * 
	 * @param clave clave del nodo a buscar
	 * @return nodo si lo encuntra, null en los casos
	 */
	public BSTNode<T> searchNode(T clave){
		if(clave==null || raiz==null) return null;
		return searchNodeR(raiz,clave);
	}
	
	public BSTNode<T> searchNodeR(BSTNode<T> nodo, T clave){
		if(nodo == null) return null;
		else if (nodo.getInfo().compareTo(clave)==0) return nodo;
		else if (nodo.getInfo().compareTo(clave)>0)
			return  searchNodeR(nodo.getLeft(), clave);
		else return  searchNodeR(nodo.getRight(), clave);
	}
	
	/**
	 * Método que añade un nuevo nodo con la información pasada al árbol.
	 * Devuelve  0 si añade correctamente. 
	 * Devuelve -2 si la clave es null. 
	 * Devuelve -1 si la clave ya existe
	 * 
	 * @param clave información del nodo a añadir
	 * @return 0 si añade, el nº correspondiente en caso contrario
	 */
	public int addNode(T clave) {
		if(clave ==null) return -2;
		else if(this.searchNode(clave)!=null) return -1;
		else if(raiz == null) {
			raiz= new BSTNode<T>(clave);
			return 0;
		}
		return addNodeR(raiz, clave);
			
	}
	
	/**
	 * Método recursivo que añade un nuevo nodo con la información pasada 
	 * partiendo del nodo pasado
	 * 
	 * @param nodo nodo a partir del cual se añade el nuevo
	 * @param clave información del nodo a añadir
	 * @return 0 cuando se añade
	 */
	private int addNodeR(BSTNode<T> nodo, T clave) {
		if(nodo.getInfo().compareTo(clave)>0) {
			if(nodo.getLeft()==null) {
				nodo.setLeft(new BSTNode<T>(clave));
				return 0;
			}
			 addNodeR(nodo.getLeft(),clave);
		}else {
			if(nodo.getRight()==null) {
				nodo.setRight(new BSTNode<T>(clave));
				return 0;
			}
			 addNodeR(nodo.getRight(),clave);
		}
		return 0;
		
	}

	/**
	 * Método que devuelve una cadena con el recorrido en preorder del árbol
	 * 
	 * @return cadena con el recorrido en preorder del árbol
	 */
	public String preOrder() {
		String cadena=preOrderR(raiz);
		if(cadena.length()>0) return cadena.substring(0,cadena.length()-1);
		return cadena;
	}

	/**
	 * Método recursivo que devuelve una cadena con el recorrido en preorder del
	 * árbol a partir del nodo pasado
	 * 
	 * @param nodo nodo a partir del cual se realiza el recorrido
	 * @return cadena con el recorrido en preorder
	 */ 
	private String preOrderR(BSTNode<T> nodo) {
		String str ="";
		if(nodo!=null) {
		str+=nodo.toString()+"\t";
	    str+=preOrderR(nodo.getLeft());
	    str+=preOrderR(nodo.getRight());
		}
		return str;
	}
	
	/**
	 * Método que devuelve una cadena con el recorrido en postorder del árbol
	 * 
	 * @return cadena con el recorrido en postorder del árbol
	 */
	public String postOrder() {
		String cadena=postOrderR(raiz);
		if(cadena.length()>0) return cadena.substring(0,cadena.length()-1);
		return cadena;
	}

	/**
	 * Método recursivo que devuelve una cadena con el recorrido en postorder 
	 * delárbol a partir del nodo pasado
	 * 
	 * @param nodo nodo a partir del cual se realiza el recorrido
	 * @return cadena con el recorrido en postorder
	 */ 
	private String postOrderR(BSTNode<T> nodo) {
		String str ="";
		if(nodo!=null) {
	    str+=postOrderR(nodo.getLeft());
	    str+=postOrderR(nodo.getRight());
	    str+=nodo.toString()+"\t";
		}
		return str;
	}
	
	/**
	 * Método que devuelve una cadena con el recorrido en inorder del árbol
	 * 
	 * @return cadena con el recorrido en inorder del árbol
	 */
	public String inOrder() {
		String cadena=inOrderR(raiz);
		if(cadena.length()>0) return cadena.substring(0,cadena.length()-1);
		return cadena;
	}

	
	/**
	 * Método recursivo que devuelve una cadena con el recorrido en inorder del
	 * árbol a partir del nodo pasado
	 * 
	 * @param nodo nodo a partir del cual se realiza el recorrido
	 * @return cadena con el recorrido en inorder
	 */ 
	private String inOrderR(BSTNode<T> nodo) {
		String str ="";
		if(nodo!=null) {
	    str+=inOrderR(nodo.getLeft());
	    str+=nodo.toString()+"\t";
	    str+=inOrderR(nodo.getRight());
		}
		return str;
	}
	
	/**
	 * Método que borra el nodo con la información pasada como parámetro
	 * Devuelve  0 si se borra el elemento.-
	 * Devuelve  2 si la clave es null o árbol vacío. 
	 * Devuelve -1 si la clave no existe. 
	 * 
	 * @param clave información del nodo a borrar
	 * @return 0 si añade, el nº correspondiente en caso contrario
	 */
	public int removeNode(T clave) {
		if(clave==null || raiz==null) return -2;
		else if(this.searchNode(clave)==null) return -1;
		raiz = removeNodeR(raiz,clave);
		return 0;
		
	}

	/**
	 * Método recursivo que borra el nodo que contiene la información pasada a 
	 * partir del nodo pasado
	 * 
	 * @param nodo nodo a partir del cual se borra el deseado
	 * @param clave información del nodo a borrar
	 * @return 0 si se borra correctamente
	 */
	private BSTNode<T> removeNodeR(BSTNode<T> nodo,T clave) {
		if(nodo.getInfo().compareTo(clave)==0) {
			if(nodo.getLeft()==null && nodo.getRight()==null) {
				return null;
			}else if(nodo.getLeft()==null) {
				return nodo.getRight();
			}else if(nodo.getRight()==null) {
				return nodo.getLeft();
			}else {
				BSTNode<T> max = maxRightR(nodo.getLeft());
				removeNode(max.getInfo());
				nodo.setInfo(max.getInfo());
				return nodo;
			}
			
		}else if(nodo.getInfo().compareTo(clave)>0)
			nodo.setLeft(removeNodeR(nodo.getLeft(),clave));
		else 
			nodo.setRight(removeNodeR(nodo.getRight(),clave));
		return nodo;
		
	}

	/**
	 * Método que busca el nodo con la clave máxima en el subarbol izquierdo
	 * @param nodo nodo a partir del que buscar
	 * @return nodo conn  la clave máxima del subarbol izquierdo
	 */
	private BSTNode<T> maxRightR(BSTNode<T> nodo) {
	   if(nodo==null) return null;
	   else if(nodo.getRight()==null) return nodo;
	   else return maxRightR(nodo.getRight());
	}
	
	/**
	 * Método que calcula la altura de un árbol
	 */
	public int getHeight() {
		return heightR(raiz);
	}
	
	/**
	 * Método que calcula la altura de un nodo pasado como parámetro
	 * @param nodo nodo del que queremos saber la altura
	 * @return altura del nodo
	 */
	private int heightR(BSTNode<T> nodo) {
		if(nodo==null) return 0;
		return 1+Math.max(heightR(nodo.getLeft()), heightR(nodo.getRight()));
	}
	
//	private int heihgtR2(BSTNode<T> nodo) {
//		if(nodo==null) return 0;
//		int altura = 1;
//		if(nodo.getLeft()!=null && nodo.getRight()!=null)
//			altura+=Integer.max(heihgtR2(nodo.getLeft()), heihgtR2(nodo.getRight()));
//		else if(nodo.getLeft()!=null)
//			altura+=heihgtR2(nodo.getLeft());
//		else 
//			altura+=heihgtR2(nodo.getRight());
//		
//		return altura;
//		
//	}
	
	/**
	 * Método que calcula el número de nodos de un árbol
	 * 
	 * @return número de nodos de un árbol
	 */
	public int numeroNodos() {
		return numeroNodosR(raiz);
	}

	private int numeroNodosR(BSTNode<T> nodo) {
		if(nodo==null) return 0;
		int i=1;
		if(nodo.getLeft()!=null && nodo.getRight()!=null) {
			i+= numeroNodosR(nodo.getLeft());
			i+= numeroNodosR(nodo.getRight());
		}else if (nodo.getLeft()!=null) 
			i+= numeroNodosR(nodo.getLeft());
		else 
			i+= numeroNodosR(nodo.getRight());
			
		return i;
	}
	
	/**
	 * Método que devuelve si un árbol es degenerado
	 * 
	 * @return verdadero si es degenerado, falso en otro caso
	 */
	public boolean esDegenerado() {
		return esDegeneradoR(raiz);
	}
	
	private boolean esDegeneradoR(BSTNode<T> nodo) {
		if(nodo==null) return true;
		boolean degenerado= true;
		if(nodo.getLeft()!=null && nodo.getRight()!=null) return false;
		else if (nodo.getLeft()!=null) 
			degenerado&=esDegeneradoR(nodo.getLeft());
		else  
			degenerado&=esDegeneradoR(nodo.getRight());
		return degenerado;
	}
	
	/**
	 * Método que calcula si un árbol es de latura minima
	 * 
	 * No tiene porque ser AVL
	 * 
	 * @return verdadero si es de altura minima, falso en otro caso
	 */
	public boolean esAlturaMinima() {
		return (Math.round(Math.log10(numeroNodos()+1)/Math.log10(2)))
				==getHeight();
	}
	
	/**
	 * Método que devuelve si un árbol es perfectamente equilibrado
	 * 
	 * Se cumple que tambien es de altura minima y AVL
	 * 
	 * @return verdadero si es perfectamente equilibrado, falso en otro caso
	 */
	public boolean esEquilibrado() {
		return Math.abs(
				numeroNodosR(raiz.getLeft())-numeroNodosR(raiz.getRight()))<=1;
	}
	
	/**
	 * Método que devuelve si el arbol es lleno
	 * 
	 * @return verdadero si el arbol es lleno, falso en otro caso
	 */
	public boolean esLleno() {
		return numeroNodos()==Math.pow(2, getHeight())-1;
	}

	/**
	 * Método que devuelve el número de nodos del nivel pasado como parámetro
	 * 
	 * @param nivel nivel del que quremos saber el número de nodos
	 * @return número de nodos del nivel
	 */
	public int nodosNivel(int nivel) {
		return nodosNivelR(raiz, nivel);
	}

	private int nodosNivelR(BSTNode<T> nodo, int nivel) {
		if (nodo ==null) return 0;
		int nodos=0;

		if(nivel==1) return 1;
		
		nodos+=nodosNivelR(nodo.getLeft(), nivel-1);
		nodos+=nodosNivelR(nodo.getRight(), nivel-1);
		
		return nodos; 
	}
	
	/**
	 * Método que devuelve la Longuitud del Camino Interno
	 * 
	 * @return LCI
	 */
	public int LCI() {
		int lci=0;
		for(int i =1;i <=getHeight();i++) {
			lci+=nodosNivel(i)*i;
		}
		return lci;
	}
	
	/**
	 * Método que devuelve la longuitud media dwel camino interno
	 * @return
	 */
	public double LCIm() {
		return (double)LCI()/numeroNodos();
	}
	
	/**
	 * Método que devuelve la Longuitud del Camino Externo
	 * 
	 * @return LCE
	 */
	public int LCE() {
		int lce=0;
		for(int i =2;i <=getHeight();i++) {
			lce+= (Math.pow(2, i-1)-nodosNivel(i))*i;
		}
		lce+=2*nodosNivel(getHeight())*(getHeight()+1);
		return lce;
	}
	
	
	/**
	 * Método que devuelve la longuitud media dwel camino externo
	 * @return
	 */
	public double LCEm() {
		int nodos=0;
		for(int i =2;i <=getHeight();i++) {
			nodos+= Math.pow(2, i-1)-nodosNivel(i);
		}
		nodos+=2*nodosNivel(getHeight());
		return (double)LCE()/nodos;
	}
	
	/**
	 * Método que devuelve una cadena con todos los nodos 
	 * que tienen dos hijos
	 * 
	 * @return Cadena con los nodos que tienen dos hijos
	 */
	public String nodosCompletos() {
		return nodosCompletosR(raiz);
	}

	private String nodosCompletosR(BSTNode<T> nodo) {
		if(nodo==null) return "";
		String str="";
		if(nodo.getLeft()!=null && nodo.getRight()!=null) 
			str+=nodo.getInfo().toString()+"\t";
		if (nodo.getLeft()!=null)
			str+= nodosCompletosR(nodo.getLeft());
		if (nodo.getRight()!=null)
			str+= nodosCompletosR(nodo.getRight());
		return str;
	}
	
	/**
	 * Método que devuelve todos los nodos comprendidos entre dos claves
	 * @param menor clave minima
	 * @param mayor clave maxima
	 * 
	 * @return Cadena con los nodos comprendidos entre las claves
	 */
	public String nodosComprendidos(T menor, T mayor) {
		return nodosComprendidosR(raiz,menor,mayor);
		
	}

	private String nodosComprendidosR(BSTNode<T> nodo, T menor, T mayor) {
		if(nodo==null) return "";
		String str="";
		if(nodo.getInfo().compareTo(menor)>=0 && 
				nodo.getInfo().compareTo(mayor)<=0) {
			str+=nodo.getInfo().toString()+"\t";
		    str+=nodosComprendidosR(nodo.getRight(), menor, mayor);
		    str+=nodosComprendidosR(nodo.getLeft(), menor, mayor);
		}
	    if (nodo.getInfo().compareTo(menor)<0)
			str+=nodosComprendidosR(nodo.getRight(), menor, mayor);
		if (nodo.getInfo().compareTo(mayor)>0)
			str+=nodosComprendidosR(nodo.getLeft(), menor, mayor);
		return str;
	}
	
	/**
	 * Método que devuelve una cadena con los ancestros de la clave pasada
	 * @param clave
	 * @return
	 */
	public String ancestrosNodo(T clave) {
		if(clave==null) return "";
		return ancestrosNodoR(raiz,clave);
	}

	private String ancestrosNodoR(BSTNode<T> nodo, T clave) {
		if(nodo==null) return "";
		String str="";
		BSTNode<T> dch = searchNodeR(nodo.getRight(), clave);
		BSTNode<T> izq = searchNodeR(nodo.getLeft(), clave);
		if (dch!=null || izq!=null) 
			str+=nodo.getInfo().toString()+"\t";
		if(izq !=null )
			str+=ancestrosNodoR(nodo.getLeft(), clave);
		else if(dch != null )
			str+=ancestrosNodoR(nodo.getRight(), clave);
		return str;
	}
	
}
