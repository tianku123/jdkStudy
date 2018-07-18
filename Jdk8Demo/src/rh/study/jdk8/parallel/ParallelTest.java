package rh.study.jdk8.parallel;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelTest {
    /**
     * 性能测试
     * @param adder
     * @param n
     * @return
     */
    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
//            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

    public static long sequentialSum_Long(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }
    public static long parallelSum_Long(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }
    public static long sequentialSum_long(long n) {
        return LongStream.rangeClosed(1, n)
                .limit(n)
                .reduce(0L, Long::sum);
    }
    public static long parallelSum_long(long n) {
        return LongStream.rangeClosed(1, n)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }
    public static void main(String[] args) {
        /**
         * Stream.iterate 生成包装类
         * 因此慢
         */
        long times = measureSumPerf(ParallelTest::sequentialSum_Long, 10_000_000);
        System.out.println("spends times: " + times);
        times = measureSumPerf(ParallelTest::parallelSum_Long, 10_000_000);
        System.out.println("spends times: " + times);
        /**
         * LongStream.rangeClosed 生成原始类型，不存在装箱拆箱问题，提升性能
         */
        times = measureSumPerf(ParallelTest::sequentialSum_long, 10_000_000);
        System.out.println("spends times: " + times);
        times = measureSumPerf(ParallelTest::parallelSum_long, 10_000_000);
        System.out.println("spends times: " + times);
    }
}
