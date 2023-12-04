package fes.aragon.inicio;

public class Inicio {
	public static void main(String[] args) {
		ArbolBinarioExpresion arbol = new ArbolBinarioExpresion();

		arbol.construyeExpresion("+ * 3 4 5");

		try {
			arbol.preOrden(arbol.getRaiz());

			System.out.println("\nEl resultado de evaluar la expresion es: " + arbol.evaluar(arbol.getRaiz()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
