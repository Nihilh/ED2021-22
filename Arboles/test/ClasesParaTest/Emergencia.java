package ClasesParaTest;

public class Emergencia implements Comparable<Emergencia>{
	
	private int prioridad;
	private String tipo;
	private int unidades;
	private String localizacion;
	
	
	public Emergencia(int prioridad, String tipo, int unidades, 
			String localizacion) {
		setPrioridad(prioridad);
		setTipo(tipo);
		setUnidades(unidades);
		setLocalizacion(localizacion);
	}


	public int getPrioridad() {
		return prioridad;
	}


	private void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}


	public String getTipo() {
		return tipo;
	}


	private void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public int getUnidades() {
		return unidades;
	}


	private void setUnidades(int unidades) {
		this.unidades = unidades;
	}


	public String getLocalizacion() {
		return localizacion;
	}


	private void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}


	@Override
	public String toString() {
		return prioridad+"-"+tipo;
	}


	@Override
	public int compareTo(Emergencia o) {
		if(getPrioridad()==o.getPrioridad()) return 0;
		else if(getPrioridad() > o.getPrioridad()) return 1;
		else return -1;
	}
	
	
	
	
	
	

}
