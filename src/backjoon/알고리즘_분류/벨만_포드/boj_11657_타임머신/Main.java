package backjoon.알고리즘_분류.벨만_포드.boj_11657_타임머신;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int start;
	int end;
	int weight;

	Edge(int u , int v, int weight) {
		this.start = u;
		this.end = v;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Edge o) {
		return weight - o.weight;
	}
}

public class Main {
	static int N, M;
	static Edge[] edges;
	static long[] dist;
	static final int INF = 60_000_000;
	
	static boolean bellmanFord(int start) {
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		for (int n = 1; n <= N - 1; n++) {
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
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringBuilder sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			edges = new Edge[M];
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				edges[m] = new Edge(A, B, C);
			}
			
			dist = new long[N + 1];
			if (bellmanFord(1)) {
				for (int i = 2; i < N + 1; i++) {
					sb.append(dist[i] == INF ? "-1" : dist[i]).append("\n");
				}
			} else {
				sb.append("-1").append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}