package backjoon.플로이드_워셜.boj_11404_플로이드;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static final int INF = 10_000_000;
	
	static void floydWarshall(int N) {
		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < N + 1; i++) {
				if (k == i) {
					continue;
				}
				for (int j = 1; j < N + 1; j++) {
					if (k == j || i == j) {
						continue;
					}
					if (map[i][j] > map[i][k] + map[k][j]) {
						map[i][j] = map[i][k] + map[k][j];
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
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (i != j) {
						map[i][j] = INF;
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
			
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (map[i][j] == INF) {
						sb.append(0).append(" ");
					} else {
						sb.append(map[i][j]).append(" ");
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
