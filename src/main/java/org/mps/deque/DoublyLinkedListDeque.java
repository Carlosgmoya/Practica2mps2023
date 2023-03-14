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
        for (int i = 0; i < index; i++) {

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
        if(size == 0){
            throw new DoubleEndedQueueException("List is empty");
        }
        boolean found = false;
        int i = 0;
        DequeNode<T> aux = first;
        while (!found && i < size) {
            if (this.get(i).equals(value)) {
                found = true;
                if(i == 0) {
                    aux.getNext().setPrevious(null);
                    this.first = aux.getNext();
                    size--;
                } else if(i == size - 1) {
                    aux.getPrevious().setNext(null);
                    this.last = aux.getPrevious();
                    size--;
                } else {
                    aux.getPrevious().setNext(aux.getNext());
                    aux.getNext().setPrevious(aux.getPrevious());
                    size--;
                }
            } else {
                aux = aux.getNext();
                i++;
            }
        }
        if(!found){
            throw new DoubleEndedQueueException("The list doesn't contain value " + value);
        }
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        if(size == 0){
            throw new DoubleEndedQueueException("List is empty");
        } else if(size == 1){
            throw new DoubleEndedQueueException("List only has 1 item");
        }
        DequeNode<T> current = first.getNext();
        DequeNode<T> aux;
        int i = 1;
        while (i < size){
            if(comparator.compare(current.getItem(), current.getPrevious().getItem()) < 0) {
                if(current.getPrevious().isFirstNode()) {
                    aux = current.getPrevious();
                    aux.setNext(current.getNext());
                    current.setNext(aux);
                    current.setPrevious(aux.getPrevious());
                    aux.setPrevious(current);
                    first = current;
                    current = current.getNext();
                } else {
                    aux = current.getPrevious();
                    aux.setNext(current.getNext());
                    current.setNext(aux);
                    current.setPrevious(aux.getPrevious());
                    aux.setPrevious(current);
                    current = aux;
                }
            } else {
                i++;
                if(current.isLastNode() && (comparator.compare(current.getItem(), last.getItem()) != 0)) {
                    current = last;
                }
                current = current.getNext();
            }
        }
    }
}
