package backjoon.알고리즘_분류.BFS.boj_2178_미로_탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N, M;
	private static int[][] maze;
	private static boolean[][] visited;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	
	private static class Node {
		private int x;
		private int y;
		private int minCnt;
		
		Node() {
			this.x = 0;
			this.y = 0;
			this.minCnt = 1;
		}
		
		Node(int x, int y, int minCnt) {
			this.x = x;
			this.y = y;
			this.minCnt = minCnt;
		}
	}
	
	private static int bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node());
		visited[0][0] = true;
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (node.x == N - 1 && node.y == M - 1) {
				return node.minCnt;
			}
			
			for (int i = 0; i < 4; i++) {
				if (node.x + dx[i] >= 0 && node.x + dx[i] < N
						&& node.y + dy[i] >= 0 && node.y + dy[i] < M
						&& maze[node.x + dx[i]][node.y + dy[i]] == 1
						&& !visited[node.x + dx[i]][node.y + dy[i]]) {
					queue.offer(new Node(node.x + dx[i], node.y + dy[i], node.minCnt + 1));
					visited[node.x + dx[i]][node.y + dy[i]] = true;
				}
			}
		}
		return -1;
	}
	
	public static void main(String args[]) throws IOException {
		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		maze = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0 ; i < N; i++) {
			String[] tmpArr = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				maze[i][j] = Integer.parseInt(tmpArr[j]);
			}
		}
		
		
		// Process
		int result = bfs();
		
		
		// Output
		bw.write(String.valueOf(result));
		
		//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				bw.write(String.valueOf(maze[i][j]));
//			}
//			bw.newLine();
//		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
