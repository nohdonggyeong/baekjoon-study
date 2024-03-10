package backjoon.유형별로_풀어보기.BFS.boj_7576_토마토;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_bak {
	static int M, N;
	static int[][] map;
	static int[] dn = {-1, 0, 1, 0};
	static int[] dm = {0, 1, 0, -1};
	static int total, count, days;
	
	static void bfs(int n, int m) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(n, m, 0));
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			days = Math.max(days, node.day);
			
			for (int i = 0; i < 4; i++) {
				int nn = node.n + dn[i];
				int nm = node.m + dm[i];
				
				if (nn < 0 || nn >= N || nm < 0 || nm >= M) {
					continue;
				}
				if (map[nn][nm] == -1 || map[nn][nm] == 1) {
					continue;
				}
				count++;
				map[nn][nm] = 1;
				queue.offer(new Node(nn, nm, node.day + 1));
			}
		}
	}

	static class Node {
		private int n;
		private int m;
		private int day;
		
		public Node (int n, int m, int day) {
			this.n = n;
			this.m = m;
			this.day = day;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		total = 0;
		map = new int[N][M];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
				if (map[n][m] == 0) {
					total++;
				}
			}
		}

		count = 0;
		days = 0;
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (map[n][m] == 1) {
					bfs(n, m);
				}
			}
		}
		
		bw.write(count == total ? String.valueOf(days) : "-1");
		bw.flush();
		bw.close();
		br.close();
	}
}
