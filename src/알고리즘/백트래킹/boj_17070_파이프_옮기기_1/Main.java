package 알고리즘.백트래킹.boj_17070_파이프_옮기기_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {0, 1, 1};
	static int[] dc = {1, 1, 0};
	static int resultCount;

	static void dfs(Node node) {
		if (node.r == N - 1 && node.c == N - 1) {
			resultCount += 1;
			
//			System.out.println();
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(visited[i][j] ? "O " : "X ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			return;
		}
		
		if (node.r - node.prevR == 1 && node.c - node.prevC == 0) {
			for (int i = 1; i < 3; i++) {
				int nr = node.r + dr[i];
				int nc = node.c + dc[i];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
					continue;
				}
				if (visited[nr][nc] || map[nr][nc] == 1) {
					continue;
				}
				if (i == 1 && (map[nr - 1][nc] == 1 || map[nr][nc - 1] == 1)) {
					continue;
				}
				
				visited[nr][nc] = true;
				dfs(new Node(nr, nc, node.r, node.c));
				visited[nr][nc] = false;
			}
		} else if (node.r - node.prevR == 0 && node.c - node.prevC == 1) {
			for (int i = 0; i < 2; i++) {
				int nr = node.r + dr[i];
				int nc = node.c + dc[i];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
					continue;
				}
				if (visited[nr][nc] || map[nr][nc] == 1) {
					continue;
				}
				if (i == 1 && (map[nr - 1][nc] == 1 || map[nr][nc - 1] == 1)) {
					continue;
				}
				
				visited[nr][nc] = true;
				dfs(new Node(nr, nc, node.r, node.c));
				visited[nr][nc] = false;
			}
		} else if (node.r - node.prevR == 1 && node.c - node.prevC == 1) {
			for (int i = 0; i < 3; i++) {
				int nr = node.r + dr[i];
				int nc = node.c + dc[i];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
					continue;
				}
				if (visited[nr][nc] || map[nr][nc] == 1) {
					continue;
				}
				if (i == 1 && (map[nr - 1][nc] == 1 || map[nr][nc - 1] == 1)) {
					continue;
				}
				
				visited[nr][nc] = true;
				dfs(new Node(nr, nc, node.r, node.c));
				visited[nr][nc] = false;
			}
		}
	}
	static class Node {
		private int r;
		private int c;
		private int prevR;
		private int prevC;
		
		public Node(int r, int c, int prevR, int prevC) {
			this.r = r;
			this.c = c;
			this.prevR = prevR;
			this.prevC = prevC;
		}
	}
	
	public static void main(String arg[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[N][N];
		resultCount = 0;
		visited[0][1] = true;
		visited[0][0] = true;
		dfs(new Node(0, 1, 0 ,0));
		
		bw.write(String.valueOf(resultCount));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
