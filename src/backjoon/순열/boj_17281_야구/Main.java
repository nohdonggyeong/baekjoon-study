package backjoon.순열.boj_17281_야구;

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
	static int N;
	static int[][] expectation;
	
	static int n, r;
	static int[] input;
	static boolean[] visited;
	static int[] temp;
	static List<int[]> output;
	
	static int[] baseCount;
	static int homeCount, outCount;
	
	static int maxCount;
	
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
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		expectation = new int[N][9];
		
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				expectation[n][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		n = 8;
		r = 8;
		
		input = new int[n];
		for (int i = 1; i < n; i++) {
			input[i] = i;
		}
		
		temp = new int[n];
		visited = new boolean[r];
		output = new ArrayList<>();
		permutation(0);
		
//		output= new ArrayList<>(Arrays.asList(new int[] {7, 6, 5, 4, 3, 0, 1, 2 }));
		maxCount = Integer.MIN_VALUE;
		for (int[] el : output) { // 선수 순서 경우의 수, 0번 선수는 4번 째면 따로 추가 할 것
			homeCount = 0;
			outCount = 0;
			baseCount = new int[3];
			int inning = 0;
			int index = 0;
			boolean favorite = true;
			while (true) {
				if (outCount >= 3) {
					if (inning >= N - 1) {
						break;
					}
					
					outCount = 0;
					baseCount = new int[3];
					inning += 1;
				}
				
				if (index >= 8) {
					index = 0;
				}
				
				int point = 0;
				if (index == 3 && favorite) {
					point = expectation[inning][0];
					favorite = false;
				} else {
					if (index == 2) {
						favorite = true;
					}
					point = expectation[inning][el[index] + 1];
					index++;
				}
				
				switch(point) {
				case 1:
					if (baseCount[2] > 0) {
						homeCount += baseCount[2];
						baseCount[2] = 0;
					}
					if (baseCount[1] > 0) {
						baseCount[2] += baseCount[1];
						baseCount[1] = 0;
					}
					if (baseCount[0] > 0) {
						baseCount[1] += baseCount[0];
						baseCount[0] = 0;
					}
					baseCount[0] += 1;
					break;
				case 2:
					if (baseCount[2] > 0) {
						homeCount += baseCount[2];
						baseCount[2] = 0;
					}
					if (baseCount[1] > 0) {
						homeCount += baseCount[1];
						baseCount[1] = 0;
					}
					if (baseCount[0] > 0) {
						baseCount[2] += baseCount[0];
						baseCount[0] = 0;
					}
					baseCount[1] += 1;
					break;
				case 3:
					if (baseCount[2] > 0) {
						homeCount += baseCount[2];
						baseCount[2] = 0;
					}
					if (baseCount[1] > 0) {
						homeCount += baseCount[1];
						baseCount[1] = 0;
					}
					if (baseCount[0] > 0) {
						homeCount += baseCount[0];
						baseCount[0] = 0;
					}
					baseCount[2] += 1;
					break;
				case 4:
					homeCount += baseCount[2];
					homeCount += baseCount[1];
					homeCount += baseCount[0];
					homeCount += 1;
					baseCount[2] = 0;
					baseCount[1] = 0;
					baseCount[0] = 0;
					break;
				case 0:
					outCount += 1;
					break;
				default:
					break;
				}
			}
			
//			System.out.println();
//			System.out.print("[");
//			for (int e : el) {
//				System.out.print(e + " ");
//			}
//			System.out.println("]");
//			System.out.println("homeCount: " + homeCount);
//			System.out.println();
			
			maxCount = Math.max(maxCount, homeCount);
			
		}
		
		bw.write(String.valueOf(maxCount));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
