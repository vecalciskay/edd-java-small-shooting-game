package shooter.util;

import java.util.Iterator;

import shooter.model.Bullet;

public class Lista<T> implements Iterable<T> {

	protected Nodo<T> inicio;
	protected int tamano;

	public Lista() {
		inicio = null;
		tamano = 0;
	}

	public boolean vacia() {
		return inicio == null;
	}

	/**
	 * Si la lista tiene por ejemplo objeto: hugo, objeto: paco, objeto: luis
	 * entonces me devuelve [hugo, paco, luis]
	 */
	public String toString() {
		if (inicio == null)
			return "[]";

		StringBuilder resultado = new StringBuilder();
		resultado.append("[");

		String separador = "";
		Nodo<T> actual = inicio;
		while (actual != null) {
			String itemString = actual.getContenido().toString();
			resultado.append(separador).append(itemString);
			separador = ",";
			actual = actual.getSiguiente();
		}

		resultado.append("]");
		return resultado.toString();
	}
	
	public int getTamano() {
		return tamano;
	}

	/**
	 * 
	 * @param o
	 * @param posicion Es un arreglo de UN entero al menos
	 * @return
	 */
	public T buscar(T o, int[] posicion) throws Exception {
		int contador = 0;
		Nodo<T> actual = inicio;
		while(actual != null && actual.getContenido() != o) {
			actual = actual.getSiguiente();
			contador++;
		}
		
		if (actual == null) {
			posicion[0] = -1;
			return null;
		}
		
		posicion[0] = contador;
		return actual.getContenido();
	}

	public T get(int idx) throws Exception {
		
		int contador = 0;
		Nodo<T> actual = inicio;
		while(actual != null && contador < idx) {
			actual = actual.getSiguiente();
			contador++;
		}
		
		if (actual == null)
			throw new Exception("El indice " + idx + " es mayor al tamano " + contador + " de la lista");
		
		return actual.getContenido(); 
	}
	
	public void insertar(T o) {
		Nodo<T> nuevoNodo = new Nodo<T>(o);
		nuevoNodo.setSiguiente(inicio);
		inicio = nuevoNodo;
		
		tamano++;
	}

	public void agregar(T o) {
		if (inicio == null) {
			insertar(o);			
			return;
		}
		
		Nodo<T> actual = inicio;
		while(actual.getSiguiente() != null) {
			actual = actual.getSiguiente();
		}
		
		Nodo<T> nuevoNodo = new Nodo<T>(o); 
		actual.setSiguiente(nuevoNodo);
		
		tamano++;
	}


	public void eliminar(T o) throws Exception {
		if (inicio == null)
			throw new Exception("Lista vacia, nada que eliminar");
		if (inicio.getContenido() == o) {
			inicio = inicio.getSiguiente();
			tamano--;
			return;
		}
		
		Nodo<T> actual = inicio;
		while(actual.getSiguiente().getContenido() != o && 
				actual.getSiguiente() != null) {
			actual = actual.getSiguiente();
		}
		if (actual.getSiguiente() == null)
			throw new Exception("No existe esa posicion en esta lista");
		
		actual.setSiguiente(actual.getSiguiente().getSiguiente());
		tamano--;
	}
	
	public void eliminar(int pos) throws Exception {
		
		if (pos < 0)
			throw new Exception("No existen posiciones negativas");
		
		if (inicio == null)
			throw new Exception("Lista vacia, nada que eliminar");
		
		if (pos == 0) {
			inicio = inicio.getSiguiente();
			tamano--;
			return;
		}
		
		int contador = 0;
		Nodo<T> actual = inicio;
		while(contador < (pos-1) && actual.getSiguiente() != null) {
			actual = actual.getSiguiente();
			contador++;
		}
		if (actual.getSiguiente() == null)
			throw new Exception("No existe esa posicion en esta lista");
		
		actual.setSiguiente(actual.getSiguiente().getSiguiente());
		tamano--;
	}

	/**
	 * Deprecated Usar el metodo tamano ya que es mas eficiente
	 * @return
	 */
	@Deprecated
	public int length() {
		int contador = 0;
		Nodo<T> actual = inicio;
		while (actual != null) {
			contador++;
			actual = actual.getSiguiente();
		}

		return contador;
	}

	@Override
	public Iterator<T> iterator() {
		return new IteradorNodo<>(inicio);
	}

	class Nodo<T> {

		private T contenido;
		private Nodo<T> siguiente;

		public Nodo(T o) {
			contenido = o;
			siguiente = null;
		}

		public T getContenido() {
			return contenido;
		}

		public void setContenido(T contenido) {
			this.contenido = contenido;
		}

		public Nodo<T> getSiguiente() {
			return siguiente;
		}

		public void setSiguiente(Nodo<T> siguiente) {
			this.siguiente = siguiente;
		}

	}

	class IteradorNodo<T> implements Iterator<T> {

		private Nodo<T> actual;

		public IteradorNodo(Nodo<T> inicio) {
			actual = inicio;
		}

		@Override
		public boolean hasNext() {
			return actual != null;
		}

		@Override
		public T next() {
			T resultado = actual.getContenido();
			actual = actual.getSiguiente();
			return resultado;
		}

	}
}
