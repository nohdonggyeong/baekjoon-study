package sw검정.advanced_기출문제.ad_230517_사격_게임_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main2 {
	static int T, H, W;
	static int[][] D;
	
	static List<int[]> candidates;
	
	static int n, r;
	static int[] input, temp, temp2;

	static boolean[][] visited;
	static int maxPoint;
	
	// 쏘는 지점 3개를 구하는 함수
	static void combinationWithRepetition(int start, int depth) {
		if (depth == r) {
			temp2 = new int[3];
			combinationWithRepetition2(0);
			return;
		}
		
		for (int i = start; i < n; i++) {
			temp[depth] = input[i];
			combinationWithRepetition(i, depth + 1);
		}
	}
	
	// 쏘는 방향 3개를 구하는 함수
	static void combinationWithRepetition2(int depth) {
		if (depth == 3) {
			// 사격
			visited = new boolean[H][W];
			for (int s = 0; s < temp.length; s++) {
				int[] startPoint = candidates.get(temp[s]);
				if (startPoint[0] == 0 && startPoint[1] == 0) {
					switch (temp2[s]) {
					case 0: // 오른쪽으로 사격
						for (int i = 0; i < W; i++) {
							visited[0][i] = true;
						}
						break;
					case 1: // 오른쪽 아래로 사격
						for (int i = 0; i < Math.min(H, W); i++) {
							visited[i][i] = true;
						}
						break;
					case 2: // 아래로 사격
						for (int i  = 0; i < H; i++) {
							visited[i][0] = true;
						}
						break;
					default:
						break;
					}
				} else if (startPoint[0] == 0 && startPoint[1] != 0) {
					switch (temp2[s]) {
					case 0: // 왼쪽 아래로 사격
						for (int i = 0; i <= Math.min(H - 1, startPoint[1]); i++) {
							visited[i][startPoint[1] - i] = true;
						}
						break;
					case 1: // 아래로 사격
						for (int i = 0; i <= H - 1; i++) {
							visited[i][startPoint[1]] = true;
						}
						break;
					case 2: // 오른쪽 아래로 사격
						for (int i = 0; i <= Math.min(H - 1, W - 1 - startPoint[1]); i++) {
							visited[i][startPoint[1] + i] = true;
						}
						break;
					default:
						break;
					}
				} else if (startPoint[0] != 0 && startPoint[1] == 0) {
					switch (temp2[s]) {
					case 0: // 오른쪽 위로 사격
						for (int i = 0; i <= Math.min(startPoint[0], W - 1); i++) {
							visited[startPoint[0] - i][i] = true;
						}
						break;
					case 1: // 오른쪽으로 사격
						for (int i = 0; i <= W - 1; i++) {
							visited[startPoint[0]][i] = true;
						}
						break;
					case 2: // 오른쪽 아래로 사격
						for (int i  = 0; i <= Math.min(H - 1 - startPoint[0], W - 1); i++) {
							visited[startPoint[0] + i][i] = true;
						}
						break;
					default:
						break;
					}
				}
			}
			
			// 계산
			int score = 0;
			for (int h = 0; h < H; h++) {
				for(int w = 0; w < W; w++) {
					if (visited[h][w]) {
						score += D[h][w];
					}
				}
			}
			
			maxPoint = Math.max(maxPoint, score);
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			temp2[depth] = input[i];
			combinationWithRepetition2(depth + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
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
			
			candidates = new ArrayList<int[]>();
			for (int h = 0; h < H; h++) {
				candidates.add(new int[] {h, 0});
			}
			for (int w = 1; w < W; w++) {
				candidates.add(new int[] {0, w});
			}
			
			n = H + W - 1;
			r = 3;
			
			input = new int[n];
			for (int i = 0; i < n; i++) {
				input[i] = i;
			}
			
			temp = new int[3];
			maxPoint = Integer.MIN_VALUE;
			
			combinationWithRepetition(0, 0);
			
			sb.append("#").append(t).append(" ").append(maxPoint).append("\n");
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}

}
