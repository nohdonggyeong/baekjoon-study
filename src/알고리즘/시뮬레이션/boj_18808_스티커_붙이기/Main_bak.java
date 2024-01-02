package 알고리즘.시뮬레이션.boj_18808_스티커_붙이기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_bak {
	static int N, M, K, R, C;
	static boolean[][] notebook;
	static Queue<boolean[][]> stickerQueue;
	
	static boolean checkSticker(int r, int c, boolean[][] sticker) {
		boolean[][] cp_notebook = notebook.clone();
		
		for (int h = r; h < r + sticker.length; h++ ) {
			for (int w = c; w < c + sticker[0].length; w++) {
				if (sticker[h - r][w - c] && cp_notebook[h][w]) {
					return false;
				}
				if (sticker[h - r][w - c]) {
					cp_notebook[h][w] = true;
				}
			}
		}
		notebook = cp_notebook.clone();
		return true;
	}
	
	static boolean[][] rotate(boolean[][] sticker) {
		boolean[][] rotated = new boolean[sticker[0].length][sticker.length];
		
		for (int i = 0; i < sticker.length; i++) {
			for (int j = 0; j < sticker[0].length; j++) {
				rotated[j][sticker.length - i - 1] = sticker[i][j];
			}
		}
		
		return rotated;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		notebook = new boolean[N][M];
		
		stickerQueue = new LinkedList<>();
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			boolean[][] temp = new boolean[R][C];
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; c++) {
					temp[r][c] = Integer.parseInt(st.nextToken()) == 1 ? true : false;
				}
			}
			
			stickerQueue.add(temp);
		}
		
		while (!stickerQueue.isEmpty()) {
			boolean[][] sticker = stickerQueue.poll();
			
			boolean flag = false;
			int rotateCount = 0;
			do {
				loop: 
				for (int r = 0; r <= notebook.length - sticker.length; r++) {
					for (int c = 0; c <= notebook[0].length - sticker[0].length; c++) {
						boolean possible = checkSticker(r, c, sticker);
						if (possible) {
							flag = false;
							break loop;
						} else {
							sticker = rotate(sticker).clone();
							rotateCount += 1;
							flag = true;
							break loop;
						}
					}
				}
			} while (flag && rotateCount <= 3);
		}
		
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (notebook[i][j]) {
					result += 1;
				}
			}
		}
		
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}

}
