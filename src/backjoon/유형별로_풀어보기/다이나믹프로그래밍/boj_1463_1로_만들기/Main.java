package backjoon.유형별로_풀어보기.다이나믹프로그래밍.boj_1463_1로_만들기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int N;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		
		for (int i = 2; i <= N; i++) {
			// 1을 빼는 이전 값에서 거슬러서 올라가 횟수 1 추가
			dp[i] = dp[i - 1] + 1;
			if (i % 2 == 0) {
				// 1을 빼는 이전 값에서 거슬러서 올라가 횟수 1 추가하는 것과 2가 나눠졌던 값과 횟수 비교
				dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			}
			if (i % 3 == 0) {
				// 1을 빼는 이전 값에서 거슬러서 올라가 횟수 1 추가하는 것과 2가 나눠졌던 값과 횟수 비교
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);
			}
		}
		
		bw.write(String.valueOf(dp[N]));
		bw.flush();
		bw.close();
		br.close();
	}
}
