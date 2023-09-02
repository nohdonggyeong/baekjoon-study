package 단계별로_풀어보기.정렬.boj_1427_소트인사이드;

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
		
		String str = br.readLine();
		Integer[] arr = new Integer[str.length()];
		for (int i = 0; i < str.length(); i++) {
			arr[i] = Character.getNumericValue(str.charAt(i));
		}
		Arrays.sort(arr, Collections.reverseOrder());
		for (int i = 0; i < arr.length; i++) {
			bw.write(String.valueOf(arr[i]));
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
