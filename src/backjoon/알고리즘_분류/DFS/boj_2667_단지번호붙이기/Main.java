package backjoon.알고리즘_분류.DFS.boj_2667_단지번호붙이기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static int N, count, resultNum;
	static List<Integer> resultList;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	static void dfs(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
				continue;
			}
			if (!visited[nr][nc]) {
				count += 1;
				visited[nr][nc] = true;
				dfs(nr, nc);
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N];
		
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < N; c++) {
				map[r][c] = str.charAt(c);
				if (map[r][c] == '0') {
					visited[r][c] = true; 
				}
			}
		}
		
		resultNum = 0;
		resultList = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!visited[r][c]) {
					resultNum += 1;
					count = 1;
					visited[r][c] = true;
					dfs(r, c);
					resultList.add(count);
				}
			}
		}
		
		Collections.sort(resultList);
		
		bw.write(String.valueOf(resultNum));
		bw.newLine();
		for (int i = 0; i < resultList.size(); i++) {
			bw.write(String.valueOf(resultList.get(i)));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
