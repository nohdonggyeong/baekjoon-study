package 알고리즘.다익스트라.boj_1854_K번째_최단경로_찾기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, k;
	static List<Node>[] adjList;
	static Queue<Integer>[] dist;
	static final int INF = 1_000_001;
	
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
		dist[start].add(0);
		
		Queue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		
		while (!pq.isEmpty()) {
			Node curNode = pq.remove();
			
			for (Node adjNode : adjList[curNode.end]) {
				if (dist[adjNode.end].size() < k) {
					dist[adjNode.end].add(curNode.weight + adjNode.weight);
					pq.add(new Node(adjNode.end, curNode.weight + adjNode.weight));
				} else if (dist[adjNode.end].peek() > curNode.weight + adjNode.weight) {
					dist[adjNode.end].poll();
					dist[adjNode.end].add(curNode.weight + adjNode.weight);
					pq.add(new Node(adjNode.end, curNode.weight + adjNode.weight));
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringBuilder sb = new StringBuilder();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			int a, b, c;
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				
				adjList[a].add(new Node(b, c));
			}
			
			dist = new PriorityQueue[n + 1];
			for (int i = 1; i <= n; i++) {
				dist[i] = new PriorityQueue<>(Collections.reverseOrder());
			}
			dijkstra(1);
			
			for (int i = 1; i <= n; i++) {
				if (dist[i].size() == k) {
					sb.append(dist[i].peek()).append("\n");
				} else {
					sb.append("-1").append("\n");
				}
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
