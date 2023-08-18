package AD검정.ad_230120_야유회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int T, N, M, answer;
	static char[][] map;
	static boolean[][][] visit;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	static Node Red, Blue;
	
	static class Node {
		private int r;
		private int c;
		private int cnt;
		
		public Node(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	static int getR() {
		visit = new boolean[N][M][2];
		Queue<Node> queue = new LinkedList<>();
		
		// 0, 0 시작
		queue.offer(new Node(0, 0, 0));
		visit[0][0][0] = true;
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			// R 도착하면 그 때까지 최소 이동시간
			if (map[node.r][node.c] == 'R') {
				return node.cnt;
			}
			
			// 동, 서 , 남, 북  탐색
			for (int i = 0; i < 4; i++) {
				int nr = node.r + dr[i];
				int nc = node.c + dc[i];
				
				// map 벗어나는가
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
					continue;
				}
				
				// 이미 방문했거나 X인가
				if (visit[nr][nc][0] || map[nr][nc] == 'X') {
					continue;
				}
				
				// 이동, 방문처리
				queue.offer(new Node(nr, nc, node.cnt + 1));
				visit[nr][nc][0] = true;
			}
		}
		return -1;
	}
	
	static int getB() {
		visit = new boolean[N][M][2];
		Queue<Node> queue = new LinkedList<>();
		
		// Red 위치 시작
		queue.offer(Red);
		visit[Red.r][Red.c][0] = true;
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			// 바깥으로 Red 던지고 B 도착하면 그 때까지 최소 이동시간
			if (visit[node.r][node.c][1] && map[node.r][node.c] == 'B') {
				return node.cnt;
			}
			
			// 동, 서 , 남, 북  탐색
			for (int i = 0; i < 4; i++) {
				int nr = node.r + dr[i];
				int nc = node.c + dc[i];
				
				// map 벗어나는가
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
					continue;
				}
				
				// 이미 방문했거나 X인가
				if (visit[nr][nc][1] || map[nr][nc] == 'X') {
					continue;
				}
				
				// 이동, 방문처리
				if (nr == 0 || nc == 0 || nr == N - 1 || nc == M - 1 || visit[node.r][node.c][1]) { // 바깥 도착했거나 이미 바깥으로 던진 경우
					queue.offer(new Node(nr, nc, node.cnt + 1));
					visit[nr][nc][1] = true;
				} else { // 바깥 도착 못한 경우
					queue.offer(new Node(nr, nc, node.cnt + 1));
					visit[nr][nc][0] = true;
				}
			}
		}
		return -1;
	}
	
	static int goToEnd() {
		visit = new boolean[N][M][2];
		Queue<Node> queue = new LinkedList<>();
		
		// Blue 위치 시작
		queue.offer(Blue);
		visit[Blue.r][Blue.c][0] = true;
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			// 바깥으로 Blue 던지고  목적지를 도착하면 그 때까지 최소 이동시간
			if (visit[node.r][node.c][1] && node.r == N - 1 && node.c == M - 1) {
				return node.cnt;
			}
			
			// 동, 서 , 남, 북  탐색
			for (int i = 0; i < 4; i++) {
				int nr = node.r + dr[i];
				int nc = node.c + dc[i];
				
				// map 벗어나는가
				if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
					continue;
				}
				
				// 이미 방문했거나 X인가
				if (visit[nr][nc][1] || map[nr][nc] == 'X') {
					continue;
				}
				
				// 이동, 방문처리
				if (nr == 0 || nc == 0 || nr == N - 1 || nc == M - 1 || visit[node.r][node.c][1]) { // 바깥 도착했거나 이미 바깥으로 던진 경우
					queue.offer(new Node(nr, nc, node.cnt + 1));
					visit[nr][nc][1] = true;
				} else { // 바깥 도착 못한 경우
					queue.offer(new Node(nr, nc, node.cnt + 1));
					visit[nr][nc][0] = true;
				}
			}
		}
		return -1;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// test case 반복
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			// map, visit, Red 위치, Blue 위치 세팅
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] =  str.charAt(j);
					
					if (map[i][j] == 'R') {
						Red = new Node(i, j, 0);
					}
					if (map[i][j] == 'B') {
						Blue = new Node(i, j, 0);
					}
				}
			}
			
			// R까지 이동
			int distR = getR();
			
			// 바깥 던지고 B까지 이동
			int distB = getB();
			
			// 바깥 던지고 종료까지 이동
			int distEnd = goToEnd();
			
			sb.append("#");
			sb.append(String.valueOf(t + 1));
			sb.append(" ");
			if (distR == -1 || distB == -1 || distEnd == -1) {
				sb.append(String.valueOf(-1));
			} else {
				sb.append(String.valueOf(distR + distB + distEnd));
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
		br.close();
	}
}
