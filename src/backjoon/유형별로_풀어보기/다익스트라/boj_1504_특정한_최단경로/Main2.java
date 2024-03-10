package backjoon.유형별로_풀어보기.다익스트라.boj_1504_특정한_최단경로;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {
	static int N, E;
	static ArrayList<Next>[] graph; // 인접 리스트
	static int v1, v2; // 반드시 거쳐야 하는 정점
	
	static int[] dist; // 시작점에서 각 정점으로 가는 최단 거리
	static boolean[] check; // 방문 확인
	static final int INF = 200_000_000; // E 200,000 * c 1,000
	
	static class Next implements Comparable<Next> {
		private int end; // end node
		private int weight; // end node로 가는 가중치
		
		public Next (int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Next o) {
			return weight - o.weight;
		}
	}
	
	static int dijkstra(int start, int end) {
		PriorityQueue<Next> pq = new PriorityQueue<Main2.Next>();
		pq.offer(new Next(start, 0));
		
		check = new boolean[N + 1];
		
		// dist, check 선언
		dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		while (!pq.isEmpty()) {
			Next curNode = pq.poll();
			int cur = curNode.end;
			
			if (!check[cur]) {
				check[cur] = true;
				
				for (Next node : graph[cur]) {
					if (dist[node.end] > dist[cur] + node.weight) {
						dist[node.end] = dist[cur] + node.weight;
						pq.add(new Next(node.end, dist[node.end]));
					}
				}
			}
		}
		
		return dist[end];
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			// 정점과 간선 수 입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			// 양방향 인접 리스트 구현
			graph = new ArrayList[N + 1];
			for (int i = 1; i < N + 1; i++) {
				graph[i] = new ArrayList<Main2.Next>();
			}
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				graph[start].add(new Next(end, weight));
				graph[end].add(new Next(start,weight));
			}
			
			// 반드시 거쳐야 하는 정점 입력
			st = new StringTokenizer(br.readLine());
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			
			// 정점1 -> v1 -> v2 -> N으로 가는 경우
			int path1 = 0;
			path1 += dijkstra(1, v1);
			path1 += dijkstra(v1, v2);
			path1 += dijkstra(v2, N);
			
			// 정점2 -> v2 -> v1 -> N으로 가는 경우
			int path2 = 0;
			path2 += dijkstra(1, v2);
			path2 += dijkstra(v2, v1);
			path2 += dijkstra(v1, N);
			
			int result = path1 >= INF && path2 >= INF ? -1 : Math.min(path1, path2);
			bw.write(String.valueOf(result));
			bw.flush();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
