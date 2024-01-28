package 알고리즘.다익스트라.boj_1916_최소비용_구하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Next implements Comparable<Next> {
	int end;
	int weight;
	
	Next(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Next o) {
		return weight - o.weight;
	}
}

public class Main {
	static int N, M;
	static List<Next>[] graph;
	static int[] dist;
	static final int INF = 100_000_000;
	static boolean[] visited;
	static PriorityQueue<Next> pq;
	
	static void dijkstra(int start) {
		pq = new PriorityQueue<Next>();
		pq.offer(new Next(start, 0));

		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		Arrays.fill(visited, false);
		
		while (!pq.isEmpty()) {
			Next curNode = pq.poll();
			int cur = curNode.end;
			
			if (visited[cur]) {
				continue;
			}
			
			visited[cur] = true;
			for (Next next : graph[cur]) {
				if (dist[next.end] > dist[cur] + next.weight) {
					dist[next.end] = dist[cur] + next.weight;
					pq.offer(new Next(next.end, dist[next.end]));
				}
			}
		}
	}
	 
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			graph = new ArrayList[N + 1];
			for (int i = 1; i < N + 1; i++) {
				graph[i] = new ArrayList<Next>();
			}
			
			for (int i = 0; i < M; i ++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				graph[start].add(new Next(end, weight));
			}
			
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			dist = new int[N + 1];
			visited = new boolean[N + 1];
			dijkstra(start);
			
			bw.write(String.valueOf(dist[end]));
			bw.flush();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
