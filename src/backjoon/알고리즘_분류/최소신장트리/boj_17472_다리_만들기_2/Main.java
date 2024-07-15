package backjoon.알고리즘_분류.최소신장트리.boj_17472_다리_만들기_2;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] visit;
	static int islandNum = 0;

	static class Node {
		int r, c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	static class Edge implements Comparable<Edge> {
		int u;
		int v;
		int w;

		Edge(int s, int e, int w) {
			this.u = s;
			this.v = e;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.w, o.w);
		}
	}

	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static int[] parent;
	static int bridgeCnt = 0;
	static int result = 0;

	static void bfs(Node startNode) { // 섬 번호 매기기
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(startNode);
		visit[startNode.r][startNode.c] = true;
		map[startNode.r][startNode.c] = islandNum;

		int nr, nc;
		while (!queue.isEmpty()) {
			Node curNode = queue.poll();

			for (int d = 0; d < 4; d++) {
				nr = curNode.r + dr[d];
				nc = curNode.c + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] != 1 || visit[nr][nc]) {
					continue;
				}

				queue.add(new Node(nr, nc));
				visit[nr][nc] = true;
				map[nr][nc] = islandNum;
			}
		}
	}

	static void exhaustiveSearch(Node startNode, int num) { // 다리 후보 모으기
		int nr = startNode.r;
		int nc = startNode.c;
		int length = 0;

		for (int d = 0; d < 4; d++) { // 상하좌우 중에서 한 방향으로 계속 이동
			while (true) {
				nr += dr[d];
				nc += dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M // 다른 섬에 이어지지 못하고 범위 벗어나거나
						|| map[nr][nc] == num) { // 자신과 같은 번호의 섬 부분과 이어지면 취소
					nr = startNode.r;
					nc = startNode.c;
					length = 0;
					break;
				}

				if (map[nr][nc] != 0) { // 다른 섬에 이어졌는데
					if (length > 1) { // length가 1보다 큰 경우 다리 후보 추가
						pq.add(new Edge(num, map[nr][nc], length));
					} // 그리고 다른 방향도 살펴보기
					nr = startNode.r;
					nc = startNode.c;
					length = 0;
					break;
				} else {
					++length; // 빈칸이면 더 나아가기
				}
			}
		}
	}

	static int find(int a) {
		if (a == parent[a])
			return a;

		return parent[a] = find(parent[a]);
	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a != b) {
			parent[a] = b;
		} else {
			return;
		}
	}
	
	static void kruskal() { // 다리 후보 중 mst 가중치 합산
		parent = new int[islandNum + 1];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}

		int a, b;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			a = find(edge.u);
			b = find(edge.v);

			if (a == b) {
				continue;
			}

			union(edge.u, edge.v);

			result += edge.w;
			++bridgeCnt;
		}

		if (result == 0 || bridgeCnt != islandNum - 1) {
			result = -1;
		}
	}
	
	public static void main(String[] args) throws Exception {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			visit = new boolean[N][M];
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				for (int m = 0; m < M; m++) {
					map[n][m] = Integer.parseInt(st.nextToken());
				}
			}

			// 섬 번호 매기기 = 그래프 노드 생성 과정
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] == 1 && !visit[r][c]) {
						++islandNum;
						bfs(new Node(r, c));
					}
				}
			}

			// 다리 후보 모으기 = 그래프 간선 생성 과정
			visit = new boolean[N][M];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] != 0) {
						exhaustiveSearch(new Node(r, c), map[r][c]);
					}
				}
			}

			// 다리 후보 중 mst 가중치 합산
			kruskal();

			bw.write(String.valueOf(result));
			bw.flush();
		}
	}
}