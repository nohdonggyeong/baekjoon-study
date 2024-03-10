package backjoon.알고리즘_분류.BFS.boj_7569_토마토;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int M, N, H;
	static int[][][] map;
	static Queue<Node> queue;
	static int maxDays;
	static int[] dh = {1, -1, 0, 0, 0, 0};
	static int[] dn = {0, 0, -1, 1, 0, 0};
	static int[] dm = {0, 0, 0, 0, 1, -1};
	
	static class Node {
		private int h;
		private int n;
		private int m;
		private int c;
		
		public Node (int h, int n, int m, int c) {
			this.h = h;
			this.n = n;
			this.m = m;
			this.c = c;
		}
	}
	
	static void bfs() {
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			for (int i = 0; i < 6; i++) {
				int nh = node.h + dh[i];
				int nn = node.n + dn[i];
				int nm = node.m + dm[i];
				
				if (nh < 0 || nh >= H || nn < 0 || nn >= N || nm < 0 || nm >= M) {
					continue;
				}
				if (map[nh][nn][nm] != 0) {
					continue;
				}
				map[nh][nn][nm] = 1;
				maxDays = Math.max(maxDays, node.c + 1);
				queue.offer(new Node(nh, nn, nm, node.c + 1));
			}
		}
	}
	
	static boolean isFinished() {
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					if (map[h][n][m] == 0) {
						return false;
					}
				}
			}			
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		queue = new LinkedList<>();
		
		map = new int[H][N][M];
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				for (int m = 0; m < M; m++) {
					map[h][n][m] = Integer.parseInt(st.nextToken());
					if (map[h][n][m] == 1) {
						queue.offer(new Node(h, n, m, 0));
					}
				}
			}			
		}
		
//		for (int h = 0; h < H; h++) {
//			for (int n = 0; n < N; n++) {
//				for (int m = 0; m < M; m++) {
//					System.out.print(String.valueOf(map[h][n][m]) + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}
		
		maxDays = 0;
		bfs();
		
		bw.write(isFinished() ? String.valueOf(maxDays) : "-1");
		bw.flush();
		bw.close();
		br.close();
	}
}
