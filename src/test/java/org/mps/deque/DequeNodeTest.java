/**
 * Test cases:
 * when a node is terminal
 * 1. [].isFirstNode() -> True
 * 2. [].isLastNode() -> True
 *
 * when a node is not terminal
 * 3. [].isNotTerminalNode() -> True
 * 4. [].setNewItem(15) -> 15
 * 5. [].setNewPrev(DequeNode<Integer> newPrev) -> DequeNode<Integer> newPrev
 * 6. []setNewNext(DequeNode<Integer> newPNext) -> DequeNode<Integer> newNext
 */

package org.mps.deque;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for class {@link DequeNodeTest}
 *
 *  @author Carlos Garcia Moya
 *  @author David Villaseca Pareja
 */

@DisplayName("A node")

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
        void isLastEmpty() { assertTrue(list.isLastNode()); }
    }


    @Nested
    @DisplayName("When prev or next are not terminal")
    class whenNotTerminal {
        @BeforeEach
        void setUp() {
            item = 5;
            DequeNode<Integer> prev2 = new DequeNode<>(item,null,prev);
            prev = new DequeNode<>(item,prev2,list);
            DequeNode<Integer> next2 = new DequeNode<>(item,next,null);
            next = new DequeNode<>(item,list,next2);
            list = new DequeNode<>(5,prev,next);
        }


        @Test
        @DisplayName("is not a terminal node")
        void isNotTerminalNode() {
            assertTrue(list.isNotATerminalNode());
        }


        @Test
        @DisplayName("set new item")
        void setNewItem() {
            Integer newItem = 15;
            list.setItem(newItem);
            Integer expectedItem = newItem;
            Integer actualItem = list.getItem();
            assertEquals(expectedItem,actualItem);
        }


        @Test
        @DisplayName("set new item")
        void setNewPrev() {
            DequeNode<Integer> newPrev = new DequeNode<>(item, null,list);
            list.setPrevious(newPrev);
            DequeNode<Integer> expectedItem = newPrev;
            DequeNode<Integer> actualItem = list.getPrevious();
            assertEquals(expectedItem,actualItem);
        }


        @Test
        @DisplayName("set new item")
        void setNewNext() {
            DequeNode<Integer> newNext = new DequeNode<>(item, list,null);
            list.setNext(newNext);
            DequeNode<Integer> expectedItem = newNext;
            DequeNode<Integer> actualItem = list.getNext();
            assertEquals(expectedItem,actualItem);
        }




    }


}