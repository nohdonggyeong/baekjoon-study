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

public class Main2 {
	static int V, E, K;
	static List<Next>[] graph;
	
	static int[] dist;
	static final int INF = Integer.MAX_VALUE;

	static class Next implements Comparable<Next> {
		int end, weight;

		public Next(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Next o) {
			return weight - o.weight;
		}
	}

	static void dijkstra(int start) {
		PriorityQueue<Next> queue = new PriorityQueue<Main2.Next>();
		boolean[] check = new boolean[V + 1];
		queue.offer(new Next(start, 0));
		dist[start] = 0;
		
		while (!queue.isEmpty()) {
			Next curNode = queue.poll();
			int cur = curNode.end;
			
			if (check[cur]) {
				continue;
			}
			
			check[cur] = true;
			for (Next node : graph[cur]) {
				if (dist[node.end] > dist[cur] + node.weight) {
					dist[node.end]= dist[cur] + node.weight;
					queue.offer(new Next(node.end, dist[node.end]));
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
			for (int i = 1; i <= V; i++) {
				graph[i] = new ArrayList<Main2.Next>();
			}
			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				graph[start].add(new Next(end, weight));
			}
			
			dist = new int[V + 1];
			Arrays.fill(dist, INF);
			
			dijkstra(K);
			
			for (int i = 1; i <= V; i++) {
				if (dist[i] == INF) {
					sb.append("INF").append("\n");
				} else {
					sb.append(dist[i]).append("\n");
				}
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
