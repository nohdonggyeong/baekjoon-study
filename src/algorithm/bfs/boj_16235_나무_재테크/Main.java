package algorithm.bfs.boj_16235_나무_재테크;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] map, A;
	static PriorityQueue<Tree> treeQueue;
	static Queue<Tree> deathQueue;
	static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

	static class Tree implements Comparable<Tree> {
		private int x;
		private int y;
		private int age;
		
		public Tree(int x, int y ,int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return age - o.age; // 오름 차순
			// return o.age - age; // 내림차순
		}
	}
	
	static void spring() {
		while (!treeQueue.isEmpty()) {
			Tree tree = treeQueue.poll();
			if (map[tree.x][tree.y] >= tree.age) {
				map[tree.x][tree.y]-= tree.age;
				treeQueue.offer(new Tree(tree.x, tree.y, tree.age + 1));
			} else {
				deathQueue.offer(new Tree(tree.x, tree.y, tree.age));
			}
		}
	}
	
	static void summer() {
		while (!deathQueue.isEmpty()) {
			Tree dead = deathQueue.poll();
			map[dead.x][dead.y] += dead.age / 2;
		}
	}
	
	static void autumn() {
		while (!treeQueue.isEmpty()) {
			Tree tree = treeQueue.poll();
			if (tree.age % 5 == 0) {
				for (int i = 0; i < 8; i++) {
					int nx = tree.x + dx[i];
					int ny = tree.y + dy[i];
					
					if (nx <= 0 || ny <= 0 || nx > N || ny > N) {
						continue;
					}
					treeQueue.offer(new Tree(nx, ny, 1));
				}
			}
			treeQueue.offer(tree);
		}
	}
	
	static void winter() {
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				map[x][y] += A[x][y];
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N + 1][N + 1];
		A = new int[N + 1][N + 1];
		
		for (int x = 1; x <= N; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 1; y <= N; y++) {
				// 추가 양분 설정
				A[x][y] = Integer.parseInt(st.nextToken());
				// 시작 양분 초기화
				map[x][y] = 5;
			}
		}
		
		// 나무 초기화
		treeQueue = new PriorityQueue<>();
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			treeQueue.offer(new Tree(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		// 계절 반복
		deathQueue = new LinkedList<>();
		for (int k = 0; k < K; k++) {
			spring();
			summer();
			autumn();
			winter();
		}
		
		bw.write(String.valueOf(treeQueue.size()));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
