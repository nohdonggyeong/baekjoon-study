package 알고리즘.다익스트라.boj_2211_네트워크_복구;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<Node>[] adjList;
	static int[] dist, prev;
	static final int INF = 10_001;
	
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
					
					prev[adjNode.end] = cur;
				}
			}
		}
	}
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringBuilder sb = new StringBuilder();
			
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
			prev = new int[N + 1];
			dijkstra(1);
			
			HashSet<HashSet<Integer>> hs = new HashSet<HashSet<Integer>>();
			HashSet<Integer> temp;
			for (int n = 2; n <= N; n++) {
				temp = new HashSet<Integer>();
				temp.add(n);
				temp.add(prev[n]);
				hs.add(temp);
			}
			
			sb.append(hs.size()).append("\n");
			for (HashSet<Integer> el : hs) {
				boolean flag = false;
				for (int e : el)  {
					sb.append(e);
					if (!flag) {
						sb.append(" ");
						flag = true;
					} else {
						sb.append("\n");
					}
				}
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
