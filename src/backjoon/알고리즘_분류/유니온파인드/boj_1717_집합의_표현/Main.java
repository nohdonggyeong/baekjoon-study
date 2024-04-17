package backjoon.알고리즘_분류.유니온파인드.boj_1717_집합의_표현;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n, m, op, a, b;
	static int[] parent, rank;
	
	static void init(int n) {
		parent = new int[n + 1];
		rank = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a == b) {
			return;
		}
		
		if (rank[a] < rank[b]) {
			parent[a] = b;
		} else if (rank[b] < rank[a]) {
			parent[b] = a;
		} else {
			parent[b] = a;
			++rank[a];
		}
	}
	
	static int find(int a) {
		if (parent[a] != a) {
			parent[a] = find(parent[a]);
		}
		return parent[a];
	}
	
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			init(n);
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				op = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				switch (op) {
					case 0:
						union(a, b);
						break;
					case 1:
						sb.append(find(a) == find(b) ? "YES" : "NO").append("\n");
						break;
					default:
						break;
				}
			}
			
			bw.write(sb.deleteCharAt(sb.length() - 1).toString());
			bw.flush();
		}
	}
}
