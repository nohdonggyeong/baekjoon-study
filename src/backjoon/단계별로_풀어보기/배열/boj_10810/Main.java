package backjoon.단계별로_풀어보기.배열.boj_10810;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int ball = Integer.parseInt(st.nextToken());
			for (int j = start - 1; j < end; j++) {
				arr[j] = ball;
			}
		}
		
		for (int i = 0; i < N; i++) {
			bw.write(String.valueOf(arr[i]));
			bw.write(" ");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}