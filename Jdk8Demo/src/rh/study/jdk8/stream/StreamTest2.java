package rh.study.jdk8.stream;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTest2 {
    public static void main(String[] args) {
        List<Dish> menu = Dish.getMockData();
        /**
         * 1、过滤卡路里低于400的食物
         * 2、根据卡路里排序
         * 3、取出菜品的名字
         */
        List<String> list = lessJdk8(menu);
        System.out.println("less jdk1.8:" + list);
        /**
         * jdk1.8处理方式
         */
        list = menu.parallelStream()
                .filter(dish -> dish.getCalories() < 400)
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println("jdk1.8:" + list);

        /**
         * 根据菜品种类分组
         */
        Map<Dish.Type, List<Dish>> groupMap = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));
        System.out.println("分组：" + groupMap);
        /**
         * 取卡路里最高的三个菜品
         */
        menu.stream().sorted((Dish o1, Dish o2) -> o2.getCalories() - o1.getCalories())
                .limit(3)
                .map(Dish::getName)
                .forEach((String str) -> System.out.println("top3" + str));
    }

    /**
     * 1、过滤卡路里低于400的食物
     * 2、根据卡路里排序
     * 3、取出菜品的名字
     * @param menu
     * @return
     */
    private static List<String> lessJdk8(List<Dish> menu) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: menu){
            if(d.getCalories() < 400){
                lowCaloricDishes.add(d);
            }
        }
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2){
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        List<String> lowCaloricDishesName = new ArrayList<>();
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }
}
