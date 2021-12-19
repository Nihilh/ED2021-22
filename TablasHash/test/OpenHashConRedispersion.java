

import org.junit.Test;

import TablaHash.OpenHash;

public class OpenHashConRedispersion {

	@Test
	public void test() {
		OpenHash<Integer> tabla = new OpenHash<Integer>(4);
		tabla.add(5);
		System.out.println(tabla.toString());
		tabla.add(10);
		System.out.println(tabla.toString());
		tabla.add(1);
		System.out.println(tabla.toString());
		tabla.add(11);
		System.out.println(tabla.toString());
		tabla.add(3);
		System.out.println(tabla.toString());
		tabla.add(4);
		System.out.println(tabla.toString());
		
		//Borrado redispersion inversa
		tabla.remove(4);
		System.out.println(tabla.toString());
		
		//Borrado redispersion inversa
		tabla.remove(3);
		System.out.println(tabla.toString());
		
		//Borrado redispersion inversa
		tabla.remove(1);
		System.out.println(tabla.toString());
	}

}
