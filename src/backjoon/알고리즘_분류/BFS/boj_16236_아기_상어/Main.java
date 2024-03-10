package backjoon.알고리즘_분류.BFS.boj_16236_아기_상어;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static Fish shark;
	static List<Fish> fishList;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int fishCount, resultTime;
	
	static class Fish implements Comparable<Fish>{
		private int r;
		private int c;
		private int size;
		private int distance;
		
		public Fish(int r, int c, int size) {
			this.r = r;
			this.c = c;
			this.size = size;
		}

		public Fish(int r, int c, int size, int distance) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.distance = distance;
		}
		
		public void setDistance(int distance) {
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Fish o) {
			if (distance > o.distance) {
				return 1;
			} else if (distance == o.distance) {
				if (r > o.r) {
					return 1;
				} else if (r == o.r) {
					return c - o.c;
				}
			}
			return -1;
		}
	}
	
	static void setDistance() {
		for (Fish el : fishList) {
			el.setDistance(bfs(el.r, el.c));
		}
	}
	
	static int bfs(int x, int y) {		
		Queue<Fish> queue = new LinkedList<>();
		queue.offer(new Fish(shark.r, shark.c, 0, 0));
		visited = new boolean[N][N];
		visited[shark.r][shark.c] = true;
		
		while (!queue.isEmpty()) {
			Fish node = queue.poll();
			if (node.r == x && node.c == y) {
				return node.distance;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = node.r + dr[i];
				int nc = node.c + dc[i];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
					continue;
				}
				if (visited[nr][nc] || map[nr][nc] > shark.size) {
					continue;
				}
				
				queue.offer(new Fish(nr, nc, 0, node.distance + 1));
				visited[nr][nc] = true;
			}
		}
		
		return -1;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		fishList = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int n = Integer.parseInt(st.nextToken());
				if (n == 9) {
					shark = new Fish(r, c, 2);
					map[r][c] = 0;
				} else if (n > 0) {
					fishList.add(new Fish(r, c, n));
					map[r][c] = n;
				}
			}
		}
		
		fishCount = 0;
		resultTime = 0;
		while (true) {
			setDistance();
			Collections.sort(fishList);
			
			if (fishList.size() < 1) {
				break;
			}
			
			Fish food = null;
			for (int i = 0; i < fishList.size(); i++) {
				if (fishList.get(i).distance > 0 && fishList.get(i).size < shark.size) {
					food = fishList.get(i);
					break;
				}
			}
			
			if (food == null) {
				break;
			} else {
				fishCount++;
				int sharkSize = shark.size;
				if (fishCount == sharkSize) {
					fishCount = 0;
					sharkSize++;
				}
				shark = new Fish(food.r, food.c, sharkSize, 0);
				resultTime += food.distance;
				
//				System.out.println();
//				System.out.print(food.r + " " + food.c + " " + food.distance);
				
				map[food.r][food.c] = 0;
				fishList.remove(food);
			}
		}
//		System.out.println();
		
		bw.write(String.valueOf(resultTime));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
