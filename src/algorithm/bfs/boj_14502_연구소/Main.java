package algorithm.bfs.boj_14502_연구소;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] mapBackup;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	static int maxCount;
	
	// dfs
	static void makeThreeWalls(int depth) {
		if (depth == 3) {
			for (int r = 0; r < N; r++) {
				mapBackup[r] = map[r].clone();
			}
			// 모든 2를 0 방향으로 확장하고 최대 남은 0 개수 세기
			spread();
			maxCount = Math.max(maxCount, getCount());

			for (int r = 0; r < N; r++) {
				map[r] = mapBackup[r].clone();
			}
			return;
		}
		
		// map이 0이라면 벽 세워보기
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0) {
					map[r][c] = 1;
					makeThreeWalls(depth + 1);
					map[r][c] = 0;
				}
			}
		}
	}
	
	// bfs
	static void spread() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 2) {
					Queue<int[]> queue = new LinkedList<>();
					queue.offer(new int[] {r, c});
					while (!queue.isEmpty()) {
						int[] arr = queue.poll();
						for (int i = 0; i < 4; i++) {
							int nr = arr[0] + dr[i];
							int nc = arr[1] + dc[i];
							
							if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
								continue;
							}
							if (map[nr][nc] == 0) {
								map[nr][nc] = 2;
								queue.offer(new int[] {nr, nc});
							}
						}
					}
				}
			}
		}
	}
	
	static int getCount() {
		int result = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0) {
					result++;
				}
			}
		}
		return result;
	}
	
	public static void main(String args[]) throws IOException {
		// 1. dfs depth 3로 이중 for문에서 0 위치에서 벽을 세우는 경우를 가정
		// 2. bfs로 2마다 시작해서 visited = true하고 0의 최대 개수 세기
		
//		LocalDateTime startTime = LocalDateTime.now();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		mapBackup = new int[N][M];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		maxCount = 0;
		makeThreeWalls(0);
		
		bw.write(String.valueOf(maxCount));
		bw.flush();
		
//		LocalDateTime endTime = LocalDateTime.now();
//		System.out.println();
//		System.out.println("[Elapsed time: " + Duration.between(startTime, endTime).getSeconds() + " sec]");
		
		bw.close();
		br.close();
	}
}
