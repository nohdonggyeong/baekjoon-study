package 알고리즘.이진탐색.boj_2805_나무_자르기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] trees;
	static int low, high;
	
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			trees = new int[N];
			low = high = 0;
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				trees[n] = Integer.parseInt(st.nextToken());
				high = Math.max(high, trees[n]);
			}
			
			while (low <= high) {
				long sum = 0;
				int mid = (low + high) / 2;
				for(int height : trees) {
					if (height > mid) {
						sum += height - mid;
					}
				}
				
				if (sum >= M) {
					low = mid + 1;
				} else if (sum < M) {
					high = mid - 1;
				}
			}
			
			bw.write(String.valueOf(high));
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
