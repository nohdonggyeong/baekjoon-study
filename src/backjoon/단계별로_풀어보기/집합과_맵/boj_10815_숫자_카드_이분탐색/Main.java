package backjoon.단계별로_풀어보기.집합과_맵.boj_10815_숫자_카드_이분탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[] arr;
	
	static boolean binarySearch(int num) {
		int leftIndex = 0;
		int rightIndex = n - 1;
		
		while (leftIndex <= rightIndex) {
			int middleIndex = (leftIndex + rightIndex) / 2;
			int middle = arr[middleIndex];
			
			if (num < middle) {
				rightIndex = middleIndex - 1;
			} else if (num > middle) {
				leftIndex = middleIndex + 1;
			} else {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (binarySearch(num)) {
				sb.append("1 ");
			} else {
				sb.append("0 ");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
