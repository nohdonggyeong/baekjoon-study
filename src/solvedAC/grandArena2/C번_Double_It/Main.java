package solvedAC.grandArena2.C번_Double_It;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] A;
	static int resultMin;
	
	static int findMax() {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, A[i]);
		}
		return max;
	}
	
	static int findMin() {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			min = Math.min(min, A[i]);
		}
		return min;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		resultMin = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			A[i] *= 2;
			resultMin = Math.min(resultMin, findMax() - findMin());
			A[i] /= 2;
		}
		
		bw.write(String.valueOf(resultMin));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
