package 단계별로_풀어보기.스택_큐_덱.boj_11866_요세푸스_문제_0;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			queue.offer(i);
		}
		
		int number = 0;
		Queue<Integer> result = new LinkedList<>();
		while (!queue.isEmpty()) {
			number += 1;
			if (number < k) {
				queue.offer(queue.poll());
			} else {
				result.offer(queue.poll());
				number = 0;
			}
		}
		
		sb.append("<");
		while (!result.isEmpty()) {
			sb.append(result.poll()).append(", ");
		}
		sb.replace(sb.length() - 2, sb.length(), ">");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
