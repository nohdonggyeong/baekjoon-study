package 알고리즘.시뮬레이션.boj_4991_로봇_청소기;

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
	static int w, h;
	static char[][] map;
	static List<Node> candidates;
	static Node cleaner;
	
	static class Node {
		private int r;
		private int c;
		private int steps;
		
		public Node(int r, int c, int steps) {
			this.r = r;
			this.c = c;
			this.steps = steps;
		}
	}
	
	static int n;
	static int[] input, temp;
	static boolean[] visited;
	static List<int[]> output;
	
	static void permutation(int depth) {
		if (depth == n) {
			output.add(temp.clone());
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				temp[depth] = input[i];
				permutation(depth + 1);
				visited[i] = false;
			}
		}
	}
	
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	static int bfs(Node start, Node end) {
		Queue<Node> queue = new LinkedList<Main.Node>();
		queue.offer(start);
		
		boolean[][] visited = new boolean[h][w];
		visited[start.r][start.c] = true;
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			if (node.r == end.r && node.c == end.c) {
				return node.steps;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = node.r + dr[i];
				int nc = node.c + dc[i];
				
				if (nr < 0 || nc < 0 || nr >= h || nc >= w) {
					continue;
				}
				if (map[nr][nc] == 'x' || visited[nr][nc]) {
					continue;
				}
				
				queue.offer(new Node(nr, nc, node.steps + 1));
				visited[nr][nc] = true;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 1. while로 입력 계속 받고 "0 0" 들어오면 break
		while (true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			if (w == 0 && h == 0) {
				break;
			}
			map = new char[h][w];
			candidates = new ArrayList<Main.Node>();
			for (int r = 0; r < h; r++) {
				String str = br.readLine();
				for (int c = 0; c < w; c++) {
					map[r][c] = str.charAt(c);
					if (map[r][c] == '*') {
						// 2. 먼지 위치를 list에 담기
						candidates.add(new Node(r, c, 0));
					} else if (map[r][c] == 'o') {
						cleaner = new Node(r, c, 0);
					}
				}
			}
			
			// 순열로 먼지 순서 case 가져오기
			n = candidates.size();
			input = new int[n];
			for (int i = 0; i < n; i++) {
				input[i] = i;
			}
			temp = new int[n];
			visited = new boolean[n];
			output = new ArrayList<>();
			permutation(0);
			
			// case를 for문으로 돌면서 bfs로 최단 거리 구하기
			int minDistance = Integer.MAX_VALUE;
			loop:
			for (int[] el : output) {
				int sumDistance = 0;
				
				// 출발지
				Node start = cleaner;
				// 목적지
				Node end = candidates.get(el[0]);
				
				int distance = 0;
				distance = bfs(start, end);
				// 목적지 도달 못한 경우를 체크한다.
				if (distance == 0) {
					minDistance = -1;
					break loop;
				}
				sumDistance += distance;
				
				for (int i = 0; i < el.length - 1; i++) {
					// 출발지
					start = candidates.get(el[i]);
					// 목적지
					end = candidates.get(el[i + 1]);

					distance = 0;
					distance = bfs(start, end);
					// 목적지 도달 못한 경우를 체크한다.
					if (distance == 0) {
						minDistance = -1;
						break loop;
					}
					sumDistance += distance;
				}
				minDistance = Math.min(minDistance, sumDistance);
			}
			// minResult로 최단거리 갱신
			sb.append(minDistance).append("\n");
		}
		
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}

}
