package 알고리즘.벨만_포드.boj_1865_웜홀;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {	
	static class Edge implements Comparable<Edge> {
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

	static int TC, N, M, W, S, E, T;
	static Edge[] edges;
	static int[] dist;
	static final int INF = 5_000_001;
	
	static boolean bellmanford(int start) {
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		for (int n = 1; n < N; n++) {
			for (int m = 0; m < 2 * M + W; m++) {
				Edge edge = edges[m];
				if (dist[edge.end] > dist[edge.start] + edge.weight) {
					dist[edge.end] = dist[edge.start] +edge.weight;
				}
			}
		}
		
		for (int m = 0; m < 2 * M + W; m++) {
			Edge edge = edges[m];
			if (dist[edge.start] != INF && dist[edge.end] > dist[edge.start] + edge.weight) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			TC = Integer.parseInt(br.readLine());
			for (int t = 1; t <= TC; t++) {
				st = new StringTokenizer(br.readLine());
				N = Integer.parseInt(st.nextToken());
				M = Integer.parseInt(st.nextToken());
				W = Integer.parseInt(st.nextToken());
				
				edges = new Edge[2 * M + W];
				int index = 0;
				for (int m = 0; m < M; m++) {
					st = new StringTokenizer(br.readLine());
					S = Integer.parseInt(st.nextToken());
					E = Integer.parseInt(st.nextToken());
					T = Integer.parseInt(st.nextToken());
					
					edges[index++] = new Edge(S, E, T);
					edges[index++] = new Edge(E, S, T);
				}
				
				for (int w = 0; w < W; w++) {
					st = new StringTokenizer(br.readLine());
					S = Integer.parseInt(st.nextToken());
					E = Integer.parseInt(st.nextToken());
					T = Integer.parseInt(st.nextToken());
					
					edges[index++] = new Edge(S, E, -T);
				}
				
				dist = new int[N + 1];
				boolean hasNegativeCycle = bellmanford(1);
				
				sb.append(hasNegativeCycle ? "YES" : "NO").append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
