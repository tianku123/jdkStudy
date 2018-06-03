package rh.study.jdk8.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 流的创建
 */
public class StreamCreateTest {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
        /**
         * 空流
         */
        stream = Stream.empty();
        /**
         * 数组转为流
         */
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        /**
         * 由文件生成流
         * Files.lines得到一个Stream
         */
        long uniqueWords = 0;
        try(Stream<String> lines =
                    Files.lines(Paths.get("data.txt"), Charset.defaultCharset())){
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        }
        catch(IOException e){
        }
        /**
         * Stream.iterate
         * Stream.generate
         */
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}
