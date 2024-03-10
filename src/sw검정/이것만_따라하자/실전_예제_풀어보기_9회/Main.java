package sw검정.이것만_따라하자.실전_예제_풀어보기_9회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {	
	static int T, N;
	static int[][] map;
	static int maxCount, minCount;
	static int[] dh = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dw = {0, 1, 1, 1, 0, -1, -1, -1};
	
	static boolean isMax(int i, int j) {
		for (int d = 0; d < 8; d++) {
			if (map[i + dh[d]][j + dw[d]] >= map[i][j]) {
				return false;
			}
		}
		return true;
	}
	
	static boolean isMin(int i, int j) {
		for (int d = 0; d < 8; d++) {
			if (map[i + dh[d]][j + dw[d]] <= map[i][j]) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxCount = 0;
			minCount = 0;
			
			for (int i = 1; i < N - 1; i++) {
				for (int j = 1; j < N - 1; j++) {
					if (isMax(i , j)) {
						maxCount++;
					} else if (isMin(i, j)) {
						minCount++;
					}
				}
			}
			
			sb.append("#").append(t + 1).append(" ").append(maxCount).append(" ").append(minCount).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
