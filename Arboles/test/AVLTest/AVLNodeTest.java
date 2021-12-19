package AVLTest;


import org.junit.Test;

import AVL.AVLNode;

public class AVLNodeTest {

	@Test
	public void test() {
		AVLNode<Integer> n = new AVLNode<>(10);
		System.out.println(n.toString());
		n.setLeft(new AVLNode<Integer>(2));
		System.out.println(n.getLeft().toString());
		
		System.out.println("Despúes de actualizar");
		n.updateBFHeight();
		System.out.println(n.toString());
		System.out.println(n.getLeft().toString());
		
		System.out.println("Añadido otro a la izq");
		n.setRight(new AVLNode<Integer>(3));
		System.out.println(n.getRight().toString());
		
		System.out.println("Despúes de actualizar");
		n.updateBFHeight();
		System.out.println(n.toString());
		System.out.println(n.getLeft().toString());
		System.out.println(n.getRight().toString());
		
		//---------------------------
		System.out.println("--------------------------------------");
		AVLNode<Integer> m = new AVLNode<>(10);
		System.out.println(m.toString());
		m.setRight(new AVLNode<Integer>(2));
		System.out.println(m.getRight().toString());
		
		System.out.println("Despúes de actualizar");
		m.updateBFHeight();
		System.out.println(m.toString());
		System.out.println(m.getRight().toString());
	}

}
