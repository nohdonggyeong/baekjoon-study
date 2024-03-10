package sw검정.이것만_따라하자.실전_예제_풀어보기_4회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int[] dh = {1, 0, -1, 0};
	static int[] dw = {0, 1, 0, -1};
	
	static boolean isNearG(int h, int w) {
		for (int i = 0; i < 4; i++) {
			if (h + dh[i] >= 0 && h + dh[i] < N && w + dw[i] >= 0 && w + dw[i] < N) {
				if (map[h + dh[i]][w + dw[i]] == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	static int countB(int h, int w) {
		int count = 0;
		for (int i = 0; i < N; i++) {
			if (map[h][i] > 0) {
				count++;
			}
		}

		for (int i = 0; i < N; i++) {
			if (map[i][w] > 0) {
				count++;
			}
		}
		return count - 1;
	}
	
	static int getTotal() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < 3; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					if ("G".equals(st.nextToken())) {
						map[i][j] = 0;
					} else {
						map[i][j] = 1;
					}
				}
			}
//			printMap();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!isNearG(i, j)) {
						map[i][j] = countB(i, j);
					}
				}
			}
//			printMap();
			
			int result = getTotal();
			sb.append("#").append(t + 1).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(String.valueOf(map[i][j]) + " ");
			}
			System.out.println();
		}
	}
}
