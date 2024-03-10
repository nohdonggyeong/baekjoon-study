package 알고리즘.다익스트라.boj_11779_최소비용_구하기_2_경로출력;

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
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static List<Node>[] adjList;
	
	static int[] dist, prev;
	static final int INF = 100_000_001;
	
	static int start, end;
	
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

					prev[adjNode.end] = cur;
					pq.add(new Node(adjNode.end, dist[adjNode.end]));
				} else if (dist[adjNode.end] == dist[cur] + adjNode.weight && prev[adjNode.end] > cur) {
					prev[adjNode.end] = cur;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());
			
			adjList = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++) {
				adjList[i] = new ArrayList<Main.Node>();
			}
			
			int u, v, w;
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				w = Integer.parseInt(st.nextToken());
				
				adjList[u].add(new Node(v, w));
			}
			
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			
			dist = new int[n + 1];
			prev = new int[n + 1];
			dijkstra(start);
			
			Stack<Integer> pathStack = new Stack<Integer>();
			int temp = end;
			while (temp != 0) {
				pathStack.push(temp);
				temp = prev[temp];
			}
			
			sb.append(dist[end]).append("\n");
			sb.append(pathStack.size()).append("\n");
			while (!pathStack.isEmpty()) {
				sb.append(pathStack.pop()).append(" ");
			}
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
