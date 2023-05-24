package algorithm.bfs.boj_2667_단지번호붙이기;

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

public class Main {
	private static int N;
	private static boolean[][] map;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	private static List<Integer> results;
	
	private static void solution() {
		Queue<Location> queue = new LinkedList<>();
		
		Location location = findStartLocation();
		while (location.x > -1) {
			queue.offer(location);
			int max = 1;
			while (!queue.isEmpty()) {
				location = queue.poll();
				
				for (int i = 0; i < 4; i++) {
					if (location.x + dx[i] >= 0
							&& location.y + dy[i] >= 0
							&& location.x + dx[i] < N
							&& location.y + dy[i] < N
							&& map[location.x + dx[i]][location.y + dy[i]]) {
						map[location.x + dx[i]][location.y + dy[i]] = false;
						queue.offer(new Location(location.x + dx[i], location.y + dy[i]));
						max++;
					}
				}
			}
			
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j] + "  ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			results.add(max);
			location = findStartLocation();
		}
		
	}

	private static Location findStartLocation() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j]) {
					map[i][j] = false;
					return new Location(i, j);
				}
			}
		}
		return new Location(-1, -1);
	}
	
	private static class Location {
		private int x;
		private int y;
		
		public Location() {
			this.x = 0;
			this.y = 0;
		}
		
		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String args[]) throws IOException {
		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String[] strArr = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				if("1".equals(strArr[j])) {
					map[i][j] = true;
				}
			}
		}
		
		// Process
		results = new ArrayList<>();
		solution();
		Collections.sort(results);
		
		// Output
		int cnt = results.size();
		bw.write(String.valueOf(cnt));
		bw.newLine();
		
		for (int i = 0; i < cnt; i++) {
			bw.write(String.valueOf(results.get(i)));
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}
}
