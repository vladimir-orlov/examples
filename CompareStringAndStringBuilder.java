public class CompareStringAndStringBuilder {
/*Задача
Выполнить в двух циклах многократное сложение cтрок в String и StringBuilder, сравнить
скорости выполнения.

Вопрос
 Время исполнения второго цикла стабильно меньше,
 но каждый раз слишком большие скачки значений (в обоих циклах).
 Почему так происходит? Что-то вычитала про понятие "разогрева" в java, но все еще не до конца ясно

Ответ
    Причина, это маленький промежуток времени. При использовании java.lang.System.nanoTime мы оперируем наносекундами,
    и в таком случае мы тестируем все что угодно, включая фазу луны, но только не наш алгоритм. Если бы работали на очень
    старом компьютере, то эксперимент мог бы получиться.
    В нашем же случае нужно увеличивать количество итераций, либо взять за основу большой текст и посимвольно добавлять его в String или StringBuilder.
 */


    public static void main(String[] args) {
        System.out.println("время исполнения: " + getTime(() -> checkString()) / 1_000_000 + " милисекунд");
        System.out.println("время исполнения: " + getTime(() -> checkStringBuilder()) / 1_000_000 + " милисекунд");
    }

    private static void checkString() {
        String str = "";
        for (int i = 0; i < 255; i++) {
            str += (char)i;
        }
    }

    private static void checkStringBuilder() {
        StringBuilder strB = new StringBuilder();
        for (int i = 0; i < 255; i++) {
            strB.append((char)i);
        }
    }

    private static long getTime(Runnable runnable) {
        long begin;

        begin = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            runnable.run();
        }

        return System.nanoTime() - begin;
    }
}
