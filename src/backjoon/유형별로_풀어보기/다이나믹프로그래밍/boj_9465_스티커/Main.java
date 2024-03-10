package backjoon.유형별로_풀어보기.다이나믹프로그래밍.boj_9465_스티커;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int T, n;
	static int[][] scores;
	static int[][] dp;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			T = Integer.parseInt(br.readLine());
			for (int t = 1; t <= T; t++) {
				 n = Integer.parseInt(br.readLine());
				 
				 scores = new int[2][n + 1];
				 st = new StringTokenizer(br.readLine());
				 for (int i = 1; i <= n; i++) {
					 scores[0][i] = Integer.parseInt(st.nextToken());
				 }
				 st = new StringTokenizer(br.readLine());
				 for (int i = 1; i <= n; i++) {
					 scores[1][i] = Integer.parseInt(st.nextToken());
				 }
				 
				 dp = new int[2][n + 1];
				 dp[0][1] = scores[0][1];
				 dp[1][1] = scores[1][1];
				 for (int i = 2; i <= n; i++) {
					 dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + scores[0][i];
					 dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + scores[1][i];
				 }
				 
				 sb.append(Math.max(dp[0][n], dp[1][n])).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
