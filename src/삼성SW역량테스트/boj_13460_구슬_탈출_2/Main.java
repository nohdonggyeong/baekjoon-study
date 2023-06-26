package 삼성SW역량테스트.boj_13460_구슬_탈출_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, answer = -1;
	static char[][] map;
	static boolean[][][][] visit;
	static Node redBall, blueBall;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static void solution() {
		Queue<Node> redBallQueue = new LinkedList<>();
		Queue<Node> blueBallQueue = new LinkedList<>();
		
		redBallQueue.offer(redBall);
		blueBallQueue.offer(blueBall);
		
		visit[redBall.x][redBall.y][blueBall.x][blueBall.y] = true;
		
		while (!redBallQueue.isEmpty() && !blueBallQueue.isEmpty()) {
			redBall  = redBallQueue.poll();
			blueBall = blueBallQueue.poll();
			
			if (redBall.cnt > 10) {
				return;
			}
			
			if (map[blueBall.x][blueBall.y] == 'O') {
				continue;
			}
			
			if (map[redBall.x][redBall.y] == 'O') {
				answer = redBall.cnt;
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int newRedX = redBall.x;
				int newRedY = redBall.y;
				while (true) {
					newRedX += dx[i];
					newRedY += dy[i];
					if (map[newRedX][newRedY] == '#') {
						newRedX -= dx[i];
						newRedY -= dy[i];
						break;
					}
					if (map[newRedX][newRedY] == 'O') {
						break;
					}
				}
				
				int newBlueX = blueBall.x;
				int newBlueY = blueBall.y;
				while (true) {
					newBlueX += dx[i];
					newBlueY += dy[i];
					if (map[newBlueX][newBlueY] == '#') {
						newBlueX -= dx[i];
						newBlueY -= dy[i];
						break;
					}
					if (map[newBlueX][newBlueY] == 'O') {
						break;
					}
				}
				
				if (newRedX == newBlueX && newRedY == newBlueY && map[newRedX][newRedY] != 'O') {
					int distRed = Math.abs(newRedX - redBall.x) + Math.abs(newRedY - redBall.y);
					int distBlue = Math.abs(newBlueX - blueBall.x) + Math.abs(newBlueY - blueBall.y);
					
					if (distRed > distBlue) {
						newRedX -= dx[i];
						newRedY -= dy[i];
					} else {
						newBlueX -= dx[i];
						newBlueY -= dy[i];
					}
				}
				
				if (!visit[newRedX][newRedY][newBlueX][newBlueY]) {
					redBallQueue.offer(new Node(newRedX, newRedY, redBall.cnt + 1));
					blueBallQueue.offer(new Node(newBlueX, newBlueY, blueBall.cnt + 1));
					visit[newRedX][newRedY][newBlueX][newBlueY] = true;
				}
			}
		}
	}
	
	static class Node {
		private int x;
		private int y;
		private int cnt;
		
		public Node (int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		map = new char[N][M];
		visit = new boolean [N][M][N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				
				if (str.charAt(j) == 'R') {
					redBall = new Node(i, j, 0);
				}
				if (str.charAt(j) == 'B') {
					blueBall = new Node(i, j, 0);
				}
			}
		}
		
		solution();
		
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
}
