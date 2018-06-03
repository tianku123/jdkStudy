package rh.study.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Stream
    API通过allMatch、anyMatch、noneMatch、findFirst和findAny方法提供了这样的工具。
 */
public class FindOrMatch {

    public static void main(String[] args) {
        List<Dish> menu = Dish.getMockData();
        /**
         * 检查谓词是否至少匹配一个元素
         * 例如，你可以用它来看 看菜单里面是否有素食可选择：
         */
        if(menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }
        /**
         *  allMatch 是否匹配所有
         *  菜品卡路里是否都小于1000
         */
        boolean isHealthy = menu.stream()
                .allMatch(d -> d.getCalories() < 1000);
        System.out.println("allMatch:" + isHealthy);
        /**
         *  noMatch 是否匹配所有
         *  菜品卡路里没有大于等于1000的
         */
        isHealthy = menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000);
        System.out.println("noneMatch:" + isHealthy);

        /**
         * 查找元素 findAny 随机选择元素
         */
        Optional<Dish> dish =
                menu.stream()
                        .filter(Dish::isVegetarian)
                        .findAny();
        System.out.println("findAny:" + dish);

        /**
         * 查找元素 findFirst
         */
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                        .map(x -> x * x)
                        .filter(x -> x % 3 == 0)
                        .findFirst(); // 9
        System.out.println("findFirst:" + firstSquareDivisibleByThree);
    }
}
