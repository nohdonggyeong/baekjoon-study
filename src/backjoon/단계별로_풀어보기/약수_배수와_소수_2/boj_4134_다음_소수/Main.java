package backjoon.단계별로_풀어보기.약수_배수와_소수_2.boj_4134_다음_소수;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static boolean isPrimeNumber(long n) {
		if (n == 0 || n == 1) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			long n = Long.parseLong(br.readLine());
			while (true) {
				if (isPrimeNumber(n)) {
					break;
				}
				n++;
			}
			sb.append(n);
			if (i < t - 1) sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
