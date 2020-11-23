package lesson7;

import kotlin.NotImplementedError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    //T=R=O(m*n), m и n -длины строк;
    public static String longestCommonSubSequence(String first, String second) {
        int f = first.length();
        int s = second.length();
        int[][] matrix = new int[f + 1][s + 1];
        char[] firstWord = first.toCharArray();
        char[] secondWord = second.toCharArray();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i <= first.length(); i++) {
            matrix[i][0] = 0;
        }
        for (int j = 0; j <= second.length(); j++) {
            matrix[0][j] = 0;
        }
        for (int i = 1; i <= first.length(); i++) {
            for (int j = 1; j <= second.length(); j++) {
                if (firstWord[i - 1] == secondWord[j - 1]) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);
                }
            }
        }

        while (f > 0 && s > 0) {
            if (firstWord[f - 1] == secondWord[s - 1]) {
                res.append(firstWord[f - 1]);
                f--;
                s--;
            } else if (matrix[f - 1][s] == matrix[f][s]) {
                f--;
            } else s--;
        }
        return res.reverse().toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    // T = O(N^2);
    // R = O(N);
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        if(list.size() <= 1) return list;
        int size = list.size();
        int[] d = new int[size] ;// d[i]-длина наиб. возрастающей подпоследовательности,оканчивающейся в эл-те с индексом i
        int[] index = new int[size];// массив для восстановдления ответа,index[i]-индекс в исходном листе,при котором достигалось наиб.значение d[i]
        for(int i = 0; i < size; ++i){
            d[i] = 1;
            index[i] = -1;
            for(int j = 0; j < i ;++j){
                if(list.get(j) < list.get(i) && d[j] + 1  > d [i]){
                    d[i] = d[j] + 1;
                    index[i] = j;
                }
            }
        }
        int max = Arrays.stream(d).max().getAsInt();
        int position = 0;
        for( int i = 0; i < d.length; ++i){
            if(d[i] == max){
                position = i;
                break;
            }
        }
        List<Integer> subSequence = new ArrayList<>();
        while (position != -1){
            subSequence.add(0,list.get(position));
            position = index[position];
        }
        return subSequence;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
