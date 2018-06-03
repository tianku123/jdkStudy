package rh.study.jdk8.stream;

import java.util.*;
import java.util.stream.Collectors;

public class CollectOrReduceTest2 {
    public static void main(String[] args) {
        List<Dish> menu = Dish.getMockData();
        /**
         * 元素求和
         */
        int[] numbers = {1,2,3,4,5};
        int sum = 0;
        for (int x : numbers) {
            sum += x;
        }
        System.out.println(sum);
        sum = Arrays.stream(numbers).reduce(0, (a, b) -> a + b);
        System.out.println(sum);
        // Integer的新方法sum
        sum = Arrays.stream(numbers).reduce(0, Integer::sum);
        System.out.println(sum);
        /**
         * 最大值、最小值
         */
        OptionalInt max = Arrays.stream(numbers).reduce(Integer::max);
        System.out.println(max.getAsInt());
        OptionalInt min = Arrays.stream(numbers).reduce(Integer::min);
        System.out.println(min.getAsInt());

    }

}
