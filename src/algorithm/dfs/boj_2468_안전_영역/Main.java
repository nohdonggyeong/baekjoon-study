package algorithm.dfs.boj_2468_안전_영역;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, top, count, answer;
	static int[][] map;
	static boolean[][] underwater;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	static void dfs(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
				continue;
			}
			if (!underwater[nr][nc]) {
				underwater[nr][nc] = true;
				dfs(nr, nc);
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		top = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				top = Math.max(top, map[i][j]);
			}
		}
		
		answer = 0;
		for (int i = 0; i < top; i++) {
			underwater = new boolean[N][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (map[r][c] <= i) {
						underwater[r][c] = true;
					}
				}
			}
			
			count = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!underwater[r][c]) {
						underwater[r][c] = true;
						count += 1;
						dfs(r, c);
					}
				}
			}
			answer = Math.max(answer,  count);
		}
		
		bw.write(String.valueOf(answer));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
