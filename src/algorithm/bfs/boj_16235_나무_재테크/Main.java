package algorithm.bfs.boj_16235_나무_재테크;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] map, A;
	static List<Tree> treeList, deathList;
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};

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
		List<Tree> addList = new ArrayList<>();
		List<Tree> removeList = new ArrayList<>();
		
		Collections.sort(treeList);
		
		for (Tree el : treeList) {
			if (map[el.x][el.y] >= el.age) {
				map[el.x][el.y] -= el.age;
				addList.add(new Tree(el.x, el.y, el.age + 1));
				removeList.add(new Tree(el.x, el.y, el.age));
			} else {
				deathList.add(new Tree(el.x, el.y, el.age));
				removeList.add(new Tree(el.x, el.y, el.age));
			}
		}
		treeList.removeAll(removeList);
		treeList.addAll(addList);
	}
	
	static void summer() {
		for (Tree el : deathList) {
			map[el.x][el.y] += el.age / 2;
		}
		deathList.clear();
	}
	
	static void autumn() {
		List<Tree> newTreeList = new ArrayList<>();
		for (Tree el : treeList) {
			if (el.age % 5 == 0) {
				for (int i = 0; i < 8; i++) {
					int nx = el.x +	dr[i];
					int ny = el.y + dr[i];
					
					if (nx <= 0 || ny <= 0 || nx > N || ny > N) {
						continue;
					}
					
					newTreeList.add(new Tree(nx, ny, 1));
				}
			}
		}
		treeList.addAll(newTreeList);
	}
	
	static void winter() {
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				map[r][c] += A[r][c];
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
		
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				// 추가 양분 설정
				A[r][c] = Integer.parseInt(st.nextToken());
				// 시작 양분 초기화
				map[r][c] = 5;
			}
		}
		
		// 나무 초기화
		treeList = new ArrayList<>();
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			treeList.add(new Tree(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		// 계절 반복
		deathList = new ArrayList<>();
		for (int k = 0; k < K; k++) {
			spring();
			summer();
			autumn();
			winter();
		}
		
		bw.write(String.valueOf(treeList.size()));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
