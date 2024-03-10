package backjoon.알고리즘_분류.플로이드_워셜.boj_1507_궁금한_민호;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] adjArr, origin;
	
	static final int INF = 50_000;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			
			N = Integer.parseInt(br.readLine());
			
			adjArr = new int[N + 1][N + 1];
			origin = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					origin[i][j] = adjArr[i][j] = Integer.parseInt(st.nextToken());
				}
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
						
						if (adjArr[i][j] > adjArr[i][k] +adjArr[k][j]) {
							bw.write("-1");
							bw.flush();
							return;
						}
							
						if (adjArr[i][j] == adjArr[i][k] + adjArr[k][j]) {
							origin[i][j] = INF;
						}
					}
				}
			}
			
			int result = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = i + 1; j <= N; j++) {
					if (origin[i][j] < INF) {
						result += origin[i][j];
					}
				}
			}
			
			bw.write(String.valueOf(result));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
