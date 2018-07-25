package rh.study.jdk8.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

/**
 * 方法和Lambda 作为一等公民
 */
public class LambdaTest1 {
    public static void main(String[] args) {
        List<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple("red", 100d));
        appleList.add(new Apple("red", 150d));
        appleList.add(new Apple("green", 10d));
        appleList.add(new Apple("white", 100d));
        appleList.add(new Apple("red", 200d));
        /**
         * 步骤1：传递代码
         */
        List<Apple> resList = Apple.filterGreenApples(appleList);
        for (Apple apple : resList) {
            System.out.println("步骤1：传递代码：" + apple.toString());
        }
        resList = Apple.filterHeavyApples(appleList);
        for (Apple apple : resList) {
            System.out.println("步骤1：传递代码：" + apple.toString());
        }
        /**
         * 步骤2：传递方法
         *
         * 方法引用新语法：Apple::isGreenApple
         */
        resList = Apple.filterApple(appleList, Apple::isHeavyApple);
        for (Apple apple : resList) {
            System.out.println("步骤2：传递方法：" + apple.toString());
        }
        resList = Apple.filterApple(appleList, Apple::isGreenApple);
        for (Apple apple : resList) {
            System.out.println("步骤2：传递方法：" + apple.toString());
        }
        /**
         * 步骤3：从传递方法到 Lambda
         *
         * 匿名函数或Lambda
         * 类似于es6中的箭头函数，语法如下：
             （params） -> 表达式
             （params） -> { 语句 }
             注意：大括号内是语句

             Lambda仅可以用于上下文是函数式接口的情况。
         */
        resList = Apple.filterApple(appleList, (Apple apple) -> apple.getWeight() > 150);
        for (Apple apple : resList) {
            System.out.println("步骤3：从传递方法到 Lambda:" + apple.toString());
        }
        // 只有一个参数时也可以简写Lambda
        resList = Apple.filterApple(appleList, apple -> apple.getColor().equals("green"));
        for (Apple apple : resList) {
            System.out.println("步骤3：从传递方法到 Lambda:" + apple.toString());
        }

        /**
         * 进化4：流操作
         */
        resList = appleList.stream()
                .filter(apple -> apple.getWeight() > 150)
                .collect(Collectors.toList());
        for (Apple apple : resList) {
            System.out.println("进化4：流操作:" + apple.toString());
        }
        resList = appleList.stream()
                .filter(apple -> apple.getColor().equals("green"))
                .collect(Collectors.toList());
//        resList.forEach(new Consumer<Apple>() {
//            @Override
//            public void accept(Apple apple) {
//                System.out.println("进化4：流操作:" + apple.toString());
//            }
//        });
        Consumer<Apple> lambda = (Apple apple) -> System.out.println("进化4：流操作:" + apple.toString());
        /**
         * 接口的默认方法
         * default void forEach(Consumer<? super T> action) {
             Objects.requireNonNull(action);
             for (T t : this) {
             action.accept(t);
             }
             }
         */
        resList.forEach(lambda);

//        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
//        evenNumbers.test(1000);
        /**
         * 排序
         */
        System.out.println(appleList);
//        appleList.sort((o1, o2) -> {if (o1.getWeight() - o2.getWeight()>0) return 1; return -1;});
        appleList.sort(Comparator.comparingDouble(Apple::getWeight));
        System.out.println(appleList);
    }
}

