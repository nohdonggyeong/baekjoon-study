package 알고리즘.시뮬레이션.boj_19236_청소년_상어;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static class Fish {
		private int r;
		private int c;
		private int dir;
		private boolean hasEaten;
		
		public Fish(int r, int c, int dir, boolean hasEaten) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.hasEaten = hasEaten;
		}
	}
	
	static int[] dr = {0, -1, -1, 0, 1, 1, 1, 0, -1}; // 0(X), 1 ~ 8
	static int[] dc = {0, 0, -1, -1, -1, 0, 1, 1, 1}; // 0(X), 1 ~ 8
	static int maxResult;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
	
		int[][] map = new int[4][4];
		Fish[] fishes = new Fish[17]; // 0(X), 1 ~ 16
		
		// map, fishes에 입력 물고기 정보 담기
		for (int r = 0; r < 4; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 4; c++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				
				map[r][c] = num;
				fishes[num] = new Fish(r, c, dir, false);
			}
		}
		
		// 청소년 상어는 (0, 0)에 있는 물고기를 먹고, (0, 0)에 들어가게 된다. 상어의 방향은 (0, 0)에 있던 물고기의 방향과 같다.
		int sharkR = 0;
		int sharkC = 0;
		int sharkDir = fishes[map[0][0]].dir;
		int sharkSum = map[0][0];
		fishes[map[0][0]].hasEaten = true;
		map[0][0] = 0;
		
		maxResult = 0;
		dfs(map, fishes, sharkR, sharkC, sharkDir, sharkSum);
		
		bw.write(String.valueOf(maxResult));
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void dfs(int[][] map, Fish[] fishes, int sharkR, int sharkC, int sharkDir, int sharkSum) {
		// 물고기는 번호가 작은 물고기부터 순서대로 이동한다.
		moveFishes(map, fishes, sharkR, sharkC);
		
		// 물고기의 이동이 모두 끝나면 상어가 이동한다. -> 물고기 이동
		for (int i = 1; i <= 3; i++) {
			int nr = sharkR + dr[sharkDir] * i;
			int nc = sharkC + dc[sharkDir] * i;
			
			if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4) {
				break;
			}
			
			if (map[nr][nc] != 0) {
				int bak = map[nr][nc];
				int newSharkR = nr;
				int newSharkC = nc;
				int newSharkDir = fishes[map[nr][nc]].dir;
				int newSharkSum = sharkSum + map[nr][nc];
				
				fishes[map[nr][nc]].hasEaten = true;
				map[nr][nc] = 0;

				maxResult = Math.max(maxResult, sharkSum);
			
				int[][] mapCopy = new int[4][4];
				for (int r = 0; r < 4; r++) {
					mapCopy[r] = map[r].clone();
				}
				Fish[] fishesCopy = fishes.clone();
				dfs(mapCopy, fishesCopy, newSharkR, newSharkC, newSharkDir, newSharkSum);
				
				map[nr][nc] = bak;
				fishes[map[nr][nc]].hasEaten = false;
			}
		}
	}
	
	static void moveFishes(int[][] map, Fish[] fishes, int sharkR, int sharkC) {
		for (int i = 1; i <= 16; i++) {
			Fish fish = fishes[i];
			if (fish.hasEaten) {
				continue;
			}
			
			for (int j = 1; j <= 8; j++) {
				int nr = fish.r + dr[fish.dir];
				int nc = fish.c + dc[fish.dir];
				
				if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4 || (nr == sharkR && nc == sharkC)) {
					fishes[i].dir += 1;
					if (fishes[i].dir > 8) {
						fishes[i].dir = 1;
					}
				} else {
					// 물고기 교체
					fishes[map[nr][nc]] = new Fish(fish.r, fish.c, map[nr][nc] == 0 ? 0 : fishes[map[nr][nc]].dir, map[nr][nc] == 0 ? true : fishes[map[nr][nc]].hasEaten);
					fishes[i] = new Fish(nr, nc, fishes[i].dir, fishes[i].hasEaten);
					
					map[fish.r][fish.c] = map[nr][nc];
					map[nr][nc]= i;
					break;
				}
			}
			
			print(map, fishes, i);
		}
	}
	
	static void print(int[][] map, Fish[] fishes, int i) {
		System.out.println();
		System.out.println(i + "번 완료");
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				System.out.print(map[r][c] + " ");
			}
			System.out.println();
		}
		for (int r = 1; r <= 16; r++) {
			System.out.print(r + "번: " + fishes[r].dir + ", ");
		}
		System.out.println();
	}
}