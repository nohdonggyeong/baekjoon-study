package 알고리즘.최소공통조상.boj_11437_LCA;

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

public class Main1 {
	static int N, M;
	static List<Integer>[] tree;
	static int[] parent;
	static int[] depth;
	static boolean[] visited;
	static int result;
	
	static void BFS() {
		int startNode = 1;
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(startNode);
		
		visited[startNode] = true;
		
		int level = 1;
		int nowSize = 1;
		int count = 0;
		while (!queue.isEmpty()) {
			int nowNode = queue.poll();
			for (int next : tree[nowNode]) {
				if (!visited[next]) {
					visited[next] = true;
					queue.add(next);
					parent[next] = nowNode;
					depth[next] = level;
				}
			}
			count++;
			if (count == nowSize) {
				count = 0;
				nowSize = queue.size();
				level++;
			}
		}
	}
	
	static int LCA(int a, int b) {
		if (depth[a] < depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		while (depth[a] != depth[b]) {
			a = parent[a];
		}
		
		while (a != b) {
			a = parent[a];
			b = parent[b];
		}
		
		return a;
	}

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringBuilder sb = new StringBuilder();
			StringTokenizer st;
			
			N = Integer.parseInt(br.readLine());
			tree = new ArrayList[N + 1];
			for (int n = 1; n <= N; n++) {
				tree[n] = new ArrayList<Integer>();
			}
			
			int u, v;
			for (int n = 1; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				tree[u].add(v);
				tree[v].add(u);
			}
			
			depth = new int[N + 1];
			parent = new int[N + 1];
			visited = new boolean[N + 1];
			
			BFS();
			
			M = Integer.parseInt(br.readLine());
			int a, b;
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
