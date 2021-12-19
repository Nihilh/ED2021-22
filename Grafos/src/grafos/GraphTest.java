package grafos;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class GraphTest {
	
	private static final double INF= Double.POSITIVE_INFINITY;

	@Test
	public void getNodetest() {
		Graph<Integer> g = new Graph<Integer>(3);

		// Nodo que exite en la posicion 0. Devuelve 0
		g.addNode(1);
		assertEquals(0, g.getNode(1));

		// Nodo que exite en la posicion 1. Devuelve 1
		g.addNode(2);
		assertEquals(1, g.getNode(2));

		// Nodo que no existe. Devuelve -1
		assertEquals(-1, g.getNode(3));

	}

	@Test
	public void existsNodetest() {
		Graph<Integer> g = new Graph<Integer>(3);

		// Nodo que exite. Devuelve verdadero
		g.addNode(1);
		assertEquals(true, g.existsNode(1));

		// Nodo que no existe. Devuelve falso
		assertEquals(false, g.existsNode(0));

	}

	@Test
	public void addNodetest() {
		Graph<Integer> g = new Graph<Integer>(2);

		// Nodo que no existe y cabe. Se añade
		assertEquals(0, g.addNode(1));
		assertEquals(1, g.getSize());
		double[][] expectedWeights= new double[][] {{0.0,0.0},{0.0,0.0}};
		boolean[][] expectedEdges=new boolean[][] {{false,false},{false,false}};
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

		// Nodo que existe y si cabría. No se añade
		assertEquals(-1, g.addNode(1));
		assertEquals(1, g.getSize());
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

		// Nodo que no existe y no cabe. No se añade
		g.addNode(3);
		assertEquals(2, g.getSize());
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

		assertEquals(-2, g.addNode(2));
		assertEquals(2, g.getSize());
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

		// Nodo que existe y no cabe. No se añade
		assertEquals(-3, g.addNode(3));
		assertEquals(2, g.getSize());
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

		// Nodo no es válido. No se añade.
		assertEquals(-4, g.addNode(null));
		assertEquals(2, g.getSize());
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

	}

	@Test
	public void existEdgeTest() {
		Graph<Integer> g = new Graph<Integer>(2);
		int v1 = 1;
		int v2 = 2;
		g.addNode(v1);
		g.addNode(v2);

		// Nodos que existen y tienen una arista. Devuelve verdadero
		g.addEdge(v1, v2, 10);
		assertEquals(true, g.existEdge(v1, v2));

		// Nodos que existen y no tienen arista. Devuelve falso
		assertEquals(false, g.existEdge(v2, v1));

		// Nodos que no existen y no tienen arista. Devuelve falso
		assertEquals(false, g.existEdge(6, 4));

	}

	@Test
	public void getEdgeTest() {
		Graph<Integer> g = new Graph<Integer>(2);
		int v1 = 1;
		int v2 = 2;
		g.addNode(v1);
		g.addNode(v2);

		// Nodos que existen y tienen una arista con peso. Devuelve el peso
		g.addEdge(v1, v2, 10);
		assertEquals(10, g.getEdge(v1, v2), 0.01);

		// Nodos que existen y no tienen arista. Devuelve -4
		assertEquals(-4, g.getEdge(v2, v1), 0.01);

		// Nodos que existen. Devuelve -3
		assertEquals(-3, g.getEdge(6, 4), 0.01);

		// Nodo de origen que no existe. Devuelve -1
		assertEquals(-1, g.getEdge(4, v1), 0.01);

		// Nodo de destino que no existe. Devuelve -2
		assertEquals(-2, g.getEdge(v1, 6), 0.01);

	}

	@Test
	public void addEdgeTest() {
		Graph<Integer> g = new Graph<Integer>(2);
		int v1 = 1;
		int v2 = 2;
		g.addNode(v1);
		g.addNode(v2);

		// Nodos que existen, sin arista y con peso válido. Se añade
		double[][] expectedWeights=new double[][] {{0.0,0.0},{0.0,0.0}};
		boolean[][] expectedEdges=new boolean[][] {{false,false},{false,false}};
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());
		
		assertEquals(0, g.addEdge(v1, v2, 10));
		
		expectedWeights= new double[][] {{0.0,10},{0.0,0.0}};
		expectedEdges= new boolean[][] {{false,true},{false,false}};
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

		// Nodos que existen y ya tienen arista. No se añade
		assertEquals(-4, g.addEdge(v1, v2, 3));
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

		// Nodos que no existen. No se añade
		assertEquals(-3, g.addEdge(6, 4, 10));
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

		// Nodo de origen que no existe. No se añade
		assertEquals(-1, g.addEdge(6, v1, 10));
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

		// Nodo de destino que no existe. No se añade
		assertEquals(-2, g.addEdge(v1, 4, 10));
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

		// Peso no válido. No se añade
		assertEquals(-8, g.addEdge(v1, v2, -10));
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

	}

	@Test
	public void removeEdgeTest() {
		Graph<Integer> g = new Graph<Integer>(2);
		int v1 = 1;
		int v2 = 2;
		g.addNode(v1);
		g.addNode(v2);

		// Nodos que existen, con arista. Se borra
		g.addEdge(v1, v2, 10);
		double[][] expectedWeights= new double[][] {{0.0,10},{0.0,0.0}};
		boolean[][] expectedEdges= new boolean[][] {{false,true},{false,false}};
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());
		
		assertEquals(0, g.removeEdge(v1, v2));
		
		expectedWeights= new double[][] {{0.0,0.0},{0.0,0.0}};
		expectedEdges= new boolean[][] {{false,false},{false,false}};
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

		// Nodos que existen y no tienen arista. No se borra
		assertEquals(-4, g.removeEdge(v2, v1));
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

		// Nodos que no existen. No se borra
		assertEquals(-3, g.removeEdge(6, 5));
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

		// Nodo de origen que no existe. No se borra
		assertEquals(-1, g.removeEdge(6, v1));
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

		// Nodo de destino que no existe. No se borra
		assertEquals(-2, g.removeEdge(v2, 4));
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

	}

	@Test
	public void removeNodeTest() {
		Graph<Integer> g = new Graph<Integer>(4);
		for(int i =1 ; i<=4;i++) {
			g.addNode(i);
		}
		for(int i =1 ; i<4;i++) {
			g.addEdge(i, i+1, 10+i);
			g.addEdge(i+1, i, 5+i);
		}
		double[][] expectedWeights= 
			new double[][]{{0.0,11,0.0,0.0},{6,0.0,12,0.0},
						   {0.0,7,0.0,13},{0.0,0.0,8,0.0}};
		boolean[][] expectedEdges= 
			new boolean[][]{{false,true,false,false},{true,false,true,false},
			                {false,true,false,true},{false,false,true,false}};
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

		// Nodo que existe en la primera posición
		assertEquals(0, g.removeNode(1));
		assertEquals(3, g.getSize());
		assertEquals(0, g.getNode(4));
		expectedWeights= 
				new double[][]{{0.0,0.0,8,0.0},{0.0,0.0,12,0.0},
							   {13,7,0.0,13},{0.0,0.0,8,0.0}};
		expectedEdges= 
			new boolean[][]{{false,false,true,false},{false,false,true,false},
				            {true,true,false,true},{false,false,true,false}};
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

		// Nodo que existe en la posición 2
		assertEquals(0, g.removeNode(2));
		assertEquals(2, g.getSize());
		assertEquals(1, g.getNode(3));
		expectedWeights= 
				new double[][]{{0.0,8,8,0.0},{13,0,0,0.0},
							   {13,0.0,0.0,13},{0.0,0,8,0.0}};
		expectedEdges= 
			new boolean[][]{{false,true,true,false},{true,false,false,false},
				            {true,false,false,true},{false,false,true,false}};
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

		// Nodo que existe en la última posición
		assertEquals(0, g.removeNode(3));
		assertEquals(1, g.getSize());
		expectedWeights= 
				new double[][]{{0.0,8,8,0.0},{13,0,0,0.0},
							   {13,0.0,0.0,13},{0.0,0,8,0.0}};
		expectedEdges= 
			new boolean[][]{{false,true,true,false},{true,false,false,false},
				            {true,false,false,true},{false,false,true,false}};
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

		// Nodo que no existe
		assertEquals(-1, g.removeNode(8));
		assertEquals(1, g.getSize());
		expectedWeights= 
				new double[][]{{0.0,8,8,0.0},{13,0,0,0.0},
							   {13,0.0,0.0,13},{0.0,0,8,0.0}};
		expectedEdges= 
			new boolean[][]{{false,true,true,false},{true,false,false,false},
				            {true,false,false,true},{false,false,true,false}};
		assertArrayEquals(expectedWeights,g.getWeights());
		assertArrayEquals(expectedEdges,g.getEdges());

	}
	
	@Test
	public void dijkstraTest() {
		//Grafo al que se le aplica dijkstra 
        Graph<Integer> graph = new Graph<Integer>(6);
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		
		graph.addEdge(1, 2, 1);
		graph.addEdge(1, 5, 10);
		graph.addEdge(1, 4, 3);
		graph.addEdge(2, 3, 5);
		graph.addEdge(3, 5, 1);
		graph.addEdge(4, 3, 2);
		graph.addEdge(4, 5, 6); 
		
		
		// Nodo fuente
		Assert.assertArrayEquals(new double[]{0.0,1.0,5.0,3.0,6.0},
				graph.dijkstra(1), 0);
		
		Assert.assertArrayEquals(new double[]{INF,0.0, 5.0,INF, 6.0}, 
				graph.dijkstra(2), 0);
		Assert.assertArrayEquals(new double[]{INF,INF, 0.0, INF, 1.0},
		        graph.dijkstra(3), 0); 
		Assert.assertArrayEquals(new double[]{INF, INF, 2.0, 0.0, 3.0},
				graph.dijkstra(4), 0); 
		Assert.assertArrayEquals(new double[]{INF,INF,INF, INF, 0.0}, 
				graph.dijkstra(5), 0);
		  
		  // Nodo no existe 
		Assert.assertArrayEquals(null, graph.dijkstra(6), 0);
		  
		  // Añadimos otra arista 
		graph.addEdge(5,2,1);
		  
		Assert.assertArrayEquals(new double[]{0.0,1.0,5.0,3.0,6.0},
				graph.dijkstra(1), 0); 
		Assert.assertArrayEquals(new double[]{INF,0.0, 5.0, INF, 6.0},
				graph.dijkstra(2), 0); 
		Assert.assertArrayEquals(new double[]{INF, 2.0, 0.0, INF, 1.0},
				graph.dijkstra(3), 0); 
		Assert.assertArrayEquals(new double[]{INF, 4.0, 2.0, 0.0, 3.0}, 
				graph.dijkstra(4), 0); 
		Assert.assertArrayEquals(new double[]{INF, 1.0, 6.0,INF, 0.0}, 
				graph.dijkstra(5), 0);
		  
		  //Añadimos un nodo aislado 
		graph.addNode(6);
		Assert.assertArrayEquals(new double[]{0.0,1.0,5.0,3.0,6.0, INF}, 
				graph.dijkstra(1), 0);
		Assert.assertArrayEquals(new double[]{INF,0.0, 5.0, INF, 6.0,INF},
				graph.dijkstra(2), 0); 
		
		//Grafo al que se aplica dijkstra partiendo de 1
		Graph<Integer> g = new Graph<Integer>(6);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		g.addNode(5);
		g.addNode(6);
				
		g.addEdge(1, 2, 3);
		g.addEdge(1, 3, 4);
		g.addEdge(1, 5, 8);
		g.addEdge(2, 5, 5);
		g.addEdge(3, 5, 3);
		g.addEdge(5, 4, 7);
		g.addEdge(5, 6, 3);
		g.addEdge(6, 4, 2);

				
		double[] expected= {0,3,4,12,7,10};
		assertArrayEquals(expected,g.dijkstra(1),0.01);
		
		//Grafo al que se aplica dijkstra partiendo de 3
		double[] expected1= {INF,INF,0,8,3,6};
		assertArrayEquals(expected1,g.dijkstra(3),0.01);
		
		//Grafo al que se aplica dijkstra partiendo de 6
		double[] expected6= {INF,INF,INF,2,INF,0};
		assertArrayEquals(expected6,g.dijkstra(6),0.01);
		
		//Grafo al que se aplica dijkstra partiendo de 1
		Graph<Integer> g1 = new Graph<Integer>(5);
		g1.addNode(1);
		g1.addNode(2);
		g1.addNode(3);
		g1.addNode(4);
		g1.addNode(5);
		
		g1.addEdge(1, 2, 1);
		g1.addEdge(1, 4, 3);
		g1.addEdge(1, 5, 10);
		g1.addEdge(2,3, 5);
		g1.addEdge(3, 5, 1);
		g1.addEdge(4, 3, 2);
		g1.addEdge(4, 5, 6);
		
		double[] expected2= {0,1,5,3,6};
		assertArrayEquals(expected2,g1.dijkstra(1),0.01);
		
		//Grafo al que se aplica dijkstra partiendo de un nodo que no existe
		assertArrayEquals(null,g1.dijkstra(6),0.01);
		
		//Grafo al que se aplica dijkstra partiendo de un nodo null
		assertArrayEquals(null,g1.dijkstra(null),0.01);

	}
	
	@Test
	public void floydTest() {
		//Grafo de carcteres
		Graph<Character> g2 = new Graph<Character>(5);
		g2.addNode('A');
		g2.addNode('B');
		g2.addNode('C');
		g2.addNode('D');
		g2.addNode('E');
		g2.addEdge('A','B', 5);
		g2.addEdge('B', 'D', 3);
		g2.addEdge('D', 'A', 6);
		g2.addEdge('A', 'C', 1);
		g2.addEdge('C', 'E', 2);
		g2.addEdge('D', 'E', 4);
			    
		assertEquals(0,g2.floyd());
		
		assertArrayEquals(new double[][] {
				{0,5,1,8,3},
				{9,0,10,3,7},
				{INF,INF,0,INF,2},
				{6,11,7,0,4},
				{INF,INF,INF,INF,0}}
		,g2.getAFloyd() );
		
		assertArrayEquals(new Character[][]{
			{null,null,null,'B','C'},
			{'D',null,'D',null,'D'},
			{null,null,null,null,null},
			{null,'A','A',null,null},
			{null,null,null,null,null}}
		,g2.getPFloyd());
		
		//Grafo de enteros
		Graph<Integer> graph = new Graph<Integer>(6);
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addEdge(1, 2, 1);
		graph.addEdge(1, 4, 3);
		graph.addEdge(1, 5, 10);
		graph.addEdge(2, 3, 5);
		graph.addEdge(3, 5, 1);
		graph.addEdge(4, 3, 2);
		graph.addEdge(4, 5, 6);
		graph.addEdge(4, 4, 6); 
		
		Assert.assertEquals(0, graph.floyd());
		
		Assert.assertArrayEquals(new double[][]{
			{0.0, 1.0, 5.0, 3.0, 6.0 },
			{INF, 0.0, 5, INF, 6.0},
			{INF, INF, 0.0, INF, 1.0},
			{INF,INF, 2, 0.0, 3},
			{INF, INF, INF, INF, 0.0}},
				graph.getAFloyd());
		
		
		//Grafo de enteros
		Graph<Integer> g = new Graph<Integer>(7);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		g.addNode(5);
		g.addNode(6);
		
		g.addEdge(1, 2, 3);
		g.addEdge(1, 3, 4);
		g.addEdge(1, 5, 8);
		g.addEdge(2, 5, 5);
		g.addEdge(3, 5, 3);
		g.addEdge(5, 4, 7);
		g.addEdge(5, 6, 3);
		g.addEdge(6, 4, 2);
		
		assertEquals(0,g.floyd());
		assertArrayEquals(new double[][] {
			{0,3,4,12,7,10},
			{INF,0,INF,10,5,8},
			{INF,INF,0,8,3,6},
			{INF,INF,INF,0,INF,INF},
			{INF,INF,INF,5,0,3},
			{INF,INF,INF,2,INF,0}},
				g.getAFloyd());
		assertArrayEquals(new Integer[][]{
			{null,null,null,6,3,5},
			{null,null,null,6,null,5},
			{null,null,null,6,null,5},
			{null,null,null,null,null,null},
			{null,null,null,6,null,null},
			{null,null,null,null,null,null}}
		,g.getPFloyd());
		
		//Se añade otra arista, haciendo un camino directo entre 1 y 6.
		g.addEdge(1, 6, 3);
		
		assertEquals(0,g.floyd());
		assertArrayEquals(new double[][] {
			{0,3,4,5,7,3},
			{INF,0,INF,10,5,8},
			{INF,INF,0,8,3,6},
			{INF,INF,INF,0,INF,INF},
			{INF,INF,INF,5,0,3},
			{INF,INF,INF,2,INF,0}},
				g.getAFloyd());
		
		assertArrayEquals(new Integer[][]{
			{null,null,null,6,3,null},
			{null,null,null,6,null,5},
			{null,null,null,6,null,5},
			{null,null,null,null,null,null},
			{null,null,null,6,null,null},
			{null,null,null,null,null,null}}
		,g.getPFloyd());
		
		//Se añade otra arista con peso 0
			g.addEdge(1, 4, 0);
				
			assertEquals(0,g.floyd());
			assertArrayEquals(new double[][] {
				{0,3,4,0,7,3},
				{INF,0,INF,10,5,8},
				{INF,INF,0,8,3,6},
    			{INF,INF,INF,0,INF,INF},
				{INF,INF,INF,5,0,3},					
				{INF,INF,INF,2,INF,0}},
					g.getAFloyd());
			
			assertArrayEquals(new Integer[][]{
				{null,null,null,null,3,null},
				{null,null,null,6,null,5},
				{null,null,null,6,null,5},					
				{null,null,null,null,null,null},
				{null,null,null,6,null,null},
				{null,null,null,null,null,null}}
			,g.getPFloyd());
		
		//Se añade un nodo aislado
		g.addNode(7);
		
		assertEquals(0,g.floyd());
		assertArrayEquals(new double[][] {
			{0,3,4,0,7,3,INF},
			{INF,0,INF,10,5,8,INF},
			{INF,INF,0,8,3,6,INF},
			{INF,INF,INF,0,INF,INF,INF},
			{INF,INF,INF,5,0,3,INF},
			{INF,INF,INF,2,INF,0,INF},
			{INF,INF,INF,INF,INF,INF,0}},
				g.getAFloyd());
		
		assertArrayEquals(new Integer[][]{
			{null,null,null,null,3,null,null},
			{null,null,null,6,null,5,null},
			{null,null,null,6,null,5,null},
			{null,null,null,null,null,null,null},
			{null,null,null,6,null,null,null},
			{null,null,null,null,null,null,null},
			{null,null,null,null,null,null,null}}
		,g.getPFloyd());
		
		//Grafo sin nodos
		Graph<Integer> g1 = new Graph<Integer>(7);
		assertEquals(-1,g1.floyd());
		assertArrayEquals(null,g1.getAFloyd());
		assertArrayEquals(null,g1.getPFloyd());
		
	}
	
	@Test
	public void minCostPathTest() {
		// Grafo de 6 nodos de enteros
		Graph<Integer> g = new Graph<Integer>(7);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		g.addNode(5);
		g.addNode(6);
		
		g.addEdge(1, 2, 3);
		g.addEdge(1, 3, 4);
		g.addEdge(1, 5, 8);
		g.addEdge(2, 5, 5);
		g.addEdge(3, 5, 3);
		g.addEdge(5, 4, 7);
		g.addEdge(5, 6, 3);
		g.addEdge(6, 4, 2);
		
		//Camino de coste minimo entre 1 y 6. A null se llama a floyd
		assertEquals(10,g.minCostPath(1, 6),0.01);
		
		//Camino de coste minimo entre 1 y 1. Coste 0
		assertEquals(0,g.minCostPath(1, 1),0.01);
		
		//Camino de coste minimo entre 2 y 3. Coste infinito
		assertEquals(INF,g.minCostPath(2, 3),0.01);
		
		//Camino de coste minimo nodos que no existen. -1
		assertEquals(-1,g.minCostPath(1, 7),0.01);
		
		assertEquals(-1,g.minCostPath(7, 2),0.01);
		
		assertEquals(-1,g.minCostPath(9, 7),0.01);
		
		//Camino de coste minimo grafo sin nodos. -1
		assertEquals(-1,new Graph<Integer>(2).minCostPath(1, 2),0.01);
	}
	
	@Test
	public void pathTest() {
		Graph<Integer> g = new Graph<Integer>(7);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		g.addNode(5);
		g.addNode(6);
		
		g.addEdge(1, 2, 3);
		g.addEdge(1, 3, 4);
		g.addEdge(1, 5, 8);
		g.addEdge(2, 5, 5);
		g.addEdge(3, 5, 3);
		g.addEdge(5, 4, 7);
		g.addEdge(5, 6, 3);
		g.addEdge(6, 4, 2);
		
		assertEquals(0,g.floyd());
		
		// Camino del nodo 1 al 6 con coste minimo 
		assertEquals("1\t(4.0)\t3\t(3.0)\t5\t(3.0)\t6",g.path(1, 6));
		
		// Camino del nodo 1 al 4 con coste minimo 
		assertEquals("1\t(4.0)\t3\t(3.0)\t5\t(3.0)\t6\t(2.0)\t4",g.path(1, 4));
		
		// Camino del nodo 3 al 1 que no existe arista
		assertEquals("3\t(Infinity)\t1",g.path(3, 1));
		
		// Camino del nodo 3 al 3 que no existe arista
		assertEquals("3",g.path(3, 3));
		
		// Camino del nodo 9 al 1 no existe el nodo origen
		assertEquals("",g.path(9, 1));
		
		// Camino del nodo 1 al 9 no existe el nodo destino
		assertEquals("",g.path(1, 9));
		
		// Camino del nodo 8 al 9 no existen los nodos
		assertEquals("",g.path(8, 9));
		
		//Camino del nodo 1 al 5 tomando ambos caminos
		Graph<Integer> g1 = new Graph<Integer>(5);
		g1.addNode(1);
		g1.addNode(2);
		g1.addNode(3);
		g1.addNode(4);
		g1.addNode(5);
		
		g1.addEdge(1, 2, 1);
		g1.addEdge(1, 4, 3);
		g1.addEdge(1, 5, 10);
		g1.addEdge(2, 3, 5);
		g1.addEdge(3, 5, 1);
		g1.addEdge(4, 3, 2);
		g1.addEdge(4, 5, 6);
		
		assertEquals(0,g1.floyd());
		assertEquals("1\t(3.0)\t4\t(2.0)\t3\t(1.0)\t5",g1.path(1, 5));
		
		//Camino del nodo 5 al 3.
        Graph<Integer> graph = new Graph<Integer>(5);
		
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addEdge(1, 2, 1);
		graph.addEdge(2, 3, 2);
		graph.addEdge(3, 4, 2);
		graph.addEdge(3, 5, 4);
		graph.addEdge(4, 2, 1);
	    graph.addEdge(4, 3, 3);
		graph.addEdge(5, 4, 5);
		
		Assert.assertEquals(0, graph.floyd());
        Assert.assertEquals("5\t(5.0)\t4\t(3.0)\t3", graph.path(5, 3));
		
		//Camino del nodo A al E 
		Graph<Character> g11 = new Graph<Character>(5);
		g11.addNode('A');
		g11.addNode('B');
		g11.addNode('C');
		g11.addNode('D');
		g11.addNode('E');
		g11.addEdge('A','B', 1);
		g11.addEdge('A', 'D', 3);
		g11.addEdge('A', 'E', 10);
		g11.addEdge('B', 'C', 5);
		g11.addEdge('C', 'E', 1);
		g11.addEdge('D', 'C', 2);
	    g11.addEdge('D', 'E', 6);
	    
		assertEquals(0,g11.floyd());
		assertEquals("A\t(3.0)\tD\t(2.0)\tC\t(1.0)\tE",g11.path('A', 'E'));
		
		//Camino del nodo B al C 
		Graph<Character> g111 = new Graph<Character>(5);
		g111.addNode('A');
		g111.addNode('B');
		g111.addNode('C');
		g111.addNode('D');
		g111.addNode('E');
		g111.addEdge('A','B', 5);
		g111.addEdge('B', 'D', 3);
		g111.addEdge('D', 'A', 6);
		g111.addEdge('A', 'C', 1);
		g111.addEdge('C', 'E', 2);
		g111.addEdge('D', 'E', 4);
			    
		assertEquals(0,g111.floyd());
		assertEquals("B\t(3.0)\tD\t(6.0)\tA\t(1.0)\tC",g111.path('B', 'C'));
		
	}
	
	@Test
	public void recorridoProfundidadTest() {
		Graph<Integer> g = new Graph<Integer>(5);
		g.addNode(1);
		g.addNode(2);
		g.addNode(3);
		g.addNode(4);
		
		g.addEdge(1, 2, 2);
		g.addEdge(1, 3, 1);
		g.addEdge(2, 4, 3);
		g.addEdge(3, 2, 4);
		g.addEdge(4, 3, 6);
		g.addEdge(4, 4, 5);
		
		//Recorrido en profundidad desde el nodo v1
		assertEquals("1\t2\t4\t3\t",g.recorridoProfundidad(1));
		
		//Recorrido en profundidad desde el nodo v2
		assertEquals("2\t4\t3\t",g.recorridoProfundidad(2));
		
		//Recorrido en profundidad desde el nodo v3
		assertEquals("3\t2\t4\t",g.recorridoProfundidad(3));
		
		//Recorrido en profundidad desde el nodo v4
		assertEquals("4\t3\t2\t",g.recorridoProfundidad(4));
		
		//Recorrido en profundidad desde el nodo v5 que no existe
		assertEquals("",g.recorridoProfundidad(5));
		
		//Recorrido en profundidad desde el nodo v1 grafo de caracteres
		Graph<Character> g1 = new Graph<Character>(5);
		g1.addNode('A');
		g1.addNode('B');
		g1.addNode('C');
		g1.addNode('D');
		
		g1.addEdge('A', 'B', 2);
		g1.addEdge('A', 'C', 1);
		g1.addEdge('B', 'D', 3);
		g1.addEdge('C', 'B', 4);
		g1.addEdge('D', 'C', 6);
		g1.addEdge('D', 'D', 5);
		
		//Recorrido en profundidad desde el nodo v1
		assertEquals("A\tB\tD\tC\t",g1.recorridoProfundidad('A'));
		
		//Recorrido en profundidad desde el nodo v1 grafo de caracteres
		Graph<Character> g11 = new Graph<Character>(8);
		g11.addNode('A');
		g11.addNode('B');
		g11.addNode('C');
		g11.addNode('D');
		g11.addNode('E');
		g11.addNode('F');
		g11.addNode('G');
		g11.addNode('H');
		
				
		g11.addEdge('A', 'B', 1);
		g11.addEdge('A', 'C', 1);
		g11.addEdge('A', 'E', 1);
		g11.addEdge('B', 'F', 1);
		g11.addEdge('C', 'F', 1);
		g11.addEdge('C', 'E', 1);
		g11.addEdge('C', 'G', 1);
		g11.addEdge('F', 'G', 1);
		g11.addEdge('G', 'H', 1);
		
		assertEquals("A\tB\tF\tG\tH\tC\tE\t",g11.recorridoProfundidad('A'));
	}

}
