package algorithm.bfs.boj_16235_나무_재테크;

import java.io.IOException;
import java.util.PriorityQueue;

public class PriorityCheck {
	static class Node implements Comparable<Node>{
		private int x;
		private int y;
		private int z;
		
		public Node(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		@Override
		public int compareTo(Node o) {
			return z - o.z; // 오름차순
			// return o.z - z // 내림차순
		}
	}
	public static void main(String args[]) throws IOException {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(2, 54, 2));
		pq.offer(new Node(21, 24, 2));
		pq.offer(new Node(2, 23, 1));
		pq.offer(new Node(89, 4, 5));
		pq.offer(new Node(21, 24, 8));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			System.out.print(node.x + " " + node.y + " " + node.z);
			System.out.println();
		}
		
	}
}
