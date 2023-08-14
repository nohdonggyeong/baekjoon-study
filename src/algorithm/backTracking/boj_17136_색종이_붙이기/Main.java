package algorithm.backTracking.boj_17136_색종이_붙이기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int[] papers;
	static int resultCount;
	
	static boolean coverable(int i, int j, int size) {
		boolean result = true;
		loop:
		for (int r = i; r < i + size; r++) {
			for (int c = j; c < j + size; c++) {
				if (r >= 10 || c >= 10 || map[r][c] != 1) {
					result = false;
					break loop;
				}
			}
		}
		return result;
	}
	
	static void coverSpace(int i, int j, int size, int status) {
		for (int r = i; r < i + size; r++) {
			for (int c = j; c < j + size; c++) {
				map[r][c] = status;
			}
		}
	}
	
	static void backTracking(int i, int j) {
		if (j > 9) {
			backTracking(i + 1, 0);
			return;
		}
		
		if (i > 9) {
			int remainSum = 0;
			for (int el : papers) {
				remainSum += el;
			}
			resultCount = Math.min(resultCount, 25 - remainSum);
			return;
		}
		
		if (map[i][j] == 1) {
			for (int s = 5; s > 0; s--) {
				if (papers[s] > 0 && coverable(i, j, s)) {
					coverSpace(i, j, s, 0);
					papers[s] -= 1;
					backTracking(i, j + 1);
					coverSpace(i, j, s, 1);
					papers[s] += 1;
				}
			}
		} else {
			backTracking(i, j + 1);
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		map = new int[10][10];
		for (int r = 0; r < 10; r++ ) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 10; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		papers = new int[6];
		Arrays.fill(papers, 5);
		papers[0] = 0;
		
		resultCount = Integer.MAX_VALUE;
		backTracking(0, 0);
		if (resultCount == Integer.MAX_VALUE) {
			resultCount = -1;
		}
		
		bw.write(String.valueOf(resultCount));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
