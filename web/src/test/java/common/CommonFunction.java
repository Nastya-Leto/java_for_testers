package common;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunction {

    // метод с использованием цикла
    /*public static String randomString(int n) {
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++) {
            result = result + (char) ('a' + rnd.nextInt(26));
        }
        return result;
    }*/

    public static String randomString(int n) {
        var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(26);
        var result = Stream.generate(randomNumbers)
                .limit(n)
                .map(i -> 'a' + i) //из числа строит новое число, прибавляя код символа а
                //.map(i -> Character.toString(i))
                .map(Character::toString)// преобразование в строку
                .collect(Collectors.joining()); //коллектор, который символы собирает в строку
        return result;
    }

}
