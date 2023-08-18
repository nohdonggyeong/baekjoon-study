package 알고리즘.DFS.boj_4963_섬의_개수;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

public class Main {
	static int w, h;
	static int[][] map;
	static boolean[][] visit;
	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int count;
	
	static void dfs(int r, int c) {
		for (int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nc < 0 || nr >= h || nc >= w) {
				continue;
			}
			if (map[nr][nc] == 0 || visit[nr][nc]) {
				continue;
			}
			
			visit[nr][nc] = true;
			dfs(nr, nc);
		}
	}
	
	public static void main(String args[]) throws IOException {
//		LocalDateTime start = LocalDateTime.now();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if (w == 0 && h == 0) {
				break;
			}
			
			map = new int[h][w];
			visit = new boolean[h][w];
			
			for (int r = 0; r < h; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < w; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			count = 0;
			for (int r = 0; r < h; r++) {
				for (int c = 0; c < w; c++) {
					if (map[r][c] == 1 && !visit[r][c]) {
						count++;
						visit[r][c] = true;
						dfs(r, c);
					}
				}
			}
			
			bw.write(String.valueOf(count));
			bw.newLine();
		}
		bw.flush();
		
//		LocalDateTime end = LocalDateTime.now();
//		System.out.println();
//		System.out.println("[Elapsed time: " + Duration.between(start, end).getSeconds() + " sec]");
		
		bw.close();
		br.close();
	}
}
