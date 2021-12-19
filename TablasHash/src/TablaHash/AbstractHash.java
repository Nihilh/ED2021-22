package TablaHash;

public abstract class AbstractHash<T> {

	/*
	 * M�todo que devuelve el n�mero de elementos de la tabla
	 * 
	 * @return N�mero de elementos de la tabla
	 */
	abstract public int getNumOfElems();
	
	/*
	 * M�todo que calcula el nuevo tama�o de la tabla si se alcanza el factor 
	 * de carga m�ximo y recoloca los elementos de la tabla en sus
	 * correspondientes posiciones
	 */
	abstract protected void reDispersion();
	
	/*
	 * M�todo que calcula el nuevo tama�o de la tabla si se alcanza el factor 
	 * de carga minimo y recoloca los elementos de la tabla en sus
	 * correspondientes posiciones
	 */
	abstract protected void inservseRedispersion();

	/*
	 * M�todo que devuelve el tama�o de la tabla
	 * 
	 * @return Tama�o de la tabla
	 */
	abstract public int getSize();

	/*
	 * M�todo que a�ade un nodo con la informaci�n pasada como par�metro en la 
	 * posici�n correspondiente de la tabla y aplica la redispersi�n si es
	 * necesario.
	 * 
	 * Devuelve -2 si el elemento es null.
	 * Devuelve -1 si se agotan las posibilidades de a�adir la informaci�n.
	 * Devuelve 0 si se a�ade correctamente.
	 * 
	 * @param elemento Informaci�n del nodo a a�adir
	 * 
	 * @return 0 si se a�ade correctamente, el n� correspondiente en otro caso
	 */
	abstract public int add(T elemento);

	/*
	 * M�todo que devuelve el elemento que coincide con la informaci�n pasada 
	 * como par�metro.
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
	 * M�todo que borra el elemento pasado como par�metro de la tabla.
	 * 
	 * Devuelve 0 si lo borra correctamente.
	 * Devuelve -1 si ya ha sido borrado.
	 * Devuelve -2 si el elemento -2.
	 * 
	 * @param elemento Elemento a borrar
	 * 
	 * @return 0 si lo devuelve correctamente, el n� correspondiente en otro caso
	 */
	abstract public int remove(T elemento);

	
	/*
	 * M�todo que devuelve una cadena con la informaci�n de la tabla
	 * 
	 * @return Cadena con la informaci�n de la tabla
	 */
	abstract public String toString();

	/**
	 * M�todo que calcula la posicion de un elemento en la tabla Hash
	 * @param elemento elemento del que queremos saber la posici�n
	 * @return posici�n del elemento
	 */
	protected int fHash(T elemento) {
		int pos = elemento.hashCode()%getSize();
		if (pos < 0) return pos+getSize();
		else return pos;
	}

	/**
	 * M�todo que devuelve si un n�mero es primo o no
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
	 * Devuelve el siguiente n�mero primo al pasado
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
	 * Devuelve el primo anterior al pasado como par�metro
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
