package backjoon.단계별로_풀어보기.스택_큐_덱.boj_2346_풍선_터뜨리기_ArrayDeque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		Deque<int[]> deque = new ArrayDeque<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		sb.append("1 ");
		int index = arr[0];
		for (int i = 1; i < n; i++) {
			deque.offer(new int[] {i + 1, arr[i]});
		}
		
		while (!deque.isEmpty()) {
			if (index > 0) {
				for (int i = 1; i < index; i++) {
					deque.offer(deque.poll());
				}
				int[] next = deque.poll();
				index = next[1];
				sb.append(next[0]).append(" ");
			} else {
				for (int i = 1; i < -index; i++) {
					deque.offerFirst(deque.pollLast());
				}
				int[] next = deque.pollLast();
				index = next[1];
				sb.append(next[0]).append(" ");
			}
		}
		
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}
