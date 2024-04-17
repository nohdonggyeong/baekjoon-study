package backjoon.알고리즘_분류.유니온파인드.boj_10216_count_circle_groups;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class MainBak {
	static class Node {
		int x;
		int y;
		int R;
		
		Node(int x, int y, int R) {
			this.x = x;
			this.y = y;
			this.R = R;
		}
	}
	
	static int T, N, x, y, R;
	static Node[] nodes;
	static int[] parent, rank;
	static Set<Integer> parentSet;
	
	static int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x == y) {
			return;
		}
		
		if (rank[x] < rank[y]) {
			parent[x] = y;
		} else if (rank[y] < rank[x]) {
			parent[y] = x;
		} else {
			parent[y] = x;
			++rank[x];
		}
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
				parent = new int[N];
				rank = new int[N];
				
				for (int i = 0; i < N; i++) {
					st = new StringTokenizer(br.readLine());
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					R = Integer.parseInt(st.nextToken());
					
					nodes[i] = new Node(x, y, R);
					parent[i] = i;
					rank[i] = 0;
				}
				
				Node nodeI, nodeJ;
				for (int i = 0; i < N - 1; i++) {
					for (int j = i + 1; j < N; j++) {
						nodeI = nodes[i];
						nodeJ = nodes[j];

						if (find(i) == find(j)) {
							continue;
						}
						
						if (Math.pow(nodeI.x - nodeJ.x, 2) + Math.pow(nodeI.y - nodeJ.y, 2) <= Math.pow(nodeI.R + nodeJ.R, 2)) {
							union(i, j);
						}
					}
				}
				
				parentSet = new HashSet<>();
				for (int num : parent) {
					parentSet.add(find(num));
				}
				
				sb.append(parentSet.size()).append("\n");
			}
			
			bw.write(sb.deleteCharAt(sb.length() - 1).toString());
			bw.flush();
		}
	}
}
