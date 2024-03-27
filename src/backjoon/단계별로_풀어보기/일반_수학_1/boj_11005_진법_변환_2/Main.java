package backjoon.단계별로_풀어보기.일반_수학_1.boj_11005_진법_변환_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		Stack<Integer> stack = new Stack<>();
		while (N >= B) {
			stack.push(N % B);
			N /= B;
		}
		stack.push(N);
		
		String result = "";
		while (!stack.isEmpty()) {
			int temp = stack.pop();
			if (temp > 9) {
				result += (char) (temp -10 + 65);
			} else {
				result += String.valueOf(temp);
			}
		}
		
		bw.write(result);
		bw.flush();
		bw.close();
		br.close();
	}
}
