package backjoon.최소공통조상.boj_1761_정점들의_거리;

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

public class 다익스트라_시간초과 {
	static int N, M;
	static List<Node>[] adjList;
	static int[] dist;
	static final int INF = 399_000_000;
	
	static class Node implements Comparable<Node> {
		int end;
		int weight;
		
		Node (int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static void dijkstra(int k) {
		Queue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(k, 0));
		
		dist[k] = 0;
		
		boolean[] check = new boolean[N + 1];
		
		while (!pq.isEmpty()) {
			Node curNode = pq.remove();
			int cur = curNode.end;
			
			if (check[cur]) {
				continue;
			}
			
			check[cur] = true;
			
			for (Node node : adjList[cur]) {
				if (dist[node.end] > dist[cur] + node.weight) {
					dist[node.end] = dist[cur] + node.weight;
					pq.add(new Node(node.end, dist[node.end]));
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			N = Integer.parseInt(br.readLine());
			
			adjList = new ArrayList[N + 1];
			for (int n = 1; n <= N; n++) {
				adjList[n] = new ArrayList<다익스트라_시간초과.Node>();
			}
			
			int u, v ,w;
			for (int n = 1; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				w = Integer.parseInt(st.nextToken());
				
				adjList[u].add(new Node(v, w));
				adjList[v].add(new Node(u, w));
			}

			dist = new int[N + 1];
			
			M = Integer.parseInt(br.readLine());
			int a, b, result;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
			
				Arrays.fill(dist, INF);
				
				dijkstra(a);
				result = dist[b];
				sb.append(result).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}