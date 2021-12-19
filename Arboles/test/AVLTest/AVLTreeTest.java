package AVLTest;

import static org.junit.Assert.*;

import org.junit.Test;
import AVL.AVLTree;

public class AVLTreeTest {

	@Test
	public void addTest() {
		
		//Rotacion simple derecha
		AVLTree<Integer> t = new AVLTree<>();
		t.addNode(50);
		t.addNode(25);
		t.addNode(75);
		t.addNode(90);
		t.addNode(10);
		t.addNode(30);
		assertEquals(
				"10:BF=0\t25:BF=0\t30:BF=0\t50:BF=0\t75:BF=1\t90:BF=0",
				t.inOrder());
		
		t.addNode(92);
		assertEquals(
			"10:BF=0\t25:BF=0\t30:BF=0\t50:BF=0\t75:BF=0\t90:BF=0\t92:BF=0",
			t.inOrder());
		
		//Rotacion simple izquierda
		AVLTree<Integer> p = new AVLTree<>();
		p.addNode(50);
		p.addNode(25);
		p.addNode(75);
		p.addNode(10);
		assertEquals(
				"10:BF=0\t25:BF=-1\t50:BF=-1\t75:BF=0",p.inOrder());
				
		p.addNode(5);
		assertEquals(
				"5:BF=0\t10:BF=0\t25:BF=0\t50:BF=-1\t75:BF=0",p.inOrder());
		
		//Rotacion doble derecha
		AVLTree<Integer> r = new AVLTree<>();
		r.addNode(50);
		r.addNode(25);
		r.addNode(75);
		r.addNode(30);
		assertEquals(
				"25:BF=1\t30:BF=0\t50:BF=-1\t75:BF=0",r.inOrder());
				
		r.addNode(26);
		assertEquals(
				"25:BF=0\t26:BF=0\t30:BF=0\t50:BF=-1\t75:BF=0",r.inOrder());
		
		//Rotacion doble izquierda
		AVLTree<Integer> d = new AVLTree<>();
		d.addNode(50);
		d.addNode(25);
		d.addNode(75);
		d.addNode(60);
		assertEquals(
				"25:BF=0\t50:BF=1\t60:BF=0\t75:BF=-1",d.inOrder());
				
		d.addNode(65);
		assertEquals(
				"25:BF=0\t50:BF=1\t60:BF=0\t65:BF=0\t75:BF=0",d.inOrder());
	}
	
	@Test
	public void padreTest() {
		AVLTree<Integer> b = new AVLTree<Integer>();
		// Borra una clave null
		assertEquals(-2, b.removeNode(null));
		
		// Borra en un árbol vacío
		assertEquals(-2, b.removeNode(12));
		
		// Insertar 5, 18 10, 40, 50, 15, 16, 12, 14, 17
		b.addNode(5);
		b.addNode(18);
		b.addNode(10);
		b.addNode(40);
		b.addNode(50);
		b.addNode(15);
		b.addNode(16);
		b.addNode(12);
		b.addNode(14);
		b.addNode(17);
		assertEquals(0, b.removeNode(50));
		assertEquals(0, b.removeNode(10));
		assertEquals(0, b.removeNode(15));
		
		assertTrue(b.padreDe(14)==null);
		assertTrue(b.padreDe(18).getInfo().compareTo(14)==0);
		assertTrue(b.padreDe(12).getInfo().compareTo(14)==0);
		assertTrue(b.padreDe(5).getInfo().compareTo(12)==0);
		assertTrue(b.padreDe(16).getInfo().compareTo(18)==0);
		assertTrue(b.padreDe(40).getInfo().compareTo(18)==0);
		assertTrue(b.padreDe(17).getInfo().compareTo(16)==0);
		
		//Árbol de enteros
		AVLTree<Integer> c = new AVLTree<Integer>();
		
		//Insertar 15,6,504,23,7,71,5,66,8
		c.addNode(15);
		c.addNode(6);
		c.addNode(50);
		c.addNode(4);
		c.addNode(23);
		c.addNode(7);
		c.addNode(71);
		c.addNode(5);
		c.addNode(66);
		c.addNode(8);
		
		assertTrue(c.padreDe(6).getInfo().compareTo(15)==0);
		assertTrue(c.padreDe(50).getInfo().compareTo(15)==0);
		assertTrue(c.padreDe(7).getInfo().compareTo(6)==0);
		assertTrue(c.padreDe(4).getInfo().compareTo(6)==0);
		assertTrue(c.padreDe(5).getInfo().compareTo(4)==0);
		assertTrue(c.padreDe(8).getInfo().compareTo(7)==0);
		assertTrue(c.padreDe(23).getInfo().compareTo(50)==0);
		assertTrue(c.padreDe(71).getInfo().compareTo(50)==0);
		assertTrue(c.padreDe(66).getInfo().compareTo(71)==0);
		
	}
	
	@Test
	public void numAristasTest() {
		//Árbol de enteros
		AVLTree<Integer> c = new AVLTree<Integer>();

		// Insertar 15,6,504,23,7,71,5,66,8
		c.addNode(15);
		c.addNode(6);
		c.addNode(50);
		c.addNode(4);
		c.addNode(23);
		c.addNode(7);
		c.addNode(71);
		c.addNode(5);
		c.addNode(66);
		c.addNode(8);
		
		assertEquals(3,c.numAristas(15, 5));
		assertEquals(3,c.numAristas(15, 66));
		assertEquals(3,c.numAristas(15, 8));
		assertEquals(2,c.numAristas(15, 4));
		assertEquals(2,c.numAristas(15, 7));
		assertEquals(2,c.numAristas(15, 23));
		assertEquals(2,c.numAristas(15, 71));
		assertEquals(1,c.numAristas(15, 6));
		assertEquals(1,c.numAristas(15, 50));
		
		System.out.println(c.caminoNodos(15, 66));
		System.out.println(c.caminoNodos(15, 8));
		System.out.println(c.caminoNodos(6, 8));
		
		c.addNode(200);
		System.out.println(c.maximoNodo());
		System.out.println(c.minimoNodo());
		
		c.addNode(0);
		System.out.println(c.minimoNodo());
	}
	
	

}
