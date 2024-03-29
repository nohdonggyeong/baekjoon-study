package backjoon.알고리즘_분류.플로이드_워셜.boj_1719_택배;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static Node[][] adjArr;
	
	static final int INF = 10_000_000;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringBuilder sb = new StringBuilder();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			adjArr = new Node[n + 1][n + 1];
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (i != j) {
						adjArr[i][j] = new Node(INF);
					}
				}
			}

			int u, v, w;
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				w = Integer.parseInt(st.nextToken());
				
				adjArr[u][v] = new Node(w, v);
				adjArr[v][u] = new Node(w, u);
			}
			
			for (int k = 1; k <= n; k++) {
				for (int i = 1; i <= n; i++) {
					if (i == k) {
						continue;
					}
					for(int j = 1; j <= n; j++) {
						if (j == k || j == i) {
							continue;
						}
						if (adjArr[i][j].dist > adjArr[i][k].dist + adjArr[k][j].dist) {
							adjArr[i][j] = new Node (adjArr[i][k].dist + adjArr[k][j].dist, adjArr[i][k].next);
						}
					}
				}
			}
			
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (i == j) {
						sb.append("-");
					} else {
						sb.append(adjArr[i][j].next);
					}
					
					if (j < n) {
						sb.append(" ");
					} else {
						sb.append("\n");
					}
				}
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static class Node {
		int dist;
		int next;
		
		Node(int dist) {
			this.dist = dist;
		}
		
		Node(int dist, int next) {
			this.dist = dist;
			this.next = next;
		}
	}
}
