package backjoon.최소신장트리.boj_1197_최소_스패닝_트리.kruskal_solution;

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
	static int V, E;
	
	static int[] parent;
	static int total;
	
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
	
	static boolean isSameParent(int a, int b) {
		a = find(a);
		b = find(b);
		return a == b;
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			parent = new int[V + 1];
			for (int i = 1; i <= V; i++) {
				parent[i] = i;
			}
			
			Queue<Edge> queue = new PriorityQueue<Edge>();
			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				queue.offer(new Edge(a, b, c));
			}
			
			int size = queue.size();
			int total = 0;
			for (int i = 0; i < size; i++) {
				Edge edge = queue.poll();
				if (!isSameParent(edge.u, edge.v)) {
					total += edge.weight;
					union(edge.u, edge.v);
				}
			}
			
			bw.write(String.valueOf(total));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
