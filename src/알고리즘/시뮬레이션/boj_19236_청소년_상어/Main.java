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
		private int sum;
		
		public Fish(int r, int c, int dir, int sum) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.sum = sum;
		}
		
		public void updateLoc(int r, int c) {
			this.r = r;
			this.c = c;
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
				fishes[num] = new Fish(r, c, dir, 0);
			}
		}
		
		// 청소년 상어는 (0, 0)에 있는 물고기를 먹고, (0, 0)에 들어가게 된다. 상어의 방향은 (0, 0)에 있던 물고기의 방향과 같다.
		Fish shark = new Fish(0, 0, fishes[map[0][0]].dir, map[0][0]);
		fishes[map[0][0]] = null;
		map[0][0] = 0;
		
		maxResult = 0;
		dfs(map, fishes, shark);
		
		bw.write(String.valueOf(maxResult));
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void dfs(int[][] map, Fish[] fishes, Fish shark) {
		// 물고기는 번호가 작은 물고기부터 순서대로 이동한다.
		for (int i = 1; i <= 16; i++) {
			Fish fish = fishes[i];
			if (fish == null) {
				continue;
			}
			int r = fish.r;
			int c = fish.c;
			
			for (int j = 1; j <= 8; j++) {
				int nr = r + dr[fish.dir];
				int nc = c + dc[fish.dir];
				
				if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4 || (nr == shark.r && nc == shark.c)) {
					fishes[i].dir += 1;
					if (fishes[i].dir > 8) {
						fishes[i].dir = 1;
					}
				} else {
					// 물고기 자리바꿈
					if (map[nr][nc] != 0 && fishes[map[nr][nc]] != null) {
						fishes[map[nr][nc]].updateLoc(r, c);
					}
					fishes[i].updateLoc(nr, nc);
					
					int temp = map[r][c];
					map[r][c] = map[nr][nc];
					map[nr][nc] = temp;
					break;
				}
			}
			
//			print(map, fishes, i);
		}
		
		// 물고기의 이동이 모두 끝나면 상어가 이동한다. -> 물고기 이동
		for (int i = 1; i <= 3; i++) {
			int nr = shark.r + dr[shark.dir] * i;
			int nc = shark.c + dc[shark.dir] * i;
			
			if (nr < 0 || nc < 0 || nr >= 4 || nc >= 4) {
				break;
			}
			
			if (map[nr][nc] != 0 && fishes[map[nr][nc]] != null) {
				// 복제하면 dfs 재귀 돌고 난 후 값을 복원할 필요 없게 됨
				int nDir = fishes[map[nr][nc]].dir;
				int nSum = shark.sum + map[nr][nc];
				
				int[][] mapCopy = new int[4][4];
				for (int r = 0; r < 4; r++) {
					for (int c = 0; c < 4; c++) {
						mapCopy[r][c] = map[r][c];
					}
					
				}
				
				Fish[] fishesCopy = new Fish[17];
				for (int r = 0; r < 17; r++) {
					fishesCopy[r] = fishes[r] == null ? null : new Fish(fishes[r].r, fishes[r].c, fishes[r].dir, 0);
				}
				Fish sharkCopy = new Fish(nr, nc, nDir, nSum);
				
				fishesCopy[mapCopy[nr][nc]] = null;
				mapCopy[nr][nc] = 0;

				maxResult = Math.max(maxResult, sharkCopy.sum);
				
				dfs(mapCopy, fishesCopy, sharkCopy);
			}
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
			if (fishes[r] != null) {
				System.out.print(r + "번: " + fishes[r].dir + ", ");
			} else {
				System.out.print(r + "번: " + "X, ");
			}
		}
		System.out.println();
		System.out.println(maxResult);
	}
}