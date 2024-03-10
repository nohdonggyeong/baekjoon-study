<<<<<<<< HEAD:src/backjoon/브루트포스/boj_14501_퇴사/Main.java
package backjoon.브루트포스.boj_14501_퇴사;
========
package 알고리즘.DFS.boj_14501_퇴사;
>>>>>>>> 935bf89da9220f22cd63a883e4aafb2cff611968:src/알고리즘/DFS/boj_14501_퇴사/Main.java

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] input;
	static boolean[] visited;
	static int maxIncome;
	
	static void dfs(int n, int income) {
		if (n > N) {
			maxIncome = Math.max(maxIncome, income);
			return;
		}
		
		if (n + input[n][0] - 1 <= N) {
			dfs(n + input[n][0], income + input[n][1]);
		}
		dfs(n + 1, income);
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		input = new int[N + 1][2];
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			input[n][0] = Integer.parseInt(st.nextToken());
			input[n][1] = Integer.parseInt(st.nextToken());
		}
		
		maxIncome = 0;
		dfs(1, 0);
		
		bw.write(String.valueOf(maxIncome));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
