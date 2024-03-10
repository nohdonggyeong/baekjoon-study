package backjoon.플로이드_워셜.boj_2617_구슬_찾기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] adjArr;

	static final int INF = 100;

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			adjArr = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i != j) {
						adjArr[i][j] = INF;
					}
				}
			}

			int u, v;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				adjArr[u][v] = 1;
			}
			
			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					if (i == k) {
						continue;
					}
					for (int j = 1; j <= N; j++) {
						if (j == k || j == i) {
							continue;
						}
						if (adjArr[i][j] > adjArr[i][k] + adjArr[k][j]) {
							adjArr[i][j] = adjArr[i][k] + adjArr[k][j];
						}
					}
				}
			}
			
			int result = 0;
			int count;
			for (int i = 1; i <= N; i++) {
				count = 0;
				for (int j = 1; j <= N; j++) {
					if (adjArr[i][j] != 0 && adjArr[i][j] != INF) {
						count++;
					}
				}
				if (count >= (N + 1) / 2) {
					result++;
				}
			}
			for (int i = 1; i <= N; i++) {
				count = 0;
				for (int j = 1; j <= N; j++) {
					if (adjArr[j][i] != 0 && adjArr[j][i] != INF) {
						count++;
					}
				}
				if (count >= (N + 1) / 2) {
					result++;
				}
			}
			
			bw.write(String.valueOf(result));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
