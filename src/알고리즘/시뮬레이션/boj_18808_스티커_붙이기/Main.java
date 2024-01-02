package 알고리즘.시뮬레이션.boj_18808_스티커_붙이기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] notebook;
	static int R, C;
	static Queue<int[][]> stickerQueue;
	
	// 내가 있으면 좋겠다고 생각한 함수를  pseudo code로 작성
	static boolean checkStickerFit(int n, int m, int[][] sticker) {
		// 영역을 넘어갔는지 확인
		if (n + sticker.length > N || m + sticker[0].length > M) {
			return false;
		}
		// 영역이 이미 1이 들어갔는지를 확인
		for (int i = 0; i < sticker.length; i++) {
			for (int j = 0; j < sticker[0].length; j++) {
				if (sticker[i][j] == 1 && notebook[n + i][m + j] == 1) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	static void markSticker(int n , int m, int[][] sticker) {
		for (int i = 0; i < sticker.length; i++) {
			for (int j = 0; j < sticker[0].length; j++) {
				if (sticker[i][j] == 1) {
					notebook[n + i][m + j] = 1;
				}
			}
		}		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		notebook = new int[N][M];
		
		stickerQueue = new LinkedList<>();
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			int[][] tempArr = new int[R][C];
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; c++) {
					tempArr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
//			printArr(tempArr);
			stickerQueue.offer(tempArr);
		}

		for (int k = 0; k < K; k++) {
			int[][] sticker = stickerQueue.poll();
//			printArr(sticker);
			loop:
			for (int i = 0; i < 4; i++) {
				boolean fitResult = false;
				for (int n = 0; n < N; n++) {
					for (int m = 0; m < M; m++) {
//						printArr(sticker);
						fitResult = checkStickerFit(n, m, sticker);
						if (fitResult) {
//							System.out.print("n, m: " + n + ", " + m);
//							printArr(sticker);
							
							// notebook을 1로 체크
							markSticker(n, m, sticker);
							break loop;
						}
					}
				}

				// 문제 제시된 대로 반복하고 안 된다면 버리기
				int[][] stickerBak = sticker.clone();
				R = sticker.length;
				C = sticker[0].length;
				sticker = new int[C][R];
				
				for (int r = 0; r < R; r++) {
					for (int c = 0; c < C; c++) {
						sticker[c][r] = stickerBak[R - 1 - r][c];
					}
				}
			}
		}
		
		int count = 0;
		// 노트북 스티커 붙인 영역 카운트 출력
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (notebook[n][m] == 1) {
					count++;
				}
			}
		}
		
		bw.write(String.valueOf(count));
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void printArr(int[][] arr) {
		System.out.println();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(String.valueOf(arr[i][j]) + " ");
			}
			System.out.println();
		}
	}
}
