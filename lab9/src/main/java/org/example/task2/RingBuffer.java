package org.example.task2;

public class RingBuffer<T> {

    private final Object[] buffer;
    private final int capacity;

    private int count;
    private int head;
    private int tail;

    private final Object lock = new Object();

    public RingBuffer(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity must be > 0");
        }
        this.capacity = capacity;
        this.buffer = new Object[capacity];
        this.count = 0;
        this.head = 0;
        this.tail = 0;
    }

    public void put(T item) throws InterruptedException {
        synchronized (lock) {
            while (count == capacity) {
                lock.wait();
            }

            buffer[tail] = item;
            tail = (tail + 1) % capacity;
            count++;

            lock.notifyAll();
        }
    }

    public T get() throws InterruptedException {
        synchronized (lock) {
            while (count == 0) {
                lock.wait();
            }

            T item = (T) buffer[head];
            buffer[head] = null;
            head = (head + 1) % capacity;
            count--;

            lock.notifyAll();
            return item;
        }
    }
}