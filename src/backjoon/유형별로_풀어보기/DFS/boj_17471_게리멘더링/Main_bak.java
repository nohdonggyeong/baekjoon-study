package backjoon.유형별로_풀어보기.DFS.boj_17471_게리멘더링;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_bak {
	static int N;
	static int[] peopleCnt;
	static boolean[][] linked;
	static boolean[] visited;
	static int minDiff;
	
	static void dfs(int node) {
		if (allVisited()) {
			return;
		}
		
		// 분리 가능하게 각각 연결된 상태였나
		if (seperatable()) {
			// 분리하고 차이 구하여 min값 구함
			int diff = getDiff();
			minDiff = Math.min(minDiff, diff);
			
			System.out.println();
			System.out.print("[");
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					System.out.print(i + " ");
				}
			}
			System.out.print("]");
			System.out.print(", [");
			for (int i = 0; i < N; i++) {
				if (!visited[i]) {
					System.out.print(i + " ");
				}
			}
			System.out.println("]");
			System.out.println(diff);
			System.out.println();
			
		}
		
		for (int i = 0; i < N; i++) {
			if (linked[node][i] && !visited[i]) {
				visited[i] = true;
				dfs(i);
				visited[i] = false;
			}
		}
	}
	
	static boolean allVisited() {
		for (int i = 0; i < N; i++) {
			if(!visited[i]) {
				return false;
			}
		}
		return true;
	}
	
	static void seperatableDfs(int node, boolean[] tempVisited) {
		for (int i = 0; i < N; i++) {
			if (linked[node][i] && !tempVisited[i]) {
				tempVisited[i] = true;
				seperatableDfs(i, tempVisited);
			}
		}
	}
	
	static boolean seperatable() {
		// true 모두 연결되었고, false 모두 연결되었나
		boolean[] tempVisited = visited.clone();
		int node = -1;
		for (int i = 0; i < N; i++) {
			if(!tempVisited[i]) {
				node = i;
				break;
			}
		}

		tempVisited[node] = true;
		seperatableDfs(node, tempVisited);
		
		for (int i = 0; i < N; i++) {
			if(!tempVisited[i]) {
				return false;
			}
		}
		return true;
	}
	
	static int getDiff() {
		int total = 0;
		int sumTrue = 0;
		for (int i = 0 ; i < N; i++) {
			total += peopleCnt[i];
			if (visited[i]) {
				sumTrue += peopleCnt[i];
			}
		}
		return Math.abs(total - sumTrue - sumTrue);
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		peopleCnt = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			peopleCnt[n] = Integer.parseInt(st.nextToken());
		}
		
		linked = new boolean[N][N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int linkedCnt = Integer.parseInt(st.nextToken());
			for (int i = 0; i < linkedCnt; i++) {
				int linkedNum = Integer.parseInt(st.nextToken()) - 1;
				linked[n][linkedNum] = true;
				linked[linkedNum][n] = true;
			}
		}
		
//		System.out.println();
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(linked[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		visited = new boolean[N];
		minDiff = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			visited[i] = true;
			dfs(i);
			visited[i] = false;
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
