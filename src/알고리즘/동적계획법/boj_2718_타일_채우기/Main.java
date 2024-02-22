package 알고리즘.동적계획법.boj_2718_타일_채우기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int T, N;
	static int[] d;
	
	static int dp(int n) {
		int result = 0;
		
		if (n == 0 || n == 1) {
			return 1;
		}
		
		if (n == 2) {
			return 5;
		}
		
		if (d[n] != 0) {
			return d[n];
		}
		
		result = dp(n - 1) + 4 * dp(n - 2);
		
		for (int i = 3; i <= n; i++) {
			if (i % 2 == 0) {
				result += 3 * dp(n - i);
			} else {
				result += 2 * dp(n - i);
			}
		}
		
		return d[n] = result;
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringBuilder sb = new StringBuilder();
			
			T = Integer.parseInt(br.readLine());
			for (int t =0; t < T; t++) {
				N = Integer.parseInt(br.readLine());
				
				d = new int[N + 1];
				int answer = dp(N);
				
				sb.append(answer).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
