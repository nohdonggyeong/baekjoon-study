package 알고리즘.최소공통조상.boj_1761_정점들의_거리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<Node>[] adjList;
	static int kMax;
	static int[][] parent;
	static int[] depth;
	static int[] dist;
	
	static void bfs(int root) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(root);
		
		boolean[] visited = new boolean[N + 1];
		visited[root] = true;
		
		int nextLevel = 1;
		int count = 0;
		int nowSize = 1;
		while (!queue.isEmpty()) {
			int now = queue.remove();
			for (Node next : adjList[now]) {
				if (!visited[next.end]) {
					visited[next.end] = true;
					queue.add(next.end);
					
					parent[0][next.end] = now;
					depth[next.end] = nextLevel;
					dist[next.end] = dist[now] + next.weight;
				}
			}
			count++;
			
			if (count == nowSize) {
				nextLevel++;
				count = 0;
				nowSize = queue.size();
			}
		}
	}
	
	static void fillParent() {
		for (int k = 1; k <= kMax; k++) {
			for (int n = 1; n <= N; n++) {
				parent[k][n] = parent[k - 1][parent[k - 1][n]];
			}
		}
	}
	
	static int lca(int a, int b) {
		if (depth[a] < depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		for (int k = kMax; k >= 0; k--) {
			if (Math.pow(2, k) <= depth[a] - depth[b]) {
				a = parent[k][a];
			}
		}
		
		if (a == b) {
			return a;
		}
		
		for (int k = kMax; k >= 0; k--) {
			if (parent[k][a] != parent[k][b]) {
				a = parent[k][a];
				b = parent[k][b];
			}
		}
		
		return parent[0][a];
	}
	
	static class Node {
		int end;
		int weight;
		
		Node (int end, int weight) {
			this.end = end;
			this.weight = weight;
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
				adjList[n] = new ArrayList<Main.Node>();
			}
			
			int u, v, w;
			for (int n = 1; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				w = Integer.parseInt(st.nextToken());
				adjList[u].add(new Node(v, w));
				adjList[v].add(new Node(u, w));
			}
			
			kMax = (int) Math.ceil(Math.log(N) / Math.log(2));
			parent = new int[kMax + 1][N + 1];
			depth = new int[N + 1];
			dist = new int[N + 1];
			bfs(1);
			fillParent();
			
			M = Integer.parseInt(br.readLine());
			int a, b, lca, distance;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				lca = lca(a, b);
				distance = dist[a] + dist[b] - 2 * dist[lca];
				sb.append(distance).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
