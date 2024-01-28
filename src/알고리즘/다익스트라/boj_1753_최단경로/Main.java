package 알고리즘.다익스트라.boj_1753_최단경로;

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
	static int V, E, K;
	static List<Next>[] graph;
	static int[] dist;
	static final int INF = 3000000;
	
	static class Next implements Comparable<Next>{
		private int end;
		private int weight;
		
		public Next(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Next o) {
			return weight - o.weight;
		}
	}
	
	static void dijkstra(int k) {
		PriorityQueue<Next> pq = new PriorityQueue<>();
		pq.offer(new Next(k, 0));
		
		dist[k] = 0;
		
		boolean[] check = new boolean[V + 1];
		
		while (!pq.isEmpty()) {
			Next curNode = pq.poll();
			int cur = curNode.end;
			
			if (check[cur]) {
				continue;
			}
			
			check[cur] = true;
			
			for (Next node : graph[cur]) {
				if (dist[node.end] > dist[cur] + node.weight) {
					dist[node.end] = dist[cur] + node.weight;
					pq.offer(new Next(node.end, dist[node.end])); // priority queue에서 값이 작아 무한 루프 돌면 안되므로 dist[node.end]를 담아준다.
				}
			}
		}
	}
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			K = Integer.parseInt(br.readLine());
			
			graph = new ArrayList[V + 1];
			for (int u = 1; u <= V; u++) {
				graph[u]= new ArrayList<>();
			}
			
			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				graph[u].add(new Next(v, w));
			}
			
			dist = new int[V + 1];
			Arrays.fill(dist, INF);
			dijkstra(K);
			
			for (int u = 1; u <= V; u++) {
				sb.append(dist[u] >= INF ? "INF" : dist[u]).append("\n");
			}
			bw.write(sb.toString().trim());
			bw.flush();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
