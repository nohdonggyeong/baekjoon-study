package backjoon.유형별로_풀어보기.DFS.boj_1189_컴백홈;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int R, C, K, answer;
	static char[][] map;
	static boolean[][] visit;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static void dfs(int r, int c, int cnt) {		
		if (r == 0 && c == C - 1) {
			if (cnt == K) {
				answer++;
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr >= 0 && nc >= 0 && nr < R && nc < C && !visit[nr][nc] && map[nr][nc] != 'T') {
				visit[nr][nc] = true;
				dfs(nr, nc, cnt + 1);
				visit[nr][nc] = false;
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visit = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		answer = 0;
		visit[R - 1][0] = true;
		dfs(R - 1, 0, 1);
		
		bw.write(String.valueOf(answer));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
