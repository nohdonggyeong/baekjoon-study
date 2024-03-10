package backjoon.유형별로_풀어보기.DFS.boj_11724_연결_요소의_개수;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_bak {
	static int N, M;
	static int[][] edges;
	static boolean[] visited;
	
	static void dfs(int node) {
		if (visited[node]) {
			return;
		}
		visited[node] = true;
		
		for (int i = 1; i <= N; i++) {
			if (edges[node][i] == 1) {
				dfs(i);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		edges = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges[a][b] = 1;
			edges[b][a] = 1;
		}

		// Process
		int result = 0;
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				dfs(i);
				result++;
			}
		}

		// Output
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
}
