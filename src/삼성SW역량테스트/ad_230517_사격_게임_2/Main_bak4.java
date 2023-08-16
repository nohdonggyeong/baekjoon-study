package 삼성SW역량테스트.ad_230517_사격_게임_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_bak4 {
	static int T, H, W;
	static int[][] D;
	
	static List<int[]> positionList;
	static int n, r;
	static int[] input;
	static int[] temp;
	static List<int[]> output;
	
	static int[][] shotCount;
	static int gamePoint, maxPoint;
	
	static void combinationRepetition(int start, int depth) {
		if (depth == r) {
			output.add(temp.clone());
			return;
		}
		
		for (int i = start; i < n; i++) {
			temp[depth] = input[i];
			combinationRepetition(i, depth + 1);
		}
	}
	
	static void shoot(int depth, int[] el) {
		if (depth == 3) {
			int sum = 0;
			for (int r = 0; r < H; r++) {
				for (int c = 0; c < W; c++) {
					if (shotCount[r][c] > 0) {
						sum += D[r][c];
					}
				}
			}
			gamePoint = Math.max(gamePoint, sum);
			return;
		}
		
		int[] shootPosition = positionList.get(el[depth]);
		int r = shootPosition[0];
		int c = shootPosition[1];
		
		if (r == 0 && c == 0) {
			for (int i = 0; i < 3; i++) {
				switch(i) {
				case 0:
					shootDown(r, c, false);
					shoot(depth + 1, el);
					shootDown(r, c, true);
					break;
				case 1:
					shootRightDown(r, c, false);
					shoot(depth + 1, el);
					shootRightDown(r, c, true);
					break;
				case 2:
					shootRight(r, c, false);
					shoot(depth + 1, el);
					shootRight(r, c, true);
					break;
				default:
					break;
				}
			}
		} else if (r == 0 && c != 0) {
			for (int i = 0; i < 3; i++) {
				switch(i) {
				case 0:
					shootDownLeft(r, c, false);
					shoot(depth + 1, el);
					shootDownLeft(r, c, true);
					break;
				case 1:
					shootDown(r, c, false);
					shoot(depth + 1, el);
					shootDown(r, c, true);
					break;
				case 2:
					shootRightDown(r, c, false);
					shoot(depth + 1, el);
					shootRightDown(r, c, true);
					break;
				default:
					break;
				}
			}
		} else if (r != 0 && c == 0) {
			for (int i = 0; i < 3; i++) {
				switch(i) {
				case 0:
					shootRightUp(r, c, false);
					shoot(depth + 1, el);
					shootRightUp(r, c, true);
					break;
				case 1:
					shootRight(r, c, false);
					shoot(depth + 1, el);
					shootRight(r, c, true);
					break;
				case 2:
					shootRightDown(r, c, false);
					shoot(depth + 1, el);
					shootRightDown(r, c, true);
					break;
				default:
					break;
				}
			}
		}
	}
	
	static void shootRight(int r ,int c, boolean reset) {
		if (!reset) {
			shotCount[r][c] += 1;
			if (c + 1 < W) {
				shootRight(r, c + 1, false);
			}	
		} else {
			shotCount[r][c] -= 1;
			if (c + 1 < W) {
				shootRight(r, c + 1, true);
			}	
		}
	}
	
	static void shootRightUp(int r, int c, boolean reset) {
		if (!reset) {
			shotCount[r][c] += 1;
			if (r - 1 >= 0 && c + 1 < W) {
				shootRightUp(r - 1, c + 1, false);
			}
		} else {
			shotCount[r][c] -= 1;
			if (r - 1 >= 0 && c + 1 < W) {
				shootRightUp(r - 1, c + 1, true);
			}
		}
	}
	
	static void shootRightDown(int r, int c, boolean reset) {
		if (!reset) {
			shotCount[r][c] += 1;
			if (r + 1 < H && c + 1 < W) {
				shootRightDown(r + 1,  c + 1, false);
			}	
		} else {
			shotCount[r][c] -= 1;
			if (r + 1 < H && c + 1 < W) {
				shootRightDown(r + 1,  c + 1, true);
			}		
		}
	}
	
	static void shootDown(int r, int c, boolean reset) {
		if (!reset) {
			shotCount[r][c] += 1;
			if (r + 1 < H) {
				shootDown(r + 1, c, false);
			}
		} else {
			shotCount[r][c] -= 1;
			if (r + 1 < H) {
				shootDown(r + 1, c, true);
			}
		}
	}
	
	static void shootDownLeft(int r, int c, boolean reset) {
		if (!reset) {
			shotCount[r][c] += 1;
			if (r + 1 < H && c - 1 >= 0) {
				shootDownLeft(r + 1, c - 1, false);
			}	
		} else {
			shotCount[r][c] -= 1;
			if (r + 1 < H && c - 1 >= 0) {
				shootDownLeft(r + 1, c - 1, true);
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		System.setIn(new FileInputStream("src\\삼성SW역량테스트\\ad_230517_사격_게임_2\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			D = new int[H][W];
			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < W; c++) {
					D[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 쏘는 장소 고르기
			positionList = new ArrayList<>();
			for (int r = 0; r < H; r++) {
				positionList.add(new int[] {r, 0});
			}
			for (int c = 1; c < W; c++) {
				positionList.add(new int[] {0, c});
			}
			
			n = positionList.size();
			r = 3;
			
			input = new int[n];
			for (int i = 0; i < n; i++) {
				input[i] = i;
			}
			
			temp = new int[r];
			output = new ArrayList<>();
			combinationRepetition(0, 0);
			
			// 쏘고 가장 큰 점수 내기
			maxPoint = Integer.MIN_VALUE;
			for (int[] el : output) { // 쏘는 장소 3곳의 경우의 수
				shotCount = new int[H][W];
				gamePoint = 0;
				shoot(0, el);
				maxPoint = Math.max(maxPoint, gamePoint);
			}
			
			// 답 출력
			sb.append("#").append(String.valueOf(t)).append(" ").append(String.valueOf(maxPoint));
			if (t < T) {
				sb.append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}
}
