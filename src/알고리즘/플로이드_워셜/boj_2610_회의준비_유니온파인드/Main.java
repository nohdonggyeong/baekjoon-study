package 알고리즘.플로이드_워셜.boj_2610_회의준비_유니온파인드;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] parent;
	static int[][] adjArr;
	static Set<Integer> hs;
	static List<Integer> resultList;
	
	static final int INF = 101;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			N = Integer.parseInt(br.readLine());
			parent = new int[N + 1];
			for (int n = 1; n <= N; n++) {
				parent[n] = n;
			}

			adjArr = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i != j) {
						adjArr[i][j] = INF;
					}
				}
			}
			
			M = Integer.parseInt(br.readLine());
			int u, v;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());

				union(u, v);
				
				adjArr[u][v] = 1;
				adjArr[v][u] = 1;
			}
			
			hs = new HashSet<Integer>();
			for (int n = 1; n <= N; n++) {
				hs.add(find(n));
			}
			
			sb.append(hs.size()).append("\n");
			
			
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
			
			resultList = new ArrayList<Integer>();
			for (int el : hs) {
				int representative = 0;
				int min = Integer.MAX_VALUE;
				
				for (int n = 1; n <= N; n++) {	
					if (parent[n] == el) {
						int maxRange = 0;
						for (int j = 1; j <= N; j++) {
							if (adjArr[n][j] != INF) {
								maxRange = Math.max(maxRange, adjArr[n][j] );	
							}
						}
						
						if (maxRange < min) {
							representative = n;
							min = maxRange;
						}
					}
				}
				
				resultList.add(representative);
			}
			
			Collections.sort(resultList);
			
			for (int el : resultList) {
				sb.append(el).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a != b) {
			parent[b] = a;
		}
	}
	
	static int find(int a) {
		if (a == parent[a]) {
			return a;
		}
		return parent[a] = find(parent[a]);
	}
}
