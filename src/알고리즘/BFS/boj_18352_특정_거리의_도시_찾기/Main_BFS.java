package 알고리즘.BFS.boj_18352_특정_거리의_도시_찾기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BFS {
	static int N, M, K, X;
	static List<Integer>[] graph;
	static List<Integer> resultList;
	
	static void bfs(int start, int distance) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(start, 0));
		
		boolean[] visited = new boolean[N + 1];
		visited[start] = true;
		
		while (!queue.isEmpty()) {
			Node nowNode = queue.remove();
			for (int next : graph[nowNode.city]) {
				if (visited[next]) {
					continue;
				}
				
				if (nowNode.dist + 1 == distance) {
					resultList.add(next);
				}
				
				visited[next] = true;
				queue.add(new Node(next, nowNode.dist + 1));
			}
		}
	}
	
	static class Node {
		int city;
		int dist;
		
		Node(int city, int dist) {
			this.city = city;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringBuilder sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[N + 1];
			for (int n = 1; n <= N; n++) {
				graph[n] = new ArrayList<Integer>();
			}
			
			int A, B;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				A = Integer.parseInt(st.nextToken());
				B = Integer.parseInt(st.nextToken());
				graph[A].add(B);
			}
			
			resultList = new ArrayList<Integer>();
			bfs(X, K);
			
			if (resultList.size() == 0) {
				bw.write("-1");
			} else {
				Collections.sort(resultList);
				for (int el : resultList) {
					sb.append(el).append("\n");
				}
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
