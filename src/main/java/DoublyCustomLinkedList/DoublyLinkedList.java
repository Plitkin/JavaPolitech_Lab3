package DoublyCustomLinkedList;

import CustomLinkedList.InterfaceList;

import java.util.Collection;
import java.util.Iterator;

public class DoublyLinkedList implements InterfaceList, Iterable<Integer> {
    private Node head;
    private Node tail;

    // Класс Node для представления узла в двусвязном списке
    private static class Node {
        int data;
        Node prev;
        Node next;

        public Node(Node prev, int data, Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    // Добавление значения в начало списка
    public void addToFront(int data) {
        Node temp = new Node(null, data, null);
        if (head == null) {
            head = tail = temp;
        } else {
            head.prev = temp;
            temp.next= head;
            head=temp;
        }
    }

    // Извлечение значения из начала списка без его удаления
    public int returnFront() {
        if (head == null) {
            System.out.println("List is empty");
            return -1; // Используем -1 как индикатор пустого списка
        }
        return head.data;
    }

    // Извлечение значения из начала списка с удалением из списка
    public int returnFrontAndRemove() {
        if (head == null) {
            System.out.println("List is empty");
            return -1;
        }
        int data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        return data;
    }

    // добавление значения в конец списка
    @Override
    public void addToLast(int data){
        Node temp = new Node(null, data, null);
        if(head == null){
            head = tail = temp;
        }else {
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
        }
    }

    // извлечение значения из конца списка без его удаления
    @Override
    public int returnLast() {
        if (tail == null) {
            System.out.println("List is empty");
            return -1;
        }
        return tail.data;
    }

    // извлечение значения из конца списка с удалением
    @Override
    public int returnLastAndRemove() {
        if (tail == null) {
            System.out.println("List is empty");
            return -1;
        }
        int data = tail.data;
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        return data;
    }

    // определение, содержит ли список заданное значение
    @Override
    public boolean contains(int value) {
        Node temp = head;
        while (temp != null) {
            if (temp.data == value) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    // определение, является ли список пустым
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    // удаление заданного значения из списка
    @Override
    public void removeValue(int data) {
        Node temp = head;
        Node tempPrev = null;

        while (temp != null) {
            if (temp.data == data) {
                if (tempPrev != null) {
                    tempPrev.next = temp.next;
                    if (temp.equals(tail)) {
                        tail = tempPrev;
                    }
                } else {
                    head = temp.next;
                }
                if (head == null) {
                    tail = null;
                }
            } else {
                tempPrev = temp;
            }
            temp = temp.next;
        }
    }

    // Добавление всех значений заданного массива в начало списка
    public void addAllToFront(int[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            addToFront(array[i]);
        }
    }

    // Добавление всех значений заданной коллекции в начало списка
    public void addAllToFront(Collection<Integer> collection) {
        for (Integer value : collection) {
            addToFront(value);
        }
    }

    // Добавление всех значений заданного массива в конец списка
    public void addAllToEnd(int[] array) {
        for (int value : array) {
            addToLast(value);
        }
    }

    // Добавление всех значений заданной коллекции в конец списка
    public void addAllToEnd(Collection<Integer> collection) {
        for (Integer value : collection) {
            addToLast(value);
        }
    }

    // Очистка списка
    public void clear() {
        head = null;
        tail = null;
    }

    // Поглощение списка другим списком с добавлением в начало текущего списка
    public void absorbToStart(DoublyLinkedList otherList) {
        if (otherList.head != null) {
            otherList.tail.next = head;
            if (head != null) {
                head.prev = otherList.tail;
            } else {
                tail = otherList.tail;
            }
            head = otherList.head;
            otherList.clear();
        }
    }

    // Поглощение списка другим списком с добавлением в конец текущего списка
    public void absorbToEnd(DoublyLinkedList otherList) {
        if (otherList.head != null) {
            otherList.head.prev = tail;
            if (tail != null) {
                tail.next = otherList.head;
            } else {
                head = otherList.head;
            }
            tail = otherList.tail;
            otherList.clear();
        }
    }

    // Печать всех значений списка в прямом порядке
    @Override
    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        Node temp = head;
        StringBuilder sb = new StringBuilder("[");
        while (temp != null) {
            sb.append(temp.data);
            sb.append(temp.next != null ? ", " : "");
            temp = temp.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // Печать всех значений списка в обратном порядке
    public void printBackward() {
        System.out.println(this.toStringBackward());
    }

    public String toStringBackward() {
        Node temp = tail;
        StringBuilder sb = new StringBuilder("[");
        while (temp != null) {
            sb.append(temp.data);
            sb.append(temp.prev != null ? ", " : "");
            temp = temp.prev;
        }
        sb.append("]");
        return sb.toString();
    }

    public interface LinkedListAction {
        void execute(int value);
    }

    // Выполнение действия для каждого значения списка в прямом порядке
    public void forEachForward(LinkedListAction action) {
        Node temp = head;
        while (temp != null) {
            action.execute(temp.data);
            temp = temp.next;
        }
    }

    // Выполнение действия для каждого значения списка в обратном порядке
    public void forEachBackward(LinkedListAction action) {
        Node temp = tail;
        while (temp != null) {
            action.execute(temp.data);
            temp = temp.prev;
        }
    }

    // Реализация Iterable для удобства работы с циклом foreach
    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private Node temp = head;

            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public Integer next() {
                int value = temp.data;
                temp = temp.next;
                return value;
            }
        };
    }
}

