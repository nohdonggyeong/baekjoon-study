package backjoon.유형별로_풀어보기.다익스트라.study_240201_pro_최소비용_구기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<Node>[] graph;
	static int[] dist;
	static boolean[] visited;
	static final int INF = 100_000_000;
	
	static class Node implements Comparable<Node>{
		private int end;
		private int weight;
		
		Node (int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return weight - o.weight;
		}
	}
	
	static void dijkstra(int start) {		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		while (!pq.isEmpty()) {
			Node curNode = pq.poll();
			int cur = curNode.end;
			
			if (visited[cur]) {
				continue;
			}
			
			visited[cur] = true;
			for (Node el : graph[cur])
			if (dist[el.end] > dist[cur] + el.weight) {
				dist[el.end] = dist[cur] + el.weight;
				pq.offer(new Node(el.end, dist[el.end]));
			}
		}
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			graph = new ArrayList[N + 1];
			for (int n = 1; n <= N; n++) {
				graph[n] = new ArrayList<>();
			}
			
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				graph[u].add(new Node(v, weight));
			}
			
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			
			dist = new int[N + 1];
			Arrays.fill(dist, INF);
			dist[start] = 0;
			
			visited = new boolean[N + 1];
			
			dijkstra(start);
			
			bw.write(String.valueOf(dist[end]));
			bw.flush();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
