package com.hl;

import com.hl.Circle.CircleDeque;
import com.hl.Circle.CircleQueue;

public class Main {
    public static void main(String[] args) {
//        deque();
//        CircleQueue();
        CircleDeque();
    }

    private static void CircleDeque() {

        CircleDeque<Integer> queue = new CircleDeque<>();

        for (int i = 0; i < 10; i++) {
            queue.enQueueFront(i + 1);
            queue.enQueueRear(i + 100);
        }

        for (int i = 0; i < 3; i++) {
            queue.deQueueFront();
            queue.deQueueRear();
        }

        queue.enQueueFront(11);
        queue.enQueueFront(12);

        System.out.println(queue);

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueueFront());
        }

    }

    private static void CircleQueue() {
        CircleQueue circleQueue = new CircleQueue<Integer>();
        //0 1 2 3 4 5 6 7 8 9
        for (int i = 0; i < 10; i++) {
            circleQueue.enQueue(i);
        }
        //null null null null null 5 6 7 8 9
        for (int i = 0; i < 5; i++) {
            circleQueue.deQueue();
        }

        //15 16 17 18 19 5 6 7 8 9
        for (int i = 15; i < 20; i++) {
            circleQueue.enQueue(i);
        }

        System.out.println(circleQueue);

        while (!circleQueue.isEmpty()) {
            System.out.println(circleQueue.deQueue());
        }
    }

    private static void queue() {
        Queue<Integer> queue = new Queue<>();

        queue.enQueue(11);
        queue.enQueue(22);
        queue.enQueue(33);
        queue.enQueue(44);

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }
    }

    private static void deque() {

        Deque<Integer> queue = new Deque<>();

        queue.enQueueFront(11);
        queue.enQueueFront(22);
        queue.enQueueRear(33);
        queue.enQueueRear(44);

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueueRear());
        }
    }
}
