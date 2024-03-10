package sw_expert_academy._sea_1234_비밀번호;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
	static int n;
	static String str;
	static Deque<Character> deque;
	
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			for (int t = 1; t <= 10; t++) {
				st = new StringTokenizer(br.readLine());
				n = Integer.parseInt(st.nextToken());
				str = st.nextToken();

				deque = new LinkedList<Character>();
				deque.addLast(str.charAt(0));
				for (int i = 1; i < n; i++) {
					if (!deque.isEmpty() && deque.peekLast() == str.charAt(i)) {
						deque.removeLast();
					} else {
						deque.add(str.charAt(i));
					}
				}
				
				sb.append("#").append(t).append(" ");
				while (!deque.isEmpty()) {
					sb.append(deque.removeFirst());
				}
				sb.append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		}
	}
}
