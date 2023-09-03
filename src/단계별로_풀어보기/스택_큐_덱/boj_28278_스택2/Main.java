package 단계별로_풀어보기.스택_큐_덱.boj_28278_스택2;

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
		
		int n = Integer.parseInt(br.readLine());
		Stack<String> stack = new Stack<>();
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			if (str.contains("1 ")) {
				stack.push(str.split(" ")[1]);
			} else if ("2".equals(str)) {
				sb.append(stack.isEmpty() ? -1 : stack.pop()).append("\n");
			} else if ("3".equals(str)) {
				sb.append(stack.size()).append("\n");
			} else if ("4".equals(str)) {
				sb.append(stack.isEmpty() ? 1 : 0).append("\n");
			} else if ("5".equals(str)) {
				sb.append(stack.isEmpty() ? -1 : stack.peek()).append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
