package backjoon.알고리즘_분류.DFS.boj_1388_바닥_장식;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static char[][] map;
	static boolean[][] visit;
	static int[] dr = {0, 1};
	static int[] dc = {1, 0};
	static int answer;
	
	static void dfs(int r, int c) {
		char shape = map[r][c];
		
		int nr = r + dr[shape == '-' ? 0 : 1];
		int nc = c + dc[shape == '-' ? 0 : 1];
		
		if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
			return;
		}
		if (visit[nr][nc] || map[nr][nc] != shape) {
			return;
		}
		
		visit[nr][nc] = true;
		dfs(nr, nc);
	}
	
	public static void main(String args[]) throws IOException {
//		LocalDateTime start = LocalDateTime.now();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visit = new boolean[N][M];
		
		for (int n = 0; n < N; n++) {
			String str = br.readLine();
			for (int m = 0; m < M; m++) {
				map[n][m] = str.charAt(m);
			}
		}
		
		answer = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (!visit[r][c]) {
					answer++;
					visit[r][c] = true;
					dfs(r, c);
				}
			}
		}
		
		bw.write(String.valueOf(answer));
		bw.flush();
		
//		LocalDateTime end = LocalDateTime.now();
//		System.out.println();
//		System.out.println("[Elapsed time: " + Duration.between(start, end).getSeconds() + " sec]");
		
		bw.close();
		br.close();
	}
}
