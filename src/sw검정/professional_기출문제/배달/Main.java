package sw검정.professional_기출문제.배달;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N, M, D;
	static int[] restaurantLocation;
	static Restaurant[] tree;
	static long deliverableHomeCount, deliverablePairCount;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringBuilder sb = new StringBuilder();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			
			restaurantLocation = new int[N + 1];
			st = new StringTokenizer(br.readLine());
			for (int n = 1; n <= N; n++) {
				restaurantLocation[n] = Integer.parseInt(st.nextToken());
			}
			
			tree = new Restaurant[N * 4];
			init(1, N, 1);
			
			deliverableHomeCount = 0;
			deliverablePairCount = 0;
			int x, y, leftoverD;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				
				leftoverD = D - Math.abs(y);
				if (leftoverD >= 0) {
					int count = query(1, x - leftoverD, x + leftoverD).count;
					if (count > 0) {
						deliverableHomeCount += 1;
						deliverablePairCount += count;						
					}
				}
			}
			
			sb.append(deliverableHomeCount).append(" ").append(deliverablePairCount);
			bw.write(sb.toString());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static Restaurant init(int start, int end, int node) {
		if (start == end) {
			return tree[node] = new Restaurant(restaurantLocation[start], restaurantLocation[start], 1);
		}
		
		int mid = (start + end) / 2;
		Restaurant leftNode = init(start, mid, node * 2);
		Restaurant rightNode = init(mid + 1, end, node * 2 + 1);
		return tree[node] = new Restaurant(leftNode.leftMost, rightNode.rightMost, leftNode.count + rightNode.count);
	}
	
	static Restaurant query(int node, int left, int right) {
		if (left > tree[node].rightMost || right < tree[node].leftMost) {
			return new Restaurant(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
		}
		
		if (left <= tree[node].leftMost && right >= tree[node].rightMost) {
			return tree[node];
		}
		
		Restaurant leftNode = query(node * 2, left, right);
		Restaurant rightNode = query(node * 2 + 1, left, right);
		return new Restaurant(Math.min(leftNode.leftMost, rightNode.leftMost), Math.max(leftNode.rightMost, rightNode.rightMost), leftNode.count + rightNode.count);
	}
	
	static class Restaurant {
		int leftMost;
		int rightMost;
		int count;
		
		Restaurant(int leftMost, int rightMost, int count) {
			this.leftMost = leftMost;
			this.rightMost = rightMost;
			this.count = count;
		}
	}
}
