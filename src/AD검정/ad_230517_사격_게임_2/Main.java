package AD검정.ad_230517_사격_게임_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int T, H, W;
	static int[][] D;
	static List<int[]> positionList;
	
	static int n, r;
	static int[] input;
	static int[] temp;
	static List<int[]> output;
	
	static int gamePoint, maxPoint;
	static int[][] shotCount;
	
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
	
	static void shoot(int depth, int[] positionIndexes) {
		if (depth == 3) {
			int point = 0;
			for (int r = 0; r < H; r++) {
				for (int c = 0; c < W; c++) {
					if (shotCount[r][c] > 0) {
						point += D[r][c];
					}
				}
			}
			gamePoint = Math.max(gamePoint, point);
			return;
		}
		
		int[] position = positionList.get(positionIndexes[depth]);
		int r = position[0];
		int c = position[1];
		
		if (r == 0 && c == 0) {
			for (int i = 0; i < 3; i++) {
				switch(i) {
				case 0:
					shootRight(r, c, false);
					shoot(depth + 1, positionIndexes);
					shootRight(r, c, true);
					break;
				case 1:
					shootRightDown(r, c, false);
					shoot(depth + 1, positionIndexes);
					shootRightDown(r, c, true);
					break;
				case 2:
					shootDown(r, c, false);
					shoot(depth + 1, positionIndexes);
					shootDown(r, c, true);
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
					shoot(depth + 1, positionIndexes);
					shootRightUp(r, c, true);
					break;
				case 1:
					shootRight(r, c, false);
					shoot(depth + 1, positionIndexes);
					shootRight(r, c, true);
					break;
				case 2:
					shootRightDown(r, c, false);
					shoot(depth + 1, positionIndexes);
					shootRightDown(r, c, true);
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
					shoot(depth + 1, positionIndexes);
					shootDownLeft(r, c, true);
					break;
				case 1:
					shootDown(r, c, false);
					shoot(depth + 1, positionIndexes);
					shootDown(r, c, true);
					break;
				case 2:
					shootRightDown(r, c, false);
					shoot(depth + 1, positionIndexes);
					shootRightDown(r, c, true);
					break;
				default:
					break;
				}
			}
		}
	}
	
	static void shootRight(int r, int c, boolean reset) {
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
			if (r - 1 >= 0 && c + 1 <W) {
				shootRightUp(r - 1, c + 1, false);
			}
		} else {
			shotCount[r][c] -= 1;
			if (r - 1 >= 0 && c + 1 <W) {
				shootRightUp(r - 1, c + 1, true);
			}
		}
	}
	
	static void shootRightDown(int r, int c, boolean reset) {
		if (!reset) {
			shotCount[r][c] += 1;
			if (r + 1 < H && c + 1 <W) {
				shootRightDown(r + 1, c + 1, false);
			}
		} else {
			shotCount[r][c] -= 1;
			if (r + 1 < H && c + 1 <W) {
				shootRightDown(r + 1, c + 1, true);
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
			
			// 사격 지점 선정
			positionList = new ArrayList<>();
			for (int r = 0; r < H; r++) {
				positionList.add(new int[] {r, 0});
			}
			for (int c = 1; c < W; c++) {
				positionList.add(new int[] {0, c});
			}
			
			n = H + W - 1;
			r = 3;
			input = new int[n];
			for (int i = 0 ; i < n; i++) {
				input[i] = i;
			}
			
			temp = new int[r];
			output = new ArrayList<>(); // position indexes
			combinationRepetition(0, 0);
			
			maxPoint = Integer.MIN_VALUE;
			for (int[] el : output) { // 사격 지점 3곳 경우의 수
				// 백트래킹으로 3곳을 depth + 1로 알아보기
				gamePoint = Integer.MIN_VALUE;
				shotCount = new int[H][W];
				shoot(0, el);
				maxPoint = Math.max(maxPoint, gamePoint);
			}
			
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
