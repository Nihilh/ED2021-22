package ColasPrioridad;

public class  BinaryHeapMax<T extends Comparable<T>>implements PriorityQueue<T>{

	private T[] monticulo; //Vector de elementos de un determinado tamaño
	private int numElementos; //Número de elementos insertados en el vector
	
	/**
	 * Constructor del montículo de minimos, con el tamaño pasado.
	 * 
	 * @param n número de elementos de la cola.
	 */
	@SuppressWarnings("unchecked")
	public BinaryHeapMax(int n) {
		numElementos = 0;
		monticulo = (T[]) new Comparable[n];
	}

	/**
	 * Método que añade un elemento a la cola de prioridad.
	 * 
	 * Devuelve -2, si el elemento es null. Devuelve -1, si no hay espacio
	 * suficiente. Devuelve 0, si lo inserta correctamente.
	 * 
	 * @param elemento elemento a añadir
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
	 * Método que devuelve el elemento con mayor prioridad.
	 *
	 * Devuelve null si la cola está llena.
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
	 * Método que elimina el elemento pasado de la cola de prioridad.
	 * 
	 * Devuelve -2, si el elemento es null o la cola está vacía. 
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
	 * Método que devuelve la posición que ocupa un elemento en la cola.
	 * 
	 * Devuelve la posición si lo encuentra. 
	 * Devuelve -1, si no lo encuentra.
	 * 
	 * @param elemento elemento del que queremos saber la posición.
	 * @return posición del elemento, -1 si no existe.
	 */
	private int search(T elemento) {
		for (int i = 0; i < numElementos; i++) {
			if (monticulo[i].compareTo(elemento) == 0) return i;
		}
		return -1;
	}

	/**
	 * Método que devuelve verdadero si la cola está vacía, falso en otro caso.
	 */
	@Override
	public boolean isEmpty() {
		return numElementos == 0;
	}

	/**
	 * Método que vacia la cola.
	 */
	@Override
	public void clear() {
		for (int i = 0; i < numElementos; i++) {
			monticulo[i] = null;
		}
		numElementos=0;
	}

	/**
	 * Método que cambia la prioridad de un elemento que se encuentra en 
	 * la posición pos por la que se pasa como parámetro.
	 * 
	 * Devuelve -2, si el elemento es null o la posición es incorrecta
	 * Devuelve -1, si la cola esta vacia. 
	 * Devuelve 0, si lo cambia correctamente.
	 * 
	 * @param pos      posición en la que poner el elemento.
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
	 * Método que devuelve una cadena con los elementos de la cola de prioridad
	 * separados por tabuladores. El último no lleva tabulador.
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
	 * Método que realiza un filtrado ascendente a partir de la posición
	 * pasada comoparámetro.
	 * 
	 * @param pos posición a partir de la cual filtrar.
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
	 * Método que realiza un filtrado descendente a partir de la posición pasada
	 * como parámetro
	 * 
	 * @param pos posición a partir de la cual filtrar.
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
	 * Método que intercambia el padre por el hijo mayor.
	 * 
	 * @param pos posición del padre.
	 * @return posición del hijo menor.
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
	 * Método que intercambia el padre por el hijo izquierdo.
	 * 
	 * @param pos posición del padre.
	 * @return posición del hijo izquierdo.
	 */
	private int cambiarHijoIzquierdo(int pos) {
		T aux = monticulo[pos];
		monticulo[pos] = monticulo[2*pos + 1];
		monticulo[2*pos + 1] = aux;
		pos = 2*pos + 1;
		return pos;
	}

	/**
	 * Método que intercambia el padre por el hijo derecho.
	 * 
	 * @param pos posición del padre.
	 * @return posición del hijo derecho.
	 */
	private int cambiarHijoDerecho(int pos) {
		T aux = monticulo[pos];
		monticulo[pos] = monticulo[2*pos + 2];
		monticulo[2*pos + 2] = aux;
		pos = 2*pos + 2;
		return pos;
	}
	
	/*
	 * Método que devuelve el minimo del monticulo
	 * 
	 * @return Elemento de valor máximo
	 */
	public T minimo() {
		T min=monticulo[numElementos/2];
		for(int i=numElementos/2;i<numElementos;i++) {
			if(monticulo[i].compareTo(min)<0) min= monticulo[i];
		}
		return min;
	}

}
