package backjoon.단계별로_풀어보기.스택_큐_덱.boj_10773_제로;

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
		
		Stack<String> stack = new Stack<>();
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			String num = br.readLine();
			if ("0".equals(num)) {
				stack.pop();
			} else {
				stack.push(num);
			}
		}
		
		int sum = 0;
		while (!stack.isEmpty()) {
			sum += Integer.parseInt(stack.pop());
		}
		bw.write(String.valueOf(sum));
		bw.flush();
		bw.close();
		br.close();
	}
}
