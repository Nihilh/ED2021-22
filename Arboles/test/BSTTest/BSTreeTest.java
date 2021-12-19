package BSTTest;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import BST.BSTree;
import ClasesParaTest.Persona;

public class BSTreeTest {

	@Test
	public void recorridosTest() {
		BSTree<Integer> t = new BSTree<>();
		t.addNode(7);
		t.addNode(6);
		t.addNode(9);
		t.addNode(8);
		t.addNode(10);
		assertEquals("7\t6\t9\t8\t10",t.preOrder());
		assertEquals("6\t7\t8\t9\t10",t.inOrder());
		assertEquals("6\t8\t10\t9\t7",t.postOrder());
		
		//Árbol de caracteres
		BSTree<Character> c = new BSTree<>();
		c.addNode('C');
		c.addNode('B');
		c.addNode('A');
		c.addNode('D');
		c.addNode('E');
		assertEquals("C\tB\tA\tD\tE",c.preOrder());
		assertEquals("A\tB\tC\tD\tE",c.inOrder());
		assertEquals("A\tB\tE\tD\tC",c.postOrder());
		
		//Árbol de personas
		BSTree<Persona> p = new BSTree<>();
		p.addNode(new Persona(7,"A"));
		p.addNode(new Persona(6,"B"));
		p.addNode(new Persona(9,"C"));
		p.addNode(new Persona(8,"D"));
		p.addNode(new Persona(10,"E"));
		assertEquals("[A:7]\t[B:6]\t[C:9]\t[D:8]\t[E:10]",p.preOrder());
		assertEquals("[B:6]\t[A:7]\t[D:8]\t[C:9]\t[E:10]",p.inOrder());
		assertEquals("[B:6]\t[D:8]\t[E:10]\t[C:9]\t[A:7]",p.postOrder());
		}
	
	@Test
	public void addTest() {
		BSTree<Integer> t = new BSTree<>();
		t.addNode(7);
		t.addNode(6);
		t.addNode(9);
		t.addNode(8);
		t.addNode(10);
		
		//Nodo que no existe
		assertEquals(0,t.addNode(11));
		
		//Nodo null
		assertEquals(-2,t.addNode(null));
		
		//Nodos que ya existen
		assertEquals(-1,t.addNode(7));
		assertEquals(-1,t.addNode(6));
		assertEquals(-1,t.addNode(9));
		assertEquals(-1,t.addNode(8));
		assertEquals(-1,t.addNode(10));
		
		//Árbol de personas
		BSTree<Persona> p = new BSTree<>();
		p.addNode(new Persona(7,"A"));
		p.addNode(new Persona(6,"B"));
		p.addNode(new Persona(9,"C"));
		p.addNode(new Persona(8,"D"));
		p.addNode(new Persona(10,"E"));
		
		//Nodo que no existe
		assertEquals(0,p.addNode(new Persona(11,"E")));
		
		//Nodo que no existe, pero con el mismo nombre que uno que si. Se añade
		assertEquals(0,p.addNode(new Persona(12,"E")));
		
		//Nodo que no existe, pero con la misma edad que uno que si. No se añade
		//Persona define que dos personas son iguales si tienen la misma edad.
		assertEquals(-1,p.addNode(new Persona(12,"K")));
		
		//Nodo null
		assertEquals(-2,p.addNode(null));
		
		//Nodos que ya existen
		assertEquals(-1,p.addNode(new Persona(7,"A")));
		assertEquals(-1,p.addNode(new Persona(6,"B")));
		assertEquals(-1,p.addNode(new Persona(9,"C")));
		assertEquals(-1,p.addNode(new Persona(8,"D")));
		assertEquals(-1,p.addNode(new Persona(10,"E")));
		
		
		//Árbol de caracteres
		BSTree<Character> c = new BSTree<>();
		c.addNode('A');
		c.addNode('B');
		c.addNode('C');
		c.addNode('D');
		c.addNode('E');
		
		//Nodo que no existe
		assertEquals(0,c.addNode('F'));
		
		//Nodo null
		assertEquals(-2,c.addNode(null));
		
		//Nodos que ya existen
		assertEquals(-1,c.addNode('A'));
		assertEquals(-1,c.addNode('B'));
		assertEquals(-1,c.addNode('C'));
		assertEquals(-1,c.addNode('D'));
		assertEquals(-1,c.addNode('E'));
		}
	
	@Test
	public void removeTest() {
		BSTree<Integer> t = new BSTree<>();
		t.addNode(7);
		t.addNode(6);
		t.addNode(9);
		t.addNode(8);
		t.addNode(10);
		t.addNode(4);
		t.addNode(5);
		
		//Nodo sin hijos
		assertEquals(0,t.removeNode(5));
		assertEquals("4\t6\t7\t8\t9\t10",t.inOrder());
		
		//Nodo con un hijo
		assertEquals(0,t.removeNode(6));
		assertEquals("4\t7\t8\t9\t10",t.inOrder());
		
		//Nodo con dos hijos
		assertEquals(0,t.removeNode(9));
		assertEquals("4\t7\t8\t10",t.inOrder());
		
		//Árbol de personas
		BSTree<Persona> p = new BSTree<>();
		p.addNode(new Persona(7,"A"));
		p.addNode(new Persona(6,"B"));
		p.addNode(new Persona(9,"C"));
		p.addNode(new Persona(8,"D"));
		p.addNode(new Persona(10,"E"));
		p.addNode(new Persona(4,"F"));
		p.addNode(new Persona(5,"G"));
		
		//Nodo sin hijos
		assertEquals(0,p.removeNode(new Persona(5,"G")));
		assertEquals("[F:4]\t[B:6]\t[A:7]\t[D:8]\t[C:9]\t[E:10]",p.inOrder());
		
		//Nodo con un hijo
		assertEquals(0,p.removeNode(new Persona(6,"B")));
		assertEquals("[F:4]\t[A:7]\t[D:8]\t[C:9]\t[E:10]",p.inOrder());
		
		//Nodo con dos hijos
		assertEquals(0,p.removeNode(new Persona(9,"C")));
		assertEquals("[F:4]\t[A:7]\t[D:8]\t[E:10]",p.inOrder());
		
		//Nodo que no existe. Persona con misma edad y distinto nombre
		assertEquals(-1,p.removeNode(new Persona(9,"E")));
		assertEquals("[F:4]\t[A:7]\t[D:8]\t[E:10]",p.inOrder());
		
		//Nodo que no existe. Persona con mismo nombre y distinta edad
		assertEquals(-1,p.removeNode(new Persona(11,"C")));
		assertEquals("[F:4]\t[A:7]\t[D:8]\t[E:10]",p.inOrder());
		
		//Nodo que no existe. Persona con distinto nombre y distinta edad
		assertEquals(-1,p.removeNode(new Persona(11,"H")));
		assertEquals("[F:4]\t[A:7]\t[D:8]\t[E:10]",p.inOrder());
		
		//Árbol de caracteres
		BSTree<Character> c = new BSTree<>();
		c.addNode('C');
		c.addNode('B');
		c.addNode('A');
		c.addNode('D');
		c.addNode('E');
		
		//Nodo raiz
		assertEquals(0,c.removeNode('C'));
		assertEquals("A\tB\tD\tE",c.inOrder());
		
		//Nodo que no existe
		assertEquals(-1,c.removeNode('Z'));
		assertEquals("A\tB\tD\tE",c.inOrder());
		
		//Clave null
		assertEquals(-2,c.removeNode(null));
		assertEquals("A\tB\tD\tE",c.inOrder());
		
		//Árbol vacio
		BSTree<Double> d = new BSTree<>();
		assertEquals(-2,d.removeNode(3.0));
		assertEquals("",d.inOrder());
	}
	
	@Test
	public void highTest() {
		BSTree<Integer> t = new BSTree<>();
		t.addNode(7);
		t.addNode(6);
		t.addNode(9);
		t.addNode(8);
		t.addNode(10);
		assertEquals(3,t.getHeight());
		t.addNode(11);
		assertEquals(4,t.getHeight());
		t.addNode(12);
		assertEquals(5,t.getHeight());
		
		//Árbol de enteros
		BSTree<Integer> c = new BSTree<Integer>();

		// Insertar 15,6,50,4,23,7,71,5,66,8
		c.addNode(15);
		c.addNode(6);
		c.addNode(50);
		c.addNode(4);
		c.addNode(23);
		c.addNode(7);
		c.addNode(71);
		assertEquals(true,c.esLleno());
		c.addNode(5);
		c.addNode(66);
		c.addNode(8);
		
		assertEquals(4,c.getHeight());
		assertEquals(false,c.esDegenerado());
		assertEquals(10, c.numeroNodos());
		
		assertEquals(1,c.nodosNivel(1));
		assertEquals(2,c.nodosNivel(2));
		assertEquals(4,c.nodosNivel(3));
		assertEquals(3,c.nodosNivel(4));
		assertEquals(29,c.LCI());
		assertEquals(2.9,c.LCIm(),0.01);
		assertEquals(4.54545,c.LCEm(),0.01);
		
		
	}
	
	@Test 
	public void esDegeneradoTest() {
		BSTree<Integer> c = new BSTree<Integer>();
		c.addNode(20);
		c.addNode(30);
		assertEquals(true,c.esAlturaMinima());
		assertEquals(true,c.esEquilibrado());
		assertEquals(false,c.esLleno());
		c.addNode(35);
		assertEquals(false,c.esAlturaMinima());
		c.addNode(32);
		c.addNode(34);
		
		assertEquals(true,c.esDegenerado());
		assertEquals(false,c.esAlturaMinima());
		assertEquals(false,c.esEquilibrado());
		
		
	}
	

}
