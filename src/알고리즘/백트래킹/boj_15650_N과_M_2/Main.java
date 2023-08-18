package 알고리즘.백트래킹.boj_15650_N과_M_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[] visited;
	static int[] selectedArr;
	
	static StringBuilder sb = new StringBuilder();
	
	static void dfs(int startNum, int depth) {
		if (depth == M) {
			for (int el : selectedArr) {
				sb.append(String.valueOf(el));
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = startNum; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				selectedArr[depth] = i + 1;
				dfs(i + 1, depth + 1);
				visited[i] = false;
			}
				
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N];
		selectedArr = new int[M];
		
		dfs(0, 0);
		
		bw.write(String.valueOf(sb));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
