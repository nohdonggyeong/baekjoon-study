package backjoon.알고리즘_분류.BFS.boj_17142_연구소_3;

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
	static int N, M;
	static int[][] map, map_bak;
	static List<Node> virusList;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static int n, r;
	static int[] input;
	static boolean[] visited;
	static List<int[]> output;
	
	static int resultTime;

	static class Node {
		private int r;
		private int c;
		private int time;
		
		public Node(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	
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
		
		for (int i = start; i < n; i ++) {
			if (!visited[i]) {
				visited[i] = true;
				combination(i + 1, depth + 1);
				visited[i] = false;
			}
		}
	}
	
	static boolean checkBlank() {
		boolean result = true;
		loop:
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 0) {
					result = false;
					break loop;
				}
			}
		}
		return result;
	}
	
	static int diffuse(int[] el) {
		Queue<Node> queue = new LinkedList<>();
		for (int e : el) {
			queue.offer(virusList.get(e));
			map[virusList.get(e).r][virusList.get(e).c] = 1;
		}
		
		int maxTime = 0;
		while (!queue.isEmpty()) {
			if (checkBlank()) {
				return maxTime;
			}
			
			Node node = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = node.r + dr[i];
				int nc = node.c + dc[i];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
					continue;
				}
				if (map[nr][nc] == 1) {
					continue;
				} else {
					queue.offer(new Node(nr, nc, node.time + 1));
					map[nr][nc] = 1;
					maxTime = Math.max(maxTime, node.time + 1);
				}
			}
		}
		
		return -1;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		virusList = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 2) {
					virusList.add(new Node(r, c, 0));
				}
			}
		}

		map_bak = new int[N][N];
		for (int r = 0; r < N; r++) {
			map_bak[r] = map[r].clone();
		}
		
		n = virusList.size();
		r = M;
		
		input = new int[n];
		for (int i = 0; i < n; i++) {
			input[i] = i;
		}
		
		visited = new boolean[n];
		output = new ArrayList<>();
		combination(0, 0);
		
		resultTime = Integer.MAX_VALUE;
		for (int[] el : output) {
			int diffusionTime = diffuse(el);
			if (diffusionTime > -1) {
				resultTime = Math.min(resultTime, diffusionTime);
			}

//			System.out.println();
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			for (int r = 0; r < N; r++) {
				map[r] = map_bak[r].clone();
			}
		}
		if (resultTime == Integer.MAX_VALUE) {
			resultTime = -1;
		}
		
		bw.write(String.valueOf(resultTime));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
