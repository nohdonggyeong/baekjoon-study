package 알고리즘.DFS.boj_10971_외판원_순회_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] W;
	static boolean[] visited;
	static int minValue;
	
	static void backTracking(int start, int now, int sum) {
		if (checkAllVisited()) {
			if (W[now][start] != 0) {
				sum += W[now][start];
				minValue = Math.min(minValue, sum);
			}
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (W[now][i] != 0 && !visited[i]) {
				visited[i] = true;
				backTracking(start, i, sum + W[now][i]);
				visited[i] = false;
			}
		}
	}
	
	static boolean checkAllVisited() {
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		W = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		minValue = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			
			visited[i] = true;
			backTracking(i, i, 0);
		}
		
		bw.write(String.valueOf(minValue));
		bw.flush();
		bw.close();
		br.close();
	}

}
