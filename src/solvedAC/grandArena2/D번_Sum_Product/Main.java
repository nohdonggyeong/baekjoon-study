package solvedAC.grandArena2.D번_Sum_Product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] A;
	
	static int resultCount;
	
	static int getLeftValue(int start, int end) {
		int result = 0;
		for (int i = start; i <= end; i++) {
			result += A[i];
		}
		return result;
	}
	
	static int getRightValue(int start, int end) {
		int result = 1;
		for (int i = start; i <= end; i++) {
			result *= A[i];
		}
		return result;
	}
	
	public static void main(String args[]) throws IOException {
//		LocalDateTime start = LocalDateTime.now();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		resultCount = N;
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N && j < i + 50; j++) {
				if (getLeftValue(i, j) == getRightValue(i, j)) {
					resultCount++;
				}
			}
		}
		
		bw.write(String.valueOf(resultCount));
		bw.flush();
		
//		LocalDateTime end = LocalDateTime.now();
//		System.out.println("[Elapsed time: " + Duration.between(start, end).getSeconds() + " sec]");
		bw.close();
		br.close();
	}
}
