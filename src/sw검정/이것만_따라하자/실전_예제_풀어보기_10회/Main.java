package sw검정.이것만_따라하자.실전_예제_풀어보기_10회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {	
	static int T;
	static Queue<Integer> bingo1;
	static Queue<Integer> bingo2;
	static Queue<Integer> bingo3;
	static Queue<Integer> numbers;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			bingo1 = new LinkedList<>();
			for (int i = 0; i < 5; i++) {
				bingo1.offer(Integer.parseInt(st.nextToken()));
			}
			
			st = new StringTokenizer(br.readLine());
			bingo2 = new LinkedList<>();
			for (int i = 0; i < 5; i++) {
				bingo2.offer(Integer.parseInt(st.nextToken()));
			}
			
			st = new StringTokenizer(br.readLine());
			bingo3 = new LinkedList<>();
			for (int i = 0; i < 5; i++) {
				bingo3.offer(Integer.parseInt(st.nextToken()));
			}
			
			st = new StringTokenizer(br.readLine());
			numbers = new LinkedList<>();
			while (st.hasMoreTokens()) {
				numbers.offer(Integer.parseInt(st.nextToken()));
			}
			
			while (!numbers.isEmpty()) {
				int number = numbers.poll();
				
				if (bingo1.size() > 0 && number == bingo1.peek()) {
					bingo1.poll();
				}
				if (bingo2.size() > 0 && number == bingo2.peek()) {
					bingo2.poll();
				}
				if (bingo3.size() > 0 && number == bingo3.peek()) {
					bingo3.poll();
				}
			}
			sb.append("#").append(t + 1).append(" ").append(bingo1.size() == 0 && bingo2.size() == 0 && bingo3.size() == 0 ? "1" : "0").append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
