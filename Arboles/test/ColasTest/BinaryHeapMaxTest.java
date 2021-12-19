package ColasTest;

import static org.junit.Assert.*;

import org.junit.Test;

import ColasPrioridad.BinaryHeapMax;

public class BinaryHeapMaxTest {

	@Test
	public void addTest() {
		BinaryHeapMax<Integer> m = new BinaryHeapMax<>(6);
		
		//Añadimos un nodo a una cola vacía
		assertEquals(0, m.add(2));
		assertEquals("2",m.toString());
		
		//Añadimos un hijo mayor que el padre, filtrado ascendente
		assertEquals(0, m.add(3));
		assertEquals("3\t2",m.toString());
		
		//Añadimos otro hijo mayor que el padre, filtrado ascendente
		assertEquals(0, m.add(5));
		assertEquals("5\t2\t3",m.toString());
		
		//Añadimos un hijo mayor que el padre, filtrado ascendente
		assertEquals(0, m.add(7));
		assertEquals("7\t5\t3\t2",m.toString());
		
		//Añadimos un hijo menor que el padre 
		assertEquals(0, m.add(1));
		assertEquals("7\t5\t3\t2\t1",m.toString());
		
		//Añadimos un hijo menor que el padre 
		assertEquals(0, m.add(0));
		assertEquals("7\t5\t3\t2\t1\t0",m.toString());
		
		//Añadimos un nodo null
		assertEquals(-2,m.add(null));
		
		//Intentamos añadir un nodo en una cola llena
		assertEquals(-1, m.add(9));
	}
	
	@Test
	public void removeTest() {
		
		BinaryHeapMax<Integer> m = new BinaryHeapMax<>(10);
		
		m.add(2);
		m.add(3);
		m.add(5);
		m.add(7);
		m.add(1);
		m.add(0);
		assertEquals("7\t5\t3\t2\t1\t0",m.toString());
		
		//Borramos la raiz
		assertEquals(0,m.remove(7));
		assertEquals("5\t2\t3\t0\t1",m.toString());
		
		//Borramos la raiz
		assertEquals(0,m.remove(5));
		assertEquals("3\t2\t1\t0",m.toString());
		
		//Borramos hoja
		assertEquals(0,m.remove(0));
		assertEquals("3\t2\t1",m.toString());
		
		//Intentamos borrar un nodo que no existe
		assertEquals(-1,m.remove(9));
		
		//Borramos hoja
		assertEquals(0,m.remove(1));
		assertEquals("3\t2",m.toString());
		
		//Borramos hoja
		assertEquals(0,m.remove(2));
		assertEquals("3",m.toString());
		
		//Borramos la raiz y se queda vacía
		assertEquals(0,m.remove(3));
		assertEquals("",m.toString());
		assertEquals(true,m.isEmpty());
		
		//Intentamos borrar un nodo de una cola vacía
		assertEquals(-2,m.remove(2));
		
		//Intentamos borrar un nodo null
		assertEquals(-2,m.remove(null));
	}

}
