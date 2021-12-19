package TablaHash;

import java.lang.reflect.Array;
import java.util.ArrayList;

import AVL.AVLTree;

public class OpenHash <T extends Comparable<T>> extends AbstractHash<T>{
	
	private int numElementos;
	private AVLTree<T> tabla[];
	private double minlf;
	private double maxlf;
	
	public static final double MINIMUN_LF= 0.33;
	public static final double MAXIMUN_LF= 1;

	@SuppressWarnings("unchecked")
	public OpenHash(int tam){
		this.numElementos=0;
		this.minlf=MINIMUN_LF;
		this.maxlf= MAXIMUN_LF;
		if(!isPositivePrime(tam)) tam = nextPrimeNumber(tam);
		this.tabla=(AVLTree<T>[]) Array.newInstance(AVLTree.class, tam);
		inicializarTabla(tam);
	}

	private void inicializarTabla(int tam) {
		for(int i=0; i < tam;i++)
			tabla[i] = new AVLTree<T>();
	}
	
	public OpenHash(int tam,double min,double max) {
		this(tam);
		this.minlf=min;
		this.maxlf=max;
	}
	
	@Override
	public int getNumOfElems() {
		return numElementos;
	}
	
	/**
	 * Método que calcula el factor de carga de la tabla Hash
	 * 
	 * @return factor de carga de la tabla
	 */
	public float calcularFactorCarga() {
		return (float) (numElementos/(getSize()*1.0));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void reDispersion() {
		if(calcularFactorCarga()>=maxlf) {
			AVLTree<T>[] aux =tabla;
			numElementos=0;
			int tam =this.nextPrimeNumber(getSize()*2);
			tabla= (AVLTree<T>[]) Array.newInstance(AVLTree.class, tam);
			inicializarTabla(tam);
			añadirNodos(aux);
			}
		
	}
	
	private void añadirNodos(AVLTree<T>[] aux) {
		for(int i = 0;i < aux.length;i++) {
			ArrayList<T> nodos= aux[i].nodos();
			if(nodos!=null) {
				for(T elemento : nodos) {
					tabla[i].addNode(elemento);	
					numElementos++;
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void inservseRedispersion() {
		if(calcularFactorCarga()<=minlf) {
			AVLTree<T>[] aux =tabla;
			numElementos=0;
			int tam =getSize()/2;
			tabla= (AVLTree<T>[]) Array.newInstance(AVLTree.class, tam);
			inicializarTabla(tam);
			añadirNodos(aux);
			}
		
	}

	@Override
	public int getSize() {
		return tabla.length;
	}

	@Override
	public int add(T elemento) {
		if(elemento==null) return -2;
		int pos =fHash(elemento);
		tabla[pos].addNode( elemento);
		numElementos++;
		reDispersion();
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T find(T elemento) {
		if(elemento==null) return null;
		int pos =fHash(elemento);
		return (T) tabla[pos].searchNode(elemento);
	}

	@Override
	public int remove(T elemento) {
		if(elemento==null) return -2;
		int pos = fHash(elemento);
		int borrado = tabla[pos].removeNode(elemento);
		if(borrado==0) {
			numElementos--;
			inservseRedispersion();
		}
		return borrado;
	}

	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		for(int i=0;i< getSize();i++){
		cadena.append(tabla[i].inOrder());
		cadena.append(";");
		}
		cadena.append("[Size: ");
		cadena.append(getSize());
		cadena.append(" Num.Elems.: ");
		cadena.append(getNumOfElems());
		cadena.append("]");
		return cadena.toString();
		}

}
