package backjoon.알고리즘_분류.최소신장트리.boj_17472_다리_만들기_2;

import java.io.*;
import java.util.*;

public class Main {

	static class Node {
		int r, c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

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

	static int N, M;
	static int[][] map;
	static boolean[][] visit;
	
	static int islandNum = 0;

	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};

	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static int[] parent;
	static int bridgeCnt = 0;
	static int result = 0;

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

			// 그래프 노드 번호 부여
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] == 1 && !visit[r][c]) {
						++islandNum;
						numberIsland(new Node(r, c));
					}
				}
			}
	        
			// 그래프 간선 만들기
			visit = new boolean[N][M];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] != 0) {
						makeBridge(new Node(r, c), map[r][c]);
					}
				}
			}

			// 크루스칼 알고리즘
			parent = new int[islandNum + 1];
			for (int i = 0; i < parent.length; i++) {
				parent[i] = i;
			}

			while (!pq.isEmpty()) {
				Edge edge = pq.poll();

				int a = find(edge.u);
				int b = find(edge.v);

				if (a == b)
					continue;

				union(edge.u, edge.v);
				result += edge.w;
				bridgeCnt++;
			}

			if (result == 0 || bridgeCnt != islandNum - 1) {
				result = -1;
			}
			
			bw.write(String.valueOf(result));
			bw.flush();
		}
	}

	public static int find(int a) {
		if (a == parent[a])
			return a;

		return parent[a] = find(parent[a]);
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a != b) {
			parent[a] = b;
		} else {
			return;
		}
	}

	public static void numberIsland(Node node) { // bfs
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(node);
		visit[node.r][node.c] = true;
		map[node.r][node.c] = islandNum;

		while (!queue.isEmpty()) {
			Node temp = queue.poll();
			int r = temp.r;
			int c = temp.c;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M
						&& map[nr][nc] == 1 && !visit[nr][nc]) {
					queue.add(new Node(nr, nc));
					visit[nr][nc] = true;
					map[nr][nc] = islandNum;
				}
			}
		}
	}

	// 상하좌우 중 한 방향으로 계속 이동, 다른 섬이 나올 때까지 반복
	public static void makeBridge(Node node, int num) {
		int cr = node.r;
		int cc = node.c;
		int length = 0;

		for (int d = 0; d < 4; d++) {
			while (true) {
				cr = cr + dr[d];
				cc = cc + dc[d];

				if (cr >= 0 && cr < N && cc >= 0 && cc < M) {
					if (map[cr][cc] == num) { // 자신과 같은 번호가 나오면 좌표와 length 초기화
						length = 0;
						cr = node.r;
						cc = node.c;
						break;
					} else if (map[cr][cc] == 0) {
						length++;
					} else { // 1보다 큰 경우 pq에 추가
						if (length > 1) {
							pq.add(new Edge(num, map[cr][cc], length));
						}
						length = 0;
						cr = node.r;
						cc = node.c;
						break;
					}
				} else {
					length = 0;
					cr = node.r;
					cc = node.c;
					break;
				}
			}
		}
	}
}