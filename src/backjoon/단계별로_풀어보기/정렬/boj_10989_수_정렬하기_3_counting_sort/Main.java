package backjoon.단계별로_풀어보기.정렬.boj_10989_수_정렬하기_3_counting_sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		int[] countingArr = new int[10001];
		for (int i = 0; i < n; i++) {
			countingArr[Integer.parseInt(br.readLine())]++;
		}
		
		for (int i = 1; i < 10001; i++) {
			while (countingArr[i]-- > 0) {
				sb.append(i).append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
