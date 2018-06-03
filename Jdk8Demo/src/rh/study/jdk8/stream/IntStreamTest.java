package rh.study.jdk8.stream;

import java.util.stream.IntStream;

public class IntStreamTest {

    public static void main(String[] args) {
        /**
         * 数值流相关应用
         * 数值流范围
         */
        IntStream intStream = IntStream.rangeClosed(1, 10);
        long count = intStream.count();
        System.out.println(count);
        intStream = IntStream.range(1, 10);
        count = intStream.count();
        System.out.println(count);
    }
}
