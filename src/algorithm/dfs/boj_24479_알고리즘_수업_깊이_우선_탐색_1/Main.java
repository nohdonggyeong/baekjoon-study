package algorithm.dfs.boj_24479_알고리즘_수업_깊이_우선_탐색_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, R;
	static List<List<Integer>> graph;
	static int[] checked;
	static int cnt;
	
	static void dfs(int vertex) {
		checked[vertex] = cnt;
		for (int i = 0; i < graph.get(vertex).size(); i++) {
			int nextVertex = graph.get(vertex).get(i);
			if (checked[nextVertex] == 0) {
				cnt += 1;
				dfs(nextVertex);
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
//		LocalDateTime start = LocalDateTime.now();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		for (int n = 0; n <= N; n++) {
			graph.add(new ArrayList<>());
		}
		checked = new int[N + 1];
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
		for (int n = 1; n <= N; n++) {
			Collections.sort(graph.get(n));
		}
		
		cnt = 1;
		dfs(R);
		
		for (int n = 1; n <= N; n++) {
			bw.write(String.valueOf(checked[n]));
			bw.newLine();
		}
		bw.flush();
		
//		LocalDateTime end = LocalDateTime.now();
//		System.out.println();
//		System.out.println("[Elapsed time: " + Duration.between(start, end).getSeconds() + " sec]");
		
		bw.close();
		br.close();
	}
}
