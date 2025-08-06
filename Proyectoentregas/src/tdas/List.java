package tdas;

import java.util.Iterator;

/**
 * Implementación de una lista dinámica que almacena elementos genéricos.
 * Permite operaciones como agregar, eliminar, acceder, modificar elementos,
 * y recorrerlos mediante iteradores.
 *
 * @param <Item> el tipo de elementos almacenados en la lista
 * 
 * @author piped
 */
public class List<Item> implements Iterable<Item> {
    
    /** Arreglo interno que almacena los elementos de la lista */
    private Item[] a;

    /** Cantidad de elementos actuales en la lista */
    private int count;

    /**
     * Constructor que inicializa la lista con capacidad inicial de 1.
     */
    public List() {
        a = (Item[]) new Object[1];
        count = 0;
    }

    /**
     * Agrega un elemento al final de la lista.
     *
     * @param item el elemento a agregar
     */
    public void list(Item item) {
        if (count == a.length)
            resize(a.length * 2);
        a[count++] = item;
    }

    /**
     * Agrega un elemento en la posición especificada.
     *
     * @param index índice en el que se agregará el elemento
     * @param placa el elemento a agregar
     * @return true si el elemento fue agregado exitosamente, false si el índice es inválido
     */
    public boolean add(int index, Item placa) {
        if (index >= 0 && index <= count) {
            if (count == a.length)
                resize(a.length * 2);
            shiftRight(index);
            a[index] = placa;
            count++;
            return true;
        }
        return false;
    }

    /**
     * Agrega un elemento al inicio de la lista.
     *
     * @param placa el elemento a agregar
     */
    public void addFirst(Item placa) {
        add(0, placa);
    }

    /**
     * Agrega un elemento al final de la lista.
     *
     * @param placa el elemento a agregar
     */
    public void addLast(Item placa) {
        add(count, placa);
    }

    /**
     * Elimina todos los elementos de la lista.
     */
    public void clear() {
        a = (Item[]) new Object[a.length];
        count = 0;
    }

    /**
     * Redimensiona el arreglo interno a una nueva capacidad.
     *
     * @param maxCap nueva capacidad del arreglo
     */
    private void resize(int maxCap) {
        Item[] aux = (Item[]) new Object[maxCap];
        for (int i = 0; i < count; i++) {
            aux[i] = a[i];
        }
        a = aux;
    }

    /**
     * Retorna el elemento en una posición específica.
     *
     * @param index posición del elemento
     * @return el elemento en esa posición, o null si el índice es inválido
     */
    public Item get(int index) {
        return index >= 0 && index < count ? a[index] : null;
    }

    /**
     * Retorna el primer elemento de la lista.
     *
     * @return el primer elemento
     */
    public Item getFirst() {
        return a[0];
    }

    /**
     * Retorna el último elemento de la lista.
     *
     * @return el último elemento
     */
    public Item getLast() {
        return a[count - 1];
    }

    /**
     * Verifica si la lista está vacía.
     *
     * @return true si está vacía, false en caso contrario
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Elimina y retorna el elemento en la posición especificada.
     *
     * @param index índice del elemento a eliminar
     * @return el elemento eliminado, o null si el índice es inválido
     */
    public Item remove(int index) {
        if (!isEmpty() && index >= 0 && index < count) {
            Item aux = a[index];
            shiftLeft(index);
            count--;
            return aux;
        }
        return null;
    }

    /**
     * Elimina y retorna el primer elemento de la lista.
     *
     * @return el primer elemento eliminado
     */
    public Item removeFirst() {
        return remove(0);
    }

    /**
     * Elimina y retorna el último elemento de la lista.
     *
     * @return el último elemento eliminado
     */
    public Item removeLast() {
        Item aux = a[--count];
        a[count] = null;
        return aux;
    }

    /**
     * Reemplaza el elemento en la posición especificada con otro.
     *
     * @param index posición del elemento a reemplazar
     * @param placa nuevo elemento
     * @return el elemento anterior, o null si el índice es inválido
     */
    public Item set(int index, Item placa) {
        if (!isEmpty() && index >= 0 && index < count) {
            Item aux = a[index];
            a[index] = placa;
            return aux;
        }
        return null;
    }

    /**
     * Retorna el número de elementos en la lista.
     *
     * @return el tamaño de la lista
     */
    public int size() {
        return count;
    }

    /**
     * Desplaza los elementos una posición a la izquierda desde el índice dado.
     *
     * @param index índice desde donde se comenzará a desplazar
     */
    private void shiftLeft(int index) {
        for (int i = index; i < count - 1; i++) {
            a[i] = a[i + 1];
        }
        a[count - 1] = null;
    }

    /**
     * Desplaza los elementos una posición a la derecha desde el índice dado.
     *
     * @param index índice desde donde se comenzará a desplazar
     */
    private void shiftRight(int index) {
        for (int i = count; i > index; i--) {
            a[i] = a[i - 1];
        }
    }

    /**
     * Retorna un iterador para recorrer los elementos de la lista.
     *
     * @return un iterador sobre los elementos de la lista
     */
    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    /**
     * Clase interna que implementa el iterador de la lista.
     */
    private class ArrayIterator implements Iterator<Item> {

        /** Índice actual del iterador */
        private int i = 0;

        /**
         * Verifica si hay más elementos por iterar.
         *
         * @return true si quedan elementos, false en caso contrario
         */
        @Override
        public boolean hasNext() {
            return i < count;
        }

        /**
         * Retorna el siguiente elemento en la iteración.
         *
         * @return el siguiente elemento
         */
        @Override
        public Item next() {
            return a[i++];
        }
    }
}
