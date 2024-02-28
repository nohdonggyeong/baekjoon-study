package PRO검정.N정류장;

import java.util.ArrayList;
import java.util.List;

public class UserSolution {
	static int n, r;
	static int[] input, temp;
	static boolean[] visited;
	static List<int[]> output;
	static int result;
	
	static void find(int[][] graph, int vertices, int start, int end, int m, int[] minCosts) {
		
		int[][] dist = floydWarshall(graph, vertices, start, end);		
		
		n = r = m;
		input = new int[n];
		for (int i = 0; i < n; i++) {
			input[i] = minCosts[i];
		}
		
		temp = new int[r];
		visited = new boolean[n];
		output = new ArrayList<int[]>();
		permutation(0);
		
		// 순열 돌며  Floyd–Warshall에서 구한 거리 합산의 최소 값을 출력
		result = 1000000000;
		for (int[] el : output) {			
			int sum = dist[start][el[0]];
			for (int i = 1; i < el.length; i++) {
				sum += dist[el[i - 1]][el[i]];
			}
			sum += dist[el[el.length - 1]][end];
			result = Math.min(result, sum);
		}
		
		System.out.println(result >= 1000000001 ? -1 : result);
	}
	
	static int[][] floydWarshall(int[][] graph, int N, int start, int end) {
		for (int k = 1; k <= N; k++) {
			if (k == start || k == end) {
				continue;
			}
			for (int i = 1; i <= N; i++) {
				if (i == k) {
					continue;
				}
				for (int j = 1; j <= N; j++) {
					if (j == k || j == i) {
						continue;
					}
					if (graph[i][j] > graph[i][k] + graph[k][j]) {
						graph[i][j] = graph[i][k] + graph[k][j];
					}
				}
			}
		}
		return graph;
	}
	
	static void permutation(int depth) {
		if (depth == r) {
			output.add(temp.clone());
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				temp[depth] = input[i];
				permutation(depth + 1);
				visited[i] = false;
			}
		}
	}
}
