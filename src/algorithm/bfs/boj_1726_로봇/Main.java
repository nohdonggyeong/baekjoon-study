package algorithm.bfs.boj_1726_로봇;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int M, N;
	static int[][] map;
	static boolean[][][] visited;
	static String[][][] history;
	static Node startNode, endNode;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static class Node {
		private int x;
		private int y;
		private int direction;
		private int count;
		
		public Node(int x, int y, int direction, int count) {
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.count = count;
		}
	}
	
	static int bfs() {
		Queue<Node> queue = new LinkedList<>();
		
		queue.offer(startNode);
		visited[startNode.x][startNode.y][startNode.direction] = true;
		history[startNode.x][startNode.y][startNode.direction] = "(" + startNode.x + ", " + startNode.y + ", " + startNode.direction + ", " + startNode.count + ")";
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			if (node.x == endNode.x && node.y == endNode.y && node.direction == endNode.direction) {
				System.out.println(history[node.x][node.y][node.direction]);
				return node.count;
			}
			
			// 같은 방향, 같은 위치의 시작과 종료라면 아래 같은 방향은 제외하는 로직 때문에 직진 계산을 먼저해줘야 함.
			for (int i = 1; i <= 3; i++) {
				int nx = node.x + dx[node.direction] * i;
				int ny = node.y + dy[node.direction] * i;
				if (nx < 0 || ny < 0 || nx >= M || ny >= N || map[nx][ny] != 0) {
					break;
				} else {
					if (!visited[nx][ny][node.direction]) {
						queue.offer(new Node(nx, ny, node.direction, node.count + 1));
						visited[nx][ny][node.direction] = true;
						history[nx][ny][node.direction] = history[node.x][node.y][node.direction] + " / " + "(" + nx + ", " + ny + ", " + node.direction + ", " + (node.count + 1) + ")";
					}
				}
			}
			
			for (int i = 0; i < 4; i++) {
				if (node.direction != i && !visited[node.x][node.y][i]) {					
					int rotateCnt;
					if ((node.direction == 0 && i == 1)
							|| (node.direction == 1 && i == 0)
							|| (node.direction == 2 && i == 3)
							|| (node.direction == 3 && i == 2)) {
						rotateCnt = 2;
					} else {
						rotateCnt = 1;
					}
					queue.offer(new Node(node.x, node.y, i, node.count + rotateCnt));
					visited[node.x][node.y][i] = true;
					history[node.x][node.y][i] = history[node.x][node.y][node.direction] + " / " + "(" + node.x + ", " + node.y + ", " + i + ", " + (node.count + rotateCnt) + ")";
				}
			}
		}
		
		return -1;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		visited = new boolean[M][N][4];
		history = new String[M][N][4];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		startNode = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);

		st = new StringTokenizer(br.readLine());
		endNode = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);
		
		bw.write(String.valueOf(bfs()));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
