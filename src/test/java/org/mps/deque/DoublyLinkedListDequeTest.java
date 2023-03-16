package org.mps.deque;

/*
    Test cases:
    When creating a new DoublyLinkedListDequeue
    1. [].first() -> Throws DoubleEndedQueueException
    2. [].last() -> Throws DoubleEndedQueueException
    3. [].size() -> 0

    cases for prepend()
    4. [].prepend(1) -> [1]
    5. [].prepend() 3 integers - > [1 -> 2 -> 3]
    6. [].prepend(1).size -> 1

    cases for append()
    7. [].apend(1) -> [1]
    8. [].apend() 3 integers - > [3 -> 2 -> 1]
    9. [].apend(1).size -> 1

    cases for deleteFirst() and deleteLast()
    10. [].DeleteFirst() || emptyList.DeleteLast() -> Throws DoubleEndedQueueException
    11. [1 -> 2 -> 3].deleteFirst().size() -> 2
    12. [1 -> 2 -> 3].deleteFirst() -> [2 -> 3]
    13. [1 -> 2 -> 3].deleteLast() -> [1 -> 2]
    14. [1].deleteFirst() -> []
    15. [1].deleteLast() -> []

    cases for get()
    16. [].get(100) -> Throws IndexOutOfBoundsException
    17. [1 -> 2 -> 3].get(0) -> 1
        [1 -> 2 -> 3].get(1) -> 2
        [1 -> 2 -> 3].get(2) -> 3

    cases for contain()
    18. [1 -> 2 -> 3].contains(24) -> False
    19. [1 -> 2 -> 3].contains(2) -> True

    cases for remove()
    20. [].remove(1) -> Throws DoubleEndedQueueException
    21. [1 -> 2 -> 3].remove(100) -> Throws DoubleEndedQueueException
    22. [1 -> 2 -> 3].remove(1).first() -> 2
    23. [1 -> 2 -> 3].remove(3).last() -> 2
    24. [1 -> 2 -> 3].remove(2).first() -> [1 -> 3]
    25. [1 -> 2 -> 3].remove(1).size() -> 2

    cases for sort()
    26. [].sort(Comparator.naturalOrder()) -> Throws DoubleEndedQueueException
    27. [1].sort(Comparator.naturalOrder()) -> Throws DoubleEndedQueueException
    28. [4 -> 3 -> 2 -> 1].sort(Comparator.naturalOrder()) -> [1 -> 2 -> 3 -> 4]
 */

import org.junit.jupiter.api.*;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for class {@link DoublyLinkedListDeque}
 *
 *  @author Carlos Garcia Moya
 *  @author David Villaseca Pareja
 */
@DisplayName("A list")
class DoublyLinkedListDequeTest {

    DoublyLinkedListDeque<Integer> list;
    @Test
    @DisplayName("is instantiated with new DoublyLinkedListDeque<>()")
    void isInstantiatedWithNew() {
        new DoublyLinkedListDeque<Integer>();
    }

    @Nested
    @DisplayName("When new")
    class whenNew {
        @BeforeEach
        void setUp() {
            list = new DoublyLinkedListDeque<Integer>();
        }

        @Test
        @DisplayName("there isn't a first item")
        void noFirst(){
            assertThrows(DoubleEndedQueueException.class, () -> list.first());
        }
        @Test
        @DisplayName("there isn't a last item")
        void noLast(){
            assertThrows(DoubleEndedQueueException.class, () -> list.last());
        }
        @Test
        @DisplayName("size 0 when list is empty")
        void isEmpty(){
            assertEquals(0, list.size());
        }

        @Nested
        @DisplayName("Prepend an element")
        class prependElement{
            @Test
            @DisplayName("Prepend an element to an empty list")
            void prependEmpty(){
                list.prepend(1);

                assertEquals(1, list.first());
            }

            @Test
            @DisplayName("Prepend elements to a list")
            void prependNotEmpty(){
                list.prepend(3);
                list.prepend(2);
                list.prepend(1);

                assertEquals(1, list.first());
                assertEquals(2, list.get(1));
                assertEquals(3, list.last());
            }

            @Test
            @DisplayName("Size increases with prepend")
            void sizePrepend(){
                Integer expectedValue = list.size() + 1;
                list.append(1);
                assertEquals(expectedValue, list.size());
            }

        }

        @Nested
        @DisplayName("Append an element")
        class appendElement {
            @Test
            @DisplayName("Append an element to an empty list")
            void appendEmpty(){
                list.append(1);
                assertEquals(1, list.last());
            }

            @Test
            @DisplayName("Append elements to a list")
            void appendNotEmpty(){
                list.append(3);
                list.append(2);
                list.append(1);

                assertEquals(1, list.last());
                assertEquals(2, list.get(1));
                assertEquals(3, list.first());
            }

            @Test
            @DisplayName("Size increases with append")
            void sizeAppend(){
                Integer expectedValue = list.size() + 1;
                list.append(1);
                assertEquals(expectedValue, list.size());
            }

        }

        @Nested
        @DisplayName("Delete elements")
        class deleteElement{
            @Test
            @DisplayName("throw exception when deleting from an empty list")
            void deleteException(){
                assertAll(
                        "Test thrown exceptions",
                        () -> assertThrows(DoubleEndedQueueException.class, () -> list.deleteFirst()),
                        () -> assertThrows(DoubleEndedQueueException.class, () -> list.deleteLast()));
            }

            @Test
            @DisplayName("size decreases whit deleteFirst")
            void sizeControlFirst(){
                list.append(1);
                list.append(2);
                list.append(3);


                list.deleteFirst();
                assertEquals(2, list.size());
            }

            @Test
            @DisplayName("size decreases whit deleteLast")
            void sizeControlLast(){
                list.append(1);
                list.append(2);
                list.append(3);


                list.deleteLast();
                assertEquals(2, list.size());
            }


            @Test
            @DisplayName("delete first element from a list")
            void deleteFirstElement(){
                list.append(1);
                list.append(2);
                list.append(3);

                list.deleteFirst();
                assertEquals(2, list.first());
            }

            @Test
            @DisplayName("delete last element from a list")
            void deleteLastElement(){
                list.append(1);
                list.append(2);
                list.append(3);

                list.deleteLast();
                assertEquals(2, list.last());
            }

            @Test
            @DisplayName("delete the only element left in a list with deleteFirst()")
            void deleteOnlyOneFirst(){
                list.append(1);

                list.deleteFirst();
                assertEquals(0, list.size());
            }

            @Test
            @DisplayName("delete the only element left in a list with deleteLast()")
            void deleteOnlyOneLast(){
                list.append(1);

                list.deleteLast();
                assertEquals(0, list.size());
            }

        }
        @Nested
        @DisplayName("Get elements")
        class getElement{

            @Test
            @DisplayName("get exception")
            void getException() {assertAll("Index given out of bounds", () -> assertThrows(IndexOutOfBoundsException.class, () -> list.get(100)));}

            @Test
            @DisplayName("get the item of the given index")
            void getTest() {
                list.append(1);
                list.append(2);
                list.append(3);
                assertEquals(1,list.get(0));
                assertEquals(2,list.get(1));
                assertEquals(3,list.get(2));
            }


        }

        @Nested
        @DisplayName("Contains elements")
        class containsElement{

            @Test
            @DisplayName("does not contain element")
            void NotContainsElementTest() {
                list.append(1);
                list.append(2);
                list.append(3);
                assertFalse(list.contains(24));
            }

            @Test
            @DisplayName("contains element")
            void ContainsElementTest() {
                list.append(1);
                list.append(2);
                list.append(3);
                assertTrue(list.contains(2));
            }
        }

        @Nested
        @DisplayName("Remove an element from a list")
        class removeElement {
            @BeforeEach
            void setup(){
                list.append(1);
                list.append(2);
                list.append(3);
            }
            @Test
            @DisplayName("remove from an empty list")
            void removeEmptyList(){
                list.deleteFirst();
                list.deleteFirst();
                list.deleteFirst();
                assertThrows(DoubleEndedQueueException.class, () -> list.remove(1));
            }

            @Test
            @DisplayName("remove a value that isn't in the list")
            void removeNotInList(){
                assertThrows(DoubleEndedQueueException.class, () -> list.remove(100));
            }

            @Test
            @DisplayName("remove a value from the list that happens to be the first one")
            void removeFirstItem(){
                list.remove(1);
                assertEquals(2, list.first());
            }

            @Test
            @DisplayName("remove a value from the list that happens to be the last one")
            void removeLastItem(){
                list.remove(3);
                assertEquals(2, list.last());
            }

            @Test
            @DisplayName("remove a value from  list")
            void removeTest(){
                list.remove(2);
                assertEquals(1, list.get(0));
                assertEquals(3, list.get(1));
            }

            @Test
            @DisplayName("Size decreases when remove a value")
            void sizeAppend(){
                list.remove(1);
                assertEquals(2, list.size());
            }

        }

        @Nested
        @DisplayName("Sort list of elements")
        class sortElements {

            @Test
            @DisplayName("sort an empty list")
            void sortEmptyList(){
                assertThrows(DoubleEndedQueueException.class, () -> list.sort(Comparator.naturalOrder()));
            }

            @Test
            @DisplayName("sort a list with one element")
            void sortOnly1Item(){
                list.append(1);
                assertThrows(DoubleEndedQueueException.class, () -> list.sort(Comparator.naturalOrder()));
            }

            @Test
            @DisplayName("sort a list of integer")
            void sortList(){
                list.append(4);
                list.append(3);
                list.append(2);
                list.append(1);

                list.sort(Comparator.naturalOrder());

                assertEquals(1, list.first());
                assertEquals(2, list.get(1));
                assertEquals(3, list.get(2));
                assertEquals(4, list.last());
            }
        }
    }

}