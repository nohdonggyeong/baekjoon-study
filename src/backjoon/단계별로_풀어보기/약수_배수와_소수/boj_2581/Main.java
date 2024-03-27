package backjoon.단계별로_풀어보기.약수_배수와_소수.boj_2581;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int M, N;
	static boolean isPrimeNumber(int number) {
		if (number == 1) {
			return false;
		}
		for (int i = 2; i <number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		M = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		int sum = 0;
		int min = Integer.MAX_VALUE;
		for (int i = M; i <= N; i++) {
			if (isPrimeNumber(i)) {
				sum += i;
				min = Math.min(min, i);
			}
		}
		
		if (sum == 0) {
			bw.write(String.valueOf(-1));
		} else {
			bw.write(String.valueOf(sum));
			bw.newLine();
			bw.write(String.valueOf(min));
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
