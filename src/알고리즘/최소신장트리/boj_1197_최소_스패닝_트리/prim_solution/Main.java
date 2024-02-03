package 알고리즘.최소신장트리.boj_1197_최소_스패닝_트리.prim_solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Next implements Comparable<Next>{
	int end;
	int weight;
	
	Next(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Next o) {
		return weight - o.weight;
	};
}

public class Main {
	static int V, E;
	
	static List<Next>[] adjList;
	static boolean[] visited;
	static int total;

	static void prim(int start) {
		Queue<Next> pq = new PriorityQueue<Next>();
		pq.offer(new Next(start, 0));
		
		visited = new boolean[V + 1];
		
		while (!pq.isEmpty()) {
			Next curNext = pq.poll();
			int cur = curNext.end;
			int curWeight = curNext.weight;
			
			if (visited[cur]) {
				continue;
			}
			visited[cur] = true;
			
			total += curWeight;
			
			for (Next next : adjList[cur]) {
				if (!visited[next.end]) {
					pq.offer(next);
				}
			}
		}
	}
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList[V + 1]; 
			for (int i = 1; i <= V; i++) {
				adjList[i] = new ArrayList<Next>();
			}
			
			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				
				adjList[A].add(new Next(B, C));
				adjList[B].add(new Next(A, C));
			}
			
			total = 0;
			prim(1);
			
			bw.write(String.valueOf(total));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
