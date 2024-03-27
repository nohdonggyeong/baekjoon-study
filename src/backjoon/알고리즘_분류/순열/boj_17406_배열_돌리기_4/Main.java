package backjoon.알고리즘_분류.순열.boj_17406_배열_돌리기_4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] A;
	static List<int[]> rotation;
	
	static int n, r;
	static int[] input;
	static boolean[] visited;
	static int[] temp;
	static List<int[]> output;
	
	static int minResult;
	
	static void permutation(int depth) {
		if (depth == r) {
			output.add(temp.clone());
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				temp[depth] = input[i];
				permutation(depth + 1);
				visited[i] = false;
			}
		}
	}
	
	static void rotate(int[] rcs) {
		int rcsR = rcs[0];
		int rcsC = rcs[1];
		int rcsS = rcs[2];
		
		for (int s = rcsS; s > 0; s--) {
			int temp = A[rcsR - s][rcsC - s];
			
			for (int i = rcsR - s; i < rcsR + s; i++) {
				A[i][rcsC - s] = A[i + 1][rcsC - s];
			}
			
			for (int i = rcsC - s; i < rcsC + s; i++) {
				A[rcsR + s][i] = A[rcsR + s][i + 1];
			}
			
			for (int i = rcsR + s; i > rcsR - s; i--) {
				A[i][rcsC + s] = A[i - 1][rcsC + s];
			}
			
			for (int i = rcsC + s; i > rcsC - s; i--) {
				A[rcsR - s][i] = A[rcsR -s][i - 1];
			}
			
			A[rcsR - s][rcsC - s + 1] = temp;
		}
	}
	
	static int getArrayValue() {
		int arrayValue = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			arrayValue = Math.min(arrayValue, Arrays.stream(A[i]).sum());
		}
		return arrayValue;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		A = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] ABak = new int[N][M];
		for (int i = 0; i < N; i++) {
			ABak[i] = A[i].clone();
		}
		
		rotation = new ArrayList<>();
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			rotation.add(new int[] {Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())});
		}
		
		n = rotation.size();
		r = n;
		
		input = new int[n];
		for (int i = 0; i < n; i++) {
			input[i] = i;
		}
		
		visited = new boolean[n];
		temp = new int[r];
		output = new ArrayList<>();
		permutation(0);
		
		minResult = Integer.MAX_VALUE;
		for (int[] el : output) {
			for (int e : el) {
				rotate(rotation.get(e));

//				System.out.println();
//				for (int i = 0; i < N; i++) {
//					for (int j = 0; j < M; j++) {
//						System.out.print(A[i][j] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println();
				
			}
			minResult = Math.min(minResult, getArrayValue());
						
			for (int i = 0; i < N; i++) {
				A[i] = ABak[i].clone();
			}
		}
		
		bw.write(String.valueOf(minResult));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
