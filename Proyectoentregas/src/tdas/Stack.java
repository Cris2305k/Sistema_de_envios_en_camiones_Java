package tdas;

import java.util.Iterator;

/**
 * Implementación de una estructura de datos tipo Pila (Stack) usando un arreglo dinámico.
 * Permite operaciones básicas como apilar (push), desapilar (pop), ver el elemento en la cima (peak),
 * verificar si está vacía y obtener el tamaño. La capacidad del arreglo se ajusta dinámicamente.
 *
 * @param <Item> el tipo de elementos almacenados en la pila
 * 
 * @author sala5
 */
public class Stack<Item> implements Iterable<Item> {

    /** Arreglo que almacena los elementos de la pila */
    private Item[] a;

    /** Contador de elementos actuales en la pila */
    private int count;

    /**
     * Constructor que inicializa una pila vacía con capacidad inicial de 1.
     */
    public Stack() {
        a = (Item[]) new Object[1];
        count = 0;
    }

    /**
     * Agrega un elemento a la cima de la pila.
     * Si el arreglo está lleno, se duplica su capacidad.
     *
     * @param item el elemento a agregar
     */
    public void push(Item item) {
        if (count == a.length)
            resize(a.length * 2);
        a[count++] = item;
    }

    /**
     * Elimina y retorna el elemento en la cima de la pila.
     * Si la pila está vacía, retorna null.
     * Redimensiona el arreglo si es necesario para ahorrar espacio.
     *
     * @return el elemento en la cima de la pila, o null si está vacía
     */
    public Item pop() {
        if (isEmpty())
            return null;
        Item aux = a[--count];
        a[count] = null; // evita loitering
        if (count > 0 && count == a.length / 4)
            resize(a.length / 2);
        return aux;
    }

    /**
     * Retorna el elemento en la cima de la pila sin eliminarlo.
     *
     * @return el elemento en la cima de la pila
     */
    public Item peak() {
        return a[count - 1];
    }

    /**
     * Verifica si la pila está vacía.
     *
     * @return true si no hay elementos en la pila, false en caso contrario
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Retorna la cantidad de elementos actualmente en la pila.
     *
     * @return el número de elementos en la pila
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
     * Retorna un iterador que recorre los elementos de la pila en orden LIFO.
     *
     * @return un iterador sobre los elementos de la pila
     */
    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    /**
     * Clase interna que implementa un iterador en orden inverso para la pila.
     */
    private class ReverseArrayIterator implements Iterator<Item> {

        /** Índice del siguiente elemento a retornar en la iteración */
        private int i = count;

        /**
         * Verifica si hay más elementos por iterar.
         *
         * @return true si hay más elementos, false si se ha llegado al final
         */
        @Override
        public boolean hasNext() {
            return i > 0;
        }

        /**
         * Retorna el siguiente elemento en la iteración.
         *
         * @return el siguiente elemento en orden LIFO
         */
        @Override
        public Item next() {
            return a[--i];
        }
    }
}
