package AD검정.ad_230517_사격_게임_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	// 중복 조합으로 사격 지점 3곳 선정(같은 곳에서 방향 다르게 3번 가능)
	// 중복 조합의 끝에서 중복 순열로 사격 방향 선정(다른 사격지점에서 동일하게 사격 가능)
	
	static int T, H, W;
	static int[][] D;
	static int answer;
	static boolean[][] visited;
	
	static List<int[]> candidates;
	static int n, r;
	static int[] input, input2;
	static int[] temp, temp2;
	static List<int[]> output;
	
	static void combinationWithRepetition(int start, int depth) {
		if (depth == r) {
//			output.add(temp.clone());
//			return;
			
			input2 = new int[3];
			for (int i = 0; i < 3; i++) {
				input2[i] = i;
			}
			temp2 = new int[3];
			permutationWithRepetition(0);
			return;
		}
		
		for (int i = start; i < n; i++) {
			temp[depth] = input[i];
			combinationWithRepetition(i, depth + 1);
		}
	}
	
	static void permutationWithRepetition(int depth) {
		if (depth == 3) {
			// temp.clone() -> {사격 장소 3곳}
			// temp2.clone() -> {사격 3방향}
			visited = new boolean[H][W];
			
			for (int i = 0; i < temp.length; i++) {
				int h = candidates.get(temp[i])[0];
				int w = candidates.get(temp[i])[1];
				if (h == 0 && w == 0) {
					switch (temp2[i]) {
					case 0: // 오른쪽
						shootRight(h, w);
						break;
					case 1: // 오른쪽 아래
						shootRightDown(h, w);
						break;
					case 2: // 아래
						shootDown(h, w);
						break;
					default:
						break;
					}
				} else if (w == 0) {
					switch (temp2[i]) {
					case 0: // 오른쪽위
						shootRightUp(h, w);
						break;
					case 1: // 오른쪽
						shootRight(h, w);
						break;
					case 2: // 오른쪽아래
						shootRightDown(h, w);
						break;
					default:
						break;
					}
				} else if (h == 0) {
					switch (temp2[i]) {
					case 0: // 오른쪽아래
						shootRightDown(h, w);
						break;
					case 1: // 아래
						shootDown(h, w);
						break;
					case 2: // 왼쪽아래
						shootLeftDown(h, w);
						break;
					default:
						break;
					}
				}
			}
			
			// visited true -> sum
			int sum = 0;
			for (int h = 0; h < H; h++) {
				for (int w = 0; w < W; w++) {
					if (visited[h][w]) {
						sum += D[h][w];
					}
				}
			}
			
			answer = Math.max(answer, sum);
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			temp2[depth] = input2[i];
			permutationWithRepetition(depth + 1);
		}
	}
	
	static void shootRight(int h, int w) {
		for (int i = 0; i < Math.max(H, W); i++) {
			if (w + i >= W) {
				return;
			}
			visited[h][w + i] = true;
		}
	}
	
	static void shootRightDown(int h, int w) {
		for (int i = 0; i < Math.max(H, W); i++) {
			if (h + i >= H || w + i >= W) {
				return;
			}
			visited[h + i][w + i] = true;
		}
	}
	
	static void shootDown(int h, int w) {
		for (int i = 0; i < Math.max(H, W); i++) {
			if (h + i >= H) {
				return;
			}
			visited[h + i][w] = true;
		}
	}
	
	static void shootRightUp(int h, int w) {
		for (int i = 0; i < Math.max(H, W); i++) {
			if (h - i < 0 || w + i >= W) {
				return;
			}
			visited[h - i][w + i] = true;
		}
	}
	
	static void shootLeftDown(int h, int w) {
		for (int i = 0; i < Math.max(H, W); i++) {
			if (h + i >= H || w - i < 0) {
				return;
			}
			visited[h + i][w - i] = true;
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
			
			// 사격 장소 3곳 선정
			// {h, 0}, {0, w} - {0, 0}
			candidates = new ArrayList<int[]>();
			for (int h = 0; h < H; h++) {
				candidates.add(new int[] {h, 0});
			}
			for (int w = 1; w < W; w++) {
				candidates.add(new int[] {0, w});
			}
			
			
			// 사격 방향 선정
			n = H + W - 1;
			r = 3;
			
			input = new int[n];
			for (int i = 0; i < n; i++) {
				input[i] = i;
			}
			
			temp = new int[r];
			answer = Integer.MIN_VALUE;
			combinationWithRepetition(0, 0);
				
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}
}
