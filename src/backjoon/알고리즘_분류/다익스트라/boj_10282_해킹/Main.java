package backjoon.알고리즘_분류.다익스트라.boj_10282_해킹;

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

	static int T, n, d, c, a, b, s;
	static List<Node>[] adjList;
	static int[] dist;
	static int count, time;
	
	static final int INF = 100_000_000;
	
	static void dijkstra(int start) {
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		Queue<Node> pq = new PriorityQueue<Main.Node>();
		pq.add(new Node(start, 0));
		
		boolean[] visited = new boolean[n + 1];
		
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
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			T = Integer.parseInt(br.readLine());
			for (int t = 0; t < T; t++) {
				st = new StringTokenizer(br.readLine());
				n = Integer.parseInt(st.nextToken());
				d = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				
				adjList = new ArrayList[n + 1];
				for (int i = 1; i <= n; i++) {
					adjList[i] = new ArrayList<Main.Node>();
				}
				
				for (int i = 0; i < d; i++) {
					st = new StringTokenizer(br.readLine());
					a = Integer.parseInt(st.nextToken());
					b = Integer.parseInt(st.nextToken());
					s = Integer.parseInt(st.nextToken());
					
					adjList[b].add(new Node(a, s));
				}
				
				dist = new int[n + 1];
				dijkstra(c);
				
				count = 0;
				time = 0;
				for (int i = 1; i <= n; i++) {
					if (dist[i] != INF) {
						count++;
						time = Math.max(time, dist[i]);
					}
				}
				sb.append(count).append(" ").append(time).append("\n");
				
			}
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
