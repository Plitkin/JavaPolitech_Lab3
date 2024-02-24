package ExpandedLinkedList;

import CustomLinkedList.InterfaceList;

public class ExpandedLinkedList implements InterfaceList {
    private static final int NODE_CAPACITY = 10; // Размер массива в каждом узле равен 10
    private Node head;
    private Node tail;

    private static class Node {
        int[] values = new int[NODE_CAPACITY];
        int count = 0;
        Node next;

        Node(int value) {
            values[count++] = value;
        }
    }

    // Добавление значения в начало списка
    @Override
    public void addToFront(int data) {
        if (head == null || head.count == NODE_CAPACITY) {
            Node temp = new Node(NODE_CAPACITY);
            temp.values[0] = data;
            temp.count = 1;
            // Если список не пуст, новый узел становится головой списка
            if (head != null) {
                temp.next = head;
            } else {
                // Если список был пуст, новый узел также становится хвостом
                tail = temp;
            }
            head = temp;
            // Вызываем перераспределение элементов для всего списка
            redistributeElements();
        } else {
            // Если в первом узле есть место, добавляем элемент в начало узла
            System.arraycopy(head.values, 0, head.values, 1, head.count);
            head.values[0] = data;
            head.count++;
        }
    }

    // Извлечение значения из начала списка без его удаления
    @Override
    public int returnFront() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return -1; // Используем -1 как индикатор пустого списка
        }
        return head.values[0];
    }

    // Извлечение значения из начала списка с удалением из списка
    @Override
    public int returnFrontAndRemove() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return -1;
        }
        int value = head.values[0];
        System.arraycopy(head.values, 1, head.values, 0, head.count - 1);
        head.count--;
        if (head.count == 0) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
        } else {
            redistributeElements();
        }
        return value;
    }

    // Добавление значения в конец списка
    @Override
    public void addToLast(int data) {
        if (tail == null) {
            head = new Node(data);
            tail = head;
        } else {
            if (tail.count < NODE_CAPACITY) {
                tail.values[tail.count++] = data;
            } else {
                Node temp = new Node(data);
                tail.next = temp;
                tail = temp;
            }
        }
    }

    // Извлечение значения из конца списка без его удаления
    @Override
    public int returnLast() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return -1;
        }
        return tail.values[tail.count - 1];
    }

    // Извлечение значения из конца списка c удаления
    @Override
    public int returnLastAndRemove() {
        if (isEmpty()) {
            System.out.println("List is empty");
            return -1;
        }
        Node last = tail;
        int value = last.values[last.count - 1];
        last.count--;
        if (last.count == 0) {
            if (head == tail) {
                head = tail = null;
            } else {
                Node temp = head;
                while (temp.next != tail) {
                    temp = temp.next;
                }
                tail = temp;
                tail.next = null;
            }
        } else {
            redistributeElements();
        }
        return value;
    }

    // Определение, содержит ли список заданное значение
    @Override
    public boolean contains(int value) {
        Node temp = head;
        while (temp != null) {
            for (int i = 0; i < temp.count; i++) {
                if (temp.values[i] == value) {
                    return true;
                }
            }
            temp = temp.next;
        }
        return false;
    }

    // Определение, является ли список пустым
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    //Метод для печати всех значений
    @Override
    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        Node temp = head;
        StringBuilder sb = new StringBuilder("[");
        while (temp != null) {
            sb.append("[");
            for (int i = 0; i < temp.count; i++) {
                sb.append(temp.values[i]);
                if (i < temp.count - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            if (temp.next != null) {
                sb.append(", ");
            }
            temp = temp.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // Удаление заданного значения из списка
    @Override
    public void removeValue(int data) {
        Node temp = head;
        boolean needRedistribution = false;
        while (temp != null) {
            for (int i = 0; i < temp.count; ) {
                if (temp.values[i] == data) {
                    System.arraycopy(temp.values, i + 1, temp.values, i, temp.count - i - 1);
                    temp.count--;
                    needRedistribution = true;
                } else {
                    i++;
                }
            }
            temp = temp.next;
        }
        if (needRedistribution) {
            redistributeElements();
        }
    }

    //Метод для перераспределения элементов
    private void redistributeElements() {
        Node temp = head;
        while (temp != null && temp.next != null) {
            // Пока в текущем узле есть место и в следующем узле есть элементы
            while (temp.count < NODE_CAPACITY && temp.next.count > 0) {
                // Перемещаем первый элемент из следующего узла в текущий узел
                temp.values[temp.count++] = temp.next.values[0];
                // Сдвигаем элементы в следующем узле
                System.arraycopy(temp.next.values, 1, temp.next.values, 0, temp.next.count - 1);
                // Уменьшаем количество элементов в следующем узле
                temp.next.count--;
            }
            // Если после перераспределения следующий узел оказался пуст, удаляем его из списка
            if (temp.next.count == 0) {
                temp.next = temp.next.next;
                // Если это был последний узел, обновляем ссылку на хвост списка
                if (temp.next == null) {
                    tail = temp;
                }
            }
            // Переходим к следующему узлу для продолжения перераспределения
            temp = temp.next;
        }
    }

    // Добавление всех значений массива в начало списка
    public void addAllToFront(int[] values) {
        for (int i = values.length - 1; i >= 0; i--) {
            addToFront(values[i]);
        }
    }

    // Добавление всех значений массива в конец списка
    public void addAllToLast(int[] values) {
        for (int value : values) {
            addToLast(value);
        }
    }

    // Поглощение списка другим развёрнутым списком в начало
    public void absorbAtStart(ExpandedLinkedList other) {
        Node temp = other.tail;
        while (temp != null) {
            int[] reverseValues = new int[temp.count];
            System.arraycopy(temp.values, 0, reverseValues, 0, temp.count);
            for (int i = reverseValues.length - 1; i >= 0; i--) {
                addToFront(reverseValues[i]);
            }
            temp = temp.next;
        }
        other.clear();
    }

    // Поглощение списка другим развёрнутым списком в конец
    public void absorbAtEnd(ExpandedLinkedList other) {
        Node temp = other.head;
        while (temp != null) {
            for (int i = 0; i < temp.count; i++) {
                addToLast(temp.values[i]);
            }
            temp = temp.next;
        }
        other.clear();
    }

    public void forEach(IntConsumer action) {
        Node temp = head;
        while (temp != null) {
            for (int i = 0; i < temp.count; i++) {
                action.accept(temp.values[i]);
            }
            temp = temp.next;
        }
    }

    // Очистка списка
    public void clear() {
        head = null;
        tail = null;
    }

    // Интерфейс для forEach
    @FunctionalInterface
    public interface IntConsumer {
        void accept(int value);
    }
}