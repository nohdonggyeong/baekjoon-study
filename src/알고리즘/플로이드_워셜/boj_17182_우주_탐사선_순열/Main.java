package 알고리즘.플로이드_워셜.boj_17182_우주_탐사선_순열;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[][] adjArr;
	
	static int n, r;
	static int[] input, temp;
	static boolean[] visited;
	static List<int[]> output;
	static final int INF = 10_000;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			 StringTokenizer st = new StringTokenizer(br.readLine());
			 N = Integer.parseInt(st.nextToken());
			 K = Integer.parseInt(st.nextToken());
			 
			 adjArr = new int[N][N];
			 for (int i = 0; i < N; i++) {
				 for (int j = 0; j < N; j++) {
					 if (i != j) {
						 adjArr[i][j] = INF;
					 }
				 }
			 }
			 
			 for (int i = 0; i < N; i++) {
				 st = new StringTokenizer(br.readLine());
				 for (int j = 0; j < N; j++) {
					 adjArr[i][j] = Integer.parseInt(st.nextToken());
				 }
			 }
			 
			 for (int k = 0; k < N; k++) {
				 for (int i = 0; i < N; i++) {
					 if (i == k) {
						 continue;
					 }
					 for (int j = 0; j < N; j++) {
						 if (j == k || j == i) {
							 continue;
						 }
						 if (adjArr[i][j] > adjArr[i][k] + adjArr[k][j]) {
							 adjArr[i][j] = adjArr[i][k] + adjArr[k][j];
						 }
					 }
				 }
			 }
			 
			 r = n = N - 1; // 시작 지점 제외한 모든 노드의 개수
			 input = new int[n];
			 int index = 0;
			 for (int i = 0; i < n; i++) {
				 if (index == K) {
					 index++;
				 }
				 input[i] = index;
				 index++;
			 }
			 
			 temp = new int[r];
			 visited = new boolean[n];
			 output = new ArrayList<int[]>();
			 permutation(0);
			 
			 int minResult = Integer.MAX_VALUE;
			 int routeSum;
			 for (int[] el : output) {
				 routeSum = adjArr[K][el[0]];
				 for (int i = 1; i < el.length; i++) {
					 routeSum += adjArr[el[i - 1]][el[i]];
				 }
				 
				 minResult = Math.min(minResult, routeSum);
			 }
			 
			 bw.write(String.valueOf(minResult));
			 bw.flush();
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void permutation(int depth) {
		if (depth == r) {
			output.add(temp.clone());
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				temp[depth] = input[i];
				permutation(depth + 1);
				visited[i] = false;
			}
		}
	}
}
