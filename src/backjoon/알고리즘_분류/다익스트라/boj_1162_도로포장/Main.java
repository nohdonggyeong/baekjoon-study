package backjoon.알고리즘_분류.다익스트라.boj_1162_도로포장;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	
	static class Node implements Comparable<Node> {
		int end;
		long weight;
		int countPaved;
		
		Node(int end, long weight, int countPaved) {
			this.end = end;
			this.weight = weight;
			this.countPaved = countPaved;
		}
		
		@Override
		public int compareTo(Node o) {
			return Long.compare(this.weight, o.weight);
		}
	}
	
	static ArrayList<ArrayList<Node>> road;
	
	static long[][] distance;
	
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			// road 입력
			road = new ArrayList<>(N + 1); // 0번 비워두고 1 ~ N 도시
			for (int n = 0; n <= N; n++) {
				road.add(new ArrayList<>());
			}
			int start, end;
			long weight;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				start = Integer.parseInt(st.nextToken());
				end = Integer.parseInt(st.nextToken());
				weight = Long.parseLong(st.nextToken());
				road.get(start).add(new Node(end, weight, 0));
				road.get(end).add(new Node(start, weight, 0));
			}
			
			// distance 초기화
			distance = new long[N + 1][K + 1];
			for (int n = 0; n <= N; n++) {
				Arrays.fill(distance[n], Long.MAX_VALUE);
			}
			distance[1][0] = 0; // 출발 1번 도시에서 도착 1번 도시로, 포장 0회 = 거리 0
			
			// priority queue 초기화
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.add(new Node(1, 0, 0));
			
			while(!pq.isEmpty()) {
				Node cur = pq.poll();
				
				if (distance[cur.end][cur.countPaved] < cur.weight) {
					continue;
				}
				
				for (Node next : road.get(cur.end)) {
					// cur에서 next까지를 포장하며 경우의 수를 확장 시 더해지는 weight는 0
					if (cur.countPaved < K && distance[next.end][cur.countPaved + 1] > distance[cur.end][cur.countPaved]) {
						distance[next.end][cur.countPaved + 1] = distance[cur.end][cur.countPaved];
						pq.add(new Node(next.end, distance[next.end][cur.countPaved + 1], cur.countPaved + 1));
					}
					
					// cur에서 next까지를 포장하지 않으며 경우의 수를 확장 시 weight 합산
					if (distance[next.end][cur.countPaved] > distance[cur.end][cur.countPaved] + next.weight) {
						distance[next.end][cur.countPaved] = distance[cur.end][cur.countPaved] + next.weight;
						pq.add(new Node(next.end, distance[next.end][cur.countPaved], cur.countPaved));
					}
				}
			}
			
			bw.write(String.valueOf(Arrays.stream(distance[N]).min().getAsLong()));
			bw.flush();
		}
	}
}
