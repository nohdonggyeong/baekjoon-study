package backjoon.알고리즘_분류.이진탐색.boj_1920_수_찾기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 시간비교 {
	static int N, M;
	static int[] A, numbers;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			long startTime = System.nanoTime();
			
			N = Integer.parseInt(br.readLine());
			
			A = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				A[n] = Integer.parseInt(st.nextToken());
			}
			
			M = Integer.parseInt(br.readLine());
			
			numbers = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				numbers[m] = Integer.parseInt(st.nextToken());
			}
			
			for (int m = 0; m < M; m++) {
				int number = numbers[m];
				boolean flag = false;
				for (int n = 0; n < N; n++) {
					if (A[n] == number) {
						flag = true;
						break;
					}
				}
				bw.write(flag ? "1" : "0");
				bw.newLine();
			}
			
			bw.flush();
			
			long endTime = System.nanoTime();
			long calculationTime = endTime - startTime;
			System.out.println("Calculation time: " + (calculationTime / 1000000000.0) + "초");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
