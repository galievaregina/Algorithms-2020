package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    // T = NlogN;
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputName));
        String line = reader.readLine();
        List<Integer> listOfPrices = new ArrayList<>();
        while (line != null) {
            if (!Pattern.matches("\\d+", line)) throw new IllegalArgumentException();
            listOfPrices.add(Integer.parseInt(line));
            line = reader.readLine();
        }
        int[] price = Arrays.stream(listOfPrices.toArray(new Integer[0])).mapToInt(i -> i).toArray();
        Pair<Integer, Integer> result = null;
        int max = 0;
        for (int i = 0; i < price.length / 2; i++) {
            for (int j = i + 1; j < price.length / 2; j++) {
                if (price[j] - price[i] > max) {
                    max = price[j] - price[i];
                    result = new Pair<>(i + 1, j + 1);
                }
            }
        }
        for (int i = price.length / 2 + 1; i < price.length; i++) {
            for (int j = i + 1; j < price.length; j++) {
                if (price[j] - price[i] > max) {
                    max = price[j] - price[i];
                    result = new Pair<>(i + 1, j + 1);
                }
            }
        }
        for (int j = price.length / 2 + 1; j < price.length; j++) {
            for (int i = price.length / 2; i > 0; i--) {
                if (price[j] - price[i] > max) {
                    max = price[j] - price[i];
                    result = new Pair<>(i + 1, j + 1);
                }
            }
        }
        return result;
    }


    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     * <p>
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    //M,N-длины сравниваемых строк
    //Т=R=O(M*N)
    static public String longestCommonSubstring(String firs, String second) {
        int[][] matrix = new int[firs.length() + 1][second.length() + 1];
        char[] firstWord = firs.toCharArray();
        char[] secondWord = second.toCharArray();
        StringBuilder res = new StringBuilder();
        int max = 0;
        int coordinate = 0;
        for (int i = 0; i < firs.length(); i++) {
            for (int j = 0; j < second.length(); j++) {
                if (firstWord[i] == secondWord[j]) {
                    matrix[i + 1][j + 1] = matrix[i][j] + 1;
                    if (matrix[i + 1][j + 1] > max) {
                        coordinate = i;
                        max = matrix[i + 1][j + 1];
                    }
                }
            }
        }
        for (int k = coordinate - max + 1; k <= coordinate; k++) {
            res.append(firstWord[k]);
        }
        return res.toString();
    }

    /**
     * Число простых чисел в интервале
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    //T=O(NloglogN)
    //R=O(N)
    static public int calcPrimesNumber(int limit) {
        if (limit <= 1) return 0;
        boolean[] array = new boolean[limit + 1];
        Arrays.fill(array, true);
        array[0] = false;
        array[1] = false;
        int falseCount = 0;
        for (int i = 2; i * i <= limit; i++) {
            if (array[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    if (array[j]) {
                        array[j] = false;
                        falseCount++;
                    }
                }
            }
        }
        return limit - 1 - falseCount;
    }
}
