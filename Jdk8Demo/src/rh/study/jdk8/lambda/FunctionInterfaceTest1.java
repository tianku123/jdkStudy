package rh.study.jdk8.lambda;

/**
 * jdk1.8新特性，函数式接口
 * 含义：只定义一个抽象方法的接口
 *  jdk1.8中接口现在还可以拥有默认方法（接口默认方法（default关键字声明），这个方法不需要子类实现）。
 *  哪怕有很多默认方法，只要接口只定义了一个抽象方法，它就仍然是一个函数式接口。
 * 例如： public interface Predicate<T>{
             boolean test (T t);
             }
             public interface Comparator<T> {
             int compare(T o1, T o2);
             }
             public interface Runnable{
             void run();
             }
             public interface ActionListener extends EventListener{
             void actionPerformed(ActionEvent e);
             }
             public interface Callable<V>{
             V call();
             }
             public interface PrivilegedAction<V>{
             V run();
             }
     抽象方法的签名可以描述Lamdba表达式的签名。
     函数式接口的抽象方法的签名称为函数描述符。
 */
public class FunctionInterfaceTest1 {

    public static void main(String[] args) {

        runnableTest();
    }

    /**
     * public interface Runnable{ void run(); }
     *  run方法什么也不接收，什么也不返回
     * 函数描述符或签名：() -> void
     */
    private static void runnableTest() {
        /**
         * @FunctionalInterface
         * public interface Runnable { public abstract void run(); }
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("传统方式");
            }
        }).start();
        new Thread(
                () -> System.out.println("Lambda方式,Runnable函数式接口对应的签名为() -> void")
        ).start();
    }
}
