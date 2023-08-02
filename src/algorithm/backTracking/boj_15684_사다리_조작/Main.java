package algorithm.backTracking.boj_15684_사다리_조작;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M, H;
	static boolean[][] map;
	static int minResult;
	
	static void backTracking(int depth) {
		if (depth > 3) {
			minResult = -1;
			return;
		}
		if (checkResult()) {
			minResult = Math.min(minResult, depth);
			return;
		}
		
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				if (!map[i][j] && !map[i - 1][j]) {
					map[i][j] = true;
					backTracking(depth + 1);
					map[i][j] = false;
				}
			}
		}
	}
	
	static boolean checkResult() {
		int checkSum = 0;
		for (int i = 1; i <= N; i++) {
			int temp = i;
			for (int j = 1; j <= M; j++) {
				if (map[j][i]) {
					temp += 1;
				} else if (map[j][i - 1]) {
					temp -= 1;
				}
			}
			if (i == temp) {
				checkSum++;
			}
		}
		return checkSum == N ? true : false;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new boolean[M + 1][N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
		}
		
		minResult = Integer.MAX_VALUE;
		backTracking(0);
		
		bw.write(String.valueOf(minResult));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
