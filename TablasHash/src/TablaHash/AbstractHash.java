package TablaHash;

public abstract class AbstractHash<T> {

	/*
	 * Método que devuelve el número de elementos de la tabla
	 * 
	 * @return Número de elementos de la tabla
	 */
	abstract public int getNumOfElems();
	
	/*
	 * Método que calcula el nuevo tamaño de la tabla si se alcanza el factor 
	 * de carga máximo y recoloca los elementos de la tabla en sus
	 * correspondientes posiciones
	 */
	abstract protected void reDispersion();
	
	/*
	 * Método que calcula el nuevo tamaño de la tabla si se alcanza el factor 
	 * de carga minimo y recoloca los elementos de la tabla en sus
	 * correspondientes posiciones
	 */
	abstract protected void inservseRedispersion();

	/*
	 * Método que devuelve el tamaño de la tabla
	 * 
	 * @return Tamaño de la tabla
	 */
	abstract public int getSize();

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
	abstract public int add(T elemento);

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
	abstract public T find(T elemento);

	/**
	 * Método que borra el elemento pasado como parámetro de la tabla.
	 * 
	 * Devuelve 0 si lo borra correctamente.
	 * Devuelve -1 si ya ha sido borrado.
	 * Devuelve -2 si el elemento -2.
	 * 
	 * @param elemento Elemento a borrar
	 * 
	 * @return 0 si lo devuelve correctamente, el nº correspondiente en otro caso
	 */
	abstract public int remove(T elemento);

	
	/*
	 * Método que devuelve una cadena con la información de la tabla
	 * 
	 * @return Cadena con la información de la tabla
	 */
	abstract public String toString();

	/**
	 * Método que calcula la posicion de un elemento en la tabla Hash
	 * @param elemento elemento del que queremos saber la posición
	 * @return posición del elemento
	 */
	protected int fHash(T elemento) {
		int pos = elemento.hashCode()%getSize();
		if (pos < 0) return pos+getSize();
		else return pos;
	}

	/**
	 * Método que devuelve si un número es primo o no
	 * 
	 * @param numero numero que queremos saber si es primo
	 * @return verdadero si es primo falso en otro caso
	 */
	protected boolean isPositivePrime(int numero) {
		if(numero%2==0) return false;
		for(int i=3;i<numero/2;i=i+2) {
			if(numero%i==0) return false;
		}
		return true;
	}

	/**
	 * Devuelve el siguiente número primo al pasado
	 * 
	 * @param numero numero del que queremos saber el siguiente primo
	 * @return siguiente numero primo
	 */
	protected int nextPrimeNumber(int numero) {
		if(numero%2==0) numero=numero+1;
		while(!isPositivePrime(numero)) {
			numero+=2;
		}
		return numero;
	}

	/**
	 * Devuelve el primo anterior al pasado como parámetro
	 * 
	 * @param numero numero del que queremos saber el primo anterior
	 * @return anterior numero primo
	 */
	protected int previousPrimeNumber(int numero) {
		if(numero<=3) return 3;
		if(isPositivePrime(numero)) numero-=2;
		if(numero%2==0) numero=numero-1;
		
		while(!isPositivePrime(numero) ) {
			numero-=2;
		}
		return numero;
	}


}
