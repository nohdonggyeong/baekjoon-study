package backjoon.플로이드_워셜.boj_11404_플로이드;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main2 {
	static int N, M;
	static int map[][];
	static final int INF = 10_000_000;
	
	static void floydWarshall(int N) {
		for (int k = 1; k <= N; k++) {
			for (int r = 1; r <= N; r++) {
				if (k == r) {
					continue;
				}
				for (int c = 1; c <= N; c++) {
					if (k == c || r == c) {
						continue;
					}
					if (map[r][c] > map[r][k] + map[k][c]) {
						map[r][c] = map[r][k] + map[k][c];
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringBuilder sb = new StringBuilder();
			StringTokenizer st;
			
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			map = new int[N + 1][N + 1];
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					if (r != c) {
						map[r][c] = INF;
					}
				}
			}
			
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				map[start][end] = Math.min(map[start][end], weight);
			}
			
			floydWarshall(N);
			
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					if (map[r][c] == INF) {
						sb.append(0).append(" ");
					} else {
						sb.append(map[r][c]).append(" ");
					}
				}
				sb.append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
