package backjoon.알고리즘_분류.세그먼트트리.boj_6549_히스토그램에서_가장_큰_직사각형;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[] heights, tree;
	static long resultArea;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringBuilder sb = new StringBuilder();
			
			StringTokenizer st;
			while (true) {
				st = new StringTokenizer(br.readLine());
				n = Integer.parseInt(st.nextToken());
				if (n == 0) {
					break;
				}
				
				heights = new int[n + 1];
				for (int i = 1; i <= n; i++) {
					heights[i] = Integer.parseInt(st.nextToken());
				}
				
				tree = new int[n * 4];
				init(1, n, 1);
				
				resultArea = getMaxArea(1, n);
				
				sb.append(resultArea).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static int init(int start, int end, int node) {
		if (start == end) {
			return tree[node] = start;
		}
		
		int mid = (start + end) / 2;
		int leftNode = init(start, mid, node * 2);
		int rightNode = init(mid + 1, end, node * 2 + 1);
		return tree[node] = heights[leftNode] <= heights[rightNode] ? leftNode : rightNode;
	}
	
	static int query(int start, int end, int node, int left, int right) {
		if (left > end || right < start) {
			return -1;
		}
		
		if (left <= start && right >= end) {
			return tree[node];
		}
		
		int mid = (start + end) / 2;
		int leftNode = query(start, mid, node * 2, left, right);
		int rightNode = query(mid + 1, end, node * 2 + 1, left, right);
		
		if (leftNode == -1) {
			return rightNode;
		} else if (rightNode == -1) {
			return leftNode;
		} else {
			return heights[leftNode] <= heights[rightNode] ? leftNode : rightNode;
		}
	}
	
	static long getMaxArea(int left, int right) {
		if (left == right) {
			return heights[left];
		}
		
		int lowestNode = query(1, n, 1, left, right);
		long lowestArea = (long) heights[lowestNode] * (long) (right - left + 1);
		// int끼리의 곱셈을 long에 담더라도 long으로 변환하면서 연산해여 오버플로가 발생하지 않음
		// https://gist.github.com/singun/ad10e770a68ea54f9067
		long maxArea = lowestArea;
		
		if (left < lowestNode) {
			long leftArea = getMaxArea(left, lowestNode - 1);
			maxArea = Math.max(maxArea, leftArea);
		}
		if (right > lowestNode) {
			long rightArea = getMaxArea(lowestNode + 1, right);
			maxArea = Math.max(maxArea, rightArea);
		}
		
		return maxArea;
	}
}
