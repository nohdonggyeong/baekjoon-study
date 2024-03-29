package backjoon.알고리즘_분류.세그먼트트리.boj_10999_구간_합_구하기_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main4 {
	static int N, M, K;
	static long[] nums, tree, lazy;
	
	static long init(int start, int end, int node) {
		if (start == end) {
			return tree[node] = nums[start];
		}
		
		int mid = (start + end) / 2;
		return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
	}
	
	static void propagate(int start, int end, int node) {
		if (lazy[node] != 0) {
			if (start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			
			tree[node] += lazy[node] * (end - start + 1);
			lazy[node] = 0;
		}
	}
	
	static void update(int start, int end, int node, int left, int right, long diff) {
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
	
	static long sum(int start, int end, int node, int left, int right) {
		propagate(start, end, node);
		
		if (left > end || right <start) {
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
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			nums = new long[N + 1];
			for (int n = 1; n <= N; n++) {
				nums[n] = Long.parseLong(br.readLine());
			}
			
			tree = new long[N * 4];
			lazy = new long[N * 4];
			init(1, N, 1);
			
			int a, b, c;
			long d, sum;
			for (int i = 0; i < M + K; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				if (a == 1) {
					d = Long.parseLong(st.nextToken());
					update(1, N, 1, b, c, d);
				} else if (a == 2) {
					sum = sum(1, N, 1, b, c);
					sb.append(sum).append("\n");
				}
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
