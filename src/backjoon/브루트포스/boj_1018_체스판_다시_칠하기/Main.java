package backjoon.브루트포스.boj_1018_체스판_다시_칠하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int getMinColored(boolean[][] board, int i, int j) {
		int cnt = 0;
		for (int r = i; r < i + 8; r++) {
			for (int c = j; c < j + 8; c++) {
				if (((r - i + c - j) % 2 == 0 && board[i][j] != board[r][c])
						|| ((r - i + c - j) % 2 == 1 && board[i][j] == board[r][c])) {
					cnt++;
				}
			}
		}
		return Math.min(cnt, 64 - cnt);
	}
	public static void main(String args[]) throws IOException {
		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[][] board = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String[] inputLine = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				if ("W".equals(inputLine[j])) {
					board[i][j] = true;
				} else {
					board[i][j] = false;
				}
			}
		}
		
		// Process
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < N - 7; i++) {
			for (int j = 0; j < M - 7; j++) {
				result = Math.min(result, getMinColored(board, i, j));
			}
		}
		
		// Output
		bw.write(String.valueOf(result));
		
		bw.flush();
		bw.close();
		br.close();
	}
}