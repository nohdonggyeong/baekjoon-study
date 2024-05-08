package backjoon.알고리즘_분류.다이나믹프로그래밍.boj_1010_다리_놓기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int T, N, M;
	static int[] temp;
	static boolean[] visited;
	static List<int[]> output;
	
	static void combination(int start, int depth) {
		if (depth == N) {
			temp = new int[N];
			int index = 0;
			for (int i = 0; i < M; i++) {
				if (visited[i]) {
					temp[index++] = i;
				}
			}
			output.add(temp.clone());
			return;
		}
		
		for (int i = start; i < M; i++) {
			if (!visited[i]) {
				visited[i] = true;
				combination(i + 1, depth + 1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			T = Integer.parseInt(br.readLine());
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			while (T-- > 0) {
				st = new StringTokenizer(br.readLine());
				N = Integer.parseInt(st.nextToken());
				M = Integer.parseInt(st.nextToken());
				
				visited = new boolean[M];
				output = new ArrayList<>();
				combination(0, 0);
				sb.append(output.size()).append("\n");
			}
			bw.write(sb.deleteCharAt(sb.length() - 1).toString());
			bw.flush();
		}
	}
}
