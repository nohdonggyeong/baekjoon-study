package backjoon.단계별로_풀어보기.약수_배수와_소수_2.boj_17103_골드바흐_파티션;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		boolean[] num = new boolean[1000001];
		num[0] = true;
		num[1] = true;
		for (int i = 2; i <= Math.sqrt(1000000); i++) {
			if (!num[i]) {
				for (int j = i + i; j <= 1000000; j += i) {
					num[j] = true;
				}
			}
		}
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			int result = 0;
			for (int i = 2; i <= n / 2; i++) {
				if (!num[i] && !num[n - i]) {
					result++;
				}
			}
			sb.append(result);
			if (t < T) {
				sb.append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
