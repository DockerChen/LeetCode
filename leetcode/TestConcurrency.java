import java.util.function.IntConsumer;

public class TestConcurrency {
    private static final int n = 10;

    public static void main(String[] args) throws InterruptedException {
        //test FooBar
        FooBar fooBar = new FooBar(n);
        Runnable runnable1 = () -> System.out.print("foo");
        Runnable runnable2 = () -> System.out.print("bar");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fooBar.foo(runnable1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fooBar.bar(runnable2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println();
        thread1.start();
        thread2.start();

        //test Foo
        Foo foo = new Foo();
        Runnable runnableFoo1 = () -> System.out.print("first");
        Runnable runnableFoo2 = () -> System.out.print("second");
        Runnable runnableFoo3 = () -> System.out.print("third");

        Thread threadFoo1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foo.first(runnableFoo1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadFoo2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foo.second(runnableFoo2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadFoo3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    foo.third(runnableFoo3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println();
        threadFoo3.start();
        threadFoo2.start();
        threadFoo1.start();

        //test ZeroEvenOdd
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);
        IntConsumer printNumber = new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.print(value);
            }
        };
        Thread threadZero = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.zero(printNumber);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadEven = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.even(printNumber);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadOdd = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.odd(printNumber);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println();
        threadZero.start();
        threadEven.start();
        threadOdd.start();

    }
}
