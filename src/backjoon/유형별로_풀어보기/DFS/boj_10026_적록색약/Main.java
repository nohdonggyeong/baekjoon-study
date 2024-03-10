package backjoon.유형별로_풀어보기.DFS.boj_10026_적록색약;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int N;
	static char[][] map;
	static char[][] redGreenMap;
	static boolean[][] visited;
	static boolean[][] redGreenvisited;
	static int count;
	static int redGreenCount;
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static void dfs(int i, int j, char[][] checkMap, boolean[][] checkVisited) {
		for (int d = 0; d < 4; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if (ni < N && ni >= 0 && nj < N && nj >= 0 && checkMap[i][j] == checkMap[ni][nj] && !checkVisited[ni][nj]) {
				checkVisited[ni][nj] = true;
				dfs(ni, nj, checkMap, checkVisited);
			}
		}
	}
	
	public static void main (String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		redGreenMap = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
			for (int j = 0; j < N; j++) {
				char c = str.charAt(j);
				// 일반사람 map
				map[i][j] = c;
				// 적록색약 map
				if (c == 'G') {
					redGreenMap[i][j] = 'R';
				} else {
					redGreenMap[i][j] = c;
				}
			}
		}
//		printMap(map);
//		printMap(redGreenMap);
		
		count = 0;
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					count += 1;
					dfs(i, j, map, visited);
				}
			}
		}

		redGreenCount = 0;
		redGreenvisited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!redGreenvisited[i][j]) {
					redGreenCount += 1;
					dfs(i, j, redGreenMap, redGreenvisited);
				}
			}
		}
		
		sb.append(count).append(" ").append(redGreenCount);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void printMap(char[][] printMap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(printMap[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void printVisited(boolean[][] printVisited) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(printVisited[i][j] + " ");
			}
			System.out.println();
		}
	}
}
