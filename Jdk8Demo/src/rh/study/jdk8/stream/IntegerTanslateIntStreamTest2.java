package rh.study.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

public class IntegerTanslateIntStreamTest2 {
    public static void main(String[] args) {
        List<Dish> menu = Dish.getMockData();
        /**
         * 求和
         * 这段代码的问题是，它有一个暗含的装箱成本。每个Integer都必须拆箱成一个原始类型， 再进行求和
         */
        int sum = menu.stream().map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println(sum);
        /**
         * 原始类型流,不用拆装箱了
         * IntStream，DoubleStream，LongStream
         */
        sum = menu.stream().mapToInt(Dish::getCalories)
                .sum();
        System.out.println(sum);

    }

}
