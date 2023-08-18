package AD검정.boj_14500_테트로미노;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M, max = 0;
	
	static int[][] map;
	static boolean[][] visit;
	
	static int[] dr = {0, 0, -1, 1};
	static int[] dc = {1, -1, 0, 0};
	
	static void dfs(int r, int c, int sum, int count) {
		if (count == 4) {
			max = Math.max(max, sum);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr  < 0 || nc < 0 || nr >= N || nc >= M) {
				continue;
			}
			
			if (visit[nr][nc]) {
				continue;
			}
			
			// 볼록한 모양은 2번째에서 한 번 뻗어주고 다른 방면을 찾음
			if (count == 2) {
				visit[nr][nc] = true;
				dfs(r, c, sum + map[nr][nc], count + 1);
				visit[nr][nc] = false;
			}
			visit[nr][nc] = true;
			dfs(nr, nc, sum + map[nr][nc], count + 1);
			visit[nr][nc] = false;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0 ; j < M; j++) {
				visit[i][j] = true;
				dfs(i, j, map[i][j], 1);
				visit[i][j] = false;
			}
		}
		
		bw.write(String.valueOf(max));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
