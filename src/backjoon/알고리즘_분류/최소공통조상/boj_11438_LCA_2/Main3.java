package backjoon.알고리즘_분류.최소공통조상.boj_11438_LCA_2;

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

public class Main3 {
	static int N, M;
	static List<Integer>[] adjList;
	
	static int kMax;
	static int[][] parent;
	static int[] depth;
	
	static void bfs(int root) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(root);
		
		boolean[] visited = new boolean[N + 1];
		visited[root] = true;
		
		int nextLevel = 1;
		int count = 0;
		int nowSize = 1;
		while (!queue.isEmpty()) {
			int now = queue.remove();
			
			for (int next : adjList[now]) {
				if (!visited[next]) {
					visited[next] = true;
					queue.add(next);
					
					parent[0][next] = now;
					depth[next] = nextLevel;
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
	
	static int LCA(int a, int b) {
		if (depth[a] < depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		for (int k = kMax; k >= 0; k--) {
			if (Math.pow(2, k) <= depth[a] - depth[b]) {
				if (depth[b] <= depth[parent[k][a]]) {
					a = parent[k][a];
				}
			}
		}
		
		for (int k = kMax; k >= 0; k--) {
			if (a != b) {
				if (parent[k][a] != parent[k][b]) {
					a = parent[k][a];
					b = parent[k][b];
				}
			}
		}
		
		int result = a;
		if (a != b) {
			result = parent[0][result];
		}
		
		return result;
	}
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			N = Integer.parseInt(br.readLine());
			
			adjList = new ArrayList[N + 1];
			for (int n = 1; n <= N; n++) {
				adjList[n] = new ArrayList<>();
			}
			
			int u, v;
			for (int n = 1; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				
				adjList[u].add(v);
				adjList[v].add(u);
			}
			
			kMax = 0;
			int temp = 1;
			while (temp <= N) {
				temp *= 2;
				kMax++;
			}
			
			parent = new int [kMax + 1][N + 1];
			depth = new int[N + 1];
			bfs(1);
			for (int k = 1; k <= kMax; k++) {
				for (int n = 1; n <= N; n++) {
					parent[k][n] = parent[k-1][parent[k-1][n]];
				}
			}
			
			M = Integer.parseInt(br.readLine());
			int a, b, result;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				result = LCA(a, b);
				sb.append(result).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
