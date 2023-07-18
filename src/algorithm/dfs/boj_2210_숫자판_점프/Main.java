package algorithm.dfs.boj_2210_숫자판_점프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	static int[] temp;
	static List<int[]> list;
	
	static void dfs(int r, int c, int depth) {
		if (depth == 6) {
			boolean contains = false;
			for (int[] el : list) {
				if (Arrays.equals(el, temp)) {
					contains = true;
					break;
				}
			}
			if (!contains) {
				list.add(temp.clone());
			}
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nc < 0 || nr >= 5 || nc >= 5) {
				continue;
			}
			
			temp[depth] = map[nr][nc];
			dfs(nr, nc, depth + 1);
		}
	}
	
	public static void main(String args[]) throws IOException {
//		LocalDateTime start = LocalDateTime.now();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		map = new int[5][5];
		for (int r = 0; r < 5; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 5; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		list = new ArrayList<>();
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				temp = new int[6];
				temp[0] = map[r][c];
				dfs(r, c, 1);
			}
		}
		
		bw.write(String.valueOf(list.size()));
		bw.flush();
		
//		LocalDateTime end = LocalDateTime.now();
//		System.out.println();
//		System.out.println("[Elapsed time: " + Duration.between(start, end).getSeconds() + " sec]");
		
		bw.close();
		br.close();
	}
}
