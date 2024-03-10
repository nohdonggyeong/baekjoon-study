package backjoon.유형별로_풀어보기.DFS.boj_1260_DFS와_BFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, V;
	static List<List<Integer>> graph;
	static boolean[] visit;
	static List<Integer> resultList;
	
	static void dfs(int vertex) {
		List<Integer> nextList = graph.get(vertex);
		Collections.sort(nextList);
		
		for (int el : nextList) {
			if (!visit[el]) {
				visit[el] = true;
				resultList.add(el);
				dfs(el);
			}
		}
	}
	
	static void bfs(int vertex) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(vertex);
		
		while (!queue.isEmpty()) {
			int v = queue.poll();
			List<Integer> nextList = graph.get(v);
			Collections.sort(nextList);
			
			for (int el : nextList) {
				if (!visit[el]) {
					visit[el] = true;
					resultList.add(el);
					queue.offer(el);
				}
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb;
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
//		LocalDateTime startDFS = LocalDateTime.now();
		visit = new boolean[N + 1];
		resultList = new ArrayList<>();
		visit[V] = true;
		resultList.add(V);
		dfs(V);
		
		sb = new StringBuilder();
		for (int el : resultList) {
			sb.append(String.valueOf(el)).append(" ");
		}
		bw.write(sb.toString().trim());
		bw.newLine();
//		LocalDateTime endDFS = LocalDateTime.now();
		
//		LocalDateTime startBFS = LocalDateTime.now();
		visit = new boolean[N + 1];
		resultList = new ArrayList<>();
		visit[V] = true;
		resultList.add(V);
		bfs(V);
		
		sb = new StringBuilder();
		for (int el : resultList) {
			sb.append(String.valueOf(el)).append(" ");
		}
		bw.write(sb.toString().trim());
		bw.newLine();
//		LocalDateTime endBFS = LocalDateTime.now();
		
		bw.flush();
		
//		System.out.println();
//		System.out.println("[Elapsed DFS time: " + Duration.between(startDFS, endDFS).getNano() + " ns]");
//		System.out.println("[Elapsed BFS time: " + Duration.between(startBFS, endBFS).getNano() + " ns]");
		
		bw.close();
		br.close();
	}
}
