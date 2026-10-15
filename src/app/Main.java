package app;

import java.util.Locale;
import java.util.Scanner;
import modelo.Nodo;
import negocio.ArbolBinario;

public class Main {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--pruebas")) {
            ejecutarPruebas();
            return;
        }

        Scanner scanner = new Scanner(System.in);
        ArbolBinario<String> arbol = new ArbolBinario<>();
        int opcion;

        do {
            mostrarMenu();
            System.out.print("Seleccione una opcion: ");
            String entrada = scanner.nextLine().trim();
            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException error) {
                System.out.println("Error: escriba un numero del 1 al 8.\n");
                opcion = 0;
                continue;
            }

            switch (opcion) {
                case 1:
                    arbol = crearArbolInicial();
                    System.out.println("Arbol inicial creado correctamente.");
                    break;
                case 2:
                    arbol.mostrarArbol();
                    break;
                case 3:
                    System.out.println("Numero de nodos: " + arbol.contarNodos());
                    break;
                case 4:
                    System.out.println("Numero de hojas: " + arbol.contarHojas());
                    break;
                case 5:
                    System.out.println("Altura en aristas: " + arbol.altura());
                    break;
                case 6:
                    consultarNodo(scanner, arbol, false);
                    break;
                case 7:
                    consultarNodo(scanner, arbol, true);
                    break;
                case 8:
                    System.out.println("Gracias por utilizar el programa. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opcion invalida. Elija del 1 al 8.");
            }
            System.out.println();
        } while (opcion != 8);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("========= ARBOL BINARIO UNIVERSITARIO =========");
        System.out.println("1. Crear el arbol inicial");
        System.out.println("2. Visualizar el arbol");
        System.out.println("3. Consultar numero de nodos");
        System.out.println("4. Consultar numero de hojas");
        System.out.println("5. Consultar altura");
        System.out.println("6. Consultar grado de un nodo");
        System.out.println("7. Verificar si un nodo es hoja");
        System.out.println("8. Salir");
    }

    private static ArbolBinario<String> crearArbolInicial() {
        ArbolBinario<String> arbol = new ArbolBinario<>();
        Nodo<String> universidad = arbol.crearRaiz("UNIVERSIDAD");
        Nodo<String> fisei = arbol.agregarIzquierdo(universidad, "FISEI");
        Nodo<String> dide = arbol.agregarDerecho(universidad, "DIDE");

        Nodo<String> software = arbol.agregarIzquierdo(fisei, "SOFTWARE");
        arbol.agregarDerecho(fisei, "TI");
        arbol.agregarIzquierdo(dide, "INVEST.");
        arbol.agregarDerecho(dide, "VINCULACIÓN");

        arbol.agregarIzquierdo(software, "NIVEL1");
        arbol.agregarDerecho(software, "NIVEL3");
        return arbol;
    }

    private static void consultarNodo(Scanner scanner, ArbolBinario<String> arbol,
                                      boolean consultarHoja) {
        if (arbol.esVacio()) {
            System.out.println("Primero debe crear el arbol (opcion 1).");
            return;
        }
        System.out.print("Ingrese el nombre del nodo: ");
        String nombre = scanner.nextLine().trim().toUpperCase(Locale.ROOT);
        Nodo<String> nodo = arbol.buscar(nombre);
        if (nodo == null) {
            System.out.println("No se encontro el nodo: " + nombre);
        } else if (consultarHoja) {
            System.out.println(nodo.getDato() + (nodo.esHoja() ? " SI es hoja." : " NO es hoja."));
        } else {
            System.out.println("Grado de " + nodo.getDato() + ": " + nodo.grado());
        }
    }

    /** Ejecutar con: java -cp out app.Main --pruebas */
    private static void ejecutarPruebas() {
        System.out.println("======= CASOS DE PRUEBA OBLIGATORIOS =======");

        ArbolBinario<String> vacio = new ArbolBinario<>();
        System.out.println("\n1. Arbol vacio:");
        comprobar("0 nodos", vacio.contarNodos() == 0);
        comprobar("0 hojas", vacio.contarHojas() == 0);
        comprobar("altura -1", vacio.altura() == -1);

        ArbolBinario<String> simple = new ArbolBinario<>();
        simple.crearRaiz("UNIVERSIDAD");
        System.out.println("\n2. Arbol con un solo nodo:");
        comprobar("1 nodo", simple.contarNodos() == 1);
        comprobar("1 hoja", simple.contarHojas() == 1);
        comprobar("altura 0", simple.altura() == 0);

        ArbolBinario<String> completo = crearArbolInicial();
        System.out.println("\n3. Arbol inicial:");
        completo.mostrarArbol();
        comprobar("9 nodos", completo.contarNodos() == 9);
        comprobar("5 hojas", completo.contarHojas() == 5);
        comprobar("altura 3", completo.altura() == 3);

        System.out.println("\n4. Insercion en una posicion ocupada:");
        try {
            completo.agregarIzquierdo(completo.buscar("FISEI"), "NUEVO");
            comprobar("rechazar insercion", false);
        } catch (IllegalStateException error) {
            System.out.println("Error esperado: " + error.getMessage());
            comprobar("rechazar insercion", completo.contarNodos() == 9
                    && "SOFTWARE".equals(completo.buscar("FISEI").getIzquierdo().getDato()));
        }

        System.out.println("\n5. Consulta de grados:");
        comprobar("grado de FISEI = 2", completo.buscar("FISEI").grado() == 2);
        comprobar("grado de SOFTWARE = 2", completo.buscar("SOFTWARE").grado() == 2);
        comprobar("NIVEL1 es hoja", completo.buscar("NIVEL1").esHoja());
        comprobar("SOFTWARE no es hoja", !completo.buscar("SOFTWARE").esHoja());
    }

    private static void comprobar(String descripcion, boolean correcto) {
        System.out.println((correcto ? "[OK] " : "[ERROR] ") + descripcion);
    }
}
