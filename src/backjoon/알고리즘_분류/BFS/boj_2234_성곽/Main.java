package backjoon.알고리즘_분류.BFS.boj_2234_성곽;

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
	static int n, m, totalRoom = 0, maxArea = 0, maxMerge = 0;
	static int[][] map;
	static boolean[][] visited;
	static int[][][] roomCount; // [][][0]: 방 번호, [][][1]: 방 개수
	static int[] dx = {0, -1, 0, 1}; // 서, 북, 동, 남 (1, 2, 4, 8의 서, 북, 동, 남과 순서를 맞춤)
	static int[] dy = {-1, 0, 1, 0};

	static class Node {
		private int x;
		private int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static void bfs(int x, int y, int id) {
		Queue<Node> queue = new LinkedList<>();
		int nowRoomCnt = 0;
		List<Node> bfsHistory = new ArrayList<>();
		
		queue.offer(new Node(x, y));
		visited[x][y] = true;
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			nowRoomCnt++;
			bfsHistory.add(new Node(node.x, node.y));
			
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				
				if ((map[node.x][node.y] & (1 << i)) == 0) { // 이동하는 방향으로 벽이 없다면
					if (nx < 0 || ny < 0 || nx >= m || ny >=n) {
						continue;
					}
					if (visited[nx][ny]) {
						continue;
					}
					
					visited[nx][ny] = true;
					queue.offer(new Node(nx, ny));
				}
			}
		}
		
		maxArea = Math.max(maxArea, nowRoomCnt);
		
		for (Node node : bfsHistory) {
			roomCount[node.x][node.y][0] = id;
			roomCount[node.x][node.y][1] = nowRoomCnt;
		}
	}
	
	static void getMaxMerge() {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					
					if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
						continue;
					}
					
					if (roomCount[i][j][0] != roomCount[nx][ny][0]) {
						maxMerge = Math.max(maxMerge, roomCount[i][j][1] + roomCount[nx][ny][1]);
					}
				}
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[m][n];
		visited = new boolean[m][n];
		roomCount = new int[m][n][2];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					bfs(i, j, totalRoom++);
				}
			}
		}
		
		getMaxMerge();
		
		bw.write(String.valueOf(totalRoom));
		bw.newLine();
		bw.write(String.valueOf(maxArea));
		bw.newLine();
		bw.write(String.valueOf(maxMerge));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
