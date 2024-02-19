package 알고리즘.세그먼트트리.boj_11505_구간_곱_구하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static long[] nums, tree;
	static final int MOD =  1_000_000_007;
	
	static long init(int start, int end, int node) {
		if (start == end) {
			return tree[node] = nums[start];
		}
		
		int mid = (start + end) / 2;
		return tree[node] = (init(start, mid, node * 2) * init(mid + 1, end, node * 2 + 1)) % MOD;
	}
	
	static long update(int start, int end, int node, int index, long num) {
		if (index < start || index > end) {
			return tree[node];
		}
		
		if (start == end) {
			return tree[node] = num;
		}
		
		int mid = (start + end) / 2;
		return tree[node] = update(start, mid, node * 2, index, num) * update(mid + 1, end, node * 2 + 1, index, num) % MOD;
	}
	
	static long query(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return 1;
		}
		
		if (left <= start && right >= end) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		return query(start, mid, node * 2, left, right) * query(mid + 1, end, node * 2 + 1, left, right) % MOD;
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
				nums[n] = Integer.parseInt(br.readLine());
			}
			
			tree = new long[N * 4];
			init(1, N, 1);
			
			int a, b;
			long c, mul;
			for (int i = 0; i < M + K; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Long.parseLong(st.nextToken());
				
				if (a == 1) {
					update(1, N, 1, b, c);
					nums[b] = c;
				} else if (a == 2) {
					mul = query(1, N, 1, b, (int) c);
					sb.append(mul % 1000000007).append("\n");
				}
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
