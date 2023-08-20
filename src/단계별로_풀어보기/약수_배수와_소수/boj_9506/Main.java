package 단계별로_풀어보기.약수_배수와_소수.boj_9506;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb;
		
		while (true) {
			sb = new StringBuilder();
			int n = Integer.parseInt(br.readLine());
			if (n == -1) {
				break;
			}
			
			int sum = 0;
			Queue<Integer> queue = new LinkedList<>();
			for (int i = 1; i <= n / 2; i++) {
				if (n % i == 0) {
					queue.offer(i);
					sum += i;
				}
			}
			if (sum == n) {
				sb.append(n).append(" = ");
				while (!queue.isEmpty()) {
					sb.append(queue.poll()).append(" + ");
				}
				String result = sb.toString();
				bw.write(result.substring(0, result.length() - 3));
			} else {
				sb.append(n).append(" is NOT perfect.");
				bw.write(sb.toString());
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
