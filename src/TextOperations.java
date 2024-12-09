import java.util.Scanner;

/**
 * Лабораторна робота №2
 *
 * Виконав: Овер'янов Богдан, ст. групи ІК-24, ФІОТ
 *
 * Варіант №11
 *
 * Створити клас, який складається з виконавчого методу, що виконує дію з текстовим рядком
 * (Із заданого тексту видалити всі слова визначеної довжини, що починаються з приголосної літери.)
 * використовуючи для цього тип даних StringBuffer. Необхідно обробити всі виключні ситуації, що
 * можуть виникнути під час виконання програмного коду. Всі змінні повинні бути описані та
 * значення їх задані у виконавчому методі.
 */
public class TextOperations {
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in); // Ініціалізація сканеру для вводу з консолі
            String inputText; // Змінна для тексту, що отримаємо з консолі
            int wordLength; // Довжина слів, які будем видалять

            // Оскільки в завданні проситься "Всі змінні повинні бути
            // описані та значення їх задані у виконавчому методі.", то я
            // реалізував вибір, чи вводити дані вручну, чи використати задані.

            // Вибираємо тип даних (задати вручну чи використати заготовані)
            while (true) {
                System.out.println("Оберіть опцію:");
                System.out.println("1. Задати дані вручну");
                System.out.println("2. Використати заздалегідь заготовані дані");

                String choice = scanner.nextLine();

                // Якщо вибираємо задати дані вручну, перевіряємо
                // щоб введений текст не був порожнім
                if (choice.equals("1")) {
                    while (true) {
                        System.out.println("Введіть текст для обробки (україською або англійською):");
                        inputText = scanner.nextLine();
                        if (inputText.isEmpty()) {
                            System.out.println("Текст не може бути порожнім. Спробуйте ще раз.");
                        } else {
                            break;
                        }
                    }

                    // Отримуємо значення довжини для перевірки слів та
                    // перевіряємо, чи є число цілим додатнім значенням
                    while (true) {
                        System.out.println("Введіть довжину слова для видалення:");
                        String lengthInput = scanner.nextLine();
                        try {
                            wordLength = Integer.parseInt(lengthInput);
                            if (wordLength > 0) {
                                break;
                            } else {
                                System.out.println("Довжина слова повинна бути додатнім цілим числом. Спробуйте ще раз.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Будь ласка, введіть коректне додатнє ціле число.");
                        }
                    }
                    break;

                    // Якщо обрано використання заданих значень, то записуємо в змінні готовий текст
                } else if (choice.equals("2")) {
                    inputText = "Серед безкінечних синьо-зелених обіймів літнього дня, коли сонце ніжно торкається золотого поля, а вітер тихо нашіптує секрети древніх дубів, природа сплітає своїм чарівним пензлем мозаїку спокою і гармонії, залишаючи нам лише загадку, що захована у кожному шелестінні листя та ніжності пелюсток квітів.";
                    wordLength = 5;
                    System.out.println("Використано заздалегідь задані дані: (довжина слова = " + wordLength + ", текст = "+ inputText + "\n");
                    break;

                } else {
                    System.out.println("Некоректний вибір. Спробуйте ще раз.");
                }
            }

            // Викликаємо метод для видалення слів
            StringBuffer textBuffer = new StringBuffer(inputText);
            StringBuffer resultBuffer = removeWords(textBuffer, wordLength);

            // Виводжу результат
            System.out.println("Оброблений текст:");
            System.out.println(resultBuffer);

            // Запитуєм, чи користувач хоче повторити виконання, чи завершити програму
            while (true) {
                System.out.println("Бажаєте повторити програму? (так/ні):");
                String userChoice = scanner.nextLine().toLowerCase();
                if (userChoice.equals("ні")) {
                    scanner.close();
                    return;
                } else if (userChoice.equals("так")) {
                    break;
                } else {
                    System.out.println("Некоректний вибір. Введіть 'так' або 'ні'.");
                }
            }
        }
    }

    /**
     * Видаляє слова заданої довжини, які починаються з приголосної літери.
     *
     * @param textBuffer - вхідний текст у вигляді StringBuffer
     * @param wordLength - довжина слова для видалення
     * @return - текст без слів заданої довжини, що починаються з приголосної
     */
    private static StringBuffer removeWords(StringBuffer textBuffer, int wordLength) {
        StringBuffer resultBuffer = new StringBuffer();
        // Я вирішив робити перевірку по голосним, бо їх просто значно менше, ніж приголосних.
        String letters = "aeiouAEIOUаеєиіїоуюяАЕЄИІЇОУЮЯ";
        int start = 0;

        for (int i = 0; i <= textBuffer.length(); i++) {
            // Розділяємо слова за пробілами, знаками пунктуації або досягненням кінця рядка
            // (друга умова це якраз перевірка, чи не є поточний символ літерою або цифрою).
            // Зробив так, бо за умовою все має бути через StringBuffer
            if (i == textBuffer.length() || !Character.isLetterOrDigit(textBuffer.charAt(i))) {
                if (start < i) {
                    StringBuffer word = new StringBuffer(textBuffer.substring(start, i));
                    char firstChar = word.charAt(0);

                    // Перевірка, чи слово не починається з голосної і має задану довжину
                    // Якщо починається з голосної чи/або має задану довжину то слово не видаляється
                    // (записується в фінальний баффер)
                    if (word.length() != wordLength || letters.indexOf(firstChar) != -1) {
                        resultBuffer.append(word);
                    }
                }
                // Додаємо розділовий символ
                if (i < textBuffer.length()) {
                    resultBuffer.append(textBuffer.charAt(i));
                }
                start = i + 1;
            }
        }

        return resultBuffer;
    }
}



