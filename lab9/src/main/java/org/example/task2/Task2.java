package org.example.task2;

public class Task2 {
    private static final int BUFFER_CAPACITY = 10;
    private static final int MESSAGES_TO_READ_BY_MAIN = 100;

    private static final int NUM_PRODUCERS = 5;
    private static final int NUM_TRANSLATORS = 2;

    public static void main(String[] args) {
        RingBuffer<String> buffer1 = new RingBuffer<>(BUFFER_CAPACITY);
        RingBuffer<String> buffer2 = new RingBuffer<>(BUFFER_CAPACITY);

        startProducers(buffer1);
        startTranslators(buffer1, buffer2);

        System.out.println("Main: Starting to read " + MESSAGES_TO_READ_BY_MAIN + " messages...");
        try {
            for (int i = 1; i <= MESSAGES_TO_READ_BY_MAIN; i++) {
                String finalMsg = buffer2.get();
                System.out.println("Main: Received (" + i + "): " + finalMsg);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Main: Read " + MESSAGES_TO_READ_BY_MAIN + " messages. Exiting.");
    }

    private static void startProducers(RingBuffer<String> buffer1) {
        for (int i = 1; i <= NUM_PRODUCERS; i++) {
            final int producerId = i;
            Runnable producerTask = () -> {
                try {
                    int msgCount = 1;
                    while (true) {
                        String msg = "Thread #" + producerId + " generated message " + (msgCount++);
                        buffer1.put(msg);
                        Thread.sleep(100 + (producerId * 50));
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            };
            Thread producerThread = new Thread(producerTask, "Producer-" + producerId);
            producerThread.setDaemon(true);
            producerThread.start();
        }
    }

    private static void startTranslators(RingBuffer<String> buffer1, RingBuffer<String> buffer2) {
        for (int i = 1; i <= NUM_TRANSLATORS; i++) {
            final int translatorId = i;
            Runnable translatorTask = () -> {
                try {
                    while (true) {
                        String originalMsg = buffer1.get();
                        String translatedMsg = "Thread #" + translatorId + " translated [" + originalMsg + "]";
                        buffer2.put(translatedMsg);
                        Thread.sleep(300 + (translatorId * 100));
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            };
            Thread translatorThread = new Thread(translatorTask, "Translator-" + translatorId);
            translatorThread.setDaemon(true);
            translatorThread.start();
        }
    }
}
