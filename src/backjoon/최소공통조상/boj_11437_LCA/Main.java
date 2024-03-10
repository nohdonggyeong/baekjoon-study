package backjoon.최소공통조상.boj_11437_LCA;

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
	static List<Integer>[] adjList;
	static int[] parent;
	static int[] depth;
	
	static void BFS() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(1);
		
		boolean[] visited = new boolean[N + 1];
		visited[1] = true;
		
		int nextLevel = 1;
		int count = 0;
		int nowSize = 1;
		while (!queue.isEmpty()) {
			int now = queue.poll();
			
			for (int next : adjList[now]) {
				if (!visited[next]) {
					parent[next] = now;
					depth[next] = nextLevel;
					
					queue.offer(next);
					visited[next] = true;
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
			int temp = b;
			b = a;
			a = temp;
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
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();

			N = Integer.parseInt(br.readLine());
			
			adjList = new ArrayList[N + 1];
			for (int n = 1; n <= N; n++) {
				adjList[n] = new ArrayList<Integer>();
			}
			
			int u, v;
			for (int n = 0; n < N - 1; n++) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				adjList[u].add(v);
				adjList[v].add(u);
			}
			
			parent = new int[N + 1];
			depth = new int[N + 1];
			BFS();
			
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
