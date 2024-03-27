package backjoon.단계별로_풀어보기.스택_큐_덱.boj_28279_덱_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		Deque<String> deque = new LinkedList<>();
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			if (str.contains("1 ")) {
				deque.addFirst(str.split(" ")[1]);
			} else if (str.contains("2 ")) {
				deque.addLast(str.split(" ")[1]);
			} else if ("3".equals(str)) {
				sb.append(deque.isEmpty() ? -1 : deque.pollFirst()).append("\n");
			} else if ("4".equals(str)) {
				sb.append(deque.isEmpty() ? -1 : deque.pollLast()).append("\n");				
			} else if ("5".equals(str)) {
				sb.append(deque.size()).append("\n");
			} else if ("6".equals(str)) {
				sb.append(deque.isEmpty() ? 1 : 0).append("\n");
			} else if ("7".equals(str)) {
				sb.append(deque.isEmpty() ? -1 : deque.peekFirst()).append("\n");
			} else if ("8".equals(str)) {
				sb.append(deque.isEmpty() ? -1 : deque.peekLast()).append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
