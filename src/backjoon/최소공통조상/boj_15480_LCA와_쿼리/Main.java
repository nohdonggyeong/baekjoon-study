package backjoon.최소공통조상.boj_15480_LCA와_쿼리;

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
	static List<Integer>[] T;
	static int r, u, v;
	
	static int kMax;
	static int[][] parent;
	static int[] depth;
	
	static int lcaR, lcaU, lcaV, lca;
	
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
			for (int next : T[now]) {
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
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			
			T = new ArrayList[N + 1];
			for (int n = 1; n <= N; n++) {
				T[n] = new ArrayList<>();
			}
			
			for (int n = 1; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				T[u].add(v);
				T[v].add(u);
			}
			
			kMax = (int) Math.ceil(Math.log(N) / Math.log(2));
			parent = new int[kMax + 1][N + 1];
			depth = new int[N + 1];
			bfs(1);
			for (int k = 1; k <= kMax; k++) {
				for (int n = 1; n <= N; n++) {
					parent[k][n] = parent[k - 1][parent[k - 1][n]];
				}
			}
			
			M = Integer.parseInt(br.readLine());
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				r = Integer.parseInt(st.nextToken());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				
				lcaR = lca(u, v);
				lcaU = lca(r, v);
				lcaV = lca(r, u);
				
				if (depth[lcaR] >= Math.max(depth[lcaU], depth[lcaV])) {
					lca = lcaR;
				} else if (depth[lcaU] >= Math.max(depth[lcaR], depth[lcaV])) {
					lca = lcaU;
				} else if (depth[lcaV] >= Math.max(depth[lcaR], depth[lcaU])) {
					lca = lcaV;
				}
				
				sb.append(lca).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
