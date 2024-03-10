package backjoon.브루트포스.boj_1839_설탕_배달;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		// Process
		int result = Integer.MAX_VALUE;
		for (int i = 0; i <= N / 3; i++) {
			for (int j = 0; j <= N / 5; j++) {
				if (3 * i + 5 * j == N) {
					result = Math.min(result, i + j);
				}
			}
		}
		result = result > 5000 ? -1 : result;
		
		// Output
		bw.write(String.valueOf(result));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
