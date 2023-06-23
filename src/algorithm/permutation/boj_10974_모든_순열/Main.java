package algorithm.permutation.boj_10974_모든_순열;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int N, R;
	static int[] arr; // 출력 용도
	static boolean[] visit; // 확인 용도
	static StringBuilder sb = new StringBuilder();
	
	static void backTracking(int depth) {
		if (depth == R) {
			for (int val : arr) {
				sb.append(val).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visit[i]) {
				visit[i] = true;
				arr[depth] = i + 1;
				backTracking(depth + 1);
				visit[i] = false;
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		R = N;
		
		arr = new int[N];
		visit = new boolean[N];
		
		backTracking(0);
		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
		br.close();	}
}
