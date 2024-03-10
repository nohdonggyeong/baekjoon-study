package backjoon.유형별로_풀어보기.BFS.boj_14442_벽_부수고_이동하기_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] map;
	static boolean[][][] visited;
	static int result;
	static int[] dn = {-1, 0, 1, 0};
	static int[] dm = {0, 1, 0, -1};
	
	static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(0, 0, K, 1));
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			if (node.n == N - 1 && node.m == M - 1) {
				result = Math.min(result, node.steps);
			}
			
			for (int i = 0; i < 4; i++) {
				int nn = node.n + dn[i];
				int nm = node.m + dm[i];
				
				if (nn < 0 || nn >= N || nm < 0 || nm >= M) {
					continue;
				}
				if (map[nn][nm] == 1) {					
					if (node.k > 0 && !visited[nn][nm][node.k - 1]) {
						visited[nn][nm][node.k - 1] = true;
						queue.offer(new Node(nn, nm, node.k - 1, node.steps + 1));
					}
				} else {
					if (!visited[nn][nm][node.k]) {
						visited[nn][nm][node.k] = true;
						queue.offer(new Node(nn, nm, node.k, node.steps + 1));
					}
				}
			}
		}
	}
	
	static class Node {
		private int n;
		private int m;
		private int k;
		private int steps;
		
		public Node(int n, int m, int k, int steps) {
			this.n = n;
			this.m = m;
			this.k = k;
			this.steps = steps;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int n = 0; n < N; n++) {
			String[] strArr = br.readLine().split("");
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(strArr[m]);
			}
		}
		
		result = Integer.MAX_VALUE;
		visited = new boolean[N][M][K + 1];
		bfs();
		
		bw.write(result == Integer.MAX_VALUE ? "-1" : String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}

}
