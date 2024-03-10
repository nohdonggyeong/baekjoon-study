package backjoon.시뮬레이션.boj_17140_이차원_배열과_연산;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int r, c, k;
	static int rLen, cLen;
	static int[][] A;
	static int resultTime;
	
	static class Pair implements Comparable<Pair>{
		private int number;
		private int count;
		
		public Pair(int number, int count) {
			this.number = number;
			this.count = count;
		}

		@Override
		public int compareTo(Pair o) {
			if (count - o.count > 0) {
				return 1;
			} else if (count == o.count) {
				if (number - o.number > 0) {
					return 1;
				} else if (number == o.number) {
					return 0;
				}
			}
			return -1;
		}
	}
	
	static void doOperation() {
		// 가로 세로 길이 판별
		if (rLen >= cLen) { // R 연산
			for (int i = 1; i <= rLen; i++) {
				PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();
				Map<Integer, Integer> map = new HashMap<>();
				for (int j = 1; j <= cLen; j++) {
					if (A[i][j] == 0) {
						continue;
					}
					map.put(A[i][j], map.containsKey(A[i][j]) ? map.get(A[i][j]) + 1 : 1);
				}
				for (int el : map.keySet()) {
					priorityQueue.offer(new Pair(el, map.get(el)));
				}
				int index = 1;
				while (!priorityQueue.isEmpty()) {
					Pair pair = priorityQueue.poll();
					A[i][index++] = pair.number;
					A[i][index++] = pair.count;
				}
				cLen = Math.max(cLen, index - 1);
				while (index < 101) {
					A[i][index++] = 0;
				}
			}
		} else { // C 연산
			for (int i = 1; i <= cLen; i++) {
				PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();
				Map<Integer, Integer> map = new HashMap<>();
				for (int j = 1; j <= rLen; j++) {
					if (A[j][i] == 0) {
						continue;
					}
					map.put(A[j][i], map.containsKey(A[j][i]) ? map.get(A[j][i]) + 1 : 1);
				}
				for (int el : map.keySet()) {
					priorityQueue.offer(new Pair(el, map.get(el)));
				}
				int index = 1;
				while (!priorityQueue.isEmpty()) {
					Pair pair = priorityQueue.poll();
					A[index++][i] = pair.number;
					A[index++][i] = pair.count;
				}
				rLen = Math.max(rLen, index - 1);
				while (index < 101) {
					A[index++][i] = 0;
				}
			}
		}
		
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		rLen = 3;
		cLen = 3;
		A = new int[101][101];
		for (int i = 1; i <= 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 3; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		resultTime = -1;
		for (int t = 0; t < 101; t++) {
			if (A[r][c] == k) {
				resultTime = t;
				break;
			}
			doOperation();
			
//			System.out.println(t + 1);
//			for (int i = 0; i < 20; i++) {
//				for (int j = 0; j < 20; j++) {
//					System.out.print(A[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
		}

		bw.write(String.valueOf(resultTime));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
