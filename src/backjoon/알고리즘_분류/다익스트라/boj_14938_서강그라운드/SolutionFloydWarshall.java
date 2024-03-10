package backjoon.알고리즘_분류.다익스트라.boj_14938_서강그라운드;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SolutionFloydWarshall {
	static int n, m, r;
	static int[] items;
	static int[][] adjArr;
	
	static final int INF = 1501;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			
			items = new int[n + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				items[i] = Integer.parseInt(st.nextToken());
			}
			
			adjArr = new int[n + 1][n + 1];
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (i != j) {
						adjArr[i][j] = INF;
					}
				}
			}
			
			int a, b, l;
			for (int i = 0; i < r; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				l = Integer.parseInt(st.nextToken());
				
				adjArr[a][b] = l;
				adjArr[b][a] = l;
			}
			
			for (int k = 1; k <= n; k++) {
				for (int i = 1; i <= n; i++) {
					if (i == k) {
						continue;
					}
					for (int j = 1; j <= n; j++) {
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
			for (int i = 1; i <= n; i++) {
				int count = 0;
				for (int j = 1; j <= n; j++) {
					if (adjArr[i][j] <= m) {
						count += items[j];
					}
				}
				result = Math.max(result, count);
			}
			
			bw.write(String.valueOf(result));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
