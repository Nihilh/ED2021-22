package grafos;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Graph<T> {

	private double[][] weights; // Pesos de las aristas
	
	private boolean[][] edges; // Matriz de aristas
	
	private T[] nodes; // Lista de nodos
	
	private int size; // Número de vértices
	
	// Matriz que guarda el coste minimo de ir de cualquier nodo a cada uno 
	//de los restantes
	private double[][] A; 
	
	// Matriz que almacena la secuencia de caminos que forman todos los caminos 
	//de coste minimo
	private T[][] P; 
	
	//Lista con el camino para el método path
	List<T> camino;
	
	//Cadena que guarda la información del recorrido en profundidad
	private String cadenaDFS;
	

	/**
	 * Constructor del grafo el cual recibe la dimensión del grafo
	 * 
	 * @param dimensión dimensión del grafo
	 */
	@SuppressWarnings("unchecked")
	public Graph(int dimension) {
		nodes = (T[]) new Object[dimension];
		this.weights = new double[dimension][dimension];
		this.edges = new boolean[dimension][dimension];
		this.size = 0;
	}
	
	/**
	 * Método que devuleve la matriz de aristas
	 * 
	 * @return matriz de aristas
	 */
	public boolean[][] getEdges() {
		return edges;
	}

	/**
	 * Método que devuleve la matriz de pesos
	 * 
	 * @return matriz de pesos
	 */
	public double[][] getWeights() {
		return weights;
	}

	/**
	 * Método que delvuelve el número de vértices
	 * 
	 * @return número de vértices
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Método que devuelve la posición del nodo pasado como parámetro dentro 
	 * del vector de nodos y -1 si el nodo no existe.
	 * 
	 * @param node nodo
	 * @return posición del nodo, -1 si no existe
	 */
	protected int getNode(T node) {
		for (int i = 0; i < size; i++)
			if (nodes[i].equals(node)) return i;
		return -1;
	}

	/**
	 * Método que indica si existe (true) o no (false) el nodo en el grafo
	 * 
	 * @param node nodo
	 * @return verdadero si existe, falso en otro caso
	 */
	public boolean existsNode(T node) {
		for (int i = 0; i < size; i++)
			if (nodes[i].equals(node)) return true;
		return false;
	}

	/**
	 *  Método que inserta un nuevo nodo que se le pasa como parámetro. 
	 *  Si lo inserta devuelve 0. 
	 *  Si ya existe y además no cabe, no lo inserta y devuelve -3. 
	 *  Si ya existe, pero sí cabría, no lo inserta y devuelve -1.
	 *  Si no existe, pero no cabe, no lo inserta y devuelve -2. 
	 *  Si el nodo a insertar no es válido, devuelve –4 en cualquier caso.
	 * 
	 * @param node nodo a insertar.
	 * @return 0 si se añade correctamente, el nº correspondiente en otro caso.
	 */
	public int addNode(T node) {
		if (node == null) return -4;
		else if (this.getNode(node) == -1 && size >= nodes.length) return -2;
		else if (this.getNode(node) != -1 && size < nodes.length)  return -1;
		else if (this.getNode(node) != -1 && size >= nodes.length) return -3;
		else if (this.getNode(node) == -1 && size < nodes.length) {
			nodes[size] = node;
			for (int i = 0; i <= size; i++) {
				edges[size][i] = false;
				edges[i][size] = false;
				weights[size][i] = 0;
				weights[i][size] = 0;
			}
			size++;
			return 0;
		}
		return -4;
	}

	/**
	 * Método que devuelve true si existe una arista entre los nodos origen y
	 * destino, false en caso contrario o no existen los nodos
	 * 
	 * @param source nodo origen
	 * @param target nodo destino
	 * @return verdadero si existe arista, falso en otro caso.
	 */
	public boolean existEdge(T source, T target) {
		int i = getNode(source);
		int j = getNode(target);
		if (i >= 0 && j >= 0) return (edges[i][j]);
		return false;
	}

	/**
	 * Método que devuelve el peso de la arista que une el nodo origen y destino
	 * Devuelve -1, -2 y -3 si no existe nodos origen,destino o ambos
	 * Devuelve -4 si no existe la arista
	 * 
	 * @param source nodo origen
	 * @param target nodo destino
	 * @return peso de la arista si existe,el nº correspondiente en otro caso.
	 */
	public double getEdge(T source, T target) {
		if (this.getNode(source) == -1 && this.getNode(target) == -1) return -3;
		else if (this.getNode(source) == -1) return -1;
		else if (this.getNode(target) == -1) return -2;
		else if (this.existEdge(source, target)) {
			int i = getNode(source);
			int j = getNode(target);
			return weights[i][j];
		}
		return -4;
	}

	/**
	 * Método que inserta una arista con el peso indicado (> 0) entre dos nodos.
	 * Devuelve 0 si la inserta. 
	 * Devuelve -1, -2 y -3 si no existe nodos origen,destino o ambos
	 * Devuelve -4 si ya existe 
	 * Devuelve -8 si el peso no es válido
	 * 
	 * @param source nodo origen
	 * @param target nodo destino
	 * @param weight peso de la arista
	 * @return 0 si se añade correctamente, el nº correspondiente en otro caso.
	 */
	public int addEdge(T source, T target, double weight) {
		if (weight < 0) return -8; 
		else if (this.getNode(source)== -1&&this.getNode(target)==-1) return -3;
		else if (this.getNode(source) == -1) return -1; 
		else if (this.getNode(target) == -1) return -2;
		else if (!this.existEdge(source, target)) {
			int i = getNode(source);
			int j = getNode(target);

			edges[i][j] = true;
			weights[i][j] = weight;

			return 0;
		}
		return -4;
	}

	/**
	 * Método que borra una arista del grafo que conecta dos nodos. 
	 * Devuelve -1, -2 y -3 si no existe nodos origen, destino o ambos 
	 * Devuelve -4 si no existe la arista. 
     * Devuelve 0 si la borra
	 * 
	 * @param source nodo origen
	 * @param target nodo destino
	 * @return 0 si se borra correctamente, el nº correspondiente en otro caso.
	 */
	public int removeEdge(T source, T target) {
		if (this.getNode(source) == -1 && this.getNode(target) == -1) return -3;
		else if (this.getNode(source) == -1) return -1;
		else if (this.getNode(target) == -1) return -2;
		else if (this.existEdge(source, target)) {
			int i = getNode(source);
			int j = getNode(target);

			edges[i][j] = false;
			weights[i][j] = 0.0;

			return 0;
		}
		return -4;
	}

	/**
	 * Método que devuelve 0 si borra el nodo correctamente
	 * Devuelve -1 en caso contrario
	 * 
	 * @param node nodo a borrar
	 * @return 0 si se borra correctamente, -1 en otro caso.
	 */
	public int removeNode(T node) {
		int i = this.getNode(node);
		if (i >= 0) {
			size--;
			if (i != size + 1) {
				nodes[i] = nodes[size];
				for (int j = 0; j <= size; j++) {
					edges[j][i] = edges[j][size];
					edges[i][j] = edges[size][j];
					weights[i][j] = weights[size][j];
					weights[j][i] = weights[j][size];
				}
				edges[i][i] = edges[size][size];
				weights[i][i] = weights[size][size];
				return 0;
			}
		}
		return -1;
	}

	/**
	 * Método que devuelve una cadena de texto con la información del grafo
	 * 
	 */
	public String toString() {
		DecimalFormat df = new DecimalFormat("#.##");
		String cadena = "";
		cadena += "NODOS\n";
		for (int i = 0; i < size; i++) {
			cadena += nodes[i].toString() + "\t";
		}
		cadena += "\n\nARISTAS\n";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (edges[i][j])
					cadena += "T\t";
				else
					cadena += "F\t";
			}
			cadena += "\n";
		}
		cadena += "\nPESOS\n";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				cadena += (edges[i][j] ? df.format(weights[i][j]) : "-") + "\t";
			}
			cadena += "\n";
		}
		return cadena;
	}
	
	/**
	 * Método que aplica el algoritmo de Dijstra aplicada al nodo pasado y 
	 * devuelve el vector de costes minimos
	 * 
	 * @param origen nodo de origen
	 * @return vector de costes minimos
	 */
	public double[] dijkstra(T origen) {
		if(origen==null || this.existsNode(origen)==false) return null;
		
		double[] costes = inicializaCostes(origen);
		T[] caminos = inicializaCaminos(origen);
		
		boolean[] S =new boolean[size];
		S[this.getNode(origen)]=true;
		
		
		int w = minCost(costes,S);
		
		while(w!=-1) {
		S[w]=true; //Ese nodo ya ha sido visitado
		for(int m =0;m<size;m++) {
			//Si el nodo no ha sido alcanzado compruebo si el coste es menor
			if(!S[m] && this.existEdge(nodes[w], nodes[m]) 
					&&costes[w] + this.getEdge(nodes[w], nodes[m])< costes[m]){
					 costes[m]= costes[w] + this.getEdge(nodes[w], nodes[m]);
					 caminos[m]= nodes[w];
				}
		     }
		w = minCost(costes,S);
		}
		
		return costes;
		
	}

	/**
	 * Método que devuelve el pivote, nodo con el menor coste del vector 
	 * 
	 * @param costes vector de costes
	 * @param s vector de nodos visitados
	 * @return nodo con el menor coste del vector 
	 */
	private int minCost(double[] costes, boolean[] s) {
		double min=Double.POSITIVE_INFINITY;
		int indice=-1;
		for(int i=0; i<size;i++) {
			if(costes[i]<min && !s[i]) {
				min=costes[i];
				indice=i;
			}
		}
		return indice;
	}

	/**
	 * Método que inicializa el vector de caminos, si exite camino desde el 
	 * nodo origen, se añade el vector origen, null si no existe camino directo
	 * 
	 * @param origen nodo origen
	 * @return vector de caminos inicializado
	 */
	private T[] inicializaCaminos(T origen) {
		@SuppressWarnings("unchecked")
		T[] caminos =(T[]) new Object[size];
		for(int i =0;i<size;i++) {
			if(origen.equals(nodes[i])|| this.existEdge(origen, nodes[i])){
				caminos[i]=origen;
			}else {
				caminos[i]=null;
			} 
		}
		return caminos;
		
	}

	/**
	 * Método que inicializa el vector de costes, el coste de ir a un nodo sin
	 * camino directo es infinito, el coste de ir a si mismo es 0, se calculan 
	 * los costes del nodo origen al resto a través de un camino directo
	 * 
	 * @param origen nodo origen
	 * @return vector de costes inicializado
	 */
	private double[] inicializaCostes(T origen) {
		double[] costes =new double[size];
		
		for(int i =0;i<size;i++) {
			if(origen.equals(nodes[i])) 
				costes[i]=0;
			else if(this.existEdge(origen, nodes[i]))
				costes[i]=this.getEdge(origen, nodes[i]);
			else costes[i]=Double.POSITIVE_INFINITY;
		}
		return costes;
	}
	
	/**
	 * Método que aplica el método de floyd a la matriz y devuelve la matriz
	 * con los costes minimos de ir de un nodo a cada uno de los restantes
	 * 
	 * @return matriz con los costes minimos de ir de un nodo a cada uno de 
	 * 		   los restantes
	 */
	public int floyd() {
		if(size<=0) return -1;
		
		inicializarA();
		inicializarP();
		
		for (int k=0;k<size;k++) 
			for (int i=0;i<size;i++) 
				for (int j=0;j<size;j++) 
					if(A[i][k]+ A[k][j] < A[i][j]) {
						A[i][j] = A[i][k]+ A[k][j];
						P[i][j]= nodes[k];
					}
		return 0;
	
	}
	
	/**
	 * Método que inicializa la matriz P de floyd, colocando null en todas sus
	 * posiciones
	 * 
	 * @return matriz P de floyd con valores null
	 */
	private T[][] inicializarP() {
		@SuppressWarnings("unchecked")
		T[][] P = (T[][]) new Object[size][size];
		
		for (int i=0;i<size;i++)
			for (int j=0;j<size;j++)
				 P[i][j]=null;
		
		this.P=P;
		return P;
	}

	/**
	 * Método que inicializa la matriz A , utilzando el peso de la arista
	 * si existe, infinito si no y 0 en el coste de ir de un nodo a si mismo
	 * 
	 * @return Matriz A de floyd inicializada
	 */
	private double[][] inicializarA() {
		double[][] A= new double[size][size];
		for(int i = 0;i<size;i++) {
			for(int j = 0;j<size;j++) {
				A[i][j]= weights[i][j];
				if(A[i][j]==0 && !this.existEdge(nodes[i], nodes[j]))
					// Sustituye los costes 0 que no tienen arista ,por infinito
					A[i][j]= Double.POSITIVE_INFINITY;
			}
			A[i][i]=0; // El coste de ir a si mismo es 0
		}
		this.A=A;
		return A;
	}
	
	/**
	 * Método que devuelve la matriz A de floyd y la calcula si no esta iniciada
	 * 
	 * @return matriz A de floyd
	 */
	protected double[][] getAFloyd(){
		if(A==null) this.floyd();
		return A;
	}
	
	/**
	 * Método que devuelve la matriz P de floyd y la calcula si no esta iniciada
	 * 
	 * @return matriz P de floyd
	 */
	protected T[][] getPFloyd(){
		if(P==null) this.floyd();
		return P;
	}
	
	/**
	 * Método que devuelve el coste mínimo entre el nodo origen y destino

	 * 
	 * @param nodoOrigen nodo origen 
	 * @param nodoDestino nodo de destino
	 * @return coste minimo entre los nodos
	 */
	public double minCostPath(T nodoOrigen,T nodoDestino) {
		if(!existsNode(nodoOrigen) || !existsNode(nodoDestino)) return -1;
		else if(A==null) floyd();
		return A[this.getNode(nodoOrigen)][this.getNode(nodoDestino)];
	}
	
	
	/**
	 * Método que devuelve una cadena con el camino del coste mínimo entre el 
	 * nodo origen y el nodo destino
	 * 
	 * @param nodoOrigen nodo de origen
	 * @param nodoDestino nodo de destino
	 * @return cadena con el camino de coste minimo
	 */
	public String path(T nodoOrigen, T nodoDestino) {
		if(!existsNode(nodoOrigen) || !existsNode(nodoDestino)) return "";
		
		 camino=new ArrayList<>();
         String str="";
         
		if (nodoDestino.equals(nodoOrigen)) return nodoOrigen.toString();
		else if (!(A[this.getNode(nodoOrigen)][this.getNode(nodoDestino)] 
				< Double.POSITIVE_INFINITY)) return nodoOrigen.toString()+
						"\t"+"(Infinity)"+"\t" + nodoDestino.toString();
		else {
			camino.add(nodoOrigen);
			pathInter(nodoOrigen,nodoDestino);
			camino.add(nodoDestino);
			str+=nodoOrigen.toString();
			for(int i =1;i<camino.size();i++) 
				str+="\t"+"("+minCostPath(camino.get(i-1),camino.get(i))
									  +")"+"\t"+camino.get(i).toString();
			return str;
		}
	}

	/**
	 * Método recursivo auxiliar que implementa el Path.
	 * Añade a la lista camino los nodos del camino de coste minimo entre los 
	 * nodos origen y destino.
	 * 
	 * @param nodoOrigen nodo origen del camino de coste minimo
	 * @param nodoDestino nodo destino del camino de coste minimo
	 */
	private void pathInter(T nodoOrigen, T nodoDestino) {
		T k = P[this.getNode(nodoOrigen)][this.getNode(nodoDestino)];
		if (k !=null) {
			pathInter(nodoOrigen,k);
			camino.add(k);
			pathInter(k,nodoDestino);
		}
	}
	
	/**
	 * Método que devuelve una cadena con las matrices A y P de floyd
	 * 
	 * @return cadena con las matrices A y P de floyd
	 */
	public String floydToString() {
		int numNodes=size; 
		String cadena=" "; 
		DecimalFormat df = new DecimalFormat("#.##"); 
		
		 double[][] aFloyd = getAFloyd(); 
	        if (aFloyd != null) { 
	            cadena += "\nAFloyd\n"; 
	            for (int i = 0; i < numNodes; i++) { 
	                for (int j = 0; j < numNodes; j++) { 
	                    cadena += df.format(aFloyd[i][j]) + "\t"; 
	                } 
	                cadena += "\n"; 
	            } 
	        } 
	  
	        T[][] pFloyd = getPFloyd(); 
	        if (pFloyd != null) { 
	                cadena += "\nPFloyd\n"; 
	            for (int i = 0; i < numNodes; i++) { 
	                for (int j = 0; j < numNodes; j++) { 
	                	if (pFloyd[i][j]==null) cadena+="-"+ "\t";
	                	
	                	else cadena += pFloyd[i][j].toString() + "\t"; 
	                } 
	                cadena += "\n"; 
	            } 
	        } 
	        return cadena; 
	}
	
	/**
	 * Método que devuelve una cadd¡ena con el recorrido en profundidad del 
	 * grafo empezando en el nodo pasado como parámetro.
	 * Si no existe devuelve una cadena vacia
	 * 
	 * @param nodo nodo origen del recorrido
	 * @return cadena con el recorrido en profundidad
	 */
	public String recorridoProfundidad(T nodo) {
		if(!this.existsNode(nodo)) return "";
		
		 cadenaDFS="";
		 boolean[] visited = new boolean[size];
		
		 recursivoProfundidad(nodo, visited);
		 
         return cadenaDFS;	
		
	}
	
	/**
	 * Método auxiliar recursivo que implementa el recorrido en profundidad. 
	 * Desde un nodopasado como parámetro, actualiza el vector de nodos 
	 * visitados, si existe camino para el resto de nodos no visitados, 
	 * repite el mismo proceso para el nuevo nodo.
	 * 
	 * @param source nodo de origen
	 * @param visited vector de booleanos con los nodos visitados
	 */
	private void recursivoProfundidad(T source, boolean[] visited) {
		visited[this.getNode(source)]=true;
		cadenaDFS+=source.toString()+"\t";
		
		for(int i =0;i<size;i++) 
			if(!visited[i] && this.existEdge(source, nodes[i]))
			   recursivoProfundidad(nodes[i],visited);
	}

}
