package backjoon.유형별로_풀어보기.DFS.boj_11559_Puyo_Puyo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static char[][] map;
	static boolean[][] visited;
	static int count, depth;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static boolean counted;
	
	// 중력으로 아래로 전체 내리는 함수
	static void downMap() {
		for (int c = 0; c < 6; c++) {
			for (int r = 11; r >= 1; r--) {
				int count12 = 0;
				while (map[r][c] == '.' && count12 < 12) {
					for (int i = r - 1; i >= 0; i--) {
						map[i + 1][c] = map[i][c];
					}
					map[0][c] = '.';
					count12 += 1;
				}
			}
		}
	}
	
	// 4개가 겹쳤는지 boolean return 함수
	static void checkPossible(char ch, int r, int c) {
		if (depth >= 4) {
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nc < 0 || nr >= 12 || nc >= 6) {
				continue;
			}
			if (map[nr][nc] != ch || visited[nr][nc]) {
				continue;
			}
			
			depth += 1;
			visited[nr][nc] = true;
			checkPossible(ch, nr, nc);
		}
	}
	
	// 4개 겹쳤다고 판단돼서 4개 이상을 .으로 바꾸는 함수
	static void changeToDot(char ch, int r, int c) {
		map[r][c] = '.';
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nc < 0 || nr >= 12 || nc >= 6) {
				continue;
			}
			if (map[nr][nc] != ch || visited[nr][nc]) {
				continue;
			}
			
			visited[nr][nc] = true;
			changeToDot(ch, nr, nc);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		map = new char[12][7];
		for (int r = 0; r < 12; r++) {
			String str = br.readLine();
			for (int c = 0; c < 6; c++) {
				map[r][c] = str.charAt(c);
			}
		}
		
		count = 0;
		// while true;
		while (true) {
			counted = false;
			
			// for row: 맨 아래줄부 위로 올라가며 4개 이상 이어졌나 boolean 확인하고 그렇다면 4개 이상을 .으로 바꾸기
			for (int r = 11; r >= 0; r--) {
				for (int c = 0; c < 6; c++) {
					if (map[r][c] != '.') {
						depth = 1;
						visited = new boolean[12][6];
						visited[r][c] = true;
						checkPossible(map[r][c], r, c);
						
						if (depth >= 4) {
							counted = true;
							visited = new boolean[12][6];
							visited[r][c] = true;
							changeToDot(map[r][c], r, c);
						}
					}
				}
			}
			
			// 12줄에서  .으로 바꾸는 작업 없었다면 while break;
			if (!counted) {
				break;
			}
		
			// count += 1;
			count += 1;
			// for row 끝났으면 중력으로 아래로 전체 내리는 함수
			downMap();
			
//			for (int r = 0; r < 12; r++) {
//				for (int c = 0; c < 6; c++) {
//					System.out.print(map[r][c] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
		}
		
		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
		br.close();
	}

}
