package 알고리즘.BFS.boj_16234_인구_이동;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, L, R;
	static int[][] map;
	static boolean[][] visited, territory;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int result;
	static boolean breakCheck;
	
	static void bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {r, c}) ;
		visited[r][c] = true;
		territory[r][c] = true;
		
		int num = 1;
		int sum = map[r][c];
		while (!queue.isEmpty()) {
			int[] node = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = node[0] + dr[i];
				int nc = node[1] + dc[i];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc]) {
					continue;
				}
				if ((Math.abs(map[node[0]][node[1]] - map[nr][nc]) < L) || (Math.abs(map[node[0]][node[1]] - map[nr][nc]) > R)) {
					continue;
				}
				
				queue.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
				territory[nr][nc] = true;
				
				num++;
				sum += map[nr][nc];
			}
		}
		
		if (num > 1) {
			int average = sum / num;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (territory[i][j]) {
						map[i][j] = average;
					}
				}
			}
			breakCheck = false;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		result = 0;
		while (true) {
			breakCheck = true;
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						territory = new boolean[N][N];
						bfs(i, j);
					}
				}
			}
			if (breakCheck) {
//				System.out.println();
//				System.out.println("result: " + result);
//				for (int i = 0; i < N; i++) {
//					for (int j = 0; j < N; j++) {
//						System.out.print(String.valueOf(map[i][j]) + " ");
//					}
//					System.out.println();
//				}
				break;
			} else {
				result++;
//				System.out.println();
//				System.out.println("result: " + result);
//				for (int i = 0; i < N; i++) {
//					for (int j = 0; j < N; j++) {
//						System.out.print(String.valueOf(map[i][j]) + " ");
//					}
//					System.out.println();
//				}
			}
		}
		
		bw.write(String.valueOf(result));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
