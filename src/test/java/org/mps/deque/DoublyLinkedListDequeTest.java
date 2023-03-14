package org.mps.deque;

import org.junit.jupiter.api.*;

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
    @DisplayName("is instatiated with new DoublyLinkedListDeque<>()")
    void isInstatiatedWithNew() {
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
    }

}