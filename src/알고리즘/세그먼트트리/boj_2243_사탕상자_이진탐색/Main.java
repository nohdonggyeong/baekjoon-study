package 알고리즘.세그먼트트리.boj_2243_사탕상자_이진탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static final int LAST_NUMBER = 1_000_000;
	static int n;
	static int[] tree;
	
	static int init(int start, int end, int node) {
		if (start == end) {
			return tree[node] = 0;
		}
		
		int mid = (start + end) / 2;
		return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
	}
	
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
	
	static int binarySearch(int start, int end, long order) {
		do {
			int mid = (start + end) / 2;
			long sum = sum(1, LAST_NUMBER, 1, 1, mid);
			
			if (sum < order) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		} while (start <= end);
		
		return start;
	}
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			tree = new int[LAST_NUMBER * 4];
			init(1, LAST_NUMBER, 1);
			
			n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				if (A == 1) {
					long B = Long.parseLong(st.nextToken());
					int number = binarySearch(1, LAST_NUMBER, B);
					sb.append(number).append("\n");
					update(1, LAST_NUMBER, 1, number, - 1);					
				} else if (A == 2) {
					int B = Integer.parseInt(st.nextToken());
					int C = Integer.parseInt(st.nextToken());
					update(1, LAST_NUMBER, 1, B, C);
				}
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
