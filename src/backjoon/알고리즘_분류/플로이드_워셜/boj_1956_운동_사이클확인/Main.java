package backjoon.알고리즘_분류.플로이드_워셜.boj_1956_운동_사이클확인;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int V, E;
	static int[][] adjArr;
	static int result;
	static final int INF = 1_000_000_000;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			adjArr = new int[V + 1][V + 1];
			for (int r = 1; r <= V; r++) {
				for (int c = 1; c <= V; c++) {
					if (r != c) {
						adjArr[r][c] = INF;
					}
				}
			}
			
			int a, b, c;
			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				adjArr[a][b] = c;
			}
			
			for (int k = 1; k <= V; k++) {
				for (int i = 1; i <= V; i++) {
					if (i == k) {
						continue;
					}
					for (int j = 1; j <= V; j++) {
						if (j == i || j == k) {
							continue;
						}
						
						if (adjArr[i][j] > adjArr[i][k] + adjArr[k][j]) {
							adjArr[i][j] = adjArr[i][k] + adjArr[k][j];
						}
					}
				}
			}
			
			result = INF;
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					if (i == j) {
						continue;
					}
					
					// 사이클이 발생은 a에서 b로 갈 수 있고, b에서 a로 갈 수 있는지 확인
					if (adjArr[i][j] != INF && adjArr[j][i] != INF) {
						result = Math.min(result, adjArr[i][j] + adjArr[j][i]);
					}
				}
			}
			
			bw.write(String.valueOf(result == INF ? -1 : result));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
