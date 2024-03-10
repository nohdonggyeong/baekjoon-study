package backjoon.순열.template_permutation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_bak18 {
	static int N, R;
	static int[] input, temp;
	static boolean[] visited;
	static List<int[]> output;
	
	static void permutation(int depth) {
		if (depth == R) {
			output.add(temp.clone());
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				temp[depth] = input[i];
				permutation(depth + 1);
				visited[i] = false;
			}
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = 10;
		R = 4;
		
		input = new int[N];
		temp = new int[R];
		visited= new boolean[N];
		output = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			input[i] = i + 1;
		}
		Arrays.sort(input);
//		for (int el : input) {
//			System.out.print(el + " ");
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
