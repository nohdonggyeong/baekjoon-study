package 단계별로_풀어보기.약수_배수와_소수_2.boj_2485_가로수;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static long gcd(long a, long b) {
		if (a % b == 0) {
			return b;
		}
		return gcd(b, a % b);
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		long[] arr = new long[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		long[] diff = new long[n - 1];
		for (int i = 0; i < n - 1; i++) {
			diff[i] = arr[i + 1] - arr[i];
		}
		
		long resultDiff = diff[0];
		for (int i = 1; i < n - 1; i++) {
			resultDiff = gcd(resultDiff, diff[i]);
		}
		
		long count = (arr[arr.length - 1] - arr[0]) / resultDiff - arr.length + 1;
		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
		br.close();
	}
}
