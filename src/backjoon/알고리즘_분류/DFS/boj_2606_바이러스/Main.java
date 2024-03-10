package backjoon.알고리즘_분류.DFS.boj_2606_바이러스;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<List<Integer>> network;
	static boolean[] visit;
	static int count;
	
	static void dfs(int vertex) {
		for (int el : network.get(vertex)) {
			if (!visit[el]) {
				count += 1;
				visit[el] = true;
				dfs(el);
			}
		}
	}
	public static void main(String args[]) throws IOException {
//		LocalDateTime start = LocalDateTime.now();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		network = new ArrayList<>();
		for (int n = 0; n <= N; n++) {
			network.add(new ArrayList<>());
		}
		
		visit = new boolean[N + 1];
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			network.get(u).add(v);
			network.get(v).add(u);
		}
		
		count = 0;
		visit[1] = true;
		dfs(1);
		
		bw.write(String.valueOf(count));
		bw.flush();
		
//		LocalDateTime end = LocalDateTime.now();
//		System.out.println();
//		System.out.println("[Elapsed time: " + Duration.between(start, end).getSeconds() + " sec]");
		
		bw.close();
		br.close();
	}
}
