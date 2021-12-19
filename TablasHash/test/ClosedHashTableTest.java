import static org.junit.Assert.*;

import org.junit.Test;

import TablaHash.ClosedHashTable;

public class ClosedHashTableTest {

	@Test
	public void addTestLineal() {
		//Crea una tabla del tamaño 6 (numero no primo)
		ClosedHashTable<Integer> tabla = 
				new ClosedHashTable<Integer>(6,1,0.2,0.5);
		//Muestra la tabla. Debera estar vacia y ser de tamaño 7
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "[Size: 7 Num.Elems.: 0]",tabla.toString());

		//Insertamos null
		assertEquals(-2,tabla.add(null));
		
		//Insertamos nodo posicion 0
		assertEquals(0,tabla.add(0));
		assertEquals("{0};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "[Size: 7 Num.Elems.: 1]",tabla.toString());
	
		//Intentamos insertar otro nodo en la posicion 0
		assertEquals(0,tabla.add(7));
		assertEquals("{0};{7};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "[Size: 7 Num.Elems.: 2]",tabla.toString());
		
		//Intentamos insertar otro nodo en la posicion 0
		assertEquals(0,tabla.add(14));
		assertEquals("{0};{7};{14};{_E_};{_E_};{_E_};{_E_};"
				+ "[Size: 7 Num.Elems.: 3]",tabla.toString());
		
		//Insertamos nodo en la posicion 6, redispersion
		assertEquals(0,tabla.add(6));
		assertEquals("{0};{_E_};{_E_};{_E_};{_E_};{_E_};{6};{7};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{14};{_E_};{_E_};"
				+ "[Size: 17 Num.Elems.: 4]",tabla.toString());
		
		//Seguimos insertando
		assertEquals(0,tabla.add(1));
		assertEquals(0,tabla.add(2));
		assertEquals(0,tabla.add(3));
		assertEquals(0,tabla.add(4));
		assertEquals("{0};{1};{2};{3};{4};{_E_};{6};{7};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{14};{_E_};{_E_};"
				+ "[Size: 17 Num.Elems.: 8]",tabla.toString());
		
		//Insertamos nodo en la posicion 6, redispersion
		assertEquals(0,tabla.add(23));
		assertEquals("{0};{1};{2};{3};{4};{_E_};{6};{7};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{14};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{23};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "[Size: 37 Num.Elems.: 9]",tabla.toString());
		
		//Insertamos nodo en la posicion 0
		assertEquals(0,tabla.add(74));
		assertEquals("{0};{1};{2};{3};{4};{74};{6};{7};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{14};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{23};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "[Size: 37 Num.Elems.: 10]",tabla.toString());
		
		//------------------------Caracteres-----------------------
		//Crea una tabla del tamaño 2 (numero no primo)
		ClosedHashTable<Character> tablac = 
				new ClosedHashTable<Character>(2,1,0.2,0.5);
		assertEquals("{_E_};{_E_};{_E_};"
				+ "[Size: 3 Num.Elems.: 0]",tablac.toString());
		
		//Insertamos caracter posición 0
		assertEquals(0,tablac.add('B'));
		assertEquals("{B};{_E_};{_E_};"
				+ "[Size: 3 Num.Elems.: 1]",tablac.toString());
		
		//Insertamos otro caracter posición 0, redispersion
		assertEquals(0,tablac.add('c'));
		assertEquals("{_E_};{c};{_E_};{B};{_E_};{_E_};{_E_};"
				+ "[Size: 7 Num.Elems.: 2]",tablac.toString());
		
		//Insertamos  caracter posición 4
		assertEquals(0,tablac.add('C'));
		assertEquals("{_E_};{c};{_E_};{B};{C};{_E_};{_E_};"
				+ "[Size: 7 Num.Elems.: 3]",tablac.toString());
		
		//Insertamos caracter posición 5
		assertEquals(0,tablac.add('K'));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{K};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{c};{B};{C};"
				+ "[Size: 17 Num.Elems.: 4]",tablac.toString());
		
		//Insertamos caracter posición 5
		assertEquals(0,tablac.add('K'));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{K};{K};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{c};{B};{C};"
				+ "[Size: 17 Num.Elems.: 5]",tablac.toString());
		
	}
	
	@Test
	public void addTestCuadratico() {
		//Crea una tabla del tamaño 6 (numero no primo)
		ClosedHashTable<Integer> tabla = 
				new ClosedHashTable<Integer>(6,2,0.2,0.5);
		//Muestra la tabla. Debera estar vacia y ser de tamaño 7
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "[Size: 7 Num.Elems.: 0]",tabla.toString());

		//Insertamos null
		assertEquals(-2,tabla.add(null));
		
		//Insertamos nodo posicion 0
		assertEquals(0,tabla.add(0));
		assertEquals("{0};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "[Size: 7 Num.Elems.: 1]",tabla.toString());
	
		//Intentamos insertar otro nodo en la posicion 0
		assertEquals(0,tabla.add(7));
		assertEquals("{0};{7};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "[Size: 7 Num.Elems.: 2]",tabla.toString());
		
		//Intentamos insertar otro nodo en la posicion 0
		assertEquals(0,tabla.add(14));
		assertEquals("{0};{7};{_E_};{_E_};{14};{_E_};{_E_};"
				+ "[Size: 7 Num.Elems.: 3]",tabla.toString());
		
		//Insertamos nodo en la posicion 6, redispersion
		assertEquals(0,tabla.add(6));
		assertEquals("{0};{_E_};{_E_};{_E_};{_E_};{_E_};{6};{7};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{14};{_E_};{_E_};"
				+ "[Size: 17 Num.Elems.: 4]",tabla.toString());
		
		//Seguimos insertando
		assertEquals(0,tabla.add(1));
		assertEquals(0,tabla.add(2));
		assertEquals(0,tabla.add(3));
		assertEquals(0,tabla.add(4));
		assertEquals("{0};{1};{2};{3};{4};{_E_};{6};{7};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{14};{_E_};{_E_};"
				+ "[Size: 17 Num.Elems.: 8]",tabla.toString());
		
		//Insertamos nodo en la posicion 6, redispersion
		assertEquals(0,tabla.add(23));
		assertEquals("{0};{1};{2};{3};{4};{_E_};{6};{7};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{14};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{23};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "[Size: 37 Num.Elems.: 9]",tabla.toString());
		
		//Insertamos nodo en la posicion 0
		assertEquals(0,tabla.add(74));
		assertEquals("{0};{1};{2};{3};{4};{_E_};{6};{7};{_E_};{74};"
				+ "{_E_};{_E_};{_E_};{_E_};{14};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{23};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "[Size: 37 Num.Elems.: 10]",tabla.toString());	
		
		//------------------------Caracteres-----------------------
		//Crea una tabla del tamaño 2 (numero no primo)
		ClosedHashTable<Character> tablac = 
				new ClosedHashTable<Character>(2,2,0.2,0.5);
		assertEquals("{_E_};{_E_};{_E_};"
				+ "[Size: 3 Num.Elems.: 0]",tablac.toString());
		
		//Insertamos caracter posición 0
		assertEquals(0,tablac.add('B'));
		assertEquals("{B};{_E_};{_E_};"
				+ "[Size: 3 Num.Elems.: 1]",tablac.toString());
		
		//Insertamos otro caracter posición 0, redispersion
		assertEquals(0,tablac.add('c'));
		assertEquals("{_E_};{c};{_E_};{B};{_E_};{_E_};{_E_};"
				+ "[Size: 7 Num.Elems.: 2]",tablac.toString());
		
		//Insertamos  caracter posición 4
		assertEquals(0,tablac.add('C'));
		assertEquals("{_E_};{c};{_E_};{B};{C};{_E_};{_E_};"
				+ "[Size: 7 Num.Elems.: 3]",tablac.toString());
		
		//Insertamos caracter posición 5
		assertEquals(0,tablac.add('K'));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{K};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{c};{B};{C};"
				+ "[Size: 17 Num.Elems.: 4]",tablac.toString());
		
		//Insertamos caracter posición 14
		assertEquals(0,tablac.add('t'));
		assertEquals("{_E_};{t};{_E_};{_E_};{_E_};{_E_};{_E_};{K};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{c};{B};{C};"
				+ "[Size: 17 Num.Elems.: 5]",tablac.toString());
		
	}
	
	@Test
	public void addDispersionDoble() {
		//Crea una tabla del tamaño 12 (numero no primo)
		ClosedHashTable<Integer> tabla = 
				new ClosedHashTable<Integer>(12,3,0.2,0.5);
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};"
				+ "[Size: 13 Num.Elems.: 0]",tabla.toString());
		
		//Se inserta elemento con hash 5
		assertEquals(0,tabla.add(5));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{_E_};{_E_};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};"
				+ "[Size: 13 Num.Elems.: 1]",tabla.toString());
		
		//Se inserta elemento con hash 8
		assertEquals(0,tabla.add(8));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{_E_};{_E_};{8};{_E_};"
				+ "{_E_};{_E_};{_E_};"
				+ "[Size: 13 Num.Elems.: 2]",tabla.toString());
		
		//Se inserta elemento con hash 10
		assertEquals(0,tabla.add(10));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{_E_};{_E_};{8};{_E_};"
				+ "{10};{_E_};{_E_};"
				+ "[Size: 13 Num.Elems.: 3]",tabla.toString());
		
		//Se inserta elemento con hash 8, se produce colisión
		assertEquals(0,tabla.add(21));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{_E_};{5};{_E_};{_E_};{8};{_E_};"
				+ "{10};{21};{_E_};"
				+ "[Size: 13 Num.Elems.: 4]",tabla.toString());
		
		//Se inserta elemento con hash 5, se produce colisión
		assertEquals(0,tabla.add(18));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{18};{5};{_E_};{_E_};{8};{_E_};"
				+ "{10};{21};{_E_};"
				+ "[Size: 13 Num.Elems.: 5]",tabla.toString());
		
		//Se inserta elemento con hash 11, se produce colisión
		assertEquals(0,tabla.add(11));
		assertEquals("{_E_};{_E_};{_E_};{_E_};{18};{5};{_E_};{_E_};{8};{11};"
				+ "{10};{21};{_E_};"
				+ "[Size: 13 Num.Elems.: 6]",tabla.toString());
	}
	
	@Test
	public void borrarTest() {
		//Crea una tabla del tamaño 6 (numero no primo)
		ClosedHashTable<Integer> tabla = 
				new ClosedHashTable<Integer>(6,2,0.2,0.5);
		
		//Insertamos nodos
		assertEquals(0,tabla.add(0));
		assertEquals(0,tabla.add(7));
		assertEquals(0,tabla.add(14));
		assertEquals(0,tabla.add(6));
		assertEquals(0,tabla.add(1));
		assertEquals(0,tabla.add(2));
		assertEquals(0,tabla.add(3));
		assertEquals(0,tabla.add(4));
		assertEquals(0,tabla.add(23));
		assertEquals("{0};{1};{2};{3};{4};{_E_};{6};{7};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{14};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{23};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "[Size: 37 Num.Elems.: 9]",tabla.toString());
		
		//Intentamos borrar un elemento null
		assertEquals(-2,tabla.remove(null));
		
		//Intentamos borrar un elemento que no existe
		assertEquals(-1,tabla.remove(99));
		
		//Borramos el 23
		assertEquals(0,tabla.remove(23));
		assertEquals("{0};{1};{2};{3};{4};{_E_};{6};{7};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{14};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_D_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "[Size: 37 Num.Elems.: 8]",tabla.toString());
		
		//Intentamos borrar el 23 que ya se ha borrado
		assertEquals(-1,tabla.remove(23));
		
		//Borramos el 14, redispersion inversa
		assertEquals(0,tabla.remove(14));
		assertEquals("{0};{1};{2};{3};{4};{_E_};{6};{7};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "[Size: 17 Num.Elems.: 7]",tabla.toString());
		
		//Borramos el 7
		assertEquals(0,tabla.remove(7));
		assertEquals("{0};{1};{2};{3};{4};{_E_};{6};{_D_};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};{_E_};"
				+ "[Size: 17 Num.Elems.: 6]",tabla.toString());
		
		//Borramos el 1,2 y 3, redispersion inversa
		assertEquals(0,tabla.remove(1));
		assertEquals(0,tabla.remove(2));
		assertEquals(0,tabla.remove(3));
		assertEquals("{0};{_E_};{_E_};{_E_};{4};{_E_};{6};"
				+ "[Size: 7 Num.Elems.: 3]",tabla.toString());
		
		//Borramos el 4 y 6
		assertEquals(0,tabla.remove(4));
		assertEquals(0,tabla.add(14));
		assertEquals(0,tabla.remove(6));
		assertEquals("{0};{14};{_E_};{_E_};{_D_};{_E_};{_D_};"
				+ "[Size: 7 Num.Elems.: 2]",tabla.toString());
		
		//Añadimos 2 elementos en la posicion 0 y los intentamos borrar
		assertEquals(0,tabla.add(21));
		assertEquals("{0};{14};{_E_};{_E_};{21};{_E_};{_D_};"
				+ "[Size: 7 Num.Elems.: 3]",tabla.toString());
		
		assertEquals(0,tabla.remove(21));
		assertEquals("{0};{14};{_E_};{_E_};{_D_};{_E_};{_D_};"
				+ "[Size: 7 Num.Elems.: 2]",tabla.toString());
		
		//Borramos el 14 , tamaño minimo de la tabla
		assertEquals(0,tabla.remove(14));
		assertEquals("{0};{_E_};{_E_};"
				+ "[Size: 3 Num.Elems.: 1]",tabla.toString());
		
		//-------------------------Caracteres----------------------------
		//Crea una tabla del tamaño 2 (numero no primo)
		ClosedHashTable<Character> tablac = 
				new ClosedHashTable<Character>(2,2,0.2,0.5);
		
		//Añadimos elementos
		assertEquals(0,tablac.add('B'));
		assertEquals(0,tablac.add('c'));
		assertEquals(0,tablac.add('C'));
		assertEquals(0,tablac.add('K'));
		assertEquals(0,tablac.add('t'));
		assertEquals("{_E_};{t};{_E_};{_E_};{_E_};{_E_};{_E_};{K};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{c};{B};{C};"
				+ "[Size: 17 Num.Elems.: 5]",tablac.toString());
		
		//Intentamos borrar null
		assertEquals(-2, tablac.remove(null));
		
		//Intentamos borrar un nodo que no existe
		assertEquals(-1, tablac.remove('A'));
		
		//Borramos t
		assertEquals(0,tablac.remove('t'));
		assertEquals("{_E_};{_D_};{_E_};{_E_};{_E_};{_E_};{_E_};{K};{_E_};{_E_};"
				+ "{_E_};{_E_};{_E_};{_E_};{c};{B};{C};"
				+ "[Size: 17 Num.Elems.: 4]",tablac.toString());
		
		//Borramos c, redispersion inversa
		assertEquals(0,tablac.remove('c'));
		assertEquals("{_E_};{_E_};{_E_};{B};{C};{K};{_E_};"
				+ "[Size: 7 Num.Elems.: 3]",tablac.toString());
		
		//Borramos B
		assertEquals(0,tablac.remove('B'));
		assertEquals("{_E_};{_E_};{_E_};{_D_};{C};{K};{_E_};"
				+ "[Size: 7 Num.Elems.: 2]",tablac.toString());
		
		//Intentamos volver a borrar B
		assertEquals(-1,tablac.remove('B'));

	}

}
