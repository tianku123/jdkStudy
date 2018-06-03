package rh.study.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流的扁平化
 */
public class FlatMapTest {

    public static void main(String[] args) {
        /**
         * 例如， 给定单词列表
         ["Hello","World"]，你想要返回列表["H","e","l", "o","W","r","d"]。
         */
        String[] words = {"Hello","World"};
        // 数组转为流
        Stream<String> stream = Arrays.stream(words);
        List<String[]> list = stream.map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(list);
        /**
         * flatMap
         * 使用flatMap方法的效果是，各个数组并不是分别映射成一个流，而是映射成流的内容。所
         * 有使用map(Arrays::stream)时生成的单个流都被合并起来，即扁平化为一个流。
         */
        List<String> ss = Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(ss);
    }
}
