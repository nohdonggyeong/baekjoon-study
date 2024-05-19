package backjoon.알고리즘_분류.순열.boj_14889_스타트와_링크;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, R;
	static int[][] S;
	static boolean[] visited;
	static int result = Integer.MAX_VALUE;
	
	static void combination(int start, int depth) {
		if (depth == R) {
			getDiff();
			return;
		}
		
		for (int i = start; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				combination(i + 1, depth + 1);
				visited[i] = false;
			}
		}
	}
	
	static void getDiff() {
		int teamStartSum = 0;
		int teamLinkSum = 0;
		
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if (visited[i] && visited[j]) {
					teamStartSum += S[i][j] + S[j][i];
				} else if (!visited[i] && !visited[j]) {
					teamLinkSum += S[i][j] + S[j][i];
				}
			}
		}
		
		result = Math.min(result, Math.abs(teamStartSum - teamLinkSum));
	}
	
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			N = Integer.parseInt(br.readLine());
			
			S = new int[N][N];
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					S[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			R = N / 2;
			visited = new boolean[N];
			combination(0, 0);
			
			bw.write(String.valueOf(result));
			bw.flush();
		}
	}
}