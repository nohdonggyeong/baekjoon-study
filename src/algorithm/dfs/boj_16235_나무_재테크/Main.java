package algorithm.dfs.boj_16235_나무_재테크;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] A;
	static int[][] map;
	static int[][][] trees;
	static int[][][] death;
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	static void spring() {
		for (int x = 0; x <= N; x++) {
			for (int y = 0; y <= N; y++) {
				for (int z = 0; z <= K + 10; z++) {
					int treeNum = trees[x][y][z];
					while (treeNum-- > 0) {
						if (map[x][y] < z) {
							death[x][y][z] += 1;
							trees[x][y][z] -= 1;
						} else {
							map[x][y] -= z;
							trees[x][y][z] -= 1;
							trees[x][y][z + 1] += 1;
						}
					}
				}
			}
		}
	}
	
	static void summer() {
		for (int x = 0; x <= N; x++) {
			for (int y = 0; y <= N; y++) {
				for (int z = 0; z <= K + 10; z++) {
					int deathNum = death[x][y][z];
					while (deathNum-- > 0) {
						map[x][y] += z / 2;
						death[x][y][z] -= 1;
					}
				}
			}
		}
	}
	
	static void autumn() {
		for (int x = 0; x <= N; x++) {
			for (int y = 0; y <= N; y++) {
				for (int z = 0; z <= K + 10; z++) {
					int treeNum = trees[x][y][z];
					while (treeNum-- > 0) {
						if (z % 5 == 0) {
							for (int i = 0; i < 8; i++) {
								int nx = x + dr[i];
								int ny = y + dc[i];
								
								if (nx <= 0 || ny <= 0 || nx > N || ny > N) {
									continue;
								}
								
								trees[nx][ny][1] += 1;
							}
						}
					}
				}
			}
		}
	}
	
	static void winter() {
		for (int x = 0; x <= N; x++) {
			for (int y = 0; y <= N; y++) {
				map[x][y] += A[x][y];
			}
		}
	}
	
	static int getNumTrees() {
		int num = 0;
		for (int x = 0; x <= N; x++) {
			for (int y = 0; y <= N; y++) {
				for (int z = 0; z <= K + 10; z++) {
					num += trees[x][y][z];
				}
			}
		}
		return num;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N + 1][N + 1];
		
		// 겨울 양분
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// 처음 양분
		map = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				map[r][c] = 5;
			}
		}
		
		// 처음 나무 위치
		trees = new int[N + 1][N + 1][K + 11];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			trees[x][y][z] += 1;
		}
		death = new int[N + 1][N + 1][K + 11];
		
		for (int k = 0; k < K; k++) {
			spring();
			summer();
			autumn();
			winter();	
		}
		
		bw.write(String.valueOf(getNumTrees()));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
