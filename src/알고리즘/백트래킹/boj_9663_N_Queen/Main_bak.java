package 알고리즘.백트래킹.boj_9663_N_Queen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_bak {
	static int N, result = 0;
	static int[] arr;
	// 배열을 받을 때 2차원 배열로 해도 되지만, 1차원 배열로 받아도 된다.
	// 각 원소의 index를 '행'이라 생각하고, 원소 값을 '열'이라 생각하는 것이다. 예시: [2, 0, 3, 1]
	
	static void nQueen(int depth) {
		if (depth == N) {
			result++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			arr[depth] = i;
			if (isPossible(depth)) {
				nQueen(depth + 1);
			}
		}
	}
	
	static boolean isPossible(int row) {
		for (int i = 0; i < row; i++) {
			if (arr[row] == arr[i]) {
				return false;
			} else if (Math.abs(row - i) == Math.abs(arr[row] - arr[i])) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		nQueen(0);
		
		bw.write(String.valueOf(result));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
