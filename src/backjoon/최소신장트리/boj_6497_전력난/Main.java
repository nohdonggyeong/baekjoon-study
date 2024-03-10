package backjoon.최소신장트리.boj_6497_전력난;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int u;
	int v;
	int weight;
	
	Edge(int u, int v, int weight) {
		this.u = u;
		this.v = v;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Edge o) {
		return weight - o.weight;
	}
}

public class Main {
	static int m, n;
	static int[] parent;
	static Queue<Edge> queue;
	static int total, count;
	
	static int find(int a) {
		if (a == parent[a]) {
			return a;
		}
		return parent[a] = find(parent[a]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a != b) {
			parent[b] = a;
		}
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			while (true) {
				st = new StringTokenizer(br.readLine());
				m = Integer.parseInt(st.nextToken());
				n = Integer.parseInt(st.nextToken());
				if (m == 0 && n == 0) {
					break;
				}
				
				parent = new int[m + 1];
				for (int i = 1; i <= m; i++) {
					parent[i] = i;
				}
				
				queue = new PriorityQueue<Edge>();
				total = 0;
				for (int i = 0; i < n; i++) {
					st = new StringTokenizer(br.readLine());
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					int z = Integer.parseInt(st.nextToken());
					queue.offer(new Edge(x, y, z));
					total += z;
				}
				
				count = 0;
				while(!queue.isEmpty()) {
					Edge edge = queue.poll();
					
					int u = find(edge.u);
					int v = find(edge.v);
					if (u != v) {
						union(u, v);
						count += edge.weight;
					}
				}
				
				sb.append(total - count).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
