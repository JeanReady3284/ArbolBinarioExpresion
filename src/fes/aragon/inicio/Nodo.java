package fes.aragon.inicio;

public class Nodo {

	protected String dato;

	public String getDato() {
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

	protected Nodo izquierdo, derecho;
	protected String etiqueta;

	public Nodo() {
		izquierdo = derecho = null;
	}

	public Nodo(String dato, String etiqueta) {
		this(dato, null, null, etiqueta);
	}

	public Nodo(String dato, Nodo izquierdo, Nodo derecho, String etiqueta) {
		this.dato = dato;
		this.izquierdo = izquierdo;
		this.derecho = derecho;
		this.etiqueta = etiqueta;
	}

}
