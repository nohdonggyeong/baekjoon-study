package 알고리즘.최소공통조상.boj_8012_한동이는_영업사원;

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
	static int n, m;
	static List<Integer>[] tree;
	static int kMax;
	static int[][] parent;
	static int[] depth;
	static int[] dist;
	static int nowCity, nextCity, lca, distance, total;
	
	static void bfs(int root) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(root);
		
		boolean[] visited = new boolean[n + 1];
		visited[root] = true;
		
		int nextLevel = 1;
		int count = 0;
		int nowSize = 1;
		while (!queue.isEmpty()) {
			int now = queue.remove();
			for (int next : tree[now]) {
				if (!visited[next]) {
					visited[next] = true;
					queue.add(next);
					
					parent[0][next] = now;
					depth[next] = nextLevel;
					dist[next] = dist[now] + 1;
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
	
	static int findLca(int a, int b) {
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
		
		for (int k = kMax; k>= 0; k--) {
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
			
			n = Integer.parseInt(br.readLine());
			tree = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++) {
				tree[i] = new ArrayList<>();
			}
			
			int a, b;
			for (int i = 1; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				tree[a].add(b);
				tree[b].add(a);
			}
			
			kMax = (int) Math.ceil(Math.log(n) / Math.log(2));
			parent = new int[kMax + 1][n + 1];
			depth = new int[n + 1];
			dist = new int[n + 1];
			bfs(1);
			for (int k = 1; k <= kMax; k++) {
				for (int i = 1; i <= n; i++) {
					parent[k][i] = parent[k - 1][parent[k - 1][i]];
				}
			}
			
			m = Integer.parseInt(br.readLine());
			nowCity = 1;
			total = 0;
			for (int i = 0; i < m; i++) {
				nextCity = Integer.parseInt(br.readLine());
				if (nowCity == nextCity) {
					continue;
				}
				
				lca = findLca(nowCity, nextCity);
				distance = dist[nowCity] + dist[nextCity] - 2 * dist[lca];
				total += distance;
				
				nowCity = nextCity;
			}
			
			bw.write(String.valueOf(total));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
