package 알고리즘.BFS.boj_1600_말이_되고픈_원숭이;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int K;
	static int W, H;
	static int[][] map;
	
	static int minResult;
	static boolean[][][] visited;
	
	static int[] dhh = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dhw = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dmh = {-1, 0, 1, 0};
	static int[] dmw = {0, 1, 0, -1};
	
	static class Node {
		private int h;
		private int w;
		private int k;
		private int count;
		
		public Node(int h, int w, int k, int count) {
			this.h = h;
			this.w = w;
			this.k = k;
			this.count = count;
		}
	}
	
	static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(0, 0, K, 0));
		
		visited = new boolean[H][W][K + 1];
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			if (node.h == H - 1 && node.w == W - 1) {
				minResult = Math.min(minResult, node.count);
				return;
			}

			// 원숭이의 움직임을 queue에 추가
			for (int i = 0; i < 4; i++) {
				int nh = node.h + dmh[i];
				int nw = node.w + dmw[i];
				
				if (nh < 0 || nh >= H || nw < 0 || nw >= W) {
					continue;
				}
				if (map[nh][nw] == 1 || visited[nh][nw][node.k]) {
					continue;
				}
				visited[nh][nw][node.k] = true;
				queue.offer(new Node(nh, nw, node.k, node.count + 1));
			}
			
			// 말의 움직임을 queue에 추가
			if (node.k > 0) {
				for (int i = 0; i < 8; i++) {
					int nh = node.h + dhh[i];
					int nw = node.w + dhw[i];
					
					if (nh < 0 || nh >= H || nw < 0 || nw >= W) {
						continue;
					}
					if (map[nh][nw] == 1 || visited[nh][nw][node.k - 1]) {
						continue;
					}
					visited[nh][nw][node.k - 1] = true;
					queue.offer(new Node(nh, nw, node.k - 1, node.count + 1));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		for (int h = 0; h < H; h++) {
			st = new StringTokenizer(br.readLine());
			for (int w = 0;  w < W; w++) {
				map[h][w] = Integer.parseInt(st.nextToken());
			}
		}
		
		minResult = Integer.MAX_VALUE;
		bfs();
		
		bw.write(String.valueOf(minResult == Integer.MAX_VALUE ? -1 : minResult));
		bw.flush();
		bw.close();
		br.close();
		
	}

}
