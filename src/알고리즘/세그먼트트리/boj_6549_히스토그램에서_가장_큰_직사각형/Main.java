package 알고리즘.세그먼트트리.boj_6549_히스토그램에서_가장_큰_직사각형;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[] nums, tree;
	static long maxWidth;
	static final int INF = 1_000_000_000;
	
	static int init(int start, int end, int node) {
		if (start == end) {
			return tree[node] = start;
		}
		
		int mid = (start + end) / 2;
		int leftMinIndex = init(start, mid, node * 2);
		int rightMinIndex = init(mid + 1, end, node * 2 + 1);
		
		return tree[node] = nums[leftMinIndex] < nums[rightMinIndex] ? leftMinIndex :rightMinIndex; 
	}
	
	static int query(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return INF;
		}
		
		if (left <= start && right >= end) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		int leftMinIndex = query(start, mid, node * 2, left, right);
		int rightMinIndex = query(mid + 1, end, node * 2 + 1, left, right);
		
		if (leftMinIndex == INF) {
			return rightMinIndex;
		} else if (rightMinIndex == INF) {
			return leftMinIndex;
		} else {
			return nums[leftMinIndex] < nums[rightMinIndex] ? leftMinIndex : rightMinIndex;
		}
	}
	
	static long getMaxWidth(int left, int right) {
		long maxWidth, tempWidth;
		int minIndex = query(1, n, 1, left, right);
		
		maxWidth = (long) (right - left + 1) * (long) nums[minIndex];
		
		if (left < minIndex) {
			tempWidth = getMaxWidth(left,  minIndex - 1);
			maxWidth = Math.max(maxWidth, tempWidth);
		}
		
		if (right > minIndex) {
			tempWidth = getMaxWidth(minIndex + 1, right);
			maxWidth = Math.max(maxWidth, tempWidth);
		}
		
		return maxWidth;
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			while (true) {
				st = new StringTokenizer(br.readLine());
				n = Integer.parseInt(st.nextToken());
				if (n == 0) {
					break;
				}
				
				nums = new int[n + 1];
				for (int i = 1; i <= n; i++) {
					nums[i] = Integer.parseInt(st.nextToken());
				}
				
				tree = new int[n * 4];
				init(1, n, 1);
				
				maxWidth = getMaxWidth(1, n);
				sb.append(maxWidth).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
