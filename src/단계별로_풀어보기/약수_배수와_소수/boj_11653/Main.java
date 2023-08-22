package 단계별로_풀어보기.약수_배수와_소수.boj_11653;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {
	static boolean isPrimeNumber(int number) {
		if (number == 1) {
			return false;
		}
		for (int i = 2; i <= number / 2; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		
		for (int i = 2; i <= Math.sqrt(n); i++) {
			while (n % i == 0) {
				bw.write(String.valueOf(i));
				bw.newLine();
				n /= i;
			}
		}
		if (n != 1) {
			bw.write(String.valueOf(n));
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
