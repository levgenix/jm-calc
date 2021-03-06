package online.javalearn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Структура целочисленного значения с возможностью конвертации в римское представление и обратно
 */
public class JmInteger {

    /**
     * Представление в 10й системе счисления
     */
    private int value;
    /**
     * Был ли ввод пользователем в римской системе
     */
    private boolean isRoman = false;

    /**
     * Конструктор для проверки на 10е или римское
     * @param s
     */
    public JmInteger(String s) {
        try {
            value = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            // Возможно, что римский формат
            value = convertToInt(s);
        }
    }

    /**
     * Конструктор для 10й системы
     * @param i
     */
    public JmInteger(int i) {
        value = i;
    }

    /**
     * Конвертация римского числа в 10е
     * Устанавливает флаг isRoman в true
     * @param s
     * @return 10е число
     */
    private int convertToInt(String s) {
        String[] romanString = s.split("");

        HashMap<String, Integer> romanToIntegerMap = new HashMap<String, Integer>();
        romanToIntegerMap.put("I", 1);
        romanToIntegerMap.put("V", 5);
        romanToIntegerMap.put("X", 10);
        romanToIntegerMap.put("L", 50);
        romanToIntegerMap.put("C", 100);
        romanToIntegerMap.put("D", 500);
        romanToIntegerMap.put("M", 1000);

        int numLength = romanString.length;
        Set<Integer> lessIndices = new HashSet<Integer>();

        for (int i = 0; i < numLength; ++i) {
            if (i+1 < numLength) {
                if (romanToIntegerMap.get(romanString[i]) < romanToIntegerMap.get(romanString[i+1]))
                    lessIndices.add(i);
            }
        }

        int num = 0;
        for (int i = 0; i < numLength;) {
            if (!lessIndices.contains(i)) {
                num = num + romanToIntegerMap.get(romanString[i]);
                ++i;
            } else {
                num = num + romanToIntegerMap.get(romanString[i+1]) - romanToIntegerMap.get(romanString[i]);
                i += 2;
            }
        }

        isRoman = true;
        return num;
    }

    /**
     * Переводит 10е число в римское представление
     * @param num
     * @return Римский формат
     */
    private String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int times = 0;
        String[] romans = new String[] { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M" };
        int[] ints = new int[] { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000 };
        for (int i = ints.length - 1; i >= 0; i--) {
            times = num / ints[i];
            num %= ints[i];
            while (times > 0) {
                sb.append(romans[i]);
                times--;
            }
        }
        return sb.toString();
    }

    public String toRoman() {
        return intToRoman(value);
    }

    public boolean isRoman() {
        return isRoman;
    }

    public Integer getValue() {
        return value;
    }
}
