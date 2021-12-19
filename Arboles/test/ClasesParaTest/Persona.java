package ClasesParaTest;

public class Persona implements Comparable<Persona>{

	private int edad;
	private String nombre;
	
	public Persona(int edad, String nombre) {
		setEdad(edad);
		setNombre(nombre);
	}
	
	public Persona (int edad) {
		setEdad(edad);
	}
	
	public int getEdad() {
		return edad;
	}

	private void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "["+nombre+":"+edad+"]";
	}

	@Override
	public int compareTo(Persona o) {
		if(edad>o.getEdad()) return 1;
		else if(edad < o.getEdad()) return -1;
		else return 0;
	}
	

}
