package 알고리즘.이진탐색.boj_1920_수_찾기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] A;
	
	static int binarySearch(int number) {
		int low = 0;
		int high = A.length - 1;
		
		while (low <= high) {
			int mid = (low + high) / 2;
			
			if (number < A[mid]) {
				high = mid - 1;
			} else if (number > A[mid]) {
				low = mid + 1;
			} else {
				return mid;
			}
		}
		
		return -1;
	}
	
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
			
			Arrays.sort(A);
			
			M = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				if (binarySearch(Integer.parseInt(st.nextToken())) >= 0) {
					bw.write("1");
					bw.newLine();
				} else {
					bw.write("0");
					bw.newLine();
				}
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
