package backjoon.순열.template_combination_with_repetition;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_bak01 {
	static int n, r;
	static int[] input;
	static int[] temp;
	static List<int[]> output;
	
	static void combinationDuplicate (int start, int depth) {
		if (depth == r) {
			output.add(temp.clone());
			return;
		}
		
		for (int i = start; i < n; i++) {
			temp[depth] = input[i];
			combinationDuplicate(i, depth + 1);
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
		
		input = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(input);
		
		temp = new int[r];
		output = new ArrayList<>();
		combinationDuplicate(0, 0);
		
		for (int[] el : output) {
			for (int i = 0; i < el.length; i++) {
				sb.append(String.valueOf(el[i])).append(" ");
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
		br.close();
	}
}
