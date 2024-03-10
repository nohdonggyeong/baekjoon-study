package sw검정.이것만_따라하자.실전_예제_풀어보기_20회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int T, N;
	static boolean[][] map;
	static int[] dh = {-1, 0, 1, 0};
	static int[] dw = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new boolean[N][N];
			for (int h = 0; h < N; h++) {
				st = new StringTokenizer(br.readLine());
				for (int w = 0; w < N; w++) {
					if ("B".equals(st.nextToken())) {
						map[h][w] = true;
						for (int i = 0; i < 4; i++) {
							int nh = h + dh[i];
							int nw = w + dw[i];
							if (nh >= 0 && nh < N && nw >= 0 && nw < N) {
								map[nh][nw] = true;
							}
						}
					}
				}
			}
			
			int count = 0;
			for (int h = 0; h < N; h++) {
				for (int w = 0; w < N; w++) {
					if (map[h][w]) {
						count += 1;
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(N * N - count).append("\n");
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}
}
