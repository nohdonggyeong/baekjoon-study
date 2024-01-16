package AD검정.ad_230120_야유회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main3 {
	static int T, N, M;
	static char[][] map;
	// 바깥으로 던졌으면 visited[r][c][1]을 true 처리하고 visited[r][c][0]은 false 유지하기
	static boolean[][][] visited;
	
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	static class Node {
		private int r;
		private int c;
		private int steps;
		private boolean thrown;
		
		public Node (int r, int c, int steps, boolean thrown) {
			this.r = r;
			this.c = c;
			this.steps = steps;
			this.thrown = thrown;
		}
	}
	
	static Node red, blue;
	static int redSteps, blueSteps, finishSteps;
	
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
					switch (str.charAt(m)) {
					case '.':
					case 'X':
						map[n][m] = str.charAt(m);
						break;
					case 'R':
						map[n][m] = str.charAt(m);
						red = new Node(n, m, 0, false);
						break;
					case 'B':
						map[n][m] = str.charAt(m);
						blue = new Node(n, m, 0, false);
						break;
					default:
						 break;
					}
				}
			}
			
			// 0, 0에서 R까지
			redSteps = 0;
			
			Queue<Node> queue = new LinkedList<Main3.Node>();
			queue.offer(new Node(0, 0, 0, false));
			
			visited = new boolean[N][M][2];
			visited[0][0][0] = true;
			
			while (!queue.isEmpty()) {
				Node node = queue.poll();
				
				if (node.r == red.r && node.c == red.c) {
					redSteps = node.steps;
					break;
				}
				
				for (int i = 0; i < 4; i++) {
					int nr = node.r + dr[i];
					int nc = node.c + dc[i];
					
					if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
						continue;
					}
					if (map[nr][nc] == 'X' || visited[nr][nc][0]) {
						continue;
					}
					
					queue.offer(new Node(nr, nc, node.steps + 1, false));
					visited[nr][nc][0] = true;
				}
			}
			
			// R에서 바깥으로 던지고 B까지
			blueSteps = 0;
			
			queue = new LinkedList<Main3.Node>();
			queue.offer(new Node(red.r, red.c, 0, false));
			
			visited = new boolean[N][M][2];
			visited[red.r][red.c][0] = true;
			
			while (!queue.isEmpty()) {
				Node node = queue.poll();
				
				if (node.r == blue.r && node.c == blue.c && node.thrown) {
					blueSteps = node.steps;
					break;
				}
				
				for (int i = 0; i < 4; i++) {
					int nr = node.r + dr[i];
					int nc = node.c + dc[i];
					
					if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
						continue;
					}
					if (map[nr][nc] == 'X' || (!node.thrown && visited[nr][nc][0]) || (node.thrown && visited[nr][nc][1])) {
						continue;
					}
					
					if (!node.thrown) {
						if (nr == 0 || nc == 0 || nr == N -1 || nc == M - 1) {
							queue.offer(new Node(nr, nc, node.steps + 1, true));
							visited[nr][nc][1] = true;
						} else {
							queue.offer(new Node(nr, nc, node.steps + 1, false));
							visited[nr][nc][0] = true;
						}
					} else {
						queue.offer(new Node(nr, nc, node.steps + 1, true));
						visited[nr][nc][1] = true;
					}
					
				}
			}
			
			// B에서 바깥으로 던지고 N - 1, M - 1까지
			finishSteps = 0;
			
			queue = new LinkedList<Main3.Node>();
			queue.offer(new Node(blue.r, blue.c, 0, false));
			
			visited = new boolean[N][M][2];
			visited[blue.r][blue.c][0] = true;
			
			while (!queue.isEmpty()) {
				Node node = queue.poll();
				
				if (node.r == N - 1 && node.c == M - 1 && node.thrown) {
					finishSteps = node.steps;
					break;
				}
				
				for (int i = 0; i < 4; i++) {
					int nr = node.r + dr[i];
					int nc = node.c + dc[i];
					
					if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
						continue;
					}
					if (map[nr][nc] == 'X' || (!node.thrown && visited[nr][nc][0]) || (node.thrown && visited[nr][nc][1])) {
						continue;
					}
					
					if (!node.thrown) {
						if (nr == 0 || nc == 0 || nr == N -1 || nc == M - 1) {
							queue.offer(new Node(nr, nc, node.steps + 1, true));
							visited[nr][nc][1] = true;
						} else {
							queue.offer(new Node(nr, nc, node.steps + 1, false));
							visited[nr][nc][0] = true;
						}
					} else {
						queue.offer(new Node(nr, nc, node.steps + 1, true));
						visited[nr][nc][1] = true;
					}
				}
			}
			
			if (redSteps == 0 || blueSteps == 0 || finishSteps == 0) {
				sb.append("#").append(t).append(" ").append(-1).append("\n");
			} else {
				sb.append("#").append(t).append(" ").append(redSteps + blueSteps + finishSteps).append("\n");				
			}
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}

}
