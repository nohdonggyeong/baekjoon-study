package sw검정.advanced_기출문제.ad_230120_야유회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int T, answer;
	static int N, M;
	static char[][] map;
	static Node red, blue;
	static boolean[][][] visited;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static class Node {
		private int r;
		private int c;
		private int steps;
		private int thrown;
		
		public Node(int r, int c, int steps, int thrown) {
			this.r = r;
			this.c = c;
			this.steps = steps;
			this.thrown = thrown;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new char[N][M];
			for (int n = 0; n < N; n++) {
				String str = br.readLine();
				for (int m = 0; m < M; m++) {
					map[n][m] = str.charAt(m);
					if (map[n][m] == 'R') {
						red = new Node(n, m, 0, 0);
					} else if (map[n][m] == 'B') {
						blue = new Node(n, m, 0, 0);
					}
				}
			}
			
			// 0, 0에서 R까지 이동
			Queue<Node> queue = new LinkedList<>();
			queue.offer(new Node(0, 0, 0, 0));
			
			visited = new boolean[N][M][2];
			visited[0][0][0] = true;
			
			int minRedPath = 0;
			while (!queue.isEmpty()) {
				Node node = queue.poll();
				
				if (node.r == red.r && node.c == red.c) {
					minRedPath = node.steps;
					break;
				}
				
				for (int i = 0; i < 4; i++) {
					int nr = node.r + dr[i];
					int nc = node.c + dc[i];
					
					if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
						continue;
					}
					
					if (map[nr][nc] == 'X' || visited[nr][nc][node.thrown]) {
						continue;
					}
					
					queue.offer(new Node(nr, nc, node.steps + 1, node.thrown));
					visited[nr][nc][node.thrown] = true;
				}
			}
			
			// R에서 B까지 이동
			queue = new LinkedList<>();
			queue.offer(new Node(red.r, red.c, 0, 0));
			
			visited = new boolean[N][M][2];
			visited[0][0][0] = true;
			
			int minBluePath = 0;
			while (!queue.isEmpty()) {
				Node node = queue.poll();
				
				if (node.r == blue.r && node.c == blue.c && node.thrown == 1) {
					minBluePath = node.steps;
					break;
				}
				
				for (int i = 0; i < 4; i++) {
					int nr = node.r + dr[i];
					int nc = node.c + dc[i];
					
					if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
						continue;
					}
					
					if (map[nr][nc] == 'X' || visited[nr][nc][node.thrown]) {
						continue;
					}
					
					if (nr == 0 || nc == 0 || nr == N -1 || nc == M - 1) {
						queue.offer(new Node(nr, nc, node.steps + 1, 1));
						visited[nr][nc][1] = true;
					} else {
						queue.offer(new Node(nr, nc, node.steps + 1, node.thrown));
						visited[nr][nc][node.thrown] = true;
					}
				}
			}
			
			// B에서 목적지까지 이동
			queue = new LinkedList<>();
			queue.offer(new Node(blue.r, blue.c, 0, 0));
			
			visited = new boolean[N][M][2];
			visited[0][0][0] = true;
			
			int minFinalPath = 0;
			while (!queue.isEmpty()) {
				Node node = queue.poll();
				
				if (node.r == N - 1 && node.c == M - 1 && node.thrown == 1) {
					minFinalPath = node.steps;
					break;
				}
				
				for (int i = 0; i < 4; i++) {
					int nr = node.r + dr[i];
					int nc = node.c + dc[i];
					
					if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
						continue;
					}
					
					if (map[nr][nc] == 'X' || visited[nr][nc][node.thrown]) {
						continue;
					}

					if (nr == 0 || nc == 0 || nr == N -1 || nc == M - 1) {
						queue.offer(new Node(nr, nc, node.steps + 1, 1));
						visited[nr][nc][1] = true;
					} else {
						queue.offer(new Node(nr, nc, node.steps + 1, node.thrown));
						visited[nr][nc][node.thrown] = true;
					}
				}
			}
			
//			System.out.println("redPath: " + minRedPath + ", bluePath: " + minBluePath + ", finalPath: " + minFinalPath);
			
			if (minRedPath == 0 || minBluePath == 0 || minFinalPath ==0) {
				answer = -1;
			} else {
				answer = minRedPath + minBluePath + minFinalPath;
			}
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}
}
