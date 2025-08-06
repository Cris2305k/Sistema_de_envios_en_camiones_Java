package tdas;

import java.util.Iterator;

/**
 * Implementación de una estructura de datos tipo Cola (Queue) usando un arreglo dinámico.
 * Permite operaciones básicas como agregar (enqueue), remover (dequeue), verificar si está vacía
 * y obtener el tamaño. Al remover elementos, los datos se desplazan hacia la izquierda.
 * 
 * @param <Item> el tipo de elementos almacenados en la cola
 * 
 * @author piped
 */
public class Queue<Item> implements Iterable<Item> {

    /** Arreglo que almacena los elementos de la cola */
    private Item[] a;

    /** Contador de elementos actuales en la cola */
    private int count;

    /**
     * Constructor que inicializa una cola vacía con capacidad inicial de 1.
     */
    public Queue() {
        a = (Item[]) new Object[1];
        count = 0;
    }

    /**
     * Agrega un elemento al final de la cola.
     * Si el arreglo está lleno, se duplica su capacidad.
     *
     * @param item el elemento a agregar
     */
    public void enqueue(Item item) {
        if (count == a.length) {
            resize(a.length * 2);
        }
        a[count++] = item;
    }

    /**
     * Elimina y retorna el primer elemento de la cola.
     * Si la cola está vacía, retorna null.
     * Redimensiona el arreglo si es necesario para ahorrar espacio.
     *
     * @return el primer elemento en la cola, o null si está vacía
     */
    public Item dequeue() {
        if (isEmpty()) {
            return null;
        }
        Item item = a[0];
        shiftLeft();        
        a[--count] = null;   
        if (count > 0 && count == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    /**
     * Desplaza todos los elementos del arreglo una posición a la izquierda.
     * Se utiliza luego de hacer un dequeue.
     */
    private void shiftLeft() {
        for (int i = 1; i < count; i++) {
            a[i - 1] = a[i];
        }
    }

    /**
     * Verifica si la cola está vacía.
     *
     * @return true si no hay elementos en la cola, false en caso contrario
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Retorna la cantidad de elementos actualmente en la cola.
     *
     * @return el número de elementos en la cola
     */
    public int size() {
        return count;
    }

    /**
     * Redimensiona el arreglo interno a una nueva capacidad.
     *
     * @param maxCap la nueva capacidad del arreglo
     */
    private void resize(int maxCap) {
        Item[] aux = (Item[]) new Object[maxCap];
        for (int i = 0; i < count; i++) {
            aux[i] = a[i];
        }
        a = aux;
    }

    /**
     * Retorna un iterador para recorrer los elementos en la cola.
     *
     * @return un iterador sobre los elementos en orden de inserción
     */
    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    /**
     * Clase interna que implementa un iterador sobre los elementos de la cola.
     */
    private class ArrayIterator implements Iterator<Item> {

        /** Índice del siguiente elemento a devolver */
        private int i = 0;

        /**
         * Verifica si hay más elementos por iterar.
         *
         * @return true si hay más elementos, false si se ha llegado al final
         */
        @Override
        public boolean hasNext() {
            return i < count;
        }

        /**
         * Retorna el siguiente elemento de la iteración.
         *
         * @return el siguiente elemento en la cola
         */
        @Override
        public Item next() {
            return a[i++];
        }
    }
}
