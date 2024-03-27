 package backjoon.알고리즘_분류.플로이드_워셜.boj_2660_회장뽑기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] adjArr;
	static int[] count;
	static List<Integer> resultList;
	
	static final int INF = 50;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			N = Integer.parseInt(br.readLine());
			adjArr = new int[N + 1][N + 1];
			
			for (int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if (i != j) {
						adjArr[i][j] = INF;
					}
				}
			}
			int u, v;
			while (true) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				if (u == -1 && v == -1) {
					break;
				}
				
				adjArr[u][v] = 1;
				adjArr[v][u] = 1;
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
			
			count = new int[N + 1];
			int max;
			for (int i = 1; i <= N; i++) {
				max = 0;
				for (int j = 1; j <= N; j++) {
					max = Math.max(max, adjArr[i][j]);
				}
				count[i] = max;
			}
			
			int min = INF;
			for (int i = 1; i <= N; i++) {
				if (min > count[i]) {
					min = count[i];
					resultList = new ArrayList<Integer>();
					resultList.add(i);
				} else if (min == count[i]) {
					resultList.add(i);
				}
			}
			
			sb.append(min).append(" ").append(resultList.size()).append("\n");
			for (int el : resultList) {
				sb.append(el).append(" ");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
