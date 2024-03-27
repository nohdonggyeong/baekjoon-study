package backjoon.알고리즘_분류.시뮬레이션.boj_16985_Maaaaaaaaaze;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static List<int[][]> candidates;
	
	// 회전하는 함수. 뒤집기는 No
	static int[][] rotate(int[][] map) {
		int[][] rotated = new int[5][5];
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				rotated[c][4 - r] = map[r][c];
			}
		}
		return rotated;
	}
	
	static int[] input, temp, temp2;
	static boolean[] visited;
	static List<int[]> output;
	
	static void permutation(int depth) {
		if (depth == 5) {
//			output.add(temp.clone());
//			return;
			
			// 2. 각 판을 4개의 경우로 회전
			temp2 = new int[5];
			permutationWithRepetition(0);
			if (finishFlag) {
				return;
			}
		}
		
		for (int i = 0; i < 5; i++) {
			if (!visited[i]) {
				visited[i] = true;
				temp[depth] = input[i];
				permutation(depth + 1);
				visited[i] = false;
			}
		}
	}
	
	static int[][][] cube;
	static boolean[][][] cubeVisited;
	static int[][] starts = {{0, 0, 0}, {0, 0, 4}, {0, 4, 0}, {0, 4, 4}, {4, 0, 0}, {4, 0, 4}, {4, 4, 0}, {4, 4, 4}};
	static int[][] ends = {{4, 4, 4}, {4, 4, 0}, {4, 0, 4}, {4, 0, 0}, {0, 4, 4}, {0, 4, 0}, {0, 0, 4}, {0, 0, 0}};
	static int[] dh = {0, 0, 0, 0, 1, -1};
	static int[] dr = {-1, 0, 1, 0, 0, 0};
	static int[] dc = {0, -1, 0, 1, 0, 0};
	static int minResult;
	static boolean finishFlag;
	
	static class Node {
		private int h;
		private int r;
		private int c;
		private int steps;
		
		public Node(int h, int r, int c, int steps) {
			this.h = h;
			this.r = r;
			this.c = c;
			this.steps = steps;
		}
	}
	
	static void permutationWithRepetition(int depth) {
		if (depth == 5) {
			// 판을 쌓는 경우의 수 -> 각 판을 회전하는 경우의 수
			// 경우를 특정시킨 cube
			cube = new int[5][5][5];
			for (int h = 0; h < 5; h++) {
				int[][] map = candidates.get(temp[h]);
				for (int i = 0; i < temp2[h]; i++) {
					int[][] rotated = rotate(map);
					for (int r = 0; r < 5; r++) {
						for (int c = 0; c < 5; c++) {
							map[r][c] = rotated[r][c];
						}
					}
				}
				for (int r = 0; r < 5; r++) {
					for (int c = 0; c < 5; c++) {
						cube[h][r][c] = map[r][c];
					}
				}
			}
			
			// 3. 꼭지점 8개 중 시작과 종료 가능한 경우의 수
			for (int i = 0; i < 8; i++) {
//				for (int h = 0; h < 5; h++) {
//					for (int r = 0; r < 5; r++) {
//						for (int c = 0; c < 5; c++) {
//							System.out.print(cube[h][r][c] + " ");
//						}
//						System.out.println();
//					}
//				}
//				System.out.println();
				
				if (cube[starts[i][0]][starts[i][1]][starts[i][2]] == 0 || cube[ends[i][0]][ends[i][1]][ends[i][2]] == 0) {
					continue;
				}
				
				// 4. 최단 거리 탐색 bfs
				bfs(starts[i], ends[i]);
				if (finishFlag) {
					return;
				}
			}
			
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			temp2[depth] = input[i];
			permutationWithRepetition(depth + 1);
		}
	}
	
	static void bfs(int[] start, int[] end) {		
		cubeVisited = new boolean[5][5][5];
		cubeVisited[start[0]][start[1]][start[2]] = true;

		Queue<Node> queue = new LinkedList<Main.Node>();
		queue.offer(new Node(start[0], start[1], start[2], 0));
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			if (node.h == end[0] && node.r == end[1] && node.c == end[2]) {
				minResult = Math.min(minResult, node.steps);
				if (minResult == 12) {
					finishFlag = true;
				}
				return;
			}
			
			for (int i = 0; i < 6; i++) {
				int nh = node.h + dh[i];
				int nr = node.r + dr[i];
				int nc = node.c + dc[i];
				
				if (nh < 0 || nr < 0 || nc < 0 || nh >= 5 || nr >= 5 || nc >= 5) {
					continue;
				}
				if (cube[nh][nr][nc] != 1 || cubeVisited[nh][nr][nc]) {
					continue;
				}
				
				cubeVisited[nh][nr][nc] = true;
				queue.offer(new Node(nh, nr, nc ,node.steps + 1));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
	
		candidates = new ArrayList<int[][]>();
		for (int h = 0; h < 5; h++) {
			int[][] map = new int[5][5];
			for (int r = 0; r < 5; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < 5; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			candidates.add(map);
		}
		
//		System.out.println();
//		for (int[][] el : candidates) {
//			for (int r = 0; r < 5; r++) {
//				for (int c = 0; c < 5; c++) {
//					System.out.print(el[r][c] + " ");
//				}
//				System.out.println();
//			}
//		}
		
//		int[][] map = candidates.get(0);
//		int[][] rotated = rotate(map);
//		System.out.println();
//		for (int r = 0; r < 5; r++) {
//			for (int c = 0; c < 5; c++) {
//				System.out.print(rotated[r][c] + " ");
//			}
//			System.out.println();
//		}
		
		// 판 5개 쌓는 경우의 수 -> 각 판을 4개의 경우로 회전 -> 꼭지점 8개 중 시작과 종료 가능한 곳에서 최단 거리 탐색 bfs
		// 1. 판 5개 쌓는 경우의 수
		input = new int[5];
		for (int i = 0; i < 5; i++) {
			input[i] = i;
		}
		
		temp = new int[5];
		visited = new boolean[5];
		output = new ArrayList<int[]>();

		minResult = Integer.MAX_VALUE;
		finishFlag = false;
		permutation(0);
		
		bw.write(minResult == Integer.MAX_VALUE ? "-1" : String.valueOf(minResult));
		bw.flush();
		bw.close();
		br.close();
	}
}
