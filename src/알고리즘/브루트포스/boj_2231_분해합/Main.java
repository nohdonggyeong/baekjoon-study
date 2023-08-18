package 알고리즘.브루트포스.boj_2231_분해합;

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
		int result = 0;
		for (int i = 1; i < N -1; i++) {
			int temp = i;
			int sum = 0;
			while (temp >= 10) {
				sum += temp % 10;
				temp /= 10;
			}
			sum += temp;
			if (i + sum == N) {
				result = i;
				break;
			}
		}
		// Output
		bw.write(String.valueOf(result));
		
		bw.close();
		br.close();
	}
}
