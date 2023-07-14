package algorithm.permutation.template_permutation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int n, r;
	static int[] input, temp;
	static boolean[] visited;
	static List<int[]> output;
	
	public static void permutation(int depth) {
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
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		n = 5;
		r = 2;
		
		input = new int[] {1, 2, 3, 4, 5};
		temp = new int[r];
		visited = new boolean[n];
		output = new ArrayList<>();
		
		permutation(0);
		
		for (int i = 0; i < output.size(); i++) {
			for (int j = 0; j < r; j++) {
				sb.append(String.valueOf(output.get(i)[j])).append(" ");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
	}
}

