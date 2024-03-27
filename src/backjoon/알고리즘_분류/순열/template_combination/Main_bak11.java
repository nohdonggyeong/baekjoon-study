package backjoon.알고리즘_분류.순열.template_combination;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_bak11 {
	static int n, r;
	static Integer[] input;
	static boolean[] visit;
	static int[] temp;
	static List<int[]> output;
	
	static void combination(int start, int depth) {
		if (depth == r) {
			temp = new int[r];
			int index = 0;
			for (int i = 0; i < n; i++) {
				if (visit[i]) {
					temp[index++] = input[i];
				}
			}
			output.add(temp.clone());
			return;
		}
		
		for (int i = start; i < n; i++) {
			if (!visit[i]) {
				visit[i] = true;
				combination(i + 1, depth + 1);
				visit[i] = false;
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		System.setIn(new FileInputStream("src\\algorithm\\permutation\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		input = new Integer[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);
//		Arrays.sort(input, Collections.reverseOrder());
		
		visit = new boolean[n];
		temp = new int[r];
		output = new ArrayList<>();
		combination(0, 0);
		
		for (int[] el : output) {
			for (int e : el) {
				sb.append(String.valueOf(e)).append(" ");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}
}
