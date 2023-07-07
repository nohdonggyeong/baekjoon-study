package algorithm.dfs.boj_3184_양;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int R, C, sheep, wolf, sheepSum, wolfSum;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	static void dfs(int r, int c) {
		if (map[r][c] == 'o') {
			sheep += 1;
		} else if (map[r][c] == 'v') {
			wolf += 1;
		}
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr >= 0 && nc >= 0 && nr < R && nc < C && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr, nc);
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == '#') {
					visited[i][j] = true;
				}
			}
		}
		
		sheepSum = 0;
		wolfSum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (!visited[i][j]) {
					sheep = 0;
					wolf = 0;
					
					visited[i][j] = true;
					dfs(i, j);
					
					if (sheep > wolf) {
						sheepSum += sheep;
					} else {
						wolfSum += wolf;
					}
				}
			}
		}
		
		bw.write(String.valueOf(sheepSum));
		bw.write(" ");
		bw.write(String.valueOf(wolfSum));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
