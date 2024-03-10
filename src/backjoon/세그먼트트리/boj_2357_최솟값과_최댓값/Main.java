package backjoon.세그먼트트리.boj_2357_최솟값과_최댓값;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static long[] nums, treeMax, treeMin;
	
	static long initMax(int start, int end, int node) {
		if (start == end) {
			return treeMax[node] = nums[start];
		}
		
		int mid = (start + end) / 2;
		return treeMax[node] = Math.max(initMax(start, mid, node * 2), initMax(mid + 1, end, node * 2 + 1)); 
	}
	
	static long initMin(int start, int end, int node) {
		if (start == end) {
			return treeMin[node] = nums[start];
		}
		
		int mid = (start + end) / 2;
		return treeMin[node] = Math.min(initMin(start, mid, node * 2), initMin(mid + 1, end, node * 2 + 1));
	}
	
	static long queryMax(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return Integer.MIN_VALUE;
		}
		
		if (left <= start && right >= end) {
			return treeMax[node];
		}
		
		int mid = (start + end) / 2;
		return Math.max(queryMax(start, mid, node * 2, left, right), queryMax(mid + 1, end, node * 2 + 1, left, right));
	}
	
	static long queryMin(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return Integer.MAX_VALUE;
		}
		
		if (left <= start && right >= end) {
			return treeMin[node];
		}
		
		int mid = (start + end) / 2;
		return Math.min(queryMin(start, mid, node * 2, left, right), queryMin(mid + 1, end, node * 2 + 1, left, right));
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringBuilder sb = new StringBuilder();

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			nums = new long[N + 1];
			for (int n = 1; n <= N; n++) {
				nums[n] = Integer.parseInt(br.readLine());
			}
			
			treeMax = new long[N * 4];
			treeMin = new long[N * 4];
			initMax(1, N, 1);
			initMin(1, N, 1);
			
			int a, b;
			long max, min;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				max = queryMax(1, N, 1, a, b);
				min = queryMin(1, N, 1, a, b);
				sb.append(min).append(" ").append(max).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
