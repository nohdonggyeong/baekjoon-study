package backjoon.유형별로_풀어보기.BFS.boj_17836_공주님을_구해라;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, T;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	static int bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(0, 0, false, 0));
		visited[0][0][0] = true;
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			if (node.r == N - 1 && node.c == M - 1) {
				return node.distance;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = node.r + dr[i];
				int nc = node.c + dc[i];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
					continue;
				}
				
				if (node.hasGram) {
					if (!visited[nr][nc][1]) {
						visited[nr][nc][1] = true;
						queue.offer(new Node(nr, nc, true, node.distance + 1));
					}
				} else {
					if (visited[nr][nc][0] || map[nr][nc] == 1) {
						continue;
					}
					
					if (map[nr][nc] == 2) {
						visited[nr][nc][0] = true;
						queue.offer(new Node(nr, nc, true, node.distance + 1));
					} else {
						visited[nr][nc][0] = true;
						queue.offer(new Node(nr, nc, false, node.distance + 1));
					}
				}
			}
		}
		
		return Integer.MAX_VALUE;
	}
	
	static class Node {
		private int r;
		private int c;
		private boolean hasGram;
		private int distance;
		
		public Node(int r, int c, boolean hasGram, int distance) {
			this.r = r;
			this.c = c;
			this.hasGram = hasGram;
			this.distance = distance;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M][2];
		
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = bfs();
		bw.write(result > T ? "Fail" : String.valueOf(result));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
