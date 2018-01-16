package ru.job4j.synchronize;

import org.junit.Test;

/**
 * Тест для класса Count.
 * @author Vitaly Kovrov (mailto:virtaly@ya.ru)
 * @version 0.1
 * @since 0.1
 */
public class CountTest {

    private class CountThread extends Thread {
        private final Count counter;

        private CountThread(Count counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            this.counter.increment();
        }
    }

    /**
     * Тест для метода increment.
     * Использование метода для работы двух потоков с общим ресурсом.
     */
    @Test
    public void whenExecute2ThreadThen2() {
        Count counter = new Count();
        Thread firstThread = new CountThread(counter);
        Thread secondThread = new CountThread(counter);
        firstThread.start();
        secondThread.start();
        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println(counter.getValue());
    }
}