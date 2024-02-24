package CustomLinkedList;

public class Main{

    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        // Добавляем значения в начало списка
        list.addToFront(3);
        list.addToFront(2);
        list.addToFront(1);

        // Выводим список
        System.out.print("Список: ");
        list.print(); // 1 2 3

        System.out.println("Первый элемент: " + list.returnFront()); // 1
        System.out.println("Удалено из начала: " + list.returnFrontAndRemove()); // 1
        System.out.print("Список: ");
        list.print(); // 2 3

        // Добавляем значения в конец списка
        list.addToLast(4);
        list.addToLast(5);
        System.out.print("Список после добавления в конец: ");
        list.print(); // 2 3 4 5

        System.out.println("Последний элемент: " + list.returnLast()); // 5
        System.out.println("Удалено с конца: " + list.returnLastAndRemove()); // 5
        System.out.print("Список: ");
        list.print(); // 2 3 4

        System.out.println("Содержит 3: " + list.contains(3)); // true
        System.out.println("Содержит 6: " + list.contains(6)); // false

        System.out.println("Список пуст: " + list.isEmpty()); // false

        // Удаляем значение
        list.removeValue(3);
        System.out.print("Удаление элемента 3: ");
        list.print(); // 2 4

        // Выполняем действие для всех значений списка
        list.forEach(value -> value * 2);
        System.out.print("Список после действия: ");
        list.print(); // 4 8
    }
}
