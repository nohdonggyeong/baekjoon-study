package 알고리즘.다익스트라.boj_5972_택배_배송;

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
	static int[] dist;
	static final int INF = 50_000_000;
	
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
		
		boolean[] visited = new boolean[N + 1];
		
		while (!pq.isEmpty()) {
			Node curNode = pq.remove();
			int cur = curNode.end;
			
			if (visited[cur]) {
				continue;
			}
			
			visited[cur] = true;
			
			for (Node adjNode : adjList[cur]) {
				if (dist[adjNode.end] > dist[cur] + adjNode.weight) {
					dist[adjNode.end] = dist[cur] + adjNode.weight;
					
					pq.add(new Node(adjNode.end, dist[adjNode.end]));
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[N + 1];
			for (int n = 1; n <= N; n++) {
				adjList[n] = new ArrayList<Main.Node>();
				
			}
			
			int A, B, C;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				A = Integer.parseInt(st.nextToken());
				B = Integer.parseInt(st.nextToken());
				C = Integer.parseInt(st.nextToken());
				
				adjList[A].add(new Node(B, C));
				adjList[B].add(new Node(A, C));
			}
			
			dist = new int[N + 1];
			dijkstra(1);
			
			bw.write(String.valueOf(dist[N]));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
