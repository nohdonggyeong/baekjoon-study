package backjoon.알고리즘_분류.DFS.boj_15900_나무_탈출;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, answer;
	static boolean[] visited;
	static List<List<Integer>> tree;
	
	static void dfs(int node, int count) {
		if (node != 1 && tree.get(node).size() == 1) {
			answer += count;
		}
		
		for (int el : tree.get(node)) {
			if(!visited[el]) {
				visited[el] = true;
				dfs(el, count + 1);	
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N + 1];
		
		tree = new ArrayList<>();
		for (int n = 0; n <= N; n++) {
			tree.add(new ArrayList<>());
		}
		
		for (int n = 0; n < N - 1; n++) {
			st = new StringTokenizer(br.readLine());
			int a  = Integer.parseInt(st.nextToken());
			int b  = Integer.parseInt(st.nextToken());
			
			tree.get(a).add(b);
			tree.get(b).add(a);
		}
		
		answer = 0;
		visited[1] = true;
		dfs(1, 0);
		
		bw.write(answer % 2 == 0 ? "No" : "Yes");
		bw.flush();
		bw.close();
		br.close();
	}
}
