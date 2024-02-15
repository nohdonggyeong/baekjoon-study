package 알고리즘.DFS.boj_1987_알파벳;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static char[][] map;
	static boolean[] checked;
	static int maxCount;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	static void dfs(int r, int c, int count) {
		maxCount = Math.max(maxCount, count);
		
		int nr, nc;
		for (int d = 0; d < 4; d++) {
			nr = r + dr[d];
			nc = c + dc[d];
			
			if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
				continue;
			}
			if (visited[nr][nc] || checked[map[nr][nc] - 'A']) {
				continue;
			}
			
			visited[nr][nc] = true;
			checked[map[nr][nc] - 'A'] = true;
			count++;
			
			dfs(nr, nc, count);
			
			visited[nr][nc] = false;
			checked[map[nr][nc] - 'A'] = false;
			count--;
		}
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			map = new char[R][C];
			checked = new boolean[26];
			String str;
			for (int r = 0; r < R; r++) {
				str = br.readLine();
				for (int c = 0; c < C; c++) {
					map[r][c] = str.charAt(c);
					
					if (r == 0 && c == 0) {
						checked[map[r][c] - 'A'] = true;
					}
				}
			}
			
			maxCount = 1;
			visited = new boolean[R][C];
			visited[0][0] = true;
			dfs(0, 0, 1);
			
			bw.write(String.valueOf(maxCount));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
