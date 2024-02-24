package ExpandedLinkedList;


public class Main {

    public static void main(String[] args) {
        ExpandedLinkedList list = new ExpandedLinkedList();

        // Добавляем элементы в список
        list.addToFront(1);
        list.addToLast(2);
        list.addToLast(3);
        list.addToLast(4);
        list.addToLast(-1);
        list.addToLast(5);
        list.addToLast(6);
        list.addToLast(7);
        list.addToLast(8);
        list.addToLast(9);
        list.addToLast(-1);
        list.addToLast(10);
        list.addToLast(11);
        list.addToLast(-1);
        list.addToLast(12);
        //Умышленно создаем лишние элементы "-1" для последующего удаления


        System.out.println("Исходный список:");
        list.print();

        // Удаляем элементы из списка
        list.returnFrontAndRemove();
        list.returnLastAndRemove();

        System.out.println("Список после удаления первого и последнего элемента:");
        list.print();

        // Добавляем массив значений в начало и конец списка
        int[] valuesToAddToFront = {-1, 0, 1};
        list.addAllToFront(valuesToAddToFront);

        int[] valuesToAddToLast = {12, 13, 14};
        list.addAllToLast(valuesToAddToLast);

        System.out.println("Список после добавления значений в начало и конец:");
        list.print();

        // Создаём второй список и добавляем в него элементы
        ExpandedLinkedList secondList = new ExpandedLinkedList();
        secondList.addToLast(15);
        secondList.addToLast(16);

        // Поглощаем второй список первым
        list.absorbAtEnd(secondList);

        System.out.println("Первый список после поглощения второго:");
        list.print();

        // Применяем действие ко всем элементам списка
        System.out.println("Применяем действие к каждому элементу списка:");
        list.forEach(value -> System.out.print(value + 1 + " "));

        System.out.println("\nПуст ли список? " + list.isEmpty());

        // Удаляем элемент из списка
        list.removeValue(-1);
        System.out.println("Список после удаления элемента -1:");
        list.print();

        // Очищаем список
        list.clear();
        System.out.println("Пуст ли список после очистки? " + list.isEmpty());

    }
}