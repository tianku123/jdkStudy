package rh.study.jdk8.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 流的收集器
 */
public class CollectorsTest {
    public static void main(String[] args) {
        List<Dish> menus = Dish.getMockData();
        /**
         * 汇总卡路里
         */
        int count = menus.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println("count:" + count);
        /**
         * 卡路里最大值最小值
         */
        Optional<Dish> max = menus.stream().max((o1, o2) -> o1.getCalories() - o2.getCalories());
        System.out.println("max:" + max.get().getCalories());
        max = menus.stream().max(Comparator.comparingInt(Dish::getCalories));
        System.out.println("max:" + max.get().getCalories());
        max = menus.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));
        System.out.println("max:" + max.get().getCalories());

        /**
         * 连接字符串
         */
        String menuNames = menus.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println(menuNames);

        /**
         * 分组
         */
        Map<Dish.Type, List<Dish>> dishesByType =
                menus.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println(dishesByType);
        /**
         * 多级分组
         */
        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menus.stream().collect(Collectors.groupingBy(Dish::getType,
                Collectors.groupingBy(Dish::getCaloricLevel
                        /*dish -> {
                    if (dish.getCalories() <= 400) {
                        return Dish.CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return Dish.CaloricLevel.NORMAL;
                    } else {
                        return Dish.CaloricLevel.FAT;
                    }
                }*/
                )));
        System.out.println("多级分组:" + dishesByTypeCaloricLevel);
        /**
         * 多级分组 计数  select count(1) from table group by type,level
         */
        Map<Dish.Type, Map<Dish.CaloricLevel, Long>> dishesByTypeCaloricLevelCount = menus.parallelStream().collect(Collectors.groupingBy(Dish::getType,
                Collectors.groupingBy(Dish::getCaloricLevel, Collectors.counting()
                        /*dish -> {
                    if (dish.getCalories() <= 400) {
                        return Dish.CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return Dish.CaloricLevel.NORMAL;
                    } else {
                        return Dish.CaloricLevel.FAT;
                    }
                }*/
                )));
        System.out.println("多级分组计数:" + dishesByTypeCaloricLevelCount);

        /**
         * 分组统计数量
         */
        Map<Dish.Type, Long> groupCount = menus.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println(groupCount);
        /**
         * 分组获取每组热量最高的菜品
         */
        Map<Dish.Type, Optional<Dish>> groupMax = menus.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(groupMax);
        /**
         * Collectors.collectingAndThen 把收集器的结果转换成另一种类型
         */
        Map<Dish.Type, Dish> mostCaloricByType =
                menus.stream()
                        .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                        Optional::get)));
        /**
         * 分区函数
         * 分素菜和荤菜
         */
        Map<Boolean, List<Dish>> partitionDish = menus.stream().collect(
                Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println(partitionDish);
    }
}
