package backjoon.유형별로_풀어보기.DFS.boj_14503_로봇_청소기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int startR, startC, startD;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int maxCount;
	
	static void cleaning(int r, int c, int d, int count) {
		if (map[r][c] == 0 && !visited[r][c]) {
			visited[r][c] = true;
			count += 1;
		}
		
		if (stuck(r, c)) {
			int backR = r - dr[d];
			int backC = c - dc[d];
			if (backR >= 0 && backC >= 0 && backR < N && backC < M && map[backR][backC] == 0) {
				cleaning(backR, backC, d, count);
			} else {
				maxCount = Math.max(maxCount, count);
				return;
			}
		} else {
			int nr = r;
			int nc = c;
			int nd = d;
			while (true) {
				nd = nd == 0 ? 3 : nd - 1;
				nr = r + dr[nd];
				nc = c + dc[nd];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
					continue;
				}
				if (map[nr][nc] == 0 && !visited[nr][nc]) {
					break;
				}
			}
			cleaning(nr, nc, nd, count);
		}
				
	}
	
	static boolean stuck(int r, int c) {
		boolean result = true;
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
				continue;
			}
			if (map[nr][nc] == 0 && !visited[nr][nc]) {
				result = false;
			}
		}
		return result;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		st = new StringTokenizer(br.readLine());
		startR = Integer.parseInt(st.nextToken());
		startC = Integer.parseInt(st.nextToken());
		startD = Integer.parseInt(st.nextToken());
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c= 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		maxCount = 0;
		cleaning(startR, startC, startD, 0);

		bw.write(String.valueOf(maxCount));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
