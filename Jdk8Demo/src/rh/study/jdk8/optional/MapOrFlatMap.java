package rh.study.jdk8.optional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by admin on 2018/6/4.
 */
public class MapOrFlatMap {

    public static void main(String[] args) {
        /**
         * 判空取值
         */
        /**
         * 取值不报空指针异常
         */
        Optional<String> optional1 = Optional.ofNullable(null);
        Optional<String> stringOptional = optional1.map(String::toString);
        System.out.println(stringOptional);
    }
}
