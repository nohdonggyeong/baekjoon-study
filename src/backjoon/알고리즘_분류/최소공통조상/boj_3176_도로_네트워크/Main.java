package backjoon.알고리즘_분류.최소공통조상.boj_3176_도로_네트워크;

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
	static int N, K;
	static List<Node>[] adjList;
	static int kMax;
	static int[][] parent;
	static int[] depth;
	static int[][] minDist, maxDist;
	static int min, max;
	
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
					minDist[0][next.end] = next.weight;
					maxDist[0][next.end] = next.weight;
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
	
	static void lca(int a, int b) {
		if (depth[a] < depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		for (int k = kMax; k >= 0; k--) {
			if (Math.pow(2, k) <= depth[a] - depth[b]) {
				min = Math.min(min, minDist[k][a]);
				max = Math.max(max, maxDist[k][a]);
				a = parent[k][a];
			}
		}
		
		if (a == b) {
//			return a;
			return;
		}
		
		for (int k = kMax; k >= 0; k--) {
			if (parent[k][a] != parent[k][b]) {
				min = Math.min(min, Math.min(minDist[k][a], minDist[k][b]));
				max = Math.max(max, Math.max(maxDist[k][a], maxDist[k][b]));
				a = parent[k][a];
				b = parent[k][b];
			}
		}
		
//		return parent[0][a];
		min = Math.min(min, Math.min(minDist[0][a], minDist[0][b]));
		max = Math.max(max, Math.max(maxDist[0][a], maxDist[0][b]));
	}
	
	static class Node{
		int end;
		int weight;
		
		Node(int end, int weight) {
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
			
			kMax = (int) (Math.log(N) / Math.log(2)) + 1;
			parent = new int[kMax + 1][N + 1];
			depth = new int[N + 1];
			minDist = new int[kMax + 1][N + 1];
			maxDist = new int[kMax + 1][N + 1];
			bfs(1);
			for (int k = 1; k <= kMax; k++) {
				for (int n = 1; n <= N; n++) {
					parent[k][n] = parent[k - 1][parent[k - 1][n]];
					minDist[k][n] = Math.min(minDist[k - 1][n], minDist[k - 1][parent[k - 1][n]]);
					maxDist[k][n] = Math.max(maxDist[k - 1][n], maxDist[k - 1][parent[k - 1][n]]);
				}
			}
			
			K = Integer.parseInt(br.readLine());
			int a, b, lca;
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				min = Integer.MAX_VALUE;
				max = Integer.MIN_VALUE;
				lca(a, b);
				
				sb.append(min).append(" ").append(max).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

