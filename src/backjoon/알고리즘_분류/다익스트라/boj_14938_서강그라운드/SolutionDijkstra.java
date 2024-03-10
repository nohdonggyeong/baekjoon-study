package backjoon.알고리즘_분류.다익스트라.boj_14938_서강그라운드;

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

public class SolutionDijkstra {
	static int n, m, r;
	static int[] items;
	static List<Node>[] adjList;
	static int[] dist;
	static final int INF = 1501;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			items = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				items[i] = Integer.parseInt(st.nextToken());
			}
			
			adjList = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			int a, b, l;
			for (int i = 0; i < r; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				l = Integer.parseInt(st.nextToken());
				
				adjList[a].add(new Node(b, l));
				adjList[b].add(new Node(a, l));
			}
			
			dist = new int[n + 1];
			int count;
			int result = 0;
			for (int i = 1; i <= n; i++) {
				dijkstra(i);
				
				count = 0;
				for (int j = 1; j <= n; j++) {
					if (dist[j] <= m) {
						count += items[j];
					}
				}
				
				result = Math.max(result, count);
			}
			
			bw.write(String.valueOf(result));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void dijkstra(int start) {
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		Queue<Node> pq = new PriorityQueue<SolutionDijkstra.Node>();
		pq.add(new Node(start, 0));
		
		boolean[] visited = new boolean[n + 1];
		
		while (!pq.isEmpty()) {
			Node curNode = pq.remove();
			int cur = curNode.end;
			
			if (visited[cur]) {
				continue;
			}
			visited[cur] = true;
			
			for (Node nextNode : adjList[cur]) {
				if (dist[nextNode.end] > dist[cur] + nextNode.weight) {
					dist[nextNode.end] = dist[cur] + nextNode.weight;
				}
				
				pq.add(new Node(nextNode.end, dist[nextNode.end]));
			}
		}
	}
	
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
}
