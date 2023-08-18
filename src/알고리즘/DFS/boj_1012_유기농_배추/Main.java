package 알고리즘.DFS.boj_1012_유기농_배추;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

public class Main {
	static int T, M, N, K;
	static int[][] map;
	static boolean[][] visit;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	static int count;
	
	static void dfs(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
				continue;
			}
			if (map[nr][nc] == 0 || visit[nr][nc]) {
				continue;
			}
			
			visit[nr][nc] = true;
			dfs(nr, nc);
		}
	}
	
	public static void main(String args[]) throws IOException {
//		LocalDateTime start = LocalDateTime.now();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visit = new boolean[N][M];
			
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				map[r][c] = 1;
			}
			
			count = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] == 1 && !visit[r][c]) {
						count++;
						visit[r][c] = true;
						dfs(r, c);
					}
				}
			}
			
			bw.write(String.valueOf(count));
			bw.newLine();
		}
		bw.flush();
		
//		LocalDateTime end = LocalDateTime.now();
//		System.out.println();
//		System.out.println("[Elapsed time: " + Duration.between(start, end).getSeconds() + " sec]");
		
		bw.close();
		br.close();
	}
}
