package backjoon.단계별로_풀어보기.스택_큐_덱.boj_4949_균형잡힌_세상;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			String str = br.readLine();
			if (".".equals(str)) {
				break;
			}
			
			boolean result = true;
			Stack<Character> stack = new Stack<>();
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '(' || str.charAt(i) == '[') {
					stack.push(str.charAt(i));
				} else if (str.charAt(i) == ')') {
					if (stack.isEmpty() || stack.pop() != '(') {
						result = false;
						break;
					}
				} else if (str.charAt(i) == ']') {
					if (stack.isEmpty() || stack.pop() != '[') {
						result = false;
						break;
					}
				}
			}
			if (!stack.isEmpty()) {
				result = false;
			}
			
			sb.append(result ? "yes" : "no").append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
