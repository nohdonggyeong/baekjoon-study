package backjoon.알고리즘_분류.순열.template_combination_with_repetition;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main_bak18 {
	static int N, R;
	static Integer[] input;
	static int[] temp;
	static boolean[] visited;
	static List<int[]> output;
	
	static void combinationWithRepetition(int start, int depth) {
		if (depth == R) {
			output.add(temp.clone());
			return;
		}
		
		for (int i = start; i < N; i++) {
			temp[depth] = input[i];
			combinationWithRepetition(i, depth + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = 10;
		R = 4;
		input = new Integer[N];
		temp = new int[R];
		for (int i = 0; i < N; i++) {
			input[i] = i + 1;
		}
		Arrays.sort(input, Collections.reverseOrder());
//		for (int el : input) {
//			System.out.print(el + " ");
//		}
		
		visited = new boolean[N];
		output = new ArrayList<>();
		combinationWithRepetition(0, 0);
		
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
