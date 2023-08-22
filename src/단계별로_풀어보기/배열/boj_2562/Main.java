package 단계별로_풀어보기.배열.boj_2562;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Integer[] arr = new Integer[9];
		Integer[] arrBak = new Integer[9];
		for (int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		arrBak = arr.clone();
		Arrays.sort(arr, Collections.reverseOrder());
		
		int max = arr[0];
		
		for (int i = 0; i < 9; i++) {
			if (arrBak[i] == max) {
				bw.write(String.valueOf(max));
				bw.newLine();
				bw.write(String.valueOf(i + 1));
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
}