package 알고리즘.분할정복.boj_1992_쿼드트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static StringBuilder sb;
	static int N;
	static int[][] map;
	
	static boolean isPossible(int x, int y, int size) {
		int number = map[x][y];
		
		for (int r = x; r < x + size; r++) {
			for (int c = y; c < y + size; c++) {
				if (number != map[r][c]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	static void quadTree(int x, int y, int size) {
		if (isPossible(x, y, size)) {
			sb.append(map[x][y]);
			return;
		}
		
		int dividedSize = size / 2;
		
		sb.append("(");
		quadTree(x, y, dividedSize);
		quadTree(x, y + dividedSize, dividedSize);
		quadTree(x + dividedSize, y, dividedSize);
		quadTree(x + dividedSize, y + dividedSize, dividedSize);
		sb.append(")");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < N; c++) {
				map[r][c] = str.charAt(c) - '0';
			}
		}
		
//		System.out.println();
//		for (int r = 0; r < N; r++) {
//			for (int c = 0; c < N; c++) {
//				System.out.print(map[r][c] + " ");
//			}
//			System.out.println();
//		}
		
		quadTree(0, 0, N);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
