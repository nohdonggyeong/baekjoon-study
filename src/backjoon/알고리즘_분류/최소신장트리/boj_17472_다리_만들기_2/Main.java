package backjoon.알고리즘_분류.최소신장트리.boj_17472_다리_만들기_2;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] visit;
	static int islandNum = 0;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static PriorityQueue<Edge> pq = new PriorityQueue<>();
	static int[] parent;
	static int bridgeCnt = 0;
	
	static int result = 0;

	// 섬 넘버링
	static void numberIslands(int r, int c) {
	    if (r < 0 || r >= N || c < 0 || c >= M
	    		|| map[r][c] != 1 || visit[r][c]) {
	        return;
	    }

	    visit[r][c] = true;
	    map[r][c] = islandNum;

	    int nr, nc;
	    for (int d = 0; d < 4; d++) {
	        nr = r + dr[d];
	        nc = c + dc[d];
	        numberIslands(nr, nc);
	    }
	}

	// 가능한 모든 다리 찾기
	static void collectBridgeCandidates(int r, int c, int d, int length, int from) {
	    int nr = r + dr[d];
	    int nc = c + dc[d];

	    // 범위를 벗어나거나 자신의 섬으로 돌아온 경우
	    if (nr < 0 || nr >= N || nc < 0 || nc >= M
	    		|| map[nr][nc] == from) {
	        return;
	    }

	    // 다른 섬에 도달한 경우
	    if (map[nr][nc] != 0) {
	        if (length > 1) {
	        	// 다리 길이가 2 이상인 경우에만 후보로 추가
	            pq.add(new Edge(from, map[nr][nc], length));
	        }
	        return;
	    }

	    // 계속해서 같은 방향으로 탐색
	    collectBridgeCandidates(nr, nc, d, length + 1, from);
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
	
	// 모든 섬을 연결하는 다리 길이의 최솟값 구하기
	static void findMinimumBridgeLength() {
		parent = new int[islandNum + 1];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}

		int a, b;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			a = find(edge.from);
			b = find(edge.to);

			if (a == b) {
				continue;
			}

			union(edge.from, edge.to);

			result += edge.weight;
			++bridgeCnt;
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

			// 섬 넘버링 = 그래프 노드 생성 과정
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] == 1 && !visit[r][c]) {
						++islandNum;
						numberIslands(r, c);
					}
				}
			}

			// 가능한 모든 다리 찾기 = 그래프 간선 생성 과정
			visit = new boolean[N][M];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (map[r][c] != 0) {
					    for (int d = 0; d < 4; d++) { // 상하좌우 중에서 한 방향으로 탐색
					        collectBridgeCandidates(r, c, d, 0, map[r][c]);
					    }
					}
				}
			}

			// 모든 섬을 연결하는 다리 길이의 최솟값 구하기 = 모든 정점을 연결하는 간선 가중치의 최소 합 구하는 과정
			findMinimumBridgeLength();
			if (result == 0 || bridgeCnt != islandNum - 1) {
				result = -1;
			}

			bw.write(String.valueOf(result));
			bw.flush();
		}
	}
}