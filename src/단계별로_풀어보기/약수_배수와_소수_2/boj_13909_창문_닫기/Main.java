package 단계별로_풀어보기.약수_배수와_소수_2.boj_13909_창문_닫기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		boolean[] arr = new boolean[n + 1]; // 연다. false.
		for (int i = 2; i <= n; i++) {
			for (int j = 1; j <= n / i; j++) {
				arr[i * j] = !arr[i * j];
			}
		}
		
		int count = 0;
		for (int i = 1; i <= n; i++) {
			if (!arr[i]) {
				count++;
			}
		}
		
		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
		br.close();
	}
}
