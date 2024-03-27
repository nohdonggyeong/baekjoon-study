package backjoon.알고리즘_분류.순열.template_permutation;

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
	static int[] input, temp;
	static boolean[] visited;
	static List<int[]> output;
	
	static void permutation(int depth) {
		if (depth == r) {
			output.add(temp.clone());
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				temp[depth] = input[i];
				permutation(depth + 1);
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
		input = new int[n];
//		Integer[] input = new Integer[n];
		temp = new int[r];
		visited = new boolean[n];
		output = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			input[i] = i + 1;
		}
//		Arrays.sort(input, Collections.reverseOrder());
//		for (int el : input) {
//			System.out.print(String.valueOf(el) + " ");
//		}
		
		permutation(0);
		for (int[] el : output) {
			for (int e : el) {
				sb.append(e).append(" ");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
