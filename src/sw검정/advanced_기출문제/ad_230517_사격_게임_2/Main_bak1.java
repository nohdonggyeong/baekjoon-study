package sw검정.advanced_기출문제.ad_230517_사격_게임_2;

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

public class Main_bak1 {
	static int T, H, W;
	static int[][] balloons;
	static int[][] shot;
	static List<int[]> shootingPositionCandidateList;
	static int combinationN, combinationR;
	static int[] shootingPositionCandidateIndex;
	static int[] combinationTemp;
	static List<int[]> shootingPositionIndexList;
	static int maxScore;
	
	static void combinationRepetition(int start, int depth) {
		if (depth == combinationR) { // 사격 위치 3곳 정했으면 중복 조합 리스트 추가
			shootingPositionIndexList.add(combinationTemp.clone());
			return;
		}
		
		for (int i = start; i < combinationN; i++) {
			combinationTemp[depth] = shootingPositionCandidateIndex[i];
			combinationRepetition(i, depth + 1);
		}
	}
	
	// 오른쪽으로 사격
	static void shootRight(int h, int w, boolean reset) {
		if (!reset) {
			shot[h][w] += 1;
			
			int newW = w + 1;
			if (newW < W) {
				shootRight(h, newW, false);
			}
		} else { // 리셋
			shot[h][w] -= 1;
			
			int newW = w + 1;
			if (newW < W) {
				shootRight(h, newW, true);
			}
		}
	}
	
	// 오른쪽 위로 사격
	static void shootRightUp(int h, int w, boolean reset) {
		if (!reset) {
			shot[h][w] += 1;
			
			int newH = h - 1;
			int newW = w + 1;
			if (newH >= 0 && newW < W) {
				shootRightUp(newH, newW, false);
			}
		} else { // 리셋
			shot[h][w] -= 1;
			
			int newH = h - 1;
			int newW = w + 1;
			if (newH >= 0 && newW < W) {
				shootRightUp(newH, newW, true);
			}
		}
	}
	
	// 오른쪽 아래로 사격
	static void shootRightDown(int h, int w, boolean reset) {
		if (!reset) {
			shot[h][w] += 1;
			
			int newH = h + 1;
			int newW = w + 1;
			if (newH < H && newW < W) {
				shootRightDown(newH, newW, false);
			}
		} else { // 리셋
			shot[h][w] -= 1;
			
			int newH = h + 1;
			int newW = w + 1;
			if (newH < H && newW < W) {
				shootRightDown(newH, newW, true);
			}
		}
	}
	
	// 아래로 사격
	static void shootDown(int h, int w, boolean reset) {
		if (!reset) {
			shot[h][w] += 1;
			
			int newH = h + 1;
			if (newH < H) {
				shootDown(newH, w, false);
			}
		} else { // 리셋
			shot[h][w] -= 1;
			
			int newH = h + 1;
			if (newH < H) {
				shootDown(newH, w, true);
			}
		}
	}
	
	// 아래 왼쪽으로 사격
	static void shootDownLeft(int h, int w, boolean reset) {
		if (!reset) {
			shot[h][w] += 1;
			
			int newH = h + 1;
			int newW = w - 1;
			if (newH < H && newW >= 0) {
				shootDownLeft(newH, newW, false);
			}
		} else { // 리셋
			shot[h][w] -= 1;
			
			int newH = h + 1;
			int newW = w - 1;
			if (newH < H && newW >= 0) {
				shootDownLeft(newH, newW, true);
			}
		}
	}
	
	// 게임점수 세기
	static int getScore() {
		int score = 0;
		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				if (shot[h][w] > 0) {
					score += balloons[h][w];
				}
			}
		}
		return score;
	}
	
	// 사격 경우마다 score 얻기
	static void shootingDFS(int depth, int[] shootingPositionIndex) {
		if (depth == 3) {			
//			if (maxScore < getScore()) {
//				System.out.println();
//				for (int h = 0; h < H; h++) {
//					for (int w = 0; w < W; w++) {
//						System.out.print(shot[h][w] > 0 ? "O " : "X ");
//					}
//					System.out.println();
//				}
//				System.out.println();
//			}
			
			maxScore = Math.max(maxScore, getScore());
			return;
		}
		
		int[] shootingPosition = shootingPositionCandidateList.get(shootingPositionIndex[depth]);
		if (shootingPosition[0] == 0 && shootingPosition[1] == 0) {
			for (int i = 0; i < 3; i++) {
				switch (i) {
				case 0:
					shootRight(0, 0, false);
					shootingDFS(depth + 1, shootingPositionIndex);
					shootRight(0, 0, true);
					break;
				case 1:
					shootDown(0, 0, false);
					shootingDFS(depth + 1, shootingPositionIndex);
					shootDown(0, 0, true);
					break;
				case 2:
					shootRightDown(0, 0, false);
					shootingDFS(depth + 1, shootingPositionIndex);
					shootRightDown(0, 0, true);
					break;
				default:
					break;
				}
			}
		} else if (shootingPosition[1] == 0) {
			for (int i = 0; i < 3; i++) {
				switch (i) {
				case 0:
					shootRight(shootingPosition[0], 0, false);
					shootingDFS(depth + 1, shootingPositionIndex);
					shootRight(shootingPosition[0], 0, true);
					break;
				case 1:
					shootRightUp(shootingPosition[0], 0, false);
					shootingDFS(depth + 1, shootingPositionIndex);
					shootRightUp(shootingPosition[0], 0, true);
					break;
				case 2:
					shootRightDown(shootingPosition[0], 0, false);
					shootingDFS(depth + 1, shootingPositionIndex);
					shootRightDown(shootingPosition[0], 0, true);
					break;
				default:
					break;
				}
			}
		} else if (shootingPosition[0] == 0) {
			for (int i = 0; i < 3; i++) {
				switch (i) {
				case 0:
					shootDown(0, shootingPosition[1], false);
					shootingDFS(depth + 1, shootingPositionIndex);
					shootDown(0, shootingPosition[1], true);
					break;
				case 1:
					shootDownLeft(0, shootingPosition[1], false);
					shootingDFS(depth + 1, shootingPositionIndex);
					shootDownLeft(0, shootingPosition[1], true);
					break;
				case 2:
					shootRightDown(0, shootingPosition[1], false);
					shootingDFS(depth + 1, shootingPositionIndex);
					shootRightDown(0, shootingPosition[1], true);
					break;
				default:
					break;
				}
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
//		LocalDateTime startTime = LocalDateTime.now();
//		System.setIn(new FileInputStream("src/삼성SW역량테스트/ad_230517_사격_게임_2/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 테스트케이스 반복
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			// H, W, balloons, shot 크기 입력
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			balloons = new int[H][W];
			shot = new int[H][W];
			
			// 풍선 점수 입력
			for (int h = 0; h < H; h++) {
				st = new StringTokenizer(br.readLine());
				for (int w = 0; w < W; w++) {
					balloons[h][w] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 사격 위치 후보 구성
			shootingPositionCandidateList = new ArrayList<>();
			for (int h = 0; h < H; h++) {
				shootingPositionCandidateList.add(new int[] {h, 0});
			}
			for (int w = 1; w < W; w++) { // {0, 0} 중복 제외
				shootingPositionCandidateList.add(new int[] {0, w});
			}
			
			// 사격 위치 중복 조합
			combinationN = shootingPositionCandidateList.size();
			combinationR = 3;
			shootingPositionCandidateIndex = new int[combinationN];
			for (int i = 0; i < combinationN; i ++) {
				shootingPositionCandidateIndex[i] = i;
			}
			combinationTemp = new int[combinationR];
			shootingPositionIndexList = new ArrayList<>();
			combinationRepetition(0, 0);
			
			// 모든 경우 중 max score 얻기
			maxScore = 0;
			for (int[] el : shootingPositionIndexList) {
				shootingDFS(0, el);
			}
			
			// 테스트케이스 결과 출력
			sb.append("#").append(String.valueOf(t + 1)).append(" ").append(String.valueOf(maxScore));
			if (t < T - 1) {
				sb.append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		
//		LocalDateTime endTime = LocalDateTime.now();
//		System.out.println();
//		System.out.println();
//		System.out.println("[Elapsed time: " + Duration.between(startTime, endTime).getSeconds() + " sec]");
		
		bw.close();
		br.close();
	}
}
