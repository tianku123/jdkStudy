package rh.study.jdk8.optional;

import java.util.Optional;

/**
 * Created by admin on 2018/6/4.
 */
public class OptionalTest {

    public static void main(String[] args) {
        /**
         * 创建空的Optional
         */
        Optional<String> empty = Optional.empty();
        System.out.println(empty);
        /**
         * 创建非空的Optional
         */
        Optional<String> optional = Optional.of("hello");
        System.out.println(optional);
        /**
         * 可接受null 的 Optional
         */
        Optional<String> optional1 = Optional.ofNullable(null);
        System.out.println(optional1);
        /**
         * 取值不报空指针异常
         */
        Optional<String> stringOptional = optional1.map(String::toString);
        System.out.println(stringOptional);
        /**
         * 抛出 NullPointerException异常的Optional
         */
        String str = null;
        Optional<String> optionalNull = Optional.of(str);
        System.out.println(optionalNull);
    }
}
