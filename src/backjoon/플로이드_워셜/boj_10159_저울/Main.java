package backjoon.플로이드_워셜.boj_10159_저울;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] adjArr, adjArr2;
	
	static final int INF = 101;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			adjArr = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i != j) {
						adjArr[i][j] = INF;
					}
				}
			}
			
			adjArr2 = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i != j) {
						adjArr2[i][j] = INF;
					}
				}
			}
			
			int u , v;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				adjArr[v][u] = 1;
				adjArr2[u][v] = 1;
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
						if (adjArr2[i][j] > adjArr2[i][k] + adjArr2[k][j]) {
							adjArr2[i][j] = adjArr2[i][k] + adjArr2[k][j];
						}
					}
				}
			}
			
			int count;
			for (int i = 1; i <= N; i++) {
				count = 0;
				for (int j = 1; j <= N; j++) {
					if (adjArr[i][j] == INF && adjArr2[i][j] == INF) {
						count++;
					}
				}
				sb.append(count).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
