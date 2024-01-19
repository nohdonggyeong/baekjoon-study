package 알고리즘.BFS.boj_1325_효율적인_해킹;

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

public class 시간초과_신뢰순서_바꾸기_전 {
// 방향 그래프(Directed Graph) 문제: A가 B를 신뢰 == B를 해킹하면, A도 해킹 가능
// 그래프 구현 2가지 방법
// 인접 행렬, 인접 리스트
// 리스트, 배열 속도 차이
// bfs, dfs 시간 비교
// 연결관계가 뻗어나가는 개수를 배열이나 리스트에 적고 가장 큰 수를 출력
	static int N, M;
	static List<List<Integer>> graph;
	
	static int bfs(int n) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(n);
		
		boolean[] visited = new boolean[N + 1];
		visited[n] = true;
		
		int hackedCount = 0;
		while (!queue.isEmpty()) {
			Integer num = queue.poll();
			
			List<Integer> nextList = graph.get(num);
			for(Integer el : nextList) {
				if (!visited[el]) {
					queue.offer(el);
					visited[el] = true;
					hackedCount += 1;
				}
			}
		}
		return hackedCount;
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList<List<Integer>>();
			for (int n = 0; n < N + 1; n++) {
				graph.add(new ArrayList<Integer>());
			}
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph.get(b).add(a);
			}
			
			int maxCount = Integer.MIN_VALUE;
			int[] resultArr = new int[N + 1];
			for (int n = 1; n <= N; n++) {
				resultArr[n] = bfs(n);
				maxCount = Math.max(maxCount, resultArr[n]);
			}
			
			for (int i = 1; i < resultArr.length; i++) {
				if (resultArr[i] == maxCount) {
					sb.append(i).append(" ");
				}
			}
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
}
