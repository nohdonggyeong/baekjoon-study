package backjoon.알고리즘_분류.최소공통조상.boj_3584_가장_가까운_공통_조상;

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

public class Main {
	static int T, N;
	static List<Integer>[] adjList;
	static int root;
	static int[] parentArr, depthArr;
	static int result;
	
	static void BFS(int root) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(root);
		
		boolean[] visited = new boolean[N + 1];
		visited[root] = true;
		
		int nextLevel = 1;
		int count = 0;
		int nowSize = 1;
		while (!queue.isEmpty()) {
			int now = queue.poll();
			
			for (int next : adjList[now]) {
				if (!visited[next]) {
					visited[next] = true;
					queue.add(next);
					
					parentArr[next] = now;
					depthArr[next] = nextLevel;
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
		if (depthArr[a] < depthArr[b]) {
			int temp = b;
			b = a;
			a = temp;
		}
		
		while (depthArr[a] != depthArr[b]) {
			a = parentArr[a];
		}
		
		while (a != b) {
			a = parentArr[a];
			b = parentArr[b];
		}
		
		return a;
	}
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			T = Integer.parseInt(br.readLine());
			for (int t = 1; t <= T; t++) {
				N = Integer.parseInt(br.readLine());
				
				adjList = new ArrayList[N + 1];
				for (int n = 1; n <= N; n++) {
					adjList[n] = new ArrayList<Integer>();
				}
				
				int parent, child;
				boolean[] hasParent = new boolean[N + 1];
				for (int n = 1; n < N; n++) {
					st = new StringTokenizer(br.readLine());
					parent = Integer.parseInt(st.nextToken());
					child = Integer.parseInt(st.nextToken());
					
					hasParent[child] = true;
					adjList[parent].add(child);
				}
				
				for (int n = 1; n <= N; n++) {
					if (adjList[n].size() > 0 && !hasParent[n]) {
						root = n;
						break;
					}
				}
				
				parentArr = new int[N + 1];
				depthArr = new int[N + 1];
				
				BFS(root);
				
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
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
