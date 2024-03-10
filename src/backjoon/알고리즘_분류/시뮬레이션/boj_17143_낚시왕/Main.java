package backjoon.알고리즘_분류.시뮬레이션.boj_17143_낚시왕;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int R, C, M;
	static Shark[][] sharkArr;
	static int[] dr= {0, -1, 1, 0, 0};
	static int[] dc= {0, 0, 0, 1, -1};
	static int resultCount;
	
	static class Shark {
		private int s;
		private int d;
		private int z;
		
		public Shark(int s, int d, int z) {
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	
	static void catchShark(int c) {
		for (int r = 1; r <= R; r++) {
			if (sharkArr[r][c] != null) {
				resultCount += sharkArr[r][c].z;
				sharkArr[r][c] = null;
				return;
			}
		}
	}
	
	static void moveSharks() {
		Shark[][] movedArr = new Shark[R + 1][C + 1];
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				if (sharkArr[r][c] != null) {
					int nr = r;
					int nc = c;
					int nd = sharkArr[r][c].d;
					for (int i = 0; i < sharkArr[r][c].s % (nd == 1 || nd == 2 ? (R - 1) * 2 : (C - 1) * 2); i++) {
						if (nr + dr[nd] < 1 || nc + dc[nd] < 1 || nr + dr[nd] > R || nc + dc[nd] > C) {
							if (nd == 1) {
								nd = 2;
							} else if (nd == 2) {
								nd = 1;
							} else if (nd == 3) {
								nd = 4;
							} else if (nd == 4) {
								nd = 3;
							}
						}
						nr += dr[nd];
						nc += dc[nd];
					}
					if (!(movedArr[nr][nc] != null && movedArr[nr][nc].z >= sharkArr[r][c].z)) {
						movedArr[nr][nc] = new Shark(sharkArr[r][c].s, nd, sharkArr[r][c].z);
					}
				}
			}
		}
		
		sharkArr = new Shark[R + 1][C + 1];
		for (int r = 1; r <= R; r++) {
			sharkArr[r] = movedArr[r].clone();
		}	
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sharkArr = new Shark[R + 1][C + 1];
		
		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			sharkArr[r][c] = new Shark(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		resultCount = 0;
		for (int c = 1; c <= C; c++) {
//			System.out.println();
//			System.out.println("--start-------------------------------------");
//			for (int i = 1; i <= R; i++) {
//				for (int j = 1; j <= C; j++) {
//					if (sharkArr[i][j] == null) {
//						System.out.print("null ");
//					} else {
//						System.out.print("[" + sharkArr[i][j].s + ", " + sharkArr[i][j].d + ", " + sharkArr[i][j].z + "] ");
//					}
//				}
//				System.out.println();
//			}
//			System.out.println("--start-------------------------------------");
			
			catchShark(c);
			moveSharks();

//			System.out.println();
//			for (int i = 1; i <= R; i++) {
//				for (int j = 1; j <= C; j++) {
//					if (sharkArr[i][j] == null) {
//						System.out.print("null ");
//					} else {
//						System.out.print("[" + sharkArr[i][j].s + ", " + sharkArr[i][j].d + ", " + sharkArr[i][j].z + "] ");
//					}
//				}
//				System.out.println();
//			}			
		}
		
		bw.write(String.valueOf(resultCount));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
