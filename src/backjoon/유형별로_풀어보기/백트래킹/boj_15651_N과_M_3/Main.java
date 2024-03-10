package backjoon.유형별로_풀어보기.백트래킹.boj_15651_N과_M_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] selectedArr;
	static StringBuilder sb = new StringBuilder();
	
	static void dfs(int depth) {
		if (depth == M) {
			for (int el : selectedArr) {
				sb.append(String.valueOf(el));
				sb.append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			selectedArr[depth] = i + 1;
			dfs(depth + 1);
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		selectedArr = new int[M];
		
		dfs(0);
		
		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
		br.close();
	}
}
