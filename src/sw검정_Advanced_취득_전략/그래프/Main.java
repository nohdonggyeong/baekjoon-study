package sw검정_Advanced_취득_전략.그래프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int T, N;
	static int minCount;
	static int[][] map;
	static int[] dh = {0, 1};
	static int[] dw = {1, 0};
	
	static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(0, 0, 0));
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			if (node.h == N - 1 && node.w == N -1) {
				minCount = Math.min(minCount, node.count);
				continue;
			}
			
			int steps = map[node.h][node.w];
			if (steps == 0) {
				continue;
			}
			
			for (int i = 1; i <= steps; i++) {
				for (int j = 0; j < 2; j++) {
					int nh = node.h + dh[j] * i;
					int nw = node.w + dw[j] * i;
					
					if (nh < N && nw < N) {
						queue.offer(new Node(nh, nw, node.count + 1));
					}
				}
			}
		}
	}
	
	static class Node {
		private int h;
		private int w;
		private int count;
		
		public Node(int h, int w, int count) {
			this.h = h;
			this.w = w;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			minCount = Integer.MAX_VALUE;
			bfs();
			sb.append("#").append(t).append(" ").append(minCount).append("\n");
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}

}
