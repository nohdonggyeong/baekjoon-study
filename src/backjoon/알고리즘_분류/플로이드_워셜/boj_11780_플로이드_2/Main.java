package backjoon.알고리즘_분류.플로이드_워셜.boj_11780_플로이드_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n, m, a, b, c;
	static Path[][] adjArr;
	
	static final int INF = 100_001;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			n = Integer.parseInt(br.readLine());
			adjArr = new Path[n + 1][n + 1];
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (i != j) {
						adjArr[i][j] = new Path(INF);
					} else {
						adjArr[i][j] = new Path(0);
					}
				}
			}
			
			m = Integer.parseInt(br.readLine());
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				
				if (adjArr[a][b].dist > c) {
					adjArr[a][b] = new Path(c);
					adjArr[a][b].stopList.add(a);
					adjArr[a][b].stopList.add(b);	
				}
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
						if (adjArr[i][j].dist > adjArr[i][k].dist + adjArr[k][j].dist) {
							adjArr[i][j].dist = adjArr[i][k].dist + adjArr[k][j].dist;
							List<Integer> tempList = new ArrayList<Integer>();
							for (int el : adjArr[i][k].stopList) {
								tempList.add(el);
							}
							for (int o = 1; o < adjArr[k][j].stopList.size(); o++) {
								tempList.add(adjArr[k][j].stopList.get(o));
							}
							adjArr[i][j].stopList = tempList;
						}
					}
				}
			}
			
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					sb.append(adjArr[i][j].dist == INF ? 0 : adjArr[i][j].dist);
					if (j < n) {
						sb.append(" ");
					} else {
						sb.append("\n");
					}
				}
			}
			
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					sb.append(adjArr[i][j].stopList.size()).append(" ");
					for (int k = 0; k < adjArr[i][j].stopList.size(); k++) {
						sb.append(adjArr[i][j].stopList.get(k));
						if (k < adjArr[i][j].stopList.size() - 1) {
							sb.append(" ");
						}
					}
					sb.append("\n");
				}
			}
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static class Path {
		int dist;
		List<Integer> stopList;
		
		Path(int dist) {
			this.dist = dist;
			this.stopList = new ArrayList<Integer>();
		}
	}
}
