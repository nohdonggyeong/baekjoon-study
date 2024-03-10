package backjoon.단계별로_풀어보기.배열.boj_10811;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = i + 1;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			Stack<Integer> stack = new Stack<>();
			for (int j = start; j <= end; j++) {
				stack.push(arr[j - 1]);
			}
			for (int j = start; j <= end; j++) {
				arr[j - 1] = stack.pop();
			}
		}
		
		for (int i = 0; i < N; i++) {
			bw.write(String.valueOf(arr[i] + " "));
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
