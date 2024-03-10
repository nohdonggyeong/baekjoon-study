package backjoon.순열.template_combination;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main_bak17 {
	static int n, r;
	static Integer[] input;
	static boolean[] visited;
	static int[] temp;
	static List<int[]> output;
	
	static void combination(int start, int depth) {
		if (depth == r) {
			int index = 0;
			temp = new int[r];
			for (int i = 0; i < n; i++) {
				if (visited[i]) {
					temp[index++] = input[i];
				}
			}
			output.add(temp.clone());
			return;
		}
		
		for (int i = start; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				combination(i + 1, depth + 1);
				visited[i] = false;
			}
		}
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		n = 10;
		r = 3;
		input = new Integer[n];
		for (int i = 0; i < n; i++) {
			input[i] = i + 1;
		}
//		Arrays.sort(input, Collections.reverseOrder());
//		for (int el : input) {
//			System.out.print(String.valueOf(el) + " ");
//		}
		
		visited = new boolean[n];
		temp = new int[r];
		output = new ArrayList<int[]>();
		combination(0, 0);
		
		for (int[] el : output) {
			for (int e : el) {
				sb.append(String.valueOf(e)).append(" ");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
