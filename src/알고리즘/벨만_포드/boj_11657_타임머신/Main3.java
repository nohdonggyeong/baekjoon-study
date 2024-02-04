package 알고리즘.벨만_포드.boj_11657_타임머신;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main3 {
	static int N, M;
	static Edge[] edges;
	static long[] dist;
	static final int INF = 500 * 10_000;
	
	static boolean bellmanFord(int start) {
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		for (int n = 0; n < N - 1; n++) {
			for (int m = 0; m < M; m++) {
				Edge edge = edges[m];
				if (dist[edge.start] != INF && dist[edge.end] > dist[edge.start] + edge.weight) {
					dist[edge.end] = dist[edge.start] + edge.weight;
				}
			}
		}
		
		for (int m = 0; m < M; m++) {
			Edge edge = edges[m];
			if (dist[edge.start] != INF && dist[edge.end] > dist[edge.start] + edge.weight) {
				return false;
			}
		}
		return true;
	}
	
	static class Edge {
		int start;
		int end;
		int weight;
		
		Edge (int start, int end, int weight) {
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
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edges[m] = new Edge(start, end, weight);
			}
			
			dist = new long[N + 1];
			int start = 1;
			if (bellmanFord(start)) {
				for (int n = 2; n < N + 1; n++) {
					if (dist[n] == INF) {
						sb.append("-1").append("\n");
					} else {
						sb.append(dist[n]).append("\n");
					}
				}
			} else {
				sb.append("-1").append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}