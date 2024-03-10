package backjoon.유형별로_풀어보기.BFS.boj_2206_벽_부수고_이동하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][][] visit;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static class Node {
		private int x;
		private int y;
		private int broken;
		private int count;
		
		public Node(int x, int y, int broken, int count) {
			this.x = x;
			this.y = y;
			this.broken = broken;
			this.count = count;
		}
	}
	
	static int bfs() {
		Queue<Node> queue = new LinkedList<>();
		
		queue.offer(new Node(0, 0, 0, 1));
		visit[0][0][0] = true;
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			if (node.x == N - 1 && node.y == M - 1) {
				return node.count;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
					continue;
				}
				
				if (map[nx][ny] == 0 && !visit[nx][ny][node.broken]) {
					queue.offer(new Node(nx, ny, node.broken, node.count + 1));
					visit[nx][ny][node.broken] = true;
				}
				
				if (map[nx][ny] == 1 && node.broken == 0 && !visit[nx][ny][node.broken]) {
					queue.offer(new Node(nx, ny, 1, node.count + 1));
					visit[nx][ny][1] = true;
				}
			}
		}
		return -1;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new boolean[N][M][2];
		
		for (int i = 0 ; i < N; i++) {
			String[] strArr = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(strArr[j]);
			}
		}
		
		bw.write(String.valueOf(bfs()));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
