package algorithm.dfs.boj_14888_연산자_끼워넣기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] A;
	static int[] opCnt; // calCnt: +, -, *, /
	static boolean[] visit;
	static int minResult = Integer.MAX_VALUE, maxResult = Integer.MIN_VALUE;
	
	static void dfs(int cal, int num) {
		if (num == A.length) {
			minResult = Math.min(minResult, cal);
			maxResult = Math.max(maxResult, cal);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if (opCnt[i] > 0) {
				opCnt[i]--;
				
				switch (i) {
				case 0:
					dfs(cal + A[num], num + 1);
					break;
				case 1:
					dfs(cal - A[num], num + 1);
					break;
				case 2:
					dfs(cal * A[num], num + 1);
					break;
				case 3:
					dfs(cal / A[num], num + 1);
					break;
				default:
					break;
				}
				
				opCnt[i]++;
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		opCnt = new int[4];
		A = new int[N];
		visit = new boolean[N - 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			opCnt[i] = Integer.parseInt(st.nextToken());
		}
		
		// Process
		dfs(A[0], 1);
		
		// Output
		bw.write (String.valueOf(maxResult));
		bw.newLine();
		bw.write(String.valueOf(minResult));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
