package rh.study.jdk8.stream;

import java.util.Arrays;
import java.util.List;

public class Dish {
    private final String name;
    // 素菜
    private final boolean vegetarian;
    // 卡路里
    private final int calories;
    private final Type type;

    private final CaloricLevel caloricLevel;

    public enum Type { MEAT, FISH, OTHER }
    /**
     * 热量分类
     * 热量不到400卡路里的菜划分为“低热量”（diet），热量400到700
     * 卡路里的菜划为“普通”（normal），高于700卡路里的划为“高热量”（fat）
     */
    public enum CaloricLevel { DIET, NORMAL, FAT }
    public Dish(String name, boolean vegetarian, int calories, Type type, CaloricLevel caloricLevel) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
        this.caloricLevel = caloricLevel;
    }
    public static List<Dish> getMockData() {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT, CaloricLevel.FAT),
                new Dish("beef", false, 700, Dish.Type.MEAT, CaloricLevel.NORMAL),
                new Dish("chicken", false, 400, Dish.Type.MEAT,  CaloricLevel.DIET),
                new Dish("french fries", true, 530, Dish.Type.OTHER,  CaloricLevel.NORMAL),
                new Dish("rice", true, 350, Dish.Type.OTHER, CaloricLevel.DIET),
                new Dish("season fruit", true, 120, Dish.Type.OTHER, CaloricLevel.DIET),
                new Dish("pizza", true, 550, Dish.Type.OTHER,  CaloricLevel.NORMAL),
                new Dish("prawns", false, 300, Dish.Type.FISH, CaloricLevel.DIET),
                new Dish("salmon", false, 450, Dish.Type.FISH,  CaloricLevel.NORMAL) );
        return menu;
    }
    public String getName() {
        return name;
    }
    public boolean isVegetarian() {
        return vegetarian;
    }
    public int getCalories() {
        return calories;
    }
    public Type getType() {
        return type;
    }

    public CaloricLevel getCaloricLevel() {
        if (this.getCalories() <= 400) {
            return Dish.CaloricLevel.DIET;
        } else if (this.getCalories() <= 700) {
            return Dish.CaloricLevel.NORMAL;
        } else {
            return Dish.CaloricLevel.FAT;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
