package 알고리즘.순열.boj_16637_괄호_추가하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N;
	static char[] expression;
	static boolean[] calculateFirst;
	
	static int n, r;
	static int[] input;
	static int[] temp;
	static List<int[]> output;
	
	static int maxResult;
	
	static void combinationRepetition(int start, int depth) {
		if (depth == r) {
			output.add(temp.clone());
			return;
		}
		
		for (int i = start; i < n; i += 2) {
			temp[depth] = input[i];
			combinationRepetition(i, depth + 1);
		}
	}
	
	static int calculate() {
		int result = Character.getNumericValue(expression[0]);
		
		for (int i = 0; i < N - 1; i += 2) {
			if (i + 3 <= N - 1 &&calculateFirst[i + 3]) {
				result = operate(result, expression[i + 1], operate(Character.getNumericValue(expression[i + 2]), expression[i + 3], Character.getNumericValue(expression[i + 4])));
				i += 2;
			} else {
				result = operate(result, expression[i + 1], Character.getNumericValue(expression[i + 2]));
			}
		}
		return result;
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
		
		expression = new char[N];
		String str = br.readLine();
		for (int i = 0; i < N; i++) {
			expression[i] = str.charAt(i);
		}
		
		if (expression.length == 1) {
			bw.write(Character.toString(expression[0]));
			bw.flush();
			
			bw.close();
			br.close();
			return;
		}
		if (expression.length == 3) {
			bw.write(String.valueOf(operate(Character.getNumericValue(expression[0]), expression[1] ,Character.getNumericValue(expression[2]))));
			bw.flush();
			
			bw.close();
			br.close();
			return;
		}
		
		n = N;
		r = (N - 3) / 4  + 1;
		
		input = new int[n];
		for (int i = 0; i < n; i++) {
			input[i] = i;
		}
		
		temp = new int[r];
		output = new ArrayList<>();
		combinationRepetition(1, 0);

		calculateFirst = new boolean[N];
		maxResult = calculate();
		for (int[] el : output) {
			calculateFirst = new boolean[N];
			for (int i = 0; i < el.length; i++) {
				if (i == 0 || i > 0 && el[i] - el[i - 1] >= 4) {
					calculateFirst[el[i]] = true;
				}
			}
			maxResult = Math.max(maxResult, calculate());
		}
		
		bw.write(String.valueOf(maxResult));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
