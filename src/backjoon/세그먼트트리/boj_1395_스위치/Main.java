package backjoon.세그먼트트리.boj_1395_스위치;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] tree, lazy;
	
	static void propagate(int start, int end, int node) {
		if (lazy[node] % 2 == 0) { // 켜고 끄면 원상태이므로 무의미함
			return;
		}
		
		if (lazy[node] != 0) {
			if (start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			
			tree[node] = (end - start + 1) - tree[node];
			lazy[node] = 0;
		}
	}
	
	static void update(int start, int end, int node, int left, int right, int diff) {
		propagate(start, end, node);
		
		if (left > end || right < start) {
			return;
		}
		
		if (left <= start && right >= end) {
			lazy[node] = diff;
			propagate(start, end, node);
			return;
		}
		
		int mid = (start + end) / 2;
		update(start, mid, node * 2, left, right, diff);
		update(mid + 1, end, node * 2 + 1, left, right, diff);
		
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}
	
	static int sum(int start, int end, int node, int left, int right) {
		propagate(start, end, node);
		
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
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			tree = new int[N * 4];
			lazy = new int[N * 4];
			
			int O, S, T, count;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				O = Integer.parseInt(st.nextToken());
				S = Integer.parseInt(st.nextToken());
				T = Integer.parseInt(st.nextToken());
				
				if (O == 0) {
					update(1, N, 1, S, T, 1);
				} else if (O == 1) {
					count = sum(1, N, 1, S, T);
					sb.append(count).append("\n");
				}
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
