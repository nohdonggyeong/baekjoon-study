package backjoon.알고리즘_분류.다익스트라.boj_1238_파티;

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
	static int N, M, X;
	static List<Next>[] graph;
	static PriorityQueue<Next> pq;
	static int[] dist;
	static final int INF = 10_000_000;
	static boolean[] visited;
	static int maxDist;	
	
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
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[N + 1];
			for (int n = 1; n < N + 1; n++) {
				graph[n] = new ArrayList<Next>();
			}
			
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				graph[start].add(new Next(end, weight));
			}
			
			dist = new int[N + 1];
			visited = new boolean[N + 1];
			
			dijkstra(X);
			int[] returnDist = dist.clone();
			
			maxDist = 0;
			for (int i = 1; i <= N; i++) {
				dijkstra(i);
				
				if (dist[X] + returnDist[i] > maxDist) {
					maxDist  = dist[X] + returnDist[i];
				}
			}
			
			bw.write(String.valueOf(maxDist));
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
