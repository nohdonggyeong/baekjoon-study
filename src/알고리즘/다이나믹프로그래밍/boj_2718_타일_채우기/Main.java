package 알고리즘.다이나믹프로그래밍.boj_2718_타일_채우기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int T, N;
	static int[] dp;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringBuilder sb = new StringBuilder();
			T = Integer.parseInt(br.readLine());
			for (int t = 1; t <= T; t++) {
				N = Integer.parseInt(br.readLine());
				if (N == 1) {
					sb.append("1").append("\n");
				} else {
					dp = new int[N + 1];
					dp[1] = 1;
					dp[2] = 5;
					for (int i = 3; i <= N; i++) {
						dp[i] = dp[i - 1] + 4 * dp[i - 2]; 
					}
					sb.append(dp[N]).append("\n");
				}
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
