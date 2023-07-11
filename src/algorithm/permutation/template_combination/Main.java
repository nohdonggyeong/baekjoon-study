package algorithm.permutation.template_combination;

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
	
	static void combination(int start, int depth) {
		if (depth == r) {
			int index = 0;
			node = new int[r];
			for (int i = 0; i < n; i ++) {
				if (visited[i]) {
					node[index] = input[i];
					index++;
				}
			}
			output.add(node);
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
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		n = 3;
		r = 2;
		
		input = new int[] {1, 2, 3};
		visited = new boolean[n];
		output = new ArrayList<>();
		
		combination(0, 0);
		
		for (int i = 0; i < output.size(); i++) {
			for (int j = 0; j < output.get(i).length; j++) {
				sb.append(String.valueOf(output.get(i)[j])).append(" ");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
	}
}
