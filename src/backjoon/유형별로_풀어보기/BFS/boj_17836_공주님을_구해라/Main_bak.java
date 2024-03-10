package backjoon.유형별로_풀어보기.BFS.boj_17836_공주님을_구해라;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_bak {
	static int N, M, T;
	
	static int[][] map;
	static boolean[][][] visit;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static class Node {
		private int x;
		private int y;
		private int gram;
		private int dist;
		
		public Node(int x, int y, int gram, int dist) {
			this.x = x;
			this.y = y;
			this.gram = gram;
			this.dist = dist;
		}
	}
	
	static int bfs() {
		Queue<Node> queue = new LinkedList<>();
		
		queue.offer(new Node(0, 0, 0, 0));
		visit[0][0][0] = true;
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			if (node.x == N - 1 && node.y == M - 1) {
				return node.dist;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}
				
				if (node.gram == 0) {
					if (!visit[nx][ny][0] && map[nx][ny] == 0) {
						queue.offer(new Node(nx, ny, 0, node.dist + 1));
						visit[nx][ny][0] = true;
					} else if(!visit[nx][ny][0] && map[nx][ny] == 2) {
						queue.offer(new Node(nx, ny, 1, node.dist + 1));
						visit[nx][ny][0] = true;
					}
				} else {
					if (!visit[nx][ny][1]) {
						queue.offer(new Node(nx, ny, 1, node.dist + 1));
						visit[nx][ny][1] = true;
					}
				}
			}
		}
		
		return Integer.MAX_VALUE;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new boolean[N][M][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0 ; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int resultTime = bfs();
		bw.write(resultTime <= T ? String.valueOf(resultTime) : "Fail");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
