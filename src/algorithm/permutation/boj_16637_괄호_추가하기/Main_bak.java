package algorithm.permutation.boj_16637_괄호_추가하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main_bak {
	static int N;
	static char[] expression;
	static boolean[] calculateFirst;
	
	static int n, r;
	static int[] input;
	static int[] temp;
	static List<int[]> output;
	
	static void combinationRepetition(int start, int depth) {
		if (depth == r) {
			output.add(temp.clone());
			return;
		}
		
		for (int i = start; i < n; i += 4) {
			temp[depth] = input[i];
			combinationRepetition(i, depth + 1);
		}
	}
	
	static void calculate() {
		int result = Character.getNumericValue(expression[0]);
		if (N > 1) {
			result = operate(result, expression[1], Character.getNumericValue(expression[2]));
			for (int i = 3; i < N; i += 2) {
				if (char)
			}
		}
	}
	
	static int operate(int x, char op, int y) {
		int result = x;
		switch(op) {
		case '+':
			result += y;
			break;
		case '-':
			result -= y;
			break;
		case '*':
			result *= y;
			break;
		default:
			break;
		}
		return result;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		for (int i = 0; i < N; i++) {
			expression[i] = str.charAt(i);
		}
		
		n = N;
		int maxR = (N - 3) / 4  + 1;
		
		input = new int[n];
		
		for (r = 0; r <= maxR; r++) {
			temp = new int[r];
			output = new ArrayList<>();
			combinationRepetition(1, 0);
			
			for (int[] el : output) {
				calculateFirst = new boolean[N];
				for (int e : el) {
					calculateFirst[e] = true;
				}
			}
		}
	}
}
