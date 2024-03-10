package backjoon.시뮬레이션.boj_17837_새로운_게임_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_bak {
	static int N, K;
	static int[][] board; // 체스 판의 색상 값
	static int[][][] chess; // 체스 상황
	static int[] pieces; // index 말의 방향 값
	static int[] dr = {0, 0, 0, -1, 1}; // →, ←, ↑, ↓
	static int[] dc = {0, 1, -1, 0, 0};
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// 0은 흰색, 1은 빨간색, 2는 파란색
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				board[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		chess = new int[N + 1][N + 1][4];
		pieces = new int[K + 1];
		for (int k = 1; k <= K; k++) {
			st = new StringTokenizer(br.readLine());
			chess[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())][0] = k;
			pieces[k] = Integer.parseInt(st.nextToken()); 
		}
		
		while (true) {
			for (int k = 1; k <= K; k++) {
				
			}
		}
		
		// while
		//    for
	}
}
