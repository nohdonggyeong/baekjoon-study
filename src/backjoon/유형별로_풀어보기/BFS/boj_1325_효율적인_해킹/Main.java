package backjoon.유형별로_풀어보기.BFS.boj_1325_효율적인_해킹;

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
	static int N, M;
	static List<Integer>[] graph;
	static int[] countArr;
	static int maxCount;
	static Queue<Integer> queue;
	static boolean[] visited;
	
	static void bfs(int n) {
		queue = new LinkedList<>();
		queue.offer(n);
		
		visited = new boolean[N + 1];
		visited[n] = true;
		
		while (!queue.isEmpty()) {
			int num = queue.poll();
			
			for (int el : graph[num]) {
				if (!visited[el]) {
					queue.offer(el);
					visited[el] = true;
					
					countArr[el] += 1;
					maxCount = Math.max(maxCount, countArr[el]);
				}
			}
		}
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
			
			graph = new ArrayList[N + 1];
			for (int n = 1; n <= N; n++) {
				graph[n] = new ArrayList<>();
			}
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				graph[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
			}
			
			countArr = new int[N + 1];
			maxCount = 0;
			for (int n = 1; n <= N; n++) {
				bfs(n);
			}

			for (int i = 1; i <= N; i++) {
				if (countArr[i] == maxCount) {
					sb.append(i).append(" ");
				}
			}
			bw.write(sb.toString().trim());
			bw.flush();
			
			long end = System.nanoTime();
			System.out.println();
			System.out.println((end - start) / 1000000000.0);
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
}
