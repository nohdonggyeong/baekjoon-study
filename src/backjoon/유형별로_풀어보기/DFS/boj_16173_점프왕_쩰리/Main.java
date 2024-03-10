package backjoon.유형별로_풀어보기.DFS.boj_16173_점프왕_쩰리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int[] dr = {0, 1};
	static int[] dc = {1, 0};
	static boolean possible;
	
	static void dfs(int r, int c) {
		if (r ==  N - 1 && c == N - 1) {
			possible = true;
			return;
		}
		
		if (map[r][c] == 0) {
			return;
		}
		
		for (int i = 0; i < 2; i++) {
			int nr = r + dr[i] * map[r][c];
			int nc = c + dc[i] * map[r][c];
			
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
				continue;
			}
			if (map[nr][nc] == 0) {
				continue;
			}
			
			dfs(nr, nc);
		}
	}
	
	public static void main(String args[]) throws IOException {
//		LocalDateTime start = LocalDateTime.now();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		possible = false;
		dfs(0, 0);
		
		bw.write(possible ?  "HaruHaru" : "Hing");
		bw.flush();
		
//		LocalDateTime end = LocalDateTime.now();
//		System.out.println();
//		System.out.println("[Elapsed time: " + Duration.between(start, end).getSeconds() + " sec]");
		
		bw.close();
		br.close();
	}
}
