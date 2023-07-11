package algorithm.permutation.template_permutation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int n, r;
	static int[] input, node;
	static boolean[] visited;
	static List<int[]> output;
	
	public static void permutation(int depth) {
		if (depth == r) {
			output.add(node.clone());
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				node[depth] = input[i];
				permutation(depth + 1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		n = 5;
		r = 3;
		
		input = new int[] {1, 2, 3, 4, 5};
		node = new int[r];
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

