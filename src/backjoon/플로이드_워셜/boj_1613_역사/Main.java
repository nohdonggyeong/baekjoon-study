package backjoon.플로이드_워셜.boj_1613_역사;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n, k, s;
	static int[][] adjArr;
	
	static final int INF = 200_000_001;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			StringBuilder sb = new StringBuilder();
			
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			adjArr = new int[n + 1][n + 1];
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (i != j) {
						adjArr[i][j] = INF;
					}
				}
			}
			
			int u, v;
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				adjArr[u][v] = 1;
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
							adjArr[i][j] = adjArr[i][k] +adjArr[k][j];
						}
					}
				}
			}
			
			s = Integer.parseInt(br.readLine());
			for (int i = 0; i < s; i++) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				
				if (adjArr[u][v] < INF) {
					sb.append("-1").append("\n");
				} else if (adjArr[v][u] < INF) {
					sb.append("1").append("\n");
				} else {
					sb.append("0").append("\n");
				}				
			}
			bw.write(sb.toString().trim());
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
