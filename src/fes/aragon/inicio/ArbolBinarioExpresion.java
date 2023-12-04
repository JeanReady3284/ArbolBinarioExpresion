package fes.aragon.inicio;

import fes.aragon.utilerias.dinamicas.cola.Cola;
import fes.aragon.utilerias.dinamicas.pila.Pila;

/**
 * Clase que permite crear un arbol de expresiones
 * 
 * @author Darxus
 *
 */
public class ArbolBinarioExpresion {

	protected Nodo raiz;

	public ArbolBinarioExpresion() {
		raiz = null;
	}

	/**
	 * Metodo que devuelve la raíz del arbol
	 * 
	 * @return devuelve la raíz del arbol
	 */
	public Nodo getRaiz() {
		return raiz;
	}

	/**
	 * Metodo que permite crear el arbol de expresiones proporcionandole una
	 * expresion prefija
	 * 
	 * @param s Cadena que indica la expresion prefija a insertar en el arbol
	 */
	public void construyeExpresion(String s) {

		String[] token = s.split(" ");

		Pila<Nodo> pila = new Pila<>();
		int contador = 1;
		Nodo ultimoNodo;
		Nodo nuevoNodo;
		boolean izq = true;

		raiz = new Nodo(token[0], "raiz");
		nuevoNodo = raiz;

		while (token.length != contador) {

			ultimoNodo = nuevoNodo;

			if (izq) {
				nuevoNodo.izquierdo = new Nodo(token[contador], "izquierdo, padre=" + ultimoNodo.dato);
				nuevoNodo = nuevoNodo.izquierdo;
				pila.insertar(ultimoNodo);
			} else {
				try {
					ultimoNodo = pila.extraer();
					ultimoNodo.derecho = new Nodo(token[contador], "derecho, padre=" + ultimoNodo.dato);
					nuevoNodo = ultimoNodo.derecho;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (esOperador(token[contador])) {
				izq = true;

			} else {
				izq = false;
			}

			contador++;

		}
	}

	/**
	 * Metodo que permite evaluar el arbol de expresiones, usando recursividad
	 * 
	 * @param n parametro que indica el nodo raiz, o nodo para aplicar operacion a
	 *          sus hijos
	 * @return si el nodo es operador devuelve la operacion entre hijo izquierdo y
	 *         derecho, si no, entonces devuelve el operando
	 */
	public float evaluar(Nodo raiz) {
		float resultado = 0;

		if (esOperador(raiz.dato)) {

			if (raiz.dato.equals("+")) {
				resultado = evaluar(raiz.izquierdo) + evaluar(raiz.derecho);

			} else if (raiz.dato.equals("-")) {
				resultado = evaluar(raiz.izquierdo) - evaluar(raiz.derecho);

			} else if (raiz.dato.equals("*")) {
				resultado = evaluar(raiz.izquierdo) * evaluar(raiz.derecho);

			} else if (raiz.dato.equals("/")) {
				resultado = evaluar(raiz.izquierdo) / evaluar(raiz.derecho);

			}
			return resultado;

		} else {
			return Float.valueOf(raiz.dato);
		}

	}

	/**
	 * Metodo que verifica si un simbolo es operador
	 * 
	 * @param s parametro que indica el simbolo a verificar
	 * @return devuelve verdadero si el signo es un operador, falso si no lo es
	 */

	public boolean esOperador(String s) {
		if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metodo que permite imprimir el nodo con su etiqueta
	 * 
	 * @param n parametro que indica el nodo a imprimir
	 */

	public void imprimir(Nodo n) {
		System.out.println(n.dato + " " + n.etiqueta);
	}

	/**
	 * Metodo que imprime el arbol con recorrido de amplitud
	 * 
	 * @throws Exception arroja una excepcion si hay problemas con la cola
	 */

	public void recorridoAmplitud() throws Exception {
		Nodo n = raiz;
		Cola<Nodo> cola = new Cola<>();
		if (n != null) {
			cola.insertar(n);

			while (!cola.estaVacia()) {
				n = cola.extraer();
				imprimir(n);
				if (n.izquierdo != null) {
					cola.insertar(n.izquierdo);
				}
				if (n.derecho != null) {
					cola.insertar(n.derecho);
				}
			}

		}
	}

	/**
	 * Metodo que permite imprimir el arbol en preorden raiz-subarbol izquierdo-
	 * subarbol derecho (prefija)
	 * 
	 * @param n parametro que recibe el nodo raiz del arbol de expresiones
	 */
	public void preOrden(Nodo n) {
		if (n != null) {
			imprimir(n);
			preOrden(n.izquierdo);
			preOrden(n.derecho);
		}

	}

	/**
	 * Metodo que imprime el arbol en orden subarbol izquierdo-raiz-subarbol derecho
	 * (interfija)
	 * 
	 * @param n parametro que recibe el nodo raiz del arbol de expresiones
	 */
	public void orden(Nodo n) {
		if (n != null) {
			orden(n.izquierdo);
			imprimir(n);
			orden(n.derecho);
		}
	}

	/**
	 * Metodo que imprime el arbol en postorden subarbol izquierdo- subarbol
	 * derecho- raiz (postfija)
	 * 
	 * @param n parametro que recibe el nodo raiz del arbol de expresiones
	 */
	public void postOrden(Nodo n) {
		if (n != null) {
			postOrden(n.izquierdo);
			postOrden(n.derecho);
			imprimir(n);
		}
	}

}
