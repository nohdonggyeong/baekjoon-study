package backjoon.알고리즘_분류.유니온파인드.boj_1717_집합의_표현;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * https://www.geeksforgeeks.org/union-by-rank-and-path-compression-in-union-find-algorithm/
 */
public class Main {
	static int[] parent, rank;
	
	static void init(int n) {
		parent = new int[n + 1];
		rank = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}
	
	static int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]); // path compression
		}
		return parent[x];
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x == y) {
			return;
		}
		
		// union by rank
		if (rank[x] < rank[y]) {
			parent[x] = y;
		} else if (rank[x] > rank[y]) {
			parent[y] = x;
		} else { // rank[x] == rank[y]
			parent[y] = x;
			rank[x]++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			long start = System.currentTimeMillis();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			init(n);
			
			String op;
			int a, b;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				op = st.nextToken();
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				switch(op) {
				case "0":
					union(a, b);
					break;
				case "1":
					a = find(a);
					b = find(b);
					sb.append(a == b ? "YES" : "NO").append("\n");
					break;
				default:
					break;
				}
			}
			
			bw.write(sb.deleteCharAt(sb.length() - 1).toString());
			bw.flush();
			
			long end = System.currentTimeMillis();
			System.out.println();
			System.out.println("Runtime: " + (end - start) + "ms");
		}
	}
}
