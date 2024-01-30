package AD검정.ad_240117_지형도;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Noh Donggyeong
 * 
 * 문제 풀었던 내용 공유합니다.
 * 1. 입력으로 주어지는 지형의 0, 0 지점부터 H, W 크기로 반복문을 돌며 모든 지역을 확인
 * 2. H, W 크기 내의 지형의 높이를 1차원 배열로 가져와서 정렬
 * 3. 정렬된 1차원 배열에서 가장 높은 값을 시작으로 깎고 쌓는 작업 수행 (높은 지형부터 깎아야 쌓을 때의 비용 절감 가능)
 * 4. 최소 비용 도출
 * 5. H와 W의 값을 바꿔 1~4 과정 동일하게 수행
 * 
 */
public class Main {
	static int T;
	static int N;
	static int H, W;
	static int[][] map;
	static int[] sorted;
	static int minCost;
	
	static void getMinCost(int r, int c) {
		// r, c로 시작하는 H, W 크기의 지형을 1차원 배열로 담고 오름차순 정렬
		int index = 0;
		sorted = new int[H * W];
		for (int i = r; i < r + H; i++) {
			for (int j = c; j < c + W; j++) {
				sorted[index++] = map[i][j];
			}
		}
		Arrays.sort(sorted);
		
		// 주어진 지형 높이는 순서대로 1차원 배열에 담긴 상태
		// 큰 높이부터 작은 높이까지 기준 높이가 되어보고 최소 비용 도출
		int preHeight = -1;
		for (int i = sorted.length - 1; i >= 0; i--) {
			if (preHeight == sorted[i]) { // 지금 기준 높이가 이전의 기준 높이와 같다면 연산 회피
				continue;
			} else {
				preHeight = sorted[i];
			}
			
			int standard = sorted[i];
			int sum = 0; // 비용 계산
			int bak = 0; // 기준 높이보다 높은 땅에서 나온 흙을 담는 변수
			for (int j = sorted.length - 1; j >= 0; j--) { // 큰 높이부터 시작되어야 bak 사용 가능
				if (sorted[j] > standard) {
					sum += (sorted[j] - standard) * 2;
					bak += (sorted[j] - standard);
				} else if (sorted[j] < standard) {
					if (standard - sorted[j] > bak) {
						sum += (standard - sorted[j] - bak) * 2 + bak;
						bak = 0;
					} else {
						sum += standard - sorted[j];
						bak -= standard - sorted[j];
					}
				}
			}
			
			minCost = Math.min(minCost, sum);
		}
	}
	
	public static void main(String[] args) {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringBuilder sb = new StringBuilder();
			StringTokenizer st;
			
			T = Integer.parseInt(br.readLine()); // Test case 반복
			for (int t = 1; t <= T; t++) {
				N = Integer.parseInt(br.readLine()); // N 입력
				
				map = new int[N][N]; // map 입력
				for (int r = 0; r < N; r++) {
					st = new StringTokenizer(br.readLine());
					for (int c = 0; c < N; c++) {
						map[r][c] = Integer.parseInt(st.nextToken());
					}
				}
				
				st = new StringTokenizer(br.readLine()); // H, W 입력
				W = Integer.parseInt(st.nextToken());
				H = Integer.parseInt(st.nextToken());

				// 입력 주어진대로 가로, 세로 크기의 최소 지형 평탄화 비용 체크 
				minCost = Integer.MAX_VALUE;
				for (int r = 0; r < N - H + 1; r++) {
					for (int c = 0; c < N - W + 1; c++) {
						getMinCost(r, c);
					}
				}
				
				// 가로와 세로를 바꾼 크기로 최소 지형 평탄화 비용 체크
				int temp = W;
				W = H;
				H = temp;
				for (int r = 0; r < N - H + 1; r++) {
					for (int c = 0; c < N - W + 1; c++) {
						getMinCost(r, c);
					}
				}
				
				sb.append("#").append(t).append(" ").append(minCost).append("\n");
			}
			bw.write(sb.toString().trim());
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
