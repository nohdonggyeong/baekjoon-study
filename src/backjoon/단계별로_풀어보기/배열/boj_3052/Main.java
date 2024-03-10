package backjoon.단계별로_풀어보기.배열.boj_3052;

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
		
		int[] arr = new int[10];
		for (int i = 0; i < 10; i++) {
			arr[i] = Integer.parseInt(br.readLine()) % 42;
		}
		Arrays.sort(arr);
		
		int num = arr[0];
		int cnt = 1;
		for (int i = 1; i < 10; i++) {
			if (num != arr[i]) {
				cnt++;
				num = arr[i];
			}
		}
		
		bw.write(String.valueOf(cnt));
		bw.flush();
		bw.close();
		br.close();
	}
}
