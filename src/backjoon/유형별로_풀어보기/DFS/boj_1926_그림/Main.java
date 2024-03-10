package backjoon.유형별로_풀어보기.DFS.boj_1926_그림;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n, m, area, resultCount, resultArea;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	static void dfs(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr >= 0 && nc >= 0 && nr < n && nc < m && map[nr][nc] == 1 && !visited[nr][nc]) {
				area += 1;
				visited[nr][nc] = true;
				dfs(nr, nc);
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		resultCount = 0;
		resultArea = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					resultCount++;
					area = 1;
					visited[i][j] = true;
					dfs(i, j);
					resultArea = Math.max(resultArea, area);
				}
			}
		}
		
		bw.write(String.valueOf(resultCount));
		bw.newLine();
		bw.write(String.valueOf(resultArea));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
