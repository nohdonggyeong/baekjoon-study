package backjoon.알고리즘_분류.플로이드_워셜.boj_2458_키_순서;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[][] check;
	static int result;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			check = new boolean[N + 1][N + 1];
			int u, v;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				
				check[u][v] = true;
			}
			
			floydWarshall(N);
			
			result = 0;
			for (int i = 1; i <= N; i++) {
				int count = 0;
				for (int j = 1; j <= N; j++) {
					if (check[i][j] || check[j][i]) {
						count++;
					}
				}
				
				if (count == N - 1) {
					result++;
				}
			}
			
			bw.write(String.valueOf(result));
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void floydWarshall(int N) {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if (i == k) {
					continue;
				}
				for (int j = 1; j <= N; j++) {
					if (j == k || j == i) {
						continue;
					}
					if (check[i][k] && check[k][j]) {
						check[i][j] = true;
					}
				}
			}
		}
	}
}
