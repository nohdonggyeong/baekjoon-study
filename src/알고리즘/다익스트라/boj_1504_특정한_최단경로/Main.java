package 알고리즘.다익스트라.boj_1504_특정한_최단경로;

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
	static int N, E;
	static int v1, v2;
	static List<Node>[] graph;
	static int[] dist;
	static final int INF = 200000000;
	
	static class Node implements Comparable<Node>{
		private int end;
		private int weight;
		
		Node(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return weight - o.weight;
		}
	}
	
	static int dijkstra(int start, int finish) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		boolean[] check = new boolean[N + 1];
		
		dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		while (!pq.isEmpty()) {
			Node curNode = pq.poll();
			int cur = curNode.end;
			
			if (check[cur]) {
				continue;
			}
			
			check[cur] = true;
			for (Node node : graph[cur]) {
				if (dist[node.end] > dist[cur] + node.weight) {
					dist[node.end] = dist[cur] + node.weight;
					pq.offer(new Node(node.end, dist[node.end]));
				}
			}
		}
		
		return dist[finish];
	}
	
	public static void main(String[] args) {
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try ( BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[N + 1];
			for (int n = 1; n < N + 1; n++) {
				graph[n] = new ArrayList<>();
			}
			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				graph[start].add(new Node(end, weight));
				graph[end].add(new Node(start, weight));
			}
			
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			
			// 1 -> v1 -> v2 -> N
			int result1 = 0;
			result1 += dijkstra(1, v1);
			result1 += dijkstra(v1, v2);
			result1 += dijkstra(v2, N);
			
			// 1 -> v2 -> v1 -> N
			int result2 = 0;
			result2 += dijkstra(1, v2);
			result2 += dijkstra(v2, v1);
			result2 += dijkstra(v1, N);
			
			int result = Math.min(result1, result2);
			bw.write(String.valueOf(result >= INF ? -1 : result));
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
