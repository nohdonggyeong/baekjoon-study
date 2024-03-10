package backjoon.순열.boj_14502_연구소;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node {
	int r;
	int c;
	
	Node(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	static int N, M;
	static int[][] map;
	static List<Node> virusList;
	static boolean[][] visited;
	
	static List<Node> emptyRoomList;
	static int[] input, temp;
	static boolean[] checked;
	static List<int[]> output;
	
	static int result;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	static void dfs(int r, int c) {
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
				continue;
			}
			if (visited[nr][nc] || map[nr][nc] != 0) {
				continue;
			}
			
			visited[nr][nc] = true;
			dfs(nr, nc);
		}
	}
	
	static void combination(int start, int depth) {
		if (depth == 3) {
			temp = new int[3];
			int index = 0;
			for (int i = 0; i < input.length; i++) {
				if (checked[i]) {
					temp[index++] = input[i];
				}
			}
			output.add(temp.clone());
			return;
		}
		
		for (int i = start; i < emptyRoomList.size(); i++) {
			if (!checked[i]) {
				checked[i] = true;
				combination(i + 1, depth + 1);
				checked[i] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			virusList = new ArrayList<Node>();
			emptyRoomList = new ArrayList<Node>();
			visited = new boolean[N][M];
			for (int n = 0; n < N; n++) {
				st =  new StringTokenizer(br.readLine());
				for (int m = 0; m < M; m++) {
					map[n][m] = Integer.parseInt(st.nextToken());
					if (map[n][m] == 2) {
						virusList.add(new Node(n, m));
						visited[n][m] = true;
					} else if (map[n][m] == 1) {
						visited[n][m] = true;
					} else if (map[n][m] == 0) {
						emptyRoomList.add(new Node(n, m));
					}
				}
			}
			
			input = new int[emptyRoomList.size()];
			for (int i = 0; i < input.length; i++) {
				input[i] = i;
			}
			
			checked = new boolean[input.length];
			output = new ArrayList<int[]>();
			combination(0, 0);
			
			int[][] mapBak = new int[N][M];
			boolean[][] visitedBak = new boolean[N][M];
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					mapBak[n][m] = map[n][m];
					visitedBak[n][m] = visited[n][m];
				}
			}
			
			result = 0;
			for (int[] el : output) {
				for (int n = 0; n < N; n++) {
					for (int m = 0; m < M; m++) {
						map[n][m] = mapBak[n][m];
						visited[n][m] = visitedBak[n][m];
					}
				}
				
				for (int e : el) {
					Node node = emptyRoomList.get(e);
					map[node.r][node.c] = 1;
					visited[node.r][node.c] = true;
				}
				
				for (Node node : virusList) {
					dfs(node.r, node.c);
				}
				
				int count = 0;
				for (int n = 0; n < N; n++) {
					for (int m = 0; m < M; m++) {
						if (map[n][m] == 0 && !visited[n][m]) {
							count += 1;
						}
					}
				}
				
				result = Math.max(result, count);
			}
			
			bw.write(String.valueOf(result));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
