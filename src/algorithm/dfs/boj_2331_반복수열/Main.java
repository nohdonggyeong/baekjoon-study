package algorithm.dfs.boj_2331_반복수열;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static List<Integer> visited = new ArrayList<Integer>();
	private static int result = -1;
	
	private static void dfs(int A, int P) {
		int nextNum = 0;
		while (A > 0) {
			nextNum += Math.pow(A % 10, P);
			A /= 10;
		}
		
		if (visited.contains(nextNum)) {
			result = visited.indexOf(nextNum);
			return;
		} else {
			visited.add(nextNum);
			dfs(nextNum, P);
		}
	}
	
	public static void main(String args[]) throws IOException {
		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		// Process
		visited.add(A);
		dfs(A, P);

		// Output
		bw.write(String.valueOf(result));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
