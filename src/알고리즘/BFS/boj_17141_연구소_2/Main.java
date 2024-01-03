package 알고리즘.BFS.boj_17141_연구소_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static List<Node> candidates;
	
	static class Node {
		private int h;
		private int w;
		private int time;
		
		public Node(int h, int w, int time) {
			this.h = h;
			this.w = w;
			this.time = time;
		}
	}

	static int n, r;
	static int[] input, temp;
	static boolean[] visited;
	static List<int[]> output;
	
	static void combination(int start, int depth) {
		if (depth == r) {
			temp = new int[r];
			int index = 0;
			for (int i = 0; i < n; i++) {
				if (visited[i]) {
					temp[index++] = i;
				}
			}
			output.add(temp.clone());
			return;
		}
		
		for (int i = start; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				combination(i + 1, depth + 1);
				visited[i] = false;
			}
		}
	}

	static int[][] mapBak;
	static Queue<Node> queue;
	static int minTime, maxTime;
	static int[] dh = {0, 1, 0, -1};
	static int[] dw = {-1, 0, 1, 0};
	
	static boolean isComplete() {
		for (int h = 0; h < N; h++) {
			for (int w = 0; w < N; w++) {
				if (map[h][w] == 0) {
					return false;
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
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		candidates = new ArrayList<Main.Node>();
		map = new int[N][N];
		for (int h = 0; h < N; h++) {
			st = new StringTokenizer(br.readLine());
			for (int w = 0; w < N; w++) {
				map[h][w] = Integer.parseInt(st.nextToken());
				if (map[h][w] == 2) {
					candidates.add(new Node(h, w, 0));
				}
			}
		}
		
		n = candidates.size();
		r = M;
		
		input = new int[n];
		for (int i = 0; i < n; i++) {
			input[i] = i;
		}
		
		visited = new boolean[n];
		output = new ArrayList<int[]>();
		combination(0, 0);
		
//		for (int[] el : output) {
//			for (int e : el) {
//				System.out.print(String.valueOf(e) + " ");
//			}
//			System.out.println();
//		}
		
		for (int h = 0; h < N; h++) {
			for (int w = 0; w < N; w++) {
				if (map[h][w] == 2) {
					map[h][w] = 0;
				}
			}
		}
		
		mapBak = new int[N][N];
		for (int h = 0; h < N; h++) {
			mapBak[h] = map[h].clone();
		}
		minTime = Integer.MAX_VALUE;
		for (int[] el : output) {
			for (int h = 0; h < N; h++) {
				map[h] = mapBak[h].clone();
			}
			queue = new LinkedList<Main.Node>();
			for (int e : el) {
				queue.offer(candidates.get(e));
				map[candidates.get(e).h][candidates.get(e).w] = 1;
			}
			
//			for (int h = 0; h < N; h++) {
//				for (int w = 0; w < N; w++) {
//					System.out.print(map[h][w] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			maxTime = 0;
			while (!queue.isEmpty()) {
				Node node = queue.poll();
				
				for (int i = 0; i < 4; i++) {
					int nh = node.h + dh[i];
					int nw = node.w + dw[i];
					
					if (nh < 0 || nw < 0 || nh >= N || nw >= N) {
						continue;
					}
					if (map[nh][nw] != 0) {
						continue;
					}
					
					queue.offer(new Node(nh, nw, node.time + 1));
					map[nh][nw] = node.time + 1;
					maxTime = Math.max(maxTime, node.time + 1);
				}
			}
			
			if (isComplete()) {
				minTime = Math.min(minTime, maxTime);
				
//				for (int h = 0; h < N; h++) {
//					for (int w = 0; w < N; w++) {
//						System.out.print(map[h][w] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println();
			}
		}
		
		bw.write(minTime == Integer.MAX_VALUE ? "-1" : String.valueOf(minTime));
		bw.flush();
		bw.close();
		br.close();
	}

}
