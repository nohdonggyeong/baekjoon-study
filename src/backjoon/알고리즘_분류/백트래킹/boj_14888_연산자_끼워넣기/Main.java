package backjoon.알고리즘_분류.백트래킹.boj_14888_연산자_끼워넣기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] A, opCnt;
	static int max, min;
	
	static void backTracking(int depth, int calculation) {
		if (depth == N) {
			max = Math.max(max, calculation);
			min = Math.min(min, calculation);
		}
		
		for (int i = 0; i < 4; i++) {
			if (opCnt[i] > 0) {
				opCnt[i]--;
				
				switch (i) {
					case 0:
						backTracking(depth + 1, calculation + A[depth]);
						break;
					case 1:
						backTracking(depth + 1, calculation - A[depth]);
						break;
					case 2:
						backTracking(depth + 1, calculation * A[depth]);
						break;
					case 3:
						backTracking(depth + 1, calculation / A[depth]);
						break;
					default:
						break;
				}
				
				opCnt[i]++;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			N = Integer.parseInt(br.readLine());
			
			A = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			
			opCnt = new int[4];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				opCnt[i] = Integer.parseInt(st.nextToken());
			}
			
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			backTracking(1, A[0]);
			
			StringBuilder sb = new StringBuilder();
			sb.append(max).append("\n").append(min);
			bw.write(sb.toString());
			bw.flush();
		}
	}
}
