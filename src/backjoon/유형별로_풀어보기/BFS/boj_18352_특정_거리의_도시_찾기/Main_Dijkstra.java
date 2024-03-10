package backjoon.유형별로_풀어보기.BFS.boj_18352_특정_거리의_도시_찾기;

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

public class Main_Dijkstra {
	static int N, M, K, X;
	static List<Node>[] graph;
	static long[] dist;
	static final long INF = 300_000_000_000L;
	
	static void dijkstra(int start) {
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		Queue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		
		boolean[] visited = new boolean[N + 1];
		
		while (!pq.isEmpty()) {
			Node nowNode = pq.remove();
			int now = nowNode.end;
			
			if (visited[now]) {
				continue;
			}
			
			visited[now] = true;
			
			for (Node nextNode : graph[now]) {
				if (dist[nextNode.end] > dist[now] + nextNode.weight) {
					dist[nextNode.end] = dist[now] + nextNode.weight;
					pq.add(new Node(nextNode.end, dist[nextNode.end]));
				}
			}
		}
	}
	
	static class Node implements Comparable<Node> {
		int end;
		long weight;
		
		Node(int end, long weight) {
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return Long.compare(this.weight, o.weight);
		}
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringBuilder sb = new StringBuilder();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[N + 1];
			for (int n = 1; n <= N; n++) {
				graph[n] = new ArrayList<>();
			}
			
			int A, B;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				A = Integer.parseInt(st.nextToken());
				B = Integer.parseInt(st.nextToken());
				graph[A].add(new Node(B, 1));
			}
			
			dist = new long[N + 1];
			dijkstra(X);
			
			boolean flag = false;
			for (int n = 1; n <= N; n++) {
				if (dist[n] == K) {
					flag = true;
					sb.append(n).append("\n");
				}
			}
			
			if (flag) {
				bw.write(sb.toString().trim());
			} else {
				bw.write("-1");
			}			
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
