package backjoon.알고리즘_분류.최소공통조상.boj_13511_트리와_쿼리_2;

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
	static List<Node>[] tree;
	static int kMax;
	static int[][] parent;
	static int[] depth;
	static long[] dist;
	
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
			for (Node nextNode : tree[now]) {
				int next = nextNode.end;
				if (!visited[next]) {
					visited[next] = true;
					queue.add(next);
					
					parent[0][next] = now;
					depth[next] = nextLevel;
					dist[next] = dist[now] + nextNode.weight; 
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
	
	static int findKth(int u, int v, int lca, int k) {
		int lcaOrder = depth[u] - depth[lca] + 1;
		if (k == lcaOrder) {
			return lca;
		} else if (k < lcaOrder) {
			int depthK = depth[u] - k + 1;
			for (int km = kMax; km >= 0; km--) {
				if (depthK <= depth[parent[km][u]]) {
					u = parent[km][u];
				}
			}
			return u;
		} else {
			int depthK = depth[lca] + (k - (depth[u] - depth[lca])) - 1;
			for (int km = kMax; km >= 0; km--) {
				if (depthK <= depth[parent[km][v]]) {
					v = parent[km][v];
				}
			}
			return v;
		}
	}
	
	static class Node {
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
			tree = new ArrayList[N + 1];
			for (int n = 1; n <= N; n++) {
				tree[n] = new ArrayList<Main.Node>();
			}
			
			int u, v, w;
			for (int n = 1; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				w = Integer.parseInt(st.nextToken());
				tree[u].add(new Node(v, w));
				tree[v].add(new Node(u, w));
			}
			
			kMax = (int) Math.ceil(Math.log(N) / Math.log(2));
			parent = new int[kMax + 1][N + 1];
			depth = new int[N + 1];
			dist = new long[N + 1];
			bfs(1);
			for (int k = 1; k <= kMax; k++) {
				for (int n = 1; n <= N; n++) {
					parent[k][n] = parent[k - 1][parent[k - 1][n]];
				}
			}
			
			M = Integer.parseInt(br.readLine());
			int op, k, lca, kth;
			long distance;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				op = Integer.parseInt(st.nextToken());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				lca = lca(u, v);
				if (op == 1) {
					distance = dist[u] + dist[v] - 2 * dist[lca];
					sb.append(distance).append("\n");
				} else if (op == 2) {
					k = Integer.parseInt(st.nextToken());
					kth = findKth(u, v, lca, k);
					sb.append(kth).append("\n");
				}
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
