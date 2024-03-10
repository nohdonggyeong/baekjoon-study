package backjoon.세그먼트트리.boj_1275_커피숍2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, Q;
	static long[] nums, tree;
	
	static long init(int start, int end, int node) {
		if (start == end) {
			return tree[node] = nums[start];
		}
		
		int mid = (start + end) / 2;
		return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
	}
	
	
	static void update(int start, int end, int node, int index, long diff) {
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
	
	static long query(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return 0;
		}
		
		if (left <= start && right >= end) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		return query(start, mid, node * 2, left, right) + query(mid + 1, end, node * 2 + 1, left, right);
	}
	
	public static void main(String[] args) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringBuilder sb = new StringBuilder();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			nums = new long[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int n = 1; n <= N; n++) {
				nums[n] = Integer.parseInt(st.nextToken());
			}
			
			tree = new long[N * 4];
			init(1, N, 1);
			
			int x, y, a;
			long b, sum, diff;
			for (int q = 0; q < Q; q++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Long.parseLong(st.nextToken());
				
				if (x > y) {
					int temp = x;
					x = y;
					y = temp;
				}
				
				sum = query(1, N, 1, x, y);
				sb.append(sum).append("\n");
				
				diff = b - nums[a];
				nums[a] = b;
				update(1, N, 1, a, diff);
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
