package backjoon.유형별로_풀어보기.벨만_포드.boj_11657_타임머신;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
	static int N, M;
	static Edge[] edges;
	static long[] dist;
	static final int INF = 500 *100_000;

	static boolean bellmanFord(int start) {
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		for (int i = 1; i <= N - 1; i++) {
			for (int j = 0; j < M; j++) {
				Edge edge = edges[j];
				if (dist[edge.start] != INF && dist[edge.end] > dist[edge.start]+ edge.weight) {
					dist[edge.end] = dist[edge.start] + edge.weight;
				}
			}
		}
		
		for (int i = 0; i < M; i++) {
			Edge edge = edges[i];
			if (dist[edge.start] != INF && dist[edge.end]> dist[edge.start] + edge.weight) {
				return false;
			}
		}
		
		return true;
	}
	
	static class Edge {
		int start;
		int end;
		int weight;
		
		Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringBuilder sb = new StringBuilder();
			StringTokenizer st;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			edges = new Edge[M];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				edges[i] = new Edge(start, end, weight);
			}
			
			dist = new long[N + 1];
			int start = 1;
			if (bellmanFord(start)) {
				for (int i = 2; i < N + 1; i++) {
					sb.append(dist[i] == INF ? "-1" : dist[i]).append("\n");
				}
			} else {
				sb.append(-1).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}