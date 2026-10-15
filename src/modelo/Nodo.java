package modelo;

/** Representa un nodo generico de un arbol binario. */
public class Nodo<T> {
    private T dato;
    private Nodo<T> izquierdo;
    private Nodo<T> derecho;

    public Nodo(T dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo<T> izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo<T> getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo<T> derecho) {
        this.derecho = derecho;
    }

    /** Una hoja no tiene hijos. */
    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }

    /** El grado es el numero de hijos: 0, 1 o 2. */
    public int grado() {
        int cantidad = 0;
        if (izquierdo != null) {
            cantidad++;
        }
        if (derecho != null) {
            cantidad++;
        }
        return cantidad;
    }
}
