package 알고리즘.순열.boj_17135_캐슬_디펜스;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, D;
	static int[][] map, mapBak;
	
	static int n, r;
	static int[] input;
	static boolean[] visited;
	static List<int[]> output;
	static int maxCount;
	
	static void combination(int start, int depth) {
		if (depth == r) {
			int[] temp = new int[r];
			int index = 0;
			for (int i = 0; i < n; i++) {
				if (visited[i]) {
					temp[index++] = input[i];
				}
			}
			output.add(temp.clone());
			return;
		}
		
		for (int i = start; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				combination(i + 1, depth + 1);
				visited[i] = false;
			}
		}
	}
	
	static boolean isGameOver() {
		boolean result = true;
		loop:
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] != 0) {
					result = false;
					break loop;
				}
			}
		}
		return result;
	}
	
	static class Node implements Comparable<Node> {
		private int r;
		private int c;
		private int distance;
		
		public Node(int r, int c, int distance) {
			this.r = r;
			this.c = c;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			if (distance - o.distance > 0) {
				return 1;
			} else if (distance == o.distance) {
				if (c - o.c > 0) {
					return 1;
				} else if (c == o.c) {
					return 0;
				}
			}
			return -1;
		}
	}
	
	public static void main(String args[]) throws IOException {
//		LocalDateTime start = LocalDateTime.now();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		mapBak = new int[N][M];
		for (int i = 0; i < N; i++) {
			mapBak[i] = map[i].clone();
		}
		
		n = M;
		r = 3;
		
		input = new int[M];
		for (int i = 0; i < M; i++) {
			input[i] = i;
		}
		
		visited = new boolean[n];
		output = new ArrayList<>();
		combination(0, 0);
		
		maxCount = Integer.MIN_VALUE;
		for (int[] el : output) { // 궁수 3명 위치 경우의 수
			int count = 0;
			while(!isGameOver()) { // 게임 끝나기까지 반복
				boolean[][] deleted = new boolean[N][M];
				for (int e : el) { // 궁수마다 공격
					List<Node> enemyList = new ArrayList<>();
					for (int i = N - 1; i >= 0 && i >= N - D; i--) {
						for (int j = 0; j < M; j++) {
							if (map[i][j] == 1 && N - i + Math.abs(j - e) <= D) {
								enemyList.add(new Node(i, j, N - i + Math.abs(j - e)));
							}
						}
					}
					if (enemyList.size() > 0) {
						Collections.sort(enemyList);
						deleted[enemyList.get(0).r][enemyList.get(0).c] = true;
					}
				}
				for (int i = N - 1; i >= 0 && i >= N - D; i--) {
					for (int j = 0; j < M; j++) {
						if (deleted[i][j]) {
							count += 1;
							map[i][j] = 0;
						}
					}
				}
				
				for (int i = N - 1; i > 0; i--) {
					map[i] = map[i - 1].clone();
				}
				Arrays.fill(map[0], 0);
				
//				System.out.println();
//				for (int e : el) {
//					System.out.print(e + " ");
//				}
//				System.out.println();
//				for (int i = 0; i < N; i++) {
//					for (int j = 0; j < M; j++) {
//						System.out.print(map[i][j] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println();
				
			}
			maxCount = Math.max(maxCount, count);
			
			for (int i = 0; i < N; i++) {
				map[i] = mapBak[i].clone();
			}
		}
		
		bw.write(String.valueOf(maxCount));
		bw.flush();
		
//		LocalDateTime end = LocalDateTime.now();
//		System.out.println();
//		System.out.println(Duration.between(start, end).getSeconds());
		
		bw.close();
		br.close();
	}
}
