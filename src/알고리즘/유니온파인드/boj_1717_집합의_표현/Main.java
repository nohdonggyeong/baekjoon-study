package 알고리즘.유니온파인드.boj_1717_집합의_표현;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[] parent;
	
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
		} else {
			return parent[a] = find(parent[a]);
		}
	}
	
	public static void main(String[] args) {
		try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringBuilder sb = new StringBuilder();
			StringTokenizer st;
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			parent = new int[n + 1];
			for (int i = 0; i < n + 1; i++) {
				parent[i] = i;
			}
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if (c == 0) {
					union(a, b);
				} else if (c == 1) {
					a = find(a);
					b = find(b);
					if (a == b) {
						sb.append("YES").append("\n");
					} else {
						sb.append("NO").append("\n");
					}
				}
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
