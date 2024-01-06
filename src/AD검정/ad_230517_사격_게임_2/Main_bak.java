package AD검정.ad_230517_사격_게임_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_bak {
	static int T, R, C, answer;
	static int[][] map;
	static int[][] visit;
	
	static int combN, combR;
	static List<int[]> input;
	static int[] inputNumber;
	static int[] temp;
	static List<int[]> outputNumber;

	static void shootRight(int r, int c, boolean clear) {
		if (!clear) {
			visit[r][c] += 1;
			int nc = c + 1;
			if (nc < C) {
				shootRight(r, nc, false);
			}
		} else {
			visit[r][c] -= 1;
			int nc = c + 1;
			if (nc < C) {
				shootRight(r, nc, true);
			}
		}
	}
	
	static void shootRightUp(int r, int c, boolean clear) {
		if (!clear) {
			visit[r][c] += 1;
			int nr = r - 1;
			int nc = c + 1;
			if (nr >= 0 && nc < C) {
				shootRightUp(nr, nc, false);
			}
		} else {
			visit[r][c] -= 1;
			int nr = r - 1;
			int nc = c + 1;
			if (nr >= 0 && nc < C) {
				shootRightUp(nr, nc, true);
			}
		}
	}
	
	static void shootRightDown(int r, int c, boolean clear) {
		if (!clear) {
			visit[r][c] += 1;
			int nr = r + 1;
			int nc = c + 1;
			if (nr < R && nc < C) {
				shootRightDown(nr, nc, false);
			}
		} else {
			visit[r][c] -= 1;
			int nr = r + 1;
			int nc = c + 1;
			if (nr < R && nc < C) {
				shootRightDown(nr, nc, true);
			}
		}
	}

	static void shootDown(int r, int c, boolean clear) {
		if (!clear) {
			visit[r][c] += 1;
			int nr = r + 1;
			if (nr < R) {
				shootDown(nr, c, false);
			}
		} else {
			visit[r][c] -= 1;
			int nr = r + 1;
			if (nr < R) {
				shootDown(nr, c, true);
			}
		}
	}
	
	static void shootDownLeft(int r, int c, boolean clear) {
		if (!clear) {
			visit[r][c] += 1;
			int nr = r + 1;
			int nc = c - 1;
			if (nr < R && nc >= 0) {
				shootDownLeft(nr, nc, false);
			}
		} else {
			visit[r][c] -= 1;
			int nr = r + 1;
			int nc = c - 1;
			if (nr < R && nc >= 0) {
				shootDownLeft(nr, nc, true);
			}
		}
	}
	
	static int getSum() {
		int sum = 0;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (visit[r][c] > 0) {
					sum += map[r][c];
				}
			}
		}
		return sum;
	}
	
	static void combinationRepetition(int start, int depth) {
		if (depth == combR) {
			outputNumber.add(temp.clone());
			return;
		}
		
		for (int i = start; i < combN; i++) {
			temp[depth] = inputNumber[i];
			combinationRepetition(i, depth + 1);
		}
	}
	
	static void dfs(int depth, int[] el, int t) {
		if (depth == combR) {
			int s = getSum();
			if (s == 127) {
				System.out.println();
				for (int e : el) {
					for (int i = 0; i < 2; i++) {
						System.out.print(String.valueOf(input.get(e)[i]) + " ");
					}
					System.out.println();
				}
				
				for (int r = 0; r < R; r++) {
					for (int c = 0; c < C; c++) {
						System.out.print(visit[r][c] > 0 ?  "O " : "X ");
					}
					System.out.println();
				}				
			}
			
			answer = Math.max(answer, getSum());
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			int[] shotPlace = input.get(el[depth]);
			if (shotPlace[0] == 0 && shotPlace[1] != 0) {
				switch(i) {
				case 0:
					shootDown(0, shotPlace[1], false);
					dfs(depth + 1, el, t);
					shootDown(0, shotPlace[1], true);
					break;
				case 1:
					shootDownLeft(0, shotPlace[1], false);
					dfs(depth + 1, el, t);
					shootDownLeft(0, shotPlace[1], true);
					break;
				case 2:
					shootRightDown(0, shotPlace[1], false);
					dfs(depth + 1, el, t);
					shootRightDown(0, shotPlace[1], true);
					break;
				default:
					break;
				}
			} else if (shotPlace[0] != 0 && shotPlace[1] == 0) {
				switch(i) {
				case 0:
					shootRight(shotPlace[0], 0, false);
					dfs(depth + 1, el, t);
					shootRight(shotPlace[0], 0, true);
					break;
				case 1:
					shootRightUp(shotPlace[0], 0, false);
					dfs(depth + 1, el, t);
					shootRightUp(shotPlace[0], 0, true);
					break;
				case 2:
					shootRightDown(shotPlace[0], 0, false);
					dfs(depth + 1, el, t);
					shootRightDown(shotPlace[0], 0, true);
					break;
				default:
					break;
				}
			} else if (shotPlace[0] == 0 && shotPlace[1] == 0) {
				switch(i) {
				case 0:
					shootRight(0, 0, false);
					dfs(depth + 1, el, t);
					shootRight(0, 0, true);
					break;
				case 1:
					shootDown(0, 0, false);
					dfs(depth + 1, el, t);
					shootDown(0, 0, true);
					break;
				case 2:
					shootRightDown(0, 0, false);
					dfs(depth + 1, el, t);
					shootRightDown(0, 0, true);
					break;
				default:
					break;
				}
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
//		LocalDateTime start = LocalDateTime.now();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[R][C];
			
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			visit = new int[R][C];
			
			input = new ArrayList<>();
			for (int r = 0; r < R; r++) {
				input.add(new int[] {r, 0});
			}
			for (int c = 0; c < C; c++) {
				input.add(new int[] {0, c});
			}
			
			combN = input.size();
			combR = 3;
			
			inputNumber = new int[combN];
			for (int i = 0; i < combN; i++) {
				inputNumber[i] = i;
			}
			
			temp = new int[combR];
			outputNumber = new ArrayList<>();
			combinationRepetition(0, 0);
	
			answer = 0;
			for (int[] el : outputNumber) {
				dfs(0, el, t);
			}
			
			sb.append("#").append(String.valueOf(t + 1)).append(" ").append(String.valueOf(answer)).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		
//		LocalDateTime end = LocalDateTime.now();
//		System.out.println();
//		System.out.println("[Elapsed time: " + Duration.between(start, end).getSeconds() + " sec]");
		
		bw.close();
		br.close();
	}
}
