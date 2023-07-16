package algorithm.dfs.boj_13565_침투;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

public class Main {
	static int M, N;
	static char[][] map;
	static boolean[][] visit;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	static boolean answer;
	
	static void dfs(int m, int n) {
		if (m == M - 1) {
			answer = true;
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nr = m + dr[i];
			int nc = n + dc[i];
			
			if (nr < 0 || nc < 0 || nr >= M | nc >= N) {
				continue;
			}
			if (map[nr][nc] == '1' || visit[nr][nc]) {
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new char[M][N];
		visit = new boolean[M][N];
		
		for (int m = 0; m < M; m++) {
			String str = br.readLine();
			for (int n = 0; n < N; n++) {
				map[m][n] = str.charAt(n);
			}
		}
		
		answer = false;
		for (int n = 0; n < N; n++) {
			if (map[0][n] == '0' && !visit[0][n]) {
				visit[0][n] = true;
				dfs(0, n);
				if (answer) {
					break;
				}
			}
		}
		
		bw.write(answer ? "YES" : "NO");
		bw.flush();
		
//		LocalDateTime end = LocalDateTime.now();
//		System.out.println();
//		System.out.println();
//		System.out.println("[Elapsed time: " + Duration.between(start, end).getSeconds() + " sec]");
		
		bw.close();
		br.close();
	}
}
