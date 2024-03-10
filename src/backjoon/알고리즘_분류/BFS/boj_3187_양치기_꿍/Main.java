package backjoon.알고리즘_분류.BFS.boj_3187_양치기_꿍;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C, resultWolf = 0, resultSheep = 0;
	static char[][] map;
	static boolean[][] visit;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	static void bfs(int x, int y) {
		Queue<Node> queue = new LinkedList<>();
		int v = 0, k = 0;
		
		queue.offer(new Node(x, y));
		visit[x][y] = true;
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();

			switch (map[node.x][node.y]) {
			case 'v':
				v++;
				break;
			case 'k':
				k++;
				break;
			default:
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				
				if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
					continue;
				}
				
				if (visit[nx][ny]) {
					continue;
				}
				
				queue.offer(new Node(nx, ny));
				visit[nx][ny] = true;
			}
		}
		
		if (v < k) {
			v = 0;
		} else {
			k = 0;
		}
		
		resultWolf += v;
		resultSheep += k;
	}
	
	static class Node {
		private int x;
		private int y;
		
		private Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visit = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			String strArr = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = strArr.charAt(j);
				
				if (strArr.charAt(j) == '#') {
					visit[i][j] = true;
				}
			}
		}
		
		for (int i = 0; i < R; i ++) {
			for (int j = 0; j < C; j++) {
				if (!visit[i][j]) {
					bfs(i, j);
				}
			}
		}
		
		bw.write(String.valueOf(resultSheep));
		bw.write(" ");
		bw.write(String.valueOf(resultWolf));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
