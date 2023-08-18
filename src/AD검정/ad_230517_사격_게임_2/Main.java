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

	static int maxPoint;
	static int[][] shotCount;
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
				shootRightDown(r + 1, c + 1, false);
			}
		} else {
			shotCount[r][c] -= 1;
			if (r + 1 < H && c + 1 < W) {
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
				shootDownLeft(r + 1, c - 1, true);
			}
		} else {
			shotCount[r][c] -= 1;
			if (r + 1 < H && c - 1 >= 0) {
				shootDownLeft(r + 1, c - 1, false);
			}
		}
	}
	static int getPoint() {
		int point = 0;
		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				if (shotCount[h][w] > 0) {
					point += D[h][w];
				}
			}
		}
		return point;
	}
	static void shoot(int[] positionIndexes, int depth) {
		if (depth == 3) {
			maxPoint = Math.max(maxPoint, getPoint());
			return;
		}
		
		int positionIndex = positionIndexes[depth];
		int[] position = positionList.get(positionIndex);
		int r = position[0];
		int c = position[1];
		
		for (int i = 0; i < 3; i++) {
			if (r == 0 && c== 0) {
				switch(i) {
				case 0:
					shootDown(r, c, false);
					shoot(positionIndexes, depth + 1);
					shootDown(r, c, true);
					break;
				case 1:
					shootRightDown(r, c, false);
					shoot(positionIndexes, depth + 1);
					shootRightDown(r, c, true);
					break;
				case 2:
					shootRight(r, c, false);
					shoot(positionIndexes, depth + 1);
					shootRight(r, c, true);
					break;
				default:
					break;
				}
			} else if (r == 0 && c != 0) {
				switch(i) {
				case 0:
					shootDown(r, c, false);
					shoot(positionIndexes, depth + 1);
					shootDown(r, c, true);
					break;
				case 1:
					shootDownLeft(r, c, false);
					shoot(positionIndexes, depth + 1);
					shootDownLeft(r, c, true);
					break;
				case 2:
					shootRight(r, c, false);
					shoot(positionIndexes, depth + 1);
					shootRight(r, c, true);
					break;
				default:
					break;
				}
			} else if (r != 0 && c == 0) {
				switch(i) {
				case 0:
					shootRight(r, c, false);
					shoot(positionIndexes, depth + 1);
					shootRight(r, c, true);
					break;
				case 1:
					shootRightUp(r, c, false);
					shoot(positionIndexes, depth + 1);
					shootRightUp(r, c, true);
					break;
				case 2:
					shootRightDown(r, c, false);
					shoot(positionIndexes, depth + 1);
					shootRightDown(r, c, true);
					break;
				default:
					break;
				}
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
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			D = new int[H][W];
			for (int h = 0; h < H; h++) {
				st = new StringTokenizer(br.readLine());
				for (int w = 0; w < W; w++) {
					D[h][w] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 장소 선정
			positionList = new ArrayList<>();
			for (int h = 0; h < H; h++) {
				positionList.add(new int[] {h, 0});
			}
			for (int w = 1; w < W; w++) {
				positionList.add(new int[] {0, w});
			}
			
			n = H + W - 1;
			r = 3;
			input = new int[n];
			for (int i = 0; i < n; i++) {
				input[i] = i;
			}
			
			temp = new int[r];
			output = new ArrayList<>();	
			combinationRepetition(0, 0);
			
			// 사격 수행
			maxPoint = Integer.MIN_VALUE;
			for (int[] el : output) {
				shotCount = new int[H][W];
				shoot(el, 0);
			}
			
			// 점수 출력
			sb.append("#").append(String.valueOf(t + 1)).append(" ").append(String.valueOf(maxPoint));
			if (t < T - 1) {
				sb.append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}
}
