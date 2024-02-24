package DoublyCustomLinkedList;

public class Main {

    public static void main(String[] args) {

        DoublyLinkedList list = new DoublyLinkedList();

        // Добавляем значения в начало списка
        list.addToFront(3);
        list.addToFront(2);
        list.addToFront(1);

        // Добавляем значения в конец списка
        list.addToLast(4);
        list.addToLast(5);

        // Печатаем список в прямом порядке
        System.out.print("Список (прямой порядок): ");
        list.print(); // 1 2 3 4 5

        // Печатаем список в обратном порядке
        System.out.print("Список (обратный порядок): ");
        list.printBackward(); // 5 4 3 2 1

        // Извлекаем значение из начала списка
        int frontValue = list.returnFrontAndRemove();
        System.out.println("Удалено из начала: " + frontValue);

        // Извлекаем значение из конца списка
        int endValue = list.returnLastAndRemove();
        System.out.println("Удаленно с конца: " + endValue);

        // Печатаем список после извлечений
        System.out.print("Список после удаления: ");
        list.print(); // 2 3 4

        // Проверяем наличие значения в списке
        int valueToCheck = 3;
        System.out.println("Список содержит " + valueToCheck + ": " + list.contains(valueToCheck));

        // Добавляем массив значений в начало списка
        int[] arrayToAddToFront = {9, 8, 7};
        list.addAllToFront(arrayToAddToFront);

        // Печатаем список после добавления массива в начало
        System.out.print("Список после добавления массива в начало: ");
        list.print(); // 7 8 9 2 3 4

        // Создаем новый список и добавляем его в конец текущего списка
        DoublyLinkedList secondList = new DoublyLinkedList();
        secondList.addToLast(10);
        secondList.addToLast(11);
        list.absorbToEnd(secondList);

        // Печатаем список после поглощения второго списка в конец
        System.out.print("Список после присоединения второго списка в конец: ");
        list.print(); // 7 8 9 2 3 4 10 11

        // Выполняем действие для каждого значения в прямом порядке
        System.out.print("Выполнение действия для каждого элемента (прямой порядок): ");
        list.forEachForward(value -> System.out.print(value * 2 + " "));
    }
}