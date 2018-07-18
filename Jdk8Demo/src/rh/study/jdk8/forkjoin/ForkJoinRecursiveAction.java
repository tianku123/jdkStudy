package rh.study.jdk8.forkjoin;

import java.util.concurrent.*;

/**
 * Created by admin on 2018/6/28.
 */
public class ForkJoinRecursiveAction extends RecursiveAction {
    // 要求和的数组
    private final long[] numbers;
    /**
     * 子任务处理的数组的起始和结束位置
     */
    private final int start;
    private final int end;
    // 不再将任务分解为子任务的数组大小
    public static final long THRESHOLD = 10;

    /**
     * 公共构造函数用于创建主任务
     *
     * @param numbers
     */
    public ForkJoinRecursiveAction(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    /**
     * 私有构造函数用于以递归方式为主任务创建子任务
     *
     * @param numbers
     * @param start
     * @param end
     */
    private ForkJoinRecursiveAction(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }
    @Override
    protected void compute() {
// 该任务负责求和的部分的大小
        int length = end - start;
        // 如果小于或等于阈值，则顺序求和
        if (length <= THRESHOLD) {
            for (int i = start; i < end; i++) {
                System.out.println(i);
            }
        } else {
            // 创建一个子任务为数组的前一半求和
            ForkJoinRecursiveAction leftTask =
                    new ForkJoinRecursiveAction(numbers, start, start + length / 2);
            // 利用另一个ForkJoinPool 线程异步执行新创建的任务
            leftTask.fork();
            // 创建另一个子任务为数组的后一半求和
            ForkJoinRecursiveAction rightTask =
                    new ForkJoinRecursiveAction(numbers, start + length / 2, end);
            // 同步执行第二个子任务，有可能进一步递归划分子任务
            rightTask.fork();
            // 读取第一个子任务的结果，如果未完成就等待
//        leftTask.join();
            // 合并值
//        return;
        }
    }

    public static void main(String[] args) {
        ForkJoinRecursiveAction task = new ForkJoinRecursiveAction(null, 0, 100);
        new ForkJoinPool().invoke(task);
    }
}
