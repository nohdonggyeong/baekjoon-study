package sw검정.이것만_따라하자.실전_예제_풀어보기_15회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int T, n, Answer;
	static int[][] map;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			
			map = new int[n * 2][2 *n];
			int h = n * 2 - 1;
			int w = 0;
			int start = 0;
			int end = n * 2 - 1;
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				map[h][w] = Integer.parseInt(st.nextToken());
				w += 2;
				if (w >= end) {
					start += 1;
					end -= 1;
					h -= 1;
					w = start;
				}
			}
			
//			for (int i = 0; i < n * 2; i++) {
//				for (int j = 0; j < n * 2; j++) {
//					System.out.print(String.valueOf(map[i][j]) + " ");
//				}
//				System.out.println();
//			}
			
			Answer = 0;
			for (int j = 0; j < n * 2; j++) {
				int sum = 0;
				for (int i = 0; i < n * 2; i++) {
					sum += map[i][j];
				}
				Answer = Math.max(Answer, sum);
			}
			
			sb.append("#").append(t).append(" ").append(Answer).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}	
