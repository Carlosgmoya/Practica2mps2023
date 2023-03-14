package org.mps.deque;

import java.util.Comparator;

/**
 *  @author Carlos Garcia Moya
 *  @author David Villaseca Pareja
 */
public class DoublyLinkedListDeque<T> implements DoubleEndedQueue<T> {

    private DequeNode<T> first;
    private DequeNode<T> last;
    private int size;

    public DoublyLinkedListDeque() {
        this.first = null;
        this.last = null;
        size = 0;
    }

    @Override
    public void prepend(T value) {
        DequeNode<T> node;
        if(size == 0) {
            node = new DequeNode<T>(value, null, null);
            this.first = node;
            this.last = node;
        } else {
            node = new DequeNode<T>(value, null, first);
            first.setPrevious(node);
            this.first = node;
        }
        size++;
    }

    @Override
    public void append(T value) {
        DequeNode<T> node;
        if(size == 0) {
            node = new DequeNode<T>(value, null, null);
            this.first = node;
            this.last = node;
        } else {
            node = new DequeNode<T>(value, last, null);
            last.setNext(node);
            this.last = node;
        }
        size++;

    }

    @Override
    public void deleteFirst() {
        if (size == 0){
            throw new DoubleEndedQueueException("La lista esta vacia");
        } else if(size == 1) {
            this.first = null;
            size--;
        } else {
            first.getNext().setPrevious(null);
            this.first = first.getNext();
            size--;
        }
    }

    @Override
    public void deleteLast() {
        if (size == 0) {
            throw new DoubleEndedQueueException("La lista esta vacia");
        } else if(size == 1) {
            this.last = null;
            size--;
        } else {
            last.getPrevious().setNext(null);
            this.last = last.getPrevious();
            size--;
        }
    }

    @Override
    public T first() {
        if(size == 0){
            throw new DoubleEndedQueueException("La lista esta vacia");
        } else {
            return this.first.getItem();
        }
    }

    @Override
    public T last() {
        if(size == 0){
            throw new DoubleEndedQueueException("La lista esta vacia");
        } else {
            return this.last.getItem();
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T get(int index) {
        if (this.size-1 < index || index < 0) {
            throw new IndexOutOfBoundsException("Esa posicion esta fuera de los limites de la lista");
        }
        DequeNode<T> result = this.first;
        for (int i = 0; i < index-1; i++) {
            if ( index == 0){
                return result.getItem();
            }
            result = result.getNext();
        }
        T item = result.getItem();
        return item;
    }

    @Override
    public boolean contains(T value) {

        DequeNode<T> result = this.first;
        for (int i = 0; i < this.size; i++){
            if (result.getItem() == value) {
                return true;
            }
            result = result.getNext();
        }
        return false;
    }

    @Override
    public void remove(T value) {

    }

    @Override
    public void sort(Comparator<? super T> comparator) {

    }
}
