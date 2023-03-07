package org.mps.deque;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
@DisplayName("A list")
class DoublyLinkedListDequeTest {

    DoublyLinkedListDeque<Integer> list;
    @Test
    @DisplayName("is instatiated with new DoublyLinkedListDeque<>()")
    void isInstatiatedWithNew() {
        new DoublyLinkedListDeque<Integer>();
    }

    @Nested
    @DisplayName("when new")
    class whenNew {
        @BeforeEach
        void setUp() {
            list = new DoublyLinkedListDeque<>();
        }
    }

}