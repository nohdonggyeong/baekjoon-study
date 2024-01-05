package 알고리즘.시뮬레이션.boj_16985_Maaaaaaaaaze;

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
	
	static int[] input, temp, temp2;
	static boolean[] visited;
//	static List<int[]> output;
	
	static int[][] startPoints = {{0, 0, 0}, {0, 0, 4}, {0, 4, 0}, {0, 4, 4}, {4, 0, 0}, {4, 0, 4}, {4, 4, 0}, {4, 4, 4}};
	static int[][] endPoints = {{4, 4, 4}, {4, 4, 0}, {4, 0, 4}, {4, 0, 0}, {0, 4, 4}, {0, 4, 0}, {0, 0, 4}, {0, 0, 0}};
	
	static int result;
	static int[] dh = {0, 0, 0, 0, 1, -1};
	static int[] dr = {-1, 0, 1, 0, 0, 0};
	static int[] dc = {0, -1, 0, 1, 0, 0};
	
	static boolean finishFlag = false;
	
	// 판을 시계방향으로 회전하는 함수 (뒤집기는 없음)
	static int[][] rotateMap(int[][] map) {
		int[][] rotated = new int[5][5];
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				rotated[c][4 - r] = map[r][c];
			}
		}
		return rotated;
	}
	
	// 판을 쌓는 permutation 함수
	static void permutation(int depth) {
		if (depth == 5) {
//			output.add(temp.clone());
//			return;
			
			temp2 = new int[5];
			permutationWithRepetition(0);
			if (finishFlag) {
				return;
			}
			return;
		}
		
		for (int i = 0; i < 5; i++) {
			if (!visited[i]) {
				visited[i] = true;
				temp[depth] = i;
				permutation(depth + 1);
				visited[i] = false;
			}
		}
	}
	
	// 각 판을 회전하는 permutation with repetition 함수 
	static void permutationWithRepetition(int depth) {
		if (depth == 5) {
			// 판 5개가 쌓아진 조합으로 cube 생성
			int[][][] cube = new int[5][5][5];
			for (int h = 0; h < 5; h++) {
				int[][] map = candidates.get(temp[h]);
				for (int r = 0; r < 5; r++) {
					for (int c = 0; c < 5; c++) {
						cube[h][r][c] = map[r][c];
					}
				}
			}
			
			// cube 각 층 회전
			for (int h = 0; h < 5; h++) {
				int[][] map = cube[h];
				for (int j = 0; j < temp2[h]; j++) {
					int[][] rotated = rotateMap(map);
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
			
			// 입구를 찾는 반복
			for (int j = 0; j < 8; j++) {
				int[] start = startPoints[j];
				int[] end = endPoints[j];
				if (cube[start[0]][start[1]][start[2]] == 0 || cube[end[0]][end[1]][end[2]] == 0) {
					continue;
				}
				
				// 최단거리 탐색 bfs
				bfs(start, end, cube);
				if (finishFlag) {
					return;
				}
			}
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			temp2[depth] = i;
			permutationWithRepetition(depth + 1);
		}
	}
	
	static void bfs(int[] start, int[] end, int[][][] cube) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(start[0], start[1], start[2], 0));
		
		boolean[][][] visited = new boolean[5][5][5];
		visited[start[0]][start[1]][start[2]] = true;
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (node.h == end[0] && node.r == end[1] && node.c == end[2]) {
				result = Math.min(result, node.steps);
				if (result == 12) {
					finishFlag = true;
				}
				return;
			}
			
			for (int i = 0; i < 6; i++) {
				int nh = node.h + dh[i];
				int nr = node.r + dr[i];
				int nc = node.c + dc[i];
				
				if (nh < 0 || nr < 0 || nc < 0
						|| nh >= 5 || nr >= 5 | nc >= 5) {
					continue;
				}
				if (cube[nh][nr][nc] == 0 || visited[nh][nr][nc]) {
					continue;
				}
				
				visited[nh][nr][nc] = true;
				queue.offer(new Node(nh, nr, nc, node.steps + 1));
			}
		}
	}
	
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
		
//		for (int h = 0; h < 5; h++) {
//			for (int r = 0; r < 5; r++) {
//				for (int c = 0; c < 5; c++) {
//					System.out.print(candidates.get(h)[r][c] + " ");
//				}
//				System.out.println();
//			}
//		}
		
//		int[][] rotated = rotateMap(candidates.get(0));
//		for (int r = 0; r < 5; r++) {
//			for (int c = 0; c < 5; c++) {
//				System.out.print(rotated[r][c] + " ");
//			}
//			System.out.println();
//		}
		
		input = new int[5];
		for (int i = 0; i < 5; i++) {
			input[i] = i;
		}
		
		temp = new int[5];
		visited = new boolean[5];
//		output = new ArrayList<>();
		
		result = Integer.MAX_VALUE;
		permutation(0);
		
//		for (int[] el : output) {
//			for (int e : el) {
//				System.out.print(e + " ");
//			}
//			System.out.println();
//		}
		
		bw.write(result == Integer.MAX_VALUE ? "-1" : String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
}
