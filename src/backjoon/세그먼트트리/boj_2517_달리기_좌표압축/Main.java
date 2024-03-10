package backjoon.세그먼트트리.boj_2517_달리기_좌표압축;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
	static int N;
	static int[] origin, sorted, compressed;
	static Map<Integer, Integer> ranking;
	static int[] tree;
	
	static void update(int start, int end, int node, int index, int diff) {
		if (index < start || index > end) {
			return;
		}
		
		tree[node] += diff;
		
		if (start == end) {
			return;
		}
		
		int mid = (start + end) / 2;
		update(start, mid, node * 2, index, diff);
		update(mid + 1, end, node * 2 + 1, index, diff);
	}
	
	static int sum(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return 0;
		}
		
		if (left <= start && right >= end) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringBuilder sb = new StringBuilder();

			N = Integer.parseInt(br.readLine());
			origin = new int[N + 1];
			sorted = new int[N + 1];
			for (int n = 1; n <= N; n++) {
				origin[n] = sorted[n] = Integer.parseInt(br.readLine());
			}
			Arrays.sort(sorted);

			ranking = new HashMap<Integer, Integer>();
			for (int n = 1; n <= N; n++) {
				ranking.put(sorted[n], n);
			}

			compressed = new int[N + 1];
			for (int n = 1; n <= N; n++) {
				compressed[n] = ranking.get(origin[n]);
			}
			
			tree = new int[N * 4];
			int rank;
			for (int n = 1; n <= N; n++) {
				rank = sum(1, N, 1, compressed[n] + 1, N) + 1;
				sb.append(rank).append("\n");
				
				update(1, N, 1, compressed[n], 1);
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
