package algorithm.dfs.boj_1260_DFS와_BFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N, M, V;
	private static boolean[] visited;
	private static boolean[][] edges;
	private static StringBuilder sb;
	
	private static void dfs(int V) {
		visited[V] = true;
		sb.append(String.valueOf(V));
		sb.append(" ");
		for (int i = 0; i <= N; i++) {
			if (edges[V][i] && !visited[i]) {
				dfs(i);
			}
		}
	}
	
	private static String bfs(int V) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(V);
		visited[V] = true;
		sb.append(String.valueOf(V));
		sb.append(" ");
		
		while (!queue.isEmpty()) {
			int vertex = queue.poll();
			
			for (int i = 0; i < edges.length; i++) {
				if (edges[vertex][i] && !visited[i]) {
					queue.add(i);
					visited[i] = true;
					sb.append(String.valueOf(i));
					sb.append(" ");
				}
			}
		}
		
		return sb.toString().trim();
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		edges = new boolean[1001][1001];
		visited = new boolean[1001];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges[a][b] = edges[b][a] = true;
		}
		
		sb = new StringBuilder();
		dfs(V);
		bw.write(sb.toString().trim());
		bw.newLine();
		
		Arrays.fill(visited, false);
		sb = new StringBuilder();
		bfs(V);
		bw.write(sb.toString().trim());
		
		bw.flush();
		
		bw.close();
		br.close();
	}
}
