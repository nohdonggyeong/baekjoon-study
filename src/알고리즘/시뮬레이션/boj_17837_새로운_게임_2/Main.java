package 알고리즘.시뮬레이션.boj_17837_새로운_게임_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[][] board; // 체스 판의 색상 값
	static int[] dr = {0, 0, 0, -1, 1}; // →, ←, ↑, ↓
	static int[] dc = {0, 1, -1, 0, 0};
	static List<Piece> pieceList;
	static int resultCount;
	
	static class Piece {
		private int r;
		private int c;
		private int direction;
		private List<Integer> carryOn;
		
		public Piece(int r, int c, int direction, List<Integer> carryOn) {
			this.r = r;
			this.c = c;
			this.direction = direction;
			this.carryOn = carryOn;
		}
	}
	
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
		
		pieceList = new ArrayList<>();
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			pieceList.add(new Piece(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), new ArrayList<>()));
		}
		
		resultCount = 0;
		while (true) {
			for (int k = 0; k < K; k++) {
				Piece piece = pieceList.get(k);
				int nh = piece.direction;
				int nr = piece.r + dr[nh];
				int nc = piece.c + dr[nh];
				List<Integer> ncarryOn = piece.carryOn;
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= N || board[nr][nc] == 2) {
					switch(nh) {
					case 1:
						nh = 2;
						break;
					case 2:
						nh = 1;
						break;
					case 3:
						nh = 4;
						break;
					case 4:
						nh = 3;
						break;
					default:
						break;
					}
					nr = piece.r + dr[nh];
					nc = piece.c + dc[nh];
				}
				if (nr < 0 || nc < 0 || nr >= N || nc >= N || board[nr][nc] == 2) {
					continue;
				}
				
				if (board[nr][nc] == 0) {
					for (int i = 0; i < pieceList.size(); i++) {
						if (pieceList.get(i).r == nr && pieceList.get(i).c == nc) {
							List<Integer> tempList = pieceList.get(i).carryOn;
							tempList.addAll(ncarryOn);
							pieceList.set(i, new Piece(pieceList.get(i).r, pieceList.get(i).c, pieceList.get(i).direction, tempList));
						}
					}
					pieceList.set(k, new Piece(nr, nc, nh, ncarryOn));
				} else if (board[nr][nc] == 1) {
					Stack<Integer> stack = new Stack<>();
					for (int i = 0; i < ncarryOn.size(); i++) {
						
					}
					for (int i = 0; i < pieceList.size(); i++) {
						if (pieceList.get(i).r == nr && pieceList.get(i).c == nc) {
							List<Integer> tempList = pieceList.get(i).carryOn;
							tempList.addAll(ncarryOn);
							pieceList.set(i, new Piece(pieceList.get(i).r, pieceList.get(i).c, pieceList.get(i).direction, tempList));
						}
					}
					pieceList.set(k, new Piece(nr, nc, nh, ncarryOn));
				}
			}
			resultCount++;
		}
		
		// while
		//    for
	}
}
