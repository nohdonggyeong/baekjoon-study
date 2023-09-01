package algorithm.dfs.boj_15684_사다리_조작;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M, H;
	static int[][] ab;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		H = Integer.parseInt(br.readLine());
		ab = new int[M][2];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			ab[i][0] = Integer.parseInt(st.nextToken());
			ab[i][1] = Integer.parseInt(st.nextToken());
		}
	}
}
