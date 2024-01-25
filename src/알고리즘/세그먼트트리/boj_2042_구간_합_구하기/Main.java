package 알고리즘.세그먼트트리.boj_2042_구간_합_구하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static long[] arr, tree;
	
	static long init(int start, int end, int node) {
		if (start == end) {
			tree[node] = arr[start];
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
		return tree[node];
	}
	
	static void update(int start, int end, int node, int index, long diff) {
		if (index < start || end < index) {
			return;
		}
		
		tree[node] += diff;
		if (start == end) {
			arr[index] = tree[node];
			return;
		}
		
		int mid = (start + end) / 2;
		update(start, mid, node * 2, index, diff);
		update(mid + 1, end, node * 2 + 1, index, diff);
	}
	
	static long sum(int start, int end, int node, int left, int right) {
		if (right < start || end < left) {
			return 0;
		}
		
		if (left <= start && end <= right) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			arr = new long[N + 1];
			for (int i = 1; i<= N; i++) {
				arr[i] = Long.parseLong(br.readLine());
			}
			
			tree = new long[N * 4 + 1];
			init(1, N, 1);
			
			for (int i = 0; i < M + K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				long c = Long.parseLong(st.nextToken());
				
				if (a == 1) {
					long diff = c - arr[b];
					arr[b] = c;
					update(1, N, 1, b, diff);
				} else if (a == 2) {
					sb.append(sum(1, N, 1, b, (int) c)).append("\n");
				}
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
