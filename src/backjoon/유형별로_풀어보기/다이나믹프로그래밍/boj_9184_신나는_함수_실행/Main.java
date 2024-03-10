package backjoon.유형별로_풀어보기.다이나믹프로그래밍.boj_9184_신나는_함수_실행;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int a, b, c;
	static int[][][] dp;
	static int w(int a, int b, int c) {
		if (a > 0 && a <= 20 && b > 0 && b <= 20 && c > 0 && c <= 20
				&& dp[a][b][c] != 0) {
			return dp[a][b][c];
		}
		
		if (a <= 0 || b <= 0 || c <= 0) {
			return 1;
		}
		if (a > 20 || b > 20 || c > 20 ) {
			return dp[20][20][20] = w(20, 20, 20);
		}
		if (a < b && b < c) {
			return dp[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
		}
		return dp[a][b][c] = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringBuilder sb = new StringBuilder();
			dp = new int[21][21][21];
			
			while (true) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				
				if (a == -1 && b == -1 && c == -1) {
					break;
				}
				
				sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ");
				sb.append(w(a, b, c)).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
