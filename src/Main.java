public class Main {

    static final int ITERATIONS = 100_000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Teste sem sincronização ===");
        testUnsynchronized();

        System.out.println("\n=== Teste com sincronização ===");
        testSynchronized();
    }

    static void testUnsynchronized() throws InterruptedException {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) counter.increment();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) counter.decrement();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Valor esperado : 0");
        System.out.println("Valor obtido   : " + counter.value());
        System.out.println("Consistente?   : " + (counter.value() == 0));
    }

    static void testSynchronized() throws InterruptedException {
        SynchronizedCounter counter = new SynchronizedCounter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) counter.increment();
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) counter.decrement();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Valor esperado : 0");
        System.out.println("Valor obtido   : " + counter.value());
        System.out.println("Consistente?   : " + (counter.value() == 0));
    }
}
