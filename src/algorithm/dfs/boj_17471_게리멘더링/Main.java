package algorithm.dfs.boj_17471_게리멘더링;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] peopleCnt;
	static boolean[] visited;
	static List<List<Integer>> graph;
	static int minDiff;
	
	static void dfs(int node) {
		if (isAllTrue(visited)) {
			return;
		}
		
		if (seperatable()) {
			int total = 0;
			int sum = 0;
			for (int i = 1; i <= N; i++) {
				total += peopleCnt[i];
				if (visited[i]) {
					sum += peopleCnt[i];
				}
			}
			int diff = Math.abs(total - sum - sum);
			minDiff = Math.min(minDiff, diff);
		}
		
		for (int el : graph.get(node)) {
			if (!visited[el]) {
				visited[el] = true;
				dfs(el);
				visited[el] = false;
			}
		}
	}
	
	static boolean isAllTrue(boolean[] checkVisited) {
		for (int i = 1; i <= N; i++) {
			if (!checkVisited[i]) {
				return false;
			}
		}
		return true;
	}
	
	static boolean seperatable() {
		boolean[] tempVisited = visited.clone();
		int node = -1;
		for (int i = 1; i <= N; i++) {
			if (!tempVisited[i]) {
				node = i;
				break;
			}
		}
		tempVisited[node] = true;
		seperatableDfs(node, tempVisited);
		
		if (isAllTrue(tempVisited)) {
			return true;
		}
		return false;
	}
	
	static void seperatableDfs(int node, boolean[] tempVisited) {
		for (int el : graph.get(node)) {
			if (!tempVisited[el]) {
				tempVisited[el] = true;
				seperatableDfs(el, tempVisited);
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		peopleCnt = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			peopleCnt[n] = Integer.parseInt(st.nextToken());
		}
		
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int linkedCnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < linkedCnt; j++) {
				graph.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		minDiff = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			visited[i] = true;
			dfs(i);
		}
		
		if (minDiff == Integer.MAX_VALUE) {
			minDiff = -1;
		}
		
		bw.write(String.valueOf(minDiff));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
