package backjoon.알고리즘_분류.순열.template_permutation_with_repetition;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_bak19 {
	static int N, R;
	static int[] input;
	static int[] temp;
	static List<int[]> output;

	static void permutationWithRepetition(int depth) {
		if (depth == R) {
			output.add(temp.clone());
			return;
		}
		
		for (int i = 0; i < N; i++) {
			temp[depth] = input[i];
			permutationWithRepetition(depth + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = 10;
		R = 3;
		
		input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = i + 1;
		}
		Arrays.sort(input);
		
//		for (int el : input) {
//			System.out.print(String.valueOf(el) + " ");
//		}
		
		temp = new int[R];
		output = new ArrayList<>();
		
		permutationWithRepetition(0);
		
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
