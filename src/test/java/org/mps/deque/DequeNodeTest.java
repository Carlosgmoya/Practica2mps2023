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
    @DisplayName("is instatiated with new DequeNodeTest<>()")
    void isInstatiatedWithNew() {
        new DequeNode<Integer>(item,prev,next);
    }

    @Nested
    @DisplayName("when prev and next are terminal")
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

    @Test
    @DisplayName("when prev and next are no terminal")
    void isNotATerminalNode() {
        item = 5;
        DequeNode<Integer> prev2 = new DequeNode<>(item,null,prev);
        prev = new DequeNode<>(item,prev2,list);
        DequeNode<Integer> next2 = new DequeNode<>(item,next,null);
        next = new DequeNode<>(item,list,next2);
        list = new DequeNode<>(item,prev,next);
        assertTrue(list.isNotATerminalNode());

    }

}