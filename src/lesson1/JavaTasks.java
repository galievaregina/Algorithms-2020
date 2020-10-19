package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     * <p>
     * Пример:
     * <p>
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */

    //Т=Nlog2(N);
    static public void sortTimes(String inputName, String outputName) throws IOException {
        List<Integer> amList = new ArrayList<>();
        List<Integer> pmList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputName))) {
            String line = reader.readLine();
            while (line != null) {
                if (!Pattern.matches("\\d{2}:\\d{2}:\\d{2}\\sPM|\\d{2}:\\d{2}:\\d{2}\\sAM", line))
                    throw new IllegalArgumentException();
                String[] partsOfTime = line.split(" ");
                if (partsOfTime[1].equals("AM")) {
                    amList.add(timeInSeconds(partsOfTime[0]));
                } else pmList.add(timeInSeconds(partsOfTime[0]));
                line = reader.readLine();
            }
            Collections.sort(amList);
            Collections.sort(pmList);
            amList.stream().forEach(x -> {
                try {
                    writer.write(timeInFormat(x) + " AM\n");
                } catch (IOException e) {
                    throw new IllegalArgumentException();
                }
            });
            pmList.stream().forEach(x -> {
                try {
                    writer.write(timeInFormat(x) + " PM\n");
                } catch (IOException e) {
                    throw new IllegalArgumentException();
                }
            });
        }
    }

    private static int timeInSeconds(String time) {
        int i;
        String[] timeToPart = time.split(":");
        if (!timeToPart[0].equals("12")) {
            i = Integer.parseInt(timeToPart[0]) * 3600 +
                    Integer.parseInt(timeToPart[1]) * 60 + Integer.parseInt(timeToPart[2]);
        } else i = Integer.parseInt(timeToPart[1]) * 60 + Integer.parseInt(timeToPart[2]);
        return i;
    }

    private static String timeInFormat(Integer time) {
        int hour;
        int min = time / 60 % 60;
        int sec;
        if (time >= 3600) {
            hour = time / 3600;
            sec = time / 1 % 60;
        } else {
            hour = 12;
            sec = time % 60;
        }
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }

    /**
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */

    //T=Nlog2(N);
    static public void sortAddresses(String inputName, String outputName) throws IOException {
        Map<String, TreeSet<String>> commonAdd = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(inputName), StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputName), StandardCharsets.UTF_8))) {
            String line = reader.readLine();
            while (line != null) {
                if (!Pattern.matches("[А-ЯЁа-яёPa-]+\\s[А-ЯЁа-яё-]+\\s-\\s[А-ЯЁа-яё-]+\\s\\d+", line))
                    throw new IllegalArgumentException();
                String[] partsOfData = line.split(" - ");
                if (commonAdd.keySet().contains(partsOfData[1])) {
                    commonAdd.get(partsOfData[1]).add(partsOfData[0]);
                } else commonAdd.put(partsOfData[1], new TreeSet<>(Collections.singleton(partsOfData[0])));
                line = reader.readLine();
            }
            Map<String, TreeSet<Integer>> streetAndNumber = new TreeMap<>();
            for (String a : commonAdd.keySet()) {
                String[] partsOfAdd = a.split(" ");
                if (streetAndNumber.keySet().contains(partsOfAdd[0])) {
                    streetAndNumber.get(partsOfAdd[0]).add(Integer.parseInt(partsOfAdd[1]));
                } else
                    streetAndNumber.put(partsOfAdd[0], new TreeSet<>(Collections.singleton(Integer.parseInt(partsOfAdd[1]))));
            }
            for (Map.Entry<String, TreeSet<Integer>> element : streetAndNumber.entrySet()) {
                for (Integer num : element.getValue()) {
                    String names = commonAdd.get(element.getKey() + " " + num).toString();
                    writer.write(element.getKey() + " " + num + " - " + names.substring(1, names.length() - 1) + "\n");
                }
            }
        }
    }

    /**
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    // Т= O(N);
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(inputName)));
             BufferedWriter writer = new BufferedWriter(new FileWriter(new File(outputName)))) {
            List<Integer> temperatures = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                if (!Pattern.matches("-?\\d+.\\d{1}", line)) throw new IllegalArgumentException();
                double value = Double.parseDouble(line);
                if (value < - 273 || value > 500) throw new IllegalArgumentException();
                temperatures.add((int)(value * 10 + 2730));
                line = reader.readLine();
            }
            int[] res = Sorts.countingSort(temperatures.stream().mapToInt(i -> i).toArray(), 7730);
            Arrays.stream(res).forEach(x -> {
                try {
                    double format = x - 2730;
                    writer.write(format /10 + "\n");
                } catch (IOException e) {
                    throw new IllegalArgumentException();
                }
            });
        }
    }

    /**
     * Сортировка последовательности
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Соединить два отсортированных массива в один
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
