package backjoon.알고리즘_분류.DFS.boj_17471_게리멘더링;

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
	static int N;
	static int[] peoples;
	static List<List<Integer>> graph;
	static boolean[] selected;
	static boolean[] visited;
	static int minDiffResult;

	static void divide(int index) {
		if (index == N) {
			List<Integer> areaAList = new ArrayList<>();
			List<Integer> areaBList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				if (selected[i]) {
					areaAList.add(i);
				} else {
					areaBList.add(i);
				}
			}
			if (areaAList.size() == 0 || areaBList.size() == 0) {
				return;
			}
			
			if (isLinked(areaAList) && isLinked(areaBList)) {
				getDiff();
			}
			return;
		}
		
		selected[index] = true;
		divide(index + 1);
		selected[index] = false;
		divide(index + 1);
	}
	
	static boolean isLinked(List<Integer> areaList) {
		Queue<Integer> queue = new LinkedList<>();
		visited = new boolean[N];
		visited[areaList.get(0)] = true;
		queue.offer(areaList.get(0));
		
		int cnt = 1;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 0; i < graph.get(cur).size(); i++) {
				int next = graph.get(cur).get(i);
				if (areaList.contains(next) && !visited[next]) {
					queue.offer(next);
					visited[next] = true;
					cnt++;
				}
			}
		}
		if (cnt == areaList.size()) {
			return true;
		}
		return false;
	}
	
	static void getDiff() {
		int sumAreaA = 0;
		int sumAreaB = 0;
		for (int i = 0; i < N; i++) {
			if (selected[i]) {
				sumAreaA += peoples[i];
			} else {
				sumAreaB += peoples[i];
			}
		}
		minDiffResult = Math.min(minDiffResult, Math.abs(sumAreaA - sumAreaB));
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		peoples = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			peoples[n] = Integer.parseInt(st.nextToken());
		}
		
		graph = new ArrayList<>();
		for (int n = 0; n < N; n++) {
			graph.add(new ArrayList<>());
		}
		
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int linkedCnt = Integer.parseInt(st.nextToken());
			for (int lc = 0; lc < linkedCnt; lc++) {
				graph.get(n).add(Integer.parseInt(st.nextToken()) - 1);
			}
		}
		
		selected = new boolean[N];
		minDiffResult = Integer.MAX_VALUE;
		divide(0);
		if (minDiffResult == Integer.MAX_VALUE) {
			minDiffResult = -1;
		}
		
		bw.write(String.valueOf(minDiffResult));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
