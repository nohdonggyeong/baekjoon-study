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
	static Node[] nodeArr;
	
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
	
	static int[][] distArr;
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
				if (visited[nr][nc] || map[nr][nc] == 'x') {
					continue;
				}
				
				queue.offer(new Node(nr, nc, node.steps + 1));
				visited[nr][nc] = true;
			}
		}
		
		return -1;
	}
	
	static int n, r;
	static int[] input, temp;
	static boolean[] visit;
	static List<int[]> output;
	
	static void permutation(int depth) {
		if (depth == r) {
			output.add(temp.clone());
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (!visit[i]) {
				visit[i] = true;
				temp[depth] = input[i];
				permutation(depth + 1);
				visit[i] = false;
			}
		}
	}
	
	static int minDist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if (w == 0 && h == 0) {
				break;
			}
			
			map = new char[h][w];
			nodeArr = new Node[11];
			int len = 1;
			
			for (int i = 0; i < h; i++) {
				String str = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = str.charAt(j);
					if (map[i][j] == '*') {
						nodeArr[len++] = new Node(i, j, 0);
					} else if (map[i][j] == 'o') {
						nodeArr[0] = new Node(i, j, 0);
					}
				}
			}
			
			// 청소기와 먼지들의 모든 거리를 담을 배열
			distArr = new int[len][len];
			
			// 청소기와 먼지들의 모든 거리를 bfs로 구하고 배열 담기
			boolean isPossible = true;
			loop:
			for (int i = 0; i < len - 1; i++) {
				for (int j = i + 1; j < len; j++) {
					Node start = nodeArr[i];
					Node end = nodeArr[j];
					distArr[i][j] = bfs(start, end);
					
					if (distArr[i][j] == -1) {
						isPossible = false;
						break loop;
					}
					
					distArr[j][i] = distArr[i][j];
				}
			}
			
			if (!isPossible) {
				sb.append("-1").append("\n");
				continue;
			}
			
//			System.out.println();
//			for (int i = 0; i < len; i++) {
//				for (int j = 0; j < len; j++) {
//					System.out.print(distArr[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			
			// 청소기인 0부터 시작해서 모든 먼지 순열 돌며 최소 거리 합계 비교
			n = len - 1;
			r = len - 1;
			
			input = new int[n];
			for (int i = 0; i < n; i++) {
				input[i] = i + 1;
			}
			
			temp = new int[r];
			visit = new boolean[n];
			output = new ArrayList<int[]>();
			permutation(0);
			
			minDist = Integer.MAX_VALUE;
			for (int[] el : output) {
				int sum = 0;
				sum += distArr[0][el[0]];
				
				for (int i = 1; i < el.length; i++) {
					sum += distArr[el[i - 1]][el[i]];
				}
				
				minDist = Math.min(minDist, sum);
			}
			
			sb.append(minDist).append("\n");
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}
}
