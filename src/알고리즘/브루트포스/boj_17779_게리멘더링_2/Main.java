package 알고리즘.브루트포스.boj_17779_게리멘더링_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int sumAll;
	static int[] sumArea;
	static boolean[][] border;
	static int resultMin;
	
	static void getSumArea(int x, int y, int d1, int d2) {
		// 경계선 설정
		for (int i = 0; i <= d1; i++) {
			border[x + i][y - i] = true;
			border[x + d2 + i][y + d2 - i] = true;
		}
		for (int i = 0; i <= d2; i++) {
			border[x + i][y + i] = true;
			border[x + d1 + i][y - d1 + i] = true;
		}
		
		// 구역 1
		for (int i = 0; i < x + d1; i++) {
			for (int j = 0; j <= y; j++) {
				if (border[i][j]) {
					break;
				}
				sumArea[0] += map[i][j];
			}
		}
		
		// 구역 2
		for (int i = 0; i <= x + d2; i++) {
			for (int j = N - 1; j > y; j--) {
				if (border[i][j]) {
					break;
				}
				sumArea[1] += map[i][j];
			}
		}
		
		// 구역 3
		for (int i = x + d1; i < N; i++) {
			for (int j = 0; j < y - d1 + d2; j++) {
				if (border[i][j]) {
					break;
				}
				sumArea[2] += map[i][j];
			}
		}
		
		// 구역 4
		for (int i = x + d2 + 1; i < N; i++) {
			for (int j = N - 1; j >= y - d1 + d2; j--) {
				if (border[i][j]) {
					break;
				}
				sumArea[3] += map[i][j];
			}
		}
		
		// 구역 5
		sumArea[4] = sumAll;
		for (int i = 0; i < 4; i++) {
			sumArea[4] -= sumArea[i];
		}
	}
	
	static int getDiff() {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i <5; i++) {
			max = Math.max(max, sumArea[i]);
			min = Math.min(min, sumArea[i]);
		}
		return max - min;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		sumAll = 0;
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				sumAll += map[i][j]; 
			}
		}
		
		resultMin = Integer.MAX_VALUE;
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				for (int d1 = 1; d1 < N; d1++) {
					for (int d2 = 1; d2 < N; d2++) {
						if (x + d1 + d2 >= N) {
							continue;
						}
						if (y - d1 < 0 || y + d2 >= N) {
							continue;
						}
						sumArea = new int[5];
						border = new boolean[N][N];
						getSumArea(x, y, d1, d2);
						resultMin = Math.min(resultMin, getDiff());
					}
				}
			}
		}
		
		bw.write(String.valueOf(resultMin));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
