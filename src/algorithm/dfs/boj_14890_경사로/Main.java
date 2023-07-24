package algorithm.dfs.boj_14890_경사로;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, L;
	static int[][] map;
	static boolean[][] slope;
	static int count;
	
	static void rowDFS(int r, int c) {
		if (c == N - 1) {
			count += 1;
			return;
		}
		
		if (map[r][c + 1] == map[r][c]) {
			rowDFS(r, c + 1);
		} else if (map[r][c + 1] == map[r][c] - 1) {
			boolean isDifferent = false;
			for (int i = 1; i <= L; i++) {
				if (c + i >= N || map[r][c + 1] != map[r][c + i]) {
					isDifferent = true;
				}
			}
			if (!isDifferent) {
				for (int i = 1; i <=L; i++) {
					slope[r][c + i] = true;
				}
				rowDFS(r, c + L);
			}
		} else if (map[r][c + 1] == map[r][c] + 1) {
			boolean isDifferent = false;
			for (int i = 0; i < L; i++) {
				if (c - i < 0 || map[r][c] != map[r][c - i] || slope[r][c - i]) {
					isDifferent = true;
				}
			}
			if (!isDifferent) {
				rowDFS(r, c + 1);
			}
		}
	}
	
	static void columnDFS(int r, int c) {
		if (r == N - 1) {
			count += 1;
			return;
		}
		
		if (map[r + 1][c] == map[r][c]) {
			columnDFS(r + 1, c);
		} else if (map[r + 1][c] == map[r][c] - 1) {
			boolean isDifferent = false;
			for (int i = 1; i <= L; i++) {
				if (r + i >= N || map[r + 1][c] != map[r + i][c]) {
					isDifferent = true;
				}
			}
			if (!isDifferent) {
				for (int i = 1; i <= L; i++) {
					slope[r + i][c] = true;
				}
				columnDFS(r + L, c);
			}
		} else if (map[r + 1][c] == map[r][c] + 1) {
			boolean isDifferent = false;
			for (int i = 0; i < L; i++) {
				if (r - i < 0 || map[r][c] != map[r - i][c] || slope[r - i][c]) {
					isDifferent = true;
				}
			}
			if (!isDifferent) {
				columnDFS(r + 1, c);
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		count = 0;
		slope = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			rowDFS(i, 0);
		}
		slope = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			columnDFS(0, i);
		}
		
		bw.write(String.valueOf(count));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
