package 알고리즘.다익스트라.boj_4485_녹색_옷_입은_애가_젤다지;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int[][] loss;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	static final int INF = Integer.MAX_VALUE;
	
	static class Node implements Comparable<Node> {
		int r;
		int c;
		int sum;
		
		Node (int r, int c, int sum) {
			this.r = r;
			this.c = c;
			this.sum = sum;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.sum, o.sum);
		}
	}
	
	static void dijkstra() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				loss[i][j] = INF;
			}
		}
		loss[0][0] = map[0][0];
		
		Queue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0, map[0][0]));
		
		boolean[][] visited = new boolean[N][N];
		
		while (!pq.isEmpty()) {
			Node curNode = pq.remove();
			
			if (visited[curNode.r][curNode.c]) {
				continue;
			}
			
			visited[curNode.r][curNode.c] = true;
			
			for (int d = 0; d < 4; d++) {
				int nr = curNode.r + dr[d];
				int nc = curNode.c + dc[d];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
					continue;
				}
				if (loss[nr][nc] > loss[curNode.r][curNode.c] + map[nr][nc]) {
					loss[nr][nc] = loss[curNode.r][curNode.c] + map[nr][nc];
					
					pq.add(new Node(nr, nc, loss[nr][nc]));
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringBuilder sb = new StringBuilder();
			StringTokenizer st;
			
			int index = 1;
			while (true) {
				N = Integer.parseInt(br.readLine());
				if (N == 0) {
					break;
				}
				
				map = new int[N][N];
				for (int r = 0; r < N; r++) {
					st = new StringTokenizer(br.readLine());
					for (int c = 0; c < N; c++) {
						map[r][c] = Integer.parseInt(st.nextToken());
					}
				}
				
				loss = new int[N][N];
				dijkstra();
				
				sb.append("Problem ").append(index++).append(": ").append(loss[N - 1][N - 1]).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
