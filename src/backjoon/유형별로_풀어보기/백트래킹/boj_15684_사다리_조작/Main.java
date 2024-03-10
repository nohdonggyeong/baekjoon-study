package backjoon.유형별로_풀어보기.백트래킹.boj_15684_사다리_조작;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.StringTokenizer;

public class Main {
	static int N, M, H;
	static boolean[][] map;
	static int minResult;
	
	static void backTracking(int r, int depth) {
		if (depth > 3) {
			return;
		}
		if (checkResult()) {
			minResult = Math.min(minResult, depth);
			return;
		}
		
		for (int i = r; i <= H; i++) {
			for (int j = 1; j < N; j++) {
				if (!map[i][j] && !map[i][j - 1]) {
					map[i][j] = true;
					backTracking(i, depth + 1);
					map[i][j] = false;
				}
			}
		}
	}
	
	static boolean checkResult() {
		for (int i = 1; i <= N; i++) {
			int temp = i;
			for (int j = 1; j <= H; j++) {
				if (map[j][temp]) {
					temp += 1;
				} else if (map[j][temp - 1]) {
					temp -= 1;
				}
			}
			if (i != temp) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String args[]) throws IOException {
		LocalDateTime start = LocalDateTime.now();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new boolean[H + 1][N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
		}
		
		minResult = Integer.MAX_VALUE;
		backTracking(1, 0);
		
		bw.write(String.valueOf(minResult > 3 ? -1 : minResult));
		bw.flush();
		
		LocalDateTime end = LocalDateTime.now();
		System.out.println();
		System.out.println("[Elapsed time: " + Duration.between(start, end).getSeconds() + " sec]");
		bw.close();
		br.close();
	}
}
