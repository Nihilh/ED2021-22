package ColasTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ClasesParaTest.Emergencia;
import ColasPrioridad.BinaryHeapMin;

public class BinaryHeapMinTest {

	@Test
	public void addTest() {
		
		BinaryHeapMin<Integer> m = new BinaryHeapMin<>(6);
		
		//Añadimos un nodo a una cola vacía
		assertEquals(0, m.add(2));
		assertEquals("2",m.toString());
		
		//Añadimos un hijo mayor que el padre
		assertEquals(0, m.add(3));
		assertEquals("2\t3",m.toString());
		
		//Añadimos otro hijo mayor que el padre
		assertEquals(0, m.add(5));
		assertEquals("2\t3\t5",m.toString());
		
		//Añadimos un hijo mayor que el padre
		assertEquals(0, m.add(7));
		assertEquals("2\t3\t5\t7",m.toString());
		
		//Añadimos un hijo menor que el padre , filtrado ascendente
		assertEquals(0, m.add(1));
		assertEquals("1\t2\t5\t7\t3",m.toString());
		
		//Añadimos un hijo menor que el padre , filtrado ascendente
		assertEquals(0, m.add(0));
		assertEquals("0\t2\t1\t7\t3\t5",m.toString());
		
		//Añadimos un nodo null
		assertEquals(-2,m.add(null));
		
		//Intentamos añadir un nodo en una cola llena
		assertEquals(-1, m.add(9));
		
		//----------------------------------------------------------------
		BinaryHeapMin<Integer> n = new BinaryHeapMin<>(10);
		
		//Añadimos un nodo a una cola vacía
		assertEquals(0, n.add(4));
		assertEquals("4",n.toString());
		
		//Añadimos un hijo menor que el padre, filtrado ascendente.
		assertEquals(0, n.add(1));
		assertEquals("1\t4",n.toString());
		
		//Añadimos un hijo mayor que el padre.
		assertEquals(0, n.add(5));
		assertEquals("1\t4\t5",n.toString());
		
		//Añadimos un hijo menor que el padre, filtrado ascendente.
		assertEquals(0, n.add(2));
		assertEquals("1\t2\t5\t4",n.toString());
		
		//Añadimos un hijo mayor que el padre.
		assertEquals(0, n.add(9));
		assertEquals("1\t2\t5\t4\t9",n.toString());
		
		//Añadimos un hijo mayor que el padre.
		assertEquals(0, n.add(8));
		assertEquals("1\t2\t5\t4\t9\t8",n.toString());
		
		//Añadimos un hijo menor que el padre, filtrado ascendente.
		assertEquals(0, n.add(3));
		assertEquals("1\t2\t3\t4\t9\t8\t5",n.toString());
		
		//Añadimos un hijo mayor que el padre.
		assertEquals(0, n.add(7));
		assertEquals("1\t2\t3\t4\t9\t8\t5\t7",n.toString());
		
		//---------------------------------------------------------------------
		// Cola de emergencias.
		BinaryHeapMin<Emergencia> e= new BinaryHeapMin<>(6);
		
		//Emergencia en una cola vacía.
		e.add(new Emergencia(8, "Hurto", 1, "La Felguera"));
		assertEquals("8-Hurto",e.toString());
		
		//Emergencia con más prioridad que su padre, filtro ascendente.
		e.add(new Emergencia(4, "Terremoto", 5, "Granada"));
		assertEquals("4-Terremoto\t8-Hurto",e.toString());
		
		//Emergencia con menos prioridad que su padre.
		e.add(new Emergencia(7, "Vandalismo", 2, "La Latina"));
		assertEquals("4-Terremoto\t8-Hurto\t7-Vandalismo",e.toString());
		
		//Emergencia con menos prioridad que su padre.
		e.add(new Emergencia(14, "Disturbios", 3, "Laviana"));
		assertEquals("4-Terremoto\t8-Hurto\t7-Vandalismo\t14-Disturbios",
				e.toString());
		
		//Emergencia con más prioridad que su padre, filtro ascendente.
		e.add(new Emergencia(1, "Incendio", 6, "Gijon"));
		assertEquals("1-Incendio\t4-Terremoto\t7-Vandalismo\t14-Disturbios"
				+ "\t8-Hurto",e.toString());
		
		//Emergencia con más prioridad que su padre, filtro ascendente.
		e.add(new Emergencia(3, "Accidente", 3, "Oviedo"));
		assertEquals("1-Incendio\t4-Terremoto\t3-Accidente\t14-Disturbios"
				+ "\t8-Hurto\t7-Vandalismo",e.toString());
		
		//Añadimos un nodo null
		assertEquals(-2,e.add(null));
		
		//Intentamos añadir un nodo en una cola llena
		assertEquals(-1, e.add(new Emergencia(3, "Huracan", 3, "Murcia")));
	}
	
	@Test
	public void removeTest() {
		
		BinaryHeapMin<Integer> m = new BinaryHeapMin<>(10);
		
		m.add(2);
		m.add(3);
		m.add(5);
		m.add(7);
		m.add(1);
		m.add(0);
		assertEquals("0\t2\t1\t7\t3\t5",m.toString());
		
		//Borramos la raiz
		assertEquals(0,m.remove(0));
		assertEquals("1\t2\t5\t7\t3",m.toString());
		
		//Borramos la raiz
		assertEquals(0,m.remove(1));
		assertEquals("2\t3\t5\t7",m.toString());
		
		//Borramos hoja
		assertEquals(0,m.remove(7));
		assertEquals("2\t3\t5",m.toString());
		
		//Intentamos borrar un nodo que no existe
		assertEquals(-1,m.remove(9));
		
		//Borramos hoja
		assertEquals(0,m.remove(5));
		assertEquals("2\t3",m.toString());
		
		//Borramos hoja
		assertEquals(0,m.remove(3));
		assertEquals("2",m.toString());
		
		//Borramos la raiz y se queda vacía
		assertEquals(0,m.remove(2));
		assertEquals("",m.toString());
		assertEquals(true,m.isEmpty());
		
		//Intentamos borrar un nodo de una cola vacía
		assertEquals(-2,m.remove(2));
		
		//Intentamos borrar un nodo null
		assertEquals(-2,m.remove(null));
		
		//-----------------------------------------------------------------
		BinaryHeapMin<Integer> n = new BinaryHeapMin<>(10);
		
		assertEquals(0, n.add(4));
		assertEquals(0, n.add(1));
		assertEquals(0, n.add(5));
		assertEquals(0, n.add(2));
		assertEquals(0, n.add(9));
		assertEquals(0, n.add(8));
		assertEquals(0, n.add(3));
		assertEquals(0, n.add(7));
		
		//Borramos el minimo.
		assertEquals(0,n.remove(1));
		assertEquals("2\t4\t3\t7\t9\t8\t5",n.toString());
		
		//Borramos el último elemento.
		assertEquals(0,n.remove(5));
		assertEquals("2\t4\t3\t7\t9\t8",n.toString());
		
		//Borramos el máximo
		assertEquals(0,n.remove(9));
		assertEquals("2\t4\t3\t7\t8",n.toString());
		
		//---------------------------------------------------------------------
		// Cola de emergencias.
		BinaryHeapMin<Emergencia> e = new BinaryHeapMin<>(6);

		e.add(new Emergencia(8, "Hurto", 1, "La Felguera"));
		e.add(new Emergencia(4, "Terremoto", 5, "Granada"));
		e.add(new Emergencia(20, "Vandalismo", 2, "La Latina"));
		e.add(new Emergencia(14, "Disturbios", 3, "Laviana"));
		e.add(new Emergencia(1, "Incendio", 6, "Gijon"));
		e.add(new Emergencia(3, "Accidente", 3, "Oviedo"));
		
		//Borramos emergencia con padre mayor que los dos hijos, filtrado desc.
		assertEquals(0, e.remove(new Emergencia(4, "Terremoto", 5, "Granada")));
		assertEquals("1-Incendio\t8-Hurto\t3-Accidente\t14-Disturbios"
				+ "\t20-Vandalismo",e.toString());
		
		//Borramos emergencia con padre mayor que un hijo, filtrado desc.
		assertEquals(0, e.remove(new Emergencia(8, "Hurto", 1, "La Felguera")));
		assertEquals("1-Incendio\t14-Disturbios\t3-Accidente\t20-Vandalismo",
				e.toString());
		
		//Borramos emergencia hoja.
		assertEquals(0,e.remove(new Emergencia(20,"Vandalismo",2,"La Latina")));
		assertEquals("1-Incendio\t14-Disturbios\t3-Accidente",e.toString());
		
		//Borramos emergencia raiz.
		assertEquals(0,e.remove(new Emergencia(1, "Incendio", 6, "Gijon")));
		assertEquals("3-Accidente\t14-Disturbios",e.toString());
}
	
	@Test
	public void sacarTest() {
		//Cola de enteros
		BinaryHeapMin<Integer> m = new BinaryHeapMin<>(10);
		
		m.add(2);
		m.add(3);
		m.add(5);
		m.add(7);
		m.add(1);
		m.add(0);
		
		assertEquals("0\t2\t1\t7\t3\t5",m.toString());
		
		//Sacamos un elemento
		assertTrue(m.sacar().equals(0));
		assertEquals("1\t2\t5\t7\t3",m.toString());
		
		//Sacamos un elemento
		assertTrue(m.sacar().equals(1));
		assertEquals("2\t3\t5\t7",m.toString());
		
		//Sacamos un elemento
		assertTrue(m.sacar().equals(2));
		assertEquals("3\t7\t5",m.toString());
		
		//Sacamos un elemento
		assertTrue(m.sacar().equals(3));
		assertEquals("5\t7",m.toString());
		
		//Sacamos un elemento
		assertTrue(m.sacar().equals(5));
		assertEquals("7",m.toString());
		
		//Sacamos el único elemento
		assertTrue(m.sacar().equals(7));
		assertEquals("",m.toString());
		assertEquals(true,m.isEmpty());
		
		//Intenetamos sacar un elemento de una cola vacía
		assertEquals(null,m.sacar());
		assertEquals("",m.toString());
		assertEquals(true,m.isEmpty());
		
		//---------------------------------------------------------------------
		// Cola de emergencias.
		BinaryHeapMin<Emergencia> e = new BinaryHeapMin<>(6);

		e.add(new Emergencia(8, "Hurto", 1, "La Felguera"));
		e.add(new Emergencia(4, "Terremoto", 5, "Granada"));
		e.add(new Emergencia(7, "Vandalismo", 2, "La Latina"));
		e.add(new Emergencia(14, "Disturbios", 3, "Laviana"));
		e.add(new Emergencia(1, "Incendio", 6, "Gijon"));
		e.add(new Emergencia(3, "Accidente", 3, "Oviedo"));
		
		//Sacamos un elemento
		assertTrue(e.sacar().compareTo(
				new Emergencia(1,"Incendio",6,"Gijon"))==0);
		assertEquals("3-Accidente\t4-Terremoto\t7-Vandalismo\t14-Disturbios"
				+ "\t8-Hurto",e.toString());
		
		//Sacamos un elemento
		assertTrue(e.sacar().compareTo(
				new Emergencia(3,"Accidente",3,"Oviedo"))==0);
		assertEquals("4-Terremoto\t8-Hurto\t7-Vandalismo\t14-Disturbios"
				,e.toString());
		
		//Sacamos un elemento
		assertTrue(e.sacar().compareTo(
				new Emergencia(4,"Terremoto",5,"Granada"))==0);
		assertEquals("7-Vandalismo\t8-Hurto\t14-Disturbios",e.toString());
		
		//Sacamos un elemento
		assertTrue(e.sacar().compareTo(
				new Emergencia(7,"Vandalismo",2,"La Latina"))==0);
		assertEquals("8-Hurto\t14-Disturbios",e.toString());
		
		//Sacamos un elemento
		assertTrue(e.sacar().compareTo(
				new Emergencia(8,"Hurto",1,"La Felguera"))==0);
		assertEquals("14-Disturbios",e.toString());
		
		//Sacamos el último elemento
		assertTrue(e.sacar().compareTo(
				new Emergencia(14,"Disturbios",3,"Laviana"))==0);
		assertTrue(e.isEmpty());
		assertEquals("",e.toString());
		
		//Intentamos sacar un elemento de una cola vacía
		assertEquals(null, e.sacar());
		assertTrue(e.isEmpty());
		assertEquals("",e.toString());
	}
	
	@Test
	public void cambiarPrioridadTest() {
		//Cola de enteros
		BinaryHeapMin<Integer> m = new BinaryHeapMin<>(6);
		
		m.add(2);
		m.add(3);
		m.add(5);
		m.add(7);
		m.add(1);
		m.add(0);
		
		assertEquals("0\t2\t1\t7\t3\t5",m.toString());
		
		//Cambiamos la prioridad del primer elemento, filtrado descendente.
		assertEquals(0,m.cambiarPrioridad(0, 8));
		assertEquals("1\t2\t5\t7\t3\t8",m.toString());
		
		//Cambiamos la prioridad del último elemento, filtrado ascendente.
		assertEquals(0,m.cambiarPrioridad(5, 0));
		assertEquals("0\t2\t1\t7\t3\t5",m.toString());
		
		//Cambiamos la prioridad de elemento intermedio, filtrado descendente.
		assertEquals(0,m.cambiarPrioridad(3, 4));
		assertEquals("0\t2\t1\t4\t3\t5",m.toString());
		
		//Cambiar prioridad de un elemento null
		assertEquals(-2,m.cambiarPrioridad(4, null));
		
		//Cambiar prioridad de una posición negativa
		assertEquals(-2,m.cambiarPrioridad(-2, 4));
		
		//Cambiar prioridad de una posición en el limite de rango
		assertEquals(-2,m.cambiarPrioridad(6, 4));
		
		//Cambiar prioridad de una posición fuera de rango
		assertEquals(-2,m.cambiarPrioridad(9, 4));
		
		//Cambiar prioridad de una cola vacia
		m.clear();
		
		assertEquals(-1,m.cambiarPrioridad(0, 5));
	}
}
