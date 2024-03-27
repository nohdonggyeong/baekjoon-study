package backjoon.알고리즘_분류.다익스트라.boj_5719_거의_최단_경로;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<Node>[] adjList;
	static int start, end;
	static int[] dist;
	static List<Integer>[] prev;
	static boolean[][] checked;
	static final int INF = 500_001;
	
	static class Node implements Comparable<Node> {
		int end;
		int weight;
		
		Node(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static void dijkstra(int start) {
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		Queue<Node> pq = new PriorityQueue<Main.Node>();
		pq.add(new Node(start, 0));
		
		boolean[] visited = new boolean[N];
		
		while (!pq.isEmpty()) {
			Node curNode = pq.remove();
			int cur = curNode.end;
			
			if (visited[cur]) {
				continue;
			}
			
			visited[cur] = true;
			
			for (Node adjNode : adjList[cur]) {
				if (checked[cur][adjNode.end]) {
					continue;
				}
				
				if (dist[adjNode.end] > dist[cur] + adjNode.weight) {
					dist[adjNode.end] = dist[cur] + adjNode.weight;
					
					pq.add(new Node(adjNode.end, dist[adjNode.end]));
					
					prev[adjNode.end].clear();
					prev[adjNode.end].add(cur);
				} else if (dist[adjNode.end] == dist[cur] + adjNode.weight) {
					prev[adjNode.end].add(cur);
				}
			}
		}
	}
	
	static void backTracking(int start, int node) {
		if (node == start) {
			return;
		}
		
		for (int prevNode : prev[node]) {
			if (checked[prevNode][node]) {
				continue;
			}
			
			checked[prevNode][node] = true;
			backTracking(start, prevNode);
		}
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			while (true) {
				st = new StringTokenizer(br.readLine());
				N = Integer.parseInt(st.nextToken());
				M = Integer.parseInt(st.nextToken());
				
				if (N == 0 && M == 0) {
					break;
				}
				
				adjList = new ArrayList[N];
				for (int n = 0; n < N; n++) {
					adjList[n] = new ArrayList<Main.Node>();
				}
				
				st = new StringTokenizer(br.readLine());
				start = Integer.parseInt(st.nextToken());
				end = Integer.parseInt(st.nextToken());
				
				int u, v, w;
				for (int m = 0; m < M; m++) {
					st = new StringTokenizer(br.readLine());
					u = Integer.parseInt(st.nextToken());
					v = Integer.parseInt(st.nextToken());
					w = Integer.parseInt(st.nextToken());
					
					adjList[u].add(new Node(v, w));
				}
				
				dist = new int[N];
				prev = new ArrayList[N];
				for (int n = 0; n < N; n++) {
					prev[n] = new ArrayList<Integer>();
				}
				checked = new boolean[N][N];
				
				dijkstra(start);
				
				backTracking(start, end);
				
				dijkstra(start);
				
				sb.append(dist[end] == INF ? "-1" : dist[end]).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
