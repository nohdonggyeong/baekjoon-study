package backjoon.알고리즘_분류.순열.template_permutation_with_repetition;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main_bak20 {
	static int n, r;
	static int[] input, temp;
	static List<int[]> output;
	
	static void permutationWithRepetition(int depth) {
		if (depth == r) {
			output.add(temp.clone());
			return;
		}
		
		for (int i = 0; i < n; i++) {
			temp[depth] = i;
			permutationWithRepetition(depth + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		n = 10;
		r = 3;
		
		input = new int[n];
		for (int i = 0; i < n; i++) {
			input[i] = i;
		}
		
		temp = new int[r];
		output = new ArrayList<int[]>();
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
