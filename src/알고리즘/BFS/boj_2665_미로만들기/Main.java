package 알고리즘.BFS.boj_2665_미로만들기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int n;
	static int[][] maze;
	static int[][] dist;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(0, 0));
		dist[0][0] = 0;
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if (nx >= 0 && nx < n
						&& ny >= 0 && ny < n
						&& dist[nx][ny] > dist[node.x][node.y]) {
					queue.offer(new Node(nx, ny));
					dist[nx][ny] = maze[nx][ny] == 1 ? dist[node.x][node.y] : dist[node.x][node.y] + 1;	
				}
			}
		}
	}
	
	static class Node {
		private int x;
		private int y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String args[]) throws IOException {
		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		maze = new int[n][n];
		dist = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split("");
			for (int j = 0; j < n; j++) {
				maze[i][j] = Integer.parseInt(str[j]);
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		
		// Process, Output
		bfs();
		bw.write(String.valueOf(dist[n - 1][n - 1]));
		
		bw.flush();
		bw.close();
		br.close();
		
//		System.out.println();
//		for(int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print(maze[i][j]);
//			}
//			System.out.println();
//		}
		
	}
}
