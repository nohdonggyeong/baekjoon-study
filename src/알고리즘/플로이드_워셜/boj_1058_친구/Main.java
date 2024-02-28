package 알고리즘.플로이드_워셜.boj_1058_친구;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] adjArr;
	static final int INF = 51;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {			
			N = Integer.parseInt(br.readLine());
			adjArr = new int[N + 1][N + 1];
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i != j) {
						adjArr[i][j] = INF;
					}
				}
			}
			
			String str;
			for (int i = 1; i <= N; i++) {
				str = br.readLine();
				for (int j = 1; j <= N; j++) {
					if (str.charAt(j - 1) == 'Y') {
						adjArr[i][j] = 1;
						adjArr[j][i] = 1;	
					}
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
						if (adjArr[i][j] > adjArr[i][k] + adjArr[k][j]) {
							adjArr[i][j] = adjArr[i][k] + adjArr[k][j];
						}
					}
				}
			}
			
			int count;
			int max = 0;
			for (int i = 1; i <= N; i++) {
				count = 0;
				for (int j = 1; j <= N; j++) {
					if (i != j && adjArr[i][j] <= 2) {
						count++;
					}
				}
				max = Math.max(max, count);
			}
			
			bw.write(String.valueOf(max));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
