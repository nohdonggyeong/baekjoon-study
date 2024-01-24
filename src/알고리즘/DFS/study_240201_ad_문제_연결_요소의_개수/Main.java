package 알고리즘.DFS.study_240201_ad_문제_연결_요소의_개수;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<Integer>[] adjList;
	static boolean[] visited;
	static int count;
	
	static void dfs(int u) {
		for (int v : adjList[u]) {
			if (!visited[v]) {
				visited[v] = true;
				dfs(v);				
			}
		}
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[N + 1];
			for (int n = 1; n <= N; n++) {
				adjList[n] = new ArrayList<>();
			}
			
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				adjList[u].add(v);
				adjList[v].add(u);
			}
			
			visited = new boolean[N + 1];
			count = 0;
			for (int n = 1; n <= N; n++) {
				if (!visited[n]) {
					visited[n] = true;
					count += 1;
					dfs(n);
				}
			}
			
			bw.write(String.valueOf(count));
			bw.flush();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
