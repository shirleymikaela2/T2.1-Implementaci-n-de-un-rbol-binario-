package negocio;

import java.util.Objects;
import modelo.Nodo;

/** Arbol binario general: los hijos se ubican de forma explicita. */
public class ArbolBinario<T> {
    private Nodo<T> raiz;

    public ArbolBinario() {
        raiz = null;
    }

    public Nodo<T> getRaiz() {
        return raiz;
    }

    public boolean esVacio() {
        return raiz == null;
    }

    public Nodo<T> crearRaiz(T dato) {
        if (!esVacio()) {
            throw new IllegalStateException("La raiz ya existe.");
        }
        raiz = new Nodo<>(dato);
        return raiz;
    }

    public Nodo<T> agregarIzquierdo(Nodo<T> padre, T dato) {
        validarPadre(padre);
        if (padre.getIzquierdo() != null) {
            throw new IllegalStateException("El hijo izquierdo ya esta ocupado.");
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        padre.setIzquierdo(nuevo);
        return nuevo;
    }

    public Nodo<T> agregarDerecho(Nodo<T> padre, T dato) {
        validarPadre(padre);
        if (padre.getDerecho() != null) {
            throw new IllegalStateException("El hijo derecho ya esta ocupado.");
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        padre.setDerecho(nuevo);
        return nuevo;
    }

    private void validarPadre(Nodo<T> padre) {
        if (padre == null || !contieneReferencia(raiz, padre)) {
            throw new IllegalArgumentException("El padre no pertenece a este arbol.");
        }
    }

    private boolean contieneReferencia(Nodo<T> actual, Nodo<T> buscado) {
        if (actual == null) {
            return false;
        }
        return actual == buscado
                || contieneReferencia(actual.getIzquierdo(), buscado)
                || contieneReferencia(actual.getDerecho(), buscado);
    }

    public Nodo<T> buscar(T dato) {
        return buscarRecursivo(raiz, dato);
    }

    private Nodo<T> buscarRecursivo(Nodo<T> actual, T dato) {
        if (actual == null) {
            return null;
        }
        if (Objects.equals(actual.getDato(), dato)) {
            return actual;
        }
        Nodo<T> encontrado = buscarRecursivo(actual.getIzquierdo(), dato);
        return encontrado != null ? encontrado : buscarRecursivo(actual.getDerecho(), dato);
    }

    public int contarNodos() {
        return contarNodosRecursivo(raiz);
    }

    private int contarNodosRecursivo(Nodo<T> actual) {
        if (actual == null) {
            return 0;
        }
        return 1 + contarNodosRecursivo(actual.getIzquierdo())
                 + contarNodosRecursivo(actual.getDerecho());
    }

    public int contarHojas() {
        return contarHojasRecursivo(raiz);
    }

    private int contarHojasRecursivo(Nodo<T> actual) {
        if (actual == null) {
            return 0;
        }
        if (actual.esHoja()) {
            return 1;
        }
        return contarHojasRecursivo(actual.getIzquierdo())
             + contarHojasRecursivo(actual.getDerecho());
    }

    /** Altura en aristas: vacio=-1 y solo raiz=0. */
    public int altura() {
        return alturaRecursiva(raiz);
    }

    private int alturaRecursiva(Nodo<T> actual) {
        if (actual == null) {
            return -1;
        }
        return 1 + Math.max(alturaRecursiva(actual.getIzquierdo()),
                            alturaRecursiva(actual.getDerecho()));
    }

    public void mostrarArbol() {
        if (esVacio()) {
            System.out.println("(Arbol vacio)");
            return;
        }
        System.out.println(raiz.getDato());
        mostrarHijos(raiz, "");
    }

    private void mostrarHijos(Nodo<T> nodo, String prefijo) {
        Nodo<T> izquierdo = nodo.getIzquierdo();
        Nodo<T> derecho = nodo.getDerecho();

        if (izquierdo != null) {
            boolean hayDerecho = derecho != null;
            System.out.println(prefijo + (hayDerecho ? "├── " : "└── ") + izquierdo.getDato());
            mostrarHijos(izquierdo, prefijo + (hayDerecho ? "│   " : "    "));
        }
        if (derecho != null) {
            System.out.println(prefijo + "└── " + derecho.getDato());
            mostrarHijos(derecho, prefijo + "    ");
        }
    }
}
