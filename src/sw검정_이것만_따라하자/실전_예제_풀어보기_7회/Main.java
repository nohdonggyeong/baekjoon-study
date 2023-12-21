package sw검정_이것만_따라하자.실전_예제_풀어보기_7회;

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
	static int N;
	static int i, j;
	static int P;
	static boolean[][] isTrap;
	static int D;
	static Queue<int[]> queue;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			i = Integer.parseInt(st.nextToken());
			j = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			
			isTrap = new boolean[N + 1][N + 1];
			st = new StringTokenizer(br.readLine());
			for (int p = 0; p < P; p++) {
				isTrap[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
			}

			D = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			queue = new LinkedList<>();
			for (int d = 0; d < D; d++) {
				queue.offer(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
			}
			
			while (!queue.isEmpty()) {
				if (i <= 0 || j <= 0 || i >= N + 1 || j >= N + 1) {
					i = 0;
					j = 0;
					break;
				}
				if (isTrap[i][j]) {
					i = 0;
					j = 0;
					break;
				}
				
				int[] move = queue.poll();
				int direction = move[0];
				int steps = move[1];
				
				switch (direction) {
				case 1:
					i -= steps;
					break;
				case 2:
					i -= steps;
					j += steps;
					break;
				case 3:
					j += steps;
					break;
				case 4:
					i += steps;
					j += steps;
					break;
				case 5:
					i += steps;
					break;
				case 6:
					i += steps;
					j -= steps;
					break;
				case 7:
					j -= steps;
					break;
				case 8:
					i -= steps;
					j -= steps;
					break;
				default:
					break;
				}
			}
			
			sb.append("#").append(t + 1).append(" ").append(i).append(" ").append(j).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
}
