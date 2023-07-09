package algorithm.dfs.boj_1325_효율적인_해킹;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_bak {
	static int N, M, count, max;
	static List<List<Integer>> graph;
	static boolean[] visited;
	static Map<Integer, Integer> countMap;
	
	static void dfs(int v) {
		for(int el : graph.get(v)) {
			if (!visited[el]) {
				count += 1;
				visited[el] = true;
				dfs(el);			
				visited[el] = false;
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		visited = new boolean[N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			graph.get(b).add(a);
		}
		
		max = 0;
		countMap = new HashMap<>();
		for (int i = 0; i < N; i++) {
			count = 1;
			visited[i] = true;
			dfs(i);
			visited[i] =false;
			countMap.put(i, count);
			max = Math.max(max, count);
		}
		
		for(int el : countMap.keySet()) {
			if (countMap.get(el) == max) {
				sb.append(String.valueOf(el + 1));
				sb.append(" ");
			}
		}
		bw.write(sb.toString().trim());
		
		bw.flush();
		bw.close();
		br.close();
	}
}
