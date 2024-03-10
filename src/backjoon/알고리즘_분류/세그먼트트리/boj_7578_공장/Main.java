package backjoon.알고리즘_분류.세그먼트트리.boj_7578_공장;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] A;
	static Map<Integer, Integer> B;
	static long[] tree;
	static long total;
	
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
	
	static long sum(int start, int end, int node, int left, int right) {
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
			
			N = Integer.parseInt(br.readLine());
			A = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int n = 1; n <= N; n++) {
				A[n] = Integer.parseInt(st.nextToken());
			}
			
			B = new HashMap<Integer, Integer>();
			st = new StringTokenizer(br.readLine());
			for (int n = 1; n <= N; n++) {
				B.put(Integer.parseInt(st.nextToken()), n);
			}
			
			tree = new long[N * 4];
			
			total = 0;
			for (int n = 1; n <= N; n++) {
				int id = A[n];
				int index = B.get(id);
				total += sum(1, N, 1, index + 1, N);
				update(1, N, 1, index, 1);
			}
			
			bw.write(String.valueOf(total));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
