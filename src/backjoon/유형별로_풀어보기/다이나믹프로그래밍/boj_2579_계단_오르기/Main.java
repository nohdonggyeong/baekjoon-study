package backjoon.유형별로_풀어보기.다이나믹프로그래밍.boj_2579_계단_오르기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int N;
	static int[] stairs;
	static int[] dp;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			N = Integer.parseInt(br.readLine());
			
			stairs = new int[N + 1];
			for (int n = 1; n <= N; n++) {
				stairs[n] = Integer.parseInt(br.readLine());
			}
			if (N == 1) {
				bw.write(String.valueOf(stairs[1]));
			} else {
				dp = new int[N + 1];
				dp[1] = stairs[1];
				dp[2] = stairs[1] + stairs[2];
				
				for (int i = 3; i <= N; i++) {
					dp[i] = Math.max(dp[i - 2], dp[i - 3] + stairs[i - 1]) + stairs[i];
				}
				
				bw.write(String.valueOf(dp[N]));	
			}
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
