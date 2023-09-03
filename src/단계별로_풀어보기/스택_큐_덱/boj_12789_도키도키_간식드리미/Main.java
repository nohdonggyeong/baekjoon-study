package 단계별로_풀어보기.스택_큐_덱.boj_12789_도키도키_간식드리미;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int order = 1;
		Stack<Integer> stack = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			while (true) {
				if (order == num) {
					order += 1;
					break;
				} else {
					if (!stack.isEmpty() && stack.peek() == order) {
						stack.pop();
						order += 1;
					} else {
						stack.push(num);
						break;
					}
				}
			}
		}
		
		boolean result = true;
		for (int i = 0; i < stack.size(); i++) {
			if (stack.pop() != order) {
				result = false;
				break;
			} else {
				order += 1;
			}
		}
		
		bw.write(result ?  "Nice" : "Sad");
		bw.flush();
		bw.close();
		br.close();
	}
}
