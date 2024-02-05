package 알고리즘.최소신장트리.boj_1647_도시_분할_계획.kruskal_solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int start;
	int end;
	int weight;
	
	Edge(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.weight, o.weight);
	}
}

public class Main {
	static int N, M;
	static int[] parent;
	static PriorityQueue<Edge> pq;
	static int total, max;
	
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
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			parent = new int[N + 1];
			for (int n = 1; n < N + 1; n++) {
				parent[n] = n;
			}
			
			int A, B, C;
			pq = new PriorityQueue<>();
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				A = Integer.parseInt(st.nextToken());
				B = Integer.parseInt(st.nextToken());
				C = Integer.parseInt(st.nextToken());
				
				pq.offer(new Edge(A, B, C));
			}
			
			int a, b;
			total = 0;
			max = 0;
			while (!pq.isEmpty()) {
				Edge edge = pq.poll();
				a = edge.start;
				b = edge.end;
				
				if (find(a) != find(b)) {
					union(a, b);
					total += edge.weight;
					max = Math.max(max, edge.weight);
				}
			}
			
			bw.write(String.valueOf(total - max));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
