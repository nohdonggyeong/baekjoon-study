package backjoon.단계별로_풀어보기.배열.boj_1546;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		double[] arr = new double[n];
		double max = Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}

		double sum = 0;
		for (int i = 0; i < n; i++) {
			arr[i] = arr[i] / max * 100;
			sum += arr[i];
		}
		double result = sum / n;
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
}
