package ColasPrioridad;

public class  BinaryHeapMax<T extends Comparable<T>>implements PriorityQueue<T>{

	private T[] monticulo; //Vector de elementos de un determinado tama�o
	private int numElementos; //N�mero de elementos insertados en el vector
	
	/**
	 * Constructor del mont�culo de minimos, con el tama�o pasado.
	 * 
	 * @param n n�mero de elementos de la cola.
	 */
	@SuppressWarnings("unchecked")
	public BinaryHeapMax(int n) {
		numElementos = 0;
		monticulo = (T[]) new Comparable[n];
	}

	/**
	 * M�todo que a�ade un elemento a la cola de prioridad.
	 * 
	 * Devuelve -2, si el elemento es null. Devuelve -1, si no hay espacio
	 * suficiente. Devuelve 0, si lo inserta correctamente.
	 * 
	 * @param elemento elemento a a�adir
	 * @return 0 si lo inserta, el correspondiente en otro caso.
	 */
	@Override
	public int add(T elemento) {
		if (elemento == null) return -2;
		if (numElementos == monticulo.length) return -1;

		monticulo[numElementos] = elemento;
		this.filtradoAscendente(numElementos);
		numElementos++;
		return 0;
	}

	/**
	 * M�todo que devuelve el elemento con mayor prioridad.
	 *
	 * Devuelve null si la cola est� llena.
	 *
	 * @return elemento que se saca de la cola.
	 */
	@Override
	public T sacar() {
		if (isEmpty()) return null;
		
		T min = monticulo[0];
		monticulo[0] = monticulo[numElementos - 1];
		this.filtradoDescendente(0);
		numElementos--;
		return min;
	}

	/**
	 * M�todo que elimina el elemento pasado de la cola de prioridad.
	 * 
	 * Devuelve -2, si el elemento es null o la cola est� vac�a. 
	 * Devuelve -1, si el elemento no existe. 
	 * Devuelve 0, si lo borra correctamente.
	 * 
	 * @param elemento elemento a borrar.
	 * @return 0 si lo elimina, el correspondiente en otro caso.
	 */
	@Override
	public int remove(T elemento) {
		if (elemento == null || isEmpty()) return -2;
		int pos = search(elemento);
		if (pos == -1) return -1;
		
		monticulo[pos] = monticulo[numElementos - 1];
		filtradoDescendente(pos);
		numElementos--;
		return 0;
	}

	/**
	 * M�todo que devuelve la posici�n que ocupa un elemento en la cola.
	 * 
	 * Devuelve la posici�n si lo encuentra. 
	 * Devuelve -1, si no lo encuentra.
	 * 
	 * @param elemento elemento del que queremos saber la posici�n.
	 * @return posici�n del elemento, -1 si no existe.
	 */
	private int search(T elemento) {
		for (int i = 0; i < numElementos; i++) {
			if (monticulo[i].compareTo(elemento) == 0) return i;
		}
		return -1;
	}

	/**
	 * M�todo que devuelve verdadero si la cola est� vac�a, falso en otro caso.
	 */
	@Override
	public boolean isEmpty() {
		return numElementos == 0;
	}

	/**
	 * M�todo que vacia la cola.
	 */
	@Override
	public void clear() {
		for (int i = 0; i < numElementos; i++) {
			monticulo[i] = null;
		}
		numElementos=0;
	}

	/**
	 * M�todo que cambia la prioridad de un elemento que se encuentra en 
	 * la posici�n pos por la que se pasa como par�metro.
	 * 
	 * Devuelve -2, si el elemento es null o la posici�n es incorrecta
	 * Devuelve -1, si la cola esta vacia. 
	 * Devuelve 0, si lo cambia correctamente.
	 * 
	 * @param pos      posici�n en la que poner el elemento.
	 * @param elemento elemento del que queremos cambiar la prioridad.
	 * 
	 * @return 0 si lo cambia, el correspondiente en otro caso.
	 */
	@Override
	public int cambiarPrioridad(int pos, T elemento) {
		if (elemento == null || pos < 0 || pos >= monticulo.length) return -2;
		if (isEmpty()) return -1;
		if (monticulo[pos].compareTo(elemento) < 0) {
			monticulo[pos] = elemento;
			filtradoAscendente(pos);
		} else {
			monticulo[pos] = elemento;
			filtradoDescendente(pos);
		}
		return 0;
	}

	/**
	 * M�todo que devuelve una cadena con los elementos de la cola de prioridad
	 * separados por tabuladores. El �ltimo no lleva tabulador.
	 * 
	 * @return cadena con los elementos.
	 */
	@Override
	public String toString() {
		if (isEmpty()) return "";
		String str = "";
		for (int i = 0; i < numElementos; i++) {
			str += monticulo[i].toString() + "\t";
		}
		return str.substring(0, str.length() - 1);
	}

	/**
	 * M�todo que realiza un filtrado ascendente a partir de la posici�n
	 * pasada comopar�metro.
	 * 
	 * @param pos posici�n a partir de la cual filtrar.
	 */
	private void filtradoAscendente(int pos) {
		int insertado = 0;
		while (insertado == 0) {
			if (pos != 0 && monticulo[pos].compareTo(monticulo[(pos-1)/2])>0) {
				T aux = monticulo[pos];
				monticulo[pos] = monticulo[(pos - 1) / 2];
				monticulo[(pos - 1) / 2] = aux;
				pos = (pos - 1) / 2;
			} else {
				insertado = 1;
			}
		}
	}

	/**
	 * M�todo que realiza un filtrado descendente a partir de la posici�n pasada
	 * como par�metro
	 * 
	 * @param pos posici�n a partir de la cual filtrar.
	 */
	private void filtradoDescendente(int pos) {
		int insertado = 0;
		while (insertado == 0) {
			if (2*pos + 1 < numElementos && 2*pos+2< numElementos &&
					monticulo[pos].compareTo(monticulo[2*pos+1]) < 0 
					&&  monticulo[pos].compareTo(monticulo[2*pos+2]) < 0)
				pos = cambiarHijoMayor(pos);
			else if (2 * pos + 1 < numElementos 
					&& monticulo[pos].compareTo(monticulo[2*pos+1]) < 0)
				pos = cambiarHijoIzquierdo(pos);
			else if (2 * pos + 2 < numElementos 
					&&  monticulo[pos].compareTo(monticulo[2*pos+2]) < 0)
				pos = cambiarHijoDerecho(pos);
			else
				insertado = 1;
		}
	}

	/**
	 * M�todo que intercambia el padre por el hijo mayor.
	 * 
	 * @param pos posici�n del padre.
	 * @return posici�n del hijo menor.
	 */
	private int cambiarHijoMayor(int pos) {
		if (monticulo[2*pos + 1].compareTo(monticulo[2*pos + 2]) < 0) {
			pos = cambiarHijoDerecho(pos);
		} else {
			pos = cambiarHijoIzquierdo(pos);
		}
		return pos;
	}

	/**
	 * M�todo que intercambia el padre por el hijo izquierdo.
	 * 
	 * @param pos posici�n del padre.
	 * @return posici�n del hijo izquierdo.
	 */
	private int cambiarHijoIzquierdo(int pos) {
		T aux = monticulo[pos];
		monticulo[pos] = monticulo[2*pos + 1];
		monticulo[2*pos + 1] = aux;
		pos = 2*pos + 1;
		return pos;
	}

	/**
	 * M�todo que intercambia el padre por el hijo derecho.
	 * 
	 * @param pos posici�n del padre.
	 * @return posici�n del hijo derecho.
	 */
	private int cambiarHijoDerecho(int pos) {
		T aux = monticulo[pos];
		monticulo[pos] = monticulo[2*pos + 2];
		monticulo[2*pos + 2] = aux;
		pos = 2*pos + 2;
		return pos;
	}
	
	/*
	 * M�todo que devuelve el minimo del monticulo
	 * 
	 * @return Elemento de valor m�ximo
	 */
	public T minimo() {
		T min=monticulo[numElementos/2];
		for(int i=numElementos/2;i<numElementos;i++) {
			if(monticulo[i].compareTo(min)<0) min= monticulo[i];
		}
		return min;
	}

}
