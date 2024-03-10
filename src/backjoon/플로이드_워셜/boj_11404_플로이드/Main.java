package backjoon.플로이드_워셜.boj_11404_플로이드;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[][] adjArr;
	static final int INF = 10_000_001;
	
	static void floydWarshall() {
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
	}
	
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());
			
			adjArr = new int[n + 1][n + 1];
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (i != j) {
						adjArr[i][j] = INF;
					}
				}
			}
			
			int a, b, c;
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				
				// adjArr[a][b] = c; <- 같은 경로에 여러 가중치가 주어지면 그 중 가장 작은 값만 남겨 저장해두어야 한다.
				adjArr[a][b] = Math.min(adjArr[a][b], c);
			}
			
			floydWarshall();
			
			for(int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (adjArr[i][j] == INF) { // 만약, i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력한다.
						sb.append(0).append(" ");
					} else {
						sb.append(adjArr[i][j]).append(" ");
					}
				}
				sb.deleteCharAt(sb.length() - 1);
				sb.append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
		}
	}
}
