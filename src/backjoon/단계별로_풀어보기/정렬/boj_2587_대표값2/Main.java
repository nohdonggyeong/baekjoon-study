package backjoon.단계별로_풀어보기.정렬.boj_2587_대표값2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int sum = 0;
		int[] arr = new int[5];
		for (int i = 0; i < 5; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}
		Arrays.sort(arr);
		
		bw.write(String.valueOf(sum / 5));
		bw.newLine();
		bw.write(String.valueOf(arr[2]));
		bw.flush();
		bw.close();
		br.close();
	}
}
