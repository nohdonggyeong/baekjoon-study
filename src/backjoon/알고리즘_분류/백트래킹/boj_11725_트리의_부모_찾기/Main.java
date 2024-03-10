package backjoon.알고리즘_분류.백트래킹.boj_11725_트리의_부모_찾기;

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
	static int N;
	static List<List<Integer>> tree;
	static boolean[] visit;
	static int[] parentNode;
	
	static void dfs(int node) {
		List<Integer> list = tree.get(node);
		for (int el : list) {
			if (!visit[el]) {
				visit[el] = true;
				parentNode[el] = node;
				dfs(el);
				visit[el] = false;
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
//		LocalDateTime start = LocalDateTime.now();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		tree = new ArrayList<>();
		for (int n = 0; n <= N; n++) {
			tree.add(new ArrayList<>());
		}
		
		for (int n = 0; n < N - 1; n++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			tree.get(u).add(v);
			tree.get(v).add(u);
		}
		
		visit = new boolean[N + 1];
		parentNode = new int[N + 1];
		dfs(1);
		
		for (int i = 2; i <= N; i++) {
			bw.write(String.valueOf(parentNode[i]));
			bw.newLine();
		}
		bw.flush();
		
//		LocalDateTime end = LocalDateTime.now();
//		System.out.println();
//		System.out.println("[Elpased time: " + Duration.between(start, end).getSeconds() + " sec]");
		
		bw.close();
		br.close();
	}
}
