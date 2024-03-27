package backjoon.알고리즘_분류.BFS.boj_1325_효율적인_해킹;

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

public class 시간초과 {
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
		long start = System.nanoTime();
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

//			for (int i = 1; i < resultArr.length; i++) {
//				System.out.println("i: " + resultArr[i]);
//			}
			for (int i = 1; i < resultArr.length; i++) {
				if (resultArr[i] == maxCount) {
					sb.append(i).append(" ");
				}
			}
			bw.write(sb.toString().trim());
			bw.flush();

			long end = System.nanoTime();
			System.out.println();
			System.out.println((end - start) / 1000000000.0 + "초");
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
}
