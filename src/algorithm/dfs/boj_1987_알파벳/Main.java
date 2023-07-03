package algorithm.dfs.boj_1987_알파벳;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int R, C, answer;
	static int[][] map;
	static boolean[] visit;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	static void dfs(int r, int c, int cnt) {
		if (visit[map[r][c]]) { // 더 탐색 불가하면 max 출력
			answer = Math.max(answer, cnt);
			return;
		} else {
			visit[map[r][c]] = true; // 현재 위치 visit true
			for (int i = 0; i < 4; i++) { // for if dfs(r, c, cnt + 1)
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (nr >= 0 && nc >= 0 && nr < R && nc < C) {
					dfs(nr, nc, cnt + 1);
				}
			}
			visit[map[r][c]] = false; // 다음 경우를 탐색하기 위해 visit true했던 것을 모두 visit false
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		visit = new boolean[26];
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j) - 'A';
			}
		}

		answer = 0;
		dfs(0, 0, 0);
		
		bw.write(String.valueOf(answer));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
