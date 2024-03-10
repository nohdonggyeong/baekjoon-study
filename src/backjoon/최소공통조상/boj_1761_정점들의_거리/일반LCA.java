package backjoon.최소공통조상.boj_1761_정점들의_거리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 일반LCA {
	static int N, M;
	static List<Node>[] adjList;
	static int[] parent, depth;
	
	static void BFS() {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(1);
		
		boolean[] visited = new boolean[N + 1];
		visited[1] = true;
		
		int nextLevel = 1;
		int count = 0;
		int nowSize = 1;
		while (!queue.isEmpty()) {
			int now = queue.remove();
			
			for (Node next : adjList[now]) {
				if (!visited[next.end]) {
					visited[next.end] = true;
					queue.add(next.end);
					
					parent[next.end] = now;
					depth[next.end] = nextLevel;
				}
			}
			count++;
			
			if (count == nowSize) {
				nextLevel++;
				count = 0;
				nowSize = queue.size();
			}
		}
	}
	
	static int LCA(int a, int b) {
		int sum = 0;
		
		if (depth[a] < depth[b]) {
			int temp = b;
			b = a;
			a = temp;
		}
		
		while (depth[a] != depth[b]) {
			for (Node next : adjList[a]) {
				if (next.end == parent[a]) {
					sum += next.weight;
					break;
				}
			}
			a = parent[a];
		}
		
		while (a != b) {
			for (Node next : adjList[a]) {
				if (next.end == parent[a]) {
					sum += next.weight;
					break;
				}
			}
			for (Node next : adjList[b]) {
				if (next.end == parent[b]) {
					sum += next.weight;
					break;
				}
			}
			a = parent[a];
			b = parent[b];
		}
		
		return sum;
	}
	
	static class Node {
		int end;
		int weight;
		
		Node(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			N = Integer.parseInt(br.readLine());
			adjList = new ArrayList[N + 1];
			for (int n = 1; n <= N; n++) {
				adjList[n] = new ArrayList<Node>();
			}
			
			int u, v, w;
			for (int n = 1; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				w = Integer.parseInt(st.nextToken());
				
				adjList[u].add(new Node(v, w));
				adjList[v].add(new Node(u, w));
			}
			
			parent = new int[N + 1];
			depth = new int[N + 1];
			
			BFS();
			
			M = Integer.parseInt(br.readLine());
			int a, b, result;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				result = LCA(a, b);
				sb.append(result).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
