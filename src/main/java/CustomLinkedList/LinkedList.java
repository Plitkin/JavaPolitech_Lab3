package CustomLinkedList;

import java.util.function.IntUnaryOperator;

public class LinkedList implements InterfaceList{

    private Node head;
    private Node tail;

    private static class Node {
        Node next;
        int data;

        public Node(Node next, int data){
            this.next=next;
            this.data=data;
        }
    }

    // добавление значения в начало списка
    @Override
    public void addToFront(int data){
        Node temp = new Node(null, data);
        if(head == null){
            head = tail = temp;
        }else {
            temp.next = head;
            head = temp;
        }
    }

    // извлечение значения из начала списка без его удаления
    @Override
    public int returnFront(){
        if (head == null) {
            System.out.println("List is empty");
            return -1; // Используем -1 как индикатор пустого списка
        }
        return head.data;
    }

    // извлечение значения из начала списка с удалением из списка
    @Override
    public int returnFrontAndRemove(){
        if (head == null) {
            System.out.println("List is empty");
            return -1; // Используем -1 как индикатор пустого списка
        }
        int data = head.data;
        head = head.next;
        return data;
    }

    // добавление значения в конец списка
    @Override
    public void addToLast(int data){
        Node temp = new Node(null, data);
        if(head == null){
            head = tail = temp;
        }else {
            tail.next = temp;
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
        if (head == null) {
            System.out.println("List is empty");
            return -1;
        }
        if (head.next == null) {
            int data = head.data;
            head = null;
            return data;
        }
        Node temp = head;
        Node tempPrev = null;
        while (temp.next != null) {
            tempPrev = temp;
            temp = temp.next;
        }
        tempPrev.next = null;
        return temp.data;
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

    // печать всех значений списка
    @Override
    public void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node temp = head;
        while (temp!=null){
            sb.append(temp.data);
            sb.append(temp.next != null ? ", " : "");
            temp = temp.next;
        }
        sb.append("]");
        return sb.toString();
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

    // выполнение действия для каждого значения из списка
    public void forEach(IntUnaryOperator action) {
        Node current = head;
        while (current != null) {
            current.data = action.applyAsInt(current.data);
            current = current.next;
        }
    }
}
