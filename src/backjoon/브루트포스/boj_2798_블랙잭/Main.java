package backjoon.브루트포스.boj_2798_블랙잭;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] cards = new int[N];
		String[] inputArr = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(inputArr[i]);
		}
		
		// Process
		int sum, max = 0;
		for (int i = 0; i < N - 2; i++) {
			for (int j = i + 1; j < N - 1; j++) {
				for (int k = j + 1; k < N; k++) {
					sum = cards[i] + cards[j] + cards[k];
					if (sum <= M) {
						max = Math.max(max, sum);
					}
				}
			}
		}
		
		// Output
		bw.write(String.valueOf(max));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
