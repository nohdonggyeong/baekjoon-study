package backjoon.시뮬레이션.boj_17143_낚시왕;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
	public static void main(String args[]) throws IOException {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(1);
		queue.offer(2);
		queue.offer(1);
		queue.offer(5);
		queue.offer(1);
		queue.offer(23);
		queue.offer(11);
		
		Queue<Integer> copy = queue;
		queue.poll();
		queue.poll();
//		while (!queue.isEmpty()) {
//			System.out.println("queue: " + queue.poll());
//		}
		
		System.out.println();
		while (!copy.isEmpty()) {
			System.out.println("copy: " + copy.poll());
		}
	}
}
