package tdas;

import java.util.Iterator;

/**
 * Clase Bag que implementa una colección simple de elementos donde 
 * se permite agregar elementos pero no eliminarlos. 
 * Es similar a una bolsa o multiconjunto, no mantiene orden específico 
 * ni elementos únicos.
 * 
 * @param <Item> el tipo de elementos que se almacenan en la bolsa
 * @author cvaro
 */
public class Bag<Item> implements Iterable<Item> {
    
    /** Arreglo interno que almacena los elementos */
    private Item[] a;
    
    /** Número de elementos actualmente almacenados */
    private int count;

    /**
     * Crea una nueva instancia de Bag vacía con capacidad inicial de 1.
     */
    public Bag() {
        a = (Item[]) new Object[1]; 
        count = 0;
    }

    /**
     * Agrega un elemento a la bolsa.
     * Si la capacidad del arreglo es alcanzada, lo redimensiona automáticamente.
     * 
     * @param item el elemento a ser agregado a la bolsa
     */
    public void add(Item item) {
        if (count == a.length)
            resize(a.length * 2);
        a[count++] = item;
    }

    /**
     * Verifica si la bolsa está vacía.
     * 
     * @return true si no hay elementos en la bolsa, false en caso contrario
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Retorna el número de elementos almacenados en la bolsa.
     * 
     * @return el tamaño actual de la bolsa
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
     * Retorna un iterador que permite recorrer los elementos en la bolsa.
     * 
     * @return un iterador para los elementos del Bag
     */
    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    /**
     * Clase interna privada que implementa Iterator para recorrer los elementos del Bag.
     */
    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;

        /**
         * Verifica si existen más elementos en la iteración.
         * 
         * @return true si hay más elementos, false en caso contrario
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
