package backjoon.투포인터.boj_2003_수들의_합_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 시간비교 {
	static int N, M;
	static int[] A;
	static int start, end, sum, count;
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			A = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				A[n] = Integer.parseInt(st.nextToken());
			}

			long startTime = System.nanoTime();
			
			// O(N^3)
			count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sum = 0;
					for (int k = i; k <= j; k++) {
						sum += A[k];
					}
					if (sum == M) {
						count++;
					}
				}
			}
			
			long endTime = System.nanoTime();
			long computationTime = endTime - startTime;
			System.out.println();
			System.out.println("Computation time: " + computationTime + "nano sec");
			
			startTime = System.nanoTime();
			
			// O(N)
			start = end = sum = count = 0;
			while (true) {
				if (sum >= M) {
					sum -= A[start++];
				} else {
					if (end >= N) {
						break;
					}
					sum += A[end++];
				}
				
				if (sum == M) {
					count++;
				}
			}
			
			endTime = System.nanoTime();
			computationTime = endTime - startTime;
			System.out.println();
			System.out.println("Computation time: " + computationTime + "nano sec");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
