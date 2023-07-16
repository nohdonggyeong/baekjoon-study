package 삼성SW역량테스트.ad_230517_사격_게임_2;

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

public class Main {
	static int T, R, C;
	static int[][] map;
	static int[][] visit;
	static int combN, combR;
	static List<int[]> inputCandidateList;
	static int[] inputCandidateIndex;
	static int[] inputIndexTemp;
	static List<int[]> inputIndexList;
	static int answer;
	
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
			inputIndexList.add(inputIndexTemp.clone());
			return;
		}
		
		for (int i = start; i < combN; i++) {
			inputIndexTemp[depth] = inputCandidateIndex[i];
			combinationRepetition(i, depth + 1);
		}
	}
	
	static void dfs(int depth, int[] inputIndex) {
		if (depth == combR) {
//			if (getSum() > answer) {
//				System.out.println();
//				for (int r = 0; r < R; r++) {
//					for (int c = 0; c < C; c++) {
//						System.out.print(visit[r][c] > 0 ? "O " : "X ");
//					}
//					System.out.println();
//				}
//			}
			
			answer = Math.max(answer, getSum());
			return;
		}
		
		for (int i = 0; i < combR; i ++) {
			int[] input = inputCandidateList.get(inputIndex[depth]);
			if (input[0] == 0 && input[1] != 0) {
				switch(i) {
				case 0:
					shootDown(0, input[1], false);
					dfs(depth + 1, inputIndex);
					shootDown(0, input[1], true);
					break;
				case 1:
					shootDownLeft(0, input[1], false);
					dfs(depth + 1, inputIndex);
					shootDownLeft(0, input[1], true);
					break;
				case 2:
					shootRightDown(0,  input[1], false);
					dfs(depth + 1, inputIndex);
					shootRightDown(0, input[1], true);
					break;
				default:
					break;
				}
			} else if (input[0] != 0 && input[1] == 0) {
				switch(i) {
				case 0:
					shootRight(input[0], 0, false);
					dfs(depth + 1, inputIndex);
					shootRight(input[0], 0, true);
					break;
				case 1:
					shootRightUp(input[0], 0, false);
					dfs(depth + 1, inputIndex);
					shootRightUp(input[0], 0, true);
					break;
				case 2:
					shootRightDown(input[0], 0, false);
					dfs(depth + 1, inputIndex);
					shootRightDown(input[0], 0, true);
					break;
				default:
					break;
				}
			} else if (input[0] == 0 && input[0] == 0) {
				switch(i) {
				case 0:
					shootRight(0, 0, false);
					dfs(depth + 1, inputIndex);
					shootRight(0, 0, true);
					break;
				case 1:
					shootDown(0, 0, false);
					dfs(depth + 1, inputIndex);
					shootDown(0, 0, true);
					break;
				case 2:
					shootRightDown(0, 0, false);
					dfs(depth + 1, inputIndex);
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
		System.setIn(new FileInputStream("src/삼성SW역량테스트/ad_230517_사격_게임_2/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// test case
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			// setting: map, visit
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[R][C];
			visit = new int[R][C];
			
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c = 0; c < C; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// setting: input list
			inputCandidateList = new ArrayList<>();
			for (int r = 0; r < R; r++) {
				inputCandidateList.add(new int[] {r, 0});
			}
			for (int c = 0; c < C; c++) {
				inputCandidateList.add(new int[] {0, c});
			}
			
			combN = inputCandidateList.size();
			combR = 3;
			inputCandidateIndex = new int[combN];
			for (int i = 0; i < combN; i++) {
				inputCandidateIndex[i] = i;
			}
			
			inputIndexTemp = new int[3];
			inputIndexList = new ArrayList<>();
			combinationRepetition(0, 0);
			
			// operation: dfs
			answer = 0;
			for (int i = 0; i < inputIndexList.size(); i++) {
				dfs(0, inputIndexList.get(i));
			}
			
			// print: answer
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
