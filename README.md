# Implementacion de un arbol binario en Java

**Asignatura:** Estructura de Datos  
**Nivel:** Tercero, Ingenieria de Software  
**Modalidad:** Individual

## Problema

Representar las dependencias academicas de una universidad mediante un arbol binario general. Cada nodo guarda un nombre y tiene como maximo dos hijos. Se implementan insercion, consulta, visualizacion, conteo recursivo de nodos y hojas, y calculo recursivo de la altura medida en aristas.

## Estructura del arbol

```text
UNIVERSIDAD
├── FISEI
│   ├── SOFTWARE
│   │   ├── NIVEL1
│   │   └── NIVEL3
│   └── TI
└── DIDE
    ├── INVEST.
    └── VINCULACIÓN
```

**Resultados esperados:** 9 nodos, 5 hojas y altura 3.

## Estructura del codigo

```text
src/
├── modelo/Nodo.java
├── negocio/ArbolBinario.java
└── app/Main.java
```

- `Nodo<T>`: dato, hijos, getters, setters, `esHoja()` y `grado()`.
- `ArbolBinario<T>`: raiz, insercion en cada lado, busqueda, conteos recursivos, altura recursiva y visualizacion.
- `Main`: menu de ocho opciones con `Scanner` y pruebas obligatorias.

## Requisitos

- Java JDK 8 o superior.
- Visual Studio Code con Extension Pack for Java (opcional).

## Instrucciones para ejecutar

Desde la carpeta principal del repositorio, en una terminal:

```bash
javac -encoding UTF-8 -d out src/modelo/Nodo.java src/negocio/ArbolBinario.java src/app/Main.java
java -cp out app.Main
```

Para ejecutar las pruebas obligatorias, sin modificar el menu:

```bash
java -cp out app.Main --pruebas
```

## Opciones del menu

1. Crear el arbol inicial.
2. Visualizar el arbol.
3. Consultar el numero de nodos.
4. Consultar el numero de hojas.
5. Consultar la altura.
6. Consultar el grado de un nodo.
7. Verificar si un nodo es hoja.
8. Salir.

## Resultados y casos de prueba

| Caso | Resultado esperado |
|---|---|
| Arbol vacio | 0 nodos, 0 hojas, altura -1 |
| Solo raiz | 1 nodo, 1 hoja, altura 0 |
| Arbol completo | 9 nodos, 5 hojas, altura 3 |
| Insertar hijo izquierdo de FISEI cuando ya existe SOFTWARE | Operacion rechazada; no se reemplaza el hijo |
| Grado de FISEI | 2 |
| Grado de SOFTWARE | 2 |

Las pruebas se ejecutan con `--pruebas` y reportan `[OK]` o `[ERROR]` para cada verificacion.

## Evidencias

Agregar capturas reales de la ejecucion del menu y de las pruebas obligatorias antes de la entrega. El PDF de Moodle incluira el enlace al repositorio, las capturas y las respuestas a las tres preguntas de reflexion.

## Restricciones cumplidas

No se utilizan `TreeSet`, `TreeMap`, `PriorityQueue`, `NavigableSet` ni `NavigableMap`. Los conteos y la altura se calculan por recursividad.
# T2.1-Implementaci-n-de-un-rbol-binario-