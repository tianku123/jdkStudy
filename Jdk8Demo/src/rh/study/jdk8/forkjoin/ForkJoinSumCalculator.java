package rh.study.jdk8.forkjoin;

import rh.study.jdk8.parallel.ParallelTest;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * extends RecursiveTask<Long> 创建可以用于分支/合并框架的任务
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    // 要求和的数组
    private final long[] numbers;
    /**
     * 子任务处理的数组的起始和结束位置
     */
    private final int start;
    private final int end;
    // 不再将任务分解为子任务的数组大小
    public static final long THRESHOLD = 10_000;

    /**
     * 公共构造函数用于创建主任务
     *
     * @param numbers
     */
    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    /**
     * 私有构造函数用于以递归方式为主任务创建子任务
     *
     * @param numbers
     * @param start
     * @param end
     */
    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        // 该任务负责求和的部分的大小
        int length = end - start;
        // 如果小于或等于阈值，则顺序求和
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        // 创建一个子任务为数组的前一半求和
        ForkJoinSumCalculator leftTask =
                new ForkJoinSumCalculator(numbers, start, start + length / 2);
        // 利用另一个ForkJoinPool 线程异步执行新创建的任务
        leftTask.fork();
        // 创建另一个子任务为数组的后一半求和
        ForkJoinSumCalculator rightTask =
                new ForkJoinSumCalculator(numbers, start + length / 2, end);
        // 同步执行第二个子任务，有可能进一步递归划分子任务
        Long rightResult = rightTask.compute();
        // 读取第一个子任务的结果，如果未完成就等待
        Long leftResult = leftTask.join();
        // 合并值
        return leftResult + rightResult;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    public static void main(String[] args) {
        /**
         * LongStream.rangeClosed 生成原始类型，不存在装箱拆箱问题，提升性能
         */
        long times = ParallelTest.measureSumPerf(ParallelTest::sequentialSum_long, 10_000_000);
        System.out.println("spends times: " + times);
        times = ParallelTest.measureSumPerf(ParallelTest::parallelSum_long, 10_000_000);
        System.out.println("spends times: " + times);
        /**
         * 这种fork join的方式反而更慢，因为上面都是流操作，不需要把流转为long[]
         * LongStream.rangeClosed(1, n).toArray() 这里的toArray影响了性能
         */
        times = ParallelTest.measureSumPerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000);
        System.out.println(times);
        System.out.println(ParallelTest.sequentialSum_long(10_000_000));
        System.out.println(ParallelTest.parallelSum_long(10_000_000));
        System.out.println(ForkJoinSumCalculator.forkJoinSum(10_000_000));
    }
}
