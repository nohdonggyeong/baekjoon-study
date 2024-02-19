package 알고리즘.다이나믹프로그래밍.boj_11727_2xn_타일링_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int n;
	static int[] dp;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			n = Integer.parseInt(br.readLine());
			if (n == 1) {
				bw.write("1");
			} else {
				dp = new int[n + 1];
				
				dp[1] = 1;
				dp[2] = 3;
				for (int i = 3; i <= n; i++) {
					dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;
				}
				bw.write(String.valueOf(dp[n]));
			}
			
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
