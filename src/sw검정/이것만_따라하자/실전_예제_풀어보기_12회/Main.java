package sw검정.이것만_따라하자.실전_예제_풀어보기_12회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int T, Y, X, N;
	static int[][] map;
	static int sum, total;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static int getSumFunction(int h, int w, int m) {
		for (int im = 0; im < m; im++) {
			String str = String.valueOf(map[h][w]);
			int d = Integer.parseInt(str.substring(0, 1));
			int steps = Integer.parseInt(str.substring(1, 2));
			
			h += dy[d - 1] * steps;
			w += dx[d - 1] * steps;
			if (h < 0 || h >= Y || w < 0 || w >= X) {
				return 0;
			}
		}
		return map[h][w] * 100;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			Y = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			map = new int[Y][X];
			for (int y = 0; y < Y; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < X; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			}

			total = 0;
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				int h = Integer.parseInt(st.nextToken()) - 1;
				int w = Integer.parseInt(st.nextToken()) - 1;
				int m = Integer.parseInt(st.nextToken());
				sum = -1000;
				
				sum += getSumFunction(h, w, m);
				total += sum;
			}
			
			sb.append("#").append(t).append(" ").append(total).append("\n");
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}
}	
