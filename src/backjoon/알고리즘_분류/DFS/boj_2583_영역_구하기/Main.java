package backjoon.알고리즘_분류.DFS.boj_2583_영역_구하기;

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
	static int M, N, K, cnt, num;
	static List<Integer> areaList;
	static boolean[][] map;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};

	static void dfs(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nc < 0 || nr >= M || nc >= N) {
				continue;
			}
			if (map[nr][nc]) {
				continue;
			}

			cnt += 1;
			map[nr][nc] = true;
			dfs(nr, nc);
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new boolean[M][N];
		
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			for (int i = M - y2; i < M - y1; i++) {
				for (int j = x1; j < x2; j++) {
					map[i][j]= true;
				}
			}
		}
		
//		System.out.println();
//		for (int i = 0; i < M; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		num = 0;
		areaList = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!map[i][j]) {
					num++;
					cnt = 1;
					map[i][j] = true;
					dfs(i, j);
					areaList.add(cnt);
				}
			}
		}
		
		Collections.sort(areaList);
		
		sb.append(String.valueOf(num));
		sb.append("\n");
		for (int cnt : areaList) {
			sb.append(String.valueOf(cnt));
			sb.append(" ");
		}
		
		bw.write(sb.toString().trim());
		
		bw.flush();
		bw.close();
		br.close();
	}
}
