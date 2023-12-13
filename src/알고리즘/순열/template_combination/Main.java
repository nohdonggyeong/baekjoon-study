package 알고리즘.순열.template_combination;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N, R;
	static int[] input;
	static int[] temp;
	static boolean[] visited;
	static List<int[]> output;
	
	static void combination(int start, int depth) {
		if (depth == R) {
			int index = 0;
			temp = new int[R];
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					temp[index++] = input[i];
				}
			}
			output.add(temp.clone());
			return;
		}
		
		for (int i = start; i < N; i++) {
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
		
		N = 10;
		R = 3;
		input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = i + 1;
		}
		
//		for (int el : input) {
//			System.out.print(String.valueOf(el) + " ");
//		}
		
		visited = new boolean[N];
		output = new ArrayList<>();
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
