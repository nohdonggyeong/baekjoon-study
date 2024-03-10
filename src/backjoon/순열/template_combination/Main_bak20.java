package backjoon.순열.template_combination;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStreamReader;

public class Main_bak20 {
	static int n, r;
	static int[] input, temp;
	static boolean[] visited;
	static List<int[]> output;
	
	static void combination(int start, int depth) {
		if (depth == r) {
			temp = new int[r];
			int index = 0;
			for (int i = 0; i < n; i++) {
				if (visited[i]) {
					temp[index++] = i;
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
		
		visited = new boolean[n];
		output = new ArrayList<int[]>();
		combination(0, 0);
		
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
