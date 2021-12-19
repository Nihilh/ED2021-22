package TablaHash;

import java.lang.reflect.Array;

public class ClosedHashTable<T> extends AbstractHash<T> {
	
	//Factor de carga minimo por defecto
	private static final double MINIMUN_LF=0.16; 
	//Factor de carga máximo por defecto
	private static final double MAXIMUN_LF=0.5; 
	
	public static final int LINEAL =1; //Exploración lineal
	public static final int CUADRATICA = 2; //Exploración cuadratica
	public static final int DISPERSION_DOBLE = 3; //Optativa
	
	private int numElementos; //Número de elementos insertados hasta el momento
	private HashNode<T>[] tabla; //Tabla Hash
	private int tipoExploracion; //Tipo de exploracion
	private double minlf; //Factor de carga minimo
	private double maxlf; //Factor de craga máximo
	
	
	/*
	 * Constructor de ClosedHashTable que crea una tabla con el tamñao del 
	 * número pasado si esprimo o el siguiente al mismo en su defecto y el tipo
	 * de exploracion pasado
	 * 
	 * @param tam Tamaño de la tabla
	 * @param tipo Tipo de exploración
	 */
	@SuppressWarnings("unchecked")
	public ClosedHashTable(int tam, int tipo) {
		numElementos=0;
		tipoExploracion=tipo;
		this.minlf= MINIMUN_LF;
		this.maxlf=MAXIMUN_LF;
		if(!isPositivePrime(tam)) tam =nextPrimeNumber(tam);
		tabla= ( HashNode<T>[]) Array.newInstance(HashNode.class, tam);
		inicializarTabla(tam);
	}
	
	/*
	 * Constructor de ClosedHashTable que crea una tabla con el tamñao del 
	 * número pasado si esprimo o el siguiente al mismo en su defecto, el tipo
	 * de exploracion pasado y los márgenes del factor de carga
	 * 
	 * @param tam Tamaño de la tabla
	 * @param tipo Tipo de exploración
	 * @param minlf Factor de carga minimo
	 * @param maxlf Factor de carga máximo
	 */
	public ClosedHashTable(int tam, int tipoExploracion, double minlf, double maxlf) {
		this(tam,tipoExploracion);
		this.minlf = minlf;
		this.maxlf = maxlf;
	}


	/**
	 * Método que inicializa la tabla con todas las posiciones con nodos vacios
	 * 
	 * @param tam Tamaño de la tabla
	 */
	private void inicializarTabla(int tam) {
		for(int i =0; i<tam;i++ ) 
			tabla[i]= new HashNode<T>();
	}
	
	/**
	 * Método que calcula el factor de carga de la tabla Hash
	 * 
	 * @return factor de carga de la tabla
	 */
	public float calcularFactorCarga() {
		return (float) (numElementos/(getSize()*1.0));
	}

	@Override
	/*
	 * Método que devuelve el número de elementos de la tabla
	 * 
	 * @return Número de elementos de la tabla
	 */
	public int getNumOfElems() {
		return numElementos;
	}

	@Override
	/*
	 * Método que devuelve el tamaño de la tabla
	 * 
	 * @return Tamaño de la tabla
	 */
	public int getSize() {
		return tabla.length;
	}

	@Override
	/*
	 * Método que añade un nodo con la información pasada como parámetro en la 
	 * posición correspondiente de la tabla y aplica la redispersión si es
	 * necesario.
	 * 
	 * Devuelve -2 si el elemento es null.
	 * Devuelve -1 si se agotan las posibilidades de añadir la información.
	 * Devuelve 0 si se añade correctamente.
	 * 
	 * @param elemento Información del nodo a añadir
	 * 
	 * @return 0 si se añade correctamente, el nº correspondiente en otro caso
	 */
	public int add(T elemento) {
		if(elemento==null) return -2;
		int pos =fHash(elemento);
		int intento=1;
		while(tabla[pos].getStatus()==HashNode.LLENO && intento<getSize()) {
			pos = posicionSiguiente(elemento,intento);
			intento++;
		}
		if(intento==getSize()) return -1;
		tabla[pos].setInfo(elemento);
		numElementos++;
		reDispersion();
		return 0;
	}

	/*
	 * Método que calcula el nuevo tamaño de la tabla si se alcanza el factor 
	 * de carga máximo y recoloca los elementos de la tabla en sus
	 * correspondientes posiciones
	 */
	@SuppressWarnings("unchecked")
	protected void reDispersion() {
		if(calcularFactorCarga()>=maxlf) {
		HashNode<T>[] aux =tabla;
		numElementos=0;
		int tam =this.nextPrimeNumber(getSize()*2);
		tabla= ( HashNode<T>[]) Array.newInstance(HashNode.class, tam);
		inicializarTabla(tam);
		for(int i = 0;i < aux.length;i++) {
			if(aux[i].getStatus()==HashNode.LLENO) add(aux[i].getInfo());
		 }
		}
	}
	
	/*
	 * Método que calcula el nuevo tamaño de la tabla si se alcanza el factor 
	 * de carga minimo y recoloca los elementos de la tabla en sus
	 * correspondientes posiciones
	 */
	@SuppressWarnings("unchecked")
	protected void inservseRedispersion() {
		if(calcularFactorCarga()<=minlf) {
			HashNode<T>[] aux =tabla;
			numElementos=0;
			int tam =getSize()/2;
			if(!isPositivePrime(tam)) tam = previousPrimeNumber(tam);
			tabla= ( HashNode<T>[]) Array.newInstance(HashNode.class, tam);
			inicializarTabla(tam);
			for(int i = 0;i < aux.length;i++) {
				if(aux[i].getStatus()==HashNode.LLENO) add(aux[i].getInfo());
			 }
			}
	}
	
	
	/*
	 * Método que devuelve la posición del elemento en función del tipo de 
	 * exploración y el número de intento pasado
	 * 
	 * @param elemento Elemento del que queremos saber la posición
	 * @param intento Número del intento
	 * 
	 * @return Posición del elemento
	 */
	private int posicionSiguiente(T elemento, int intento) {
		if(tipoExploracion==LINEAL) {
			return (fHash(elemento)+intento)%getSize();
		}else if (tipoExploracion==CUADRATICA) {
			return (fHash(elemento)+intento*intento)%getSize();
		}else if(tipoExploracion==DISPERSION_DOBLE) {
			return (fHash(elemento)+intento*disDoble(elemento))%getSize();
		}
		return -1;
	}

	/**
	 * Método que calcula la función de dispwersión doble
	 * 
	 * @param elemento elemento
	 * @return resultado de la función
	 */
	private int disDoble(T elemento) {
		int number=previousPrimeNumber(getSize())-(fHash(elemento)
				%previousPrimeNumber(getSize()));
		return number;
	}

	@Override
	/*
	 * Método que devuelve el elemento que coincide con la información pasada 
	 * como parámetro.
	 * 
	 * Devuelve null si no encuentra el elemnto en la tabla.
	 * Devuelve null si el elemto pasado es null.
	 * Devuelve el elemento si lo encuentra.
	 * 
	 * @param elemento Elemento a buscar en la tabla
	 * 
	 * @return Elemento de la tabla si lo encuentra o null en caso contrario
	 */
	public T find(T elemento) {
		if(elemento==null) return null;
		int pos = fHash(elemento);
		int intento=1;
		while( intento<getSize()) {
			if(tabla[pos].getStatus()== HashNode.LLENO) {
				if(tabla[pos].getInfo().equals(elemento)) return tabla[pos].getInfo();
				else pos = posicionSiguiente(elemento, intento);
			}else if(tabla[pos].getStatus()==HashNode.BORRADO) {
				//Si se permiten repetidos eliminar esta linea
				if(tabla[pos].getInfo().equals(elemento)) return null;
				pos = posicionSiguiente(elemento, intento);
			}
			intento++;
		}
		return null;
	}

	@Override
	/**
	 * Método que borra el elemento pasado como parámetro de la tabla.
	 * 
	 * Devuelve 0 si lo borra correctamente.
	 * Devuelve -1 si ya ha sido borrado.
	 * Devuelve -2 si el elemento es null o no lo encuentra.
	 * 
	 * @param elemento Elemento a borrar
	 * 
	 * @return 0 si lo devuelve correctamente, el nº correspondiente en otro caso
	 */
	public int remove(T elemento) {
		if(elemento==null) return -2;
		int pos = fHash(elemento);
		int intento=1;
		while(intento<getSize()) {
			if(tabla[pos].getStatus()== HashNode.LLENO) {
				if(tabla[pos].getInfo().equals(elemento)) {
					tabla[pos].remove();
					numElementos--;
					inservseRedispersion();
					return 0;
				}else pos = posicionSiguiente(elemento, intento);
			}else if(tabla[pos].getStatus()==HashNode.BORRADO) {
				//Si se permiten repetidos eliminar esta linea
				if(tabla[pos].getInfo().equals(elemento)) return -1;
				 pos = posicionSiguiente(elemento, intento);
			}
			intento++;
		}
		return -1;
	}

	@Override
	/*
	 * Método que devuelve una cadena con la información de la tabla
	 * 
	 * @return Cadena con la información de la tabla
	 */
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		for(int i=0;i< getSize();i++){
		cadena.append(tabla[i].toString());
		cadena.append(";");
		}
		cadena.append("[Size: ");
		cadena.append(getSize());
		cadena.append(" Num.Elems.: ");
		cadena.append(getNumOfElems());
		cadena.append("]");
		return cadena.toString();
		}
	
	/**
	 * Método que devuelve si el elemento pasado esta repetido 
	 * 
	 * @param elemento elemento que queremos saber si esta repetido
	 * @return verdadero si esta repetido, falso en otro caso
	 */
	public boolean esDuplicado(T elemento) {
		if (elemento == null)
			return false;
		int pos = fHash(elemento);
		int intento = 1;
		boolean encontrado = false;
		while (intento < getSize()) {
			if (tabla[pos].getStatus() == HashNode.LLENO) {
				if (tabla[pos].getInfo().equals(elemento)) {

					if (encontrado == false) encontrado = true;
					else return true;
					}
			}
		    pos = posicionSiguiente(elemento, intento);
			intento++;
		}
		return false;

	}

}
