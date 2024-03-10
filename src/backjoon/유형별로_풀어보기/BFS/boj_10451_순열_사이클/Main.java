package backjoon.유형별로_풀어보기.BFS.boj_10451_순열_사이클;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int getCycleCnt(int[] arr) {
		int cycleCnt = 0;
		int checkCnt = 0;
		boolean[] visited = new boolean[arr.length];
		Queue<Integer> queue = new LinkedList<>();
		
		int start = 1;
		queue.offer(arr[0]);
		visited[0] = true;
		checkCnt++;
		
		while (!queue.isEmpty()) {
			int index = queue.poll();
			if (index == start) {
				cycleCnt++;
				if (checkCnt < visited.length) {
					for (int i = 0; i < visited.length; i++) {
						if (!visited[i]) {
							start = i + 1;
							index = i + 1;
							break;
						}
					}
				} else {
					break;
				}
			}
			if (!visited[index - 1]) {
				queue.offer(arr[index - 1]);
				visited[index - 1] = true;
				checkCnt++;
			}
		}
		return cycleCnt;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			
			bw.write(String.valueOf(getCycleCnt(arr)));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
