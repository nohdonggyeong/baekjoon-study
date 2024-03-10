package backjoon.알고리즘_분류.DFS.boj_2468_안전_영역;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_bak {
	static int n;
	static int[][] map;
	static boolean[][] checked;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int dfs(int x, int y, int height) {
		checked[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1) continue;
			if (checked[nx][ny]) continue;
			if (map[nx][ny] > height) {
				dfs(nx, ny, height);
			}
		}
		return 1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		int maxHeight = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > maxHeight) {
					maxHeight = map[i][j];
				}
			}
		}
		
		int max = 0;
		for (int height = 0; height <= maxHeight; height++) {
			checked = new boolean[n][n];
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!checked[i][j] && map[i][j] > height) {
						cnt += dfs(i, j, height);
					}
				}
			}
			max = Math.max(max, cnt);
		}
		
		bw.write(String.valueOf(max));
		bw.close();
		br.close();
	}
}
