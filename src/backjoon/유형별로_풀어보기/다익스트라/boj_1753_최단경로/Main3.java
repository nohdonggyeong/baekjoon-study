package backjoon.유형별로_풀어보기.다익스트라.boj_1753_최단경로;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main3 {
	static int V, E, K;
	static List<Next>[] graph;
	static int[] dist;
	static final int INF = 3000000; // E * W: 오버플로우 나지 않기 위함
	
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
	
	// graph는 연결 관계와 가중치의 구현체
	// queue와 Node는 다익스트라 알고리즘을 수행하는 작업물
	// dist는 출발노드로부터 각 노드의 최단 거리 산출물
	static void dijkstra(int start) {
		PriorityQueue<Next> queue = new PriorityQueue<Main3.Next>();
		queue.offer(new Next(start, 0));

		boolean[] visited = new boolean[V + 1];
		
		dist = new int[V + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		while (!queue.isEmpty()) {
			Next curNode = queue.poll();
			int cur = curNode.end;
			
			if (visited[cur]) {
				continue;
			}
			
			visited[cur] = true;
			for (Next node : graph[cur]) {
				if (dist[node.end] > dist[cur] + node.weight) {
					dist[node.end] = dist[cur] + node.weight;
					queue.offer(new Next(node.end, dist[node.end] - dist[cur]));
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
				graph[u] = new ArrayList<Main3.Next>();
			}
			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(br.readLine());
				graph[Integer.parseInt(st.nextToken())].add(new Next(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			dijkstra(K);
			
			for (int u = 1; u <= V; u++) {
				if (INF == dist[u]) {
					sb.append("INF").append("\n");
				} else {
					sb.append(dist[u]).append("\n");
				}
			}
			bw.write(sb.toString().trim());
			bw.flush();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
