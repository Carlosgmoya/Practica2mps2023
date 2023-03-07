package org.mps.deque;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("A list")
class DequeNodeTest {

    DequeNode<Integer> list;
    DequeNode<Integer> next;
    DequeNode<Integer> prev;
    Integer item;

    @Test
    @DisplayName("is instatiated with new DoublyLinkedListDeque<>()")
    void isInstatiatedWithNew() {
        new DequeNode<Integer>(item,prev,next);
    }

    @Nested
    @DisplayName("when new")
    class whenNew {
        @BeforeEach
        void setUp() {
            prev = null;
            next = null;
            item = 5;
            list = new DequeNode<>(5,prev,next);
        }
        @Test
        @DisplayName("is first node empty")
        void isFirstEmpty() {
            assertTrue(list.isFirstNode());
        }
        @Test
        @DisplayName("is last node empty")
        void isLastEmpty() {
            assertTrue(list.isFirstNode());
        }
    }

}