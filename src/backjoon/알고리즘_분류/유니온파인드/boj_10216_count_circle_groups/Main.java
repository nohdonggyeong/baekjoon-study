package backjoon.알고리즘_분류.유니온파인드.boj_10216_count_circle_groups;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static class Node implements Comparable<Node> {
		int x;
		int y;
		int R;
		
		Node(int x, int y, int R) {
			this.x = x;
			this.y = y;
			this.R = R;
		}
		
		@Override
		public int compareTo(Node o) {
			if (this.x == o.x) {
				if (this.y == o.y) {
					return Integer.compare(o.R, this.R);
				}
				return Integer.compare(this.y, o.y);
			}
			return Integer.compare(this.x, o.x);
		}
	}
	
	static int T, N, x, y, R;
	static Node[] nodes;
	static int[] parent, rank;
	static Set<Integer> countSet = new HashSet<Integer>();
	
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
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			T = Integer.parseInt(br.readLine());
			while (T-- > 0) {
				
				N = Integer.parseInt(br.readLine());
				nodes = new Node[N];
				
				for (int n = 0; n < N; n++) {
					st = new StringTokenizer(br.readLine());
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					R = Integer.parseInt(st.nextToken());
					nodes[n] = new Node(x, y, R);
				}
				
				Arrays.sort(nodes);

				parent = new int[N];
				rank = new int[N];
				for (int n = 0; n < N; n++) {
					parent[n] = n;
					rank[n] = 0;
				}
				
				for (int i = 0; i < N - 1; i++) {
					for (int j = i + 1; j < N; j++) {
						if (Math.pow(nodes[i].x - nodes[j].x, 2) + Math.pow(nodes[i].y - nodes[j].y, 2)
						<= Math.pow(nodes[i].R + nodes[j].R, 2)) {
							union(i, j);
						}
					}
				}
				
				for (int i = 0; i < N; i++) {
					parent[i] = find(parent[i]);
				}
				
				countSet.clear();
				for (int i = 0; i < N; i++) {
					countSet.add(parent[i]);
				}
				
				sb.append(countSet.size()).append("\n");
			}
			
			bw.append(sb.deleteCharAt(sb.length() - 1).toString());
			bw.flush();
		}
	}
}
