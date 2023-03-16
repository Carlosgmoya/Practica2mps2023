package org.mps.deque;

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
        @DisplayName("is empty")
        void isEmpty(){
            int expectedValue = 0;
            int actualValue = list.size();
            assertEquals(expectedValue, actualValue);
        }

        @Nested
        @DisplayName("Prepend an element")
        class prependElement{
            @Test
            @DisplayName("Prepend an element to an empty list")
            void prependEmpty(){
                list.prepend(1);

                Integer expectedValue = 1;
                Integer actualValue = list.first();
                assertEquals(expectedValue, actualValue);
            }

            @Test
            @DisplayName("Prepend elements to a list")
            void prependNotEmpty(){
                list.prepend(3);
                list.prepend(2);
                list.prepend(1);

                Integer expectedValue = 1;
                Integer actualValue = list.first();
                assertEquals(expectedValue, actualValue);
            }

            @Test
            @DisplayName("Size increases with prepend")
            void sizePrepend(){
                Integer expectedValue = list.size() + 1;
                list.append(1);
                Integer actualValue = list.size();
                assertEquals(expectedValue, actualValue);
            }

        }

        @Nested
        @DisplayName("Append an element")
        class appendElement {
            @Test
            @DisplayName("Append an element to an empty list")
            void appendEmpty(){
                list.append(1);

                Integer expectedValue = 1;
                Integer actualValue = list.last();
                assertEquals(expectedValue, actualValue);
            }

            @Test
            @DisplayName("Append elements to a list")
            void appendNotEmpty(){
                list.append(3);
                list.append(2);
                list.append(1);

                Integer expectedValue = 1;
                Integer actualValue = list.last();
                assertEquals(expectedValue, actualValue);
            }

            @Test
            @DisplayName("Size increases with append")
            void sizeAppend(){
                Integer expectedValue = list.size() + 1;
                list.append(1);
                Integer actualValue = list.size();
                assertEquals(expectedValue, actualValue);
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
            @DisplayName("size decreases whit delete")
            void sizeControl(){
                list.append(1);
                list.append(2);
                list.append(3);


                Integer expectedValue = 2;
                list.deleteFirst();
                Integer actualValue = list.size();
                assertEquals(expectedValue, actualValue);
            }


            @Test
            @DisplayName("delete first element from a list")
            void deleteFirstElement(){
                list.append(1);
                list.append(2);
                list.append(3);


                Integer expectedValue = 2;
                list.deleteFirst();
                Integer actualValue = list.first();
                assertEquals(expectedValue, actualValue);
            }

            @Test
            @DisplayName("delete last element from a list")
            void deleteLastElement(){
                list.append(1);
                list.append(2);
                list.append(3);

                Integer expectedValue = 2;
                list.deleteLast();
                Integer actualValue = list.last();
                assertEquals(expectedValue, actualValue);
            }

            @Test
            @DisplayName("delete the only element left in a list with deleteFirst()")
            void deleteOnlyOneFirst(){
                list.append(1);

                list.deleteFirst();
                assertEquals(list.size(), 0);
            }

            @Test
            @DisplayName("delete the only element left in a list with deleteLast()")
            void deleteOnlyOneLast(){
                list.append(1);

                list.deleteLast();
                assertEquals(list.size(), 0);
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
                assertFalse(list.contains(5));
                assertFalse(list.contains(24));
                assertFalse(list.contains(9));
            }

            @Test
            @DisplayName("contains element")
            void ContainsElementTest() {
                list.append(1);
                list.append(2);
                list.append(3);
                assertTrue(list.contains(1));
                assertTrue(list.contains(2));
                assertTrue(list.contains(3));
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