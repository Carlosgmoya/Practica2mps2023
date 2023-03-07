package org.mps.deque;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("A no")
class DequeNodeTest {

    DequeNode<Integer> list;
    DequeNode<Integer> next;
    DequeNode<Integer> prev;
    Integer item;

    @Test
    @DisplayName("is instatiated with new DequeNode<T>()")
    void isInstatiatedWithNew() {
        new DequeNode<Integer>(item,prev,next);
    }

    @Nested
    @DisplayName("when prev or next are terminal")
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
        void isLastEmpty() { assertTrue(list.isFirstNode()); }

    }
    @Test
    @DisplayName("When prev or next are not terminal")
    void isNotTerminal() {
        DequeNode<Integer> prev3 = null;
        Integer item2 = 3;
        DequeNode<Integer> prev2 = new DequeNode<>(item2,prev3,list);
        Integer item3 = 6;
        DequeNode<Integer> next3 = null;
        DequeNode<Integer> next2 = new DequeNode<>(item3,list,next3);
        list = new DequeNode<>(item2, prev2,next2);
        assertTrue(list.isNotATerminalNode());
    }

}