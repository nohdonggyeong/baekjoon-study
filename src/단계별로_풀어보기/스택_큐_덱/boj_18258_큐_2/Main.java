package 단계별로_풀어보기.스택_큐_덱.boj_18258_큐_2;

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
		int n = Integer.parseInt(br.readLine());
		Deque<String> queue = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			if (str.contains("push ")) {
				queue.offer(str.split(" ")[1]);
			} else if ("pop".equals(str)) {
				sb.append(queue.isEmpty() ? -1 : queue.poll()).append("\n"); 
			} else if ("size".equals(str)) {
				sb.append(queue.size()).append("\n");
			} else if ("empty".equals(str)) {
				sb.append(queue.isEmpty() ? 1 : 0).append("\n");
			} else if ("front".equals(str)) {
				sb.append(queue.isEmpty() ? -1 : queue.peekFirst()).append("\n");
			} else if ("back".equals(str)) {
				sb.append(queue.isEmpty() ? -1 : queue.peekLast()).append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
