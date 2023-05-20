package algorithm.bfs.boj_1261_알고스팟;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {
	private static int N;
	private static int M;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	private static int[][] maze;
	private static boolean[][] visited;
	
	private static int bfs() {
		PriorityQueue<Room> priorityQueue = new PriorityQueue<>();
		priorityQueue.offer(new Room(0, 0, 0));

		Room nowRoom;
		int nx, ny;
		while (!priorityQueue.isEmpty()) {
			nowRoom = priorityQueue.poll();
			visited[nowRoom.x][nowRoom.y] = true;
			
			if (nowRoom.x == M - 1 && nowRoom.y == N - 1) {
				return nowRoom.cnt;
			}
			
			for (int i = 0; i < 4; i++) {
				nx = nowRoom.x + dx[i];
				ny = nowRoom.y + dy[i];
				if (nx >= 0 && nx <= M - 1 && ny >= 0 && ny <= N - 1 && !visited[nx][ny]) {
					priorityQueue.offer(new Room(nx, ny, nowRoom.cnt + maze[nx][ny]));
				}
			}
		}
		return -1;
	}
	
	private static class Room implements Comparable<Room> {
		private int x;
		private int y;
		private int cnt;
		
		Room(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Room room) {
			return this.cnt - room.cnt;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// setting
		String[] inputSizeArr = br.readLine().split(" ");
		N = Integer.parseInt(inputSizeArr[0]);
		M = Integer.parseInt(inputSizeArr[1]);
		maze = new int[M][N];
		visited = new boolean[M][N];

		String[] rowData;
		for (int i = 0; i < M; i++) {
			rowData = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				maze[i][j] = Integer.parseInt(rowData[j]);
			}
		}
		
		// bfs print
		bw.write(String.valueOf(bfs()));
		
//		// check print
//		bw.newLine();
//		for(int i = 0; i <M; i ++) {
//			for (int j = 0; j< N; j++) {
//				bw.write(String.valueOf(maze[i][j]));
//			}
//			bw.newLine();
//		}
		
		bw.flush();
		
		bw.close();
		br.close();
		
	}
}
